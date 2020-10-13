package Users;

import Utils.UtilManager;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplaceholderDeleteTest {

    @Test
    public void deleteUser() {

        Response response = given()
                .when()
                .delete(UtilManager.BASE_URL + UtilManager.USERS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

    }
}
