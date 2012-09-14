package yeene.kallisto;

import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

/**
 * @author yeene
 */
public class SatteliteBodiesStateChangeCollector {

  private Vector accellerationDifference = new Vector(0.0, 0.0, 0.0);
  private Sattelite baseObject;

  public SatteliteBodiesStateChangeCollector(final Sattelite baseObject) {
    this.baseObject = baseObject;
  }

  /**
   * have two sattelites influence each other.
   * The same sattelites won't influence each other.
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
    accellerationDifference = accellerationDifference.add(difference);
  }


  public Vector getAccellerationDifference() {
    return accellerationDifference;
  }

  /**
   * apply the changes to he sattelite.
   */
  public void apply() {
    baseObject.apply(accellerationDifference.mult(Constants.G));
  }
}
