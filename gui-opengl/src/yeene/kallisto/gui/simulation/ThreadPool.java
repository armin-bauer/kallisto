package yeene.kallisto.gui.simulation;

/**
 * this class will hold onto a few threads that can perform simulation operations in the system.
 *
 * @author yeene
 */
public class ThreadPool {

  protected SystemThread threads[];

  public ThreadPool(final int numberOfThreads) {
    threads = new SystemThread[numberOfThreads];
    for(int i = 0; i < numberOfThreads; i++) {
      threads[i] = new SystemThread();
    }
  }



}
