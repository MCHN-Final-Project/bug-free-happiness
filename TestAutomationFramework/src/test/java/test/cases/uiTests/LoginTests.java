package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.UserController;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.BeforeAll;
import weare.ui.pagemodels.LoginPage;
import weare.ui.pagemodels.models.UserData;
import weare.ui.pagemodels.models.UserModelForUi;

public class LoginTests {
    static BaseController baseController = new BaseController();
    static UserController userController = new UserController();
    UserModelForUi userModel = new UserModelForUi();
    UserActions actions = new UserActions();
    LoginPage loginPage = new LoginPage(actions.getDriver());
    static UserData userData = new UserData();

    @BeforeAll
    public static void setup() {
        userData.username = baseController.getRandomUsername();
        userData.password = baseController.getRandomPassword();
        userData.email = baseController.getRandomEmail();
        userController.createUser(userData.username, userData.password, userData.email, false);

    }

}
