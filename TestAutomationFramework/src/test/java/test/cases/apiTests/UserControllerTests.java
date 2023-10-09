package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.PostController;
import api.controllers.UserController;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserControllerTests {
    static UserController userController = new UserController();
    PostController postController = new PostController();
    static String[] user;

    @BeforeAll
    public static void setup() {
        user = userController.createUser(true);
        userController.authenticateUser();
    }
    @Test
    public void registerUser_createPost(){
        userController.createUser(false);
        userController.authenticateUser();
        userController.getAllUsers();
        postController.createPublicPost(BaseController.faker.lorem().sentence(), BaseController.faker.internet().image());
        postController.getAllUsersPosts();
        postController.deletePost();

    }
    @Test
    public void findUser_UpdateExpertise_UpdateProfile_Flow() {
        Response response  = userController.getUserById(Integer.parseInt(user[1]), user[0]);

        Assertions.assertEquals(user[1], response.jsonPath().get("id").toString(),
                "Target user does not equal found user");
        Assertions.assertEquals(user[0], response.jsonPath().get("username").toString(),
                "Target user does not equal found user");
    }
}
