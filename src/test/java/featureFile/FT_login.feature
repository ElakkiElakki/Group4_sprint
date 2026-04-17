@login
Feature: Booking Register

  Scenario: User registers using email OTP
    Given user opens booking homepage for registration
    When user dismisses popup if visible
    And user selects register option
    And user provides email and proceeds
    Then user completes CAPTCHA and OTP verification