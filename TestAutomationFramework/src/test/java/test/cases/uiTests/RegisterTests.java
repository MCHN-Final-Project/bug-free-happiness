package test.cases.uiTests;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import weare.ui.pagemodels.RegisterPage;
import weare.ui.pagemodels.models.UserModelForUi;

public class RegisterTests {
    UserModelForUi userModel = new UserModelForUi();
    UserActions actions = new UserActions();
    RegisterPage registerPage = new RegisterPage(actions.getDriver());

    @AfterAll
    public static void cleanup() {
        UserActions.quitDriver();
    }

    @Test
    @DisplayName("Register a new user")
    void register_User() {
        registerPage.enterUsername("casc");
        registerPage.enterEmail("mail@mail.com");
        registerPage.enterPassword("password");
        actions.clickElement(Utils.getUIMappingByKey("register.registerButton"));
        userModel = registerPage.assertSuccessfulRegistration("casc");

        Assertions.assertNotNull(userModel,
                "User was not found in the database");
        Assertions.assertEquals(userModel.username, "casc");
        try {
            actions.assertElementPresent("register.loginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Expected page not loaded successfully");
        }
    }
}
