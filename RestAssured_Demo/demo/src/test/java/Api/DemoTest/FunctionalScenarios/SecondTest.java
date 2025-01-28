package Api.DemoTest.FunctionalScenarios;


import org.testng.annotations.Test;


import Api.DemoTest.setup.DemoRequest;
import Config.BaseSetup;
import io.restassured.response.Response;
import utils.APIWrapper;
import utils.RestAssuredConfiguration;


public class SecondTest extends BaseSetup {

    private static Response response;

    @Test(description = "RequestDemoPost")
    public void requestDemoPost() {
        DemoRequest demoRequest = DemoRequest.createDemoRequest();
        demoRequest.setName("Felipe");
        demoRequest.setJob("Automation QA");
        response=APIWrapper.post("/api/users", demoRequest);
        APIWrapper.displayResponse(response);
        RestAssuredConfiguration.validateResponse201(response);
    }


}
