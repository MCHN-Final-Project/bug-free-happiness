package test.cases.apiTests;

import api.controllers.PostController;
import api.controllers.UserController;
import org.junit.jupiter.api.Test;

public class UserControllerTests {
    UserController userController = new UserController();
    PostController postController = new PostController();
    @Test
    public void registerUser_createPost(){
        userController.createUser();
        userController.authenticateUser();
        userController.getAllUsers();
        postController.createPublicPost();
        postController.getAllUsersPosts();
        postController.deletePost();

    }
}
