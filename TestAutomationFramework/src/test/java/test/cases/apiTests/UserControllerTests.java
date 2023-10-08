package test.cases.apiTests;

import api.controllers.BaseController;
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
        postController.createPublicPost(BaseController.faker.lorem().sentence(), BaseController.faker.internet().image());
        postController.getAllUsersPosts();
        postController.deletePost();

    }
}
