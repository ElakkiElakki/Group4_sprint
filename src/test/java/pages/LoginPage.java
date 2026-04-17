package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@data-testid='header-sign-in-button']")
    WebElement signInBtn;

    @FindBy(id = "username")
    WebElement emailField;

    @FindBy(xpath = "//button//span[contains(text(),'Continue')]")
    WebElement continueBtn;

    @FindBy(xpath = "//input[@autocomplete='one-time-code' or @type='tel']")
    WebElement otpField;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();
    }

    public void handleCookiePopup() {
        try {
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Accept']")));
            acceptBtn.click();
            System.out.println("Cookie popup handled");
        } catch (Exception e) {
            System.out.println("No cookie popup");
        }
    }

    public void clickSignIn() {
        handleCookiePopup();

        WebElement signIn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@data-testid='header-sign-in-button']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signIn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(signIn)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signIn);
        }
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void waitForOTP() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(otpField));
        System.out.println("Enter OTP manually...");
        Thread.sleep(60000);
    }

    public boolean isLoginSuccessful() {
        try {
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));

            longWait.until(d -> {
                boolean avatar = d.findElements(By.xpath("//a[@data-testid='header-avatar']")).size() > 0;
                boolean account = d.findElements(By.xpath("//button[contains(@aria-label,'Account')]")).size() > 0;
                boolean signIn = d.findElements(By.xpath("//a[@data-testid='header-sign-in-button']")).size() > 0;

                return avatar || account || !signIn;
            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}