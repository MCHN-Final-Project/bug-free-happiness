package weare.ui.pagemodels;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class LatestPostPage extends BasePage {

    public LatestPostPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.latestPosts");
        navigateToPage();
        assertPageNavigated();
    }

    public void clickOnBrowsePublicPostsButton(){
        actions.clickElement("latestPost.browsePublicPostsButton");
    }

   public void clickOnExplorePostButton(String postContent){
        actions.clickElement("latestPost.explorePostButton", postContent);
   }
}
