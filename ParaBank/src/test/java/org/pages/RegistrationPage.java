package org.pages;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindAll;

public class RegistrationPage extends BaseClass{
	
	public RegistrationPage() {
		PageFactory.initElements(driver, this);
	}
	@FindAll({
		@FindBy(id="customer.firstName"),
		@FindBy(xpath = "(//input[@class='input'])[3]")})
	private WebElement firstName;
	
	@FindAll({
		@FindBy(id="customer.lastName"),
		@FindBy(xpath = "(//table[@class='form2']//input)[2]")})
	private WebElement lastName;
	
	@FindAll({
		@FindBy(id="customer.address.street"),
		@FindBy(xpath = "(//table[@class='form2']//input)[3]")})
	private WebElement address;
	
	@FindBy(id="customer.address.city")
	private WebElement city;
	
	@FindBy(id="customer.address.state")
	private WebElement state;
	
	@FindBy(id="customer.address.zipCode")
	private WebElement zipCode;
	
	@FindAll({
		@FindBy(id="customer.phoneNumber"),
		@FindBy(xpath = "//input[@name='customer.phoneNumber']")})
	private WebElement phone;
	
	@FindBy(id="customer.ssn")
	private WebElement ssnCode;
	
	@FindBy(id="customer.username")
	private WebElement userName;
	
	@FindBy(id="customer.password")
	private WebElement password;
	
	@FindBy(id="repeatedPassword")
	private WebElement confirmPassword;
	
	@FindBy(xpath="(//input[@class='button'])[2]")
	private WebElement registerCTA;
	
    @FindBy(xpath = "//*[contains(text(),'Your account was created successfully')]")
    private WebElement successMessage;

    @FindBy(xpath = "//span[@class='error']")
    private List<WebElement> errorMessages;
    
    @FindBy(xpath = "//span[contains(text(),'This username already exists')]")
    private WebElement existingUserError;

    public WebElement getExistingUserError() { 
        return existingUserError;
    }

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getAddress() {
		return address;
	}

	public WebElement getCity() {
		return city;
	}

	public WebElement getState() {
		return state;
	}

	public WebElement getZipCode() {
		return zipCode;
	}

	public WebElement getPhone() {
		return phone;
	}

	public WebElement getSsnCode() {
		return ssnCode;
	}

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getConfirmPassword() {
		return confirmPassword;
	}

	public WebElement getRegisterCTA() {
		return registerCTA;
	}
	public WebElement getSuccessMessage() { 
		return successMessage; 
		}

    public List<WebElement> getErrorMessages() { 
    	return errorMessages; 
    	}
    }

