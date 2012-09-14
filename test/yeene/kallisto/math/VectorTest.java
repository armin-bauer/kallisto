package yeene.kallisto.math;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import yeene.kallisto.Constants;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author yeene
 */
public class VectorTest {

  @Test(dataProvider = "equals: Vector_x_Vector_x_equality")
  public void equals_checksIfBothVectorsAreEqual(final Vector v1, final Vector v2, final boolean expectedResult) {
    // fixture setup: done by the dataprovider

    // execution: compare the vectors
    final boolean result = v1.equals(v2);

    // assertion: result should be same as expectedResult
    assertThat(result).describedAs("result after comparing v1 and v2").isEqualTo(expectedResult);
  }

  @DataProvider(name = "equals: Vector_x_Vector_x_equality")
  public Object[][] equals_dataProvider() {
    final Vector v1 = new Vector(0.0, 1.0, 2.0);
    final Vector v2 = new Vector(0.0, 1.0, 2.0);
    final Vector v3 = new Vector(0.0 + Constants.EPSILON.doubleValue(), 1.0, 2.0);
    final Vector v4 = new Vector(5.0, 6.0, 7.0);

    return new Object[][] {
      new Object[] { v1, v1, true }, // same object
      new Object[] { v1, v2, true }, // same values in different objects
      new Object[] { v1, v3, true }, // not the same values but very close
      new Object[] { v1, v4, false }, // different
    };
  }

  @Test(dataProvider = "add: Vector_x_Vector_x_ExpectedResultVector")
  public void add_addsAnotherVectorToSelf(final Vector x1, final Vector x2, final Vector expectedResult) throws Exception {
    // fixture setup: done by the dataprovider

    // execution: add the vectors
    final Vector result1 = x1.add(x2);
    final Vector result2 = x2.add(x1);

    // assertion: both vectors should have the same values and both should be same as expected result.
    assertThat(result1).describedAs("Result for x1+x2").isEqualTo(result2).isEqualTo(expectedResult);
  }

  @DataProvider(name = "add: Vector_x_Vector_x_ExpectedResultVector")
  public Object[][] add_dataProvider() {
    return new Object[][] {
      new Object[] { new Vector( 0.0,  0.0,  0.0), new Vector( 0.0,  0.0,  0.0), new Vector( 0.0,  0.0,  0.0) },
      new Object[] { new Vector( 1.0,  2.0,  3.0), new Vector( 4.0,  5.0,  6.0), new Vector( 5.0,  7.0,  9.0) },
      new Object[] { new Vector(-1.0, -2.0, -3.0), new Vector( 4.0,  5.0,  6.0), new Vector( 3.0,  3.0,  3.0) },
      new Object[] { new Vector( 1.0, -2.0,  0.0), new Vector(-4.0,  5.0, -6.0), new Vector(-3.0,  3.0, -6.0) }
    };
  }

  @Test(dataProvider = "sub: Vector_x_Vector_x_ExpectedResultVector")
  public void sub_subtractsAnotherVectorToSelf(final Vector x1, final Vector x2, final Vector expectedResult) throws Exception {
    // fixture setup: done by the dataprovider

    // execution: subtract the vectors
    final Vector result1 = x1.sub(x2);

    // assertion: both vectors should have the same values and both should be same as expected result.
    assertThat(result1).describedAs("Result for x1+x2").isEqualTo(expectedResult);
  }

  @DataProvider(name = "sub: Vector_x_Vector_x_ExpectedResultVector")
  public Object[][] sub_dataProvider() {
    return new Object[][] {
      new Object[] { new Vector( 0.0,  0.0,  0.0), new Vector( 0.0,  0.0,  0.0), new Vector( 0.0,  0.0,  0.0) },
      new Object[] { new Vector( 1.0,  2.0,  3.0), new Vector( 4.0,  5.0,  6.0), new Vector(-3.0, -3.0, -3.0) },
      new Object[] { new Vector(-1.0, -2.0, -3.0), new Vector( 4.0,  5.0,  6.0), new Vector(-5.0, -7.0, -9.0) },
      new Object[] { new Vector( 1.0, -2.0,  0.0), new Vector(-4.0,  5.0, -6.0), new Vector( 5.0, -7.0,  6.0) }
    };
  }

