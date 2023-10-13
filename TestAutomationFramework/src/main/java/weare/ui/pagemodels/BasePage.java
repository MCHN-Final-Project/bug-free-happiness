package weare.ui.pagemodels;

import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserData;

public abstract class BasePage {

    protected WebDriver driver;
    protected String url;
    public UserActions actions;
    public static UserModel userModel;
    public static UserData userData = new UserData();

    public BasePage(WebDriver driver, String urlKey, boolean register) {
        if (register) {
            this.driver = driver;
            this.url = Utils.getConfigPropertyByKey(urlKey);
            actions = new UserActions();
        }
        else nonRegister(driver, urlKey);
    }

    public String getUrl() {
        return url;
    }
    public void nonRegister (WebDriver driver, String urlKey) {
        this.driver = driver;
        UserController userController = new UserController();
        userModel = userController.createUser(userData.username, userData.password, userData.email,false);
        this.url = Utils.getConfigPropertyByKey(urlKey);
        navigateToPage();
        assertPageNavigated();
        driver.manage().addCookie(userModel.cookie);
        actions = new UserActions();
        this.url = Utils.getConfigPropertyByKey(urlKey);
    }

    public void navigateToPage() {
        this.driver.get(url);
    }

    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(url),
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + url);
    }

}
