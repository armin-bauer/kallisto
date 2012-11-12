package yeene.kallisto.gui.simulation;

/**
 * this is an internal of the SystemThread. It holds State info of the SystemThread.
 *
 * @author yeene
*/
enum SystemThreadState {
  WAIT_FOR_PACKET,
  EXIT,
  PERFORM_SIMULATION
}
