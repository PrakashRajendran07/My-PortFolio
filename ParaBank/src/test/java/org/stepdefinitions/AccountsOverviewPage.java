package org.stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.pages.AccountDetailsPage;
import org.pages.AccountsOverview;
import org.pages.LoginPage;
import org.utility.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountsOverviewPage extends BaseClass{
	LoginPage login = new LoginPage();
    AccountsOverview overview = new AccountsOverview();
    AccountDetailsPage details = new AccountDetailsPage();
    private String selectedAccountNumber;

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        String url = ConfigReader.getProperty("baseURL");
		 driver = BaseClass.getDriver();
		 driver.get(url);  
    }

    @Given("The user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        typeValue(login.getUserName(), username);
        typeValue(login.getPassword(), password);
        click(login.getLoginCTA());
    }

    @When("The user navigates to the Accounts Overview page")
    public void the_user_navigates_to_the_accounts_overview_page() {
    	waitForVisibilityOfAllElements(overview.getAccountLinks(), 5);
    }

    @Then("The user should see a list of accounts with their balances")
    public void the_user_should_see_a_list_of_accounts_with_their_balances() {
        List<WebElement> links = overview.getAccountLinks();
        List<WebElement> balances = overview.getAccountBalances();
        assertTrue(!links.isEmpty() && !balances.isEmpty(), "Accounts not found in overview.");
    }

    @When("The user clicks on an account number")
    public void the_user_clicks_on_an_account_number() throws InterruptedException {
    	waitForVisibilityOfAllElements(overview.getAccountLinks(), 5);
        WebElement first = overview.getAccountLinks().get(0);
        selectedAccountNumber = first.getText().trim();
        first.click();
        Thread.sleep(2000);
    }

    @Then("The user should be navigated to the account details page")
    public void the_user_should_be_navigated_to_the_account_details_page() {
        assertTrue(driver.getCurrentUrl().contains("activity.htm") 
                || driver.getPageSource().contains("Account Details"),
                "Not on Account Details page.");
    }
    @Then("The account details should match the selected account")
    public void the_account_details_should_match_the_selected_account() {
    	
        String actualAccountNumber = details.getAccountNumber();
        assertEquals(actualAccountNumber, selectedAccountNumber,
            "Account number mismatch between overview and details page.");
    }


}
