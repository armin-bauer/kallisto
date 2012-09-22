package yeene.kallisto.systembuilder.dsl;

/**
 * definition of functionality that is
 *
 * @author armin
 */
public interface SystemBuilderDSLPositionByAngle {

  /**
   * set an angle for the course of the track of the object
   * @param angle angle of the track of the object
   * @return same interface for configuring the next steps
   */
  SystemBuilderDSLPosition withEclipticInclination(double angle);

  /**
   *
   * @param halfAxis the distance between the object to the sun
   * @return same interface for configuring the next steps
   */
  SystemBuilderDSLPosition withBigHalfAxis(long halfAxis);

  /**
   * to find out where the planet / object currently is.
   * @param theta where in the circle the planet is (0..360)
   * @return same interface for configuring the next steps
   */
  SystemBuilderDSLPosition withThetaInDegrees(int theta);

  /**
   * length of initial velocity vector
   * @param startspeed speed in meter per second
   * @return same interface for configuring the next steps
   */
  SystemBuilderDSLPosition withStartSpeed(long startspeed);

}
