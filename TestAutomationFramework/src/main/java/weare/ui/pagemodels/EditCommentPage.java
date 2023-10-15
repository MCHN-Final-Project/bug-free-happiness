package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class EditCommentPage extends BasePage {

    public EditCommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.editComment", false);
    }

    public void enterEditedCommentContent(String editedContent){
        actions.waitForElementClickable("editComment.content");
        actions.typeValueInField(editedContent,"editComment.content");
    }

    public void clickOnEditCommentButton(){
        actions.clickElement("editComment.editButton");
    }

}