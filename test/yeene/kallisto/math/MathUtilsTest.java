package yeene.kallisto.math;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import yeene.kallisto.Constants;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * some math utilities
 *
 * @author yeene
 */
public class MathUtilsTest {

  @Test(dataProvider = "number, expected result for sqrt(number)")
  public void sqrt_calculatesSquareRoot(final BigDecimal x, final BigDecimal expectedResult) throws Exception {
    // fixture: by dataprovider

    // execution: call sqrt
    final BigDecimal result = MathUtils.sqrt(x, Constants.PRECISISION);

    // assertion: should be as expected
    assertThat(result.compareTo(expectedResult)).
      describedAs("calculated square root").
      isEqualTo(0);
  }

  @DataProvider(name = "number, expected result for sqrt(number)")
  public Object[][] sqrt_dataProvider() {
    return new Object[][] {
      new Object[] { BigDecimal.valueOf(9), BigDecimal.valueOf(3) },
      new Object[] { BigDecimal.valueOf(2), BigDecimal.valueOf(1.4142) },
    };
  }

  @Test(dataProvider = "radius x sphericalVolume")
  public void sphericVolume_calculatesSphericalVolume(final BigDecimal radius, final BigDecimal expectedResult) throws Exception {
    // fixture: by dataprovider

    // execution: call sphericVolume()
    final BigDecimal result = MathUtils.sphericVolume(radius);

    // assertion: should be as expected value
    assertThat(result.compareTo(expectedResult)).
      describedAs("calculated spherical volume").
      isEqualTo(0);
  }

  @DataProvider(name = "radius x sphericalVolume")
  public Object[][] sphericVolume_dataProvider() {
    return new Object[][] {
      new Object[] { BigDecimal.valueOf(1), BigDecimal.valueOf(4.1888) },
      new Object[] { BigDecimal.valueOf(10), BigDecimal.valueOf(4188.7867) }
    };
  }

}
