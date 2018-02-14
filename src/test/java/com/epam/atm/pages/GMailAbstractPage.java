package com.epam.atm.pages;

import com.epam.atm.singleton.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GMailAbstractPage {
    protected WebDriver webDriver;
    protected WebDriverWait driverWait;
    private static final long SLEEP_TIMEOUT = 5000L;

    public GMailAbstractPage() {
        this.webDriver = WebDriverSingleton.getWebDriverInstance();
        driverWait = new WebDriverWait(webDriver, SLEEP_TIMEOUT);
        PageFactory.initElements(webDriver, this);
    }

    public boolean isElementPresent(By locator) {
        return webDriver.findElements(locator).size() > 0;
    }

    public void highlightElement(WebElement element) {
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) webDriver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "lightgreen" + "'", element);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
    }

    public void waitingForAppearanceElement(WebElement element) {
        driverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickOnElement(WebElement element) {
        highlightElement(element);
        new Actions(webDriver).click(element).build().perform();
    }

    public void typeInElement(WebElement element, String text) {
        highlightElement(element);
        new Actions(webDriver).sendKeys(element, text).build().perform();
    }

    public Actions getActions(){
        return new Actions(webDriver);
    }
}
