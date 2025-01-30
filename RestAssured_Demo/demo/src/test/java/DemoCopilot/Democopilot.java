package DemoCopilot;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;


public class Democopilot {

@BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testGetUsers() {
        Response response = given()
                                .when()
                                .get("/api/users?page=2")
                                .then()
                                .statusCode(200)
                                .body("data", not(empty()))
                                .body("data[0].id", notNullValue())
                                .body("data[0].email", notNullValue())
                                .extract()
                                .response();
        
        System.out.println(response.asString());
    }
    
}
