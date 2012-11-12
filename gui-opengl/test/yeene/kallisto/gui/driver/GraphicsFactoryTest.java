package yeene.kallisto.gui.driver;

import mockit.Deencapsulation;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import yeene.kallisto.gui.driver.nullout.NulloutGraphics;
import yeene.kallisto.gui.driver.opengl.OpenGLGraphics;

import static org.fest.assertions.Assertions.assertThat;
import static yeene.kallisto.gui.driver.GraphicsFactory.DriverType;

/**
 * @author yeene
 */
public class GraphicsFactoryTest {

  @BeforeMethod
  public void setup() {
    Deencapsulation.setField(GraphicsFactory.class, "managedInstance", null);
  }

  @Test(dataProvider = "type x frame class type provider")
  public void getInstance_returnsOpenGLImplementation_forOpenGLConfiguration(final DriverType type, final Class<GraphicFrame> clazz) throws Exception {
    // fixture:

    // execution: set for opengl implementation
    GraphicsFactory.setDriverType(type);
    final GraphicFrame frame = GraphicsFactory.getInstance();

    // assertion: must be opengl implementation.
    assertThat(frame).describedAs("returned frame").isInstanceOf(clazz);
  }

  @Test(dataProvider = "type x frame class type provider")
  public void getInstance_reusesInstanceOfDriver(final DriverType type, final Class<GraphicFrame> clazz) throws Exception {
    // fixture:

    // execution: set for opengl implementation
    GraphicsFactory.setDriverType(type);
    final GraphicFrame frame1 = GraphicsFactory.getInstance();
    final GraphicFrame frame2 = GraphicsFactory.getInstance();

    // assertion: must be opengl implementation.
    assertThat(frame1).describedAs("returned frame").isSameAs(frame2);
  }

  @DataProvider(name = "type x frame class type provider")
  public Object[][] typeProvider() {
    return new Object[][] {
      new Object[] { DriverType.OPENGL, OpenGLGraphics.class },
      new Object[] { DriverType.NULL, NulloutGraphics.class }
    };
  }

}
