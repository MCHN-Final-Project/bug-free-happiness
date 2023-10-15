package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class CreatePostPage extends BasePage {

    public CreatePostPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.createPost", true);
    }

    public void clickOnPostVisibilityButton() {
        actions.waitForElementClickable("post.clickPostVisibilityButton");
        actions.clickElement("post.clickPostVisibilityButton");
    }
    public void enterPostBody(String postBody) {
        actions.typeValueInField(postBody,"post.postContent");
    }
    public void clickOnSaveButton(){
        actions.clickElement("post.saveButton");
    }

}
