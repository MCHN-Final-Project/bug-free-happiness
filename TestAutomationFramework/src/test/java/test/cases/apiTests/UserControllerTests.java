package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.PostController;
import api.controllers.UserController;
import api.controllers.models.PostModel;
import api.controllers.models.UserModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.models.UserData;

public class UserControllerTests {
    static UserController userController = new UserController();
    PostController postController = new PostController();
    static BaseController baseController = new BaseController();
    static UserModel user;
    static UserData userData = new UserData();
    PostModel post;

    @BeforeAll
    public static void setup() {
        userData.username = baseController.getRandomUsername();
        userData.password = baseController.getRandomPassword();
        userData.email = baseController.getRandomEmail();
        user = userController.createUser(userData.username, userData.password, userData.email,false);
        userController.authenticateUser(userData.username, userData.password);
    }

    @Test
    @DisplayName("Get all existing users in app.")
    public void view_All_Users_In_App() {
        Response response = userController.getAllUsers();
        int finalUserId = baseController.getUserId(response);
        Assertions.assertEquals(user.id, finalUserId, "User ID does not match");
    }

    @Test
    @DisplayName("Get all users posts")
    public void view_All_Users_Posts() {
        String randomContent = BaseController.faker.lorem().sentence();
        String randomPicture = BaseController.faker.internet().image();

        post = postController.createPublicPost(randomContent, randomPicture,
                userData.username, userData.password);

        String content = post.content;
        String picture = post.picture;

        Assertions.assertEquals(content, randomContent, "Content does not match");
        Assertions.assertEquals(picture, randomPicture, "Picture does not match");

        int postId = postController.getAllPost().jsonPath().get("[0].postId");
        postController.getAllUsersPosts(userData.username, userData.password);
        Assertions.assertEquals(post.postId, postId, "Post id does not mach");

        postController.deletePost(userData.username, userData.password);
        Assertions.assertNotEquals
                (post.postId, postController.getLatestPost(postController.getAllPost()), "Post not deleted");
    }

    @Test
    @DisplayName("Update user expertise info with valid data")
    public void findUser_Update_Expertise_With_Valid_Data_Successfully() {
        Response response = userController.getUserById(user.id, user.username);

        Assertions.assertEquals(String.valueOf(user.id), response.jsonPath().get("id").toString(),
                "Target user does not equal found user");
        Assertions.assertEquals(user.username, response.jsonPath().get("username").toString(),
                "Target user does not equal found user");
    }
}
