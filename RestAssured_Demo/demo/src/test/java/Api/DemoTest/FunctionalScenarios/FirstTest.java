package Api.DemoTest.FunctionalScenarios;

import org.testng.annotations.Test;
import com.google.gson.Gson;
import Api.DemoTest.setup.DemoRequest;
import Config.BaseSetup;

import static io.restassured.RestAssured.given;

public class FirstTest extends BaseSetup {

    @Test(description = "RequestDemoPost")
    public void requestDemoPost() {
        DemoRequest demoRequest = DemoRequest.createDemoRequest();
        demoRequest.setJob("QA Manual");
        demoRequest.setName("Juan Felipe ");
        Gson gson = new Gson();
        String DemoRequest = gson.toJson(demoRequest);
        given()
                .body(DemoRequest)
                .when().log().all()
                .post("/api/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);

    }

    @Test(description = "RequestDemoGet")
    public void RequestDemoGet() {
        DemoRequest demoRequest = DemoRequest.createDemoRequest();
        Gson gson = new Gson();
        String DemoRequest = gson.toJson(demoRequest);
        given()
                .body(DemoRequest)
                .when().log().all()
                .get("/api/users/2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }



}
