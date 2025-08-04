@transferFunds
Feature: Fund Transfer Functionality

  Background: 
    Given The user launches the ParaBank application
    When The user logs in with valid username and password

  @positive
  Scenario: Requesting fund transfer with valid amount and different from/to accounts
    When The user navigates to the Transfer Funds page
    And The user enters amount "10"
    And The user selects from account "16008"
    And The user selects to account "16230"
    And The user clicks on the Transfer button
    Then The Transfer Complete page should display valid amount with from account and to account
    
  @negative1
  Scenario: Requesting fund transfer with same from and to accounts
    When The user navigates to the Transfer Funds page
    And The user enters amount "10"
    And The user selects from account "16008"
    And The user selects to account same as from account "16008"
    And The user clicks on the Transfer button
    Then The system should display an error message indicating "From and To account numbers should not be the same"

  @negative2
  Scenario: Requesting fund transfer with negative amount
    When The user navigates to the Transfer Funds page
    And The user enters negative amount "-10"
    And The user selects from account "16008"
    And The user selects to account "16230"
    And The user clicks on the Transfer button
    Then The system should display an error message for negative amount indicating "The amount cannot be negative"
