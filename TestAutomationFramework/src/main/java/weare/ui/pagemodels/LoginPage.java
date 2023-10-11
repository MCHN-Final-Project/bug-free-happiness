package weare.ui.pagemodels;

import com.telerikacademy.testframework.Utils;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.login");
        navigateToPage();
        assertPageNavigated();
    }

    public void enterUsername(String username) {
        actions.typeValueInField(username, Utils.getUIMappingByKey("login.username"));
    }

    public void enterPassword(String password) {
        actions.typeValueInField(password, Utils.getUIMappingByKey("login.password"));
    }

    public void submitLoginForm() {
        actions.clickElement("login.loginButton");
    }
}
