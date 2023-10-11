package test.cases.apiTests;

import api.controllers.BaseController;
import api.controllers.SkillsController;
import api.controllers.models.SkillModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

public class SkillsControllerTests {

    SkillsController skillsController = new SkillsController();
    BaseController baseController = new BaseController();
    private int createdSkillId;
    private String textToCreateSkill;
    private String responseText;

    @BeforeEach
    public void local_setup(TestInfo testInfo) {

        textToCreateSkill = "Created skill: " + baseController.getRandomSentence();
        if (testInfo.getTags().contains("PartialSetup")) return;

        SkillModel response = skillsController.createSkill(textToCreateSkill);
        createdSkillId = response.skillId;
        responseText = response.skill;
    }

    @AfterEach
    public void local_cleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup")) return;

        skillsController.deleteSkill(createdSkillId);
    }

    @Test
    @DisplayName("Get skills successfully")
    public void getSkills_whenExisting_successfully() {

        Response response = skillsController.getSkills();

        baseController.assertResponseIsArrayAndNotEmpty(response);
        skillsController.assertSkillIdIsNotNullAndSkillIsNotEmpty(response);
    }

    @Test
    @Tag("PartialSetup")
    @DisplayName("Create skill successfully")
    public void createSkill_withValidData_successfully() {

        SkillModel response = skillsController.createSkill
                (textToCreateSkill);

        Assertions.assertTrue(response.skillId > -1);
        Assertions.assertEquals(textToCreateSkill, response.skill);

        createdSkillId = response.skillId;
    }

    @Test
    @DisplayName("Get skill by id successfully")
    public void getSkill_byId_whenExisting_successfully() {

        skillsController.getSkillById(createdSkillId);

        Assertions.assertEquals(textToCreateSkill, responseText);
    }

    @Test
    @DisplayName("Edit skill successfully")
    public void editSkill_withNewSkillText_successfully() {

        String textToEditSkill = "Edited Skill: " + baseController.getRandomSentence();

        skillsController.editSkill(textToEditSkill, createdSkillId);

        Response response = skillsController.getSkillById(createdSkillId);
        baseController.assertResponseBodyIsNotEmpty(response);
        skillsController.assertNewSkillContentIsCorrect(textToEditSkill, response);
    }

    @Test
    @Tag("NoCleanup")
    @DisplayName("Delete skill successfully")
    public void deleteSkill_whenExisting_successfully() {

        skillsController.deleteSkill(createdSkillId);

        Response response = skillsController.getSkills();
        skillsController.assertSkillIdIsNotNullAndSkillIsNotEmpty(response);
        skillsController.assertSkillIsNotPresent(createdSkillId, response);
    }
}
