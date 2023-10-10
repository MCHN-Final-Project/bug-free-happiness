package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.PostController;
import api.controllers.UserController;
import api.controllers.models.PostModel;
import api.controllers.models.UserModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

public class UserControllerTests {
    static UserController userController = new UserController();
    PostController postController = new PostController();
    BaseController baseController = new BaseController();
    static UserModel user;
    PostModel post;

    @BeforeAll
    public static void setup() {
        user = userController.createUser(false);
        userController.authenticateUser();
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

        post = postController.createPublicPost(randomContent, randomPicture);

        String content = post.content;
        String picture = post.picture;

        Assertions.assertEquals(content, randomContent, "Content does not match");
        Assertions.assertEquals(picture, randomPicture, "Picture does not match");

        int postId = postController.getAllPost().jsonPath().get("[0].postId");
        postController.getAllUsersPosts();
        Assertions.assertEquals(post.postId, postId, "Post id does not mach");

        postController.deletePost();
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
