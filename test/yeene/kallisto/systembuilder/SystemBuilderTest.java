package yeene.kallisto.systembuilder;

import org.testng.annotations.Test;
import yeene.kallisto.Sattelite;
import yeene.kallisto.SimulatedSystem;
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

    final Sattelite sattelite = system.getElements().get(0);
    assertThat(sattelite.getName()).describedAs("name of the generated Object").isEqualTo("sun");
    assertThat(sattelite.getRadius()).describedAs("radius of generated Object").isEqualTo(BigDecimal.valueOf(1000));
    assertThat(sattelite.getMass()).describedAs("mass of the generated Object").isEqualTo(BigDecimal.valueOf(1200));
    assertThat(sattelite.getPosition()).describedAs("position of the generated object").isEqualTo(NULLVECTOR);
    assertThat(sattelite.getPosition()).describedAs("velocity of the generated object").isEqualTo(NULLVECTOR);
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
    final Sattelite sattelite = system.getElements().get(0);
    assertThat(sattelite.getMass().doubleValue()).describedAs("mass of the generated Object").isEqualTo(1200d);
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

    final Sattelite sattelite = system.getElements().get(0);
    assertThat(sattelite.getName()).describedAs("name of the generated Object").isEqualTo("sun");
    assertThat(sattelite.getRadius()).describedAs("radius of generated Object").isEqualTo(BigDecimal.valueOf(1000));
    assertThat(sattelite.getPosition()).describedAs("position of the generated object").isEqualTo(NULLVECTOR);
    assertThat(sattelite.getPosition()).describedAs("velocity of the generated object").isEqualTo(NULLVECTOR);

    final Double s = sattelite.getMass().subtract(BigDecimal.valueOf(5168962746l)).doubleValue();
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
    final Sattelite s = system.getElements().get(0);
    assertThat(s.getName()).describedAs("name of object").isEqualTo("mercury");
    assertThat(s.getRadius()).describedAs("radius of object").isEqualTo(BigDecimal.valueOf(2439000));
    assertThat(s.getMass()).describedAs("mass of object").isEqualTo(BigDecimal.valueOf(3.302E23));
    assertThat(s.getPosition()).describedAs("position of object").isEqualTo(new Vector(ZERO, BigDecimal.valueOf(57909000000l), ZERO));
    assertThat(s.getVelocity()).describedAs("velocity of object").isEqualTo(new Vector(BigDecimal.valueOf(-47870l), ZERO, ZERO));
  }
}
