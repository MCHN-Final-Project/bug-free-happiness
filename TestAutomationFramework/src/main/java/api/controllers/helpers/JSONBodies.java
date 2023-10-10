package api.controllers.helpers;

public class JSONBodies {

    public static final String CREATE_SKILL_BODY = "{\n" +
            "    \"category\": {\n" +
            "        \"id\": 100,\n" +
            "        \"name\": \"All\"\n" +
            "    },\n" +
            "    \"skill\": \"%s\",\n" +
            "    \"skillId\": 0\n" +
            "}";

    public static final String CREATE_USER_BODY = "{\n" +
            "  \"authorities\": [\n" +
            "    \"ROLE_USER\"\n" +
            "  ],\n" +
            "  \"category\": {\n" +
            "    \"id\": 102,\n" +
            "    \"name\": \"Actor\"\n" +
            "  },\n" +
            "  \"confirmPassword\": \"%s\",\n" +
            "  \"email\": \"%s\",\n" +
            "  \"password\": \"%s\",\n" +
            "  \"username\": \"%s\"\n" +
            "}";

    public static final String SEND_REQUEST_BODY = "{\n" +
            "    \"id\": %d,\n" +
            "    \"username\": \"%s\"\n" +
            "}";
}
