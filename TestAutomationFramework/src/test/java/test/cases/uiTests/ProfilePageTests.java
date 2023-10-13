package test.cases.uiTests;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.LoginPage;
import weare.ui.pagemodels.ProfilePage;

public class ProfilePageTests extends BaseTest {
    @Test
    public void test() {
        profilePage.navigateToProfileEdit();
        profilePage.enterFirstName();
        profilePage.enterBio();
    }
}
