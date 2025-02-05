package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

public class PutRequestExample {
    @Test
    public void updatePet() {
        // Set the base URL for the API
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        try {
            // Create JSON payload to update the pet
            JSONObject requestParams = new JSONObject();
            requestParams.put("id", 12345);  // Pet ID to update
            requestParams.put("name", "Tommy Updated");
            requestParams.put("status", "sold");

            // Send a PUT request to update the pet
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(requestParams.toString())
                    .when()
                    .put("/pet");

            // Print the response body
            System.out.println("Response Body: " + response.getBody().asString());

            // Assertions to validate the response
            if (response.statusCode() == HttpStatus.SC_OK) {
                response.then().body("name", equalTo("Tommy Updated"));
                response.then().body("status", equalTo("sold"));
            } else {
                System.out.println("Failed to update pet. Status code: " + response.statusCode());
            }

        } catch (Exception e) {
            System.err.println("Exception occurred while making PUT request: " + e.getMessage());
        }
    }
}
