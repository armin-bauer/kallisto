package yeene.kallisto.gui.driver.opengl;

import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.testng.annotations.BeforeMethod;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author armin
 */
public class OpenGLGraphicsTest {

  public static final String WINDOW_NAME = "window name";
  public static final int DESIRED_MAXX = 1024;
  public static final int DESIRED_MAXY = 768;
  private OpenGLGraphics opengl;

  @BeforeMethod
  public void setUp() throws Exception {
    opengl = new OpenGLGraphics();
  }


 // @Test
  public void init_usesDisplay(final @Mocked Display display) throws Exception {
    // fixture: make assertions through the
    new NonStrictExpectations() {{
      Display.create();
      times = 1;

      Display.setDisplayMode((DisplayMode) any);
      times = 1;
      result = new Delegate() {
        void setDisplayMode(final DisplayMode mode) {
          assertThat(mode.getWidth()).describedAs("width").isEqualTo(DESIRED_MAXX);
          assertThat(mode.getHeight()).describedAs("height").isEqualTo(DESIRED_MAXY);
        }
      };

      Display.setTitle(WINDOW_NAME);
      times = 1;
    }};

    // execution: call init method with given parameters
    opengl.init(DESIRED_MAXX, DESIRED_MAXY, 16, WINDOW_NAME);

    // assertion: by expectations
    new Verifications() {{
      Display.setTitle(WINDOW_NAME);
      Display.setDisplayMode((DisplayMode) any);
      Display.create();
    }};
  }

}
