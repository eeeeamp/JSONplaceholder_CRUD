package Comments;

import Utils.UtilManager;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostACommentTest extends BaseTest {


    @Test
    public void postAComment() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonRequest.toString())
                .when()
                .post(UtilManager.BASE_URL + UtilManager.COMMENTS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        assertEquals(randomPostId, jsonResponse.getInt("postId"));
        assertEquals(fakeName, jsonResponse.get("name"));
        assertEquals(fakeEmail, jsonResponse.get("email"));
        assertEquals(fakeBody, jsonResponse.get("body"));

    }
}
