package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.base.BaseClass;

public class RequestLoanPage extends BaseClass {

    public RequestLoanPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "amount")
    private WebElement loanAmount;

    @FindBy(id = "downPayment")
    private WebElement downPayment;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Apply Now']")
    private WebElement applyNowButton;
    
    @FindBy(xpath = "//p[contains(text(),'Congratulations')]")
    private WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(),'You do not have sufficient funds')]")
    private WebElement errorMessage;

    @FindBy(id = "loanStatus")
    private WebElement loanStatus;

    @FindBy(id = "newAccountId")
    private WebElement newAccountNumber;
    
    @FindBy(xpath = "//a[text()='Request Loan']")
    private WebElement requestLoanLink;

    public WebElement getRequestLoanLink() {
		return requestLoanLink;
	}

	public WebElement getLoanAmount() {
        return loanAmount;
    }

    public WebElement getDownPayment() {
        return downPayment;
    }

    public WebElement getFromAccountDropdown() {
        return fromAccountDropdown;
    }

    public WebElement getApplyNowButton() {
        return applyNowButton;
    }
    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getLoanStatus() {
        return loanStatus;
    }

    public WebElement getNewAccountNumber() {
        return newAccountNumber;
    }
    
    public String getNewAccountNumberText() {
        return newAccountNumber.getText().trim();
    }

}