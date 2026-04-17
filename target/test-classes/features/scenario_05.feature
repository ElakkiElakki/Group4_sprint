Feature: Booking Taxi Booking Flow

Scenario: Verify successful taxi booking flow

  Given user is on booking home page
  When user clicks sign in button
  And user enters email "elakks14@gmail.com"
  And user clicks continue
  And user enters OTP manually
  Then user should be logged in successfully

  Given user launches taxi booking page
  When user enters pickup as "Orchard Road"
  And user enters destination as "Sentosa"
  And user selects valid future date and time
  And user performs taxi search
  And user selects a taxi from results
  And user clicks book now
  And user enters first name as "John"
  And user enters last name as "Doe"
  And user enters booking email as "john.doe@test.com"
  And user enters phone number as "9876543210"
  And user confirms the booking
  Then booking should be completed successfully
  And confirmation page should be displayed