package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class CommentPage extends BasePage {

    public CommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.comment", true);
    }
    public void enterCommentBody(String commentBody) {
        actions.typeValueInField(commentBody,"comment.content");
    }

  public void clickOnPostCommentButton(){
        actions.clickElement("comment.postCommentButton");
  }

  public void clickLikeOnButton(){
        actions.clickElement("comment.likeButton");
  }

  public void clickDislikeButton(){
        actions.clickElement("comment.dislikeButton");
  }

  public void clickEditCommentButton() {
        actions.clickElement("comment.editCommentButton");
  }

  public void clickDeleteCommentButton() {
        actions.clickElement("comment.deleteCommentButton");
  }

  public void clickOnEditPostButton(){
        actions.clickElement("comment.editPostButton");
  }

}
