package Config;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import utils.ConfigManager;
import utils.ExtentReport.ExtentReportSetup;

public class BaseSetup {

    @BeforeSuite
    public void setup() {
        String baseHost = ConfigManager.getBaseUrl();
        System.out.println(baseHost);
        RestAssured.baseURI = baseHost;
        final String reportsFolder = "extent-reports";
        ExtentReportSetup.deleteAllFilesInFolder(reportsFolder);


    }

}
