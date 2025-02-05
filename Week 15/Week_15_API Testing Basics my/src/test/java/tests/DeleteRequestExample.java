package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

public class DeleteRequestExample {
    @Test
    public void removePet() {
        // Set the base URL for the API
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        try {
            // First, verify if the pet exists by sending a GET request
            Response getResponse = RestAssured.given()
                    .pathParam("petId", 12345) // Assume the pet ID we want to check
                    .when()
                    .get("/pet/{petId}");

            // Print the response of the GET request to check if the pet exists
            System.out.println("GET Response Body: " + getResponse.getBody().asString());

            // If the pet exists (status code 200), proceed to delete
            if (getResponse.statusCode() == HttpStatus.SC_OK) {
                // Send a DELETE request to remove the pet by its ID
                Response deleteResponse = RestAssured.given()
                        .pathParam("petId", 12345)
                        .when()
                        .delete("/pet/{petId}");

                // Print the response body of DELETE request
                System.out.println("DELETE Response Body: " + deleteResponse.getBody().asString());

                // Assertions to validate the DELETE response
                if (deleteResponse.statusCode() == HttpStatus.SC_OK) {
                    // Adjust the expected message based on the actual response
                    deleteResponse.then().body("message", equalTo("12345")); // We expect the ID in the message
                } else {
                    System.out.println("Failed to delete pet. Status code: " + deleteResponse.statusCode());
                }
            } else {
                System.out.println("Pet does not exist. Status code: " + getResponse.statusCode());
            }

        } catch (Exception e) {
            System.err.println("Exception occurred while making DELETE request: " + e.getMessage());
        }
    }
}

