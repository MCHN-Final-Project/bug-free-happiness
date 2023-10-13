package weare.ui.pagemodels;

import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserData;

public class AdminPage extends BasePage {
    UserController userController = new UserController();
    UserActions actions = new UserActions();
    public UserData userData = new UserData();
    UserModel userModel;
    public AdminPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.adminPage", false);
        userModel = userController.createUser
                (userData.username, userData.password, userData.email, true);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userData.username, userData.password);
        navigateToPage();
        assertPageNavigated();
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
}
