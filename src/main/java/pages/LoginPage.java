package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // 🔹 Locators
    By signInBtn = By.xpath("//a[contains(@aria-label,'Sign in')]");
    By emailField = By.id("username");
    By continueBtn = By.xpath("//button//span[contains(text(),'Continue')]");
    By verifyBtn = By.xpath("//button[@type='submit']");

    // 🔹 Actions
    public void clickSignIn() {
        click(signInBtn);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void clickContinue() {
        click(continueBtn);
    }

    public void enterOTPManually() {
        try {
            System.out.println("👉 Enter OTP manually...");
            Thread.sleep(25000);
        } catch (Exception e) {}
    }

    public void clickVerifyButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();
        } catch (Exception e) {
            System.out.println("Verify button not present / auto login");
        }
    }

    // 🔥 IMPORTANT: REUSABLE LOGIN METHOD (FIXES YOUR ERROR)
    public void login(String email) {

        clickSignIn();
        enterEmail(email);
        clickContinue();
        enterOTPManually();
        clickVerifyButton();
    }
}