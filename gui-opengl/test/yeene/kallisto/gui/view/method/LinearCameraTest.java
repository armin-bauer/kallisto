package yeene.kallisto.gui.view.method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author armin
 */
public class LinearCameraTest {

  private LinearCamera camrea;

  @BeforeMethod
  public void setup() {
    camrea = new LinearCamera();
  }

  @Test
  public void recalculatePoint_scalesByConstantFactor() throws Exception {
    // fixture: create a vector to be scaled and set a scaling factor.
    final Vector vec = new Vector(1.0, 2.0, 3.0);
    camrea.setScalingFactorPoint(BigDecimal.valueOf(3.0));

    // execution: scale.
    final Vector result = camrea.recalculatePoint(vec);

    // assertion: should be 3, 6, 9 now
    assertThat(result).
      describedAs("resulting vector").
      isEqualTo(new Vector(3.0, 6.0, 9.0));
  }

  @Test
  public void recalculateRadius_scalesByConstantFactor() throws Exception {
    // fixture: crate a vector and a radius
    final BigDecimal length = BigDecimal.valueOf(10.0);
    camrea.setScalingFactorRadius(BigDecimal.valueOf(3.0));

    // execution: recalculate by radius
    final BigDecimal newRadius = camrea.recalculateRadius(Vector.NULLVECTOR, BigDecimal.valueOf(10.0));

    // assertion: should be 30.0
    assertThat(newRadius).
      describedAs("new radius of number").
      isGreaterThanOrEqualTo(BigDecimal.valueOf(30.0)).
      isLessThanOrEqualTo(BigDecimal.valueOf(30.0));
  }
}
