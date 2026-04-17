package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // 🔹 Locators
    By closePopup = By.xpath("//button[contains(@aria-label,'Dismiss')]");
    By carRentalTab = By.id("cars");

    By pickupInput = By.xpath("//input[contains(@placeholder,'Airport')]");
    By dropInput = By.xpath("//input[contains(@placeholder,'Drop')]");

    By searchBtn = By.xpath("//button[@type='submit']");
    By differentDropCheckbox = By.xpath("//input[@type='checkbox']");

    // 🔹 Actions

    public void closePopup() {
        try {
            if (driver.findElements(closePopup).size() > 0) {
                click(closePopup);
            }
        } catch (Exception e) {}
    }

    public void clickCarRentalTab() {
        click(carRentalTab);
    }

    public void enableDifferentDropLocation() {
        click(differentDropCheckbox);
    }

    public void enterPickup(String location) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(pickupInput)).clear();
        driver.findElement(pickupInput).sendKeys(location);

        try { Thread.sleep(2000); } catch (Exception e) {}

        driver.findElement(pickupInput).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(pickupInput).sendKeys(Keys.ENTER);
    }

    public void clearPickup() {
        WebElement element = driver.findElement(pickupInput);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public void enterDropLocation(String location) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropInput)).clear();
        driver.findElement(dropInput).sendKeys(location);

        try { Thread.sleep(2000); } catch (Exception e) {}

        driver.findElement(dropInput).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(dropInput).sendKeys(Keys.ENTER);
    }

    // 🔥 SAFE DATE HANDLING
    public void selectDate() {
        System.out.println("Date selection skipped (default date used)");
    }

    // 🔥 SAFE TIME HANDLING
    public void selectTime() {
        System.out.println("Time selection skipped (default time used)");
    }

    public void clickSearch() {
        click(searchBtn);
    }

    public boolean isErrorDisplayed() {
        return driver.getPageSource().toLowerCase().contains("error");
    }

    public boolean isNoResultsDisplayed() {
        return driver.getPageSource().toLowerCase().contains("no cars");
    }
}