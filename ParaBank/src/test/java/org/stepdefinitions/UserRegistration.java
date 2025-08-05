package org.stepdefinitions;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.pages.RegistrationPage;
import org.utility.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserRegistration extends BaseClass {

    RegistrationPage registrationPage;
    
    @Given("the user navigates to the registration page")
    public void the_user_navigates_to_the_registration_page() {
    	 String url = ConfigReader.getProperty("registrationURL");
		 driver = BaseClass.getDriver();
		 driver.get(url);  
		 
		 registrationPage = new RegistrationPage();
    }

    @When("the user provides valid details {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void the_user_provides_valid_details(String firstName, String lastName, String address, String city, String state,
                                                String zipCode, String ssn, String username, String password, String confirmPassword) {
        typeValue(registrationPage.getFirstName(), firstName);
        typeValue(registrationPage.getLastName(), lastName);
        typeValue(registrationPage.getAddress(), address);
        typeValue(registrationPage.getCity(), city);
        typeValue(registrationPage.getState(), state);
        typeValue(registrationPage.getZipCode(), zipCode);
        typeValue(registrationPage.getSsnCode(), ssn);
        typeValue(registrationPage.getUserName(), username);
        typeValue(registrationPage.getPassword(), password);
        typeValue(registrationPage.getConfirmPassword(), confirmPassword);
    }
    @When("the user fills all fields except {string}")
    public void the_user_fills_all_fields_except(String fieldToLeaveBlank) {
       
        typeValue(registrationPage.getFirstName(), "John");
        typeValue(registrationPage.getLastName(), "Doe");
        typeValue(registrationPage.getAddress(), "123 Main St");
        typeValue(registrationPage.getCity(), "Chennai");
        typeValue(registrationPage.getState(), "TN");
        typeValue(registrationPage.getZipCode(), "600001");
        typeValue(registrationPage.getSsnCode(), "123-45-6789");
        typeValue(registrationPage.getUserName(), "johndoe123");
        typeValue(registrationPage.getPassword(), "password123");
        typeValue(registrationPage.getConfirmPassword(), "password123");

        switch (fieldToLeaveBlank.toLowerCase()) {
            case "first name":
                registrationPage.getFirstName().clear();
                break;
            case "last name":
                registrationPage.getLastName().clear();
                break;
            case "address":
                registrationPage.getAddress().clear();
                break;
            case "city":
                registrationPage.getCity().clear();
                break;
            case "state":
                registrationPage.getState().clear();
                break;
            case "zip code":
                registrationPage.getZipCode().clear();
                break;
            case "ssn":
                registrationPage.getSsnCode().clear();
                break;
            case "username":
                registrationPage.getUserName().clear();
                break;
            case "password":
                registrationPage.getPassword().clear();
                break;
            case "password confirmation":
                registrationPage.getConfirmPassword().clear();
                break;
            default:
                System.out.println("Invalid field name: " + fieldToLeaveBlank);
        }
    }
    
    @When("clicks the submit button")
    public void clicks_the_submit_button() {
        click(registrationPage.getRegisterCTA());
    }
    @Then("the user sees confirmation message {string}")
    public void the_user_sees_confirmation_message(String expectedMessage) throws IOException {
        if (isElementVisible(registrationPage.getSuccessMessage(), 5)) {
            String actualMessage = registrationPage.getSuccessMessage().getText().trim();
            System.out.println("Confirmation Message: " + actualMessage);
            captureScreen("RegistrationSuccess");
            assertEquals(actualMessage, expectedMessage);
        } else {
            throw new AssertionError("Success message not found within timeout.");
        }
    }

    @Then("the user sees error message {string}")
    public void the_user_sees_error_message(String expectedErrorMessage) throws IOException {
    	try {
            List<WebElement> errorElements = registrationPage.getErrorMessages();
            waitForVisibilityOfAllElements(errorElements, 10);
            List<String> actualErrors = errorElements.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
            System.out.println("Captured Errors: " + actualErrors);
            if (actualErrors.isEmpty()) {
                throw new AssertionError("No visible error messages found on the page.");
            }
            String fieldNameForScreenshot = getFieldNameFromError(expectedErrorMessage);
            captureScreen("RegistrationError_" + fieldNameForScreenshot);
            assertTrue(
                actualErrors.stream()
                    .anyMatch(err -> err.equalsIgnoreCase(expectedErrorMessage)),
                "Expected error message not found: " + expectedErrorMessage
            );

        } catch (Exception e) {
            captureScreen("ErrorValidationFailure");
            throw new AssertionError("Failed during error message validation: " + e.getMessage(), e);
        }
    }

    @Then("the user sees duplicate user error message {string}")
    public void the_user_sees_duplicate_user_error_message(String expectedErrorMessage) throws IOException {
    	if (isElementVisible(registrationPage.getExistingUserError(), 5)) {
            String actualError = registrationPage.getExistingUserError().getText().trim();
            captureScreen("DuplicateUserError");
            assertEquals(actualError, expectedErrorMessage);
        } else {
            throw new AssertionError("Duplicate user error message not found.");
        }
    }

    private String getFieldNameFromError(String errorMessage) { 
        if (errorMessage.toLowerCase().contains("first name")) return "FirstName";
        if (errorMessage.toLowerCase().contains("last name")) return "LastName";
        if (errorMessage.toLowerCase().contains("address")) return "Address";
        if (errorMessage.toLowerCase().contains("city")) return "City";
        if (errorMessage.toLowerCase().contains("state")) return "State";
        if (errorMessage.toLowerCase().contains("zip code")) return "ZipCode";
        if (errorMessage.toLowerCase().contains("Social Security Number")) return "SSN";
        if (errorMessage.toLowerCase().contains("username")) return "Username";
        if (errorMessage.toLowerCase().contains("password confirmation")) return "PasswordConfirmation";
        if (errorMessage.toLowerCase().contains("password")) return "Password";
        if (errorMessage.toLowerCase().contains("already exists")) return "DuplicateUser";
        return "UnknownField";
    }
    
}