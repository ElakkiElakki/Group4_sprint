package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;

import pages.*;
import utils.*;

import java.util.Properties;

public class SearchSteps {

    WebDriver driver;
    HomePage home;
    LoginPage login;
    ResultsPage results;
    Properties prop = ConfigReader.initProperties();

    @Given("user is on car rental page")
    public void user_on_car_rental_page() {

        driver = DriverFactory.getDriver();

        home = new HomePage(driver);
        login = new LoginPage(driver);
        results = new ResultsPage(driver);

        login.login(prop.getProperty("email"));

        try { Thread.sleep(5000); } catch (Exception e) {}

        driver.navigate().to(prop.getProperty("url"));

        home.closePopup();
        home.clickCarRentalTab();
    }

    // 🔴 1. EMPTY
    @When("user clicks on search button without entering details")
    public void empty_search() {
        home.clickSearch();
    }

    @Then("error message should be displayed")
    public void verify_error() {
        Assert.assertTrue(home.isErrorDisplayed());
    }

    // 🔴 2. CHECKBOX
    @When("user enables different drop location")
    public void enable_drop() {
        home.enableDifferentDropLocation();
    }

    @Then("error should be displayed for missing fields")
    public void missing_fields_error() {
        Assert.assertTrue(home.isErrorDisplayed());
    }

    // 🔴 3. INVALID
    @When("user enters invalid pickup and drop location")
    public void invalid_locations() {

        home.clearPickup();
        home.enterPickup("@@@123");

        home.enableDifferentDropLocation();
        home.enterDropLocation("###$$$");

        home.selectDate();
        home.selectTime();
    }

    @Then("no results or error message should be displayed")
    public void invalid_result() {
        Assert.assertTrue(home.isNoResultsDisplayed() || home.isErrorDisplayed());
    }

    // 🔴 4. VALID
    @When("user enters valid pickup and drop location")
    public void valid_locations() {

        home.clearPickup();

        home.enterPickup("Dubai");

        home.enableDifferentDropLocation();
        home.enterDropLocation("Abu Dhabi");
    }

    @When("user selects future dates")
    public void select_dates() {
        home.selectDate();
        home.selectTime();
    }

    @When("user clicks on search button")
    public void click_search() {
        home.clickSearch();
    }

    @Then("user should navigate to results page")
    public void verify_results() {
        Assert.assertTrue(results.isResultsDisplayed());
    }
}