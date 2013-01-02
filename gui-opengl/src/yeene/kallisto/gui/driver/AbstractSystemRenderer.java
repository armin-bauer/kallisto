package yeene.kallisto.gui.driver;

import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.gui.view.method.AbstractCamera;

/**
 * @author armin
 */
public abstract class AbstractSystemRenderer implements SystemRenderer {

  protected AbstractCamera camera;
  protected SimulatedSystem system;

  private AbstractCamera nextCamera;
  private SimulatedSystem nextSystem;


  @Override
  public final void setCamera(final AbstractCamera camera) {
    nextCamera = camera;
  }

  @Override
  public final void setSimulatedSystem(final SimulatedSystem system) {
    nextSystem = system;
  }

  @Override
  public final void renderFrame() {
    if(null != nextCamera) {
      camera = nextCamera;
      nextCamera = null;
    }

    if(null != nextSystem) {
      system = nextSystem;
      nextSystem = null;
    }

    user_renderFrame();
  }

  /**
   * user function for rendering the frame. This method guarantees that camera and system
   * won't change during rendering.
   */
  protected abstract void user_renderFrame();

}
