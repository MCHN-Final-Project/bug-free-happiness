package weare.ui.pagemodels;

import api.controllers.BaseController;
import api.controllers.UserController;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import weare.ui.pagemodels.models.UserModelForUi;

public class CommentPage extends BasePage {
    UserController userController = new UserController();
    BaseController baseController = new BaseController();
    UserModelForUi userModelForUi = new UserModelForUi();
    RegisterPage registerPage = new RegisterPage(actions.getDriver());
    public CommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.comment", false);
        String username = baseController.getRandomUsername();
        userController.createUser(username, baseController.getRandomPassword(),
                baseController.getRandomEmail(), false);
        userModelForUi = registerPage.assertUserExists(username);
        navigateToPage();
        assertPageNavigated();
    }

   public void enterCommentContent(){
        actions.clickElement("comment.content");
   }

  public void clickOnPostCommentButton(){
        actions.clickElement("comment.postCommentButton");
  }

  public void clickOnShowCommentsButton(){
        actions.clickElement("comment.showCommentsButton");
  }

  public void clickLikeOnButton(String commentContent){
        actions.clickElement("comment.likeButton", commentContent);
  }

  public void clickEditCommentButton(String commentContent) {
        actions.clickElement("comment.editComment", commentContent);
  }

  public void clickDeleteCommentButton(String commentContent) {
        actions.clickElement("comment.deleteComment", commentContent);
  }

}
