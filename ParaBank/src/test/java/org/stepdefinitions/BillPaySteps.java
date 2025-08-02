package org.stepdefinitions;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pages.BillPayPage;
import org.pages.LoginPage;
import org.utility.ConfigReader;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.cucumber.java.en.*;

public class BillPaySteps extends BaseClass {

    BillPayPage billPay = new BillPayPage();
    LoginPage login = new LoginPage();

    @Given("the user is logged in and navigates to the Bill Pay page")
    public void the_user_is_logged_in_and_navigates_to_the_bill_pay_page() throws InterruptedException {
    	String url = ConfigReader.getProperty("baseURL");
    	driver = BaseClass.getDriver();
		driver.get(url);  
		driver.manage().window().maximize();
        login.getUserName().sendKeys(ConfigReader.getProperty("username"));
        login.getPassword().sendKeys(ConfigReader.getProperty("password"));
        login.getLoginCTA().click();
        Thread.sleep(3000);
        isElementVisible(billPay.getBillPayLink(), 5);
        click(billPay.getBillPayLink());
        assertTrue(isElementVisible(billPay.getBillPayHeader(), 5), "Bill Pay page not visible");
    }

    @When("the user enters {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void the_user_enters_all_fields(String payeeName, String address, String city, String state, String zip,
                                           String phone, String account, String verifyAccount, String amount) {
        typeValue(billPay.getPayeeName(), payeeName);
        typeValue(billPay.getPayeeAddress(), address);
        typeValue(billPay.getPayeeCity(), city);
        typeValue(billPay.getPayeeState(), state);
        typeValue(billPay.getPayeeZipCode(), zip);
        typeValue(billPay.getPayeePhone(), phone);
        typeValue(billPay.getAccountNumber(), account);
        typeValue(billPay.getVerifyAccount(), verifyAccount);
        typeValue(billPay.getAmount(), amount);
    }

    @When("clicks the Send Payment button")
    public void clicks_the_send_payment_button() throws InterruptedException {
        click(billPay.getSendPaymentButton());
        isElementVisible(billPay.getSuccessMessage(), 5);
        
    }

    @Then("the user should see a success message with payee {string} and amount {string}")
    public void the_user_should_see_success_message_with_payee_and_amount(String payee, String amount) throws IOException {
    	captureScreen1("BillPaid", "BillPayment");
        String message = getElementText(billPay.getSuccessMessage());
        assertTrue(message.contains(payee), "Success message doesn't contain payee name");
        assertTrue(message.contains(amount), "Success message doesn't contain amount");
    }

    @When("the user fills all the available fields except {string}")
    public void the_user_fills_all_the_available_fields_except(String fieldToBlank) {
        
        typeValue(billPay.getPayeeName(), "Prakash");
        typeValue(billPay.getPayeeAddress(), "123 Main St");
        typeValue(billPay.getPayeeCity(), "Chennai");
        typeValue(billPay.getPayeeState(), "TN");
        typeValue(billPay.getPayeeZipCode(), "600001");
        typeValue(billPay.getPayeePhone(), "9876543210");
        typeValue(billPay.getAccountNumber(), "12345");
        typeValue(billPay.getVerifyAccount(), "12345");
        typeValue(billPay.getAmount(), "100");

        switch (fieldToBlank.toLowerCase()) {
            case "payee name":
                billPay.getPayeeName().clear();
                break;
            case "address":
                billPay.getPayeeAddress().clear();
                break;
            case "city":
                billPay.getPayeeCity().clear();
                break;
            case "state":
                billPay.getPayeeState().clear();
                break;
            case "zip code":
                billPay.getPayeeZipCode().clear();
                break;
            case "phone number":
                billPay.getPayeePhone().clear();
                break;
            case "account":
                billPay.getAccountNumber().clear();
                break;
            case "verify account":
                billPay.getVerifyAccount().clear();
                break;
            case "amount":
                billPay.getAmount().clear();
                break;
            default:
                System.out.println("Invalid field name: " + fieldToBlank);
        }
    }

    @Then("the user views the error message {string}")
    public void the_user_views_the_error_message(String expectedErrorMessage) throws IOException {
    	try {
    		List<WebElement> errorElements = new WebDriverWait(driver, Duration.ofSeconds(10))
    	            .until(driver -> { List<WebElement> elements = billPay.getErrorMessages();
    	                return elements.stream().anyMatch(WebElement::isDisplayed) ? elements : null;
    	            });
    		
    		

            List<String> actualErrors = errorElements.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
String fieldNameForScreenshot = getFieldNameFromError(expectedErrorMessage);
            
            captureScreen1("RegistrationError_" + fieldNameForScreenshot, "BillPayment");
            assertTrue(
                actualErrors.stream()
                    .anyMatch(err -> err.equalsIgnoreCase(expectedErrorMessage)),
                "Expected error message not found: " + expectedErrorMessage
            );
           // System.out.println("Captured Errors: " + actualErrors);
            if (actualErrors.isEmpty()) {
                throw new AssertionError("No visible error messages found on the page.");
            }
            

        } catch (Exception e) {
        	captureScreen1("ErrorValidationFailure", "BillPayment");
            throw new AssertionError("Failed during error message validation: " + e.getMessage(), e);
        }
    }

    @When("the user provides account number {string} and verify account number {string}")
    public void the_user_provides_mismatched_accounts(String account, String verifyAccount) {
        typeValue(billPay.getAccountNumber(), account);
        typeValue(billPay.getVerifyAccount(), verifyAccount);
    }
    
    private String getFieldNameFromError(String expectedErrorMessage) { 
        if (expectedErrorMessage.toLowerCase().contains("Payee name")) return "PayeeName";
        if (expectedErrorMessage.toLowerCase().contains("Address")) return "Address";
        if (expectedErrorMessage.toLowerCase().contains("City")) return "City";
        if (expectedErrorMessage.toLowerCase().contains("State")) return "State";
        if (expectedErrorMessage.toLowerCase().contains("Zip Code")) return "ZipCode";
        if (expectedErrorMessage.toLowerCase().contains("Phone Number")) return "Phone";
        if (expectedErrorMessage.toLowerCase().contains("Account Number")) return "AccountNumber";
        if (expectedErrorMessage.toLowerCase().contains("Amount")) return "Amount";
        return "UnknownField";
    }
}
