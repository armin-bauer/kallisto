package yeene.kallisto.gui.driver;

import yeene.kallisto.gui.driver.terminal.TerminalOutput;
import yeene.kallisto.gui.driver.opengl.OpenGLGraphics;

import static yeene.kallisto.gui.driver.GraphicsFactory.DriverType.TERMINAL;

/**
 * used to get graphic drivers.
 * @author yeene
 */
public class GraphicsFactory {

  private static GraphicFrame managedInstance;
  private static DriverType configuration = TERMINAL;

  public static enum DriverType {
    TERMINAL, OPENGL
  }

  public static void setDriverType(final DriverType configuration) {
    GraphicsFactory.configuration = configuration;
  }

  public static GraphicFrame getInstance() {
    if(managedInstance == null && configuration == DriverType.TERMINAL) {
      managedInstance = new TerminalOutput();
    } else if(managedInstance == null && configuration == DriverType.OPENGL) {
      managedInstance = new OpenGLGraphics();
    }

    return managedInstance;
  }

}
