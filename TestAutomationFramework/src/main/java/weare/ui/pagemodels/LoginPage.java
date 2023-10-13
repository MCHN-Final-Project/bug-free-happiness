package weare.ui.pagemodels;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.login");
        navigateToPage();
        assertPageNavigated();
    }

    public void enterUsername(String username) {
        actions.typeValueInField(username, "login.username");
    }

    public void enterPassword(String password) {
        actions.typeValueInField(password, "login.password");
    }

    public void submitLoginForm() {
        actions.clickElement("login.loginButton");
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submitLoginForm();
    }
}
