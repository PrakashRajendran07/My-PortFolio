package org.stepdefinitions;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.pages.AccountsOverview;
import org.pages.LoginPage;
import org.pages.OpenAccountPage;
import org.utility.ConfigReader;

import io.cucumber.java.en.*;

import static org.testng.Assert.*;

public class OpenAccountSteps extends BaseClass {

    OpenAccountPage openAccount = new OpenAccountPage();
    AccountsOverview overviewPage = new AccountsOverview();
    LoginPage loginPage;
    double oldBalance, newBalance;
    String newAccountNumber;

    @Given("The user is logged in with valid credentials")
    public void the_user_is_logged_in_with_valid_credentials() {
    	String url = ConfigReader.getProperty("baseURL");
		 driver = BaseClass.getDriver();
		 driver.get(url);  
         loginPage = new LoginPage();
         loginPage.getUserName().sendKeys(ConfigReader.getProperty("username"));
         loginPage.getPassword().sendKeys(ConfigReader.getProperty("password"));
         loginPage.getLoginCTA().click();
    }
    
    @Given("The user is on the Accounts Overview page")
    public void the_user_is_on_the_accounts_overview_page() {
    	
        click(overviewPage.getAccountsOverviewLink());
    }

    @When("The user notes the current balance of the account number {string}")
    public void the_user_notes_current_balance(String accNum) {
        String balanceText = overviewPage.getAccountBalance(accNum);
        oldBalance = parseCurrency(balanceText);
    }

    @When("The user navigates to the Open New Account page")
    public void the_user_navigates_to_open_new_account_page() {
        click(openAccount.getOpenAccountPage());
    }

    @When("The user selects {string} as account type")
    public void the_user_selects_account_type(String type) {
        selectDropdownOption(openAccount.getAccountTypeDropdown(), "visibletext", type);
    }

    @When("The user selects account number {string} as the funding account")
    public void the_user_selects_funding_account(String accountNumber) {
        selectDropdownOption(openAccount.getAccountTypeDropdown(), "visibletext", accountNumber);
    }

//    @When("The user selects an existing account to fund the new account")
//    public void the_user_selects_existing_account() {
//        selectByIndex(openAccount.getExistingAccountDropdown(), 1); // select second account
//    }

    @When("The user clicks on Open New Account button")
    public void the_user_clicks_open_account_button() {
        click(openAccount.getOpenNewAccountButton());
    }

    @Then("A new account should be successfully created")
    public void new_account_should_be_created() {
        assertTrue(openAccount.getSuccessMessage().isDisplayed(), "Success message is not displayed");
    }

    @Then("A new account number should be displayed")
    public void new_account_number_should_be_displayed() {
        assertTrue(openAccount.getNewAccountNumberLink().isDisplayed(), "New account number link is not displayed");
        newAccountNumber = getText(openAccount.getNewAccountNumberLink());
    }

    @Then("The success message {string} should be visible")
    public void the_success_message_should_be_visible(String expectedMessage) {
        String actualMessage = getText(openAccount.getSuccessMessage()).trim();
        assertEquals(actualMessage, expectedMessage.trim(), "Success message mismatch");
    }

    @When("The user navigates back to the Accounts Overview page")
    public void navigate_back_to_accounts_overview() {
        click(overviewPage.getAccountsOverviewLink());
    }

    @Then("The balance of account number {string} should be reduced compared to the previous balance")
    public void the_balance_should_be_reduced(String accNum) {
        String newBalanceText = overviewPage.getAccountBalance(accNum);
        newBalance = parseCurrency(newBalanceText);
        assertTrue(oldBalance - newBalance == 100.00, "Expected reduction of $100. Actual: $" + (oldBalance - newBalance));
    }

    @When("The user opens a new account successfully")
    public void the_user_opens_a_new_account_successfully() {
        click(openAccount.getOpenAccountPage());
        selectDropdownOption(openAccount.getAccountTypeDropdown(),"text", "SAVINGS");
        selectDropdownOption(openAccount.getExistingAccountDropdown(), "index" , "1");
        click(openAccount.getOpenNewAccountButton());
        assertTrue(openAccount.getNewAccountNumberLink().isDisplayed());
    }

    @When("The user clicks on the generated account number link")
    public void the_user_clicks_account_number_link() {
        click(openAccount.getNewAccountNumberLink());
    }

    @Then("The system should navigate to the details page of the newly created account")
    public void system_should_navigate_to_account_details_page() {
        WebElement title = openAccount.getOpenAccountTitle();
        assertTrue(title.isDisplayed(), "Account details page is not displayed");
    }
}
