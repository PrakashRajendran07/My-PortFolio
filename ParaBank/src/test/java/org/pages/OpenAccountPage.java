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
