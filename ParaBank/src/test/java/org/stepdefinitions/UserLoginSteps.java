package org.stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.base.BaseClass;
import org.pages.AccountsOverview;
import org.pages.LoginPage;
import org.utility.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLoginSteps extends BaseClass {
    LoginPage loginPage;

    @Given("the user navigates to the customer login page")
    public void the_user_navigates_to_the_customer_login_page()  {
        String url = ConfigReader.getProperty("baseURL");
        driver = BaseClass.getDriver();
        driver.get(url);
        driver.manage().window().maximize();
        loginPage = new LoginPage();
    }

    @When("the user provides valid credentials {string} and {string}")
    public void the_user_provides_valid_credentials_and(String username, String password) {
        typeValue(loginPage.getUserName(), username);
        typeValue(loginPage.getPassword(), password);
    }

    @When("the user provides invalid details {string} and {string}")
    public void the_user_provides_invalid_details(String username, String password) {
        typeValue(loginPage.getUserName(), username);
        typeValue(loginPage.getPassword(), password);
    }

    @When("the user provides credentials {string} and {string}")
    public void the_user_provides_credentials_and(String username, String password) {
        typeValue(loginPage.getUserName(), username);
        typeValue(loginPage.getPassword(), password);
    }

    @When("clicks the Login button")
    public void clicks_the_login_button() {
        click(loginPage.getLoginCTA());
    }

    @Then("the user should be redirected to the Accounts Overview page")
    public void the_user_should_be_redirected_to_the_accounts_overview_page() throws IOException {
        AccountsOverview overview = new AccountsOverview();
        waitUntilVisible(overview.getAccountsOverviewHeading(), 5);
        boolean atOverviewPage = overview.getAccountsOverviewHeading().isDisplayed();
        captureScreen1("LoginSuccessful", "LoginPage");
        assertTrue(atOverviewPage, "User is not on Accounts Overview page");
        
    }

    @Then("the user should be redirected to the error page displaying the message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) throws IOException {
    	waitUntilVisible(loginPage.getErrorMessage(), 5);
        String actualErrorMessage = loginPage.getErrorMessage().getText().trim();
        assertEquals(actualErrorMessage, expectedErrorMessage,
                "Error message mismatch.");
        String fileName = getFileNameForError(expectedErrorMessage);
        captureScreen1(fileName, "LoginPage");
    }
    
    private String getFileNameForError(String errorMessage) {
        if (errorMessage.contains("could not be verified")) {
            return "InvalidCredentialsError.png";
        } else if (errorMessage.contains("Please enter a username and password")) {
            return "EmptyCredentialsError.png";
        } else if (errorMessage.contains("Please enter a username")) {
            return "EmptyUsernameError.png";
        } else if (errorMessage.contains("Please enter a password")) {
            return "EmptyPasswordError.png";
        } else {
            return "UnknownError.png";
        }
    }
 
}
