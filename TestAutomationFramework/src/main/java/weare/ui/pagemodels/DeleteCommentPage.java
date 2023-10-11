package weare.ui.pagemodels;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class DeleteCommentPage extends BasePage {

    public DeleteCommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.deleteComment");
        navigateToPage();
        assertPageNavigated();
    }

    public void clickOnDropDownMenu(){
        actions.clickElement("deleteComment.dropDownMenu");
    }

    public void clickOnSubmitButton() {
        actions.clickElement("deleteComment.submitButton");
    }

}
