package yeene.kallisto;

import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

/**
 * This is a helper class that is used by {@link SimulatedSystem} to collect the
 * acceleration that is inflicted on an object in the system by the other objects.
 *
 * @author yeene
 */
class SatteliteBodiesStateChangeCollector {

  private Vector inflictedAcceleration = new Vector(0.0, 0.0, 0.0);
  private Sattelite baseObject;

  public SatteliteBodiesStateChangeCollector(final Sattelite baseObject) {
    this.baseObject = baseObject;
  }

  /**
   * An object influences this one in acceleration. Calculate the influence it has
   * and keep it in the inflictedAcceleration sum Vector.
   *
   * If other is the same as baseObject, no calculation will be done since an object
   * can't influence itself.
   *
   * @param other the other sattelite
   */
  public void influenceSattelite(final Sattelite other) {
    if(baseObject == other) {
      return;
    }

    // calculate how far apart the objects are
    final Vector positionDifference = other.getPosition().sub(baseObject.getPosition());
    final BigDecimal distance = positionDifference.length();

    // calculate influence on the other object.
    final Vector difference = positionDifference.mult(other.getMass()).div(distance.pow(3));
    inflictedAcceleration = inflictedAcceleration.add(difference);
  }


  public Vector getInflictedAcceleration() {
    return inflictedAcceleration;
  }

  /**
   * apply the changes to he sattelite.
   */
  public void apply() {
    baseObject.apply(inflictedAcceleration.mult(Constants.G));
  }
}
