package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestAssuredConfiguration {

    static Gson gsonNull = new GsonBuilder().serializeNulls().create();
    static Gson gson = new Gson();
    static Gson gsonExclusion = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setExclusionStrategies(new RequiredExclusionStrategy())
            .create();
    static Gson gsonExclusionNotNull = new GsonBuilder()
            .setPrettyPrinting()
            .setExclusionStrategies(new RequiredExclusionStrategy())
            .create();

    public static RequestSpecification getPlainRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getBaseUrl())
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
    }

    public static RequestSpecification getRequestSpecification(String endPoint, Object requestPayload) {
        return RestAssured.given()
                .baseUri(ConfigManager.getBaseUrl() + endPoint)
                .contentType(ContentType.JSON)
                .body(gson.toJson(requestPayload))
                .log().method()
                .log().uri()
                .log().headers()
                .log().body();
    }

    
    public static RequestSpecification getRequestSpecificationGet(String endPoint) {
        return RestAssured.given()
                .baseUri(ConfigManager.getBaseUrl() + endPoint)
                .contentType(ContentType.JSON)
                .log().method()
                .log().uri()
                .log().headers()
                .log().body();
    }

    public static void validateResponse200(Response response) {
        response.then()
                .spec(get200ResponseSpecification());
    }

    public static void validateResponse201(Response response) {
        response.then()
                .spec(get201ResponseSpecification());
    }

    public static ResponseSpecification get200ResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification get201ResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

}
