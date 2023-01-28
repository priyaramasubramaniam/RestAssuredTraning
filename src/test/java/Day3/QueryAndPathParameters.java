package Day3;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class QueryAndPathParameters {

    /* https://reqres.in/api/users?page=2&id=5
        pathParams = users
        queryParams = page=2 and id=5
     */


    @Test
    public void testQueryAndPathParameter()
    {
        given()
                .pathParam("myPath", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when()
                .get("https://reqres.in/api/{myPath}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
