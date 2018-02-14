package com.epam.atm.pages;

import com.epam.atm.singleton.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GMailDraftFolderPage extends GMailBoxPage {

    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10000;

    public boolean isNewMessageInDraftFolder(String subjectText) {
        WebElement newMessageInDraftFolder = getMessageBySubjectText(subjectText);
        waitingForAppearanceElement(newMessageInDraftFolder);
        return newMessageInDraftFolder.isDisplayed();
    }

    public GMailMessagePage clickOnMessageWithSubject(String subjectText) {
        WebElement messageInDraftFolder = getMessageBySubjectText(subjectText);
        clickOnElement(messageInDraftFolder);
        return new GMailMessagePage();
    }

    public boolean isMessageRemoveFromDraft(String subjectText) {
        WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getWebDriverInstance(), WAIT_FOR_ELEMENT_TIMEOUT_SECONDS);
        By draftMailLocator = By.xpath("//span[text()='" + subjectText + "']");
        wait.until(ExpectedConditions
                .invisibilityOfElementLocated(draftMailLocator));
        return isElementPresent(draftMailLocator);
    }

    private WebElement getMessageBySubjectText(String subjectText) {
        By draftMailLocator = By.xpath("//span[text()='" + subjectText + "']");
        return WebDriverSingleton.getWebDriverInstance().findElement(draftMailLocator);
    }
}
