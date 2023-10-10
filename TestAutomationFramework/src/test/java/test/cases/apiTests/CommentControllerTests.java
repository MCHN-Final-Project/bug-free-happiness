package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.CommentController;
import api.controllers.PostController;
import api.controllers.UserController;
import api.controllers.models.CommentModel;
import api.controllers.models.PostModel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentControllerTests {
    static PostController postController = new PostController();
    CommentController commentController = new CommentController();
    static UserController userController = new UserController();
    CommentModel comment;
    PostModel post;

    @BeforeAll
    public static void setup() {
        userController.createUser(false);
        userController.authenticateUser();
    }
    @BeforeEach
    public void local_Setup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoSetup") && !testInfo.getDisplayName().contains("Create comment successfully."))
            return;

        postController.createPublicPost(BaseController.faker.lorem().sentence(), BaseController.faker.internet().image());
        create_Comment_With_Valid_Data_Success();
    }
    @AfterEach
    public void local_Cleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup"))
            return;
        delete_Comment_And_Post_When_Post_Exist_Successfully();

    }
    @Test
    @Tag("NoSetup")
    @DisplayName("Create comment successfully.")
    public void create_Comment_With_Valid_Data_Success() {
        postController.createPublicPost(BaseController.faker.lorem().sentence(), BaseController.faker.internet().image());

        String randomCommentContent = BaseController.faker.lorem().sentence();
        comment = commentController.createComment(randomCommentContent);
        String content = comment.content;

        Assertions.assertEquals(content, randomCommentContent, "Content of comment does not mach");
    }

    @Test
    @DisplayName("Get last created comment.")
    public void view_Created_SuccessfullyComment(){
        Response response = commentController.getCreatedComment();

        JsonPath responseBody = response.jsonPath();
        int assertCommentId = responseBody.getInt("commentId");
        Assertions.assertEquals(comment.commentId, assertCommentId, "Comment id does not mach");
    }

    @Test
    @DisplayName("Get all comments in post.")
    public void view_All_Comments_InPost() {
        Response response = commentController.getAllCommentsInPost();

        JsonPath responseBody = response.jsonPath();
        String actualCommentIdStringWithBrackets = responseBody.getString("commentId");
        String actualCommentIdString = actualCommentIdStringWithBrackets.replaceAll("\\[|\\]", "");
        int actualCommentId = Integer.parseInt(actualCommentIdString);

        Assertions.assertEquals(comment.commentId, actualCommentId, "Comment id does not match");
    }
    @Test
    @DisplayName("Get all comments in app.")
    public void view_All_Comments_InApp() {
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
    public void likeComment_When_Comment_Exists_Successfully() {

        Response response = commentController.likeComment(comment.commentId);
        boolean isLiked = response.jsonPath().getBoolean("liked");
        Assertions.assertTrue(isLiked, "The comment should be liked");
        Assertions.assertNotNull(comment.likes, "Comment has no likes");
    }

    @Test
    @DisplayName("Edit a comment successfully")
    public void editComment_When_Comment_Exists_Successfully() {
        String commentContent = commentController.getAllCommentsInPost().jsonPath().get("[0].content");
        commentController.editComment(comment.commentId);

        Assertions.assertNotSame
                (commentContent, comment.content, "Comment contents not edited");
    }

    @Test
    @Tag("NoCleanup")
    @DisplayName("Delete a post successfully")
    public void delete_Comment_And_Post_When_Post_Exist_Successfully(){

        if (comment == null) {
            comment = new CommentModel();
        }

        if (post == null) {
            post = new PostModel();
        }
        commentController.deleteComment();
        Assertions.assertNotEquals
                (comment.commentId, commentController.getLatestPost(commentController.getAllCommentsInPost()),
                        "Comment is not deleted");

        postController.deletePost();
        Assertions.assertNotEquals
                (post.postId, postController.getLatestPost(postController.getAllPost()), "Post not deleted");
    }
}
