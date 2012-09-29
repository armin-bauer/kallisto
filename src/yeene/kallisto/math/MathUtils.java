package yeene.kallisto.math;

import yeene.kallisto.Constants;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

/**
 * some misc math utils.
 *
 * @author yeene
 */
public class MathUtils {

  /**
   * got this gem from http://www.java-forums.org/advanced-java/44345-square-rooting-bigdecimal.html
   * calculates using newtons method.
   * @param x base
   * @param scale scale
   * @return sqrt(x) to scale digits.
   */
  public static BigDecimal sqrt(final BigDecimal x, final int scale)
  {
    // Check that x >= 0.
    if (x.signum() < 0) {
      throw new IllegalArgumentException("x < 0");
    }

    // n = x*(10^(2*scale))
    final BigInteger n = x.movePointRight(scale << 1).toBigInteger();

    // The first approximation is the upper half of n.
    final int bits = (n.bitLength() + 1) >> 1;
    BigInteger ix = n.shiftRight(bits);
    BigInteger ixPrev;

    // Loop until the approximations converge
    // (two successive approximations are equal after rounding).
    do {
      ixPrev = ix;

      // x = (x + n/x)/2
      ix = ix.add(n.divide(ix)).shiftRight(1);
    } while (ix.compareTo(ixPrev) != 0);

    return new BigDecimal(ix, scale);
  }

  /**
   * calculates the volume of a sphere
   * @param objectRadius radius of the object
   * @return volume of the object
   */
  public static BigDecimal sphericVolume(final BigDecimal objectRadius) {
    return valueOf(4).multiply(Constants.PI).multiply(objectRadius.pow(3)).divide(valueOf(3), Constants.PRECISISION, BigDecimal.ROUND_HALF_UP);
  }

  /**
   * make a matrix that stretches all dimensions in the same way by a given factor
   * @param x stretch factor for all dimmensions
   * @return matrix
   */
  public static Matrix stretchMatrix(final Double x) {
    return stretchMatrix(valueOf(x));
  }

  /**
   * make a matrix that stretches all dimensions in the same way by a given factor
   * @param v stretch factor for all dimmensions
   * @return matrix
   */
  public static Matrix stretchMatrix(final BigDecimal v) {
    return stretchMatrix(v, v, v);
  }

  /**
   * make a matrix that stretches all dimensions in the same way by a given factor
   * @param x stretch factor for x direction
   * @param y stretch factor for y direction
   * @param z stretch factor for z direction
   * @return matrix
   */
  public static Matrix stretchMatrix(final BigDecimal x, final BigDecimal y, final BigDecimal z) {
    return new Matrix( new BigDecimal[][] {{ x, ZERO, ZERO }, { ZERO, y, ZERO }, { ZERO, ZERO, z }});
  }

  /**
   * makes a matrix that rotates around a point. rotations are provided in degrees (0..360)
   * @param rotX rotation on the x axis
   * @param rotY rotation on the y axis
   * @param rotZ rotation on the z axis
   * @return matrix with the given rotations.
   */
  public static Matrix rotationalMatrix(Double rotX, Double rotY, Double rotZ) {

    rotX = rotX * PI / 180.0;
    rotY = rotY * PI / 180.0;
    rotZ = rotZ * PI / 180.0;


    final Matrix xRotation = new Matrix(
      new BigDecimal[][] {
        {  ONE,               ZERO,                ZERO },
        { ZERO, valueOf(cos(rotX)), valueOf(-sin(rotX)) },
        { ZERO, valueOf(sin(rotX)), valueOf( cos(rotX)) }
      });
    final Matrix yRotation = new Matrix(
      new BigDecimal[][] {
        { valueOf( cos(rotY)), ZERO, valueOf(sin(rotY)) },
        {                ZERO,  ONE,               ZERO },
        { valueOf(-sin(rotY)), ZERO, valueOf(cos(rotY)) }
      });
    final Matrix zRotation = new Matrix(
      new BigDecimal[][] {
        { valueOf(cos(rotZ)), valueOf(-sin(rotZ)), ZERO },
        { valueOf(sin(rotZ)), valueOf( cos(rotZ)), ZERO },
        {               ZERO,                ZERO,  ONE }
      });

    return xRotation.multiply(yRotation).multiply(zRotation);
  }


}
