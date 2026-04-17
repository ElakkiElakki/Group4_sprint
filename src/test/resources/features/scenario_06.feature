Feature: Booking Taxi Validation

Scenario: Login and validate empty and partial search scenarios

  Given user is on booking home page
  When user clicks sign in button
  And user enters email "elakks14@gmail.com"
  And user clicks continue
  And user enters OTP manually
  Then user should be logged in successfully

  Given user launches taxi booking page
  When user clicks search without entering any data
  Then system should not proceed to results page

  Given user launches taxi booking page
  When user enters only destination
  And user clicks search without entering any data
  Then system should not proceed to results page

  Given user launches taxi booking page
  When user enters only pickup
  And user clicks search without entering any data
  Then system should not proceed to results page