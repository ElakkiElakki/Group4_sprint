package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import utils.Pages;

public class SearchSteps {

    @Given("user launches taxi booking page")
    public void openTaxiPage() {
        Pages.TP.openTaxiPage();
    }

    @When("user clicks search without entering any data")
    public void clickSearchEmpty() {
        Pages.TP.clickSearch();
    }

    @When("user enters only destination")
    public void onlyDestination() {
        Pages.TP.enterDestination();
    }

    @When("user enters only pickup")
    public void onlyPickup() {
        Pages.TP.enterPickup();
    }

    @Then("system should not proceed to results page")
    public void verifyNoNavigation() {
        Assert.assertTrue(Pages.TP.isStillOnSamePage(),
                "Navigation happened unexpectedly");
    }

    @When("user enters pickup as {string}")
    public void user_enters_pickup_as(String pickup) {
        Pages.TP.enterPickupLocation(pickup);
    }

    @When("user enters destination as {string}")
    public void user_enters_destination_as(String destination) {
        Pages.TP.enterDestinationLocation(destination);
    }

    @When("user selects valid future date and time")
    public void user_selects_valid_future_date_and_time() {
        Pages.TP.selectValidFutureDateAndTime();
    }

    @When("user selects another valid future date and time")
    public void user_selects_another_valid_future_date_and_time() {
        Pages.TP.selectAnotherValidFutureDateAndTime();
    }

    @When("user performs taxi search")
    public void user_performs_taxi_search() {
        Pages.TP.clickSearch();
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertTrue(Pages.TP.areSearchResultsDisplayed(),
                "Search results were not displayed");
    }

    @When("user observes each taxi listing on results page")
    public void user_observes_each_taxi_listing_on_results_page() {
        Pages.TP.observeTaxiListings();
    }

    @Then("car type should be displayed for each result")
    public void car_type_should_be_displayed_for_each_result() {
        Assert.assertTrue(Pages.TP.isCarTypeDisplayedForEachResult(),
                "Car type is not displayed");
    }

    @Then("fare should be displayed for each result")
    public void fare_should_be_displayed_for_each_result() {
        Assert.assertTrue(Pages.TP.isFareDisplayedForEachResult(),
                "Fare is not displayed");
    }
}