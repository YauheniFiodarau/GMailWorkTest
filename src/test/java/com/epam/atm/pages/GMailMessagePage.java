package com.epam.atm.pages;

import com.epam.atm.singleton.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GMailMessagePage extends GMailAbstractPage {

    @FindBy(css = "div textarea[role = 'combobox']")
    private WebElement addresseeInput;

    @FindBy(css = "input[name='subjectbox']")
    private WebElement subjectInput;

    @FindBy(css = "div[aria-label='Message Body']")
    private WebElement messageInput;

    @FindBy(css = "img[alt='Close']")
    private WebElement closeBtn;

    @FindBy(css = "div[aria-label~='Send']")
    private WebElement sendBtn;

    public GMailMessagePage enterAddressee(String addressee) {
        waitingForAppearanceElement(addresseeInput);
        typeInElement(addresseeInput, addressee);
        return this;
    }

    public GMailMessagePage enterSubject(String subject) {
        clickOnElement(subjectInput);
        typeInElement(subjectInput, subject);
        return this;
    }

    public GMailMessagePage enterMessage(String message) {
        typeInElement(messageInput, message);
        return new GMailMessagePage();
    }

    public GMailBoxPage closeAndSaveToDraft() {
        clickOnElement(closeBtn);
        return new GMailBoxPage();
    }

    public boolean isCorrectAddressee(String expectedMail) {
        By addresseeLocator = By.cssSelector("span[email='" + expectedMail + "']");
        return isElementPresent(addresseeLocator);
    }

    public boolean isCorrectSubject(String expectedSubject) {
        By subjectLocator = By.cssSelector("input[value='" + expectedSubject + "']");
        return isElementPresent(subjectLocator);
    }

    public boolean isCorrectBodyMessage(String expectedMessage) {
        By messageLocator = By.xpath("//div[text()='" + expectedMessage + "']");
        return isElementPresent(messageLocator);
    }

    public GMailBoxPage clickOnSendBtn() {
        clickOnElement(sendBtn);
        return new GMailBoxPage();
    }
}
