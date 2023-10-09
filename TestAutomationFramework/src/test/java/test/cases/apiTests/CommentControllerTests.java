package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.CommentController;
import api.controllers.PostController;
import api.controllers.UserController;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentControllerTests {
    PostController postController = new PostController();
    CommentController commentController = new CommentController();
    UserController userController = new UserController();
    @Test
    @Order(1)
    public void createValidComment_when_UserIsLoggedIn() {
        userController.createUser(false);
        userController.authenticateUser();
        postController.createPublicPost(BaseController.faker.lorem().sentence(), BaseController.faker.internet().image());
        commentController.createComment();
        commentController.getCreatedComment();
    }
    @Test
    @Order(2)
    public void likeComment_which_CorrectlyCreated() {
        commentController.likeComment();
        commentController.getCreatedComment();
    }
    @Test
    @Order(3)
    public void editComment_which_CorrectlyCreated() {
        commentController.editComment();
        commentController.getCreatedComment();
    }
    @Test
    @Order(4)
    public void deleteCommentAndPost_which_CorrectlyCreated() {
        commentController.deleteComment();
        commentController.getAllCommentsInPost();
        commentController.getAllCommentsInApp();
        postController.deletePost();
        postController.getAllUsersPosts();
    }
}
