package test.cases.uiTests;

import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.ProfilePage;
import weare.ui.pagemodels.SearchPage;

import java.util.NoSuchElementException;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static java.lang.String.format;

public class SearchTests extends BaseTest {

    public static SearchPage searchPage = new SearchPage(actions.getDriver());

    private static String lastName;

    @BeforeAll
    public static void setUp() {
        lastName = userData.username + "son";
        ProfilePage profilePage = new ProfilePage(actions.getDriver());
        profilePage.navigateToProfileEdit();
        profilePage.enterFirstName(userData.username);
        profilePage.enterLastName(lastName);
        profilePage.enterBirthDay(20202000);
        profilePage.enterBio();
        profilePage.updateProfile();
        profilePage.updateProfessionalCategory("Pilot");

    }

    @BeforeEach
    public void localSetup(){
        searchPage.navigateToPage();

    }

    @AfterEach
    public void clearSearchFields() {
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

        searchPage.enterProfession("Pilot");
        searchPage.clickOnSearchButton();

        try {
        actions.assertElementPresent(format(getUIMappingByKey("search.userCategory"), "Pilot"));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Search by user professional category unsuccessful");
        }

    }

    @Test
    @DisplayName("Search for existing user by first name successfully")
    public void searchForExistingUserByUserFirstNameSuccessfully() {

        searchPage.enterUsersName(userData.username);
        searchPage.clickOnSearchButton();

        try {
        actions.assertElementPresent(format(getUIMappingByKey("search.userName"), userData.username));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Search for existing user by first name unsuccessful");
        }

    }

    @Test
    @DisplayName("Search for existing user by full name successfully")
    public void searchForExistingUserByUserFullNameSuccessfully() {

        searchPage.enterUsersName(userData.username + " " + lastName);
        searchPage.clickOnSearchButton();

        try {
            actions.assertElementPresent(format(getUIMappingByKey("search.userName"), userData.username+ " " + lastName));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Search for existing user by last name unsuccessful");
        }
    }
}
