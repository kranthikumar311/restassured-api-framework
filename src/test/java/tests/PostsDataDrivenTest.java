package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostsDataDrivenTest extends BaseTest 
{

    @DataProvider(name = "postIds")
    public Object[][] postIdProvider() 
    {
        return new Object[][] {
                {1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"},
                {2, "qui est esse"},
                {3, "ea molestias quasi exercitationem repellat qui ipsa sit aut"}
        };
    }

    @Test(dataProvider = "postIds")
    public void testGetPostById(int postId, String expectedTitle) {
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", postId)
            .when()
                .get("/posts/{id}")
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.jsonPath().getInt("id"), postId);
        Assert.assertEquals(response.jsonPath().getString("title"), expectedTitle);
    }

    @DataProvider(name = "newPosts")
    public Object[][] newPostProvider() {
        return new Object[][] {
                {"First Post", "First body content", 1},
                {"Second Post", "Second body content", 2},
                {"Third Post", "Third body content", 3}
        };
    }

    @Test(dataProvider = "newPosts")
    public void testCreateMultiplePosts(String title, String body, int userId) {
        String requestBody = String.format(
                "{ \"title\": \"%s\", \"body\": \"%s\", \"userId\": %d }",
                title, body, userId
        );

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

        Assert.assertEquals(response.jsonPath().getString("title"), title);
        Assert.assertEquals(response.jsonPath().getString("body"), body);
        Assert.assertEquals(response.jsonPath().getInt("userId"), userId);
    }
}