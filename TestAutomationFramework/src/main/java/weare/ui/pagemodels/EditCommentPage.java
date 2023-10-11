package weare.ui.pagemodels;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class EditCommentPage extends BasePage {
    public EditCommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.editComment");
        navigateToPage();
        assertPageNavigated();
    }

    public void enterEditedCommentContent(String editedContent){
        actions.typeValueInField("editComment.content", editedContent);
    }

    public void clickOnEditCommentButton(){
        actions.clickElement("editComment.editButton");
    }

}