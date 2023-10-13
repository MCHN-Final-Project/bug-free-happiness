package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;
import weare.ui.pagemodels.*;
import weare.ui.pagemodels.models.UserData;
import weare.ui.pagemodels.models.UserModelForUi;

public class BaseTest {
    static BaseController baseController = new BaseController();
    static UserModelForUi userModelForUi = new UserModelForUi();
    static UserActions actions = new UserActions();
    //
    static UserController userController = new UserController();
    //static HomePage homePage = new HomePage(actions.getDriver());
    //AdminPage adminPage = new AdminPage(actions.getDriver());
    //CommentPage commentPage = new CommentPage(actions.getDriver());
    //CreatePostPage createPostPage = new CreatePostPage(actions.getDriver());
    //DeleteCommentPage deleteCommentPage = new DeleteCommentPage(actions.getDriver());
    //EditCommentPage editCommentPage = new EditCommentPage(actions.getDriver());
    //LatestPostPage latestPostPage = new LatestPostPage(actions.getDriver());
    //LoginPage loginPage = new LoginPage(actions.getDriver());
    //ProfilePage profilePage = new ProfilePage(actions.getDriver());
    static UserModel userModel = new UserModel();
    static UserData userData = new UserData();

    @BeforeAll
    public static void setup() {

        //homePage.navigateToPage();
    }

    @AfterAll
    public static void cleanup() {
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModelForUi.userId);
    }
}
