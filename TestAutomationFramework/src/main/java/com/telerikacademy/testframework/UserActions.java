package com.telerikacademy.testframework;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static com.telerikacademy.testframework.Utils.*;
import static java.lang.String.format;

public class UserActions {

    final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public UserActions() {
        this.driver = getWebDriver();
    }

    public static void quitDriver() {
        tearDownWebDriver();
    }

    public void clickElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);

        LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void typeValueInField(String value, String field, Object... fieldArguments) {
        String locator = getLocatorValueByKey(field, fieldArguments);
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    //############# WAITS #########
    public void waitForElementVisible(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementVisibleUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    public void waitForElementClickable(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementToBeClickableUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    //############# WAITS #########
    public void navigateBack() {
        driver.navigate().back();
    }

    public void waitForElementPresent(String locator, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        waitForElementPresenceUntilTimeout(locator, defaultTimeout, arguments);
    }

    public void assertElementPresent(String locator) {
        Assertions.assertNotNull(driver.findElement(By.xpath(getUIMappingByKey(locator))),
                format("Element with %s doesn't present.", locator));
    }

    public void assertElementNotPresent(String locator) {
        boolean test = true;
        try {
            Assertions.assertNull(driver.findElement(By.xpath(getUIMappingByKey(locator))));
        } catch (Exception e) {
            test = false;
        }
        Assertions.assertFalse(test, "Test succeeded when expected to fail");
    }

    private String getLocatorValueByKey(String locator) {
        return format(getUIMappingByKey(locator));
    }

    private String getLocatorValueByKey(String locator, Object[] arguments) {
        return format(getUIMappingByKey(locator), arguments);
    }

    private void waitForElementVisibleUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementToBeClickableUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    public void javaScriptExecutorScrollIntoView(String key, Object... arguments) {
        try {
            Thread.sleep(2000);
            String locator = getLocatorValueByKey(key, arguments);

            LOGGER.info("Scrolling into view on element " + key);
            WebElement element = driver.findElement(By.xpath(locator));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            LOGGER.info("Scroll failed");
        }
    }

    public void javaScriptExecutorClick(String key, Object... arguments) {
        try {
            Thread.sleep(2000);
            String locator = getLocatorValueByKey(key, arguments);

            LOGGER.info("Clicking on element " + key);
            WebElement element = driver.findElement(By.xpath(locator));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            LOGGER.info("Click failed");
        }
    }

    public void assertPageNotFound(String key) {
        String page = Utils.getConfigPropertyByKey(key);
        LOGGER.info("Attempting to access page: " + key);
        driver.get(page);
        Assertions.assertTrue(driver.getCurrentUrl().contains(page),
                "Landed URL is not as expected. Actual URL: " + driver.getCurrentUrl() + ". Expected URL: " + page);
        assertElementPresent("notFound.notFoundMessage");
    }
    public void selectFromDropdown(String key, String start, int target) {
        Select drop = new Select(driver.findElement(By.xpath(key)));
        drop.selectByVisibleText(start);
        drop.selectByIndex(target);
    }
    public void selectDate(String key) {
        ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('birthDayE')" +
                        ".removeAttribute('readonly');");

        WebElement fromDateBox = driver.findElement(By.xpath(Utils.getUIMappingByKey(key)));
        fromDateBox.clear();
        fromDateBox.sendKeys("12-12-1950");
    }
    public void selectValueFromDropdown(String value, String field, Object... fieldArguments){
        Select dropdown = new Select(getWebElement(field,fieldArguments));
        dropdown.selectByVisibleText(value);
    }

    public WebElement getWebElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);
        waitForElementVisible(locator);
        return driver.findElement(By.xpath(locator));
    }

    public void uploadImage(String locatorKey,String imagePath) {
        File picture = new File(imagePath);
        String absolutePath = picture.getAbsolutePath();
        WebElement element = getWebElement(locatorKey);
        element.sendKeys(absolutePath);
    }

    public void pressKey(Keys key) {
        Actions actions = new Actions(driver);
        switch (key) {
            case ENTER:
                actions.sendKeys(Keys.ENTER).perform();
                break;
            case TAB:
                actions.sendKeys(Keys.TAB).perform();
                break;
            case DELETE:
                actions.sendKeys(Keys.DELETE).perform();
                break;
            case BACK_SPACE:
                actions.sendKeys(Keys.BACK_SPACE).perform();
                break;
        }
    }
}
