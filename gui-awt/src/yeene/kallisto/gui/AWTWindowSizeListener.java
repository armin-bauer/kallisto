package yeene.kallisto.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author yeene
 */
public class AWTWindowSizeListener extends ComponentAdapter {

  /**
   * Invoked when the component's size changes.
   */
  @Override
  public void componentResized(final ComponentEvent e) {
    final RenderTask renderer = Main.getInstance().getRenderer();
    if(renderer != null) {
      renderer.handleResize();
    }
  }
}
