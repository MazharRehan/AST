package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetRequestAuth extends BaseTest {
    @Test
    public void getPetsByStatus() {
        // GET Request - Retrieve pets by status
        Response response = RestAssured.given()
                .cookie(sessionCookie.getName(), sessionCookie.getValue())
                .when()
                .get("/pet/findByStatus?status=pending");
        System.out.println("GET API Response: " + response.getBody().asString());
        response.then().statusCode(200);
    }
}
