package TestNG;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

import static Main.Launch_Browser.driver;

public class Extentreport extends TestListenerAdapter{


    public ExtentHtmlReporter htmlReporter;//Look and Feel of Reports
    public ExtentReports extent;//Enviornment Setting OS,Browser,Project Name, Host Name, User
    public ExtentTest test;//Log the Testcase status (Pass/Fail/Skip)

    {
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
        System.out.println("On start");
        htmlReporter.config().setDocumentTitle("Test Report");
        htmlReporter.config().setReportName("Testing Report");
//      htmlReporter.config().setTheme(Theme.DARK);
        extent=new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Enviornment", "QA");
        extent.setSystemInfo("Project Name", "Orange HRM");
        extent.setSystemInfo("user", "Divyash");

    }
    public void onTestSuccess(ITestResult result)
    {
        System.out.println("Test Success");
        test=extent.createTest(result.getName());
        test.log(Status.PASS, "Test Case Passed"+result.getName());

    }

    public void onTestFailure(ITestResult result)
    {
        test=extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Case Failed is:"+result.getName());
        test.log(Status.FAIL, "Test Case Failed is:"+result.getThrowable());
        File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(ss, new File("D:\\Screenshot/OrangeHRMLogin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onTestSkipped(ITestResult result)
    {
        test=extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Case Skipped is:"+result.getName());
    }
    public void onFinish(ITestContext testContext)
    {	System.out.println("On Finish");
        extent.flush();
    }
}