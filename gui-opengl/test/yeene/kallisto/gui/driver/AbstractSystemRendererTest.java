package yeene.kallisto.gui.driver;

import mockit.Mocked;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.gui.view.method.AbstractCamera;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author armin
 */
public class AbstractSystemRendererTest {

  private AbstractSystemRenderer renderer;

  @BeforeMethod
  public void setup() {
    renderer = new AbstractSystemRenderer() {

      @Override
      protected void user_renderFrame() {
      }
    };
  }

  @Test
  public void renderFrame_appliesCameraAndSimulatedSystem_whenCalled(
    final @Mocked SimulatedSystem system,
    final @Mocked AbstractCamera camera
  ) throws Exception {
    // fixture:

    // execution: set and render.
    renderer.setCamera(camera);
    renderer.setSimulatedSystem(system);
    assertThat(renderer.camera).describedAs("camera in the renderer").isNull();
    assertThat(renderer.system).describedAs("system in the renderer").isNull();
    renderer.renderFrame();

    // assertion: now the camera and system should be set.
    assertThat(renderer.camera).describedAs("camera in the renderer").isNotNull().isSameAs(camera);
    assertThat(renderer.system).describedAs("system in the renderer").isNotNull().isSameAs(system);
  }

}
