package Day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeadersDemo {

    //@Test
    void testHeader()
    {
        given()
                .when()
                .get("https://google.co.in")

                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1");
    }

    @Test
    void getHeaderInfo()
    {
       Response res =  given()
                .when()
               .get("https://google.co.in");

       //Get single header
        String h1 = res.getHeader("Content-Type");
        System.out.println(h1);

        //Get multiple Header
        Headers headers = res.getHeaders();
        for (Header header: headers)
        {
            String headersName = header.getName();
            String headerValue = header.getValue();
            System.out.println(headersName+"    "+headerValue);
        }
    }
}
