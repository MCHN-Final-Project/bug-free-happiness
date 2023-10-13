package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import weare.ui.pagemodels.RegisterPage;
import weare.ui.pagemodels.models.UserData;
import weare.ui.pagemodels.models.UserModelForUi;

public class RegisterTests extends BaseTest{

    @Test
    @DisplayName("Register a new user")
    void registerUserWithValidDataSuccessfully() {
        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPassword(userData.password);
        actions.clickElement("register.registerButton");
        userModelForUi = registerPage.assertUserExists(userData.username);

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
