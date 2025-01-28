package Api.DemoTest.FunctionalScenarios;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Api.DemoTest.setup.DemoRequest;
import Config.BaseSetup;
import io.restassured.response.Response;
import utils.APIWrapper;
import utils.RestAssuredConfiguration;
import utils.ExtentReport.ExtentReportSetup;

@Listeners(ExtentReportSetup.class)

public class TestWithExtent extends BaseSetup{

    private static Response response;

    @Test(description = "RequestDemoPostExtent")
    public void RequestDemoPostExtent() {
        DemoRequest demoRequest = DemoRequest.createDemoRequest();
        demoRequest.setName("Felipe");
        demoRequest.setJob("Automation QA");
        response = APIWrapper.postExtentReport("/api/users", demoRequest);
        APIWrapper.displayResponse(response);
        RestAssuredConfiguration.validateResponse201(response);
    }

    @Test(description = "RequestDemoGetExtent")
    public void RequestDemoGetExtent() {
        response = APIWrapper.getExtentReport("/api/users/2");
        APIWrapper.displayResponse(response);
        RestAssuredConfiguration.validateResponse200(response);
    }

}
