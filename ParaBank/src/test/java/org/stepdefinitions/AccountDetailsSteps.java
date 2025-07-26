package org.stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.pages.AccountDetailsPage;
import org.pages.AccountsOverview;
import org.pages.LoginPage;
import org.utility.ConfigReader;

import io.cucumber.java.en.*;

public class AccountDetailsSteps extends BaseClass {

    LoginPage loginPage;
    AccountsOverview accountsOverview;
    AccountDetailsPage accountDetailsPage;

    String selectedAccountNumber;
    String expectedAccountType;

    @Given("The user is logged in and on the Accounts Overview page")
    public void the_user_is_logged_in_and_on_the_accounts_overview_page() {
    	
    	String url = ConfigReader.getProperty("baseURL");
		 driver = BaseClass.getDriver();
		 driver.get(url);  
         loginPage = new LoginPage();
         loginPage.getUserName().sendKeys(ConfigReader.getProperty("username"));
         loginPage.getPassword().sendKeys(ConfigReader.getProperty("password"));
         loginPage.getLoginCTA().click();
         accountsOverview = new AccountsOverview();
         waitForVisibilityOfAllElements(accountsOverview.getAccountLinks(), 10);

    }

    @When("The user clicks on any account number to view details")
    public void the_user_clicks_on_any_account_number_to_view_details() {
    	if (accountsOverview == null) { 
            accountsOverview = new AccountsOverview(); 
        }
        List<WebElement> accountLinks = accountsOverview.getAccountLinks();
        waitForVisibilityOfAllElements(accountLinks, 10);
        if (accountLinks.isEmpty()) {
            throw new RuntimeException("No account links found."); 
        }
        WebElement accLink = accountLinks.get(0);
        selectedAccountNumber = accLink.getText().trim();
        accLink.click();
    }

    @When("The user selects the month {string} which has transactions in the activity period dropdown")
    public void the_user_selects_the_month_in_activity_dropdown(String month) {
        accountDetailsPage = new AccountDetailsPage();
        waitUntilVisible(accountDetailsPage.getActivityPeriodDropdown(), 10);
        selectDropdownOption(accountDetailsPage.getActivityPeriodDropdown(), "visibletext", month);
        accountDetailsPage.getGoButton().click();
    }

    @Then("The system should display transactions for month {string}")
    public void the_system_should_display_transactions_for_month(String month) {
        accountDetailsPage = new AccountDetailsPage();
        waitUntilVisible(accountDetailsPage.getTransactionTable(), 10);
        assertTrue(accountDetailsPage.getTransactionTable().isDisplayed(), "Transactions are not displayed");
    }

    @When("The user selects the month {string} which has no transactions in the activity period dropdown")
    public void the_user_selects_invalid_month_in_activity_dropdown(String month) {
        accountDetailsPage = new AccountDetailsPage();
        waitUntilVisible(accountDetailsPage.getActivityPeriodDropdown(), 10); 
        selectDropdownOption(accountDetailsPage.getActivityPeriodDropdown(), "visibletext", month);
        accountDetailsPage.getGoButton().click();
    }

    @Then("A {string} message should be displayed")
    public void no_transactions_message_should_be_displayed(String expectedMessage) {
    	accountDetailsPage = new AccountDetailsPage();
    	WebElement noTransaction = accountDetailsPage.getNoTransaction();
        waitUntilVisible(noTransaction, 10); 
        assertEquals(noTransaction.getText().trim(), expectedMessage);
    }

    @When("The user selects the transaction type {string} which has transactions in the transaction type dropdown")
    public void the_user_selects_valid_transaction_type(String type) {
        accountDetailsPage = new AccountDetailsPage();
        waitUntilVisible(accountDetailsPage.getTypeDropdown(), 10);
        selectDropdownOption(accountDetailsPage.getTypeDropdown(), "visibletext", type);
        accountDetailsPage.getGoButton().click();
    }

    @Then("The system should display transactions of type {string}")
    public void system_should_display_transactions_of_given_type(String type) {
        accountDetailsPage = new AccountDetailsPage();
        List<WebElement> rows = accountDetailsPage.getTransactionTables();
      //  List<WebElement> rows = driver.findElements(By.xpath("//table[@id='transactionTable']//tr"));
        assertTrue(rows.size() > 1, "No transactions of type " + type + " found");
    }

    @When("The user selects the transaction type {string} which has no transactions in the transaction type dropdown")
    public void the_user_selects_invalid_transaction_type(String type) {
        accountDetailsPage = new AccountDetailsPage();
        waitUntilVisible(accountDetailsPage.getTypeDropdown(), 10);
        selectDropdownOption(accountDetailsPage.getTypeDropdown(), "visibletext", type);
        accountDetailsPage.getGoButton().click();
    }

    @Then("The displayed account type should match the expected type from the external file")
    public void the_displayed_account_type_should_match_expected_type_from_excel() throws IOException {
        accountDetailsPage = new AccountDetailsPage();
        WebElement accountTypeElement = accountDetailsPage.getAccountTypeElement();
        waitUntilVisible(accountTypeElement,10);
        expectedAccountType = readExcelFile("Account_Details", "Sheet1", getRowNumberForAccount(selectedAccountNumber), 1).toUpperCase();
        String actualAccountType = accountTypeElement.getText().trim().toUpperCase();

        assertEquals(actualAccountType, expectedAccountType, "Account type mismatch");
    }
    private int getRowNumberForAccount(String accountNumber) throws IOException {
        for (int i = 1; i < 100; i++) {
            String value = readExcelFile("Account_Details", "Sheet1", i, 0);
            if (value.equals(accountNumber)) {
                return i;
            }
        }
        throw new RuntimeException("Account number not found in Excel: " + accountNumber);
    }
}
