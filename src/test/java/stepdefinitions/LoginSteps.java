package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;

import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

import java.util.Properties;

public class LoginSteps {

    WebDriver driver;
    LoginPage login;
    HomePage home;
    Properties prop = ConfigReader.initProperties();

    @Given("user opens the browser")
    public void user_opens_the_browser() {

        driver = DriverFactory.getDriver();

        login = new LoginPage(driver);
        home = new HomePage(driver);
    }

    @When("user clicks on sign in button")
    public void click_sign_in() {
        login.clickSignIn();
    }

    @When("user enters valid email")
    public void enter_email() {
        login.enterEmail(prop.getProperty("email"));
    }

    @When("user clicks continue button")
    public void click_continue() {
        login.clickContinue();
    }

    @When("user enters OTP manually")
    public void enter_otp() {
        login.enterOTPManually();
    }

    @When("user clicks verify button")
    public void click_verify() {
        login.clickVerifyButton();
    }

    @Then("user should be logged in successfully")
    public void verify_login_success() {

        driver.navigate().to(prop.getProperty("url"));

        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("booking"), "Login failed");
    }
}