package yeene.kallisto.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this utility class for managing the configuration loads / stores defaults to the configuration
 * and parses the commandline to override configuration data.
 *
 * @author yeene
 */
public class Configuration {

  public static final String CONF_OUTPUT_METHOD = "output.graphic.driver";
  public static final String CONF_THREADPOOL_SIZE = "threadpool.size";

  private final Map<String, String> configuration = new HashMap<String, String>();
  private boolean displayHelp = false;
  private final List<String> errors = new ArrayList<String>();

  private Configuration() {

  }

  public String getValueFor(final String option) {
    return configuration.get(option);
  }

  public int getIntValueFor(final String option, int defaultValue) {
    try {
      return Integer.valueOf(configuration.get(option));
    } catch(Exception ignored) {
      return defaultValue;
    }
  }

  public boolean hasErrors() {
    return errors.size() > 0;
  }

  public boolean isDisplayHelp() {
    return displayHelp;
  }

  public static Configuration loadConfiguration(final String[] configuration) {
    final Configuration result = new Configuration();

    setDefaults(result);

    for(int i=0;i<configuration.length;i++) {
      final String key = configuration[i];

      // check for known values.
      if(COMMANDLINE_ARG_GUI.equalsIgnoreCase(key)) {
        result.configuration.put(CONF_OUTPUT_METHOD, configuration[i + 1]);
        i++;
      } else if(COMMANDLINE_ARG_HELP.equalsIgnoreCase(key)) {
        result.displayHelp = true;
      } else {
        result.errors.add(key);
      }
    }

    // call help.
    if(result.isDisplayHelp()) {
      showHelpText();
    }

    return result;
  }

  /**
   * sets default settings to the Configuration supplied to the function
   * @param result configuration to change
   */
  private static void setDefaults(final Configuration result) {
    result.configuration.put(CONF_THREADPOOL_SIZE, "2");
  }

  private static void showHelpText() {
    System.out.println("Kallisto - GUI - Commandline Options:\n");
    System.out.println("  --help                     ... this help text.\n");
    System.out.println("  --gui <option>             ... set display technology to be used for graphics output.\n");
    System.out.println("        Available options are: \n");
    System.out.println("        opengl               ... with graphics output.\n");
    System.out.println("        term                 ... basic console support only\n");
    System.out.println();
    System.out.println();
  }


  protected static final String COMMANDLINE_ARG_GUI = "--gui";
  protected static final String COMMANDLINE_ARG_HELP = "--help";
}
