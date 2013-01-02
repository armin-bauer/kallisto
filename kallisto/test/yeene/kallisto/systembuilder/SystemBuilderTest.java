package yeene.kallisto.systembuilder;

import org.testng.annotations.Test;
import yeene.kallisto.Constants;
import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.Satellite;
import yeene.kallisto.math.Vector;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.fest.assertions.Assertions.assertThat;
import static yeene.kallisto.math.Vector.NULLVECTOR;

/**
 * @author yeene
 */
public class SystemBuilderTest {

  @Test
  public void getSystem_generatesSystemWithConfiguredData_whenObjectWithPositionIsGiven() throws Exception {
    // fixture:
    final SystemBuilder builder = new SystemBuilder() {{
      createObject().
        named("sun").
        withRadius(1000).
        withMass(1200).
        withPosition(NULLVECTOR);
    }};

    // execution: call getSystem() to generate the initial system.
    final SimulatedSystem system = builder.getSystem();

    // assertion: builder should contain a single object.
    assertThat(system.getElements()).
      describedAs("elements in the generated system").hasSize(1);

    final Satellite satellite = system.getElements().get(0);
    assertThat(satellite.getName()).describedAs("name of the generated Object").isEqualTo("sun");
    assertThat(satellite.getRadius()).describedAs("radius of generated Object").isEqualTo(BigDecimal.valueOf(1000));
    assertThat(satellite.getMass()).describedAs("mass of the generated Object").isEqualTo(BigDecimal.valueOf(1200));
    assertThat(satellite.getPosition()).describedAs("position of the generated object").isEqualTo(NULLVECTOR);
    assertThat(satellite.getPosition()).describedAs("velocity of the generated object").isEqualTo(NULLVECTOR);
  }

  @Test
  public void getSystem_generatesSystemWithConfiguredData_forMassAsDouble() throws Exception {
    // fixture:
    final SystemBuilder builder = new SystemBuilder() {{
      createObject().
        named("sun").
        withRadius(1000).
        withMass(1200.0d).
        withPosition(NULLVECTOR);
    }};

    // execution: call getSystem() to generate the initial system.
    final SimulatedSystem system = builder.getSystem();

    // assertion: builder should contain a single object.
    final Satellite satellite = system.getElements().get(0);
    assertThat(satellite.getMass().doubleValue()).describedAs("mass of the generated Object").isEqualTo(1200d);
  }

  @Test
  public void getSystem_generatesSystemWithConfiguredData_whenObjectIsGivenWithDensityInsteadOfMass() throws Exception {
    // fixture:
    final SystemBuilder builder = new SystemBuilder() {{
      createObject().named("sun").withRadius(1000).withDensity(1.234).withPosition(NULLVECTOR);
    }};

    // execution: call getSystem() to generate the initial system.
    final SimulatedSystem system = builder.getSystem();

    // assertion: builder should contain a single object.
    assertThat(system.getElements()).
      describedAs("elements in the generated system").hasSize(1);

    final Satellite satellite = system.getElements().get(0);
    assertThat(satellite.getName()).describedAs("name of the generated Object").isEqualTo("sun");
    assertThat(satellite.getRadius()).describedAs("radius of generated Object").isEqualTo(BigDecimal.valueOf(1000));
    assertThat(satellite.getPosition()).describedAs("position of the generated object").isEqualTo(NULLVECTOR);
    assertThat(satellite.getPosition()).describedAs("velocity of the generated object").isEqualTo(NULLVECTOR);

    final Double s = satellite.getMass().subtract(BigDecimal.valueOf(5168962746l)).doubleValue();
    assertThat(Math.abs(s)).describedAs("mass of the generated Object").isLessThan(1);
  }

  @Test
  public void getSystem_caclulatesPosition_whenEclipticInclinationDistanceAndThetaAreSupplied() throws Exception {
    // fixture:
    final SystemBuilder builder = new SystemBuilder() {{
      createObject().
        named("mercury").
        withRadius(2439000l).
        withMass(3.302E23).
        withEclipticInclination(0.0).
        withBigHalfAxis(57909000000l).
        withThetaInDegrees(90).
        withStartSpeed(47870l);
    }};

    // execution: call getSystem() to generate the initial system.
    final SimulatedSystem system = builder.getSystem();

    // assertion: make assertions on the position of the planet.
    final Satellite s = system.getElements().get(0);
    assertThat(s.getName()).describedAs("name of object").isEqualTo("mercury");
    assertThat(s.getRadius()).describedAs("radius of object").isEqualTo(BigDecimal.valueOf(2439000));
    assertThat(s.getMass()).describedAs("mass of object").isEqualTo(BigDecimal.valueOf(3.302E23));
    assertThat(s.getPosition()).describedAs("position of object").isEqualTo(new Vector(ZERO, BigDecimal.valueOf(57909000000l), ZERO));
    assertThat(s.getVelocity()).describedAs("velocity of object").isEqualTo(new Vector(BigDecimal.valueOf(-47870l), ZERO, ZERO));
  }

  @Test
  public void getSystem_setsInitialVelocity() throws Exception {
    // fixture:
    final SystemBuilder builder = new SystemBuilder() {{
      createObject().
        named("mercury").
        withRadius(1l).
        withMass(1d).
        withVelocity(new Vector(10.0, 0.0, 0.0));
    }};

    // execution: call getSystem() to generate initial system
    final SimulatedSystem system = builder.getSystem();
    system.step();

    // assertion: make assertions on the position of the planet.
    final Satellite s = system.getElements().get(0);
    assertThat(s.getPosition()).
      describedAs("position of object").
      isEqualTo(new Vector(Constants.DT.multiply(BigDecimal.TEN).doubleValue(), 0.0, 0.0));
  }

  @Test
  public void getSystem_canCreateObjectsThatAreDefinedRelativelyToOtherObjects() throws Exception {
    // fixture: describe a system from moon and earth with the moon being defined relatively to the earth.
    final SystemBuilder builder = new SystemBuilder() {{
      createObject().named("sun").withRadius(1392700000l).withMass(1.989E30).withPosition(NULLVECTOR);

      // generate the inner planets
      createObject().named("earth").withRadius(6378000l).withMass(5.974E24).withEclipticInclination(0.0).withBigHalfAxis(149600000000l).withThetaInDegrees(0).withStartSpeed(29780l);
      createObject().named("moon").withRadius(3475l).withMass(7.349E22).startingFrom("earth").withDistance(384400000l).withThetaInDegrees(0).withRelativeEclipticInclination(0.0).withRelativeStartSpeed(1032l);
    }};

    // execution: build the system
    final SimulatedSystem system = builder.getSystem();

    // assertion: check properties of moon.
    final Satellite earth = system.getElements().get(1);
    final Satellite moon = system.getElements().get(2);

    assertThat(moon.getPosition()).describedAs("initial position of the moon").isEqualTo(earth.getPosition().add(new Vector(384400000.0, 0.0, 0.0)));
    assertThat(moon.getVelocity()).describedAs("initial velocity of the moon").isEqualTo(earth.getVelocity().add(new Vector(0.0, 1032.0, 0.0)));
  }

}
