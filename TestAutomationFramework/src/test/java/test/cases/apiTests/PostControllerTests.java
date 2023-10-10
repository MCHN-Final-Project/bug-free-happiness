package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.CommentController;
import api.controllers.PostController;
import api.controllers.UserController;
import api.controllers.models.CommentModel;
import api.controllers.models.PostModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void local_Setup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoSetup"))
            return;
        create_Post_With_Valid_Data_Success();
    }

    @AfterEach
    public void local_Cleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup"))
            return;
        delete_Post_When_Post_Exists_Success();
    }

    @Test
    @Tag("NoSetup")
    @DisplayName("Create post successfully")
    public void create_Post_With_Valid_Data_Success() {
        String randomContent = BaseController.faker.lorem().sentence();
        String randomPicture = BaseController.faker.internet().image();

        post = postController.createPublicPost(randomContent, randomPicture);

        String content = post.content;
        String picture = post.picture;

        Assertions.assertEquals(content, randomContent, "Content does not match");
        Assertions.assertEquals(picture, randomPicture, "Picture does not match");
    }

    @Test
    @DisplayName("Edit a post successfully")
    public void edit_Post_With_Valid_Data_Success() {
        String content = postController.getAllPost().jsonPath().get("[0].content");

        postController.editPost(post.postId);

        Assertions.assertNotSame
                (content, post.content, "Post contents not edited");
    }

    @Test
    @DisplayName("Like a post successfully")
    public void like_Post_When_Post_Exists_Success() {
        postController.likePost(post.postId);

        Assertions.assertNotNull(post.likes, "Post has no likes");
    }

    @Test
    @Tag("NoCleanup")
    @DisplayName("Delete a post successfully")
    public void delete_Post_When_Post_Exists_Success() {
        postController.deletePost();

        Assertions.assertNotEquals
                (post.postId, postController.getLatestPost(postController.getAllPost()), "Post not deleted");
    }

    @Test
    @DisplayName("Get all existing posts")
    public void view_All_Posts() {
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
    public void view_Comments_For_Post() {
        String randomCommentContent = BaseController.faker.lorem().sentence();
        comment = commentController.createComment(randomCommentContent);

        String commentId = String.format("[%d]", comment.commentId);

        Assertions.assertEquals(commentId, commentController.getAllCommentsInPost().jsonPath().get("commentId").toString(),
                "Displayed comment does not match created comment");
    }
}