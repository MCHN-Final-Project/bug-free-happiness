package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.*;

public class CommentsTests extends BaseTest {
    UserActions actions = new UserActions();
    CreatePostPage createPostPage = new CreatePostPage(actions.getDriver());
    BaseController baseController = new BaseController();
    LatestPostPage latestPostPage = new LatestPostPage(actions.getDriver());
    HomePage homePage = new HomePage(actions.getDriver());
    CommentPage commentPage = new CommentPage(actions.getDriver());
    EditCommentPage editCommentPage = new EditCommentPage(actions.getDriver());

    DeleteCommentPage deleteCommentPage = new DeleteCommentPage(actions.getDriver());

    @BeforeEach
    @Tag("TestSet:Q5FP-32")
    public void homeSetUp() {
        homePage.navigateToPage();
    }

    @AfterAll
    public static void cleanUp() {
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModelForUi.userId);
    }

    @Test
    @DisplayName("Create comment below already created post successfully")
    public void createValidCommentBelowPostSuccessfully() {

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

    @Test
    @DisplayName("Edit already created comment successfully")
    public void editValidCommentSuccessfully() {

        actions.waitForElementClickable("home.addNewPostButton");
        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("latestPost.explorePostButton");
        latestPostPage.clickOnExplorePostButton("latestPost.explorePostButton");

        actions.javaScriptExecutorScrollIntoView("comment.content");
        String commentContent = baseController.getRandomSentence();
        commentPage.enterCommentBody(commentContent);

        commentPage.clickOnPostCommentButton();

        actions.javaScriptExecutorScrollIntoView("comment.showCommentsButton");
        actions.waitForElementClickable("comment.showCommentsButton");
        actions.javaScriptExecutorClick("comment.showCommentsButton");

        actions.waitForElementClickable("comment.editCommentButton");
        commentPage.clickEditCommentButton();

        String editCommentContent = baseController.getRandomSentence();
        editCommentPage.enterEditedCommentContent(editCommentContent);

        actions.waitForElementClickable("editComment.editButton");
        editCommentPage.clickOnEditCommentButton();

        actions.javaScriptExecutorScrollIntoView("comment.showCommentsButton");
        actions.waitForElementClickable("comment.showCommentsButton");
        actions.javaScriptExecutorClick("comment.showCommentsButton");

        actions.assertElementPresent(String.format(Utils.getUIMappingByKey("comment.editCommentAssertion"), editCommentContent));
    }

    @Test
    @DisplayName("Like already created comment successfully")
    public void LikeCommentSuccessfully() {

        actions.waitForElementClickable("home.addNewPostButton");
        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("latestPost.explorePostButton");
        latestPostPage.clickOnExplorePostButton("latestPost.explorePostButton");

        actions.javaScriptExecutorScrollIntoView("comment.content");
        String commentContent = baseController.getRandomSentence();
        commentPage.enterCommentBody(commentContent);

        commentPage.clickOnPostCommentButton();

        actions.javaScriptExecutorScrollIntoView("comment.showCommentsButton");
        actions.waitForElementClickable("comment.showCommentsButton");
        actions.javaScriptExecutorClick("comment.showCommentsButton");


        actions.waitForElementClickable("comment.likeButton");
        commentPage.clickLikeOnButton();

        actions.waitForElementPresent("comment.likesAssert");
        actions.assertElementPresent("comment.likesAssert");
    }

    @Test
    @DisplayName("Dislike already created comment successfully")
    public void DislikeCommentSuccessfully() {

        actions.waitForElementClickable("home.addNewPostButton");
        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("latestPost.explorePostButton");
        latestPostPage.clickOnExplorePostButton("latestPost.explorePostButton");

        actions.javaScriptExecutorScrollIntoView("comment.content");
        String commentContent = baseController.getRandomSentence();
        commentPage.enterCommentBody(commentContent);

        commentPage.clickOnPostCommentButton();

        actions.javaScriptExecutorScrollIntoView("comment.showCommentsButton");
        actions.waitForElementClickable("comment.showCommentsButton");
        actions.javaScriptExecutorClick("comment.showCommentsButton");


        actions.waitForElementClickable("comment.likeButton");
        commentPage.clickLikeOnButton();

        actions.waitForElementClickable("comment.dislikeButton");
        commentPage.clickDislikeButton();

        actions.waitForElementPresent("comment.dislikeAssert");
        actions.assertElementPresent("comment.dislikeAssert");
    }

    @Test
    @DisplayName("Delete comment successfully")
    public void deleteCommentSuccessfully() {

        actions.waitForElementClickable("home.addNewPostButton");
        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("latestPost.explorePostButton");
        latestPostPage.clickOnExplorePostButton("latestPost.explorePostButton");

        actions.javaScriptExecutorScrollIntoView("comment.content");
        String commentContent = baseController.getRandomSentence();
        commentPage.enterCommentBody(commentContent);

        commentPage.clickOnPostCommentButton();

        actions.javaScriptExecutorScrollIntoView("comment.showCommentsButton");
        actions.waitForElementClickable("comment.showCommentsButton");
        actions.javaScriptExecutorClick("comment.showCommentsButton");


        actions.waitForElementClickable("comment.deleteCommentButton");
        commentPage.clickDeleteCommentButton();

        actions.waitForElementClickable("deleteComment.dropDownMenu");
        deleteCommentPage.clickOnDropDownMenu();
        actions.selectValueFromDropdown("Delete comment", "deleteComment.dropDownMenu");

        deleteCommentPage.clickOnSubmitButton();

        actions.assertElementPresent("deleteComment.deleteAssertion");
    }


}
