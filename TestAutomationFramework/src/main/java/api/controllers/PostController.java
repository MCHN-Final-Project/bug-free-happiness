package api.controllers;

import io.restassured.authentication.FormAuthConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostController extends BaseController {
    UserController userController = new UserController();

    public int postId;

    public JsonPath createPublicPost(String randomContent, String randomPicture) {

        String postBody = "{\n" +
                "  \"content\": \"" + randomContent + "\",\n" +
                "  \"picture\": \"" + randomPicture + "\",\n" +
                "  \"public\": true\n" +
                "}";

        Response response = getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .body(postBody)
                .when()
                .post("/api/post/auth/creator")
                .then().statusCode(200)
                .extract().response();

        String responseBody = response.asString();

        return response.jsonPath();

    }

    public void editPost(int postId) {
        String randomContent = BaseController.faker.lorem().sentence();
        String randomPicture = BaseController.faker.internet().image();

        String postBody = "{\n" +
                "  \"content\": \"" + randomContent + "\",\n" +
                "  \"picture\": \"" + randomPicture + "\",\n" +
                "  \"public\": true\n" +
                "}";

        getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .body(postBody)
                .when()
                .put(String.format("/api/post/auth/editor?postId=%d", postId))
                .then().statusCode(200);
    }

    public Response likePost(int postId) {
        return getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD,
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

    public void getAllUsersPosts() {

        String requestBody = "{\n" +
                "  \"index\": 0,\n" +
                "  \"next\": true,\n" +
                "  \"searchParam1\": \"\",\n" +
                "  \"searchParam2\": \"\",\n" +
                "  \"size\": 200\n" +
                "}";

        getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .body(requestBody)
                .when()
                .get("/api/users/" + getUserId(userController.getAllUsers()) + "/posts")
                .then().statusCode(200)
                .extract().response();
    }

    public void deletePost() {
        getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("postId", getLatestPost(getAllPost()))
                .when()
                .delete("/api/post/auth/manager?postId=" + getLatestPost(getAllPost()))
                .then().statusCode(200)
                .extract().response();
    }
}




