package Day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingDemo {

    @Test
    void testLogging()
    {
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .log().cookies()
                .log().headers()
                .log().ifStatusCodeIsEqualTo(200);

    }


}
