package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.base.BaseClass;

public class FundTransferPage extends BaseClass {

    public FundTransferPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "amount")
    private WebElement amountInput;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(id = "toAccountId")
    private WebElement toAccountDropdown;

    @FindBy(xpath = "//input[@value='Transfer']")
    private WebElement transferButton;
    
    @FindBy(id = "amountResult")
    private WebElement amountResult;
    
    @FindBy(id = "fromAccountIdResult")
    private WebElement fromAccountResult;

	@FindBy(id = "toAccountIdResult")
    private WebElement toAccountResult;
    
    
    @FindBy(xpath = " //a[contains(text(),'From and To account numbers should not be the same')]")
    private WebElement sameAccError;
    
    @FindBy(xpath = " //a[contains(text(),'The amount cannot be negative')]")
    private WebElement negativeAmountError;
    
    
    public WebElement getSameAccError() {
		return sameAccError;
	}

	public WebElement getNegativeAmountError() {
		return negativeAmountError;
	}

    public WebElement getAmountResult() {
		return amountResult;
	}

	public WebElement getFromAccountResult() {
		return fromAccountResult;
	}

	public WebElement getToAccountResult() {
		return toAccountResult;
	}

	public WebElement getAmountInput() {
        return amountInput;
    }

    public WebElement getFromAccountDropdown() {
        return fromAccountDropdown;
    }

    public WebElement getToAccountDropdown() {
        return toAccountDropdown;
    }

    public WebElement getTransferButton() {
        return transferButton;
    }

    
}