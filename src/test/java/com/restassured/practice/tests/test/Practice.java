package com.restassured.practice.tests.test;

import com.restassured.practice.tests.data.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Practice {
    public static void main(String[] args) {
        JsonPath jsonPath;
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Add Place API
        String response =
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace())
        .when().post("/maps/api/place/add/json")
        .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);

        jsonPath = new JsonPath(response);
        String placeID = jsonPath.getString("place_id");
        System.out.println(placeID);

        //Update Place API
        String newAddress = "70 Summer walk, USA";
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeID + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
        .when().put("/maps/api/place/update/json")
        .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        String getPlaceResponse =
        given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeID)
        .when().get("/maps/api/place/get/json")
        .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        jsonPath = new JsonPath(getPlaceResponse);
        String actualAddress = jsonPath.getString("address");
        System.out.println(actualAddress);
    }
}
