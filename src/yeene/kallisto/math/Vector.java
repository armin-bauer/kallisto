package yeene.kallisto.math;

import yeene.kallisto.Constants;

import java.math.BigDecimal;

/**
 * Implementation of a basic vector math-class.
 *
 * @author yeene
 */
public final class Vector {

  public static final Vector NULLVECTOR = new Vector(0.0, 0.0, 0.0);


  private final BigDecimal x;
  private final BigDecimal y;
  private final BigDecimal z;

  public Vector(final Double x, final Double y, final Double z) {
    this(BigDecimal.valueOf(x), BigDecimal.valueOf(y), BigDecimal.valueOf(z));
  }

  public Vector(final BigDecimal x, final BigDecimal y, final BigDecimal z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public BigDecimal getX() {
    return x;
  }

  public BigDecimal getY() {
    return y;
  }

  public BigDecimal getZ() {
    return z;
  }

  public Vector add(final Vector v) {
    return new Vector(x.add(v.x), y.add(v.y), z.add(v.z));
  }

  public Vector sub(final Vector v) {
    return new Vector(x.subtract(v.x), y.subtract(v.y), z.subtract(v.z));
  }

  public Vector mult(final Double d) {
    return mult(BigDecimal.valueOf(d));
  }

  public Vector mult(final BigDecimal d) {
    return new Vector(x.multiply(d), y.multiply(d), z.multiply(d));
  }

  public Vector div(final Double k) {
    final BigDecimal d = BigDecimal.valueOf(k);
    return new Vector( x.divide(d, Constants.PRECISISION, BigDecimal.ROUND_HALF_UP),
                       y.divide(d, Constants.PRECISISION, BigDecimal.ROUND_HALF_UP),
                       z.divide(d, Constants.PRECISISION, BigDecimal.ROUND_HALF_UP));
  }

  public Vector div(final BigDecimal d) {
    return new Vector( x.divide(d, Constants.PRECISISION, BigDecimal.ROUND_HALF_UP),
                       y.divide(d, Constants.PRECISISION, BigDecimal.ROUND_HALF_UP),
                       z.divide(d, Constants.PRECISISION, BigDecimal.ROUND_HALF_UP));
  }

  public BigDecimal length() {
    return BigDecimal.valueOf( Math.pow((x.multiply(x).add(y.multiply(y)).add(z.multiply(z))).doubleValue(), 0.5) );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    if(obj == this) {
      return true;
    }

    if(!(obj instanceof Vector)) {
      return false;
    }

    final Vector o = (Vector) obj;
    return (o.getX().subtract(getX())).abs().compareTo(Constants.EPSILON) <= 0 &&
           (o.getY().subtract(getY())).abs().compareTo(Constants.EPSILON) <= 0 &&
           (o.getZ().subtract(getZ())).abs().compareTo(Constants.EPSILON) <= 0;
  }

  @Override
  public String toString() {
    return "Vector{" +
      "x=" + x + ", " +
      "y=" + y + ", " +
      "z=" + z +
      '}';
  }

  public Vector crossProduct(final Vector v) {
    final BigDecimal a2b3 = getY().multiply(v.getZ());
    final BigDecimal a3b2 = getZ().multiply(v.getY());
    final BigDecimal a3b1 = getZ().multiply(v.getX());
    final BigDecimal a1b3 = getX().multiply(v.getZ());
    final BigDecimal a1b2 = getX().multiply(v.getY());
    final BigDecimal a2b1 = getY().multiply(v.getX());

    return new Vector(a2b3.subtract(a3b2), a3b1.subtract(a1b3), a1b2.subtract(a2b1));
  }
}
