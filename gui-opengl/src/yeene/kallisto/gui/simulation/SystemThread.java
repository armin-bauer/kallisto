package yeene.kallisto.gui.simulation;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import yeene.kallisto.SimulatedSystem;

/**
 * this thread holds the system.
 * @author yeene
 */
public class SystemThread implements Runnable {
  private static final Category LOGGER = Logger.getLogger(SystemThread.class);

  SystemThreadState state;
  private SimulatedSystem simulatedSystem;

  public SystemThread() {
    state = SystemThreadState.WAIT_FOR_PACKET;

    final Thread nestedThread = new Thread(this);
    nestedThread.setDaemon(true);
    nestedThread.start();
  }

  /**
   * set the simulated system to perform a simulation on and set the thread running state.
   * @param system sytem to be simulated
   */
  public void setSimulatedSystem(final SimulatedSystem system) {
    if(system == null) {
      state = SystemThreadState.WAIT_FOR_PACKET;
      simulatedSystem = system;
    } else {
      simulatedSystem = system;
      state = SystemThreadState.PERFORM_SIMULATION;
    }
  }

  @Override
  public void run() {
    // daemon thread: loop forever... or at least until the loop method returns false...
    while(true) {
      try {

        if(!loop()) {
          return;
        }

      } catch(InterruptedException ex) {
        LOGGER.error(ex, ex);
      }
    }
  }

  /**
   * this method is called each time the thread loops.
   * @return false if the thread should exit.
   */
  protected boolean loop() throws InterruptedException {

    switch (state) {
      case EXIT:
        return false;

      case PERFORM_SIMULATION:
        simulatedSystem.step();
        break;

      case WAIT_FOR_PACKET:
      default:
        Thread.sleep(100);
        break;
    }

    return true;
  }
}
