package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.Utils;
import com.telerikacademy.testframework.pages.BasePage;
import com.telerikacademy.testframework.UserActions;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserData;

public class ProfilePage extends BasePage {
UserController userController = new UserController();
UserActions actions = new UserActions();

public UserData userData = new UserData();
UserModel userModel;
    public ProfilePage(WebDriver driver) {
        //Cookie cookie = new Cookie("JSESSIONID",
        //        userController.authenticateUser(userData.username, userData.password));
        //driver.manage().addCookie(cookie);
        super(driver, "weAreSocialNetwork.profile");
        userModel = userController.createUser
                (userData.username, userData.password, userData.email, false);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userData.username, userData.password);
        ProfilePage.super.url = String.format
                (Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), userModel.id);
        navigateToPage();
        assertPageNavigated();
    }

    public void navigateToProfileEdit() {
        actions.clickElement("profile.editButton");
    }
    public void enterFirstName() {
        actions.typeValueInField("profile.inputFirstName", BaseController.faker.name().firstName());
    }
    public void enterLastName() {
        actions.typeValueInField("profile.inputLastName", BaseController.faker.name().lastName());
    }
    public void enterBirthDay() {
<<<<<<< Updated upstream
        actions.clickElement("profile.inputBirthDay");
        actions.clickElement("profile.inputBirthDay");
        actions.typeValueInField(BaseController.faker.date().birthday(18, 60).toString(), "profile.inputBirthDay");
=======
        actions.typeValueInField("profile.inputBirthDay", BaseController.faker.date().birthday().toString());
>>>>>>> Stashed changes
    }
}
