package yeene.kallisto.gui.driver;

/**
 * interface for all view mechanisms.
 *
 * @author armin
 */
public interface GraphicFrame {

  /**
   * creates the window with the base view for rendering
   * @param desiredMaxx window width
   * @param desiredMaxy window height
   * @param desiredBpp bits per pixel
   * @param windowName name of window to create
   */
  void            init(int desiredMaxx, int desiredMaxy, int desiredBpp, final String windowName);

  /**
   * performs the main loop for
   */
  void            mainloop();

}
