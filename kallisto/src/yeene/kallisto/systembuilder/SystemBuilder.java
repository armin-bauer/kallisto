package yeene.kallisto.systembuilder;

import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.Satellite;
import yeene.kallisto.CalculatingSimulatedSystem;
import yeene.kallisto.math.Matrix;
import yeene.kallisto.math.Vector;
import yeene.kallisto.systembuilder.dsl.SystemBuilderDSLNamed;

import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static yeene.kallisto.math.MathUtils.rotationalMatrix;

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
   * @return initialised CalculatingSimulatedSystem Object.
   */
  public SimulatedSystem getSystem() {
    final SimulatedSystem result = new CalculatingSimulatedSystem();

    for(final DSLObjectConfiguration configuration : configurations) {
      result.addPlanets(createObjectFromConfiguration(configuration, result));
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


  private Satellite createObjectFromConfiguration(final DSLObjectConfiguration configuration, final SimulatedSystem resultingSystem) {

    final Vector position;
    final Vector velocity;

    // CASE1: a position was defined relatively to another object.
    if(configuration.getRelative() != null) {
      final Satellite relative = resultingSystem.find(configuration.getRelative());

      final Vector relativePositionToInitialSystem = new Vector(configuration.getDistanceFromRelative(), ZERO, ZERO);
      final Vector relativeVelocityToInitialSystem = new Vector(ZERO, configuration.getVelocityRelative(), ZERO);

      final Matrix m = rotationalMatrix(0.0 /*configuration.getThetaRelative() */, 0.0, configuration.getThetaRelative());

      position = m.multiply(relativePositionToInitialSystem).add(relative.getPosition());
      velocity = m.multiply(relativeVelocityToInitialSystem).add(relative.getVelocity());
    }
    // CASE2: a position was defined directly, usw it
    else if(configuration.getInitialPosition() != null) {
      // use the configured data.
      position = configuration.getInitialPosition();
      velocity = configuration.getInitialVelocity();
    }

    // CASE3: a position was defined through a circle, so calculate vectors
    else {
      // make by rotating stuff
      final Vector positionRelative = new Vector(configuration.getBigHalfAxis(), ZERO, ZERO);
      final Vector velocityRelative = new Vector(ZERO, configuration.getStartSpeed(), ZERO);

      final Matrix m = rotationalMatrix(0.0 /* TODO configuration.getTrackAngle() */, 0.0, configuration.getTheta());

      position = m.multiply(positionRelative);
      velocity = m.multiply(velocityRelative);
    }

    // create the satellite.
    return new Satellite(
      configuration.getName(),
      configuration.getObjectRadius(),
      configuration.getMass(),
      position,
      velocity
    );
  }

  private DSLObjectConfiguration saveConfiguration(final DSLObjectConfiguration dslObjectConfiguration) {
    configurations.add(dslObjectConfiguration);
    return dslObjectConfiguration;
  }


}
