package yeene.kallisto.systembuilder.dsl;

import yeene.kallisto.math.Vector;

/**
 * select a method to define a position for the object.
 *
 * @author yeene
 */
public interface SystemBuilderDSLPosition extends SystemBuilderDSLPositionByAngle {

  /**
   * define a position by putting an object to a place in the simulation space
   * @param position position to put the object
   */
  SystemBuilderDSLPosition withPosition(Vector position);

  /**
   * define a velocity directly on a given object
   * @param velocity velocity to set for the object
   */
  SystemBuilderDSLPosition withVelocity(Vector velocity);

}
