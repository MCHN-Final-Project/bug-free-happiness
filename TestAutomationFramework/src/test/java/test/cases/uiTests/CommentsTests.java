package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.CommentPage;
import weare.ui.pagemodels.CreatePostPage;
import weare.ui.pagemodels.HomePage;
import weare.ui.pagemodels.LatestPostPage;

public class CommentsTests extends BaseTest{
    CreatePostPage createPostPage = new CreatePostPage(actions.getDriver());
    BaseController baseController = new BaseController();
    LatestPostPage latestPostPage = new LatestPostPage(actions.getDriver());
    HomePage homePage = new HomePage(actions.getDriver());
    CommentPage commentPage = new CommentPage(actions.getDriver());

    @AfterAll
    public static void cleanUp(){
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModelForUi.userId);
    }

    @Test
    @DisplayName("Create comment below already created post successfully")
    public void createValidCommentBelowPostSuccessfully(){

        actions.waitForElementClickable("home.addNewPostButton");
        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        actions.uploadImage("post.imageFileButton", "src/main/resources/picture.png.png");

        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("latestPost.explorePostButton");
        latestPostPage.clickOnExplorePostButton("latestPost.explorePostButton");

        actions.javaScriptExecutorScrollIntoView("comment.content");
        String commentContent = baseController.getRandomSentence();
        commentPage.enterCommentBody(commentContent);

        commentPage.clickOnPostCommentButton();

        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("comment.commentExistingAssertion"), commentContent));
    }
}
