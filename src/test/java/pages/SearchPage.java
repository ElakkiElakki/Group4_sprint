package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    By pickup = By.xpath("//input[contains(@placeholder,'pick')]");
    By destination = By.xpath("//input[contains(@placeholder,'destination')]");
    By searchBtn = By.xpath("//button[@type='submit']");
    By resultCards = By.xpath("//div[contains(@data-testid,'result')] | //div[contains(@class,'result')]");

    public void openURL(String url) {
        driver.get(url);
    }

    public void enterPickup(String value) {
        typeSafely(pickup, value);
    }

    public void enterDestination(String value) {
        typeSafely(destination, value);
    }

    public void clickSearch() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        button.click();
    }

    public boolean isStillOnTaxiPage() {
        String url = driver.getCurrentUrl().toLowerCase();
        return url.contains("taxi");
    }

    public void enterPickupAndSelect(String value) {
        typeAndSelectSuggestion(pickup, value);
    }

    public void enterDestinationAndSelect(String value) {
        typeAndSelectSuggestion(destination, value);
    }

    public boolean isResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(resultCards));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCarListVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultCards));
            return driver.findElements(resultCards).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFareDisplayed() {
        try {
            List<WebElement> fares = driver.findElements(
                    By.xpath("//*[contains(text(),'€') or contains(text(),'SGD') or contains(text(),'USD')]")
            );
            return fares.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCarTypeDisplayed() {
        try {
            List<WebElement> carTypes = driver.findElements(
                    By.xpath("//*[contains(text(),'Sedan') or contains(text(),'SUV') or contains(text(),'Van')]")
            );
            return carTypes.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private void typeSafely(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
    }

    private void typeAndSelectSuggestion(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);

        for (char c : value.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        element.sendKeys(Keys.ARROW_DOWN);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        element.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}