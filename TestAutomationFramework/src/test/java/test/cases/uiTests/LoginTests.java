package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.BasePage;
import weare.ui.pagemodels.LoginPage;
import weare.ui.pagemodels.models.UserData;

import java.util.Objects;

public class LoginTests {
    static UserActions actions = new UserActions();
    static LoginPage loginPage = new LoginPage(actions.getDriver());

    @BeforeEach
    @Tag("TestSet:Q5FP-24")
    public void setup() {
        loginPage.navigateToPage();
        loginPage.assertPageNavigated();
    }

    @AfterAll
    public static void cleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup"))
            return;
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", BasePage.userModel.id);
    }

    @Test
    @DisplayName("Successful login with valid credentials")
    public void loginWithValidUserSuccessfully() {
        loginPage.login(BasePage.userData.username, BasePage.userData.password);

        actions.assertElementPresent("home.logoutButton");
        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("home.personalProfileAlt"),
                BasePage.userModel.id));
    }

    @Test
    @DisplayName("Error displayed when using correct username/wrong password")
    public void loginWithInvalidPasswordThrowsError() {
        String invalidPassword = BaseController.faker.internet().password();
        while (Objects.equals(invalidPassword, BasePage.userData.password))
            invalidPassword = BaseController.faker.internet().password();

        loginPage.login(BasePage.userData.username, invalidPassword);

        actions.assertElementPresent("home.credentialError");
        actions.assertElementNotPresent(String.format(Utils.getUIMappingByKey("home.personalProfileAlt"),
                BasePage.userModel.id));
    }

    @Test
    @DisplayName("Error displayed when using wrong username/correct password")
    public void loginWithInvalidUsernameThrowsError() {
        String invalidUsername = BaseController.faker.name().firstName();
        while (Objects.equals(invalidUsername, BasePage.userData.password))
            invalidUsername = BaseController.faker.name().firstName();

        loginPage.login(invalidUsername, BasePage.userData.password);

        actions.assertElementPresent("home.credentialError");
        actions.assertElementNotPresent(String.format(Utils.getUIMappingByKey("home.personalProfileAlt"),
                BasePage.userModel.id));
    }

    @Test
    @DisplayName("Error displayed when using empty fields")
    public void loginWithEmptyCredentialsThrowsError() {
        loginPage.login("", "");

        actions.assertElementPresent("home.credentialError");
        actions.assertElementNotPresent(String.format(Utils.getUIMappingByKey("home.personalProfileAlt"),
                BasePage.userModel.id));
    }

    @Test
    @DisplayName("User is not logged back in when navigating back after logging out")
    public void navigateBackWhenLoggedOutDoesNotLoadProfile() {
        loginWithValidUserSuccessfully();
        actions.clickElement("home.logoutButton");
        actions.assertElementPresent("home.signInButton");

        actions.navigateBack();

        actions.assertElementPresent("home.signInButton");
        actions.assertElementNotPresent("home.logoutButton");
    }

    @Test
    @DisplayName("Login in with a deleted user unsuccessfully")
    public void loginWithDeletedUserThrowsError() {
        UserController userController = new UserController();
        UserData userData = new UserData();
        UserModel local = userController.createUser(userData.username, userData.password, userData.email, false);
        SqlMethods.deleteUserById("user_id", local.id);

        loginPage.login(userData.username, userData.password);

        actions.assertElementPresent("home.credentialError");
        actions.assertElementNotPresent(String.format(Utils.getUIMappingByKey("home.personalProfileAlt"),
                local.id));
    }

    @Test
    @DisplayName("Regular user cannot access Admin panel when logged in")
    public void loggedInAsUserAdminPanelNotVisible() {
        loginWithValidUserSuccessfully();

        actions.assertElementNotPresent("admin.adminZone");
        actions.assertPageNotFound("weAreSocialNetwork.adminPage");
    }

    @Test
    @DisplayName("Admin user can access Admin panel when logged in")
    public void loggedInAsAdminAdminPanelVisible() {
        UserController userController = new UserController();
        UserData userData = new UserData();
        UserModel local = userController.createUser(userData.username, userData.password, userData.email, true);

        loginPage.login(local.username, userData.password);
        actions.assertElementPresent("admin.adminZone");

        actions.clickElement(Utils.getUIMappingByKey("admin.adminZone"));
        actions.assertElementPresent("admin.userList");

        SqlMethods.deleteUserById("user_id", local.id);
    }
}
