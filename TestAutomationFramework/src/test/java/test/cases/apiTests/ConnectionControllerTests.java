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
        step_createSender_toSendConnectionRequest_Successfully();
        step_createReceiver_toReceiveConnectionRequest_Successfully();
        step_Sender_sendsConnectionRequest_toReceiver_Successfully();
        step_Receiver_GetsConnectionRequest_SentBySender_Successfully();
        step_Receiver_ApprovesConnectionRequest_SentBySender_Successfully();
    }


    public void step_createSender_toSendConnectionRequest_Successfully() {

        String randomUsername = getRandomUsername();
        String randomEmail = getRandomEmail();
        String randomPassword = getRandomPassword();

        Response response = connectionController.createUserWithInitialParams(randomUsername, randomPassword, randomEmail);

        assertStatusCode(response, 200);
        assertResponseBodyIsNotEmpty(response);
        assertResponseUsernameIsCorrect(randomUsername, response);

        userName1 = randomUsername;
        userPassword1 = randomPassword;

        System.out.println("Sender of connection request is created successfully");
    }

    public void step_createReceiver_toReceiveConnectionRequest_Successfully() {

        String randomUsername = getRandomUsername();
        String randomEmail = getRandomEmail();
        String randomPassword = getRandomPassword();

        Response response = connectionController.createUserWithInitialParams(randomUsername, randomPassword, randomEmail);

        assertStatusCode(response, 200);
        assertResponseBodyIsNotEmpty(response);
        assertResponseUsernameIsCorrect(randomUsername, response);

        userName2 = randomUsername;
        userPassword2 = randomPassword;
        userId2 = getUserIdFromStringResponse(response);

        System.out.println("Receiver of connection request is created successfully");
    }

    public void step_Sender_sendsConnectionRequest_toReceiver_Successfully() {

        Response response = connectionController.sendConnectionRequest(userName1, userPassword1, userId2, userName2);

        assertStatusCode(response, 200);
        assertSenderReceiverAndRequestSent(response, userName1, userName2);

        System.out.println("Sender sends connection request to Receiver successfully");
    }

    public void step_Receiver_GetsConnectionRequest_SentBySender_Successfully() {

        Response response = connectionController.getUserConnectionRequest(userName2, userPassword2, userId2);

        assertStatusCode(response, 200);
        assertResponseIsArrayAndNotEmpty(response);
        assertResponseContainsRequestId(response);

        requestId = getRequestId(response);

        System.out.println("Receiver gets the sent connection request successfully");
    }

    public void step_Receiver_ApprovesConnectionRequest_SentBySender_Successfully() {
        Response response = connectionController.approveConnectionRequest(userName2, userPassword2, userId2, requestId);

        assertStatusCode(response, 200);
        assertConnectionRequestIsApproved(response);

        System.out.println("Receiver approves the connection request successfully");
    }
}