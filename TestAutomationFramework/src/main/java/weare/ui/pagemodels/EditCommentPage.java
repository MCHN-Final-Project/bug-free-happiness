package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class EditCommentPage extends BasePage {

    public EditCommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.editComment", false, false);
    }

    public void enterEditedCommentContent(String editedContent){
        actions.typeValueInField("editComment.content", editedContent);
    }

    public void clickOnEditCommentButton(){
        actions.clickElement("editComment.editButton");
    }

}