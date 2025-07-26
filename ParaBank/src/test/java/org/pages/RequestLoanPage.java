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

}