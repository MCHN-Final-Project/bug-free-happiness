package weare.ui.pagemodels;

import api.controllers.BaseController;
import org.openqa.selenium.WebDriver;

public class CreatePostPage extends BasePage {

    BaseController baseController = new BaseController();
    public CreatePostPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.createPost", true);
    }
    public void clickOnNewPostButton(){
        actions.clickElement("post.newPostButton");
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
