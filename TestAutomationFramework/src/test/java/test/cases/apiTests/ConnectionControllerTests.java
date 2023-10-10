package test.cases.apiTests;

import api.controllers.ConnectionController;
import api.controllers.UserController;
import api.controllers.models.UserModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static api.controllers.BaseController.*;
import static api.controllers.ConnectionController.*;


public class ConnectionControllerTests {
    ConnectionController connectionController = new ConnectionController();
    UserController userController = new UserController();
    UserModel sender;
    UserModel receiver;
    String senderUsername;
    String senderPassword;
    String receiverUsername;
    String receiverPassword;
    int receiverId;
    int requestId;


    @BeforeEach
    public void local_setup(TestInfo testInfo) {
        sender = userController.createUser(false);
        receiver = userController.createUser(false);
        senderUsername = sender.username;
        senderPassword = "password";
        receiverUsername = receiver.username;
        receiverPassword = "password";
        receiverId = receiver.id;

        if (testInfo.getTags().contains("InitialSetup")) return;
        connectionController.sendConnectionRequest
                (senderUsername, senderPassword, receiverId, receiverUsername);

        if (testInfo.getTags().contains("PartialSetup")) return;
        Response response = connectionController.getUserConnectionRequest
                (receiverUsername, receiverPassword, receiverId);
        requestId = getRequestId(response);


    }

    @Test
    @Tag("InitialSetup")
    public void sendConnectionRequest_toExistingUser_Successfully() {

        Response response = connectionController.sendConnectionRequest
                (senderUsername, senderPassword, receiverId, receiverUsername);

        assertStatusCode(response, 200);
        assertSenderReceiverAndRequestSent(response, senderUsername, receiverUsername);
        System.out.println("Connection request is sent successfully");
    }


    @Test
    @Tag("PartialSetup")
    public void getConnectionRequests_whenExisting_successfully() {

        Response response = connectionController.getUserConnectionRequest
                (receiverUsername, receiverPassword, receiverId);

        assertStatusCode(response, 200);
        assertResponseIsArrayAndNotEmpty(response);
        assertResponseContainsRequestId(response);
        System.out.println("Connection requests are got successfully");
    }

    @Test
    public void approveConnectionRequest_whenExisting_successfully() {

        Response response1 = connectionController.approveConnectionRequest
                (receiverUsername, receiverPassword, receiverId, requestId);

        assertStatusCode(response1, 200);
        assertConnectionRequestIsApproved(response1);
        System.out.println("Connection request is approved successfully");
    }
}