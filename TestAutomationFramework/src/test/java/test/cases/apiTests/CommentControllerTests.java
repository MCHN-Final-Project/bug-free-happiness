package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.CommentController;
import api.controllers.PostController;
import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import api.controllers.models.UserModel;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import api.controllers.models.CommentModel;
import api.controllers.models.PostModel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.models.UserData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentControllerTests {
    PostController postController = new PostController();
    CommentController commentController = new CommentController();
    static UserController userController = new UserController();
    static BaseController baseController = new BaseController();
    static UserData userData = new UserData();
    CommentModel comment;
    static UserModel user;
    PostModel post;

    @BeforeAll
    public static void setup() {
        userData.username = baseController.getRandomUsername();
        userData.password = baseController.getRandomPassword();
        userData.email = baseController.getRandomEmail();
        user = userController.createUser(userData.username, userData.password, userData.email, false);
    }

    @BeforeEach
    public void localSetup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoSetup"))
            return;

        createCommentWithValidDataSuccessfully();
    }
    @AfterEach
    public void localCleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup"))
            return;
        deleteCommentAndPostWhenPostExistSuccessfully();
    }

    @AfterAll
    public static void cleanup() {
        SqlMethods.deleteUserById("user_id", user.id);
    }

    @Test
    @Tag("NoSetup")
    @DisplayName("Create comment successfully")
    public void createCommentWithValidDataSuccessfully() {
        post = postController.createPublicPost(BaseController.faker.lorem().sentence(), BaseController.faker.internet().image(),
                userData.username, userData.password);

        String randomCommentContent = BaseController.faker.lorem().sentence();
        comment = commentController.createComment(randomCommentContent, userData.username, userData.password);
        String content = comment.content;

        Assertions.assertEquals(content, randomCommentContent, "Content of comment does not mach");
    }

    @Test
    @DisplayName("Get last created comment")
    public void viewCreatedCommentSuccessfully(){
        Response response = commentController.getCreatedComment(userData.username, userData.password);

        JsonPath responseBody = response.jsonPath();
        int assertCommentId = responseBody.getInt("commentId");
        Assertions.assertEquals(comment.commentId, assertCommentId, "Comment id does not mach");
    }

    @Test
    @DisplayName("Get all comments in post")
    public void viewAllCommentsInPost() {
        Response response = commentController.getAllCommentsInPost(userData.username, userData.password);

        JsonPath responseBody = response.jsonPath();
        String actualCommentIdStringWithBrackets = responseBody.getString("commentId");
        String actualCommentIdString = actualCommentIdStringWithBrackets.replaceAll("\\[|\\]", "");
        int actualCommentId = Integer.parseInt(actualCommentIdString);

        Assertions.assertEquals(comment.commentId, actualCommentId, "Comment id does not match");
    }
    @Test
    @DisplayName("Get all comments in app")
    public void viewAllCommentsInApp() {
        Response response = commentController.getAllCommentsInApp();

        JsonPath responseBody = response.jsonPath();
        String actualCommentIdsStringWithBrackets = responseBody.getString("commentId");
        String actualCommentIdsString = actualCommentIdsStringWithBrackets.replaceAll("\\[|\\]", "");

        String[] commentIdsArray = actualCommentIdsString.split(", ");

        List<Integer> commentIdsList = new ArrayList<>();
        for (String commentId : commentIdsArray) {
            commentIdsList.add(Integer.parseInt(commentId));
        }

        int latestCommentId = Collections.max(commentIdsList);

        Assertions.assertEquals(comment.commentId, latestCommentId, "Latest comment ID does not match");
    }

    @Test
    @DisplayName("Like a comment successfully")
    public void likeCommentWhenCommentExistsSuccessfully() {

        Response response = commentController.likeComment(comment.commentId, userData.username, userData.password);
        boolean isLiked = response.jsonPath().getBoolean("liked");
        Assertions.assertTrue(isLiked, "The comment should be liked");
        Assertions.assertNotNull(comment.likes, "Comment has no likes");
    }

    @Test
    @DisplayName("Edit a comment successfully")
    public void editCommentWhenCommentExistsSuccessfully() {
        String commentContent = commentController.getAllCommentsInPost(userData.username, userData.password).jsonPath().get("[0].content");
        commentController.editComment(comment.commentId, userData.username, userData.password);

        Assertions.assertNotSame
                (commentContent, comment.content, "Comment contents not edited");
    }

    @Test
    @Tag("NoCleanup")
    @DisplayName("Delete a post successfully")
    public void deleteCommentAndPostWhenPostExistSuccessfully(){

        if (comment == null) {
            comment = new CommentModel();
        }

        if (post == null) {
            post = new PostModel();
        }
        commentController.deleteComment(userData.username, userData.password);
        Assertions.assertNotEquals
                (comment.commentId, commentController.getLatestPost(commentController.getAllCommentsInPost(userData.username, userData.password)),
                        "Comment is not deleted");

        postController.deletePost(userData.username, userData.password);
        Assertions.assertNotEquals
                (post.postId, postController.getLatestPost(postController.getAllPost()), "Post not deleted");
    }
}
