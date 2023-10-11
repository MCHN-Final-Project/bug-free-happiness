package weare.ui.pagemodels.models;

import api.controllers.BaseController;


public class UserData {
    static BaseController baseController = new BaseController();
    public String username = baseController.getRandomUsername();
    public String password = baseController.getRandomPassword();
    public String email = baseController.getRandomEmail();
}
