package yeene.kallisto;

import java.math.BigDecimal;

/**
 * definition of constants used in the calculations.
 *
 * @author armin
 */
public class Constants {

  /**
   * the gravitation constant (http://en.wikipedia.org/wiki/Gravitational_constant)
   */
  public static final BigDecimal G = BigDecimal.valueOf(6.67384E-11);

  /**
   * for viewing.
   */
  public static final BigDecimal scale = new BigDecimal("1000000000.0");

  /**
   * definition of the step size in seconds.
   */
  public static BigDecimal dt = BigDecimal.ONE;

  /**
   * definition of an epsilon to choose for equality checks on numbers.
   */
  public static final BigDecimal EPSILON = BigDecimal.valueOf(0.0001);

  /**
   * number of decimals after period in calculations
   */
  public static final int precisision = 4;
}