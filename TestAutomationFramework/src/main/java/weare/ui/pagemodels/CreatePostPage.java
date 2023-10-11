package weare.ui.pagemodels;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class CreatePostPage extends BasePage {
    public CreatePostPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.createPost");
        navigateToPage();
        assertPageNavigated();
    }

    public void clickOnNewPostButton(){
        actions.clickElement("page.newPostButton");
    }

    public void clickOnPostVisibilityButton(){
        actions.clickElement("page.choosePostVisibilityButton");
    }

    public void enterPostBody(String postBody){
        actions.clickElement(postBody, "page.postContent");
    }

    public void clickOnImageButton(){
        actions.clickElement("page.imageFileButton");
    }

    public void clickOnSaveButton(){
        actions.clickElement("page.saveButton");
    }

}
