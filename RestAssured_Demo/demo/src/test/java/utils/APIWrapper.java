package utils;

import com.google.gson.Gson;

import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import utils.ExtentReport.ExtentReportManager;

import static io.restassured.RestAssured.given;

public class APIWrapper {

    static RequestSpecification requestSpecification = RestAssuredConfiguration.getPlainRequestSpec();
    static Gson gson = new Gson();
    static Response response;

    public static Response post(String endpoint, Object body) {
        return given()
                .spec(requestSpecification)
                .body(gson.toJson(body))
                .post(endpoint);
    }

    public static void displayResponse(Response response) {
        System.out.println("\nHTTP Status Code: " + response.getStatusCode());
        System.out.println("\nBody: \n" + response.getBody().asPrettyString());
        System.out.println("\nError Message: " + response.getHeader("errormsg"));
    }
    
    public static Response postExtentReport(String endpoint, Object body) {
        RequestSpecification requestSpecificationExtentReport = RestAssuredConfiguration
                .getRequestSpecification(endpoint, body);
        response = requestSpecificationExtentReport.post();
        printRequestLogInReport(requestSpecificationExtentReport);
        printResponseLogInReport(response);
        return response;
    }

    public static Response getExtentReport(String endpoint) {
        RequestSpecification requestSpecificationExtentReport = RestAssuredConfiguration
                .getRequestSpecificationGet(endpoint);
        response = requestSpecificationExtentReport.get();
        printRequestLogInReport(requestSpecificationExtentReport);
        printResponseLogInReport(response);
        return response;
    }




    private static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier
                .query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint : " + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method : " + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Headers : ");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request body : ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
    }

    private static void printResponseLogInReport(Response response) {
        ExtentReportManager.logInfoDetails("Response status : " + response.getStatusCode());
        /*
         * ExtentReportManager.logInfoDetails("Response Headers are ");
         * ExtentReportManager.logHeaders(response.getHeaders().asList());
         */
        ExtentReportManager.logInfoDetails("Trace ID : " + response.getHeader("traceid"));
        ExtentReportManager.logInfoDetails("Response body : ");
        String jsonString = response.asPrettyString();
        ExtentReportManager.logJson(jsonString);
    }
}
