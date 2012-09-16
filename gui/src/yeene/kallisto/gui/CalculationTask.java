package yeene.kallisto.gui;

import yeene.kallisto.SimulatedSystem;

/**
 * @author yeene
 */
public class CalculationTask implements Runnable {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    final SimulatedSystem system = Main.getInstance().getSimulatedSystem();

    while (true) {
      system.step();
    }
  }

}
