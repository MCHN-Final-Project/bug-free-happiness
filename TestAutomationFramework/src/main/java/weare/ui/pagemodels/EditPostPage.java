package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class EditPostPage extends BasePage{

    public EditPostPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.editPost", true);
    }

    public void clickOnPostVisibilityButton() {
        actions.waitForElementClickable("editPost.clickPostVisibilityButton");
        actions.clickElement("editPost.clickPostVisibilityButton");
    }

    public void enterPostBody(String postBody) {
        actions.typeValueInField(postBody,"editPost.postContent");
    }

    public void clickOnSaveButton(){
        actions.clickElement("editPost.saveButton");
    }
}
