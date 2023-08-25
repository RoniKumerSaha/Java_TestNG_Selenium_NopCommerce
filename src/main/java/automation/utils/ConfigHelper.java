package automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {

    static String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + "/src/main/java/automation/utils/config.properties";

    public static String getValue(String propertyName) {
        try (InputStream input = new FileInputStream(PROPERTIES_FILE_PATH)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            return prop.getProperty(propertyName);
        } catch (IOException ex) {
            return "";
        }
    }
}
