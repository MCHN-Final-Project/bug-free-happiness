package api.controllers;

import api.controllers.models.CommentModel;
import api.controllers.models.PostModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class CommentController extends BaseController {
    UserController userController = new UserController();
    PostController postController = new PostController();

    CommentModel commentModel = new CommentModel();
    ObjectMapper comment = new ObjectMapper();

    public CommentModel createComment() {
        String randomCommentContent = BaseController.faker.lorem().sentence();

        String commentBody = "{\n" +
                "  \"commentId\": 0,\n" +
                "  \"content\": \"" + randomCommentContent + "\",\n" +
                "  \"deletedConfirmed\": true,\n" +
                "  \"postId\": " + getLatestPost(postController.getAllPost()) + ",\n" +
                "  \"userId\": " + getUserId(userController.getAllUsers()) + "\n" +
                "}";

        Response response = getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD, new FormAuthConfig("/authenticate", "username", "password"))
                .body(commentBody)
                .when()
                .post("/api/comment/auth/creator")
                .then().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String commentContent = jsonPath.getString("content");
        Assertions.assertEquals(commentContent, randomCommentContent, "Comment content does not match");

        String responseBody = response.asString();
        try {
            commentModel = comment.readValue(responseBody, CommentModel.class);
        } catch (JsonProcessingException ignored) {}
        return commentModel;
    }

    public Response likeComment() {

        Response response = getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD, new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("commentId", getLatestCommentId(getAllCommentsInPost()))
                .when()
                .post("/api/comment/auth/likesUp?commentId=" + getLatestCommentId(getAllCommentsInPost()))
                .then().statusCode(200)
                .extract().response();

        JsonPath responseBody = response.jsonPath();
        int assertCommentId = responseBody.getInt("commentId");
        boolean isLiked = responseBody.getBoolean("liked");
        Assertions.assertEquals(assertCommentId, getLatestCommentId(getAllCommentsInPost()), "Comment id does not match");
        Assertions.assertTrue(isLiked);

        return response;
    }

    public Response editComment() {

        return getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD, new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("commentId", getLatestCommentId(getAllCommentsInPost()))
                .queryParam("content", EDIT_CONTENT)
                .accept("application/json")
                .when()
                .put("/api/comment/auth/editor?commentId=" + getLatestCommentId(getAllCommentsInPost()) + "&content=" + EDIT_CONTENT)
                .then().statusCode(200)
                .extract().response();
    }

    public Response getCreatedComment() {

        return getRestAssured()
                .queryParam("commentId", getLatestCommentId(getAllCommentsInPost()))
                .when()
                .get("/api/comment/single?commentId=" + getLatestCommentId(getAllCommentsInPost()))
                .then().statusCode(200)
                .extract().response();
    }

    public Response getAllCommentsInPost() {

        return getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD, new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("postId", getLatestPost(postController.getAllPost()))
                .when()
                .get("/api/comment/byPost?postId=" + getLatestPost(postController.getAllPost()))
                .then().statusCode(200)
                .extract().response();
    }

    public Response getAllCommentsInApp() {

        return getRestAssured()
                .when()
                .get("/api/comment")
                .then().statusCode(200)
                .extract().response();
    }

    public Response deleteComment() {

        return getRestAssured()
                .auth()
                .form(getLatestRegisteredUsername(userController.getAllUsers()), USER_PASSWORD, new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("commentId", getLatestCommentId(getAllCommentsInPost()))
                .when()
                .delete("/api/comment/auth/manager?commentId=" + getLatestCommentId(getAllCommentsInPost()))
                .then().statusCode(200)
                .extract().response();
    }
}

