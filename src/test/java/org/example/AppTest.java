package org.example;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertTrue;

import io.restassured.RestAssured;
import org.junit.Test;

import java.awt.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    String Endpoint = "https://jsonplaceholder.cypress.io/posts/";
    String parameter = "1";
    @Test
    public void checkSchema() {
        RestAssured.given()
                .when()
                .get(Endpoint+parameter)
                .then()
                .log()
                .ifValidationFails()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema.json"));
    }


}
