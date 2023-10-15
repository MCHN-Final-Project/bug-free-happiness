package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class LatestPostPage extends BasePage {

    public LatestPostPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.latestPost", true);
    }

    public void clickOnBrowsePublicPostsButton(){
        actions.clickElement("latestPost.browsePublicPostsButton");
    }

   public void clickOnExplorePostButton(String postContent){
        actions.clickElement("latestPost.explorePostButton", postContent);
   }

   public void clickOnLikeButton(){
        actions.clickElement("post.likePostButton");
   }

   public void clickOnDislikeButton(){
        actions.clickElement("post.dislikePostButton");
   }
}
