package yeene.kallisto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simmulation of a System of n bodies.
 *
 * @author yeene
 */
public class SimulatedSystem {

  private List<Sattelite> bodies = new ArrayList<Sattelite>();
  private long numberOfIterationSteps = 0l;

  /**
   * get a bounding box around the stuff
   * @return a bounding box around the system
   */
  public BoundingBox getBoundingBox() {
    BigDecimal maxx = new BigDecimal("-99999999999999999999999");
    BigDecimal maxy = new BigDecimal("-99999999999999999999999");
    BigDecimal maxz = new BigDecimal("-99999999999999999999999");
    BigDecimal minx = new BigDecimal("99999999999999999999999");
    BigDecimal miny = new BigDecimal("99999999999999999999999");
    BigDecimal minz = new BigDecimal("99999999999999999999999");

    for(final Sattelite s : bodies) {
      maxx = s.getPosition().getX().compareTo(maxx) > 0 ? s.getPosition().getX() : maxx;
      maxy = s.getPosition().getY().compareTo(maxy) > 0 ? s.getPosition().getY() : maxy;
      maxz = s.getPosition().getZ().compareTo(maxz) > 0 ? s.getPosition().getZ() : maxz;

      minx = s.getPosition().getX().compareTo(minx) < 0 ? s.getPosition().getX() : minx;
      miny = s.getPosition().getY().compareTo(miny) < 0 ? s.getPosition().getY() : miny;
      minz = s.getPosition().getZ().compareTo(minz) < 0 ? s.getPosition().getZ() : minz;
    }

    return new BoundingBox(maxx, maxy, maxz, minx, miny, minz);
  }

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

    numberOfIterationSteps++;
  }

  /**
   * add a number of planets
   * @param planets one or multiple planets
   */
  public void addPlanets(final Sattelite... planets) {
    Collections.addAll(bodies, planets);
  }

  /**
   * @return the elements in the system.
   */
  public List<Sattelite> getElements() {
    return bodies;
  }

  /**
   * @return the number of iteration steps in this simulation.
   */
  public long getIterationCount() {
    return numberOfIterationSteps;
  }
}
