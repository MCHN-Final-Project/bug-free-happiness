package test.cases.uiTests;

import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.RegisterPage;
import weare.ui.pagemodels.models.UserData;

public class RegisterTests extends BaseTest {
    UserActions actions = new UserActions();
    RegisterPage registerPage = new RegisterPage(actions.getDriver());

    @AfterAll
    @Tag("TestSet:Q5FP-26")
    public static void cleanup() {
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModelForUi.userId);
    }

    @Test
    @DisplayName("Register a new user with valid data successfully")
    void registerUserWithValidDataSuccessfully() {
        UserData newUser = new UserData();
        registerPage.enterUsername(newUser.username);
        registerPage.enterEmail(newUser.email);
        registerPage.enterPasswordInBothPasswordFields(newUser.password);
        actions.clickElement("register.registerButton");
        userModelForUi = registerPage.assertUserExists(userData.username);

        Assertions.assertNotNull(userModelForUi,
                "User was not found in the database");
        Assertions.assertEquals(userModelForUi.username, userData.username);
        actions.assertElementPresent("register.updateProfileLoginButton");
    }

    @Test
    @DisplayName("Register user with existing user data unsuccessfully")
    void registerUserWithRegisteredUserDataUnsuccessfully() {
        userModel = userController.createUser(userData.username, userData.password, userData.email, false);

        registerPage.enterUsername(userModel.username);
        registerPage.enterEmail(userModel.email);
        registerPage.enterPasswordInBothPasswordFields(userData.password);
        actions.clickElement("register.registerButton");

        actions.assertElementNotPresent("register.updateProfileLoginButton");
        actions.assertElementPresent("register.userExistError");
    }

    @Test
    @DisplayName("Register user with empty username unsuccessfully")
    void registerUserWithEmptyUsernameUnsuccessfully() {

        registerPage.enterUsername("");
        registerPage.enterEmail(userData.email);
        registerPage.enterPasswordInBothPasswordFields(userData.password);
        actions.clickElement("register.registerButton");

        actions.assertElementNotPresent("register.updateProfileLoginButton");
    }

    @Test
    @DisplayName("Register user with empty email field unsuccessfully")
    void registerUserWithEmptyEmailUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail("");
        registerPage.enterPasswordInBothPasswordFields(userData.password);
        actions.clickElement("register.registerButton");

        actions.assertElementNotPresent("register.updateProfileLoginButton");
    }

    @Test
    @DisplayName("Register user with invalid email format unsuccessfully")
    void registerUserWithInvalidEmailUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.password);
        registerPage.enterPasswordInBothPasswordFields(userData.password);
        actions.clickElement("register.registerButton");

        actions.assertElementNotPresent("register.updateProfileLoginButton");
        actions.assertElementPresent("register.invalidEmailFormatError");
    }

    @Test
    @DisplayName("Register user with empty password fields unsuccessfully")
    void registerUserWithEmptyPasswordFieldsUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPasswordInBothPasswordFields("");
        actions.clickElement("register.registerButton");

        actions.assertElementNotPresent("register.updateProfileLoginButton");
    }

    @Test
    @DisplayName("Register user with empty first password field unsuccessfully")
    void registerUserWithEmptyFirstPasswordFieldUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPassword("");
        registerPage.confirmPassword(userData.password);
        actions.clickElement("register.registerButton");

        actions.assertElementNotPresent("register.updateProfileLoginButton");
    }

    @Test
    @DisplayName("Register user with empty confirmation password field unsuccessfully")
    void registerUserWithEmptyConfirmPasswordFieldUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPassword(userData.password);
        registerPage.confirmPassword("");
        actions.clickElement("register.registerButton");

        actions.assertElementNotPresent("register.updateProfileLoginButton");
        actions.assertElementPresent("register.userPasswordNotConfirmedError");
    }

    @Test
    @DisplayName("Register user with different passwords in password fields unsuccessfully")
    void registerUserWithDifferentPasswordsUnsuccessfully() {
        UserData differentPassword = new UserData();

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPassword(userData.password);
        registerPage.confirmPassword(differentPassword.password);
        actions.clickElement("register.registerButton");

        actions.assertElementNotPresent("register.updateProfileLoginButton");
        actions.assertElementPresent("register.userPasswordNotConfirmedError");
    }
}
