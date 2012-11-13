package yeene.kallisto.gui.driver.terminal;

import yeene.kallisto.gui.driver.GraphicFrame;

/**
 * this defines a graphic interface that has no output
 */
public class TerminalOutput implements GraphicFrame {


  @Override
  public void init(final int desiredMaxx, final int desiredMaxy, final int desiredBpp, final String windowName) {
    // nothing to be done here.
  }

  @Override
  public void mainloop() {

    // just loop forever
    while(true) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // on exception just exit.
        return;
      }
    }

  }


}
