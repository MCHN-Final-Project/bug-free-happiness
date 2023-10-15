package test.cases.uiTests;

import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.RegisterPage;
import weare.ui.pagemodels.models.UserData;

public class RegisterTests extends BaseTest {
    UserActions actions = new UserActions();
    RegisterPage registerPage = new RegisterPage(actions.getDriver());
    @AfterAll
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
        try {
            actions.assertElementPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Expected page not loaded successfully");
        }
    }

    @Test
    @DisplayName("Register user with existing user data unsuccessfully")
    void registerUserWithRegisteredUserDataUnsuccessfully() {
        userModel = userController.createUser(userData.username, userData.password, userData.email, false);

        registerPage.enterUsername(userModel.username);
        registerPage.enterEmail(userModel.email);
        registerPage.enterPasswordInBothPasswordFields(userData.password);
        actions.clickElement("register.registerButton");

        try {
            actions.assertElementNotPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Registration with already registered user successful");
        }
        actions.assertElementPresent("register.userExistError");
    }

    @Test
    @DisplayName("Register user with empty username unsuccessfully")
    void registerUserWithEmptyUsernameUnsuccessfully() {

        registerPage.enterUsername("");
        registerPage.enterEmail(userData.email);
        registerPage.enterPasswordInBothPasswordFields(userData.password);
        actions.clickElement("register.registerButton");

        try {
            actions.assertElementNotPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Registration with empty username successful");
        }
    }

    @Test
    @DisplayName("Register user with empty email field unsuccessfully")
    void registerUserWithEmptyEmailUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail("");
        registerPage.enterPasswordInBothPasswordFields(userData.password);
        actions.clickElement("register.registerButton");

        try {
            actions.assertElementNotPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Registration with empty email field successful");
        }
    }

    @Test
    @DisplayName("Register user with invalid email format unsuccessfully")
    void registerUserWithInvalidEmailUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.password);
        registerPage.enterPasswordInBothPasswordFields(userData.password);
        actions.clickElement("register.registerButton");

        try {
            actions.assertElementNotPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Registration with invalid email format successful");
        }
        actions.assertElementPresent("register.invalidEmailFormatError");
    }

    @Test
    @DisplayName("Register user with empty password fields unsuccessfully")
    void registerUserWithEmptyPasswordFieldsUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPasswordInBothPasswordFields("");
        actions.clickElement("register.registerButton");

        try {
            actions.assertElementNotPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Registration with empty password fields successful");
        }
    }

    @Test
    @DisplayName("Register user with empty first password field unsuccessfully")
    void registerUserWithEmptyFirstPasswordFieldUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPassword("");
        registerPage.confirmPassword(userData.password);
        actions.clickElement("register.registerButton");

        try {
            actions.assertElementNotPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Registration with empty first password field successful");
        }
    }

    @Test
    @DisplayName("Register user with empty confirmation password field unsuccessfully")
    void registerUserWithEmptyConfirmPasswordFieldUnsuccessfully() {

        registerPage.enterUsername(userData.username);
        registerPage.enterEmail(userData.email);
        registerPage.enterPassword(userData.password);
        registerPage.confirmPassword("");
        actions.clickElement("register.registerButton");

        try {
            actions.assertElementNotPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Registration with empty password confirmation field successful");
        }
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

        try {
            actions.assertElementNotPresent("register.updateProfileLoginButton");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Registration with different passwords in password fields successful");
        }
        actions.assertElementPresent("register.userPasswordNotConfirmedError");
    }
}
