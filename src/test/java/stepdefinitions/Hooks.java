package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;
import utils.DriverFactory;

import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class Hooks {

    WebDriver driver;
    Properties prop;

    @Before
    public void setup() {

        prop = ConfigReader.initProperties();
        driver = DriverFactory.initDriver(prop.getProperty("browser"));

        driver.get(prop.getProperty("url"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}