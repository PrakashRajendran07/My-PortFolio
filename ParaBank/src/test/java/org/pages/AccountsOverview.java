package org.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import org.base.BaseClass;

public class AccountsOverview extends BaseClass {

    public AccountsOverview() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Accounts Overview')]")
    private WebElement accountsOverviewHeading;
    
    @FindBy(xpath = "//table[@id='accountTable']//tr/td/a")
    private List<WebElement> accountLinks;

    @FindBy(xpath = "//table[@id='accountTable']//tr/td[2]")
    private List<WebElement> accountBalances;

    public List<WebElement> getAccountLinks() {
        return accountLinks;
    }

    public List<WebElement> getAccountBalances() {
        return accountBalances;
    }

    public WebElement getAccountsOverviewHeading() {
        return accountsOverviewHeading;
    }
    
    public void clickFirstAccountLink() {
        accountLinks.get(0).click();
    }
}

