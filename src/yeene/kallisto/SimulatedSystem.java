package yeene.kallisto;

import java.util.ArrayList;
import java.util.List;

/**
 * Simmulation of a System of n bodies.
 *
 * @author yeene
 */
public class SimulatedSystem {

  private List<Sattelite> bodies = new ArrayList<Sattelite>();

  /**
   * let the system go to the next state.
   */
  public void step() {
    final List<SatteliteBodiesStateChangeCollector> stateChanges = new ArrayList<SatteliteBodiesStateChangeCollector>();

    // go through all planets and calculate the differences.
    for(final Sattelite i : bodies) {

      // get the base object
      SatteliteBodiesStateChangeCollector sw = new SatteliteBodiesStateChangeCollector(i);
      stateChanges.add(sw);

      // calculate other object's influence on this object.
      for(final Sattelite j : bodies) {
        sw.influenceSattelite(j);
      }
    }

    // step through the sattelites once more and perform calculations on the
    // objects.
    for(final SatteliteBodiesStateChangeCollector collector : stateChanges) {
      collector.apply();
    }
  }

  /**
   * add a number of planets
   * @param planets one or multiple planets
   */
  public void addPlanets(final Sattelite... planets) {
    for(final Sattelite planet : planets) {
      bodies.add(planet);
    }
  }
}
