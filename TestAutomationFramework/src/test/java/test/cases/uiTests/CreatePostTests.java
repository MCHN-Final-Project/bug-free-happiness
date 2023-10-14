package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.CreatePostPage;
import weare.ui.pagemodels.HomePage;

public class CreatePostTests extends BaseTest{
    CreatePostPage createPostPage = new CreatePostPage(actions.getDriver());
    BaseController baseController = new BaseController();

    HomePage homePage = new HomePage(actions.getDriver());

    @AfterEach
    public void cleanUp(){
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModelForUi.userId);
    }

    @Test
    @DisplayName("Create public post successfully")
    public void createPublicPostWithValidDataSuccessfully(){

        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        actions.uploadImage("post.imageFileButton", "src/main/resources/picture.png.png");

        createPostPage.clickOnSaveButton();

        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("post.postExistingAssertion"), postContent));
        actions.assertElementPresent("post.publicPostAssertion");
    }

    @Test
    @DisplayName("Create private post successfully")
    public void createPrivatePostWithValidDataSuccessfully(){
        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Private post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        actions.uploadImage("post.imageFileButton", "src/main/resources/picture.png.png");

        createPostPage.clickOnSaveButton();

        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("post.postExistingAssertion"), postContent));
        actions.assertElementPresent("post.privatePostAssertion");
    }
}
