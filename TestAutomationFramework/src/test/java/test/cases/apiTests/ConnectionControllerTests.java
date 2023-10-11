package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.ConnectionController;
import api.controllers.UserController;
import api.controllers.helpers.SqlMethods;
import api.controllers.models.UserModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import weare.ui.pagemodels.models.UserData;


public class ConnectionControllerTests {
    static BaseController baseController = new BaseController();
    ConnectionController connectionController = new ConnectionController();
    static UserController userController = new UserController();
    static UserModel sender;
    static UserModel receiver;
    static UserData senderCred = new UserData();
    static UserData receiverCred = new UserData();
    private static int requestId;


    @BeforeAll
    public static void setup() {
        senderCred.username = baseController.getRandomUsername();
        senderCred.password = baseController.getRandomPassword();
        senderCred.email = baseController.getRandomEmail();
        sender = userController.createUser(senderCred.username, senderCred.password, senderCred.email, false);
        receiverCred.username = baseController.getRandomUsername();
        receiverCred.password = baseController.getRandomPassword();
        receiverCred.email = baseController.getRandomEmail();
        receiver = userController.createUser(receiverCred.username, receiverCred.password, receiverCred.email, false);
    }

    @BeforeEach
    public void localSetup(TestInfo testInfo) {
        if (testInfo.getTags().contains("InitialSetup")) return;
        connectionController.sendConnectionRequest
                (sender.username, senderCred.password, receiver.id, receiver.username);

        if (testInfo.getTags().contains("PartialSetup")) return;
        Response response = connectionController.getUserConnectionRequest
                (receiver.username, receiverCred.password, receiver.id);
        requestId = baseController.getRequestId(response);
    }

    @AfterEach
    public void localCleanup(){
        SqlMethods.deleteRequestById("id", requestId);
    }

    @AfterAll
    public static void cleanup() {
        SqlMethods.deleteUserById("user_id", sender.id);
        SqlMethods.deleteUserById("user_id", receiver.id);
    }

    @Test
    @Tag("InitialSetup")
    @DisplayName("Send connection request successfully")
    public void sendConnectionRequestToExistingUserSuccessfully() {

        Response response = connectionController.sendConnectionRequest
                (sender.username, senderCred.password, receiver.id, receiver.username);

        connectionController.assertSenderReceiverAndRequestAreExisting
                (response, sender.username, receiver.username);

        Response idGetter = connectionController.getUserConnectionRequest
                (receiver.username, receiverCred.password, receiver.id);
        requestId = baseController.getRequestId(idGetter);
    }


    @Test
    @Tag("PartialSetup")
    @DisplayName("Get connection requests successfully")
    public void getConnectionRequestsWhenExistingSuccessfully() {

        Response response = connectionController.getUserConnectionRequest
                (receiver.username, receiverCred.password, receiver.id);
        requestId = baseController.getRequestId(response);

        connectionController.assertResponseIsArrayAndNotEmpty(response);
        connectionController.assertResponseContainsRequestId(response);
    }

    @Test
    @DisplayName("Approve connection request successfully")
    public void approveConnectionRequestWhenExistingSuccessfully() {

        Response response = connectionController.approveConnectionRequest
                (receiver.username, receiverCred.password, receiver.id, requestId);

        connectionController.assertConnectionRequestIsApproved(response);
        connectionController.assertSenderAndReceiverAreCorrect(response, receiver.username, sender.username);

        SqlMethods.deleteConnectionById("user_a", sender.id);
        SqlMethods.deleteConnectionById("user_a", receiver.id);
    }
}