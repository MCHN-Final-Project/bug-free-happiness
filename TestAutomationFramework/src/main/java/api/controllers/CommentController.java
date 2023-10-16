package api.controllers;

import api.controllers.models.CommentModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;

public class CommentController extends BaseController {
    UserController userController = new UserController();
    PostController postController = new PostController();

    CommentModel commentModel = new CommentModel();
    ObjectMapper comment = new ObjectMapper();

    public CommentModel createComment(String randomCommentContent, String username, String password) {

        String commentBody = "{\n" +
                "  \"commentId\": 0,\n" +
                "  \"content\": \"" + randomCommentContent + "\",\n" +
                "  \"deletedConfirmed\": true,\n" +
                "  \"postId\": " + getLatestPost(postController.getAllPost()) + ",\n" +
                "  \"userId\": " + getUserId(userController.getAllUsers()) + "\n" +
                "}";

        Response response = getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig("/authenticate", "username", "password"))
                .body(commentBody)
                .when()
                .post("/api/comment/auth/creator")
                .then().statusCode(200)
                .extract().response();

        String responseBody = response.asString();
        try {
            commentModel = comment.readValue(responseBody, CommentModel.class);
        } catch (JsonProcessingException ignored) {
        }
        return commentModel;
    }

    public Response likeComment(int commentId, String username, String password) {

        return getRestAssured()
                .auth()
                .form(username, password,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("commentId", commentId)
                .when()
                .post("/api/comment/auth/likesUp?commentId=" + commentId)
                .then().statusCode(200)
                .extract().response();
    }

    public void editComment(int commentId, String username, String password) {

        getRestAssured()
                .auth()
                .form(username, password,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("commentId", commentId)
                .queryParam("content", EDIT_CONTENT)
                .accept("application/json")
                .when()
                .put("/api/comment/auth/editor?commentId=" + commentId + "&content=" + EDIT_CONTENT)
                .then().statusCode(200)
                .extract().response();
    }

    public Response getCreatedComment(String username, String password) {

        return getRestAssured()
                .queryParam("commentId", getLatestCommentId(getAllCommentsInPost(username, password)))
                .when()
                .get("/api/comment/single?commentId=" + getLatestCommentId(getAllCommentsInPost(username, password)))
                .then().statusCode(200)
                .extract().response();
    }

    public Response getAllCommentsInPost(String username, String password) {

        return getRestAssured()
                .auth()
                .form(username, password, new FormAuthConfig("/authenticate", "username", "password"))
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

    public void deleteComment(String username, String password) {

        getRestAssured()
                .auth()
                .form(username, password,
                        new FormAuthConfig("/authenticate", "username", "password"))
                .queryParam("commentId", getLatestCommentId(getAllCommentsInPost(username, password)))
                .when()
                .delete("/api/comment/auth/manager?commentId=" + getLatestCommentId(getAllCommentsInPost(username, password)))
                .then().statusCode(200)
                .extract().response();
    }
}

