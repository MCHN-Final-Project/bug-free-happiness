package weare.ui.pagemodels;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver, "weAreSocialNetwork.homepage", false);
        navigateToPage();
        assertPageNavigated();
    }
    public void clickOnSingInButton() {
        actions.clickElement("home.signInButton");
    }
    public void clickOnHomeButton(){
        actions.clickElement("home.homeButton");
    }
    public void clickOnLatestPostButton(){
        actions.clickElement("home.latestPostsButton");
    }
    public void clickOnAboutUsButton(){actions.clickElement("home.aboutUsButton");}
    public void clickOnPersonalProfileButton(){actions.clickElement("home.personalProfileButton");}
    public void clickOnAddNewPostButton(){
        actions.waitForElementClickable("home.addNewPostButton");
        actions.clickElement("home.addNewPostButton");}
    public void clickOnLogoutButton(){actions.clickElement("home.logoutButton");}
    public void clickOnWeAreButton(){actions.clickElement("home.weAreButton");}
    public void enterProfession(String profession) {actions.typeValueInField(profession, "home.profession");}
    public void enterUsersName(String username) {actions.typeValueInField(username, "home.username");}
    public void clickOnSearchButton() {actions.clickElement("home.searchButton");}
}
