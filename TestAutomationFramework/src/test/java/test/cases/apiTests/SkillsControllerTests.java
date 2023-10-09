package test.cases.apiTests;

import api.controllers.SkillsController;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static api.controllers.BaseController.*;
import static api.controllers.SkillsController.*;

public class SkillsControllerTests {
    private static String userCategoryId;
    private static String userCategoryName;
    private static String createdNewSkillId;
    private static String randomSkillText_Create;
    private static String randomSkillText_Edit;
    SkillsController skillsController = new SkillsController();

    @Test
    public void CRUD_Skills_Flow_With_ValidData_Successfully() {
        step_getExistingSkills_Successfully();
        step_createNewSkill_Successfully();
        step_getCreatedSkill_ToAssert_SkillCreatedSuccessfully();
        step_editCreatedSkillWithNewSkillText_Successfully();
        step_getEditedSkillById_ToAssert_EditedSuccessfully();
        step_deleteCreatedSkill_Successfully();
        step_getSkills_ToAssert_SkillDeletedSuccessfully();
    }


    public void step_getExistingSkills_Successfully() {

        Response response = skillsController.getSkills();

        assertStatusCode(response, 200);
        assertResponseIsArrayAndNotEmpty(response);
        assertSkillIdIsNotNullAndSkillIsNotEmpty(response);

        userCategoryId = response.getBody().jsonPath().getString("[0].category.id");
        userCategoryName = response.getBody().jsonPath().getString("[0].category.name");

        System.out.println("Get Existing skills is successful");
    }


    public void step_createNewSkill_Successfully() {

        randomSkillText_Create = "Created skill: " + getRandomSentence();

        Response response = skillsController.createSkill(userCategoryId, userCategoryName, randomSkillText_Create);

        assertStatusCode(response, 200);
        assertResponseBodyIsNotEmpty(response);
        assertNewSkillContentIsCorrect(randomSkillText_Create, response);

        createdNewSkillId = response.getBody().jsonPath().getString("skillId");

        System.out.println("Create new skill is successful");
    }


    public void step_getCreatedSkill_ToAssert_SkillCreatedSuccessfully() {

        Response response = skillsController.getSkillById(createdNewSkillId);

        assertStatusCode(response, 200);
        assertResponseBodyIsNotEmpty(response);
        assertNewSkillContentIsCorrect(randomSkillText_Create, response);

        System.out.println("Created skill is asserted successfully");
    }

    public void step_editCreatedSkillWithNewSkillText_Successfully() {
        randomSkillText_Edit = "Edited Skill: " + getRandomSentence();

        Response response = skillsController.editSkill(randomSkillText_Edit, createdNewSkillId);

        assertStatusCode(response, 200);

        System.out.println("Created skill is edited successfully");
    }

    public void step_getEditedSkillById_ToAssert_EditedSuccessfully() {
        Response response = skillsController.getSkillById(createdNewSkillId);

        assertStatusCode(response, 200);
        assertResponseBodyIsNotEmpty(response);
        assertNewSkillContentIsCorrect(randomSkillText_Edit, response);

        System.out.println("Edited skill is asserted successfully");
    }


    public void step_deleteCreatedSkill_Successfully() {
        Response response = skillsController.deleteSkill(createdNewSkillId);

        assertStatusCode(response, 200);

        System.out.println("Created and Edited skill is deleted successfully");
    }

    public void step_getSkills_ToAssert_SkillDeletedSuccessfully() {
        Response response = skillsController.getSkills();

        assertStatusCode(response, 200);
        assertResponseIsArrayAndNotEmpty(response);
        assertSkillIdIsNotNullAndSkillIsNotEmpty(response);
        assertSkillIsNotPresent(createdNewSkillId, response);

        System.out.println("Skill deletion is asserted successfully");
    }

}
