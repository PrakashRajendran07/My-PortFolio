package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import org.base.BaseClass;

public class AccountDetailsPage extends BaseClass {

    public AccountDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Account Details')]")
    private WebElement accountDetailsHeading;

    @FindBy(id = "accountId")
    private WebElement accountNumber;

    @FindBy(id = "balance")
    private WebElement balance;

    @FindBy(id = "transactionTable")
    private WebElement transactionTable;
    
    @FindBy(id = "month")
    private WebElement activityPeriodDropdown;

    @FindBy(id = "transactionType")
    private WebElement typeDropdown;

    @FindBy(xpath = "//input[@value='Go']")
    private WebElement goButton;
    
    @FindBy(id = "accountType")
    private WebElement accountType;
    
    @FindBy(xpath = "//p[@id='noTransactions']")
    private WebElement noTransaction;  

    @FindBy(xpath = "//table[@id='transactionTable']//tr")
    private List<WebElement> transactionTables;
    
    public List<WebElement> getTransactionTables() {
		return transactionTables;
	}

	public WebElement getNoTransaction() {
		return noTransaction;
	}

	public WebElement getAccountTypeElement() {
        return accountType;
    }

    public WebElement getActivityPeriodDropdown() {
		return activityPeriodDropdown;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getGoButton() {
		return goButton;
	}

	public WebElement getAccountDetailsHeading() {
        return accountDetailsHeading;
    }

    public String getAccountNumber() {
        return accountNumber.getText().trim();
    }
    
    public WebElement getAccountNumberElement() {
        return accountNumber;
    }

    public WebElement getBalance() {
        return balance;
    }

    public WebElement getTransactionTable() {
        return transactionTable;
    }
    public String getAccountType() {
        return accountType.getText();
    }
    
    
}