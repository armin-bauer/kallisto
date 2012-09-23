package yeene.kallisto.systembuilder;

import yeene.kallisto.math.MathUtils;
import yeene.kallisto.math.Vector;
import yeene.kallisto.systembuilder.dsl.*;

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

  private Double theta;
  private BigDecimal bigHalfAxis;
  private Double trackAngle;
  private BigDecimal startSpeed;


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
  public SystemBuilderDSLPosition withMass(final double mass) {
    this.mass = BigDecimal.valueOf(mass);
    return this;
  }

  @Override
  public SystemBuilderDSLPosition withDensity(final Double densityOfObject) {
    this.mass = BigDecimal.valueOf(densityOfObject).multiply(MathUtils.sphericVolume(objectRadius));
    return this;
  }

  @Override
  public SystemBuilderDSLAbsolutePosition withPosition(final Vector position) {
    this.initialPosition = position;
    return this;
  }

  @Override
  public SystemBuilderDSLAbsolutePosition withVelocity(final Vector velocity) {
    this.initialVelocity = velocity;
    return this;
  }

  @Override
  public SystemBuilderDSLPosition withEclipticInclination(final double angle) {
    initialPosition = null;
    trackAngle = angle;
    return this;
  }

  @Override
  public SystemBuilderDSLPosition withBigHalfAxis(final long halfAxis) {
    initialPosition = null;
    this.bigHalfAxis = BigDecimal.valueOf(halfAxis);
    return this;
  }

  @Override
  public SystemBuilderDSLPosition withThetaInDegrees(final int theta) {
    this.theta = (double) theta;
    initialPosition = null;
    return this;
  }

  @Override
  public SystemBuilderDSLPosition withStartSpeed(final long startspeed) {
    this.startSpeed = BigDecimal.valueOf(startspeed);
    initialPosition = null;
    return this;
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

  public Double getTheta() {
    return theta;
  }

  public BigDecimal getBigHalfAxis() {
    return bigHalfAxis;
  }

  public Double getTrackAngle() {
    return trackAngle;
  }

  public BigDecimal getStartSpeed() {
    return startSpeed;
  }
}
