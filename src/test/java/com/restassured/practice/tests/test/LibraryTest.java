package com.restassured.practice.tests.test;

import com.restassured.practice.tests.data.Payload;
import com.restassured.practice.tests.helper.Helper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LibraryTest {
    JsonPath jsonPath;

    @Test
    public void addBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response =
        given().header("Content-Type", "application/json")
                .body(Payload.AddBook("zgtsfd", "1234"))
        .when().post("/Library/Addbook.php")
        .then().assertThat().statusCode(200).extract().response().asString();

        jsonPath = Helper.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);
    }
}
