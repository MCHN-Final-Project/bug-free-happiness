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
    private static int createdNewSkillId;
    private static String randomSkillText_Create;
    private static String responseText;
    static SkillsController skillsController = new SkillsController();


    @BeforeEach
    public void local_setup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoSetup")) return;
        Response response = skillsController.getSkills();
        userCategoryId = response.getBody().jsonPath().getString("[0].category.id");
        userCategoryName = response.getBody().jsonPath().getString("[0].category.name");
        randomSkillText_Create = "Created skill: " + getRandomSentence();

        if (testInfo.getTags().contains("PartialSetup")) return;
        SkillModel response1 = skillsController.createSkill(userCategoryId, userCategoryName, randomSkillText_Create);
        createdNewSkillId = response1.skillId;
        responseText = response1.skill;

    }

    @AfterEach
    public void local_cleanup(TestInfo testInfo) {
        if (testInfo.getTags().contains("NoCleanup")) return;
        skillsController.deleteSkill(createdNewSkillId);
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

        String userCategoryIdToCreateIn = userCategoryId;
        String userCategoryNameToCreateIn = userCategoryName;
        String textToCreateSkill = randomSkillText_Create;

        SkillModel response = skillsController.createSkill
                (userCategoryIdToCreateIn, userCategoryNameToCreateIn, textToCreateSkill);

        Assertions.assertTrue(response.skillId > -1);
        Assertions.assertEquals(randomSkillText_Create, response.skill);
        System.out.println("Create skill request is successful");

        createdNewSkillId = response.skillId;
    }

    @Test
    @Tag("NoCleanup")
    public void getSkill_byId_whenExisting_successfully() {

        int skillIdToGet = createdNewSkillId;
        String createdSkillText = randomSkillText_Create;
        String responseSkillText = responseText;

        skillsController.getSkillById(skillIdToGet);

        Assertions.assertEquals(createdSkillText, responseSkillText);
        System.out.println("Get skill by Id request is successful");
    }

    @Test
    public void editSkill_withNewSkillText_successfully() {

        int skillIdToEdit = createdNewSkillId;
        String randomSkillText_Edit = "Edited Skill: " + getRandomSentence();

        skillsController.editSkill(randomSkillText_Edit, skillIdToEdit);

        Response response1 = skillsController.getSkillById(skillIdToEdit);
        assertResponseBodyIsNotEmpty(response1);
        assertNewSkillContentIsCorrect(randomSkillText_Edit, response1);
        System.out.println("Edit skill request is successful");
    }

    @Test
    @Tag("NoCleanup")
    public void deleteSkill_whenExisting_successfully() {

        int skillIdToDelete = createdNewSkillId;

        skillsController.deleteSkill(skillIdToDelete);

        Response response1 = skillsController.getSkills();
        assertResponseIsArrayAndNotEmpty(response1);
        assertSkillIdIsNotNullAndSkillIsNotEmpty(response1);
        assertSkillIsNotPresent(skillIdToDelete, response1);
        System.out.println("Delete skill request is successful");
    }
}
