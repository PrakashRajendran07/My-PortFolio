package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.base.BaseClass;

public class OpenAccountPage extends BaseClass {

    public OpenAccountPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "type")
    private WebElement accountTypeDropdown;

    @FindBy(id = "fromAccountId")
    private WebElement existingAccountDropdown;

    @FindBy(xpath = "//input[@value='Open New Account']")
    private WebElement openNewAccountButton;
    
    @FindBy(xpath = "//a[contains(text(),'Open New Account')]")
    private WebElement openAccountPage;

    @FindBy(xpath = "//p[contains(text(),'now open.')]")
    private WebElement successMessage; 
    
    @FindBy(xpath = "//a[@id='newAccountId']")
    private WebElement newAccountNumberLink; 
    
    @FindBy(xpath = "//h1[contains(text(),'Open New Account')]")
    private WebElement openAccountTitle; 

    public WebElement getOpenAccountTitle() {
		return openAccountTitle;
	}

	public WebElement getNewAccountNumberLink() {
		return newAccountNumberLink;
	}

	public WebElement getOpenAccountPage() {
		return openAccountPage;
	}

	public WebElement getSuccessMessage() {
		return successMessage;
	}

	public WebElement getAccountTypeDropdown() {
        return accountTypeDropdown;
    }

    public WebElement getExistingAccountDropdown() {
        return existingAccountDropdown;
    }

    public WebElement getOpenNewAccountButton() {
        return openNewAccountButton;
    }

}
