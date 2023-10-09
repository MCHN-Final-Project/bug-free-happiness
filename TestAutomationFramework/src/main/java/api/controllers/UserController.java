package api.controllers;

import io.restassured.response.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController extends BaseController {

    public String[] createUser(Boolean admin) {
        String adm = "";
        if (admin)
            adm = "admin";
        String createUserBody = "{\n" +
                "  \"authorities\": [\n" +
                "    \"ROLE_USER\"\n" +
                "  ],\n" +
                "  \"category\": {\n" +
                "    \"id\": 102,\n" +
                "    \"name\": \"Actor\"\n" +
                "  },\n" +
                "  \"confirmPassword\": \"password\",\n" +
                "  \"email\": \"" + BaseController.faker.internet().emailAddress() + "\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"username\": \"" + adm + BaseController.faker.name().firstName() + "\"\n" +
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
        return userInfo;
    }

    public String authenticateUser() {

        String jsessionIdCookie = getRestAssured()
                .formParam("username", getLatestRegisteredUsername(getAllUsers()))
                .formParam("password", USER_PASSWORD)
                .when()
                .post("/authenticate")
                .then().statusCode(302)
                .extract().response().getCookie("JSESSIONID");
        return jsessionIdCookie;
    }

    public Response getAllUsers() {

        String getAllUsersBody =
                "{\n" +
                        "  \"index\": 0,\n" +
                        "  \"next\": true,\n" +
                        "  \"searchParam1\": \"\",\n" +
                        "  \"searchParam2\": \"\",\n" +
                        "  \"size\": 100\n" +
                        "}";

        return getRestAssured()
                .when()
                .body(getAllUsersBody)
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
