import controllers.UserController;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static testdata.ApiTestData.DEFAULT_USER;


public class ApiTestUser {

    @Test
    void createUserTest() {
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
                 statusCode(200);

    }

    @Test
    void loginUserTest() {
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
                statusCode(200);

    }

    @Test
    void logoutUserTest() {
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
                statusCode(200);

    }

    @Test

        void findUserTest() {
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
                statusCode(200).
                body("username", equalTo("Test")).
                body("email", equalTo("test@mail.ru"));

    }

    @Test
    void changeUserTest() {
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
        String jsonBody2 = """
  {
  "id": 0,
  "username": "Test",
  "firstName": "New_Test1",
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
                statusCode(200).
                body("username", equalTo("Test")).
                body("firstName", equalTo("New_Test"));
        given().
                log().
                all().
                header("Content-Type", "application/json").
                body(jsonBody2).
                when().
                put(endpoint2).
                then().
                log().
                all().
                statusCode(200);
        given().
                log().
                all().
                header("Content-Type", "application/json").
                when().
                get(endpoint2).
                then().
                log().
                all().
                statusCode(200).
                body("username", equalTo("Test")).
                body("firstName", equalTo("New_Test1"));

    }

    @Test
    void deleteUserTest() {
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
                delete(endpoint2).
                then().
                log().
                all().
                statusCode(200);

    }


}
