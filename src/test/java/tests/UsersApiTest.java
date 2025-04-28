package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UsersApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    public void testGetSingleUser() {
        Response response = RestAssured
                .given()
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response Body: " + response.asString());
        Assert.assertEquals(response.jsonPath().getString("data.first_name"), "Janet");
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println("Response Body: " + response.asString());
        Assert.assertNotNull(response.jsonPath().getString("id"));
    }
}
