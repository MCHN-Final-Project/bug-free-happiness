package weare.ui.pagemodels;

import api.controllers.UserController;
import com.telerikacademy.testframework.UserActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserData;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class AdminPage extends BasePage {
    UserActions actions = new UserActions();

    public AdminPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.homepage", true);
        userData = new UserData();
        UserController userController = new UserController();
        userModel = userController.createUser(userData.username, userData.password, userData.email, true);
        driver.manage().addCookie(userModel.cookie);
    }
    public void navigateToUserList() {
        actions.clickElement("admin.userList");
        actions.assertElementPresent("admin.loadMoreUsers");
    }
    public void disableUser() {
        actions.assertElementPresent("admin.disableProfile");
        actions.clickElement("admin.disableProfile");
        actions.assertElementPresent("admin.enableProfile");
    }

    public void enableUser() {
        actions.assertElementPresent("admin.enableProfile");
        actions.clickElement("admin.enableProfile");
        actions.assertElementPresent("admin.disableProfile");
    }

    public void chooseUserById(int id){
        actions.waitForElementClickable(getUIMappingByKey("admin.seeUserProfile"), id);
        actions.clickElement(getUIMappingByKey("admin.seeUserProfile"), id);
    }

    public void clearField(String key) {
        actions.clickElement(getUIMappingByKey(key));
        for (int i = 0; i < 20; i++) {
            actions.pressKey(Keys.BACK_SPACE);
        }
    }
}
