package api.controllers;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.telerikacademy.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;

public class BaseController {
    public static final String USER_PASSWORD = "password";
    public static final String EDIT_CONTENT = "Good job";
    static Faker faker = new Faker();

    public RequestSpecification getRestAssured() {
        Gson deserializer = new Gson();
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("weAreSocialNetwork.homepage"));
    }

    public int getLatestPost(Response response) {
        JSONArray jsonArray = new JSONArray(response.body().asString());

        int postId = 0;
        for (int i = 0; i <= jsonArray.length() - 1; i++) {
            if (jsonArray.getJSONObject(i).getInt("postId") > postId) {
                postId = jsonArray.getJSONObject(i).getInt("postId");
            }
        }
        return postId;
    }

    public String getLatestRegisteredUsername(Response response) {
        JSONArray jsonArray = new JSONArray(response.body().asString());
        String username = jsonArray.getJSONObject(0).getString("username");

        return username;
    }

    public int getLatestCommentId(Response response) {
        JSONArray jsonArray = new JSONArray(response.body().asString());
        int commentId = jsonArray.getJSONObject(0).getInt("commentId");

        return commentId;
    }

    public int getUserId(Response response) {
        JSONArray jsonArray = new JSONArray(response.body().asString());
        int userId = jsonArray.getJSONObject(0).getInt("userId");
        return userId;
    }

}
