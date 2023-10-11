package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.BasePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.LoginPage;
import weare.ui.pagemodels.models.UserData;

public class LoginTests {
    static BaseController baseController = new BaseController();
    static UserController userController = new UserController();
    static UserModel userModel = new UserModel();
    UserActions actions = new UserActions();
    LoginPage loginPage = new LoginPage(actions.getDriver());
    static UserData userData = new UserData();

    @BeforeAll
    public static void setup() {
        userData.username = baseController.getRandomUsername();
        userData.password = baseController.getRandomPassword();
        userData.email = baseController.getRandomEmail();
        userModel = userController.createUser(userData.username, userData.password, userData.email, false);
    }
    @AfterAll
    public static void cleanup() {
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModel.id);
    }
    @Test
    public void test() {
        loginPage.login(userData.username, userData.password);
    }

}
