package Comments;

import Utils.UtilManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetCommentsTest {

    @Test
    public void getAllComments() {

        Response response = given()
                .when()
                .get(UtilManager.BASE_URL + UtilManager.COMMENTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        assertEquals(500, jsonResponse.getList("id").size());

    }

    @Test
    public void getOneCommentWithPathParams() {

        Response response = given()
                .pathParam("id", 2)
                .when()
                .get(UtilManager.BASE_URL + UtilManager.COMMENTS + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();
        assertEquals("Jayne_Kuhic@sydney.com", jsonResponse.get("email"));
        assertEquals(1, jsonResponse.getInt("postId"));

    }

    @Test
    public void getOneCommentWithQueryParams(){

        Response response = given()
                .queryParam("postId", 7)
                .when()
                .get(UtilManager.BASE_URL + UtilManager.COMMENTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();
        List<Integer> postIdList = jsonResponse.getList("postId");

        assertEquals(5, postIdList.size());
        assertTrue(postIdList.stream()
                .allMatch(postIdList.get(0)::equals));

    }

}
