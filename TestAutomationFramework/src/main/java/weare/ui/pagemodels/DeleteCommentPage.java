package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.UserController;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserModelForUi;

public class DeleteCommentPage extends BasePage {
    UserController userController = new UserController();
    BaseController baseController = new BaseController();
    UserModelForUi userModelForUi = new UserModelForUi();
    RegisterPage registerPage = new RegisterPage(actions.getDriver());

    public DeleteCommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.deleteComment", false);
        String username = baseController.getRandomUsername();
        userController.createUser(username, baseController.getRandomPassword(),
                baseController.getRandomEmail(), false);
        userModelForUi = registerPage.assertUserExists(username);
        navigateToPage();
        assertPageNavigated();
    }

    public void clickOnDropDownMenu(){
        actions.clickElement("deleteComment.dropDownMenu");
    }

    public void clickOnSubmitButton() {
        actions.clickElement("deleteComment.submitButton");
    }

}
