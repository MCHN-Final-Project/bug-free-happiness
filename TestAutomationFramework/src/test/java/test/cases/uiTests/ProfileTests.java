package test.cases.uiTests;

import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import api.controllers.models.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.ProfilePage;
import weare.ui.pagemodels.models.UserData;

import java.sql.Time;
import java.time.LocalTime;

import static weare.ui.pagemodels.BasePage.userData;
import static weare.ui.pagemodels.BasePage.userModel;

public class ProfileTests {
    UserActions actions = new UserActions();
    UserController userController = new UserController();
    ProfilePage profilePage = new ProfilePage(actions.getDriver());

    @BeforeEach
    @Tag("TestSet:Q5FP-2/Q5FP-28")
    public void setup() {
        actions.getDriver().get(String.format
                (Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), userModel.id));
        profilePage.assertPageNavigated();
    }

    @AfterEach
    public void cleanup() {
        SqlMethods.deleteUserById("user_id", userModel.id);
    }

    @AfterAll
    public static void quitBrowser() {
        UserActions.quitDriver();
    }

    @Test
    @DisplayName("Navigate back to user profile")
    public void navigateToProfileFromEditPageSuccessfully() {
        profilePage.navigateToProfileEdit();

        actions.clickElement("home.personalProfileButton");
        actions.assertElementPresent("profile.editButton");
        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("profile.userChecker"),
                userModel.username));

    }

    @Test
    @DisplayName("Update profile with valid data")
    public void profileUpdateWithValidDataSuccessfully() {
        profilePage.navigateToProfileEdit();

        profilePage.enterFirstName();
        profilePage.enterLastName();
        profilePage.enterBirthDay("profile.inputBirthDay");
        profilePage.selectGender("MALE", 1);
        profilePage.enterEmail();
        profilePage.enterBio();
        profilePage.selectCity("Sofia", 1);
        profilePage.updateProfile();
        actions.clickElement("home.personalProfileButton");
        String newUserData = userController.getUserById
                (userModel.id, userData.username).asString();
        try {
            userModel = new ObjectMapper().readValue(newUserData, UserModel.class);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
        System.out.println(userModel.id);
        System.out.println(newUserData);
        actions.assertElementPresent(String.format
                (Utils.getUIMappingByKey("profile.userChecker"),
                        userModel.username));
        actions.assertElementPresent(String.format
                (Utils.getUIMappingByKey("profile.nameChecker"),
                        userModel.firstName));
        actions.assertElementPresent(String.format
                (Utils.getUIMappingByKey("profile.emailChecker"),
                        userModel.email));
    }

    @Test
    @DisplayName("Edit professional category")
    public void professionalUpdateSuccessfully() {
        profilePage.navigateToProfileEdit();

        profilePage.updateProfession("All", 5);
        actions.clickElement("profile.updateProf");
        actions.assertElementPresent("profile.profCheck");
    }

    @Test
    @DisplayName("Add skills to profile")
    public void skillsUpdatedSuccessfully() {
        profilePage.navigateToProfileEdit();

        for (int i = 1; i < 6; i++) {
            actions.typeValueInField
                    ("TestSkill " + i + Time.valueOf(LocalTime.now()),
                            String.format(Utils.getUIMappingByKey("profile.skillInput"), i));
        }
        actions.typeValueInField("5", "profile.updateAvailability");
        actions.clickElement("profile.updateSkills");

        actions.assertElementPresent("profile.skillCheck");
    }

    @Test
    @DisplayName("Send and receive a friend request")
    public void usersConnectedSuccessfully() {
        UserData recCreate = new UserData();
        UserModel recipient = userController
                .createUser(recCreate.username, recCreate.password, recCreate.email, false);

        profilePage.sendConnectionRequest(recipient.id);
        actions.assertElementPresent("connection.verifySent");

        actions.getDriver().manage().deleteAllCookies();
        actions.getDriver().manage().addCookie(recipient.cookie);
        actions.getDriver()
                .get(String.format(Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), recipient.id));
        profilePage.acceptRequest();
        actions.waitForElementClickable("profile.backToProfile");
        actions.clickElement("profile.backToProfile");

        actions.waitForElementVisible("profile.friendCount");
        actions.assertElementPresent("profile.friendCount");

        SqlMethods.TruncateRequests();
        SqlMethods.TruncateConnections();
    }
}
