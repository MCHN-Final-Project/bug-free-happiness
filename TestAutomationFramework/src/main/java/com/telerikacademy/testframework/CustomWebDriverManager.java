package com.telerikacademy.testframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class CustomWebDriverManager {

    public enum CustomWebDriverManagerEnum {
        INSTANCE;
        protected enum BrowserTypes {
            FIREFOX,
            FIREFOX_HEADLESS,
            CHROME,
            CHROME_HEADLESS,
            EDGE,
            EDGE_HEADLESS
        }
        private WebDriver driver = setupBrowser(BrowserTypes.CHROME);

        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        public WebDriver getDriver() {
            if (driver == null) {
                setupBrowser(BrowserTypes.CHROME);
            }
            return driver;
        }

        public static WebDriver setupBrowser(BrowserTypes browserTypes) {
            switch (browserTypes) {
                case CHROME:
                    WebDriver chromeDriver = new ChromeDriver();
                    chromeDriver.manage().window().maximize();
                    return chromeDriver;
                case CHROME_HEADLESS:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    WebDriver chromeHeadlessDriver = new ChromeDriver(chromeOptions);
                    return chromeHeadlessDriver;
                case FIREFOX:
                    WebDriver firefoxDriver = new FirefoxDriver();
                    firefoxDriver.manage().window().maximize();
                    return firefoxDriver;
                case FIREFOX_HEADLESS:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    WebDriver firefoxHeadlessDriver = new FirefoxDriver(firefoxOptions);
                    return firefoxHeadlessDriver;
                case EDGE:
                    WebDriver edgeDriver = new EdgeDriver();
                    edgeDriver.manage().window().maximize();
                    return edgeDriver;
                case EDGE_HEADLESS:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    WebDriver edgeHeadlessDriver = new EdgeDriver(edgeOptions);
                    return edgeHeadlessDriver;
            }
            return null;
        }
    }
}
