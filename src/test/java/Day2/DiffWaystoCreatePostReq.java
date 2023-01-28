package Day2;
/*
    1) Using Hashmap
    2) Using json.org - Add dependency josn.org
    3) Using POJO
    4)
 */

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DiffWaystoCreatePostReq {

    //@Test(priority = 1)
    void testPostUsingHasmap()
    {
        HashMap data = new HashMap();
        data.put("name", "sugisp");
        data.put("location", "Chennai");
        data.put("phone", "123456789");

        String courseArr[] = {"c", "c++"};
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("sugisp"))
                .body("location", equalTo("Chennai"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("c"))
                .body("courses[1]", equalTo("c++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    // 2) Using json.org
    //@Test(priority = 1)
    void testPostUsingJsonObject()
    {
        JSONObject data = new JSONObject();
        data.put("name", "Lavanya");
        data.put("location", "Madurai");
        data.put("phone", "123456");

        String courseArr[] = {"c", "c++"};
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                //.statusCode(201)
                .body("name", equalTo("Lavanya"))
                .body("location", equalTo("Madurai"))
                .body("phone", equalTo("123456"))
                .body("courses[0]", equalTo("c"))
                .body("courses[1]", equalTo("c++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    // 2) Using POJO(Plain Old Java Object)
    //@Test(priority = 1)
    void testPostUsingPOJO()
    {
        Pojo data = new Pojo();
        data.setName("Scott");
        data.setLocation("Goa");
        data.setPhone("123456");

        String courses[] = {"c", "c++"};
        data.setCourses(courses);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                //.statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("Goa"))
                .body("phone", equalTo("123456"))
                .body("courses[0]", equalTo("c"))
                .body("courses[1]", equalTo("c++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    //4) Using External json file
    @Test(priority = 1)
    void testPostUsingExternalJsonFile() throws FileNotFoundException {
        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Karthi"))
                .body("location", equalTo("Theni"))
                .body("phone", equalTo("123456"))
                .body("courses[0]", equalTo("Civi"))
                .body("courses[1]", equalTo("Mech"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    @Test(priority = 2)
    void deleteStudent()
    {
        given()
                .when()
                .delete("http://localhost:3000/students/5")
                .then()
                .statusCode(200)
                .log().all();
    }
}
