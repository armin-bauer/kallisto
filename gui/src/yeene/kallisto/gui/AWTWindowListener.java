package yeene.kallisto.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author yeene
 */
public class AWTWindowListener implements WindowListener {
  /**
   * Invoked the first time a window is made visible.
   */
  @Override
  public void windowOpened(final WindowEvent e) {
  }

  /**
   * Invoked when the user attempts to close the window
   * from the window's system menu.
   */
  @Override
  public void windowClosing(final WindowEvent e) {
  }

  /**
   * Invoked when a window has been closed as the result
   * of calling dispose on the window.
   */
  @Override
  public void windowClosed(final WindowEvent e) {
    System.exit(0);
  }

  /**
   * Invoked when a window is changed from a normal to a
   * minimized state. For many platforms, a minimized window
   * is displayed as the icon specified in the window's
   * iconImage property.
   *
   * @see java.awt.Frame#setIconImage
   */
  @Override
  public void windowIconified(final WindowEvent e) {
  }

  /**
   * Invoked when a window is changed from a minimized
   * to a normal state.
   */
  @Override
  public void windowDeiconified(final WindowEvent e) {
  }

  /**
   * Invoked when the Window is set to be the active Window. Only a Frame or
   * a Dialog can be the active Window. The native windowing system may
   * denote the active Window or its children with special decorations, such
   * as a highlighted title bar. The active Window is always either the
   * focused Window, or the first Frame or Dialog that is an owner of the
   * focused Window.
   */
  @Override
  public void windowActivated(final WindowEvent e) {
  }

  /**
   * Invoked when a Window is no longer the active Window. Only a Frame or a
   * Dialog can be the active Window. The native windowing system may denote
   * the active Window or its children with special decorations, such as a
   * highlighted title bar. The active Window is always either the focused
   * Window, or the first Frame or Dialog that is an owner of the focused
   * Window.
   */
  @Override
  public void windowDeactivated(final WindowEvent e) {
  }
}
