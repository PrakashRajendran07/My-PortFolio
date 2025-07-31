@openAccount
Feature: Open New Account Functionality



 Background:
    Given The user is logged in with valid credentials
    
  @valid
  Scenario: Successfully open a new account and verify updated balance
    Given The user is on the Accounts Overview page
    When The user notes the current balance of the account number "12345"
    And The user navigates to the Open New Account page
    And The user selects "SAVINGS" as account type
    And The user selects account number "12345" as the funding account
    And The user clicks on Open New Account button
    Then A new account should be successfully created
    And A new account number should be displayed
    And The success message "Congratulations, your account is now open." should be visible
    When The user navigates back to the Accounts Overview page
    Then The balance of account number "12345" should be reduced compared to the previous balance

  @navigation
  Scenario: User opens a new account and navigates to its details page
    When The user opens a new account successfully
    And The user clicks on the generated account number link
    Then The system should navigate to the details page of the newly created account
