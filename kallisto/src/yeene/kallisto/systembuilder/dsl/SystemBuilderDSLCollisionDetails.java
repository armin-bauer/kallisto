package yeene.kallisto.systembuilder.dsl;

/**
 * this part of the DSL can be used to give details about collisions to the
 * configuration. Like the object's radius or other properties.
 *
 * @author yeene
 */
public interface SystemBuilderDSLCollisionDetails {

  /**
   * supply a radius to the DSL assuming that objects are round
   * @param radius radius of the object
   * @return the DSL step that sets the mass of objects
   */
  SystemBuilderDSLMass withRadius(long radius);

}
