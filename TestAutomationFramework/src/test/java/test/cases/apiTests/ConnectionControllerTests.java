package test.cases.apiTests;

import api.controllers.ConnectionController;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static api.controllers.BaseController.*;
import static api.controllers.ConnectionController.*;


public class ConnectionControllerTests {
    ConnectionController connectionController = new ConnectionController();

    private static Integer userId2;
    private static String userName1;
    private static String userName2;
    private static String userPassword1;
    private static String userPassword2;
    private static Integer requestId;


    @Test
    public void Send_Receive_Accept_FriendConnectionRequest_Flow_Successfully() {
        step_createUser1_toSendConnectionRequest_Successfully();
        step_createUser2_toReceiveConnectionRequest_Successfully();
        step_user1_sendsConnectionRequest_toUser2_Successfully();
        step_user2GetsConnectionRequestSentByUser1_Successfully();
        step_user2ApprovesConnectionRequestSentByUser1_Successfully();
    }


    public void step_createUser1_toSendConnectionRequest_Successfully() {

        String randomUsername = getRandomUsername();
        String randomEmail = getRandomEmail();
        String randomPassword = getRandomPassword();

        Response response = connectionController.createUser(randomUsername, randomPassword, randomEmail);

        assertStatusCode(response, 200);
        assertResponseBodyIsNotEmpty(response);
        assertResponseUsernameIsCorrect(randomUsername, response);

        userName1 = randomUsername;
        userPassword1 = randomPassword;

        System.out.println("User1 to send request is created successfully");
    }

    public void step_createUser2_toReceiveConnectionRequest_Successfully() {

        String randomUsername = getRandomUsername();
        String randomEmail = getRandomEmail();
        String randomPassword = getRandomPassword();

        Response response = connectionController.createUser(randomUsername, randomPassword, randomEmail);

        assertStatusCode(response, 200);
        assertResponseBodyIsNotEmpty(response);
        assertResponseUsernameIsCorrect(randomUsername, response);

        userName2 = randomUsername;
        userPassword2 = randomPassword;
        userId2 = getUserIdFromStringResponse(response);

        System.out.println("User2 to receive request is created successfully");
    }

    public void step_user1_sendsConnectionRequest_toUser2_Successfully() {

        Response response = connectionController.sendConnectionRequest(userName1, userPassword1, userId2, userName2);

        assertStatusCode(response, 200);
        assertSenderReceiverAndRequestSent(response, userName1, userName2);

        System.out.println("User1 sends connection request to user2 successfully");
    }

    public void step_user2GetsConnectionRequestSentByUser1_Successfully() {

        Response response = connectionController.getUserConnectionRequest(userName2, userPassword2, userId2);

        assertStatusCode(response, 200);
        assertResponseIsArrayAndNotEmpty(response);
        assertResponseContainsRequestId(response);

        requestId = getRequestId(response);

        System.out.println("User2 gets the sent connection request successfully");
    }

    public void step_user2ApprovesConnectionRequestSentByUser1_Successfully() {
        Response response = connectionController.approveConnectionRequest(userName2, userPassword2, userId2, requestId);

        assertStatusCode(response, 200);
        assertConnectionRequestIsApproved(response);

        System.out.println("User2 approves the connection request successfully");
    }
}