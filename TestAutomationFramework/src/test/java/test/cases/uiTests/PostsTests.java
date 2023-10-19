package test.cases.uiTests;

import api.controllers.BaseController;
import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.*;

public class PostsTests extends BaseTest {
    UserActions actions = new UserActions();
    CreatePostPage createPostPage = new CreatePostPage(actions.getDriver());
    BaseController baseController = new BaseController();
    HomePage homePage = new HomePage(actions.getDriver());
    CommentPage commentPage = new CommentPage(actions.getDriver());
    LatestPostPage latestPostPage = new LatestPostPage(actions.getDriver());
    EditPostPage editPostPage = new EditPostPage(actions.getDriver());
    DeletePostPage deletePostPage = new DeletePostPage(actions.getDriver());

    @BeforeEach
    @Tag("TestSet:Q5FP-42")
    public void homeSetUp() {
        homePage.navigateToPage();
    }

    @AfterAll
    public static void cleanUp() {
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModelForUi.userId);
    }

    @Test
    @DisplayName("Create public post successfully")
    public void createPublicPostWithValidDataSuccessfully() {

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
    public void createPrivatePostWithValidDataSuccessfully() {
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

    @Test
    @DisplayName("Edit already created public post successfully")
    public void editPostAsPublicWithValidDataSuccessfully() {

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
    @DisplayName("Edit already created private post successfully")
    public void editPostAsPrivateWithValidDataSuccessfully() {

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

    @Test
    @DisplayName("Like post successfully")
    public void LikePostSuccessfully() {

        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        actions.uploadImage("post.imageFileButton", "src/main/resources/picture.png.png");

        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("post.likePostButton");
        latestPostPage.clickOnLikeButton();

        actions.waitForElementPresent("post.likeAssertion");
        actions.assertElementPresent("post.likeAssertion");
    }

    @Test
    @DisplayName("Dislike post successfully")
    public void DislikePostSuccessfully() {

        homePage.clickOnAddNewPostButton();

        actions.waitForElementClickable("post.clickPostVisibilityButton");
        createPostPage.clickOnPostVisibilityButton();
        actions.selectValueFromDropdown("Public post", "post.clickPostVisibilityButton");

        actions.javaScriptExecutorScrollIntoView("post.postContent");

        String postContent = baseController.getRandomSentence();
        createPostPage.enterPostBody(postContent);

        actions.uploadImage("post.imageFileButton", "src/main/resources/picture.png.png");

        createPostPage.clickOnSaveButton();

        actions.waitForElementClickable("post.likePostButton");
        latestPostPage.clickOnLikeButton();

        actions.waitForElementClickable("post.dislikePostButton");
        latestPostPage.clickOnDislikeButton();

        actions.waitForElementPresent("post.dislikeAssertion");
        actions.assertElementPresent("post.dislikeAssertion");
    }

    @Test
    @DisplayName("Delete already created post successfully")
    public void deletePostSuccessfully() {

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

        deletePostPage.clickOnDeleteButton();

        actions.waitForElementClickable("deletePost.deleteDropDownMenuButton");
        deletePostPage.clickOnDropDownButton();
        actions.selectValueFromDropdown("Delete post", "deletePost.deleteDropDownMenuButton");

        deletePostPage.clickOnSubmitButton();

        actions.assertElementPresent("deletePost.deleteAssertion");
    }
}
