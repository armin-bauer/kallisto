package yeene.kallisto;

import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

/**
 * Definition of an Object in the simulation.
 * @author yeene
 */
public class Sattelite {

  private final String name;
  private BigDecimal radius;

  private final BigDecimal mass;
  private Vector acceleration;
  private Vector velocity;
  private Vector position;


  public Sattelite(final String name, final BigDecimal radius, final BigDecimal mass, final Vector position, final Vector velocity, final Vector acceleration) {
    this.name = name;
    this.radius = radius;
    this.mass = mass;
    this.acceleration = acceleration;
    this.velocity = velocity;
    this.position = position;

    if(name == null) {
      throw new IllegalArgumentException("Name of Object must never be null!");
    }
  }

  /**
   * @param accellerationDelta difference in accelleration
   */
  public void apply(final Vector accellerationDelta) {
    // add the change in accelleration to the current acceleration.
    acceleration = accellerationDelta.add(accellerationDelta);

    // add the change in velocity to the current velocity.
    velocity = velocity.add(acceleration.mult(Constants.DT));

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

  public Vector getAcceleration() {
    return acceleration;
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

    final Sattelite sattelite = (Sattelite) o;

    if (acceleration != null ? !acceleration.equals(sattelite.acceleration) : sattelite.acceleration != null)
      return false;
    if (mass != null ? !mass.equals(sattelite.mass) : sattelite.mass != null) return false;
    if (!name.equals(sattelite.name)) return false;
    if (position != null ? !position.equals(sattelite.position) : sattelite.position != null) return false;
    if (radius != null ? !radius.equals(sattelite.radius) : sattelite.radius != null) return false;
    if (velocity != null ? !velocity.equals(sattelite.velocity) : sattelite.velocity != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
