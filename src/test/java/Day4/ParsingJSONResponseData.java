package Day4;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class ParsingJSONResponseData {
    //@Test
    void testJSONResponse() {
        //Approach 1
        /*
        given()
                .when()
                .get("http://localhost:3000/store")

                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("books[2].title", equalTo("ref"));
           */

        //Approach 2
        Response res = given()
                .when()
                .get("http://localhost:3000/store");

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
        String bookName = res.jsonPath().get("books[2].title");
        Assert.assertEquals(bookName, "ref");
    }

    @Test
    void testResponseBodyData()
    {
        Response res = given()
                .when()
                .get("http://localhost:3000/store");
        JSONObject jsonObject = new JSONObject(res.asString()); //Converting json response as string

        //Get title of the all books
        /*
        for(int i=0; i<jsonObject.getJSONArray("books").length(); i++)
        {
            String title = jsonObject.getJSONArray("books").getJSONObject(i).get("title").toString();
            System.out.println(title);
        }
        */

        //Search for book present or not -- Validation 1
        /* boolean status = false;
        for(int i=0; i<jsonObject.getJSONArray("books").length(); i++)
        {
            String title = jsonObject.getJSONArray("books").getJSONObject(i).get("title").toString();
            if(title.equals("ref"))
            {
                status = true;
                System.out.println("Book found");
                break;
            }
        }
        Assert.assertEquals(status, true);
         */

        //Count total of the book's price
        double totalPrice = 0;
        for(int i=0; i<jsonObject.getJSONArray("books").length();i++)
        {
            String priceasstring = jsonObject.getJSONArray("books").getJSONObject(i).get("price").toString();
            double price = Double.parseDouble(priceasstring);
            totalPrice = totalPrice+price;
        }
        Assert.assertEquals(totalPrice, 156.09);
    }


}
