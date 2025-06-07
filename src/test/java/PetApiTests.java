import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetApiTests {
    @Test
    void createPetTest() {
        String endpoint = "https://petstore.swagger.io/v2/pet";
        String jsonBody = """
{
  "id": 0,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
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
    void changeUserTest() {
        String endpoint1 = "https://petstore.swagger.io/v2/pet";
        String jsonBody = """
{
  "id": 0,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}
                 """;
        String jsonBody2 = """
{
  "id": 0,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "doggie2",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
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
                body(jsonBody2).
                when().
                put(endpoint1).
                then().
                log().
                all().
                statusCode(200);

    }

}


