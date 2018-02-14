package com.epam.atm.pages;

import com.epam.atm.singleton.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GMailSentFolderPage extends GMailBoxPage {
    @FindBy(css = "button[name='ok']")
    private WebElement obBtn;

    private By messageLocator;

    public boolean isMessageInSentFolder(String subjectText) {
        messageLocator = getMessageLocator(subjectText);
        return isElementPresent(messageLocator);
    }

    public void deleteMessageFromSentFolder(String subjectText) {
        messageLocator = getMessageLocator(subjectText);
        WebElement messageElement = WebDriverSingleton.getWebDriverInstance().findElement(messageLocator);
        getActions()
                .contextClick(messageElement)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build().perform();
        waitingForAppearanceElement(obBtn);
        clickOnElement(obBtn);
    }

    private By getMessageLocator(String subjectText) {
        return By.xpath("//div[@role='main']//span/b[text()='" + subjectText + "']");
    }
}
