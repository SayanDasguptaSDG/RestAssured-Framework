package com.restassured.practice.tests.test;

import com.restassured.practice.tests.data.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CoursesTest {
    JsonPath jsonPath;

    @Test
    public void numberOfCourses() {
        jsonPath = new JsonPath(Payload.CoursePrices());
        int countCourses = jsonPath.getInt("courses.size()");
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        int totalAmount = 0;

        for(int i=0; i<countCourses; i++) {
            totalAmount += jsonPath.getInt("courses[" + i + "].price") *
                    jsonPath.getInt("courses[" + i + "].copies");
        }
        Assert.assertEquals(totalAmount, purchaseAmount);
    }
}
