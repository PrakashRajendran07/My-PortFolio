package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.base.BaseClass;

public class FindTransactionsPage extends BaseClass {

    public FindTransactionsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "accountId")
    private WebElement accountDropdown;
    
    @FindBy(id = "transactionId")
    private WebElement findByTransactionID;
    
    @FindBy(id = "findById")
    private WebElement findByIdBtn;
    
    @FindBy(id = "transactionDate")
    private WebElement findByTransactionDate;
    
    @FindBy(id = "findByDate")
    private WebElement findByDateBtn;
    
    @FindBy(id = "fromDate")
    private WebElement findByDateRangeFrom;
    
    @FindBy(id = "toDate")
    private WebElement findByDateRangeTo;
    
    @FindBy(id = "findByDateRange")
    private WebElement findByDateRangeBtn;

    @FindBy(id = "amount")
    private WebElement findbyAmount;
    
    @FindBy(id = "findByAmount")
    private WebElement findByAmpuntBtn;

	public WebElement getAccountDropdown() {
		return accountDropdown;
	}

	public WebElement getFindByTransactionID() {
		return findByTransactionID;
	}

	public WebElement getFindByIdBtn() {
		return findByIdBtn;
	}

	public WebElement getFindByTransactionDate() {
		return findByTransactionDate;
	}

	public WebElement getFindByDateBtn() {
		return findByDateBtn;
	}

	public WebElement getFindByDateRangeFrom() {
		return findByDateRangeFrom;
	}

	public WebElement getFindByDateRangeTo() {
		return findByDateRangeTo;
	}

	public WebElement getFindByDateRangeBtn() {
		return findByDateRangeBtn;
	}

	public WebElement getFindbyAmount() {
		return findbyAmount;
	}

	public WebElement getFindByAmpuntBtn() {
		return findByAmpuntBtn;
	}

    
}