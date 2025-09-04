import controllers.UserController;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static testdata.ApiTestData.DEFAULT_USER;
import static testdata.ApiTestData.UPDATE_DEFAULT_USER;

@Feature("ControllerTests")
@Tag("api")
class ControllerApiTests {
    UserController userController = new UserController();

    @BeforeEach
    @AfterEach
    void clear() {
        userController.deleteUserByName(DEFAULT_USER.getUsername());
    }

    @Test
    @DisplayName("Create add user is returns 200 status ok")
    void createUserTest() {
        Response response = userController.addDefaultUser();
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Get User")
    void getUser() {
        Response addUserResponse = userController.addUser(DEFAULT_USER);
        assertThat(addUserResponse.statusCode()).isEqualTo(200);

        Response getUserResponse = userController.getUserByName(DEFAULT_USER.getUsername());
        User actualUser = getUserResponse.as(User.class);

        assertThat(getUserResponse.statusCode()).isEqualTo(200);
        assertThat(actualUser).usingRecursiveComparison().ignoringFields("id").isEqualTo(DEFAULT_USER);
    }

    @Test
    @DisplayName("Delete User")
    void deleteUser() {
        Response addUserResponse = userController.addUser(DEFAULT_USER);
        assertThat(addUserResponse.statusCode()).isEqualTo(200);

        Response deleteUserResponse = userController.deleteUserByName(DEFAULT_USER.getUsername());

        assertThat(deleteUserResponse.statusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Update User")
    void updateUser() {
        Response addUserResponse = userController.addUser(DEFAULT_USER);
        assertThat(addUserResponse.statusCode()).isEqualTo(200);

        Response updateUserByName = userController.updateUserByName(UPDATE_DEFAULT_USER.getUsername());

        assertThat(updateUserByName.statusCode()).isEqualTo(200);
    }

}