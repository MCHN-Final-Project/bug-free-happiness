package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class CommentPage extends BasePage {

    public CommentPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.comment", true, false);
    }
    public void enterCommentBody(String commentBody) {
        actions.typeValueInField(commentBody,"comment.content");
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

  public void clickOnEditPostButton(){
        actions.clickElement("comment.editPostButton");
  }

}
