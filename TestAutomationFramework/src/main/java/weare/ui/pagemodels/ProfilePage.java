package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.Utils;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserData;

public class ProfilePage extends BasePage {
UserController userController = new UserController();
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
        actions.typeValueInField(BaseController.faker.name().firstName(),"profile.inputFirstName");
    }
    public void enterLastName() {
        actions.typeValueInField(BaseController.faker.name().lastName(),"profile.inputLastName");
    }
    public void enterBirthDay() {
        actions.typeValueInField(BaseController.faker.date().birthday().toString(), "profile.inputBirthDay");
    }
}
