package com.restassured.practice.tests.test;

import com.restassured.practice.tests.data.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class Courses {
    public static void main(String[] args) {
        /*
        1. Print No of courses returned by API
        2.Print Purchase Amount
        3. Print Title of the first course
        4. Print All course titles and their respective Prices
        5. Print no of copies sold by RPA Course
        6. Verify if Sum of all Course prices matches with Purchase Amount
         */
        JsonPath jsonPath;
        jsonPath = new JsonPath(Payload.CoursePrices());

        int countCourses = jsonPath.getInt("courses.size()");
        System.out.println(countCourses);

        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        String firstCourseTitle = jsonPath.get("courses[0].title");
        System.out.println(firstCourseTitle);

        String courseTitle = "";
        int coursePrice = 0;
        int numberOfCopies = 0;
        int totalAmount = 0;

        for(int i=0; i<countCourses; i++) {
            courseTitle = jsonPath.get("courses[" + i + "].title");
            coursePrice = jsonPath.getInt("courses[" + i + "].price");
            System.out.println("Course title : " + courseTitle + "\tCourse price : " + coursePrice);
        }

        for(int i=0; i<countCourses; i++) {
            courseTitle = jsonPath.get("courses[" + i + "].title");
            if(courseTitle.equalsIgnoreCase("RPA")) {
                numberOfCopies = jsonPath.getInt("courses[" + i + "].copies");
                System.out.println("Course title : " + courseTitle +
                        "\tCourse copies sold : " + numberOfCopies);
                break;
            }
        }

        for(int i=0; i<countCourses; i++) {
            totalAmount += jsonPath.getInt("courses[" + i + "].price") *
                    jsonPath.getInt("courses[" + i + "].copies");
        }
        if(totalAmount == purchaseAmount) {
            System.out.println("Total amount : " + totalAmount +
                    " and Purchase Amount : " + purchaseAmount + "\nIt's a MATCH....");
        }
        else {
            System.out.println("Total amount : " + totalAmount +
                    " and Purchase Amount : " + purchaseAmount + "\nIt's not a MATCH....");
        }
        Assert.assertEquals(totalAmount, purchaseAmount);
    }
}
