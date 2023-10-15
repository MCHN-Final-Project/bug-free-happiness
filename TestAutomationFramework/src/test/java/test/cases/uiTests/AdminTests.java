package test.cases.uiTests;

import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.AdminPage;
import weare.ui.pagemodels.models.UserData;

public class AdminTests {

    UserActions actions = new UserActions();
    AdminPage adminPage = new AdminPage(actions.getDriver());

   private static int regularUserId;

//    @BeforeAll
//    public static void setUpUser() {
//        UserController userController = new UserController();
//        UserData regularUser = new UserData();
//        UserModel regularUserModel = userController.createUser(regularUser.username, regularUser.password, regularUser.password, false);
//        regularUserId = regularUserModel.id;
//    }

    @Test
    public void disableUserSuccessfully() {

        adminPage.navigateToUserList();
        adminPage.chooseUserById(regularUserId);

    }

}
