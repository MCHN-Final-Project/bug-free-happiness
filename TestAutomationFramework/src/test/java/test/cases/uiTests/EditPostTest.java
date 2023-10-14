package test.cases.uiTests;

import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.*;

public class EditPostTest extends BaseTest{

    LatestPostPage latestPostPage = new LatestPostPage(actions.getDriver());
    EditPostPage editPostPage = new EditPostPage(actions.getDriver());
    CreatePostPage createPostPage = new CreatePostPage(actions.getDriver());
    CommentPage commentPage = new CommentPage(actions.getDriver());
    HomePage homePage = new HomePage(actions.getDriver());

    @AfterAll
    public static void cleanUp(){
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModelForUi.userId);
    }

    @Test
    @DisplayName("Edit already created post successfully")
    public void editPostAsPublicWithValidDataSuccessfully(){

        actions.waitForElementClickable("home.addNewPostButton");
        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        actions.waitForElementClickable("post.saveButton");
        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("latestPost.explorePostButton");
        latestPostPage.clickOnExplorePostButton("latestPost.explorePostButton");

        actions.waitForElementClickable("comment.editPostButton");
        commentPage.clickOnEditPostButton();

        actions.waitForElementClickable("editPost.clickPostVisibilityButton");
        editPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "editPost.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("editPost.postContent");

        String editPostContent = baseController.getRandomSentence();
        editPostPage.enterPostBody(editPostContent);

        actions.uploadImage("editPost.imageFileButton", "src/main/resources/picture.png.png");

        createPostPage.clickOnSaveButton();

        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("editPost.publicPostAssertion"), editPostContent));
    }

    @Test
    @DisplayName("Edit already created post successfully")
    public void editPostAsPrivateWithValidDataSuccessfully(){

        actions.waitForElementClickable("home.addNewPostButton");
        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Private post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        actions.waitForElementClickable("post.saveButton");
        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("latestPost.explorePostButton");
        latestPostPage.clickOnExplorePostButton("latestPost.explorePostButton");

        actions.waitForElementClickable("comment.editPostButton");
        commentPage.clickOnEditPostButton();

        actions.waitForElementClickable("editPost.clickPostVisibilityButton");
        editPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Private post", "editPost.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("editPost.postContent");

        String editPostContent = baseController.getRandomSentence();
        editPostPage.enterPostBody(editPostContent);

        actions.uploadImage("editPost.imageFileButton", "src/main/resources/picture.png.png");

        createPostPage.clickOnSaveButton();

        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("editPost.publicPostAssertion"), editPostContent));
    }
}
