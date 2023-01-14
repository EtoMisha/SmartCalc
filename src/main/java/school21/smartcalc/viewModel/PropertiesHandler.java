package school21.smartcalc.viewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import school21.smartcalc.models.CalcProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesHandler.class);

    private static final String PROPERTIES_PATH = "/SmartCalcFiles/config.properties";
    private static final String USER_HOME_DIR = System.getProperty("user.home");

    public CalcProperties getProperties() {

        CalcProperties response = new CalcProperties();
        try (FileInputStream input = new FileInputStream(USER_HOME_DIR + PROPERTIES_PATH)) {
            Properties prop = new Properties();
            prop.load(input);

            response.setBgColor1(prop.getProperty("background.color1"));
            response.setBgColor2(prop.getProperty("background.color2"));
            response.setFontSize(prop.getProperty("font.size"));
            response.setFontColor(prop.getProperty("font.color"));
            response.setStatus("OK");
            LOG.info("[getProperties] ok, response " + response);
        } catch (IOException ex) {
            response.setStatus("Error");
            response.setMessage(ex.getMessage());
            LOG.error("[getProperties] error: " + ex.getMessage());
        }
        return response;
    }
}
