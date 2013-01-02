package yeene.kallisto;

import java.util.List;

/**
 * @author armin
 */
public interface SimulatedSystem {
  /**
   * This Method lets the system go to the next state.
   * It will calculate the forces the bodies in the simulation affect each other with and then
   * apply the changes in position, velocity etc. to them.
   */
  void step();

  /**
   * Add one or more Satellites to the system.
   *
   * @param planets one or multiple objects for the simulation
   */
  void addPlanets(Satellite... planets);

  /**
   * @return the elements in the system.
   */
  List<Satellite> getElements();

  /**
   * @return the number of iteration steps in this simulation.
   */
  long getIterationCount();

  /**
   * find an object by it's given name.
   * @param name name of the object to find.
   * @return the object found or null if not found.
   */
  Satellite find(String name);

  /**
   * Convenience Method for geting a bounding box around the stuff
   * @return a bounding box around the system
   */
  BoundingBox getBoundingBox();
}
