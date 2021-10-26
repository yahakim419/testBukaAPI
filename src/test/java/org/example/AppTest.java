package org.example;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    String Endpoint = "https://jsonplaceholder.cypress.io/posts";

    @Test
    public void assureDataType() {
        RestAssured.given()
                .when()
                .get(Endpoint)
                .then()
                .log()
                .ifValidationFails()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema2.json"));
    }



    @Test
    public  void postTest(){
        JSONObject requestParams = new JSONObject();

        requestParams.put("title", "Recomendation");
        requestParams.put("body", "Motorcycle");
        requestParams.put("userId", 12);

       RestAssured.given()
                .contentType("application/json")
                .body(requestParams.toJSONString())
                .post(Endpoint)
                .then()
                .statusCode(201)
                .body("title",equalTo("Recomendation")
                ,"body",equalTo("Motorcycle")
                ,"userId",equalTo(12),
                "id",equalTo(101));

    }


}
