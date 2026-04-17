Feature: Search Functionality

Scenario: Verify search functionality with different inputs

Given user is on car rental page

#  EMPTY

When user clicks on search button without entering details
Then error message should be displayed

# 2 CHECKBOX VALIDATION

When user enables different drop location
And user clicks on search button
Then error should be displayed for missing fields

# 3️ INVALID INPUT

When user enters invalid pickup and drop location
And user clicks on search button
Then no results or error message should be displayed

# 4️ VALID INPUT

When user enters valid pickup and drop location
And user selects future dates
And user clicks on search button
Then user should navigate to results page
