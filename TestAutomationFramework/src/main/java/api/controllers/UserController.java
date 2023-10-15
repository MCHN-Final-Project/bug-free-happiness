package api.controllers;

import api.controllers.models.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static api.controllers.helpers.Endpoints.AUTHENTICATE_ENDPOINT;
import static api.controllers.helpers.JSONBodies.GET_ALL_USERS_BODY;

public class UserController extends BaseController {

    UserModel userModel = new UserModel();
    ObjectMapper user = new ObjectMapper();

    public UserModel createUser(String username, String password, String email, Boolean admin) {
        String adm = "";
        if (admin) adm = "admin";
        String createUserBody = "{\n" +
                "  \"authorities\": [\n" +
                "    \"ROLE_USER\"\n" +
                "  ],\n" +
                "  \"category\": {\n" +
                "    \"id\": 102,\n" +
                "    \"name\": \"Actor\"\n" +
                "  },\n" +
                "  \"confirmPassword\": \"" + password + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"username\": \"" + adm + username + "\"\n" +
                "}";

        Response response = getRestAssured()
                .body(createUserBody)
                .when()
                .post("/api/users/")
                .then().statusCode(200)
                .extract()
                .response();

        String responseBody = response.asString();

        Pattern pattern = Pattern.compile("User with name (\\w+) and id (\\d+)");
        Matcher matcher = pattern.matcher(responseBody);

        String[] userInfo = new String[2];

        if (matcher.find()) {
            String name = matcher.group(1);
            String id = matcher.group(2);

            userInfo[0] = name;
            userInfo[1] = id;
        } else {
            System.out.println("Pattern not found in the input string.");
        }

        String userData = getUserById(Integer.parseInt(userInfo[1]), userInfo[0]).asString();


        try {
            userModel = user.readValue(userData, UserModel.class);
        } catch (JsonProcessingException ignored) {
        }
        Cookies restAssuredCookies = getRestAssured()
                .contentType(ContentType.URLENC)
                .formParam("username", userModel.username)
                .formParam("password", password)
                .when()
                .post(AUTHENTICATE_ENDPOINT)
                .then()
                .statusCode(302)
                .extract().detailedCookies();
        Cookie restAssuredJSessionIdCookie = restAssuredCookies.get("JSESSIONID");

        org.openqa.selenium.Cookie seleniumCookie = new org.openqa.selenium.Cookie(
                restAssuredJSessionIdCookie.getName(),
                restAssuredJSessionIdCookie.getValue(),
                restAssuredJSessionIdCookie.getDomain(),
                restAssuredJSessionIdCookie.getPath(),
                restAssuredJSessionIdCookie.getExpiryDate(),
                restAssuredJSessionIdCookie.isSecured()
        );

        userModel.setCookie(seleniumCookie);

        return userModel;
    }

    public Response getAllUsers() {

        return getRestAssured()
                .when()
                .body(GET_ALL_USERS_BODY)
                .post("/api/users")
                .then().statusCode(200)
                .extract().response();
    }

    public Response getUserById(int userId, String userName) {
        return getRestAssured()
                .when()
                .get(String.format("/api/users/auth/%d?principal=%s", userId, userName));
    }
}