  @Test(dataProvider = "mult: multiplies with double or bigdecimal")
  public void mult_multiplies(final Vector v, final double r1, final Vector expectedResult) throws Exception {
    // fixture setup: done by dataprovider

    // execution: multiply twice, once with a double and once with a bigdecimal value
    final Vector result1 = v.mult(r1);
    final Vector result2 = v.mult(BigDecimal.valueOf(r1));

    // assertion: result should be as expected.
    assertThat(result1).describedAs("v times r1").isEqualTo(result2).isEqualTo(expectedResult);
  }

  @DataProvider(name = "mult: multiplies with double or bigdecimal")
  public Object[][] mult_dataProvider() {
    return new Object[][] {
      new Object[] { new Vector(0.0, 0.0, 0.0), 2.0, new Vector(0.0, 0.0, 0.0) },
      new Object[] { new Vector(1.0, 1.0, 1.0), 2.0, new Vector(2.0, 2.0, 2.0) },
      new Object[] { new Vector(1.0, 2.0, 3.0), 2.0, new Vector(2.0, 4.0, 6.0) },
      new Object[] { new Vector(1.0, 0.0, 0.0), 3.0, new Vector(3.0, 0.0, 0.0) },
      new Object[] { new Vector(0.0, 1.0, 0.0), 2.0, new Vector(0.0, 2.0, 0.0) },
      new Object[] { new Vector(0.0, 0.0, 1.0), 2.0, new Vector(0.0, 0.0, 2.0) },
    };
  }

  @Test(dataProvider = "div: divides by double or bigdecimal")
  public void div_multiplies(final Vector v, final double r1, final Vector expectedResult) throws Exception {
    // fixture setup: done by dataprovider

    // execution: multiply twice, once with a double and once with a bigdecimal value
    final Vector result1 = v.div(r1);
    final Vector result2 = v.div(BigDecimal.valueOf(r1));

    // assertion: result should be as expected.
    assertThat(result1).describedAs("v times r1").isEqualTo(result2).isEqualTo(expectedResult);
  }

  @DataProvider(name = "div: divides by double or bigdecimal")
  public Object[][] div_dataProvider() {
    return new Object[][] {
      new Object[] { new Vector( 0.0,  0.0,  0.0), 2.0, new Vector( 0.0,  0.0,  0.0) },
      new Object[] { new Vector(10.0, 10.0, 10.0), 2.0, new Vector( 5.0,  5.0,  5.0) },
      new Object[] { new Vector( 8.0,  6.0,  3.0), 2.0, new Vector( 4.0,  3.0, 1.50) }
    };
  }

  @Test(dataProvider = "length: Vector_x_DesiredResult")
  public void length_returnsLengthOfVector(final Vector v, final double expectedLenght) throws Exception {
    // fixture: by dataProvicer

    // execution: call length on the vector
    final BigDecimal result = v.length();

    // assertion: should be the same as the expected length
    assertThat(result).describedAs("calculated lenghth of vector").isEqualTo(BigDecimal.valueOf(expectedLenght));
  }

  @DataProvider(name = "length: Vector_x_DesiredResult")
  public Object[][] length_dataProvider() {
    return new Object[][] {
      new Object[] { new Vector(0.0, 0.0, 0.0), 0.0 },
      new Object[] { new Vector(1.0, 0.0, 0.0), 1.0 },
      new Object[] { new Vector(0.0, 1.0, 0.0), 1.0 },
      new Object[] { new Vector(0.0, 0.0, 1.0), 1.0 },
      new Object[] { new Vector(3.0, 4.0, 0.0), 5.0 },
    };
  }

}
