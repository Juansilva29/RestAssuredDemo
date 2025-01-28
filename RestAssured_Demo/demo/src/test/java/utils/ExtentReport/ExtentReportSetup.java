package utils.ExtentReport;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportSetup implements ITestListener {

    public static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    public void onStart(ITestContext context) {
        String fileName = ExtentReportManager.getReportNameWithTimeStamp();
        String fullReportPath = System.getProperty("user.dir") + "//extent-reports//" + fileName;     // Define la ruta del archivo del reporte.
        extentReports = ExtentReportManager.createInstance(fullReportPath, "Test API Automation Report","Test ExecutionReport");     // Crea la instancia de ExtentReports con título y descripción.
    }

    public void onFinish(ITestContext context) {
        if (extentReports != null)
            extentReports.flush(); // Llama a extentReports.flush() para guardar el reporte al finalizar las pruebas.
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(
                // "Test Name " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName(),
                "Test Name - " + result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.assignCategory(result.getTestClass().getName());
        extentTest.set(test);
    }

    public void onTestFailure(ITestResult result) {
        ExtentReportManager.logFailureDetails("Test Failed");

    }

    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.logWarningDetails("Test Skipped");
    }


    public static void deleteAllFilesInFolder(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists()) {
            if (folder.isDirectory()) {
                File[] files = folder.listFiles();

                if (files != null && files.length > 0) {
                    deleteFiles(files);
                    System.out.println("All files in the folder have been deleted.");
                } else {
                    System.out.println("The folder is empty. No files to delete.");
                }
            } else {
                System.out.println("The specified path is not a folder.");
            }
        } else {
            System.out.println("The specified folder does not exist.");
        }
    }

    private static void deleteFiles(File[] files) {
        for (File file : files) {
            if (file.isFile()) {
                if (file.delete()) {
                    System.out.println("Deleted file: " + file.getName());
                } else {
                    System.out.println("Failed to delete file: " + file.getName());
                }
            }
        }
    }

}
