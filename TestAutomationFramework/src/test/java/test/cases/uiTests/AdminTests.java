package test.cases.uiTests;

import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.AdminPage;
import weare.ui.pagemodels.models.UserData;

import static weare.ui.pagemodels.BasePage.userModel;

public class AdminTests {

    UserActions actions = new UserActions();
    AdminPage adminPage = new AdminPage(actions.getDriver());

    @BeforeAll
    public static void setUpUser() {
        UserData regularUser = new UserData();
        UserController userController = new UserController();
        UserModel regularUserModel = userController.createUser(regularUser.username, regularUser.password, regularUser.email, false);
    }

    @BeforeEach
    public void setUp() {
        actions.getDriver().get(String.format
                (Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), userModel.id));
        adminPage.assertPageNavigated();
    }

    @Test
    public void disableUserSuccessfully() {

//        adminPage.navigateToUserList();
//        adminPage.chooseUserById(regularUserId);

    }

}
