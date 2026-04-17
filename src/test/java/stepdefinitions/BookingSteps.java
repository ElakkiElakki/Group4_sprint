package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import utils.Pages;

public class BookingSteps {

    @When("user selects a taxi from results")
    public void user_selects_a_taxi_from_results() {
        Pages.TP.selectTaxiFromResults();
    }

    @When("user clicks book now")
    public void user_clicks_book_now() {
        Pages.TP.clickBookNow();
    }

    @When("user enters first name as {string}")
    public void user_enters_first_name_as(String firstName) {
        Pages.TP.enterFirstName(firstName);
    }

    @When("user enters last name as {string}")
    public void user_enters_last_name_as(String lastName) {
        Pages.TP.enterLastName(lastName);
    }

    @When("user enters booking email as {string}")
    public void user_enters_booking_email_as(String email) {
        Pages.TP.enterBookingEmail(email);
    }

    @When("user enters phone number as {string}")
    public void user_enters_phone_number_as(String phone) {
        Pages.TP.enterPhoneNumber(phone);
    }

    @When("user confirms the booking")
    public void user_confirms_the_booking() {
        Pages.TP.confirmBooking();
    }

    @Then("booking should be completed successfully")
    public void booking_should_be_completed_successfully() {
        Assert.assertTrue(Pages.TP.isBookingSuccessful(),
                "Booking was not completed successfully");
    }

    @Then("confirmation page should be displayed")
    public void confirmation_page_should_be_displayed() {
        Assert.assertTrue(Pages.TP.isConfirmationPageDisplayed(),
                "Confirmation page is not displayed");
    }
}