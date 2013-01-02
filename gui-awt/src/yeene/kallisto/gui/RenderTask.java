package yeene.kallisto.gui;

import yeene.kallisto.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeene
 */
public class RenderTask implements Runnable {

  private SimulatedSystem system;
  private Dimension dimension;
  private BigDecimal scaleFactorX;
  private BigDecimal scaleFactorY;
  private static Map<String, Color> stars = new HashMap<String, Color>();
  static {
    stars.put("moon", Color.WHITE);
    stars.put("sun", Color.YELLOW);
    stars.put("mercury", Color.GRAY);
    stars.put("venus", Color.GREEN);
    stars.put("earth", Color.BLUE);
    stars.put("mars", Color.RED);
    stars.put("jupiter", Color.DARK_GRAY);
    stars.put("saturn", Color.PINK);
    stars.put("uranus", Color.ORANGE);
    stars.put("neptun", Color.ORANGE);
    stars.put("pluto", Color.CYAN);
  }

  private Image offscreenImage;

  public RenderTask() {
    system = Main.getInstance().getSimulatedSystem();
    handleResize();
  }

  /**
   * handles a resize event and recalculates the scaling factors for
   * the drawing thread.
   */
  public void handleResize() {
    final Frame f = Main.getInstance().getFrame();
    dimension = f.getSize();
    final BoundingBox bb = system.getBoundingBox();

    scaleFactorX = BigDecimal.valueOf(0.5).multiply(BigDecimal.valueOf(dimension.getWidth()-10).divide(bb.getWidth(), 32, BigDecimal.ROUND_HALF_UP));
    scaleFactorY = BigDecimal.valueOf(0.5).multiply(BigDecimal.valueOf(dimension.getHeight()-10).divide(bb.getHeight(), 32, BigDecimal.ROUND_HALF_UP));
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {

    // render forever.
    while(true) {
      // redraw...
      final Graphics graphics = getGraphics();

      graphics.fillRect(0, 0, dimension.width, dimension.height);
      for(final Satellite s : system.getElements()) {
        // calculate planet position to window coordinates.
        int x = (s.getPosition().getX().multiply(scaleFactorX).intValue()) + (dimension.width / 2);
        int y = (s.getPosition().getY().multiply(scaleFactorY).intValue()) + (dimension.height / 2);
        int radius = 4+s.getRadius().multiply(scaleFactorX).intValue();

        final Color c;
        if(stars.keySet().contains(s.getName())) {
          c = stars.get(s.getName());
        } else {
          c = Color.GREEN;
        }
        graphics.setColor(c);
        graphics.fillArc(x-radius, y-radius, radius, radius, 0, 360);

        graphics.setColor(Color.WHITE);
        graphics.drawString(s.getName(), x, y+8);
      }

      graphics.drawString("Number of Days since Simulation Start: " + (Constants.DT.longValue() * system.getIterationCount()) / (24l*60l*60l), 0, 760);

      swapBuffers();
      graphics.dispose();

      // wait.
      try { Thread.sleep(100); } catch (InterruptedException e) { break; }
    }
  }

  /**
   * swap active and inactive buffer.
   */
  private void swapBuffers() {
    final Frame f = Main.getInstance().getFrame();
    final Graphics g = f.getGraphics();

    g.drawImage(offscreenImage, 0, 0, f);
    g.dispose();
    offscreenImage.flush();
  }

  /**
   * get graphics for the active screen buffer.
   * @return graphics interface
   */
  private Graphics getGraphics() {
    final Frame f = Main.getInstance().getFrame();

    offscreenImage = f.createImage(dimension.width, dimension.height);
    return offscreenImage.getGraphics();
  }
}
