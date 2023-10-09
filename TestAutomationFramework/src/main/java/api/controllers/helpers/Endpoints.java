package api.controllers.helpers;

public class Endpoints {

    public static final String GET_SKILLS_ENDPOINT = "api/skill/";
    public static final String CREATE_SKILL_ENDPOINT = "api/skill/create";
    public static final String GET_SKILL_BY_ID_ENDPOINT = "api/skill/getOne?skillId=";
    public static final String EDIT_SKILL_TEXT_ENDPOINT = "api/skill/edit?skill=";
    public static final String EDIT_SKILL_ID_ENDPOINT = "&skillId=";
    public static final String DELETE_SKILL_ENDPOINT = "api/skill/delete?skillId=";
    public static final String CREATE_USER_ENDPOINT = "api/users/";
    public static final String AUTHENTICATE_ENDPOINT = "authenticate";
    public static final String SEND_REQUEST_ENDPOINT = "api/auth/request";
    public static final String GET_USERS_REQUESTS_ENDPOINT = "api/auth/users/%d/request/";
    public static final String APPROVE_REQUEST_ENDPOINT = "approve?requestId=%d";
}
