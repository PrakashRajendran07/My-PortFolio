Feature: Accounts Overview

  Background:
    Given The user is on the login page
    And The user logs in with username "John" and password "12345"

  Scenario: View all accounts in overview page
    When The user navigates to the Accounts Overview page
    Then The user should see a list of accounts with their balances

  Scenario: Click on account link to view account details
    When The user clicks on an account number
    Then The user should be navigated to the account details page
    And The account details should match the selected account
