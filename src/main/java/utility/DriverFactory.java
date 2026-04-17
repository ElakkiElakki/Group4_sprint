package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver getDriver() {

		if (driver == null) {

			String browser = ConfigReader.get("browser");

			if (browser.equalsIgnoreCase("chrome")) {

				// 🔥 THIS LINE = auto downloads correct ChromeDriver
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");

				driver = new ChromeDriver(options);
			}

			else if (browser.equalsIgnoreCase("edge")) {

				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().window().maximize();
			}

			else if (browser.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}

			else {
				throw new RuntimeException("Invalid browser in config.properties");
			}

			driver.get(ConfigReader.get("baseUrl"));
		}

		return driver;
	}

	public static void quitDriver() {

		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}