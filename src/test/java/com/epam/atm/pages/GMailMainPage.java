package com.epam.atm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GMailMainPage extends GMailAbstractPage {

    @FindBy(css = "a[href*='AccountChooser']")
    private WebElement signInBtn;

    public GMailLoginPage clickSignInButton() {
        waitingForAppearanceElement(signInBtn);
        clickOnElement(signInBtn);
        return new GMailLoginPage();
    }
}
