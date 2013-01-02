package yeene.kallisto.gui;

import mockit.Deencapsulation;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yeene.kallisto.gui.driver.GraphicFrame;
import yeene.kallisto.gui.driver.GraphicsFactory;
import yeene.kallisto.gui.simulation.ThreadPool;

import static org.fest.assertions.Assertions.assertThat;
import static yeene.kallisto.gui.Configuration.CONF_THREADPOOL_SIZE;

/**
 * @author yeene
 */
public class KallistoGUITest {

  private KallistoGUI gui;

  @BeforeMethod
  public void setup() {
    Deencapsulation.setField(KallistoGUI.class, "instance", null);

    gui = KallistoGUI.getInstance();
  }

  @Test
  public void init_usesOutputMethodFromConfiguration() throws Exception {
    // fixture:
    new NonStrictExpectations() {
      @Mocked Configuration configuration;
      @Mocked GraphicFrame frame;
      @Mocked ThreadPool threadPool;
      @Mocked(methods = "getInstance") GraphicsFactory graphicsFactory = null;
      {
        Configuration.loadConfiguration((String[]) any);
        minTimes = 1;
        result = new Delegate() {
          Configuration loadConfiguration(final String[] confString) {
            assertThat(confString).describedAs("configuration string").containsOnly("--gui", "opengl");
            return configuration;
          }
        };

        new ThreadPool(2);
        times = 1;

        configuration.getIntValueFor(CONF_THREADPOOL_SIZE, 2);
        minTimes = 1;
        result = 2;

        configuration.getValueFor(Configuration.CONF_OUTPUT_METHOD);
        minTimes = 1;
        result = "opengl";

        GraphicsFactory.getInstance();
        result = frame;
        minTimes = 1;

        frame.init(anyInt, anyInt, anyInt, anyString);
        times = 1;
      }
    };

    // execution: call the init function...
    gui.init(new String[] { "--gui", "opengl" });

    // assertion: by expectations.
  }

}
