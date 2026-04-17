package stepdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.*;
import utils.Pages;

public class LoginSteps {

    @Given("user is on booking home page")
    public void openHomePage() {
        Pages.LP.openHomePage();
    }

    @When("user clicks sign in button")
    public void clickSignIn() {
        Pages.LP.clickSignIn();
    }

    @When("user enters email {string}")
    public void enterEmail(String email) {
        Pages.LP.enterEmail(email);
    }

    @When("user clicks continue")
    public void clickContinue() {
        Pages.LP.clickContinue();
    }

    @When("user enters OTP manually")
    public void enterOTP() throws InterruptedException {
        Pages.LP.waitForOTP();
    }

    @Then("user should be logged in successfully")
    public void verifyLogin() {
        Assert.assertTrue(Pages.LP.isLoginSuccessful(),
                "Login failed");
    }
}