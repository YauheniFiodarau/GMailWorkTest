package com.epam.atm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GMailLoginPage extends GMailAbstractPage {

    @FindBy(css = "input[type='email']")
    private WebElement emailInput;

    @FindBy(css = "div#identifierNext")
    private WebElement nextBtn;

    @FindBy(css = "input[type='password']")
    private WebElement pwdInput;

    @FindBy(css = "div#passwordNext")
    private WebElement signInBtn;

    public GMailLoginPage enterAccountName(String accountName){
        waitingForAppearanceElement(emailInput);
        typeInElement(emailInput, accountName);
        clickOnElement(nextBtn);
        return this;
    }

    public GMailBoxPage enterPassword(String pwd){
        waitingForAppearanceElement(pwdInput);
        typeInElement(pwdInput, pwd);
        clickOnElement(signInBtn);
        return new GMailBoxPage();
    }

}
