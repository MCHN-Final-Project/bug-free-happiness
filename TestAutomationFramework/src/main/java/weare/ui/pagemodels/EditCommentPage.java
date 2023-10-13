package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.UserController;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserModelForUi;

public class EditCommentPage extends BasePage {
    UserController userController = new UserController();
    BaseController baseController = new BaseController();
    UserModelForUi userModelForUi = new UserModelForUi();
    RegisterPage registerPage = new RegisterPage(actions.getDriver());
    public EditCommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.editComment", false);
        String username = baseController.getRandomUsername();
        userController.createUser(username, baseController.getRandomPassword(),
                baseController.getRandomEmail(), false);
        userModelForUi = registerPage.assertUserExists(username);
        navigateToPage();
        assertPageNavigated();
    }

    public void enterEditedCommentContent(String editedContent){
        actions.typeValueInField("editComment.content", editedContent);
    }

    public void clickOnEditCommentButton(){
        actions.clickElement("editComment.editButton");
    }

}