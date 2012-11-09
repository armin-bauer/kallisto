package yeene.kallisto;

import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

/**
 * Definition of an Object in the simulation.
 *
 * The Object has properties that are used for graphical representation like
 *  - name
 *  - radius
 *
 * and it has properties that are used for calculating the behaviour in the simulation
 *  - mass
 *  - velocity
 *  - position
 *
 * @author yeene
 */
public class Satellite {

  private final String name;
  private BigDecimal radius;

  private final BigDecimal mass;
  private Vector velocity;
  private Vector position;

  public Satellite(final String name, final BigDecimal radius, final BigDecimal mass, final Vector position, final Vector velocity) {
    this.name = name;
    this.radius = radius;
    this.mass = mass;
    this.velocity = velocity;
    this.position = position;

    if(name == null) {
      throw new IllegalArgumentException("Name of Object must never be null!");
    }
  }

  /**
   * @param accelleration accelleration that is applied on the object
   */
  public void apply(final Vector accelleration) {
    // add the change in velocity to the current velocity.
    velocity = velocity.add(accelleration.mult(Constants.DT));

    // step forward by a step.
    position = position.add(velocity.mult(Constants.DT));
  }

  public String getName() {
    return name;
  }

  public BigDecimal getRadius() {
    return radius;
  }

  public BigDecimal getMass() {
    return mass;
  }

  public Vector getVelocity() {
    return velocity;
  }

  public Vector getPosition() {
    return position;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Satellite satellite = (Satellite) o;

    if (mass != null ? !mass.equals(satellite.mass) : satellite.mass != null) return false;
    if (!name.equals(satellite.name)) return false;
    if (position != null ? !position.equals(satellite.position) : satellite.position != null) return false;
    if (radius != null ? !radius.equals(satellite.radius) : satellite.radius != null) return false;
    if (velocity != null ? !velocity.equals(satellite.velocity) : satellite.velocity != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
