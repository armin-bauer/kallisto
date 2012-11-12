package yeene.kallisto.gui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static yeene.kallisto.gui.Configuration.*;

/**
 * @author yeene
 */
public class ConfigurationTest {


  public static final String VALUE_NULL = "null";
  public static final String VALUE_OPENGL = "opengl";

  @Test(dataProvider = "commandline x option x value - provider")
  public void loadConfiguration_loadsConfigurationFromCommandline(final String[] commandline, final String option, final String value) throws Exception {
    // fixture: not needed

    // execution: load the configuration
    final Configuration configuration = Configuration.loadConfiguration(commandline);

    // assertion: should contain the given option and it should have the given value
    assertThat(configuration.getValueFor(option)).describedAs("value of the configuration option named " + option).isEqualTo(value);
  }

  @DataProvider(name = "commandline x option x value - provider")
  public Object[][] configurationDataProvider() {
    final String[] commandLine1 = new String[] { COMMANDLINE_ARG_GUI, VALUE_OPENGL };
    final String[] commandLine2 = new String[] { COMMANDLINE_ARG_GUI, VALUE_NULL};

    return new Object[][] {
      new Object[] { commandLine1, CONF_OUTPUT_METHOD, VALUE_OPENGL},
      new Object[] { commandLine2, CONF_OUTPUT_METHOD, VALUE_NULL}
    };
  }

  @Test
  public void hasErrors_returnsTrue_whenInvalidCommandlineWasGiven() throws Exception {
    // fixture:

    // execution: make config with invalid commandline.
    final Configuration configuration = Configuration.loadConfiguration(new String[] { "--invalid-configuration-option" });

    // assertion: hasErrors should return true.
    assertThat(configuration.hasErrors()).describedAs("error indicator on the parsed configuration").isTrue();
  }

}
