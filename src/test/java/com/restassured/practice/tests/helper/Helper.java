package com.restassured.practice.tests.helper;

import io.restassured.path.json.JsonPath;

public class Helper {
    public static JsonPath rawToJson(String response) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }
}
