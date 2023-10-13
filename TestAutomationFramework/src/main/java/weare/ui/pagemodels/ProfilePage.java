package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.UserController;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserData;

public class ProfilePage extends BasePage {
    UserController userController = new UserController();
    UserActions actions = new UserActions();

    public UserData userData = new UserData();


    public ProfilePage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.homepage");
        ProfilePage.super.url = String.format
                (Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), userModel.id);
        navigateToPage();
        assertPageNavigated();
    }

    public void navigateToProfileEdit() {
        actions.clickElement("profile.editButton");
    }

    public void enterFirstName() {
        actions.typeValueInField(BaseController.faker.name().firstName(), "profile.inputFirstName");
    }

    public void enterLastName() {
        actions.typeValueInField(BaseController.faker.name().lastName(), "profile.inputLastName");
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
