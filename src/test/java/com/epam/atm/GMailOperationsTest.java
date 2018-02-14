package com.epam.atm;

import com.epam.atm.pages.GMailBoxPage;
import com.epam.atm.pages.GMailDraftFolderPage;
import com.epam.atm.pages.GMailMainPage;
import com.epam.atm.pages.GMailMessagePage;
import com.epam.atm.singleton.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GMailOperationsTest {

    private static final String ACCOUNT_NAME = "yauhenifiodarau";
    private static final String PWD = "6651828lao";
    private static final String MAIL_ADRESS = "yauheni.fiodarau@gmail.com";
    private static final String SUBJECT_TEXT = "seleniumTest";
    private static final String MESSAGE_TEXT = "Test message for autotest";
    private static final String GMAIL_START_URL = "https://www.google.com/gmail/about/";

    @BeforeClass
    public void loadBrowser() {
        WebDriverSingleton.getWebDriverInstance().get(GMAIL_START_URL);
    }

    @Test
    public void successfulLoginTest() {
        boolean isLoginSuccess = new GMailMainPage()
                .clickSignInButton()
                .enterAccountName(ACCOUNT_NAME)
                .enterPassword(PWD)
                .isInTheGMailBox();
        Assert.assertTrue(isLoginSuccess, "Login failed!");
    }

    @Test(dependsOnMethods = "successfulLoginTest")
    public void verificationThatNewMessageSaveToDraft() {
        boolean isNewMessageSaveToDraft = new GMailBoxPage()
                .createNewMessage()
                .enterAddressee(MAIL_ADRESS)
                .enterSubject(SUBJECT_TEXT)
                .enterMessage(MESSAGE_TEXT)
                .closeAndSaveToDraft()
                .clickOnDraftFolderLink()
                .isNewMessageInDraftFolder(SUBJECT_TEXT);
        Assert.assertTrue(isNewMessageSaveToDraft, "New message doesn't in draft folder!");
    }

    @Test(dependsOnMethods = "verificationThatNewMessageSaveToDraft")
    public void verificationThatMessageFromDraftHasCorrectAddressee() {
        boolean isCorrectAddressee = new GMailDraftFolderPage()
                .clickOnMessageWithSubject(SUBJECT_TEXT)
                .isCorrectAddressee(MAIL_ADRESS);
        Assert.assertTrue(isCorrectAddressee, "Incorrect addressee!");
    }

    @Test(dependsOnMethods = "verificationThatMessageFromDraftHasCorrectAddressee")
    public void verificationThatMessageFromDraftHasCorrectSubject() {
        boolean isCorrectSubject = new GMailMessagePage()
                .isCorrectSubject(SUBJECT_TEXT);
        Assert.assertTrue(isCorrectSubject, "Incorrect subject!");
    }

    @Test(dependsOnMethods = "verificationThatMessageFromDraftHasCorrectSubject")
    public void verificationThatMessageFromDraftHasCorrectBodyMessage() {
        boolean isCorrectBodyMessage = new GMailMessagePage()
                .isCorrectBodyMessage(MESSAGE_TEXT);
        Assert.assertTrue(isCorrectBodyMessage, "Incorrect message!");
    }

    @Test(dependsOnMethods = "verificationThatMessageFromDraftHasCorrectBodyMessage")
    public void verificationThatMessageDisappearedFromDraftAfterSending() {
        boolean isMessageExistInDraft = new GMailMessagePage()
                .clickOnSendBtn()
                .clickOnDraftFolderLink()
                .isMessageRemoveFromDraft(SUBJECT_TEXT);
        Assert.assertFalse(isMessageExistInDraft);
    }

    @Test(dependsOnMethods = "verificationThatMessageDisappearedFromDraftAfterSending")
    public void verificationThatMessageInSentFolder() {
        boolean isMessageExistInSentFolder = new GMailBoxPage()
                .clickOnSentFolderLink()
                .isMessageInSentFolder(SUBJECT_TEXT);
        Assert.assertTrue(isMessageExistInSentFolder);
    }

    @AfterClass
    public void deleteMessageFromSentFolderLogOutAndCloseBrowser() {
        new GMailBoxPage()
                .clickOnSentFolderLink()
                .deleteMessageFromSentFolder(SUBJECT_TEXT);
        new GMailBoxPage()
                .clickOnAccountBtn()
                .clickSignOutBtn();
        WebDriverSingleton.exit();
    }
}
