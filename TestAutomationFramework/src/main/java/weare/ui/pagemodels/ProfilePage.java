package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.UserController;
import api.controllers.models.UserModel;
import com.telerikacademy.testframework.Utils;
import com.telerikacademy.testframework.pages.BasePage;
import com.telerikacademy.testframework.UserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        actions.typeValueInField(BaseController.faker.name().firstName(),"profile.inputFirstName");
    }
    public void enterLastName() {
        actions.typeValueInField(BaseController.faker.name().lastName(),"profile.inputLastName");
    }
    public void enterBirthDay() {
        //implement locator profile.inputBirthDay
    }
    public void selectGender() {
        //implement locator profile.inputGender
    }
    public void enterEmail() {
        actions.typeValueInField(BaseController.faker.internet().emailAddress(), "profile.inputEmail");
    }
    public void enterBio() {
        actions.typeValueInField(BaseController.faker.lorem().paragraph(), "profile.inputInfo");
    }
    public void selectCity() {
        //implement locator profile.inputCity
    }
    public void updateProfile() {
        actions.clickElement("profile.updateButton");
    }
}
