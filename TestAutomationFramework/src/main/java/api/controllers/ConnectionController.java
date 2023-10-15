package api.controllers;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static api.controllers.helpers.Endpoints.*;
import static api.controllers.helpers.JSONBodies.SEND_REQUEST_BODY;
import static api.controllers.helpers.QueryParams.REQUEST_ID_PARAM;
import static java.lang.String.format;
import static org.asynchttpclient.util.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionController extends BaseController {


    public Response sendConnectionRequest(String userName, String userPassword, int userId2, String userName2) {

        Cookies cookies = getRestAssured()
                .contentType(ContentType.URLENC)
                .formParam("username", userName)
                .formParam("password", userPassword)
                .when()
                .post(AUTHENTICATE_ENDPOINT)
                .then()
                .statusCode(302)
                .extract().detailedCookies();

        String requestBody = format(SEND_REQUEST_BODY, userId2, userName2);

        return getRestAssured()
                .cookies(cookies)
                .body(requestBody)
                .when()
                .post(SEND_REQUEST_ENDPOINT);
    }

    public Response getUserConnectionRequest(String userName, String userPassword, int userId) {

        Cookies cookies = getRestAssured()
                .contentType(ContentType.URLENC)
                .formParam("username", userName)
                .formParam("password", userPassword)
                .when()
                .post(AUTHENTICATE_ENDPOINT)
                .then()
                .statusCode(302)
                .extract().detailedCookies();

        return getRestAssured()
                .cookies(cookies)
                .when()
                .get(format(GET_USERS_REQUESTS_ENDPOINT, userId));
    }

    public Response approveConnectionRequest(String userName, String userPassword, int userId, int requestId) {

        Cookies cookies = getRestAssured()
                .contentType(ContentType.URLENC)
                .formParam("username", userName)
                .formParam("password", userPassword)
                .when()
                .post(AUTHENTICATE_ENDPOINT)
                .then()
                .statusCode(302)
                .extract().detailedCookies();

        return getRestAssured()
                .cookies(cookies)
                .queryParam("requestId", requestId)
                .when()
                .post(format(GET_USERS_REQUESTS_ENDPOINT, userId)
                        + APPROVE_REQUEST_ENDPOINT + REQUEST_ID_PARAM + requestId)
                .then()
                .statusCode(200)
                .extract().response();
    }


    public void assertSenderReceiverAndRequestAreExisting(Response response, String userName, String userName2) {

        String responseText = response.getBody().asString();

        Pattern pattern = Pattern.compile("(\\w+) send friend request to (\\w+)");
        Matcher matcher = pattern.matcher(responseText);

        String user1 = "";
        String user2 = "";

        if (matcher.find()) {
            user1 = matcher.group(1);
            user2 = matcher.group(2);
        } else {
            System.out.println("No match found in the response.");
        }
        assertEquals(user1, userName, "Sender is not correct.");
        assertEquals(user2, userName2, "Receiver is not correct.");
        assertTrue(responseText.contains("send friend request to"), "The request is not sent.");
    }

    public void assertResponseContainsRequestId(Response response) {
        JsonPath jsonPath = response.getBody().jsonPath();
        assertNotNull(jsonPath.get("[0].id"), "The 'id' property does not exist in the response.");
    }

    public void assertConnectionRequestIsApproved(Response response) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("approved request of"));
    }

    public void assertSenderAndReceiverAreCorrect(Response response, String userName, String userName2) {

        String responseText = response.getBody().asString();

        Pattern pattern = Pattern.compile("(\\w+) approved request of (\\w+)");
        Matcher matcher = pattern.matcher(responseText);

        String user1 = "";
        String user2 = "";

        if (matcher.find()) {
            user1 = matcher.group(1);
            user2 = matcher.group(2);
        } else {
            System.out.println("No match found in the response.");
        }
        assertEquals(user1, userName, "Receiver is not correct.");
        assertEquals(user2, userName2, "Sender is not correct.");
        assertTrue(responseText.contains("approved request of"), "The request was not approved.");
    }
}
