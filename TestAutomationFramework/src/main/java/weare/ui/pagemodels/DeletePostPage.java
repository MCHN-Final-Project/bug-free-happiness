package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class DeletePostPage extends BasePage{
    public DeletePostPage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.deletePost", true);
    }
    public void clickOnDeleteButton() {
        actions.waitForElementClickable("deletePost.deletePostButton");
        actions.clickElement("deletePost.deletePostButton");
    }

    public void clickOnDropDownButton() {
        actions.waitForElementClickable("deletePost.deleteDropDownMenuButton");
        actions.clickElement("deletePost.deleteDropDownMenuButton");
    }

    public void clickOnSubmitButton(){
        actions.waitForElementClickable("deletePost.submitButton");
        actions.clickElement("deletePost.submitButton");
    }
}
