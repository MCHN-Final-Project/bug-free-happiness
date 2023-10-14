package api.controllers.models;


import org.openqa.selenium.Cookie;
import java.util.ArrayList;

public class UserModel {

    public int id;
    public String username;
    public ArrayList<String> authorities;
    public String email;
    public String firstName;
    public String lastNAme;
    public String gender;
    public Object city;
    public String birthYear;
    public String personalReview;
    public String expertise;
    public ArrayList<String> skills;
    public Cookie cookie;
    public Cookie getCookie() {
        return cookie;
    }
    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}

