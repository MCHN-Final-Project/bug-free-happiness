package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class CreatePostPage extends BasePage {
    public CreatePostPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.createPost", false);
        navigateToPage();
        assertPageNavigated();
    }

    public void clickOnNewPostButton(){
        actions.clickElement("post.newPostButton");
    }
    public void clickOnPostVisibilityButton(){
        actions.clickElement("post.clickPostVisibilityButton");
    }

    public void enterPostBody(String postBody){
        actions.clickElement(postBody, "post.postContent");
    }

    public void clickOnImageButton(){
        actions.clickElement("post.imageFileButton");
    }

    public void clickOnSaveButton(){
        actions.clickElement("post.saveButton");
    }

}
