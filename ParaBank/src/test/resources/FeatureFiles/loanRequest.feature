@loanRequest
Feature: Loan Request Functionality

  Background:
    Given The user launches the ParaBank application with valid credentials
    And The user navigates to the Loan Request page

  @negative
  Scenario: Requesting loan with insufficient funds
    When The user enters loan details with account "14787", loan amount "2000", and down payment "2000"
    And The user submits the loan request
    Then The system should be redirected to the Loan Request Processed page
    And The system should display error message "You do not have sufficient funds for the given down payment."
    And The loan status should be displayed as "Denied" 
  @positive
  Scenario: Requesting loan with sufficient funds
    When The user enters loan details with account "16452", loan amount "100", with sufficient down payment "0"
    And The user submits the loan request
    Then The system should be redirected to the Loan Request Processed page
    And The system should display success message "Congratulations, your loan has been approved."
    And The loan status should be "Approved"
    And A new loan account number should be displayed

  @verifyAccountDetails
  Scenario: Verifying new loan account from account details page
    Given The user has successfully requested a loan with account "16452", loan amount "100", with sufficient down payment "0"
    When The user clicks on the newly generated loan account number
    Then The user should be redirected to the Account Details page
    And The account number should match the newly generated loan account number
    And The account type should be "LOAN"