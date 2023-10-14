package test.cases.uiTests;

import api.controllers.helpers.SqlMethods;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import weare.ui.pagemodels.*;

public class DeletePostTests extends BaseTest{
    LatestPostPage latestPostPage = new LatestPostPage(actions.getDriver());
    CreatePostPage createPostPage = new CreatePostPage(actions.getDriver());
    HomePage homePage = new HomePage(actions.getDriver());

    DeletePostPage deletePostPage = new DeletePostPage(actions.getDriver());

    @AfterAll
    public static void cleanUp(){
        UserActions.quitDriver();
        SqlMethods.deleteUserById("user_id", userModelForUi.userId);
    }

    @Test
    @DisplayName("Delete already created post successfully")
    public void deletePostSuccessfully(){

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
