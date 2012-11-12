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

  private final Map<String, String> configuration = new HashMap<String, String>();
  private final List<String> errors = new ArrayList<String>();

  private Configuration() {

  }

  public String getValueFor(final String option) {
    return configuration.get(option);
  }

  public boolean hasErrors() {
    return errors.size() > 0;
  }


  public static Configuration loadConfiguration(final String[] configuration) {
    final Configuration result = new Configuration();

    for(int i=0;i<configuration.length;i++) {
      final String key = configuration[i];

      // check for known values.
      if(COMMANDLINE_ARG_GUI.equalsIgnoreCase(key)) {
        result.configuration.put(CONF_OUTPUT_METHOD, configuration[i+1]);
        i++;
      } else {
        result.errors.add(key);
      }
    }

    return result;
  }



  protected static final String COMMANDLINE_ARG_GUI = "--gui";
}
