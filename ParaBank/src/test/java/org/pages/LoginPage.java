package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.base.BaseClass;
public class LoginPage extends BaseClass {
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@name='username']")
	private WebElement userName;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@value='Log In']")
	private WebElement loginCTA;
	
	@FindBy(xpath="//a[contains(text(),'Forgot login')]")
	private WebElement forgotLogin;
	
	@FindBy(xpath="//a[contains(text(),'Register')]")
	private WebElement registration;
	
	@FindBy(xpath = "//p[text()='The username and password could not be verified.']")
	private WebElement invalidCredentialMessage;
	
	@FindBy(xpath = "//p[text()='Please enter a username and password.']")
	private WebElement emptyCredentialsMessage;
	
	@FindBy(xpath = "//p[contains(@class, 'error')]")
	private WebElement errorMessage;

	public WebElement getInvalidCredentialMessage() {
		return invalidCredentialMessage;
	}

	public WebElement getEmptyCredentialsMessage() {
		return emptyCredentialsMessage;
	}

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginCTA() {
		return loginCTA;
	}

	public WebElement getForgotLogin() {
		return forgotLogin;
	}

	public WebElement getRegistration() {
		return registration;
	}
	public WebElement getErrorMessage() {
		return errorMessage;
	}

}
