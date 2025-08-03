package org.stepdefinitions;

import org.base.BaseClass;
import org.pages.RequestLoanPage;
import org.utility.ConfigReader;
import org.pages.AccountDetailsPage;
import org.pages.LoginPage;

import io.cucumber.java.en.*;
import static org.testng.Assert.*;

import java.io.IOException;

public class RequestLoanSteps extends BaseClass {

    RequestLoanPage loanPage = new RequestLoanPage();
    LoginPage login = new LoginPage();
    AccountDetailsPage accountDetailsPage = new AccountDetailsPage();
    String newAccNum;
    String newlyGeneratedAccount;

    @Given("The user launches the ParaBank application with valid credentials")
    public void the_user_launches_the_parabank_application_with_valid_credentials() {
    	String url = ConfigReader.getProperty("baseURL");
		driver = BaseClass.getDriver();
		driver.get(url);  
		driver.manage().window().maximize();
        login.getUserName().sendKeys(ConfigReader.getProperty("username"));
        login.getPassword().sendKeys(ConfigReader.getProperty("password"));
        login.getLoginCTA().click();
        isElementVisible(loanPage.getRequestLoanLink(), 5);
    }

    @And("The user navigates to the Loan Request page")
    public void the_user_navigates_to_the_loan_request_page() {
    	click(loanPage.getRequestLoanLink());
    	isElementVisible(loanPage.getLoanAmount(), 5);
    }

    @When("The user enters loan details with account {string}, loan amount {string}, and down payment {string}")
    public void the_user_enters_loan_details(String accountNumber, String loanAmount, String downPayment) {
        typeValue(loanPage.getLoanAmount(), loanAmount);
        typeValue(loanPage.getDownPayment(), downPayment);
        selectDropdownOption(loanPage.getFromAccountDropdown(), "value", accountNumber);
    }
    
    @When("The user enters loan details with account {string}, loan amount {string}, with sufficient down payment {string}")
    public void the_user_enters_loan_details_with(String accountNumber, String loanAmount, String downPayment) {
        typeValue(loanPage.getLoanAmount(), loanAmount);
        typeValue(loanPage.getDownPayment(), downPayment);
        selectDropdownOption(loanPage.getFromAccountDropdown(), "value", accountNumber);
    }

    @And("The user submits the loan request")
    public void the_user_submits_the_loan_request() {
        click(loanPage.getApplyNowButton());
    }

    @Then("The system should be redirected to the Loan Request Processed page")
    public void the_system_should_be_redirected_to_the_processed_page() {
        isElementVisible(loanPage.getLoanStatus(), 5);
        assertTrue(driver.getPageSource().contains("Loan Request Processed"));
    }

    @Then("The system should display error message {string}")
    public void the_system_should_display_error_message(String expectedMessage) {
        String actualMessage = loanPage.getErrorMessage().getText();
        assertEquals(actualMessage, expectedMessage);
    }

    @Then("The loan status should be {string}")
    public void the_loan_status_should_be(String expectedStatus) throws IOException {
    	captureScreen1("successLoan", "LoanRequest");
        String actualStatus = loanPage.getLoanStatus().getText();
        assertEquals(actualStatus, expectedStatus);
    }
    
    @Then("The loan status should be displayed as {string}")
    public void the_loan_status_should_be_displayed_as(String expectedStatus) throws IOException {
    	captureScreen1("deniedLoan", "LoanRequest");
        String actualStatus = loanPage.getLoanStatus().getText();
        assertEquals(actualStatus, expectedStatus);
    }

    @Then("The system should display success message {string}")
    public void the_system_should_display_success_message(String expectedMessage) {
        String actualMessage = loanPage.getSuccessMessage().getText();
        assertEquals(actualMessage, expectedMessage);
    }

    @Then("A new loan account number should be displayed")
    public void a_new_loan_account_number_should_be_displayed() {
        newlyGeneratedAccount = loanPage.getNewAccountNumber().getText();
        assertNotNull(newlyGeneratedAccount);
        System.out.println("New Loan Account Created: " + newlyGeneratedAccount);
    }

    @Given("The user has successfully requested a loan with account {string}, loan amount {string}, with sufficient down payment {string}")
    public void the_user_has_successfully_requested_a_loan(String accountNumber, String loanAmount, String downPayment) {
    	 typeValue(loanPage.getLoanAmount(), loanAmount);
         typeValue(loanPage.getDownPayment(), downPayment);
         selectDropdownOption(loanPage.getFromAccountDropdown(), "value", accountNumber);
         click(loanPage.getApplyNowButton());
         isElementVisible(loanPage.getLoanStatus(), 5);
       //  assertNotNull(newlyGeneratedAccount);
    }

    @When("The user clicks on the newly generated loan account number")
    public void the_user_clicks_on_the_newly_generated_loan_account_number() {
    	newAccNum = loanPage.getNewAccountNumberText();
        click(loanPage.getNewAccountNumber());
        
    }

    @Then("The user should be redirected to the Account Details page")
    public void the_user_should_be_redirected_to_the_account_details_page(){
        isElementVisible(accountDetailsPage.getAccountDetailsHeading(), 5);
        assertTrue(accountDetailsPage.getAccountDetailsHeading().getText().contains("Account Details"));
    }

    @Then("The account number should match the newly generated loan account number")
    public void the_account_number_should_match() throws InterruptedException, IOException {
    	Thread.sleep(1000);
        String detailsPageAccount = accountDetailsPage.getAccountNumber();
        assertEquals(detailsPageAccount, newAccNum);
        captureScreen1("loanDetails", "LoanRequest");
    }

    @Then("The account type should be {string}")
    public void the_account_type_should_be(String expectedType) {
        String actualType = accountDetailsPage.getAccountType();
        assertEquals(actualType, expectedType);
    }
}
