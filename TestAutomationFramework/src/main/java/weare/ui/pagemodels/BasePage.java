package weare.ui.pagemodels;

import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserData;

public class BasePage {

    protected WebDriver driver;
    protected String url;
    public UserActions actions;
    public static UserModel userModel;
    public static UserData userData;

    public BasePage(WebDriver driver, String urlKey, boolean register) {
        if (register) {
            this.driver = driver;
            this.url = Utils.getConfigPropertyByKey(urlKey);
            actions = new UserActions();
            navigateToPage();
        } else nonRegister(driver, urlKey);
    }

    public void nonRegister(WebDriver driver, String urlKey) {
        this.driver = driver;
        UserController userController = new UserController();
        userData = new UserData();
        userModel = userController.createUser(userData.username, userData.password, userData.email, false);
        this.url = Utils.getConfigPropertyByKey(urlKey);
        navigateToPage();
        assertPageNavigated();
        driver.manage().addCookie(userModel.cookie);
        actions = new UserActions();
    }

    public void navigateToPage() {
        this.driver.get(url);
    }

    public void navigateToPage(String urlKey) {
        this.driver.get(Utils.getConfigPropertyByKey(urlKey));
    }

    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(url),
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + url);
    }

    public void assertPageNavigated(String urlKey) {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(Utils.getConfigPropertyByKey(urlKey)),
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + Utils.getConfigPropertyByKey(urlKey));
    }

}
