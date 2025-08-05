package org.stepdefinitions;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.pages.FundTransferPage;
import org.pages.HomePage;
import org.pages.LoginPage;
import org.utility.ConfigReader;

import io.cucumber.java.en.*;
import static org.testng.Assert.*;

import java.io.IOException;

public class FundTransferSteps extends BaseClass {

    FundTransferPage transferPage = new FundTransferPage();
    LoginPage login = new LoginPage();
    HomePage home = new HomePage();
    String selectedFromAccount;
    String selectedToAccount;
    String enteredAmount;

    @Given("The user launches the ParaBank application")
    public void the_user_launches_the_para_bank_application() {
    	 String url = ConfigReader.getProperty("baseURL");
		 driver = BaseClass.getDriver();
		 driver.get(url);  
		 driver.manage().window().maximize();
    }

    @When("The user logs in with valid username and password")
    public void the_user_logs_in_with_valid_username_and_password() {
    	login.getUserName().sendKeys(ConfigReader.getProperty("username"));
        login.getPassword().sendKeys(ConfigReader.getProperty("password"));
        login.getLoginCTA().click();
    }

    @When("The user navigates to the Transfer Funds page")
    public void the_user_navigates_to_the_transfer_funds_page() throws InterruptedException {
    	Thread.sleep(2000);
    	click(home.getTransferFunds());
    }

    @When("The user enters amount {string}")
    public void the_user_enters_amount(String amount) throws InterruptedException {
        isElementVisible(transferPage.getAmountInput(), 5);
        typeValue(transferPage.getAmountInput(), amount);
        enteredAmount = transferPage.getAmountInput().getAttribute("value");      
    }
    
    @When("The user enters negative amount {string}")
    public void the_user_enters_negative_amount(String amount) throws InterruptedException {
        isElementVisible(transferPage.getAmountInput(), 5);   
    }

    @When("The user selects from account {string}")
    public void the_user_selects_from_account(String fromAccount) {
        selectDropdownOption(transferPage.getFromAccountDropdown(), "value", fromAccount);
        WebElement dropdown = transferPage.getFromAccountDropdown();	
        selectedFromAccount = getSelectedOption(dropdown);
        
    }

    @When("The user selects to account {string}")
    public void the_user_selects_to_account(String toAccount) {
        selectDropdownOption(transferPage.getToAccountDropdown(), "value", toAccount);
        WebElement dropdown = transferPage.getToAccountDropdown();	
        selectedToAccount = getSelectedOption(dropdown);
    }
    
    @When("The user selects to account same as from account {string}")
    public void the_user_selects_to_account_same_as_from_account(String toAccount) {
        selectDropdownOption(transferPage.getToAccountDropdown(), "value", toAccount);
    }

    @When("The user clicks on the Transfer button")
    public void the_user_clicks_on_the_transfer_button() {
        click(transferPage.getTransferButton());
        isElementVisible(transferPage.getAmountResult(), 5);

    }

    @Then("The Transfer Complete page should display valid amount with from account and to account")
    public void the_transfer_complete_page_should_display_valid_amount_with_from_account_and_to_account() throws IOException {
    	captureScreen1("Successful_Transfer", "FundTransfer");
       // isElementVisible(transferPage.getAmountResult(), 5);
        String amountRequested = transferPage.getAmountResult().getText().replace("$", "").trim();
        double actualAmount = Double.parseDouble(amountRequested);
        double expectedAmount = Double.parseDouble(enteredAmount);
        assertEquals(actualAmount, expectedAmount, "Entered amount mismatch");
        String fromAcc = transferPage.getFromAccountResult().getText();
        String toAcc = transferPage.getToAccountResult().getText();
        assertEquals(fromAcc, selectedFromAccount);
        assertEquals(toAcc, selectedToAccount);
    }

    @Then("The system should display an error message indicating {string}")
    public void the_system_should_display_an_error_message_indicating(String expectedError) throws IOException {
    	captureScreen1("SameAcc_ErrorMessage", "FundTransfer");
        String actualError = getElementText(transferPage.getSameAccError());
        assertTrue(actualError.toLowerCase().contains(expectedError.toLowerCase()), "Error message mismatch");
    }
    
    @Then("The system should display an error message for negative amount indicating {string}")
    public void the_system_should_display_an_error_message__for_negative_amount_indicatfing(String expectedError) throws IOException {
    	captureScreen1("NegativeAmount_ErrorMessage", "FundTransfer");
        String actualError = getElementText(transferPage.getNegativeAmountError());
        assertTrue(actualError.toLowerCase().contains(expectedError.toLowerCase()), "Error message mismatch");
    }
    
    
}
