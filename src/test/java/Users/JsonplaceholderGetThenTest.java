package Users;

import Utils.UtilManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderGetThenTest  {

    @Test
    public void readAllUsers() {

        Response response = given()
                .when()
                .get(UtilManager.BASE_URL + UtilManager.USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        //There is a test for the email containing .pl

        List<String> namesList = jsonResponse.getList("name");
        List<String> emailList = jsonResponse.getList("email");

        List<String> emailsWithPl = emailList
                .stream()
                .filter(email -> email.endsWith(".pl"))
                .collect(Collectors.toList());

        assertEquals(10, namesList.size());
        assertTrue(emailsWithPl.isEmpty());

    }

    @Test
    public void readOneUser() {

        given()
                .when()
                .get(UtilManager.BASE_URL + UtilManager.USERS + "/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"))
                .body("address.street", equalTo("Kulas Light"));

    }

    @Test
    public void readOneUserWithPathParam() {

        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get(UtilManager.BASE_URL + UtilManager.USERS + "/{userId}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();
        System.out.println(response.asString());
        assertEquals("Leanne Graham", jsonResponse.get("name"));
        assertEquals("Kulas Light", jsonResponse.get("address.street"));

    }

    @Test
    public void readOneUserWithQueryParam() {

        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get(UtilManager.BASE_URL + UtilManager.USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        assertEquals("Leanne Graham", jsonResponse.getList("name").get(0));
        assertEquals("Kulas Light", jsonResponse.getList("address.street").get(0));

    }
}
