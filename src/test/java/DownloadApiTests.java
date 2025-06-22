import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@Story("Download API")
class DownloadApiTests {

    @Test
    void uploadTest() {
        int petId = 1;
        String url = String.format("https://petstore.swagger.io/v2/pet/%s/uploadImage", petId);
        String fileName = "Octocat.png";
        File file = new File(fileName);

        given().
                when().
                multiPart("file", file, "image/png").
                contentType("multipart/form-data").
                accept("application/json").
                post(url).
                then().
                log().
                all().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200).
                body("code", equalTo(200)).
                body("type", equalTo("unknown")).
                body("message", containsString("File uploaded to ./" + fileName));
    }

    @Test
    void testDownloadHttpClient() {
        String endpoint = "https://alfabank.servicecdn.ru/site-upload/67/dd/356/zayavlenie-IZK.pdf";
        String fileName = "downloaded.pdf";

                given().
                        when().
                        get(endpoint).
                        then().
                        assertThat().
                        contentType("application/pdf").
                        statusCode(200);
    }
}