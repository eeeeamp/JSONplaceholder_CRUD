package Users;

import Utils.UtilManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPOSTTest  {

    private String jsonBody = "{\n" +
            "    \"name\": \"Emilka Test\",\n" +
            "    \"username\": \"Emilka\",\n" +
            "    \"email\": \"emilka@test.pl\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"Kulas Light\",\n" +
            "      \"suite\": \"Apt. 556\",\n" +
            "      \"city\": \"Gwenborough\",\n" +
            "      \"zipcode\": \"92998-3874\",\n" +
            "      \"geo\": {\n" +
            "        \"lat\": \"-37.3159\",\n" +
            "        \"lng\": \"81.1496\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"phone\": \"1-770-736-8031 x56442\",\n" +
            "    \"website\": \"hildegard.org\",\n" +
            "    \"company\": {\n" +
            "      \"name\": \"Romaguera-Crona\",\n" +
            "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "      \"bs\": \"harness real-time e-markets\"\n" +
            "    }\n" +
            "  }";

    @Test
    public void createAUser() {

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post(UtilManager.BASE_URL + UtilManager.USERS)
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        assertEquals("Emilka Test", jsonResponse.get("name"));

    }
}
