package yeene.kallisto.systembuilder;

import yeene.kallisto.math.MathUtils;
import yeene.kallisto.math.Vector;
import yeene.kallisto.systembuilder.dsl.SystemBuilderDSLCollisionDetails;
import yeene.kallisto.systembuilder.dsl.SystemBuilderDSLMass;
import yeene.kallisto.systembuilder.dsl.SystemBuilderDSLNamed;
import yeene.kallisto.systembuilder.dsl.SystemBuilderDSLPosition;

import java.math.BigDecimal;

import static yeene.kallisto.math.Vector.NULLVECTOR;

/**
 * implementation of the system builder dsl.
 *
 * @author yeene
 */
public class DSLObjectConfiguration implements SystemBuilderDSLNamed, SystemBuilderDSLCollisionDetails, SystemBuilderDSLMass, SystemBuilderDSLPosition {

  private String name;
  private BigDecimal objectRadius = BigDecimal.ZERO;
  private BigDecimal mass = BigDecimal.ZERO;
  private Vector initialPosition = NULLVECTOR;
  private Vector initialVelocity = NULLVECTOR;


  // ------------------------------------------------- DSL Components -----------------------------------------------
  @Override
  public SystemBuilderDSLMass withRadius(final long radius) {
    this.objectRadius = BigDecimal.valueOf(radius);
    return this;
  }

  @Override
  public SystemBuilderDSLCollisionDetails named(final String objectName) {
    this.name = objectName;
    return this;
  }

  @Override
  public SystemBuilderDSLPosition withMass(final long mass) {
    this.mass = BigDecimal.valueOf(mass);
    return this;
  }

  @Override
  public SystemBuilderDSLPosition withDensity(final Double densityOfObject) {
    this.mass = BigDecimal.valueOf(densityOfObject).multiply(MathUtils.sphericVolume(objectRadius));
    return this;
  }

  @Override
  public void withPosition(final Vector position) {
    this.initialPosition = position;
  }


  // ------------------------------------------------- GETTERS -----------------------------------------------
  public String getName() {
    return name;
  }

  public BigDecimal getObjectRadius() {
    return objectRadius;
  }

  public BigDecimal getMass() {
    return mass;
  }

  public Vector getInitialPosition() {
    return initialPosition;
  }

  public Vector getInitialVelocity() {
    return initialVelocity;
  }
}
