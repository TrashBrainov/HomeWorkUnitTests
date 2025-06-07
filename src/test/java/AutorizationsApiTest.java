import configurations.TestConfig;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


class AutorizationsApiTest {
    TestConfig config = new TestConfig();

    @Test
    void tokenTest() {
        String url = "https://www.ae.com/ugp-api/auth/oauth/v4/token";

 given().
                when().
                header("Authorization", config.getBasicAuth()).
                header("Aesite", "AEO_US").
                contentType("application/x-www-form-urlencoded").
                formParam("grant_type", "client_credentials").
                post(url).
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                body("scope", equalTo("guest")).
                body("token_type", equalTo("client_credentials")).
                body("expires_in", equalTo(1800));
    }
}