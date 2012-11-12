package yeene.kallisto.gui;

/**
 * @author yeene
 */
public class Main {

  public static void main(final String[] argv) {
    final KallistoGUI gui = KallistoGUI.getInstance();

    // initialise the application.
    gui.init(argv);

    // get into the mainloop of the application. When the mainloop exists, quit the application completely.
    gui.run();
  }

}
