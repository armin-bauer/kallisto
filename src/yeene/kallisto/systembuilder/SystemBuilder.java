package yeene.kallisto.systembuilder;

import yeene.kallisto.Sattelite;
import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.math.Matrix;
import yeene.kallisto.math.Vector;
import yeene.kallisto.systembuilder.dsl.SystemBuilderDSLNamed;

import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static yeene.kallisto.math.MathUtils.rotationalMatrix;
import static yeene.kallisto.math.Vector.NULLVECTOR;

/**
 * The SystemBuilder is a Domain Specifig Language for generating Systems of particles
 * for the kalisto simulation engine.
 * Proposed Syntax:
 *
 *  new SystemBuilder() {[
 *    createObject().
 *      named("sun").
 *      withRadius(1000).
 *      withMass(1200).
 *      withPosition(NULLVECTOR);
 *
 *    createObject().
 *      named("mercury").
 *      withRadius(10).
 *      withMass(11).
 *      withInitialVelocity(10000).
 *      withEclipticInclination(7).
 *      withDistance(10000).
 *      withTheta(120.0);
 *  ]}.getSystem();
 *
 * @author yeene
 */
public class SystemBuilder {

  private final List<DSLObjectConfiguration> configurations = new ArrayList<DSLObjectConfiguration>();

  /**
   * build the system from the given configuration and return it.
   * @return initialised SimulatedSystem Object.
   */
  public SimulatedSystem getSystem() {
    final SimulatedSystem result = new SimulatedSystem();

    for(final DSLObjectConfiguration configuration : configurations) {
      result.addPlanets(createObjectFromConfiguration(configuration));
    }

    return result;
  }

  /**
   * begins the generation of a new Object for the simulated system and returns
   * the startpoint of the DSL
   * @return startpoint of generative DSL.
   */
  protected SystemBuilderDSLNamed createObject() {
    return saveConfiguration(new DSLObjectConfiguration());
  }


  private Sattelite createObjectFromConfiguration(final DSLObjectConfiguration configuration) {

    final Vector position;
    final Vector velocity;
    if(configuration.getInitialPosition() != null) {
      // use the configured data.
      position = configuration.getInitialPosition();
      velocity = configuration.getInitialVelocity();
    } else {
      // make by rotating stuff
      final Vector positionRelative = new Vector(configuration.getBigHalfAxis(), ZERO, ZERO);
      final Vector velocityRelative = new Vector(ZERO, configuration.getStartSpeed(), ZERO);

      final Matrix m = rotationalMatrix(0.0 /* TODO configuration.getTrackAngle() */, 0.0, configuration.getTheta());

      position = m.multiply(positionRelative);
      velocity = m.multiply(velocityRelative);
    }

    // create the sattelite.
    return new Sattelite(
      configuration.getName(),
      configuration.getObjectRadius(),
      configuration.getMass(),
      position,
      velocity,
      NULLVECTOR);
  }

  private DSLObjectConfiguration saveConfiguration(final DSLObjectConfiguration dslObjectConfiguration) {
    configurations.add(dslObjectConfiguration);
    return dslObjectConfiguration;
  }


}
