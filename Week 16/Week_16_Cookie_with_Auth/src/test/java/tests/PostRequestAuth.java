package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class PostRequestAuth extends BaseTest {
    @Test
    public void postPetByStatus() {
        // POST Request - Create a new pet
        JSONObject postParams = new JSONObject();
        postParams.put("id","12345");
        postParams.put("name","snowy");
        postParams.put("status","sold");

        Response postResponse = RestAssured.given()
                .cookie(sessionCookie.getName(), sessionCookie.getValue()) // Pass the cookie to the request
                .contentType("application/json")
                .body(postParams.toString())
                .when()
                .post("/pet"); // Example API call to add a new pet
        System.out.println("POST API Response: " + postResponse.getBody().asString());
        postResponse.then().statusCode(200);  // Validate successful response
    }
}
