package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import weare.ui.pagemodels.RegisterPage;
import weare.ui.pagemodels.models.UserData;
import weare.ui.pagemodels.models.UserModelForUi;

public class RegisterTests {
    static BaseController baseController = new BaseController();
    static UserModelForUi userModel = new UserModelForUi();
    UserActions actions = new UserActions();
    RegisterPage registerPage = new RegisterPage(actions.getDriver());
    static UserData userData = new UserData();
    @BeforeAll
    public static void setup() {
        userData.username = baseController.getRandomUsername();
        userData.password = baseController.getRandomPassword();
        userData.email = baseController.getRandomEmail();
    }

    @AfterAll
    public static void cleanup() {
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModel.userId);
    }

    @Test
    @DisplayName("Register a new user")
    void register_User_With_Valid_Data_Successfully() {
        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPassword(userData.password);
        actions.clickElement("register.registerButton");
        userModel = registerPage.assertUserExists(userData.username);

        Assertions.assertNotNull(userModel,
                "User was not found in the database");
        Assertions.assertEquals(userModel.username, userData.username);
        try {
            actions.assertElementPresent("register.loginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Expected page not loaded successfully");
        }
    }
}
