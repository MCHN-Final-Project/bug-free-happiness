package weare.ui.pagemodels;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.home");
        navigateToPage();
        assertPageNavigated();
    }


}
