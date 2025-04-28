package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersApiTest {

    @Test
    public void getPublicAPI() {
        Response response = RestAssured
                .given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1");

        System.out.println("Status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
