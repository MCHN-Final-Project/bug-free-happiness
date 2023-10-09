package test.cases.apiTests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled ("Currently not implemented E2E test")
public class PostEndToEndTests extends PostControllerTests {

    @Test
    public void CRUD_Post_Flow_With_Valid_Data_Successfully() {
        create_Post_With_Valid_Data();
        System.out.println("Post created successfully");
        edit_Post_With_Valid_Data();
        System.out.println("Post edited successfully");
        like_Post_When_Post_Exists();
        System.out.println("Post liked successfully");
        delete_Post_When_Post_Exists();
        System.out.println("Post deleted successfully");
    }
}
