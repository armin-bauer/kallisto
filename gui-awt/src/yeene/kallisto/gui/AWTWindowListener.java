package yeene.kallisto.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author yeene
 */
public class AWTWindowListener extends WindowAdapter {

  /**
   * Invoked when the user attempts to close the window
   * from the window's system menu.
   */
  @Override
  public void windowClosing(final WindowEvent e) {
    System.exit(0);
  }

}
