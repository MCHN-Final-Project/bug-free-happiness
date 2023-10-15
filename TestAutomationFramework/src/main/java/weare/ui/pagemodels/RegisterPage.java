package weare.ui.pagemodels;

import api.controllers.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserModelForUi;

import java.util.ArrayList;
import java.util.Objects;

public class RegisterPage extends BasePage {
    UserController userController = new UserController();
    UserModelForUi user = new UserModelForUi();

    public RegisterPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.register", true);
        navigateToPage();
        assertPageNavigated();
    }

    public void enterUsername(String username) {
        actions.typeValueInField(username, "register.username");
    }

    public void enterEmail(String email) {
        actions.typeValueInField(email, "register.email");
    }

    public void enterPasswordInBothPasswordFields(String password) {
        actions.typeValueInField(password, "register.password");
        actions.typeValueInField(password, "register.confirmPassword");
    }

    public void enterPassword(String password) {
        actions.typeValueInField(password, "register.password");
    }

    public void confirmPassword(String password) {
        actions.typeValueInField(password, "register.confirmPassword");
    }

    public UserModelForUi assertUserExists(String username) {
        ArrayList<Object> users = userController.getAllUsers().jsonPath().get("$");
        for (Object instance : users) {
            try {
                String json = new ObjectMapper().writeValueAsString(instance);
                user = new ObjectMapper().readValue(json, UserModelForUi.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(user.username, username)) {
                return user;
            }
        }
        System.out.println(user.username + "not found");
        return null;
    }
}
