package Users;

import Utils.UtilManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplaceholderGetTest  {


    @Test
    public void readAllUsers() {

        Response response = given()
                .when()
                .get(UtilManager.BASE_URL + UtilManager.USERS);

        System.out.println(response.asString());

        JsonPath jsonResponse = response.jsonPath();

        List<String> namesList = jsonResponse.getList("name");

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(10, namesList.size());
    }

    @Test
    public void readOneUser() {

        Response response = given()
                .when()
                .get(UtilManager.BASE_URL + UtilManager.USERS + "/1");

        Assertions.assertEquals(200, response.getStatusCode());
        JsonPath jsonResponse = response.jsonPath();
        System.out.println(response.asString());

        Assertions.assertEquals("Leanne Graham", jsonResponse.get("name"));
        Assertions.assertEquals("Kulas Light", jsonResponse.get("address.street"));

    }

    @Test
    public void readOneUserWithPathParam() {
        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get(UtilManager.BASE_URL + UtilManager.USERS + "/{userId}");

        Assertions.assertEquals(200, response.getStatusCode());

        JsonPath jsonResponse = response.jsonPath();
        System.out.println(response.asString());
        Assertions.assertEquals("Leanne Graham", jsonResponse.get("name"));
        Assertions.assertEquals("Kulas Light", jsonResponse.get("address.street"));

    }

    @Test
    public void readOneUserWithQueryParam() {
        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get(UtilManager.BASE_URL + UtilManager.USERS);

        Assertions.assertEquals(200, response.getStatusCode());

        JsonPath jsonResponse = response.jsonPath();
        System.out.println(response.asString());

        Assertions.assertEquals("Leanne Graham", jsonResponse.getList("name").get(0));
        Assertions.assertEquals("Kulas Light", jsonResponse.getList("address.street").get(0));

    }
}
