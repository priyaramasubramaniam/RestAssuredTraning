package Day5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponseData {
    //@Test
    void testXMLResponse()
    {
        //Approach 1
        /* given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page", equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
                */
        //Approach 2
        Response res = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
        String id = res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(id, "1");
        String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals(travelerName, "Developer");
    }

    @Test
    void testXMLResponseBody()
    {
        Response res = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");
        // Get total number of travellers
        XmlPath xmlPath = new XmlPath(res.asString());
        List <String> travellers = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travellers.size(), 10);

        List <String> travellersName = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        boolean status = false;
        for (String travellerName:travellersName)
        {
            System.out.println(travellerName);
            if(travellerName.equals("Ashor"))
            {
                status = true;
                break;
            }
        }
        Assert.assertEquals(status, true);
        Assert.assertEquals(status, true);

    }

}
