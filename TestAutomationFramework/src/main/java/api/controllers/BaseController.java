package api.controllers;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.telerikacademy.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;

import static org.asynchttpclient.util.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseController {
    public static final String USER_PASSWORD = "password";
    public static final String EDIT_CONTENT = "Good job";
    public static Faker faker = new Faker();

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

    public static int getRequestId(Response response) {
        JsonPath jsonPath = response.getBody().jsonPath();
        return jsonPath.getInt("[0].id");
    }

    public static String getRandomSentence(){
        return faker.lorem().sentence(5);
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomUsername() {
        return faker.name().firstName();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals(actualStatusCode, expectedStatusCode, "Incorrect status code.");
    }

    public static void assertResponseBodyIsNotEmpty(Response response) {
        String responseBody = response.getBody().asString();
        assertNotNull(responseBody, "Response body is empty.");
    }

    public static void assertResponseIsArrayAndNotEmpty(Response response) {
        assertTrue(response.getBody().jsonPath().getList("$").size() > 0,
                "The response is not an array, or the array is empty.");
    }

}
