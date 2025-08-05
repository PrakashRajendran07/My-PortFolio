package org.stepdefinitions;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.base.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.pages.HomePage;
import org.pages.LoginPage;
import org.pages.UpdateContactInfoPage;
import org.utility.ConfigReader;

import io.cucumber.java.en.*;

public class UpdateContactInfoSteps extends BaseClass {

    UpdateContactInfoPage updatePage = new UpdateContactInfoPage();
    LoginPage login = new LoginPage();
    HomePage home = new HomePage();
    
    public void clearField(WebElement element) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // Select all
        element.sendKeys(Keys.DELETE);                    // Delete selected
        element.sendKeys(" ");                            // Trigger validation (space)
        element.sendKeys(Keys.BACK_SPACE);                // Remove space
    }

    @Given("The user is logged in and navigates to the Update Contact Info page")
    public void the_user_is_logged_in_and_navigates_to_the_update_contact_info_page() throws InterruptedException {
    	 String url = ConfigReader.getProperty("baseURL");
		 driver = BaseClass.getDriver();
		 driver.get(url);  
		 driver.manage().window().maximize();
		 login.getUserName().sendKeys(ConfigReader.getProperty("username"));
	     login.getPassword().sendKeys(ConfigReader.getProperty("password"));
	     login.getLoginCTA().click();
	     isElementVisible(home.getUpdateContactInfo(), 5);
	     click(home.getUpdateContactInfo());
	     Thread.sleep(3000);
    }

    @When("The user fills all fields except {string}")
    public void the_user_fills_all_fields_except(String fieldToLeaveBlank) {
    	isElementVisible(updatePage.getFirstName(), 5);
        typeValue(updatePage.getFirstName(), "John");
        typeValue(updatePage.getLastName(), "James");
        typeValue(updatePage.getAddress(), "Madras");
        typeValue(updatePage.getCity(), "Madras");
        typeValue(updatePage.getState(), "TN");
        typeValue(updatePage.getZipCode(), "600053");

        switch (fieldToLeaveBlank.toLowerCase()) {
            case "first name":
                clearField(updatePage.getFirstName());
                break;
            case "last name":
                clearField(updatePage.getLastName());
                break;
            case "address":
                clearField(updatePage.getAddress());
                break;
            case "city":
                clearField(updatePage.getCity());
                break;
            case "state":
                clearField(updatePage.getState());
                break;
            case "zip code":
                clearField(updatePage.getZipCode());
                break;
            default:
                System.out.println("Invalid field: " + fieldToLeaveBlank);
        }
    }

    @And("the user clicks the Update Profile button")
    public void the_user_clicks_the_update_profile_button() throws InterruptedException {
    	Thread.sleep(3000);
        click(updatePage.getUpdateProfileBtn());
    }
    
    @Then("the user should be displayed with error message {string}")
    public void the_user_should_be_displayed_with_error_message(String expectedErrorMessage) throws IOException {
    	try {
            List<WebElement> errorElements = updatePage.getErrorMessages();
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
            captureScreen("UpdateError_" + fieldNameForScreenshot);

            assertTrue(
                actualErrors.stream()
                    .anyMatch(err -> err.equalsIgnoreCase(expectedErrorMessage)),
                "Expected error message not found: " + expectedErrorMessage
            );

        } catch (Exception e) {
            captureScreen("UpdateErrorValidationFailure");
            throw new AssertionError("Failed during error message validation: " + e.getMessage(), e);
        }

    }

    @When("The user enters valid values in all fields")
    public void the_user_enters_valid_values_in_all_fields(io.cucumber.datatable.DataTable dataTable) {
    	Map<String, String> data = dataTable.asMaps().get(0);
        typeValue(updatePage.getFirstName(), data.get("firstName"));
        typeValue(updatePage.getLastName(), data.get("lastName"));
        typeValue(updatePage.getAddress(), data.get("address"));
        typeValue(updatePage.getCity(), data.get("city"));
        typeValue(updatePage.getState(), data.get("state"));
        typeValue(updatePage.getZipCode(), data.get("zipCode"));
        typeValue(updatePage.getPhoneNumber(), data.get("phoneNumber"));
        click(updatePage.getUpdateProfileBtn());
    }

    @Then("The system should redirect to the profile updated page")
    public void the_system_should_redirect_to_the_profile_updated_page() {
        assertTrue(driver.getCurrentUrl().contains("profile.htm"), "Redirection failed");
    }

    @Then("The success message should be {string}")
    public void the_success_message_should_be(String expectedMessage) {
        String actualMessage = updatePage.getSuccessMessage().getText();
        assertEquals(actualMessage.trim(), expectedMessage.trim(), "Success message mismatch");
    }
    
    private String getFieldNameFromError(String errorMessage) { 
        if (errorMessage.toLowerCase().contains("First name")) return "FirstName";
        if (errorMessage.toLowerCase().contains("Last name")) return "LastName";
        if (errorMessage.toLowerCase().contains("Address")) return "Address";
        if (errorMessage.toLowerCase().contains("City")) return "City";
        if (errorMessage.toLowerCase().contains("State")) return "State";
        if (errorMessage.toLowerCase().contains("Zip Code")) return "ZipCode";
        return "UnknownField";
    }
}
