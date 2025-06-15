package configurations;

public class TestConfig {
    public static final String BASE_URL = "https://petstore.swagger.io/v2/";
    private static final String BASE_AUTH = "Basic MjBlNDI2OTAtODkzYS00ODAzLTg5ZTctODliZmI0ZWJmMmZlOjVmNDk5NDVhLTdjMTUtNDczNi05NDgxLWU4OGVkYjQwMGNkNg==";

    public String getBaseUrl() {
        String baseUrl = System.getProperty("baseUrl", BASE_URL);
        return baseUrl;
    }
    public String getBasicAuth() {
        return System.getProperty("baseAuth", BASE_AUTH);
    }

}