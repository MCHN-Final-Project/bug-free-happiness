package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.CommentController;
import api.controllers.PostController;
import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import api.controllers.models.CommentModel;
import api.controllers.models.PostModel;
import api.controllers.models.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.models.UserData;

import java.util.ArrayList;

public class PostControllerTests {
    PostController postController = new PostController();
    CommentController commentController = new CommentController();
    static UserController userController = new UserController();
    static BaseController baseController = new BaseController();
    static UserModel user;
    static UserData userData = new UserData();
    PostModel post;
    CommentModel comment;


    @BeforeAll
    public static void setup() {
        userData.username = baseController.getRandomUsername();
        userData.password = baseController.getRandomPassword();
        userData.email = baseController.getRandomEmail();
        user = userController.createUser(userData.username, userData.password, userData.email,false);
    }

    @BeforeEach
    public void localSetup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoSetup"))
            return;
        createPostWithValidDataSuccessfully();
    }

    @AfterEach
    public void localCleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup"))
            return;
        deletePostWhenPostExistsSuccessfully();
    }

    @AfterAll
    public static void cleanup() {
        SqlMethods.deleteUserById("user_id", user.id);
    }

    @Test
    @Tag("NoSetup")
    @DisplayName("Create post successfully")
    public void createPostWithValidDataSuccessfully() {
        String randomContent = BaseController.faker.lorem().sentence();
        String randomPicture = BaseController.faker.internet().image();

        post = postController.createPublicPost(randomContent, randomPicture,
                userData.username, userData.password);

        String content = post.content;
        String picture = post.picture;

        Assertions.assertEquals(content, randomContent, "Content does not match");
        Assertions.assertEquals(picture, randomPicture, "Picture does not match");
    }

    @Test
    @DisplayName("Edit a post successfully")
    public void editPostWithValidDataSuccessfully() {
        String content = postController.getAllPost().jsonPath().get("[0].content");

        postController.editPost(post.postId, userData.username, userData.password);

        Assertions.assertNotSame
                (content, post.content, "Post contents not edited");
    }

    @Test
    @DisplayName("Like a post successfully")
    public void likePostWhenPostExistsSuccessfully() {
        postController.likePost(post.postId, userData.username, userData.password);

        Assertions.assertNotNull(post.likes, "Post has no likes");
    }

    @Test
    @Tag("NoCleanup")
    @DisplayName("Delete a post successfully")
    public void deletePostWhenPostExistsSuccessfully() {
        postController.deletePost(userData.username, userData.password);

        Assertions.assertNotEquals
                (post.postId, postController.getLatestPost(postController.getAllPost()), "Post not deleted");
    }

    @Test
    @DisplayName("Get all existing posts")
    public void viewAllPosts() {
        ArrayList<Object> posts = postController.getAllPost().jsonPath().get("$");

        for (Object instance : posts) {
            try {
                String json = new ObjectMapper().writeValueAsString(instance);
                post = new ObjectMapper().readValue(json, PostModel.class);
            } catch (JsonProcessingException ignored) {
            }
            Assertions.assertNotNull(post.content, String.format("Post %d content is missing", post.postId));
            Assertions.assertNotNull(post.picture, String.format("Post %d picture is missing", post.postId));
        }
    }

    @Test
    @DisplayName("Get all comments for a post")
    public void viewCommentsForPost() {
        String randomCommentContent = BaseController.faker.lorem().sentence();
        comment = commentController.createComment(randomCommentContent, userData.username, userData.password);

        String commentId = String.format("[%d]", comment.commentId);

        Assertions.assertEquals(commentId,
                commentController.getAllCommentsInPost(userData.username, userData.password).jsonPath().get("commentId").toString(),
                "Displayed comment does not match created comment");
    }
}