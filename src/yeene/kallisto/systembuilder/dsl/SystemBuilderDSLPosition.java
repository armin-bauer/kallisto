package yeene.kallisto.systembuilder.dsl;

import yeene.kallisto.math.Vector;

/**
 * select a method to define a position for the object.
 *
 * @author yeene
 */
public interface SystemBuilderDSLPosition {

  /**
   * define a position by putting an object to a place in the simulation space
   * @param position position to put the object
   */
  void withPosition(Vector position);

}
