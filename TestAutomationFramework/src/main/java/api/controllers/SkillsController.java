package api.controllers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static api.controllers.helpers.Endpoints.*;
import static api.controllers.helpers.JSONBodies.CREATE_SKILL_BODY;
import static java.lang.String.format;
import static org.asynchttpclient.util.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class SkillsController extends BaseController {

    public Response getSkills() {

        return getRestAssured()
                .when()
                .get(GET_SKILLS_ENDPOINT)
                .then()
                .extract().response();
    }

    public Response createSkill(String userCategoryId, String userCategoryName, String skillText) {

        String requestBody = format(CREATE_SKILL_BODY, userCategoryId, userCategoryName, skillText);

        return getRestAssured()
                .body(requestBody)
                .when()
                .post(CREATE_SKILL_ENDPOINT);
    }

    public Response getSkillById(String skillId) {

        return getRestAssured()
                .queryParam("skillId", skillId)
                .when()
                .get(GET_SKILL_BY_ID_ENDPOINT);
    }

    public Response editSkill(String editText, String skillId) {

        String encodedSkillText = URLEncoder.encode(editText, StandardCharsets.UTF_8);

        return getRestAssured()
                .queryParam("skillId", skillId)
                .when()
                .put(format("%s%s%s", EDIT_SKILL_TEXT_ENDPOINT, encodedSkillText, EDIT_SKILL_ID_ENDPOINT));
    }


    public Response deleteSkill(String skillId) {


        return getRestAssured()
                .queryParam("skillId", skillId)
                .when()
                .put(DELETE_SKILL_ENDPOINT);
    }


    public static void assertNewSkillContentIsCorrect(String randomSkillText, Response response) {
        String responseSkillText = response.getBody().jsonPath().get("skill");
        String decodedResponseSkillText = URLDecoder.decode(responseSkillText, StandardCharsets.UTF_8);

        assertEquals(randomSkillText, decodedResponseSkillText, "The content is not correct or empty");
    }

    public static void assertSkillIsNotPresent(String deletedSkill, Response response1) {
        JsonPath jsonPath = response1.jsonPath();

        List<Map<String, Object>> skills = jsonPath.getList("$");

        boolean isCreatedSkillNotPresent = skills.stream()
                .noneMatch(skill -> skill.get("skillId").equals(deletedSkill));

        assertTrue(isCreatedSkillNotPresent);
    }

    public static void assertSkillIdIsNotNullAndSkillIsNotEmpty(Response response) {
        JsonPath jsonPath = response.getBody().jsonPath();

        int numberOfSkills = jsonPath.getList("$").size();

        for (int i = 0; i < numberOfSkills; i++) {
            assertNotNull(jsonPath.get("[" + i + "].skill"), "Skill is null.");
            assertNotNull(jsonPath.get("[" + i + "].skillId"), "Skill id is null.");

            String skill = jsonPath.get("[" + i + "].skill");
            assertFalse(skill.isEmpty(),
                    "Empty skill at index " + i);
        }
    }
}
