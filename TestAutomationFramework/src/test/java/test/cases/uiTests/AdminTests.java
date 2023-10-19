package test.cases.uiTests;

import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import weare.ui.pagemodels.AdminPage;
import weare.ui.pagemodels.LoginPage;
import weare.ui.pagemodels.models.UserData;

import java.util.List;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.Utils.getWebDriver;
import static java.lang.String.format;
import static weare.ui.pagemodels.BasePage.userModel;

public class AdminTests {

    UserActions actions = new UserActions();
    AdminPage adminPage = new AdminPage(actions.getDriver());
    static UserModel regularUser = new UserModel();
    static UserData regularUserData = new UserData();
    static String lastName;

    @BeforeAll
    @Tag("TestSet:Q5FP-28/Q5FP-50/Q5FP-109")
    public static void setUpUser() {
        UserController userController = new UserController();
        regularUser = userController.createUser(regularUserData.username, regularUserData.password, regularUserData.email, false);
        lastName = regularUser.username + "son";
    }

    @AfterAll
    public static void clearUpData() {
        SqlMethods.deleteUserById("user_id", userModel.id);
        SqlMethods.deleteUserById("user_id", regularUser.id);
        UserActions.quitDriver();
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {

        actions.getDriver().get(String.format
                (Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), userModel.id));
        adminPage.assertPageNavigated();
        actions.clickElement("admin.adminZone");

        adminPage.navigateToUserList();
        adminPage.chooseUserById(regularUser.id);
        actions.clickElement("profile.editButton");
        adminPage.clearField("profile.inputFirstName");
        actions.typeValueInField(regularUser.username, "profile.inputFirstName");
        adminPage.clearField("profile.inputLastName");
        actions.typeValueInField(lastName, "profile.inputLastName");
        actions.selectDate("profile.inputBirthDay");
        actions.clickElement("profile.updateButton");

        if (testInfo.getTags().contains("PartialSetup")) return;
        actions.clickElement("admin.adminZone");
        adminPage.navigateToUserList();
        adminPage.chooseUserById(regularUser.id);
    }

    @Test
    @Tag("PartialSetup")
    @DisplayName("As admin edit user profile successfully")
    public void asAdminEditUserProfileSuccessfully() {

        actions.clickElement("profile.homeButton");
        actions.waitForElementClickable("home.username");
        actions.typeValueInField(regularUser.username, "home.username");
        actions.waitForElementClickable("home.searchButton");
        actions.clickElement("home.searchButton");

        actions.assertElementPresent(format(getUIMappingByKey("search.userName"), regularUser.username));
    }

    @Test
    @DisplayName("As admin disable user profile successfully")
    public void disableUserProfileSuccessfully() {

        List<WebElement> enableButton = getWebDriver().findElements(By.xpath(getUIMappingByKey("admin.enableProfile")));
        if (!enableButton.isEmpty()) {
            enableButton.get(0).click();
        }

        actions.clickElement("admin.disableProfile");
        actions.clickElement("profile.logOutButton");

        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.login(regularUserData.username, regularUserData.password);

        actions.assertElementPresent("login.wrongUserOrPasswordMessage");
    }

    @Test
    @DisplayName("As admin enable user profile successfully")
    public void enableUserProfileSuccessfully() {

        List<WebElement> disableButton = getWebDriver().findElements(By.xpath(getUIMappingByKey("admin.disableProfile")));
        if (!disableButton.isEmpty()) {
            disableButton.get(0).click();
        }

        actions.clickElement("admin.enableProfile");
        actions.clickElement("profile.logOutButton");

        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.login(regularUserData.username, regularUserData.password);

        actions.assertElementPresent("home.logoutButton");
        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("home.personalProfileAlt"),
                regularUser.id));
    }
}


