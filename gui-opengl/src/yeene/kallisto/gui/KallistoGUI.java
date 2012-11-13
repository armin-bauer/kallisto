package yeene.kallisto.gui;

import yeene.kallisto.gui.driver.GraphicsFactory;

import static yeene.kallisto.gui.Configuration.CONF_OUTPUT_METHOD;

/**
 * base class of the kallisto gui holding the manager components for different tasks
 * within the application.
 *
 * @author yeene
 */
public class KallistoGUI {

  private static KallistoGUI instance;

  private Configuration configuration;


  private KallistoGUI() {

  }


  public static KallistoGUI getInstance() {
    if(instance == null) {
      instance = new KallistoGUI();
    }

    return instance;
  }

  void init(final String[] argv) {
    // load the configuration
    configuration = Configuration.loadConfiguration(argv);

    // init the calculation API
    applyGraphicsSettings();

    // init the connection to BOINC

    // init the GUI.

    // setup the simulated system.
  }

  /**
   * starts the main loop in the application.
   */
  void run() {
    GraphicsFactory.getInstance().mainloop();
  }

  private void applyGraphicsSettings() {
    // set the driver type.
    final String driverType = configuration.getValueFor(CONF_OUTPUT_METHOD);
    if("opengl".equalsIgnoreCase(driverType)) {
      GraphicsFactory.setDriverType(GraphicsFactory.DriverType.OPENGL);
    } else {
      GraphicsFactory.setDriverType(GraphicsFactory.DriverType.TERMINAL);
    }

    // start up the graphics driver.
    GraphicsFactory.getInstance().init(1024, 768, 24, Constants.WINDOW_NAME);
  }

}
