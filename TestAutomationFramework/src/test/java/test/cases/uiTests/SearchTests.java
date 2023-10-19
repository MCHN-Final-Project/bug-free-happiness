package test.cases.uiTests;

import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.ProfilePage;
import weare.ui.pagemodels.SearchPage;
import weare.ui.pagemodels.models.UserData;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static java.lang.String.format;
import static weare.ui.pagemodels.BasePage.userData;
import static weare.ui.pagemodels.BasePage.userModel;

public class SearchTests {
    public static UserActions actions = new UserActions();

    public static SearchPage searchPage = new SearchPage(actions.getDriver());

    private static String lastName;
    
    @BeforeAll
    @Tag("TestSet:Q5FP-20")
    public static void setUp() {
        lastName = userData.username + "son";
        ProfilePage profilePage = new ProfilePage(actions.getDriver());
        actions.getDriver().get(String.format
                (Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), userModel.id));
        profilePage.assertPageNavigated();
        profilePage.navigateToProfileEdit();
        profilePage.enterFirstName(userData.username);
        profilePage.enterLastName(lastName);
        profilePage.enterBirthDay("profile.inputBirthDay");
        profilePage.updateProfile();
        profilePage.updateProfession("All", 6);
        actions.clickElement("profile.updateProf");
    }

    @BeforeEach
    public void localSetup() {
        searchPage.navigateToPage();
    }

    @AfterEach
    public void clearSearchFields(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoSearchClear")) return;
        searchPage.clearNameInSearchField();
        searchPage.clearProfessionInSearchField();
    }

    @AfterAll
    public static void deleteUser() {
        SqlMethods.deleteUserById("user_id", userModel.id);
        UserActions.quitDriver();
    }

    @Test
    @DisplayName("Search for existing user by professional category successfully")
    public void searchForExistingUserByProfessionalCategorySuccessfully() {

        searchPage.enterProfession("Baker");
        searchPage.clickOnSearchButton();

        actions.assertElementPresent(format(getUIMappingByKey("search.userCategory"), "Baker"));
    }

    @Test
    @DisplayName("Search for existing user by first name successfully")
    public void searchForExistingUserByUserFirstNameSuccessfully() {

        searchPage.enterUsersName(userData.username);
        searchPage.clickOnSearchButton();

        actions.assertElementPresent(format(getUIMappingByKey("search.userName"), userData.username));
    }

    @Test
    @DisplayName("Search for existing user by full name successfully")
    public void searchForExistingUserByUserFullNameSuccessfully() {

        searchPage.enterUsersName(userData.username + " " + lastName);
        searchPage.clickOnSearchButton();

        actions.assertElementPresent(format(getUIMappingByKey("search.userName"), userData.username + " " + lastName));
    }

    @Test
    @Tag("NoSearchClear")
    @DisplayName("Search for existing user by different professional category unsuccessfully")
    public void searchForExistingUserByDifferentProfessionalCategoryUnsuccessfully() {

        searchPage.enterProfession("Tailor");
        searchPage.clickOnSearchButton();

        actions.assertElementPresent("search.noUsersMessage");
    }

    @Test
    @Tag("NoSearchClear")
    @DisplayName("Search for existing user by different name unsuccessfully")
    public void searchForExistingUserByDifferentNameUnsuccessfully() {

        UserData differentUserName = new UserData();

        searchPage.enterUsersName(differentUserName.username);
        searchPage.clickOnSearchButton();

        actions.assertElementPresent("search.noUsersMessage");
    }
}
