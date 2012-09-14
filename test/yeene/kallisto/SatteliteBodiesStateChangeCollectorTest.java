package yeene.kallisto;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author yeene
 */
public class SatteliteBodiesStateChangeCollectorTest {

  private static final BigDecimal RADIUS_OF_FIRST_BODY = BigDecimal.valueOf(10000000);
  private static final BigDecimal MASS_OF_FIRST_BODY = BigDecimal.valueOf(2000000);
  private static final BigDecimal RADIUS_OF_SECOND_BODY = BigDecimal.valueOf(5000000);
  private static final BigDecimal MASS_OF_SECOND_BODY = BigDecimal.valueOf(20000000);

  private SatteliteBodiesStateChangeCollector difference;
  private Sattelite baseSattelite;
  private Sattelite otherSattelite;


  @BeforeMethod
  public void setUp() throws Exception {
    baseSattelite = new Sattelite("First", RADIUS_OF_FIRST_BODY, MASS_OF_FIRST_BODY, new Vector(10.0, 10.0, 10.0), new Vector(1.0, 0.0, 0.0), new Vector(1.0, 0.0, 0.0));
    otherSattelite = new Sattelite("Second", RADIUS_OF_SECOND_BODY, MASS_OF_SECOND_BODY, new Vector(100.0, 10.0, 10.0), new Vector(1.0, 0.0, 0.0), new Vector(1.0, 0.0, 0.0));

    difference = new SatteliteBodiesStateChangeCollector(baseSattelite);
  }

  @Test
  public void influenceSattelite_leavesTheBodyState_whenInfluencedByItself() throws Exception {
    // fixture setup:

    // execution; caclulate influence.
    difference.influenceSattelite(baseSattelite);

    // assertion: no direction change.
    assertThat(difference.getAccellerationDifference()).
      describedAs("acceleration").
      isEqualTo(new Vector(0.0, 0.0, 0.0));
  }

  @Test
  public void influenceSattelite_changesTheBodyState_whenInfluencedByOther() throws Exception {
    // fixture setup:

    // execution: calculate influence
    difference.influenceSattelite(otherSattelite);

    // assertion: acceleration change
    assertThat(difference.getAccellerationDifference()).
      describedAs("acceleration").
      isEqualTo(new Vector(2469.1358, 0.0, 0.0));
  }
}
