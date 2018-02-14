package com.epam.atm.singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static WebDriver webDriverInstance = null;

    private WebDriverSingleton() {
    }

    public static WebDriver getWebDriverInstance() {
        if (webDriverInstance == null) {
            webDriverInstance = initializingWebDriver();
        }
        return webDriverInstance;
    }

    private static WebDriver initializingWebDriver() {
        System.setProperty("webdriver.chrome.driver", "c:\\SeleniumDrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts()
                .pageLoadTimeout(15, TimeUnit.SECONDS);
        webDriver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }

    public static void exit() {
        if (webDriverInstance != null) {
            try {
                webDriverInstance.quit();
            } catch (Exception exception) {
                System.out.println("Can`t close browser");
            } finally {
                webDriverInstance = null;
            }
        }
    }

}
