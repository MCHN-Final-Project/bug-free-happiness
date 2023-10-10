package test.cases.apiTests;

import api.controllers.SkillsController;
import api.controllers.models.SkillModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static api.controllers.BaseController.*;
import static api.controllers.SkillsController.*;

public class SkillsControllerTests {
    private static String userCategoryId;
    private static String userCategoryName;
    private static int createdSkillId;
    private static String textToCreateSkill;
    private static String responseText;
    static SkillsController skillsController = new SkillsController();


    @BeforeEach
    public void local_setup(TestInfo testInfo) {

        if (testInfo.getTags().contains("NoSetup")) return;
        Response response = skillsController.getSkills();
        userCategoryId = response.getBody().jsonPath().getString("[0].category.id");
        userCategoryName = response.getBody().jsonPath().getString("[0].category.name");

        if (testInfo.getTags().contains("PartialSetup")) return;
        textToCreateSkill = "Created skill: " + getRandomSentence();
        SkillModel response1 = skillsController.createSkill(userCategoryId, userCategoryName, textToCreateSkill);
        createdSkillId = response1.skillId;
        responseText = response1.skill;
    }

    @AfterEach
    public void local_cleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup")) return;
        skillsController.deleteSkill(createdSkillId);
    }

    @Test
    @Tag("NoSetup")
    @Tag("NoCleanup")
    public void getSkills_whenExisting_successfully() {

        Response response = skillsController.getSkills();

        assertResponseIsArrayAndNotEmpty(response);
        assertSkillIdIsNotNullAndSkillIsNotEmpty(response);
        System.out.println("Get skills request is successful");
    }

    @Test
    @Tag("PartialSetup")
    public void createSkill_withValidData_successfully() {

        SkillModel response = skillsController.createSkill
                (userCategoryId, userCategoryName, textToCreateSkill);

        Assertions.assertTrue(response.skillId > -1);
        Assertions.assertEquals(textToCreateSkill, response.skill);
        System.out.println("Create skill request is successful");

        createdSkillId = response.skillId;
    }

    @Test
    @Tag("NoCleanup")
    public void getSkill_byId_whenExisting_successfully() {

        skillsController.getSkillById(createdSkillId);

        Assertions.assertEquals(textToCreateSkill, responseText);
        System.out.println("Get skill by Id request is successful");
    }

    @Test
    public void editSkill_withNewSkillText_successfully() {

        String textToEditSkill = "Edited Skill: " + getRandomSentence();

        skillsController.editSkill(textToEditSkill, createdSkillId);

        Response response = skillsController.getSkillById(createdSkillId);
        assertResponseBodyIsNotEmpty(response);
        assertNewSkillContentIsCorrect(textToEditSkill, response);
        System.out.println("Edit skill request is successful");
    }

    @Test
    @Tag("NoCleanup")
    public void deleteSkill_whenExisting_successfully() {

        skillsController.deleteSkill(createdSkillId);

        Response response = skillsController.getSkills();
        assertResponseIsArrayAndNotEmpty(response);
        assertSkillIdIsNotNullAndSkillIsNotEmpty(response);
        assertSkillIsNotPresent(createdSkillId, response);
        System.out.println("Delete skill request is successful");
    }
}
