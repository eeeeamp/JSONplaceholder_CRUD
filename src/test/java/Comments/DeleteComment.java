package Comments;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static Utils.UtilManager.BASE_URL;
import static Utils.UtilManager.COMMENTS;
import static io.restassured.RestAssured.given;

public class DeleteComment extends BaseTest {

    @Test
    public void deleteComment(){

        given()
                .pathParam("id", randomCommentId)
                .when()
                .delete(BASE_URL + COMMENTS + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

}
