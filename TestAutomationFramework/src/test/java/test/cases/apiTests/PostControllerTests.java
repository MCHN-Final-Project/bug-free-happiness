package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.CommentController;
import api.controllers.PostController;
import api.controllers.UserController;
import api.controllers.models.CommentModel;
import api.controllers.models.PostModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class PostControllerTests {
    PostController postController = new PostController();
    CommentController commentController = new CommentController();
    static UserController userController = new UserController();
    PostModel post;
    CommentModel comment;

    @BeforeAll
    public static void setup() {
        userController.createUser(false);
        userController.authenticateUser();
    }

    @BeforeEach
    public void local_setup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoSetup"))
            return;
        create_Post_With_Valid_Data();
    }

    @AfterEach
    public void local_cleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup"))
            return;
        delete_Post_When_Post_Exists();
    }

    @Test
    @Tag("NoSetup")
    public void create_Post_With_Valid_Data() {
        String randomContent = BaseController.faker.lorem().sentence();
        String randomPicture = BaseController.faker.internet().image();

        post = postController.createPublicPost(randomContent, randomPicture);

        String content = post.content;
        String picture = post.picture;

        Assertions.assertEquals(content, randomContent, "Content does not match");
        Assertions.assertEquals(picture, randomPicture, "Picture does not match");
    }

    @Test
    public void edit_Post_With_Valid_Data() {
        String content = postController.getAllPost().jsonPath().get("[0].content");

        postController.editPost(post.postId);

        Assertions.assertNotSame
                (content, post.content, "Post contents not edited");
    }

    @Test
    public void like_Post_When_Post_Exists() {
        postController.likePost(post.postId);

        Assertions.assertNotNull(post.likes, "Post has no likes");
    }

    @Test
    @Tag("NoCleanup")
    public void delete_Post_When_Post_Exists() {
        postController.deletePost();

        Assertions.assertNotEquals
                (post.postId, postController.getLatestPost(postController.getAllPost()), "Post not deleted");
    }

    @Test
    public void View_All_Posts() {
        ArrayList<Object> posts = postController.getAllPost().jsonPath().get("$");

        for (Object instance : posts) {
            try {
            String json = new ObjectMapper().writeValueAsString(instance);
            post = new ObjectMapper().readValue(json, PostModel.class);
            } catch (JsonProcessingException ignored) {}
            Assertions.assertNotNull(post.content, String.format("Post %d content is missing", post.postId));
            Assertions.assertNotNull(post.picture, String.format("Post %d picture is missing", post.postId));
        }
    }

    @Test
    public void View_Comments_For_Post() {
        comment = commentController.createComment();

        String commentId = String.format("[%d]", comment.commentId);

        Assertions.assertEquals(commentId, commentController.getAllCommentsInPost().jsonPath().get("commentId").toString(),
                "Displayed comment does not match created comment");
    }
}