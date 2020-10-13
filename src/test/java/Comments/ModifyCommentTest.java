package Comments;

import Utils.UtilManager;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifyCommentTest extends BaseTest {


    @Test
    public void modifyACommentWithPutMethod() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonRequest.toString())
                .pathParam("id", randomCommentId)
                .when()
                .put(UtilManager.BASE_URL + UtilManager.COMMENTS + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        assertEquals(randomPostId, jsonResponse.getInt("postId"));
        assertEquals(fakeName, jsonResponse.get("name"));
        assertEquals(fakeEmail, jsonResponse.get("email"));
        assertEquals(fakeBody, jsonResponse.get("body"));

    }

    @Test
    public void modifyACommentWithPatchMethod() {

        JSONObject patchRequest = new JSONObject();

        patchRequest.put("email", fakeEmail);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(patchRequest.toString())
                .pathParam("id", randomCommentId)
                .when()
                .put(UtilManager.BASE_URL + UtilManager.COMMENTS + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        assertEquals(randomCommentId, jsonResponse.getInt("id"));
        assertEquals(fakeEmail, jsonResponse.get("email"));


    }
}
