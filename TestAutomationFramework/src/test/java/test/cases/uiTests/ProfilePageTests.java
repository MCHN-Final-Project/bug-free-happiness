package test.cases.uiTests;

import api.controllers.UserController;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.ProfilePage;

public class ProfilePageTests {
    UserActions actions = new UserActions();
    ProfilePage profilePage = new ProfilePage(actions.getDriver());
    UserController userController = new UserController();

    @Test
    public void test() {
        profilePage.navigateToProfileEdit();
        profilePage.enterFirstName();
        profilePage.enterBio();
    }
}
