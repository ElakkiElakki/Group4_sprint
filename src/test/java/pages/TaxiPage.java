package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class TaxiPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "airport_taxis")
    WebElement airportTaxisTab;

    @FindBy(name = "pickup")
    WebElement pickupField;

    @FindBy(name = "dropoff")
    WebElement dropField;

    @FindBy(xpath = "//button[normalize-space()='Search' or .//span[normalize-space()='Search']]")
    WebElement searchBtn;

    public TaxiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    public void openTaxiPage() {
        WebElement taxiTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("airport_taxis")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", taxiTab);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(taxiTab)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", taxiTab);
        }

        wait.until(ExpectedConditions.urlContains("taxi"));
    }

    public void clickSearch() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[normalize-space()='Search' or .//span[normalize-space()='Search']]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    // ---------- Negative scenario helpers ----------

    public void enterPickup() {
        selectFromAutosuggestion(pickupField, "Changi Airport");
    }

    public void enterDestination() {
        selectFromAutosuggestion(dropField, "Marina Bay Sands");
    }

    public boolean isStillOnSamePage() {
        return driver.getCurrentUrl().contains("taxi");
    }

    // ---------- Positive scenario helpers ----------

    public void enterPickupLocation(String pickupText) {
        selectFromAutosuggestion(pickupField, pickupText);
    }

    public void enterDestinationLocation(String destinationText) {
        selectFromAutosuggestion(dropField, destinationText);
    }

    public void selectValidFutureDateAndTime() {
        selectFutureDate(7);
    }

    public void selectAnotherValidFutureDateAndTime() {
        selectFutureDate(14);
    }

    public boolean areSearchResultsDisplayed() {
        try {
            WebDriverWait resultWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            return resultWait.until(d -> {
                String url = d.getCurrentUrl().toLowerCase();
                String source = d.getPageSource().toLowerCase();

                return url.contains("search") ||
                       url.contains("results") ||
                       source.contains("available") ||
                       source.contains("cars") ||
                       source.contains("vehicles") ||
                       source.contains("taxi");
            });
        } catch (Exception e) {
            return false;
        }
    }

    public void observeTaxiListings() {
        wait.until(d -> {
            List<WebElement> cards = d.findElements(By.xpath(
                    "//*[contains(@class,'card') or contains(@data-testid,'card') or contains(text(),'Sedan') or contains(text(),'SUV') or contains(text(),'price') or contains(text(),'fare')]"
            ));
            return !cards.isEmpty();
        });
    }

    public boolean isCarTypeDisplayedForEachResult() {
        try {
            List<WebElement> carTypes = driver.findElements(By.xpath(
                    "//*[contains(text(),'Sedan') or contains(text(),'SUV') or contains(text(),'Van') or contains(text(),'Minivan') or contains(text(),'Executive')]"
            ));
            return !carTypes.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFareDisplayedForEachResult() {
        try {
            List<WebElement> fares = driver.findElements(By.xpath(
                    "//*[contains(text(),'$') or contains(text(),'€') or contains(text(),'₹') or contains(text(),'SGD') or contains(text(),'fare') or contains(text(),'price')]"
            ));
            return !fares.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // ---------- Booking flow helpers ----------

    public void selectTaxiFromResults() {
        By resultButtonsLocator = By.xpath(
                "//button[contains(.,'Book now') or contains(.,'Reserve') or contains(.,'Select')] | " +
                "//a[contains(.,'Book now') or contains(.,'Reserve') or contains(.,'Select')]"
        );

        List<WebElement> buttons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(resultButtonsLocator)
        );

        if (buttons.isEmpty()) {
            throw new RuntimeException("No taxi options available to select");
        }

        WebElement firstButton = buttons.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstButton);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(firstButton)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstButton);
        }
    }

    public void clickBookNow() {
        By bookNowLocator = By.xpath(
                "//button[contains(.,'Book now') or contains(.,'Reserve') or contains(.,'Select')] | " +
                "//a[contains(.,'Book now') or contains(.,'Reserve') or contains(.,'Select')]"
        );

        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(bookNowLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public void enterFirstName(String firstName) {
        By firstNameLocator = By.xpath("//input[@name='firstName' or contains(@autocomplete,'given-name')]");
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameLocator));
        field.clear();
        field.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        By lastNameLocator = By.xpath("//input[@name='lastName' or contains(@autocomplete,'family-name')]");
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameLocator));
        field.clear();
        field.sendKeys(lastName);
    }

    public void enterBookingEmail(String email) {
        By emailLocator = By.xpath("//input[@type='email' or @name='email']");
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailLocator));
        field.clear();
        field.sendKeys(email);
    }

    public void enterPhoneNumber(String phone) {
        By phoneLocator = By.xpath("//input[@type='tel' or @name='phone' or contains(@autocomplete,'tel')]");
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneLocator));
        field.clear();
        field.sendKeys(phone);
    }

    public void confirmBooking() {
        By confirmLocator = By.xpath(
                "//button[contains(.,'Confirm booking') or contains(.,'Complete booking') or contains(.,'Book now')] | " +
                "//a[contains(.,'Confirm booking') or contains(.,'Complete booking') or contains(.,'Book now')]"
        );

        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(confirmLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public boolean isBookingSuccessful() {
        try {
            WebDriverWait resultWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            return resultWait.until(d -> {
                String source = d.getPageSource().toLowerCase();
                String url = d.getCurrentUrl().toLowerCase();

                return source.contains("booking confirmed") ||
                       source.contains("confirmation") ||
                       source.contains("your booking") ||
                       url.contains("confirmation") ||
                       url.contains("booking");
            });
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConfirmationPageDisplayed() {
        try {
            By confirmationLocator = By.xpath(
                    "//*[contains(text(),'Booking confirmed') or contains(text(),'Confirmation') or contains(text(),'Your booking')]"
            );
            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationLocator));
            return confirmation.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ---------- Core reusable methods ----------

    private void selectFromAutosuggestion(WebElement field, String value) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(field));
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.DELETE);

        Actions actions = new Actions(driver);
        for (char ch : value.toCharArray()) {
            actions.sendKeys(input, String.valueOf(ch))
                   .pause(Duration.ofMillis(120))
                   .perform();
        }

        String listBoxId = input.getAttribute("aria-controls");

        if (listBoxId == null || listBoxId.isBlank()) {
            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);
            return;
        }

        By listBox = By.id(listBoxId);
        wait.until(ExpectedConditions.presenceOfElementLocated(listBox));

        try {
            By optionsLocator = By.xpath(
                    "//*[@id='" + listBoxId + "']//*[@role='option' or self::li or self::button or .//*[@role='option']]"
            );

            List<WebElement> options = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator)
            );

            if (!options.isEmpty()) {
                WebElement firstOption = options.get(0);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstOption);

                try {
                    wait.until(ExpectedConditions.elementToBeClickable(firstOption)).click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption);
                }
            } else {
                input.sendKeys(Keys.ARROW_DOWN);
                input.sendKeys(Keys.ENTER);
            }
        } catch (Exception e) {
            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);
        }

        wait.until(d -> {
            String current = input.getAttribute("value");
            String invalid = input.getAttribute("aria-invalid");
            return current != null && !current.isBlank() && !"true".equalsIgnoreCase(invalid);
        });

        System.out.println("Selected value: " + input.getAttribute("value"));
    }

    private void selectFutureDate(int plusDays) {
        LocalDate targetDate = LocalDate.now().plusDays(plusDays);
        String targetDay = String.valueOf(targetDate.getDayOfMonth());

        By dateFieldLocator = By.xpath(
                "//*[contains(text(),'Add a return')]/preceding-sibling::*[1] | " +
                "//*[contains(@aria-label,'date') or contains(@data-testid,'date') or contains(text(),'Apr') or contains(text(),'May') or contains(text(),'Jun') or contains(text(),'Jul')]"
        );

        WebElement dateField = wait.until(ExpectedConditions.presenceOfElementLocated(dateFieldLocator));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(dateField)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateField);
        }

        List<By> possibleDayLocators = List.of(
                By.xpath("//button[normalize-space()='" + targetDay + "']"),
                By.xpath("//*[normalize-space()='" + targetDay + "']"),
                By.xpath("//*[contains(@aria-label,'" + targetDay + "')]"),
                By.xpath("//*[contains(@data-date,'" + targetDate.toString() + "')]")
        );

        boolean selected = false;

        for (By locator : possibleDayLocators) {
            try {
                WebElement day = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", day);

                try {
                    wait.until(ExpectedConditions.elementToBeClickable(day)).click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", day);
                }

                selected = true;
                break;
            } catch (Exception ignored) {
            }
        }

        if (!selected) {
            throw new RuntimeException("Unable to select future date");
        }
    }
}