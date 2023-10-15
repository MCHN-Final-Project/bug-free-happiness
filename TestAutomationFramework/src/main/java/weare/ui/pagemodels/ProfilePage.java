package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.UserController;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {
    UserController userController = new UserController();
    UserActions actions = new UserActions();


    public ProfilePage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.homepage", false);
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

    public void enterFirstName(String name) {
        actions.typeValueInField(name, "profile.inputFirstName");
    }

    public void enterLastName() {
        actions.typeValueInField(BaseController.faker.name().lastName(), "profile.inputLastName");
    }

    public void enterLastName(String name) {
        actions.typeValueInField(name, "profile.inputLastName");
    }

    public void enterBirthDay() {
        //implement locator profile.inputBirthDay
    }
    public void enterBirthDay(int eightFigures) {
        actions.typeValueInField(String.valueOf(eightFigures), "//input[@id=\"birthDayE\"]");    }

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

    public void updateProfessionalCategory(String profession){
        actions.typeValueInField(profession, "//select[@id='category.id']");
        actions.clickElement("(//button[@name='submit' and @class='btn btn-primary'])[2]");
    }
}
