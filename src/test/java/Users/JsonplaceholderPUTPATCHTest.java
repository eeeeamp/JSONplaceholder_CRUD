package Users;

import Utils.UtilManager;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPUTPATCHTest  {

    private static Faker faker;
    private String fakeEmail;
    private String fakeFullName;
    private String fakeUserName;
    private String fakeWWW;
    private String fakePhone;

    @BeforeAll
    public static void beforeAll() {

        faker = new Faker();

    }

    @BeforeEach
    public void beforeEach() {

        fakeEmail = faker.internet().emailAddress();
        fakeFullName = faker.name().fullName();
        fakeUserName = faker.name().username();
        fakeWWW = faker.internet().url();
        fakePhone = faker.phoneNumber().phoneNumber();

    }

    @Test
    public void updateAnUserPut() {

        JSONObject requestBody = new JSONObject();
        JSONObject address = new JSONObject();
        JSONObject geo = new JSONObject();
        JSONObject company = new JSONObject();

        requestBody.put("name", fakeFullName);
        requestBody.put("username", fakeUserName);
        requestBody.put("email", fakeEmail);
        requestBody.put("phone", fakePhone);
        requestBody.put("website", fakeWWW);

        company.put("name", "test company");
        company.put("catchPhrase", "test company");
        company.put("bs", "test company");
        requestBody.put("company", company);

        address.put("street", "Test");
        address.put("suite", "Test");
        address.put("city", "Test");
        address.put("zipcode", "1234");

        geo.put("lat", "33");
        geo.put("lng", "33");

        address.put("geo", geo);
        requestBody.put("address", address);

        Response response = given()
                .contentType("application/json")
                .body(requestBody.toString())
                .when()
                .put(UtilManager.BASE_URL + UtilManager.USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        assertEquals(fakeFullName, jsonResponse.get("name"));
        assertEquals(fakeUserName, jsonResponse.get("username"));
        assertEquals(fakeEmail, jsonResponse.get("email"));
        assertEquals("1", jsonResponse.get("id").toString());

    }

    @Test
    public void updateAnUserPatch() {

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", fakeEmail);

        Response response = given()
                .contentType("application/json")
                .body(jsonRequest.toString())
                .when()
                .patch(UtilManager.BASE_URL + UtilManager.USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonResponse = response.jsonPath();

        assertEquals(fakeEmail, jsonResponse.get("email"));
        assertEquals("1", jsonResponse.get("id").toString());

    }
}
