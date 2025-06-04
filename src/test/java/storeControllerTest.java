import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class storeControllerTest {
    @Test
    void jsonSchemaTest() {
        String endpoint = "https://petstore.swagger.io/v2/store/inventory";
        given().
                header("accept", "application/json").
                when().
                get(endpoint).
                then().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonsSchema/storeSchema.json"));
    }

}
