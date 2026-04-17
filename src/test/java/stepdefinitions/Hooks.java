package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.Pages;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        Pages.driver = driver;
        Pages.initPages();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();   // closes all browser windows
        }
    }
}