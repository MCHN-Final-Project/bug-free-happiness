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
import static org.junit.jupiter.api.Assertions.*;

public class BaseController {
    public static final String EDIT_CONTENT = "Good job";
    public static Faker faker = new Faker();

    public RequestSpecification getRestAssured() {

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
        return jsonArray.getJSONObject(0).getString("username");
    }

    public int getLatestCommentId(Response response) {
        JSONArray jsonArray = new JSONArray(response.body().asString());
        return jsonArray.getJSONObject(0).getInt("commentId");
    }

    public int getUserId(Response response) {
        JSONArray jsonArray = new JSONArray(response.body().asString());
        return jsonArray.getJSONObject(0).getInt("userId");
    }

    public int getRequestId(Response response) {
        JsonPath jsonPath = response.getBody().jsonPath();
        return jsonPath.getInt("[0].id");
    }

    public String getRandomSentence() {
        return faker.lorem().sentence(25);
    }

    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String getRandomUsername() {
        return faker.name().firstName().concat(faker.name().lastName());
    }

    public String getRandomPassword() {
        return faker.internet().password();
    }

    public void assertStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode, "Incorrect status code.");
    }

    public void assertResponseBodyIsNotEmpty(Response response) {
        String responseBody = response.getBody().asString();
        assertNotNull(responseBody, "Response body is empty.");
    }

    public void assertResponseIsArrayAndNotEmpty(Response response) {
        assertFalse(response.getBody().jsonPath().getList("$").isEmpty(),
                "The response is not an array, or the array is empty.");
    }

}
