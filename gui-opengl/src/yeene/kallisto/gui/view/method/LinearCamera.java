package yeene.kallisto.gui.view.method;

import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

/**
 * a camera that can be moved but only stretches the scene by a linear factor.
 *
 * @author armin
 */
public class LinearCamera extends MovingCamera {

  private BigDecimal scalingFactorRadius = BigDecimal.ONE;
  private BigDecimal scalingFactorPoint = BigDecimal.ONE;

  public BigDecimal getScalingFactorRadius() {
    return scalingFactorRadius;
  }

  public void setScalingFactorRadius(final BigDecimal scalingFactorRadius) {
    this.scalingFactorRadius = scalingFactorRadius;
  }

  public BigDecimal getScalingFactorPoint() {
    return scalingFactorPoint;
  }

  public void setScalingFactorPoint(final BigDecimal scalingFactorPoint) {
    this.scalingFactorPoint = scalingFactorPoint;
  }


  @Override
  public Vector recalculatePoint(final Vector point) {
    return point.mult(scalingFactorPoint);
  }

  @Override
  public BigDecimal recalculateRadius(final Vector basePosition, final BigDecimal length) {
    return length.multiply(scalingFactorRadius);
  }

}
