Feature: Booking Taxi Positive Validation

Scenario: Login and validate taxi search with valid inputs and result details

  Given user is on booking home page
  When user clicks sign in button
  And user enters email "elakks14@gmail.com"
  And user clicks continue
  And user enters OTP manually
  Then user should be logged in successfully

  Given user launches taxi booking page
  When user enters pickup as "Changi Airport"
  And user enters destination as "Marina Bay Sands"
  And user selects valid future date and time
  And user performs taxi search
  Then search results should be displayed

  When user observes each taxi listing on results page
  Then car type should be displayed for each result
  And fare should be displayed for each result