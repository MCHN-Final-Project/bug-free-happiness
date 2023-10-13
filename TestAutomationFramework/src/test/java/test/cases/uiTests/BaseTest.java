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
    static UserController userController = new UserController();
    static UserModel userModel = new UserModel();
    static UserData userData = new UserData();

}
