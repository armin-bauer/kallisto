package yeene.kallisto.systembuilder.dsl;

/**
 * use this to define objects in the system that move relative to other systems
 * like moons or dust particles or rockets.
 *
 * @author yeene
 */
public interface SystemBuilderDSLPositionRelativeTo {

  /**
   * define that the object is located in relation to the object named as argument.
   * this object will be called relative.
   *
   * @param otherObject parent object to relate to
   */
  SystemBuilderDSLPositionRelativeTo startingFrom(String otherObject);

  /**
   * define the distance to the relative
   * @param distance distance from the relative.
   */
  SystemBuilderDSLPositionRelativeTo withDistance(long distance);

  /**
   * define the angle of the circular orbit.
   * @param theta in degrees.
   */
  SystemBuilderDSLPositionRelativeTo withThetaInDegrees(double theta);

  /**
   * define the ecliptic inclination relative to the other object
   * @param alpha in degrees
   */
  SystemBuilderDSLPositionRelativeTo withRelativeEclipticInclination(double alpha);

  /**
   * set the relative velocity
   * @param velocity in m/s
   */
  SystemBuilderDSLPositionRelativeTo withRelativeStartSpeed(long velocity);

}
