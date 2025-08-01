@openAccount
Feature: Open New Account Functionality

 Background:
    Given The user is logs in with valid username and password
  
  @positive
  Scenario: Verify opening a new savings account
    When The user navigates to the Open New Account page
    And The user selects "SAVINGS" as account type
    And The user clicks on Open New Account button
    Then A new account should be successfully created
    And A new account number should be displayed
    And The success message "Congratulations, your account is now open." should be visible

  @positive
  Scenario: Verify opening a new checking account
    When The user navigates to the Open New Account page
    And The user selects account type as "CHECKING"
    And The user clicks on Open New Account button
    Then A new account should be successfully created
    And A new account number should be displayed
    And The success message "Congratulations, your account is now open." should be visible

  @validation
  Scenario: Verify that the balance is reduced from the source account after successful creation of new account
    Given The user is on the Accounts Overview page
    When The user notes the current balance of the account number "13455"
    And The user navigates to the Open New Account page
    And The user selects account number "13455" as the funding account
    And The user clicks on Open New Account button
    And The user navigates back to the Accounts Overview page
    Then The balance of account number "13455" should be reduced compared to the previous balance

  @validation
  Scenario: Verify clicking on the new account number navigates to the account details page
    When The user opens a new account successfully
    And The user clicks on the generated account number link
    Then The system should navigate to the details page of the newly created account
