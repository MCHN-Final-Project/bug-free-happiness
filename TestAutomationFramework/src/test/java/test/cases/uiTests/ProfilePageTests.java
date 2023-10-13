package test.cases.uiTests;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.ProfilePage;

public class ProfilePageTests {
    UserActions actions = new UserActions();
    ProfilePage profilePage = new ProfilePage(actions.getDriver());

    @Test
    public void test() {
        profilePage.navigateToProfileEdit();
        profilePage.enterFirstName();
        profilePage.enterBio();
    }
}
