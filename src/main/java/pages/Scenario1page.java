package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class Scenario1page {

	WebDriver driver;
	WebDriverWait wait;

	public Scenario1page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}

	// ===== LOCATORS =====
	By flightsTab = By.id("flights");
	By oneWayRadio = By.xpath("//label[@for='search_type_option_ONEWAY']");
	By leavingFromBtn = By.xpath("//button[@data-ui-name='input_location_from_segment_0']");
	By removeDefaultCity = By.xpath("//button[@data-autocomplete-chip-idx='0']");
	By goingToBtn = By.xpath("//button[@data-ui-name='input_location_to_segment_0']");
	By travellersBtn = By.xpath("//button[@data-ui-name='button_occupancy']");
	By childPlus = By.xpath("//button[@data-ui-name='button_occupancy_children_plus']");
	By childAgeDropdown = By.xpath("//select[@data-ui-name='select_occupancy_children_age_0']");
	By doneBtn = By.xpath("//button[@data-ui-name='button_occupancy_action_bar_done']");
	By dateBtn = By.xpath("//button[@data-ui-name='button_date_segment_0']");
	By june20 = By.xpath("//span[@data-date='2026-06-20']");
	By searchBtn = By.xpath("//button[.//span[text()='Search']]");

	// ===== BASIC FLOW =====

	public void openFlightsTab() {
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(flightsTab));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
	}

	public void selectOneWay() {
		wait.until(ExpectedConditions.elementToBeClickable(oneWayRadio)).click();
	}

	// ===== INPUT METHODS =====

	public void clearDepartureIfPresent() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(removeDefaultCity)).click();
		} catch (Exception e) {
			System.out.println("No default departure");
		}
	}

	public void clearDestinationIfPresent() {
		try {
			WebElement goingTo = wait.until(ExpectedConditions.presenceOfElementLocated(goingToBtn));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", goingTo);

			WebElement input = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@data-ui-name='input_text_autocomplete']")));

			input.sendKeys(Keys.CONTROL + "a");
			input.sendKeys(Keys.DELETE);

		} catch (Exception e) {
			System.out.println("Destination already empty");
		}
	}

	public void enterDepartureOnly(String city) {
		WebElement leavingFrom = wait.until(ExpectedConditions.presenceOfElementLocated(leavingFromBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", leavingFrom);

		clearDepartureIfPresent();

		WebElement input = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add another airport or city']")));

		input.sendKeys(city);

		WebElement option = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//li[@data-ui-name='locations_list_item'])[1]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	}

	public void enterDestinationOnly(String city) {
		WebElement goingTo = wait.until(ExpectedConditions.presenceOfElementLocated(goingToBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", goingTo);

		WebElement input = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@data-ui-name='input_text_autocomplete']")));

		input.sendKeys(city);

		WebElement option = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//li[@data-ui-name='locations_list_item'])[1]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	}

	public void addChildWithoutAge() {
		wait.until(ExpectedConditions.elementToBeClickable(travellersBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(childPlus)).click();
		wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
	}

	public void handleTravellersWithAge() {
		wait.until(ExpectedConditions.elementToBeClickable(travellersBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(childPlus)).click();

		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(childAgeDropdown));
		dropdown.sendKeys("6");

		wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
	}

	public void selectTravelDate() {
		wait.until(ExpectedConditions.elementToBeClickable(dateBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(june20)).click();
	}

	// ===== ACTION =====

	public void clickSearch() {
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
	}

	// ===== VALIDATIONS =====

	public void verifyDepartureError() {
		By error = By.xpath("//div[contains(text(),'departure') or contains(text(),'Leaving from')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(error));
	}

	public void verifyChildAgeError() {
		By error = By.xpath("//div[contains(text(),'age')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(error));
	}

	public void waitForResults() {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='progressbar']")));
		} catch (Exception e) {
			System.out.println("No loader");
		}
	}

	public void verifyFlightsDisplayed() {
		By results = By.xpath("//div[contains(@data-testid,'flight_card')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(results));
	}
}