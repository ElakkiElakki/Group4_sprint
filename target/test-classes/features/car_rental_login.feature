Feature: Login Functionality

Scenario: Verify user can login with valid email and OTP

Given user opens the browser
When user clicks on sign in button
And user enters valid email
And user clicks continue button
And user enters OTP manually
And user clicks verify button
Then user should be logged in successfully
