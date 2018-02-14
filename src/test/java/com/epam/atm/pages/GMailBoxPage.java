package com.epam.atm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GMailBoxPage extends GMailAbstractPage {

    @FindBy(css = "a[title~='(yauheni.fiodarau@gmail.com)']")
    private WebElement currentAccount;

    @FindBy(css = "div[jscontroller] div[role='button']")
    private WebElement composeBtn;

    @FindBy(css = "a[title~='Sent']")
    private WebElement sentMailLink;

    @FindBy(css = "a[title~='Drafts']")
    private WebElement draftFolderLink;

    @FindBy(css = "a[role='button'][href*='account']")
    private WebElement accountBtn;

    @FindBy(css = "a[href*='Logout']")
    private WebElement signOutBtn;

    public boolean isInTheGMailBox() {
        waitingForAppearanceElement(currentAccount);
        return currentAccount.isDisplayed();
    }

    public GMailMessagePage createNewMessage() {
        waitingForAppearanceElement(composeBtn);
        clickOnElement(composeBtn);
        return new GMailMessagePage();
    }

    public GMailDraftFolderPage clickOnDraftFolderLink() {
        clickOnElement(draftFolderLink);
        return new GMailDraftFolderPage();
    }

    public GMailSentFolderPage clickOnSentFolderLink() {
        clickOnElement(sentMailLink);
        return new GMailSentFolderPage();
    }

    public GMailBoxPage clickOnAccountBtn() {
        clickOnElement(accountBtn);
        return this;
    }

    public void clickSignOutBtn() {
        waitingForAppearanceElement(signOutBtn);
        clickOnElement(signOutBtn);
    }
}
