package yeene.kallisto.math;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static yeene.kallisto.math.MathUtils.rotationalMatrix;
import static yeene.kallisto.math.MathUtils.stretchMatrix;

/**
 * @author yeene
 */
public class MatrixTest {

  @Test(dataProvider = "matrix x vector x expectedResult")
  public void multiply_multipliesWithVector(final Matrix m, final Vector v, final Vector expectedResult) throws Exception {
    // fixture: by dataprovicer

    // execution: multiply with a vector.
    final Vector result =  m.multiply(v);

    // assertion: should be same as expected.
    assertThat(result).describedAs("result after multiplication").isEqualTo(expectedResult);
  }

  @DataProvider(name = "matrix x vector x expectedResult")
  public Object[][] multiply_dataProvider() {
    return new Object[][] {
      new Object[] { stretchMatrix(1.0),                  new Vector(1.0, 2.0, 3.0), new Vector( 1.0,     2.0,      3.0) },
      new Object[] { stretchMatrix(2.0),                  new Vector(1.0, 2.0, 3.5), new Vector( 2.0,     4.0,      7.0) },
      new Object[] { rotationalMatrix( 0.0,  90.0,  0.0), new Vector(1.0, 0.0, 0.0), new Vector( 0.0,     0.0,     -1.0) },
      new Object[] { rotationalMatrix( 0.0, 270.0,  0.0), new Vector(1.0, 0.0, 0.0), new Vector( 0.0,     0.0,      1.0) },
      new Object[] { rotationalMatrix( 0.0, 180.0,  0.0), new Vector(1.0, 0.0, 0.0), new Vector(-1.0,     0.0,      0.0) },
      new Object[] { rotationalMatrix( 0.0,  45.0,  0.0), new Vector(1.0, 0.0, 0.0), new Vector( 0.70710, 0.0,     -0.70710) },
      new Object[] { rotationalMatrix(45.0,   0.0,  0.0), new Vector(0.0, 1.0, 0.0), new Vector( 0.0,     0.70710,  0.70710) },
      new Object[] { rotationalMatrix( 0.0,   0.0, 45.0), new Vector(1.0, 0.0, 0.0), new Vector( 0.70710, 0.70710,  0.0) }
    };
  }
}
