package FirstAPI;


import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {
    @Test
    public void ApiTest() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        String status = "available";
        Response response = RestAssured
                .given()
                .header("accept", "application/json")
                //.param()
                .when()
                .get("BookStore/v1/Books")
                .then()
                .log().all()
                .extract().response();
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode, 200, "massage");
        String firstAuthor = response.jsonPath().getString("books[0].author");
        String publisherName = response.jsonPath().getString("books[0].publisher");
        Assert.assertEquals(firstAuthor, "Richard E. Silverman", "ავტორი არასწორია!");
        Assert.assertEquals(publisherName, "O'Reilly Media", "გამომცემელი არასწორია!");
    }
        // დავალება 2
        @Test
        public void ApiTest2() {
            RestAssured.baseURI = "https://bookstore.toolsqa.com";
            JSONObject requestbody = new JSONObject();
            requestbody.put("userName", "tamtakurts");
            requestbody.put("password", "Password!123");

            Response response = RestAssured
                    .given()
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(requestbody.toString())
                    .when()
                    .post("Account/v1/User")
                    .then()
                    .log().all()
                    .extract().response();
            int statuscode = response.getStatusCode();
            Assert.assertEquals(statuscode, 201, "statuscode201");
            String userID = response.jsonPath().getString("userID");
            Assert.assertNotNull(userID, "userID");


        }
    }

