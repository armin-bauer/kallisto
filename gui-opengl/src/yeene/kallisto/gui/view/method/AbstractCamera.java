package yeene.kallisto.gui.view.method;

import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

/**
 * implements an interface with base functionality to a camera to be used when rendering
 * the scene.
 *
 * @author armin
 */
public abstract class AbstractCamera {

  protected Vector position;
  protected Vector view;
  protected Vector up = new Vector(0.0, 1.0, 0.0);

  public Vector getPosition() {
    return position;
  }

  public Vector getView() {
    return view;
  }

  public Vector getUp() {
    return up;
  }

  /**
   * moves the camera / set's its new position
   */
  public abstract void moveCamera();

  /**
   * recalculates a point in space, used for applying stretching if needed
   * @param point input point
   * @return output point that might have been stretched.
   */
  public abstract Vector recalculatePoint(final Vector point);

  /**
   * recalculates a radius of an object in the space, used for applying stretching if needed
   * @param basePosition input point, center of object
   * @param length length of raduis
   * @return stretched radius
   */
  public abstract BigDecimal recalculateRadius(final Vector basePosition, final BigDecimal length);

}
