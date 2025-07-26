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