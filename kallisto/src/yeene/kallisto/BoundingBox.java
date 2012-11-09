package yeene.kallisto;

import java.math.BigDecimal;

/**
 * @author yeene
 */
public class BoundingBox {

  private final BigDecimal maxx;
  private final BigDecimal maxy;
  private final BigDecimal maxz;
  private final BigDecimal minx;
  private final BigDecimal miny;
  private final BigDecimal minz;

  public BoundingBox(final BigDecimal maxx, final BigDecimal maxy, final BigDecimal maxz, final BigDecimal minx, final BigDecimal miny, final BigDecimal minz) {
    this.maxx = maxx;
    this.maxy = maxy;
    this.maxz = maxz;
    this.minx = minx;
    this.miny = miny;
    this.minz = minz;
  }

  public BigDecimal getMaxx() {
    return maxx;
  }

  public BigDecimal getMaxy() {
    return maxy;
  }

  public BigDecimal getMaxz() {
    return maxz;
  }

  public BigDecimal getMinx() {
    return minx;
  }

  public BigDecimal getMiny() {
    return miny;
  }

  public BigDecimal getMinz() {
    return minz;
  }

  public BigDecimal getWidth() {
    final BigDecimal result1 = maxx.subtract(minx);
    final BigDecimal result2 = maxy.subtract(miny);
    final BigDecimal result = (result2.compareTo(result1) > 0) ? result2 : result1;
    if(result.compareTo(BigDecimal.ZERO) <= 0) {
      return BigDecimal.valueOf(1000);
    }
    return result;
  }

  public BigDecimal getHeight() {
    return getWidth();
  }
}
