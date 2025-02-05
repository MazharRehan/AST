package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DelRequestAuth extends BaseTest {
    @Test
    public void delPetByid() {
        // DELETE Request - Delete a pet
        Response deleteResponse = RestAssured.given()
                .cookie(sessionCookie.getName(), sessionCookie.getValue()) // Pass the cookie to the request
                .when()
                .delete("/pet/12345"); // Example API call to delete a pet by ID
        System.out.println("DELETE API Response: " + deleteResponse.getBody().asString());
        deleteResponse.then().statusCode(404);  // Validate successful response
    }
}
