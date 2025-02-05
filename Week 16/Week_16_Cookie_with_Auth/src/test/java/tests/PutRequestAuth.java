package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class PutRequestAuth extends BaseTest {
    @Test
    public void putPetByName() {
        // PUT Request - Update pet details
        JSONObject putParams = new JSONObject();
        putParams.put("id","12345");
        putParams.put("name","fluffy(updated from snowy)");
        putParams.put("status","pending");

        Response putResponse = RestAssured.given()
                .cookie(sessionCookie.getName(), sessionCookie.getValue()) // Pass the cookie to the request
                .contentType("application/json")
                .body(putParams.toString())
                .when()
                .put("/pet"); // Example API call to update a pet
        System.out.println("PUT API Response: " + putResponse.getBody().asString());
        putResponse.then().statusCode(200);  // Validate successful response
    }
}
