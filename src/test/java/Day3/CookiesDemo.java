package Day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CookiesDemo {

    //@Test
    public void testCookie()
    {
        given()
                .when()
                .get("https://google.co.in")

                .then()
                .cookie("AEC")
                .log().all();
    }

    @Test(priority = 2)
    public void getCookieInfo()
    {
       Response res = given()
                .when()
                .get("https://google.co.in");
        //Get Single cookie
        String cookie = res.getCookie("AEC");
        System.out.println(cookie);

        //Get All cookies
        Map<String, String> cookies = res.getCookies();
        Set<String> CookieKeys = cookies.keySet();
        for (String CookieKey:CookieKeys)
        {
            String cookieValue = res.getCookie(CookieKey);
            System.out.println(cookieValue);
        }
    }
}
