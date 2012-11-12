package yeene.kallisto.gui.driver;

import yeene.kallisto.gui.driver.nullout.NulloutGraphics;
import yeene.kallisto.gui.driver.opengl.OpenGLGraphics;

import static yeene.kallisto.gui.driver.GraphicsFactory.DriverType.NULL;

/**
 * used to get graphic drivers.
 * @author yeene
 */
public class GraphicsFactory {

  private static GraphicFrame managedInstance;
  private static DriverType configuration = NULL;

  public static enum DriverType {
    NULL, OPENGL
  }

  public static void setDriverType(final DriverType configuration) {
    GraphicsFactory.configuration = configuration;
  }

  public static GraphicFrame getInstance() {
    if(managedInstance == null && configuration == DriverType.NULL) {
      managedInstance = new NulloutGraphics();
    } else if(managedInstance == null && configuration == DriverType.OPENGL) {
      managedInstance = new OpenGLGraphics();
    }

    return managedInstance;
  }

}
