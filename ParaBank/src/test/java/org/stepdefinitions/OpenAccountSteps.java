package org.stepdefinitions;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.pages.AccountDetailsPage;
import org.pages.AccountsOverview;
import org.pages.LoginPage;
import org.pages.OpenAccountPage;
import org.utility.ConfigReader;

import io.cucumber.java.en.*;

import static org.testng.Assert.*;

import java.io.IOException;

public class OpenAccountSteps extends BaseClass {
	    OpenAccountPage openAccount = new OpenAccountPage();
	    AccountsOverview accountsOverview = new AccountsOverview();
	    LoginPage login = new LoginPage();
	    AccountDetailsPage accDetails = new AccountDetailsPage();

	    double oldBalance;
	    double newBalance;
	    String accountNumber;
	    String newAccountNum;
	    
	    @Given("The user is logs in with valid username and password")
	    public void the_user_is_logs_in_with_valid_username_and_password()  {
	    		String url = ConfigReader.getProperty("baseURL");
	    		 driver = BaseClass.getDriver();
	    		 driver.get(url);  
	    		 driver.manage().window().maximize();
	             login.getUserName().sendKeys(ConfigReader.getProperty("username"));
	             login.getPassword().sendKeys(ConfigReader.getProperty("password"));
	             login.getLoginCTA().click();
	    		 isElementVisible(openAccount.getOpenAccountPage(), 5);
	    	}
	    @When("The user navigates to the Open New Account page")
	    public void the_user_navigates_to_the_open_new_account_page() throws InterruptedException {
	        click(openAccount.getOpenAccountPage());
	       Thread.sleep(3000);
	        isElementVisible(openAccount.getAccountTypeDropdown(), 5);
	    }
	    @When("The user selects {string} as account type")
	    public void the_user_selects_as_account_type(String accountType) {
	        selectDropdownOption(openAccount.getAccountTypeDropdown(), "visibletext", accountType);
	    
	    }
	    @When("The user clicks on Open New Account button")
	    public void the_user_clicks_on_open_new_account_button() throws InterruptedException, IOException {
	    	Thread.sleep(1000);
	        click(openAccount.getOpenNewAccountButton());
	        isElementVisible(openAccount.getSuccessMessage(), 5);
	        captureScreen1("new_acc", "AccountCreation");
	    }

	    @Then("A new account should be successfully created")
	    public void a_new_account_should_be_successfully_created() {
	        assertTrue(openAccount.getSuccessMessage().isDisplayed(), "Account creation success message not displayed");
	        isElementVisible(accountsOverview.getAccountsOverviewHeading(), 5);
	    }

	    @Then("A new account number should be displayed")
	    public void a_new_account_number_should_be_displayed() {
	        assertTrue(openAccount.getNewAccountNumberLink().isDisplayed(), "New account number link not visible");
	    }

	    @Then("The success message {string} should be visible")
	    public void the_success_message_should_be_visible(String expectedMessage) {
	    	
	        String actualMessage = getText(openAccount.getSuccessMessage());
	        assertEquals(actualMessage.trim(), expectedMessage.trim(), "Success message mismatch");
	    }
	    
	    @When("The user selects account type as {string}")
	    public void the_user_selects_account_type_as(String accountType) {
	    	selectDropdownOption(openAccount.getAccountTypeDropdown(), "visibletext", accountType);
	    }
	    
	    @Given("The user is on the Accounts Overview page")
	    public void the_user_is_on_the_accounts_overview_page() {
	       accountsOverview.getAccountsOverviewLink();
	       waitForVisibilityOfAllElements(accountsOverview.getAccountBalances(), 5);
	    }
	    @When("The user notes the current balance of the account number {string}")
	    public void the_user_notes_the_current_balance_of_the_account_number(String accNum) {
	    	accountNumber = accNum;
	        String oldBalanceText = accountsOverview.getAccountBalance(accNum);
	        oldBalance = parseCurrency(oldBalanceText);
	       
	    }
	    @When("The user selects account number {string} as the funding account")
	    public void the_user_selects_account_number_as_the_funding_account(String accountNumber) {
	    	selectDropdownOption(openAccount.getExistingAccountDropdown(), "value", accountNumber);
	    }
	    @When("The user navigates back to the Accounts Overview page")
	    public void the_user_navigates_back_to_the_accounts_overview_page() {
	    	click(accountsOverview.getAccountsOverviewLink());
	    	waitForVisibilityOfAllElements(accountsOverview.getAccountBalances(), 5);
	    }
	    @Then("The balance of account number {string} should be reduced compared to the previous balance")
	    public void the_balance_of_account_number_should_be_reduced_compared_to_the_previous_balance(String accNum) {
	    	String newBalanceText = accountsOverview.getAccountBalance(accNum); 
	        newBalance = parseCurrency(newBalanceText);  
	        double expectedNewBalance = oldBalance - 100.00;
	        expectedNewBalance = Math.round(expectedNewBalance * 100.0) / 100.0;
	        newBalance = Math.round(newBalance * 100.0) / 100.0;
	        assertEquals(newBalance, expectedNewBalance, 
	            "Balance mismatch: Expected $" + expectedNewBalance + " but found $" + newBalance);
	    }
	    @When("The user opens a new account successfully")
	    public void the_user_opens_a_new_account_successfully() throws InterruptedException {
	    	click(openAccount.getOpenAccountPage());
	        isElementVisible(openAccount.getAccountTypeDropdown(), 5);
	        selectDropdownOption(openAccount.getAccountTypeDropdown(), "text", "SAVINGS");
	        Thread.sleep(1000);
	        click(openAccount.getOpenNewAccountButton());
	        isElementVisible(openAccount.getSuccessMessage(), 5);
	        newAccountNum = openAccount.getNewAccountNumberLink().getText();
	        assertTrue(openAccount.getSuccessMessage().isDisplayed());
	    }

	    @When("The user clicks on the generated account number link")
	    public void the_user_clicks_on_the_generated_account_number_link() {
	        click(openAccount.getNewAccountNumberLink());
	    }

	    @Then("The system should navigate to the details page of the newly created account")
	    public void the_system_should_navigate_to_the_details_page_of_the_newly_created_account() {
	        String currentUrl = driver.getCurrentUrl();
	        assertTrue(currentUrl.contains(newAccountNum), "User not navigated to account details page.");
	    }


    
}
