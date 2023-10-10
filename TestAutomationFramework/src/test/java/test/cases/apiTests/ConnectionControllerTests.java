package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.ConnectionController;
import api.controllers.UserController;
import api.controllers.models.UserModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;


public class ConnectionControllerTests {
    BaseController baseController = new BaseController();
    ConnectionController connectionController = new ConnectionController();
    UserController userController = new UserController();
    UserModel sender;
    UserModel receiver;
    private int requestId;


    @BeforeEach
    public void local_setup(TestInfo testInfo) {
        sender = userController.createUser(false);
        receiver = userController.createUser(false);

        if (testInfo.getTags().contains("InitialSetup")) return;
        connectionController.sendConnectionRequest
                (sender.username, "password", receiver.id, receiver.username);

        if (testInfo.getTags().contains("PartialSetup")) return;
        Response response = connectionController.getUserConnectionRequest
                (receiver.username, "password", receiver.id);
        requestId = baseController.getRequestId(response);
    }

    @Test
    @Tag("InitialSetup")
    public void sendConnectionRequest_toExistingUser_successfully() {

        Response response = connectionController.sendConnectionRequest
                (sender.username, "password", receiver.id, receiver.username);

        connectionController.assertSenderReceiverAndRequestAreExisting(response, sender.username, receiver.username);
        System.out.println("Connection request is sent successfully");
    }


    @Test
    @Tag("PartialSetup")
    public void getConnectionRequests_whenExisting_successfully() {

        Response response = connectionController.getUserConnectionRequest
                (receiver.username, "password", receiver.id);

        connectionController.assertResponseIsArrayAndNotEmpty(response);
        connectionController.assertResponseContainsRequestId(response);
        System.out.println("Connection requests are got successfully");
    }

    @Test
    public void approveConnectionRequest_whenExisting_successfully() {

        Response response = connectionController.approveConnectionRequest
                (receiver.username, "password", receiver.id, requestId);

        connectionController.assertConnectionRequestIsApproved(response);
        connectionController.assertSenderAndReceiverAreCorrect(response, receiver.username, sender.username);
        System.out.println("Connection request is approved successfully");
    }
}