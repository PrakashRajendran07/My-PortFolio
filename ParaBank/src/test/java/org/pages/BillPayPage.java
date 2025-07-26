package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.base.BaseClass;

public class BillPayPage extends BaseClass {

    public BillPayPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "payee.name")
    private WebElement payeeName;

    @FindBy(name = "payee.address.street")
    private WebElement payeeAddress;

    @FindBy(name = "payee.address.city")
    private WebElement payeeCity;

    @FindBy(name = "payee.address.state")
    private WebElement payeeState;

    @FindBy(name = "payee.address.zipCode")
    private WebElement payeeZipCode;

    @FindBy(name = "payee.phoneNumber")
    private WebElement payeePhone;

    @FindBy(name = "payee.accountNumber")
    private WebElement accountNumber;

    @FindBy(name = "verifyAccount")
    private WebElement verifyAccount;

    @FindBy(name = "amount")
    private WebElement amount;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(xpath = "//input[@value='Send Payment']")
    private WebElement sendPaymentButton;

    public WebElement getPayeeName() {
        return payeeName;
    }

    public WebElement getPayeeAddress() {
        return payeeAddress;
    }

    public WebElement getPayeeCity() {
        return payeeCity;
    }

    public WebElement getPayeeState() {
        return payeeState;
    }

    public WebElement getPayeeZipCode() {
        return payeeZipCode;
    }

    public WebElement getPayeePhone() {
        return payeePhone;
    }

    public WebElement getAccountNumber() {
        return accountNumber;
    }

    public WebElement getVerifyAccount() {
        return verifyAccount;
    }

    public WebElement getAmount() {
        return amount;
    }

    public WebElement getFromAccountDropdown() {
        return fromAccountDropdown;
    }

    public WebElement getSendPaymentButton() {
        return sendPaymentButton;
    }

}