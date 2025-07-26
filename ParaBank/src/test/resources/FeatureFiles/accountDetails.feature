@accountDetails
Feature: Account Details Transaction Filters

  Background:
    Given The user is logged in and on the Accounts Overview page
    When The user clicks on any account number to view details

  @positive
  Scenario: Filter transactions by activity period with valid month
    When The user selects the month "July" which has transactions in the activity period dropdown
    Then The system should display transactions for month "July"

  @negative
  Scenario: Filter transactions by activity period with no transactions
    When The user selects the month "January" which has no transactions in the activity period dropdown
    Then A "No transactions found." message should be displayed

  @positive
  Scenario: Filter transactions by transaction type - Credit
    When The user selects the transaction type "Credit" which has transactions in the transaction type dropdown
    Then The system should display transactions of type "Credit"

  @negative
  Scenario: Filter transactions by transaction type - Debit
    When The user selects the transaction type "Debit" which has no transactions in the transaction type dropdown
    Then A "No transactions found." message should be displayed

  @positive
  Scenario: Validate account type using external file
    Then The displayed account type should match the expected type from the external file
