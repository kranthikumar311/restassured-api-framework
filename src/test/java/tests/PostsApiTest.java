package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostsApiTest extends BaseTest {

    // ==================== GET ====================

    @Test
    public void testGetSinglePost() {
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", 1)
            .when()
                .get("/posts/{id}")
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("title"), "Title should not be null");
    }

    @Test
    public void testGetAllPosts() {
        Response response = given()
                .spec(requestSpec)
            .when()
                .get("/posts")
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .response();

        int postCount = response.jsonPath().getList("$").size();
        Assert.assertEquals(postCount, 100, "Total posts should be 100");
    }

    @Test
    public void testGetNonExistentPost() {
        given()
                .spec(requestSpec)
                .pathParam("id", 9999)
            .when()
                .get("/posts/{id}")
            .then()
                .statusCode(404);
    }

    // ==================== POST ====================

    @Test
    public void testCreatePost() {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
            .when()
                .post("/posts")
            .then()
                .spec(responseSpec)
                .statusCode(201)
                .extract()
                .response();

        Assert.assertNotNull(response.jsonPath().getString("id"), "ID should not be null");
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
        Assert.assertEquals(response.jsonPath().getString("body"), "bar");
        Assert.assertEquals(response.jsonPath().getInt("userId"), 1);
    }

    // ==================== PUT (Full Update) ====================

    @Test
    public void testUpdatePostWithPut() {
        String requestBody = "{ \"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1 }";

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", 1)
                .body(requestBody)
            .when()
                .put("/posts/{id}")
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.jsonPath().getString("title"), "updated title");
        Assert.assertEquals(response.jsonPath().getString("body"), "updated body");
    }

    // ==================== PATCH (Partial Update) ====================

    @Test
    public void testUpdatePostWithPatch() {
        String requestBody = "{ \"title\": \"patched title\" }";

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", 1)
                .body(requestBody)
            .when()
                .patch("/posts/{id}")
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.jsonPath().getString("title"), "patched title");
    }

    // ==================== DELETE ====================

    @Test
    public void testDeletePost() {
        given()
                .spec(requestSpec)
                .pathParam("id", 1)
            .when()
                .delete("/posts/{id}")
            .then()
                .statusCode(200);
    }
}