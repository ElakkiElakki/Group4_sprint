package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static Properties initProperties() {

        prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config/config.properties"
            );
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("config.properties not found");
        }

        return prop;
    }
}