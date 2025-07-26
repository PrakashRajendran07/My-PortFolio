package org.pages;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotLoginPage extends BaseClass{
	
	public ForgotLoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "address.street")
    private WebElement address;

    @FindBy(id = "address.city")
    private WebElement city;

    @FindBy(id = "address.state")
    private WebElement state;

    @FindBy(id = "address.zipCode")
    private WebElement zipCode;

    @FindBy(id = "ssn")
    private WebElement ssn;

    @FindBy(xpath = "//input[@value='Find My Login Info']")
    private WebElement findMyLoginInfoButton;

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

    public WebElement getSsn() {
        return ssn;
    }

    public WebElement getFindMyLoginInfoButton() {
        return findMyLoginInfoButton;
    }
}