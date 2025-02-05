package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetRequestExample {
    @Test
    public void fetchAvailablePets() {
        // Set the base URL for the API
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Send a GET request with a query parameter
        Response response = RestAssured.given()
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus");
        //response.then().statusCode(200);


        // Print the response body
        System.out.println("Response Body: " + response.getBody().asString());

        // Assertions to validate the response
        response.then().statusCode(200); // Check if the status code is 200
        response.then().body("[0].status", equalTo("available")); // Validate the status of the first pet
    }
}
