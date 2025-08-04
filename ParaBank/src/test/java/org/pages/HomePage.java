package org.pages;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass{
	
	public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Open New Account")
    private WebElement openNewAccount;

    @FindBy(linkText = "Accounts Overview")
    private WebElement accountsOverview;

    @FindBy(xpath = "//a[normalize-space(text())='Transfer Funds']")
    private WebElement transferFunds;

    @FindBy(linkText = "Bill Pay")
    private WebElement billPay;

    @FindBy(linkText = "Find Transactions")
    private WebElement findTransactions;

    @FindBy(linkText = "Update Contact Info")
    private WebElement updateContactInfo;

    @FindBy(linkText = "Request Loan")
    private WebElement requestLoan;

    @FindBy(linkText = "Log Out")
    private WebElement logOut;

    public WebElement getOpenNewAccount() {
        return openNewAccount;
    }

    public WebElement getAccountsOverview() {
        return accountsOverview;
    }

    public WebElement getTransferFunds() {
        return transferFunds;
    }

    public WebElement getBillPay() {
        return billPay;
    }

    public WebElement getFindTransactions() {
        return findTransactions;
    }

    public WebElement getUpdateContactInfo() {
        return updateContactInfo;
    }

    public WebElement getRequestLoan() {
        return requestLoan;
    }

    public WebElement getLogOut() {
        return logOut;
    }

}
