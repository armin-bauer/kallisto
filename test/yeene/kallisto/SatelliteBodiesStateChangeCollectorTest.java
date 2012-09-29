package yeene.kallisto;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author yeene
 */
public class SatelliteBodiesStateChangeCollectorTest {

  private static final BigDecimal RADIUS_OF_FIRST_BODY = BigDecimal.valueOf(10000000);
  private static final BigDecimal MASS_OF_FIRST_BODY = BigDecimal.valueOf(2000000);
  private static final BigDecimal RADIUS_OF_SECOND_BODY = BigDecimal.valueOf(5000000);
  private static final BigDecimal MASS_OF_SECOND_BODY = BigDecimal.valueOf(20000000);

  private SatelliteBodiesStateChangeCollector difference;
  private Satellite baseSatellite;
  private Satellite otherSatellite;


  @BeforeMethod
  public void setUp() throws Exception {
    baseSatellite = new Satellite("First", RADIUS_OF_FIRST_BODY, MASS_OF_FIRST_BODY, new Vector(10.0, 10.0, 10.0), new Vector(1.0, 0.0, 0.0));
    otherSatellite = new Satellite("Second", RADIUS_OF_SECOND_BODY, MASS_OF_SECOND_BODY, new Vector(100.0, 10.0, 10.0), new Vector(1.0, 0.0, 0.0));

    difference = new SatelliteBodiesStateChangeCollector(baseSatellite);
  }

  @Test
  public void influenceSatellite_leavesTheBodyState_whenInfluencedByItself() throws Exception {
    // fixture setup:

    // execution; caclulate influence.
    difference.influenceSatellite(baseSatellite);

    // assertion: no direction change.
    assertThat(difference.getInflictedAcceleration()).
      describedAs("acceleration").
      isEqualTo(new Vector(0.0, 0.0, 0.0));
  }

  @Test
  public void influenceSatellite_changesTheBodyState_whenInfluencedByOther() throws Exception {
    // fixture setup:

    // execution: calculate influence
    difference.influenceSatellite(otherSatellite);

    // assertion: acceleration change
    assertThat(difference.getInflictedAcceleration()).
      describedAs("acceleration").
      isEqualTo(new Vector(2469.1358, 0.0, 0.0));
  }
}
