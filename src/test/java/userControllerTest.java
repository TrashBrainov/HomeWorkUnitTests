import io.restassured.path.json.JsonPath;
import models.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static configurations.TestConfig.BASE_URL;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

public class userControllerTest {


    @Test
    void createUserSchemaTest() {

        String endpoint = "https://petstore.swagger.io/v2/user";
        String jsonBody = """
                  {
                  "id": 0,
                  "username": "Test",
                  "firstName": "New_Test",
                  "lastName": "New_Test_LastName",
                  "email": "test@mail.ru",
                  "password": "1234567",
                  "phone": "+79991111111",
                  "userStatus": 1
                }
                """;
        given().
                log().
                all().
                header("accept", "application/json").
                header("Content-Type", "application/json").
                body(jsonBody).
                when().
                post(endpoint).
                then().
                log().
                all().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonsSchema/createUserSchema.json"));
    }

    @Test
    void loginUserSchemaTest() {
        String endpoint = "https://petstore.swagger.io/v2/user/login?username=dsadfdsfd&password=sdasd";

        given().
                log().
                all().
                header("Content-Type", "application/json").
                when().
                get(endpoint).
                then().
                log().
                all().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonsSchema/createUserSchema.json"));

    }


    @Test
    void logoutSchemaTest() {
        String endpoint = "https://petstore.swagger.io/v2/user/logout";

        given().
                log().
                all().
                header("Content-Type", "application/json").
                when().
                get(endpoint).
                then().
                log().
                all().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonsSchema/createUserSchema.json"));

    }

    @Test
    void findSchemaTest() {
        String endpoint1 = "https://petstore.swagger.io/v2/user";
        String endpoint2 = "https://petstore.swagger.io/v2/user/Test";
        String jsonBody = """
                  {
                  "id": 0,
                  "username": "Test",
                  "firstName": "New_Test",
                  "lastName": "New_Test_LastName",
                  "email": "test@mail.ru",
                  "password": "1234567",
                  "phone": "+79991111111",
                  "userStatus": 1
                }
                """;
        given().
                log().
                all().
                header("accept", "application/json").
                header("Content-Type", "application/json").
                body(jsonBody).
                when().
                post(endpoint1);
        given().
                log().
                all().
                header("Content-Type", "application/json").
                when().
                get(endpoint2).
                then().
                log().
                all().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonsSchema/findUserSchema.json"));

    }


    @Test
    void changeSchemaTest() {

        String endpoint = "https://petstore.swagger.io/v2/user/Test";
        String jsonBody = """
                  {
                  "id": 0,
                  "username": "Test",
                  "firstName": "New_Test",
                  "lastName": "New_Test_LastName",
                  "email": "test@mail.ru",
                  "password": "1234567",
                  "phone": "+79991111111",
                  "userStatus": 1
                }
                """;
        given().
                log().
                all().
                header("Content-Type", "application/json").
                body(jsonBody).
                when().
                put(endpoint).
                then().
                log().
                all().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonsSchema/createUserSchema.json"));

    }

    @Test
    void deleteSchemaTest() {
        String endpoint1 = BASE_URL + "user";
        String endpoint2 = BASE_URL + "user/Test";
        String jsonBody = """
                  {
                  "id": 0,
                  "username": "Test",
                  "firstName": "New_Test",
                  "lastName": "New_Test_LastName",
                  "email": "test@mail.ru",
                  "password": "1234567",
                  "phone": "+79991111111",
                  "userStatus": 1
                }
                """;
        given().
                log().
                all().
                header("accept", "application/json").
                header("Content-Type", "application/json").
                body(jsonBody).
                when().
                post(endpoint1);
        given().
                log().
                all().
                header("Content-Type", "application/json").
                when().
                delete(endpoint2).
                then().
                log().
                all().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonsSchema/createUserSchema.json"));

    }


    @Test
    void jsonPathTest() {
        User expextedUser = new User(0, "Test_User", "Ivan", "Ivanov",
                "test@mail.ru", "password", "12345678", 0);
        String endpoint = BASE_URL + "user/" + expextedUser.getUsername();

        String jsonResponse = given().when().get(endpoint).asString();
        JsonPath jsonPath = from(jsonResponse);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(jsonPath.getString("firstName")).isEqualTo(expextedUser.getFirstName());
        softly.assertThat(jsonPath.getString("lastName")).isEqualTo(expextedUser.getLastName());
        softly.assertThat(jsonPath.getString("username")).isEqualTo(expextedUser.getUsername());

    }
}