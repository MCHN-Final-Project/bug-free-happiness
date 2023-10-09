package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.PostController;
import api.controllers.UserController;
import api.controllers.models.UserModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class UserControllerTests {
    static UserController userController = new UserController();
    PostController postController = new PostController();
    static UserModel user;

    @BeforeAll
    public static void setup() {
        user = userController.createUser(false);
        userController.authenticateUser();
    }
    @Test
    public void registerUser_createPost(){
        userController.getAllUsers();
        postController.createPublicPost(BaseController.faker.lorem().sentence(), BaseController.faker.internet().image());
        postController.getAllUsersPosts();
        postController.deletePost();

    }
    @Test
    public void findUser_UpdateExpertise_UpdateProfile_Flow() {
        Response response  = userController.getUserById(user.id, user.username);

        Assertions.assertEquals(String.valueOf(user.id), response.jsonPath().get("id").toString(),
                "Target user does not equal found user");
        Assertions.assertEquals(user.username, response.jsonPath().get("username").toString(),
                "Target user does not equal found user");
    }
}
