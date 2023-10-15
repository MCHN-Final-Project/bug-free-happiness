package test.cases.uiTests;

import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.Driver;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import weare.ui.pagemodels.AdminPage;
import weare.ui.pagemodels.models.UserData;

import static weare.ui.pagemodels.BasePage.userModel;

public class AdminTests {

    UserActions actions = new UserActions();
    AdminPage adminPage = new AdminPage(actions.getDriver());

   private static int regularUserId;

   @BeforeEach
   public void setUp(){
       actions.getDriver().get(String.format
               (Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), userModel.id));
       adminPage.assertPageNavigated();
   }

//    @BeforeAll
//    public static void setUpUser() {
//        UserController userController = new UserController();
//        UserData regularUser = new UserData();
//        UserModel regularUserModel = userController.createUser(regularUser.username, regularUser.password, regularUser.password, false);
//        regularUserId = regularUserModel.id;
//    }

    @Test
    public void disableUserSuccessfully() {

//        adminPage.navigateToUserList();
//        adminPage.chooseUserById(regularUserId);

    }

}
