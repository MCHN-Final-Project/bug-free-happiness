package api.controllers;

import api.controllers.models.PostModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;

public class PostController extends BaseController {
    UserController userController = new UserController();

    PostModel postModel = new PostModel();
    ObjectMapper post = new ObjectMapper();

    public PostModel createPublicPost(String randomContent, String randomPicture, String username, String password) {

        String postBody = "{\n" +
                "  \"content\": \"" + randomContent + "\",\n" +
                "  \"picture\": \"" + randomPicture + "\",\n" +
                "  \"public\": true\n" +
                "}";

        Response response = getRestAssured()
                .auth()
                .form(username, password,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .body(postBody)
                .when()
                .post("/api/post/auth/creator")
                .then().statusCode(200)
                .extract().response();

        String responseBody = response.asString();

        try {
            postModel = post.readValue(responseBody, PostModel.class);
        } catch (JsonProcessingException ignored) {}
        return postModel;

    }

    public void editPost(int postId, String username, String password) {
        String randomContent = BaseController.faker.lorem().sentence();
        String randomPicture = BaseController.faker.internet().image();

        String postBody = "{\n" +
                "  \"content\": \"" + randomContent + "\",\n" +
                "  \"picture\": \"" + randomPicture + "\",\n" +
                "  \"public\": true\n" +
                "}";

        getRestAssured()
                .auth()
                .form(username, password,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .body(postBody)
                .when()
                .put(String.format("/api/post/auth/editor?postId=%d", postId))
                .then().statusCode(200);
    }

    public void likePost(int postId, String username, String password) {
        getRestAssured()
                .auth()
                .form(username, password,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .when()
                .post(String.format("/api/post/auth/likesUp?postId=%d", postId))
                .then().statusCode(200)
                .extract().response();
    }

    public Response getAllPost() {

        return getRestAssured()
                .when()
                .get("/api/post/")
                .then().statusCode(200)
                .extract().response();
    }

    public void getAllUsersPosts(String username, String password) {

        String requestBody = "{\n" +
                "  \"index\": 0,\n" +
                "  \"next\": true,\n" +
                "  \"searchParam1\": \"\",\n" +
                "  \"searchParam2\": \"\",\n" +
                "  \"size\": 200\n" +
                "}";

        getRestAssured()
                .auth()
                .form(username, password,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .body(requestBody)
                .when()
                .get("/api/users/" + getUserId(userController.getAllUsers()) + "/posts")
                .then().statusCode(200)
                .extract().response();
    }

    public void deletePost(String username, String password) {
        getRestAssured()
                .auth()
                .form(username, password,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("postId", getLatestPost(getAllPost()))
                .when()
                .delete("/api/post/auth/manager")
                .then().statusCode(200)
                .extract().response();
    }
}




