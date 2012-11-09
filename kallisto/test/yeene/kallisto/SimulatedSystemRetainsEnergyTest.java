package yeene.kallisto;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import yeene.kallisto.math.Vector;
import yeene.kallisto.systembuilder.SystemBuilder;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;
import static yeene.kallisto.math.Vector.NULLVECTOR;

/**
 * @author yeene
 */
public class SimulatedSystemRetainsEnergyTest {
  private SimulatedSystem simulatedSystem;
  private Satellite planet1;
  private Satellite planet2;
  private Satellite planet3;


  @BeforeMethod
  public void setup() {
    simulatedSystem = new SystemBuilder() {{
      createObject().named("sun").withRadius(1392700000l).withMass(1.989E30).withPosition(NULLVECTOR);
      createObject().named("mercury").withRadius(2439000l).withMass(3.302E23).withEclipticInclination(7.0).withBigHalfAxis(57909000000l).withThetaInDegrees(90).withStartSpeed(47870l);
      createObject().named("venus").withRadius(6051000l).withMass(4.869E24).withEclipticInclination(3.395).withBigHalfAxis(108160000000l).withThetaInDegrees(200).withStartSpeed(35020l);
    }}.getSystem();

    planet1 = simulatedSystem.getElements().get(0);
    planet2 = simulatedSystem.getElements().get(1);
    planet3 = simulatedSystem.getElements().get(2);
  }


  @Test(dataProvider = "number of steps")
  public void step_impulseAfterStepIsTheSameAsBeforeStep(final int numberOfSteps) throws Exception {
    // fixture setup: make a simulated System of two objects and note their individual rotational impulse.
    final Vector impulsePlanet1Before = impulse(planet1);
    final Vector impulsePlanet2Before = impulse(planet2);
    final Vector impulsePlanet3Before = impulse(planet3);

    // execution: perform a step.
    for(int i=0;i<numberOfSteps;i++) {
      simulatedSystem.step();
    }

    // assertion: get impulse after stepping and compare to original.
    final Vector impulsePlanet1After = impulse(planet1);
    final Vector impulsePlanet2After = impulse(planet2);
    final Vector impulsePlanet3After = impulse(planet3);

    final Vector totalRotationalImpulseBefore = impulsePlanet1Before.add(impulsePlanet2Before).add(impulsePlanet3Before);
    final Vector totalRotationalImpulseAfter = impulsePlanet1After.add(impulsePlanet2After).add(impulsePlanet3After);

    assertThat(totalRotationalImpulseBefore.length().subtract(totalRotationalImpulseAfter.length())).
      describedAs("impulse change on step for planet system").
      isZero();
  }

  @DataProvider(name = "number of steps")
  public Object[][] numberOfStepsProvider() {
    return new Object[][] {
      new Object[] {      1 },
      new Object[] {     10 },
      new Object[] {    100 },
//      new Object[] {   1000 },
//      new Object[] {  10000 },
//      new Object[] { 100000 },
    };
  }


  public static Vector getCenterOfMass(final SimulatedSystem simulatedSystem) {
    Vector result = Vector.NULLVECTOR;
    BigDecimal totalMass = BigDecimal.ZERO;

    for(final Satellite s : simulatedSystem.getElements()) {
      result = result.add(s.getPosition().mult(s.getMass()));
      totalMass = totalMass.add(s.getMass());
    }

    return result.div(totalMass);
  }

  public static Vector impulse(final Satellite planet) {
    return planet.getVelocity().mult(planet.getMass());
  }

}
