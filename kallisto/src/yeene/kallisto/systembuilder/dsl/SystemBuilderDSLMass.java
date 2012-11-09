package yeene.kallisto.systembuilder.dsl;

/**
 * This part of the Configuration DSL sets the objects mass.
 *
 * @author yeene
 */
public interface SystemBuilderDSLMass {

  /**
   * set the object mass.
   * @param mass mass to set
   * @return the positioning part of the object
   */
  SystemBuilderDSLPosition withMass(final long mass);

  /**
   * set the object mass.
   * @param mass mass to set
   * @return the positioning part of the object
   */
  SystemBuilderDSLPosition withMass(final double mass);

  /**
   * sets the objects mass by supplying the density. Along with the radius, a
   * mass is calculated.
   * @param densityOfObject density of the described object
   * @return the positioning part of the object
   */
  SystemBuilderDSLPosition withDensity(final Double densityOfObject);

}
