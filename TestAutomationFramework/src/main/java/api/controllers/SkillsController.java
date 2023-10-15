package api.controllers;

import api.controllers.models.SkillModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static api.controllers.helpers.Endpoints.*;
import static api.controllers.helpers.JSONBodies.CREATE_SKILL_BODY;
import static api.controllers.helpers.QueryParams.SKILL_ID_PARAM;
import static api.controllers.helpers.QueryParams.SKILL_PARAM;
import static java.lang.String.format;
import static org.asynchttpclient.util.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class SkillsController extends BaseController {
    SkillModel skillModel = new SkillModel();
    ObjectMapper skill = new ObjectMapper();

    public SkillModel createSkill(String skillText) {

        String requestBody = format(CREATE_SKILL_BODY, skillText);

        Response response = getRestAssured()
                .body(requestBody)
                .when()
                .post(CREATE_SKILL_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().response();

        String responseBody = response.asString();
        try {
            skillModel = skill.readValue(responseBody, SkillModel.class);
        } catch (JsonProcessingException ignored) {
        }
        return skillModel;
    }

    public Response getSkills() {

        return getRestAssured()
                .when()
                .get(GET_SKILLS_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response getSkillById(int skillId) {

        return getRestAssured()
                .queryParam("skillId", skillId)
                .when()
                .get(GET_SKILL_BY_ID_ENDPOINT + SKILL_ID_PARAM)
                .then()
                .statusCode(200).
                extract().response();
    }

    public void editSkill(String editText, int skillId) {

        String encodedSkillText = URLEncoder.encode(editText, StandardCharsets.UTF_8);

        getRestAssured()
                .queryParam("skillId", skillId)
                .when()
                .put(EDIT_SKILL_TEXT_ENDPOINT + SKILL_PARAM + encodedSkillText + "&" + SKILL_ID_PARAM)
                .then()
                .statusCode(200)
                .extract().response();
    }


    public void deleteSkill(int skillId) {

        getRestAssured()
                .queryParam("skillId", skillId)
                .when()
                .put(DELETE_SKILL_ENDPOINT + SKILL_ID_PARAM)
                .then()
                .statusCode(200)
                .extract().response();
    }


    public void assertNewSkillContentIsCorrect(String randomSkillText, Response response) {
        String responseSkillText = response.getBody().jsonPath().get("skill");
        String decodedResponseSkillText = URLDecoder.decode(responseSkillText, StandardCharsets.UTF_8);

        assertEquals(randomSkillText, decodedResponseSkillText, "The content is not correct or empty");
    }

    public void assertSkillIsNotPresent(int deletedSkill, Response response1) {
        JsonPath jsonPath = response1.jsonPath();

        List<Map<String, Object>> skills = jsonPath.getList("$");

        boolean isCreatedSkillNotPresent = skills.stream()
                .noneMatch(skill -> skill.get("skillId").equals(deletedSkill));

        assertTrue(isCreatedSkillNotPresent);
    }

    public void assertSkillIdIsNotNullAndSkillIsNotEmpty(Response response) {
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
