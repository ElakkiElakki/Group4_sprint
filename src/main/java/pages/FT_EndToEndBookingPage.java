package pages;

import org.openqa.selenium.support.ui.Select;

import utility.ConfigReader;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class FT_EndToEndBookingPage {

	WebDriver driver;
	WebDriverWait wait;

	public FT_EndToEndBookingPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}

	// ===== LOCATORS =====

	By flightsTab = By.id("flights");
	By oneWayRadio = By.xpath("//label[@for='search_type_option_ONEWAY']");
	By leavingFromBtn = By.xpath("//button[@data-ui-name='input_location_from_segment_0']");
	By removeDefaultCity = By.xpath("//button[@data-autocomplete-chip-idx='0']");
	By goingToBtn = By.xpath("//button[@data-ui-name='input_location_to_segment_0']");
	By dateBtn = By.xpath("//button[@data-ui-name='button_date_segment_0']");
	By june20 = By.xpath("//span[@data-date='2026-06-20']");
	By travellersBtn = By.xpath("//button[@data-ui-name='button_occupancy']");
	By childPlus = By.xpath("//button[@data-ui-name='button_occupancy_children_plus']");
	By childAgeDropdown = By.xpath("//select[@data-ui-name='select_occupancy_children_age_0']");
	By doneBtn = By.xpath("//button[@data-ui-name='button_occupancy_action_bar_done']");
	By searchBtn = By.xpath("//button[.//span[text()='Search']]");

	By firstFlightCard = By.id("flightcard-0");
	By viewDetailsBtn = By.xpath("//div[@id='flightcard-0']//button[@data-testid='flight_card_bound_select_flight']");
	By continueBtnDetails = By.xpath("//button[.//span[text()='Continue']]");
	By ecoClassicContinue = By.xpath("//button[@data-testid='branded_fare_cta_1']");

	By contactEmail = By.name("booker.email");
	By countryCode = By.name("countryCode");
	By phoneNumber = By.name("number");

	By nextBtn = By.xpath("//button[.//span[text()='Next']]");
	By noFlexRadio = By.xpath("//input[@name='ticket-type' and @value='standard']");
	By skipBtn = By.xpath("//button[.//span[text()='Skip']]");

	By paymentIframe = By.xpath("//iframe[@title='Payment']");

	// ===== METHODS =====

	public void openFlightsTab() {
		WebElement flights = wait.until(ExpectedConditions.elementToBeClickable(flightsTab));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", flights);
	}

	public void selectOneWay() {
		wait.until(ExpectedConditions.elementToBeClickable(oneWayRadio)).click();
	}

	public void handleMultipleDeparture() {

		WebElement leavingFrom = wait.until(ExpectedConditions.presenceOfElementLocated(leavingFromBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", leavingFrom);

		WebElement input = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add another airport or city']")));

		wait.until(ExpectedConditions.elementToBeClickable(removeDefaultCity)).click();

		input.sendKeys("Bangalore");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flights-searchbox_suggestions")));

		WebElement option1 = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//li[@data-ui-name='locations_list_item'])[1]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", option1);

		// click again
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", leavingFrom);

		input = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add another airport or city']")));

		input.sendKeys("Chennai");

		WebElement option2 = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//li[@data-ui-name='locations_list_item'])[1]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", option2);
	}

	public void handleMultipleDestination() {

		WebElement goingTo = wait.until(ExpectedConditions.presenceOfElementLocated(goingToBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", goingTo);

		WebElement input = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@data-ui-name='input_text_autocomplete']")));

		input.sendKeys("Paris");

		WebElement parisOption = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//li[@data-ui-name='locations_list_item']//b[text()='CDG']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", parisOption);
	}

	public void selectTravelDate() {
		wait.until(ExpectedConditions.elementToBeClickable(dateBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(june20)).click();
	}

	public void handleTravellers() {
		wait.until(ExpectedConditions.elementToBeClickable(travellersBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(childPlus)).click();

		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(childAgeDropdown));
		dropdown.sendKeys("6");

		wait.until(ExpectedConditions.elementToBeClickable(doneBtn)).click();
	}

	public void clickSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
	}

	public void openFirstFlightDetails() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstFlightCard));
		wait.until(ExpectedConditions.elementToBeClickable(viewDetailsBtn)).click();
	}

	public void clickContinueFromDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(continueBtnDetails)).click();
	}

	public void selectTicketAndContinue() {
		WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(ecoClassicContinue));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
	}

	// ===== TRAVELLERS =====

	public void fillTravellerDetails() {

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Enter your details')]")));

		List<WebElement> buttons = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(@aria-label,'Add this traveler')]")));

		String[] firstNames = { "Nethra", "Arjun" };
		String[] lastNames = { "Shee", "Kumar" };

		int i = 0;

		for (WebElement btn : buttons) {

			String label = btn.getAttribute("aria-label");

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name,'firstName')]")));

			driver.findElement(By.xpath("//input[contains(@name,'firstName')]")).sendKeys(firstNames[i]);
			driver.findElement(By.xpath("//input[contains(@name,'lastName')]")).sendKeys(lastNames[i]);

			new Select(driver.findElement(By.xpath("//select[contains(@name,'gender')]"))).selectByVisibleText("Male");

			if (label.contains("Child")) {
				new Select(driver.findElement(By.xpath("//select[contains(@name,'month')]")))
						.selectByVisibleText("June");

				driver.findElement(By.xpath("//input[contains(@name,'day')]")).sendKeys("10");
				driver.findElement(By.xpath("//input[contains(@name,'year')]")).sendKeys("2018");
			}

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[text()='Done']]"))).click();

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[contains(@name,'firstName')]")));

			i++;
		}
	}

	// ===== CONTACT =====

	public void fillContactDetails() {

		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmail));

		// 🔥 FORCE CLEAR (IMPORTANT)
		emailField.click();
		emailField.sendKeys(Keys.CONTROL + "a");
		emailField.sendKeys(Keys.DELETE);

		// now enter email
		emailField.sendKeys(ConfigReader.get("email"));

		// country code
		Select code = new Select(wait.until(ExpectedConditions.elementToBeClickable(countryCode)));
		code.selectByValue("in");

		// phone
		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumber));

		phone.click();
		phone.sendKeys(Keys.CONTROL + "a");
		phone.sendKeys(Keys.DELETE);
		phone.sendKeys("9876543210");

		// click next
		WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
	}

	public void selectTicketType() {

		By label = By.xpath("//label[@data-testid='ticket_type_radio_standard']");

		WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(label));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	}

	public void clickNextAfterTicket() {

		By nextBtn = By.xpath("//button[.//span[text()='Next']]");

		WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextBtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
	}

	public void skipSeatSelection() {

		By skipBtn = By.xpath("//button[.//span[text()='Skip']]");

		WebElement skip = wait.until(ExpectedConditions.elementToBeClickable(skipBtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", skip);
	}

	public void verifyPaymentPage() {

		By paymentFrame = By.xpath("//iframe[@title='Payment']");

		WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentFrame));

		if (frame.isDisplayed()) {
			System.out.println("SUCCESS: Reached Payment Page. Execution completed.");
		} else {
			throw new AssertionError("FAILED: Payment page not loaded");
		}
	}

	public void verifyResults() {

		By results = By.xpath("//div[contains(@data-testid,'flight_card')]");

		wait.until(ExpectedConditions.visibilityOfElementLocated(results));
	}
}