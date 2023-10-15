package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.ConnectionController;
import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {
    UserActions actions = new UserActions();

    public ProfilePage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.homepage", false);
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

    public void enterBirthDay(String key) {
        actions.selectDate(key);
    }

    public void selectGender(String input, int output) {
        actions.selectFromDropdown(Utils.getUIMappingByKey("profile.inputGender"), input, output);
    }

    public void enterEmail() {
        driver.findElement(By.xpath(Utils.getUIMappingByKey("profile.inputEmail"))).clear();
        actions.typeValueInField(BaseController.faker.internet().emailAddress(), "profile.inputEmail");
    }

    public void enterBio() {
        actions.typeValueInField(BaseController.faker.lorem().sentence(), "profile.inputInfo");
    }

    public void selectCity(String input, int output) {
        actions.selectFromDropdown(Utils.getUIMappingByKey("profile.inputCity"), input, output);
    }
    public void updateProfession (String input, int output) {
        actions.selectFromDropdown(Utils.getUIMappingByKey("profile.profession"), input, output);
    }

    public void updateProfile() {
        actions.clickElement("profile.updateButton");
    }

    public void sendConnectionRequest(int targetUser) {
        actions.getDriver()
                .get(String.format(Utils.getConfigPropertyByKey("weAreSocialNetwork.profile"), targetUser));
        actions.clickElement("connection.sendRequest");
    }
    public void acceptRequest() {
        actions.clickElement("connection.newRequests");
        actions.waitForElementClickable("connection.approveRequest");
        actions.clickElement("connection.approveRequest");
    }
}
