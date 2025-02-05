package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class PostRequestExample {
    @Test
    public void addNewPet() {
        // Set the base URL
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Create JSON payload for the request
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", 12345);
        requestParams.put("name", "Tommy");
        requestParams.put("status", "available");

        // Send a POST request with the JSON body
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestParams.toString())
                .when()
                .post("/pet");
        //response.then().statusCode(200);

        // Print the response body
        System.out.println("Response Body: " + response.getBody().asString());

        // Assertions
        response.then().statusCode(200); // Validate status code
        response.then().body("name", equalTo("Tommy")); // Validate pet name
    }
}