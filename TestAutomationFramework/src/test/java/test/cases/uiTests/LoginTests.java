package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.DisplayName;
import weare.ui.pagemodels.BasePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.LoginPage;
import weare.ui.pagemodels.models.UserData;

import java.util.Objects;

public class LoginTests {
    static UserController userController = new UserController();
    UserActions actions = new UserActions();
    LoginPage loginPage = new LoginPage(actions.getDriver());

    @BeforeAll
    public static void setup() {
    }
    @AfterAll
    public static void cleanup() {
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
    @DisplayName("Error displayed when using wrong password")
    public void loginWithInvalidPasswordThrowsError() {
        String invalidPassword = null;
        while (Objects.equals(invalidPassword, BasePage.userData.password))
            invalidPassword = BaseController.faker.internet().password();

        loginPage.login(BasePage.userData.username, invalidPassword);

        actions.assertElementPresent("home.credentialError");
    }

}
