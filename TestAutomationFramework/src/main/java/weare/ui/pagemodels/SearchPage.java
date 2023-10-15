package weare.ui.pagemodels;

import org.openqa.selenium.*;

import java.util.TreeMap;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.homepage", false);
    }

    public void enterProfession(String profession) {
        actions.waitForElementClickable("home.profession");
        actions.typeValueInField(profession, "home.profession");
    }

    public void enterUsersName(String username) {
        actions.waitForElementClickable("home.username");
        actions.typeValueInField(username, "home.username");
    }

    public void clickOnSearchButton() {
        actions.waitForElementClickable("home.searchButton");
        actions.clickElement("home.searchButton");
    }


    public void clearNameInSearchField() {
        actions.waitForElementPresent("home.username");
        WebElement inputElement = driver.findElement(By.id("searchParam2"));

        String inputValue = inputElement.getAttribute("value");

        if (!inputValue.isEmpty()) {
            actions.waitForElementClickable("home.username");
            actions.clickElement("home.username");
            for (int i = 0; i < inputValue.length(); i++) {
                actions.pressKey(Keys.BACK_SPACE);
            }
        }
    }

    public void clearProfessionInSearchField() {
        actions.waitForElementPresent("home.profession");
        WebElement inputElement = driver.findElement(By.id("searchParam1"));

        String inputValue = inputElement.getAttribute("value");

        if (!inputValue.isEmpty()) {
            actions.waitForElementClickable("home.profession");
            actions.clickElement("home.profession");
            for (int i = 0; i < inputValue.length(); i++) {
                actions.pressKey(Keys.BACK_SPACE);
            }
        }
    }
}
