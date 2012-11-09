package yeene.kallisto.systembuilder.dsl;

import yeene.kallisto.math.Vector;

/**
 * @author yeene
 */
public interface SystemBuilderDSLAbsolutePosition {

  /**
   * define a position by putting an object to a place in the simulation space
   * @param position position to put the object
   */
  SystemBuilderDSLAbsolutePosition withPosition(Vector position);

  /**
   * define a velocity directly on a given object
   * @param velocity velocity to set for the object
   */
  SystemBuilderDSLAbsolutePosition withVelocity(Vector velocity);
}
