package yeene.kallisto;

import org.fest.assertions.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author yeene
 */
public class CalculatingSimulatedSystemTest {

  public static final Vector INITIAL_POSITION_PLANET_1 = new Vector(100.0, 0.0, 0.0);
  public static final Vector INITIAL_POSITION_PLANET_2 = new Vector(0.0, 0.0, 0.0);
  public static final Vector INITIAL_POSITION_PLANET_3 = new Vector(0.0, 100.0, 0.0);
  public static final int MAXIMUM_STEPS_BEFORE_COLLISION_IS_EXPECTED = 100000;

  private SimulatedSystem simulatedSystem;

  @BeforeMethod
  public void setUp() throws Exception {
    simulatedSystem = new CalculatingSimulatedSystem();
  }

  @Test
  public void step_increasesIterationCount() throws Exception {
    // fixture: at first: iteration count is 0

    // execution: call step three times, iteration count must go up each time.
    assertThat(simulatedSystem.getIterationCount()). describedAs("number of iterations").isEqualTo(0);
    simulatedSystem.step();
    assertThat(simulatedSystem.getIterationCount()). describedAs("number of iterations").isEqualTo(1);
    simulatedSystem.step();
    assertThat(simulatedSystem.getIterationCount()). describedAs("number of iterations").isEqualTo(2);

    // assertion:
  }

  @Test
  public void step_twoPlanetsFallNearEachOther_whenTheyHaveNoInitialVelocity() throws Exception {
    // fixture setup: make a simulatedSystem of two planets.
    final Satellite planet1 = generateFirstPlanet();
    final Satellite planet2 = generateSecondPlanet();

    simulatedSystem.addPlanets(planet1, planet2);

    // execution: step one bit.
    simulatedSystem.step();

    // assertion: the distance should be smaller after step than before step.
    final Vector distanceAfterStep = planet1.getPosition().sub(planet2.getPosition());
    final Vector distanceBeforeStep = INITIAL_POSITION_PLANET_1.sub(INITIAL_POSITION_PLANET_2);
    final BigDecimal differenceInDistance = distanceBeforeStep.sub(distanceAfterStep).length();

    assertThat(differenceInDistance.compareTo(BigDecimal.ZERO)).
      describedAs("distance between the planets after one step").
      isEqualTo(1); // >0
  }

  @Test
  public void step_hasSymmetricalResult_whenOneCentralBodyIsPulledBetweenTwoOthers() throws Exception {
    // fixture:
    final Satellite s1 = generateFirstPlanet();
    final Satellite s2 = generateSecondPlanet();
    final Satellite s3 = generateThirdPlanet();
    simulatedSystem.addPlanets(s1, s2, s3);

    // execution: perform one step
    simulatedSystem.step();

    // assertion: planet 2 should have same x any y value and both should have gone in the direction of (100.0, 100.0)
    assertThat(s2.getPosition().getX()).
      describedAs("x position of second planet").
      isEqualTo(s2.getPosition().getY());

    assertThat(s1.getPosition().getX()).
      describedAs("x position of first planet").
      isEqualTo(s3.getPosition().getY());

    assertThat(s1.getPosition().getY()).
      describedAs("y position of first planet").
      isEqualTo(s3.getPosition().getX());
  }

  @Test
  public void step_twoPlanetsCollide_whenTheyHaveNoInitialVelocity() throws Exception {
    // fixture setup: make a simulatedSystem of two planets
    final Satellite planet1 = generateFirstPlanet();
    final Satellite planet2 = generateSecondPlanet();

    simulatedSystem.addPlanets(planet1, planet2);

    // execution: execute for a while, wait until the planets distance increases. Should happen within {@link MAXIMUM_STEPS_BEFORE_COLLISION_IS_EXPECTED} steps.
    BigDecimal lastDistance = INITIAL_POSITION_PLANET_1.sub(INITIAL_POSITION_PLANET_2).length();
    int i=0;
    for (; i < MAXIMUM_STEPS_BEFORE_COLLISION_IS_EXPECTED; i++) {
      simulatedSystem.step();

      final BigDecimal newDistance = planet1.getPosition().sub(planet2.getPosition()).length();
      if(lastDistance.compareTo(newDistance) < 0) {
        // distance has increased again. planets have passed each other in the simmulation.
        break;
      }

      lastDistance = newDistance;
    }

    // assertion: should have taken less than 100000 steps to collide.
    assertThat(i).
      describedAs("numebr of steps before collision").
      satisfies(new Condition<Integer>() {
        @Override
        public boolean matches(final Integer value) {
          return value < MAXIMUM_STEPS_BEFORE_COLLISION_IS_EXPECTED;
        }
      });
  }

  @Test
  public void find_looksUpAnObjectFromTheSystem() throws Exception {
    // fixture: make system of two objects
    final Satellite planet1 = generateFirstPlanet();
    final Satellite planet2 = generateSecondPlanet();

    simulatedSystem.addPlanets(planet1, planet2);

    // execution: perform find on the system
    final Satellite planet = simulatedSystem.find("Planet 2");

    // assertion: planet should be the same as planet2
    assertThat(planet).describedAs("planet returned by find").isEqualTo(planet2);
  }



  private Satellite generateFirstPlanet() {
    return new Satellite("Planet 1", BigDecimal.TEN, BigDecimal.valueOf(8000000000000l), INITIAL_POSITION_PLANET_1, Vector.NULLVECTOR);
  }

  private Satellite generateSecondPlanet() {
    return new Satellite("Planet 2", BigDecimal.TEN, BigDecimal.valueOf(300000000000l), INITIAL_POSITION_PLANET_2, Vector.NULLVECTOR);
  }

  private Satellite generateThirdPlanet() {
    return new Satellite("Planet 3", BigDecimal.TEN, BigDecimal.valueOf(8000000000000l), INITIAL_POSITION_PLANET_3, Vector.NULLVECTOR);
  }

}
