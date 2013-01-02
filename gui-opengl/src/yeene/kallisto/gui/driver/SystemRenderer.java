package yeene.kallisto.gui.driver;

import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.gui.view.method.AbstractCamera;

/**
 * @author armin
 */
public interface SystemRenderer {

  /**
   * set a camera to the renderer. This camera will be used to render things.
   * will apply for *next* frame
   * @param camera the camera to be used
   */
  void setCamera(AbstractCamera camera);

  /**
   * sets the system that should be rendered by this gui
   * will apply for *next* frame
   * @param system new system to be rendered
   */
  void setSimulatedSystem(SimulatedSystem system);

  /**
   * render one frame. applies the change of the system and the camera.
   */
  void renderFrame();

}
