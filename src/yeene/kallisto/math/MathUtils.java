package yeene.kallisto.math;

import yeene.kallisto.Constants;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
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
    return BigDecimal.valueOf(4).multiply(Constants.PI).multiply(objectRadius.pow(3)).divide(BigDecimal.valueOf(3), Constants.PRECISISION, BigDecimal.ROUND_HALF_UP);
  }
}
