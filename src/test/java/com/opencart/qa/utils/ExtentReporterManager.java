package com.opencart.qa.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencart.qa.tests.BaseTest;

public class ExtentReporterManager implements ITestListener{
	
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	public void onTestStart(ITestResult result) {
		
		  }
	
	public void onStart(ITestContext context) {
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/ExtentReports/myReport"+date+".html");// specificies the html report
		extentSparkReporter.config().setDocumentTitle("Automation Report");//Title of Report
		extentSparkReporter.config().setReportName("Functional Testing");//Name of the Report
		extentSparkReporter.config().setTheme(Theme.DARK);
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		
		extentReports.setSystemInfo("Environment", "QA");
//		extentReports.setSystemInfo("Browser", "Chrome");
		extentReports.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extentReports.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {//result.getMethod();
		
		extentTest = extentReports.createTest(result.getTestClass().getName());//Create a new entry in the report
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.PASS, "Testcase PASSED is: "+ result.getMethod());//Update the execution status P/F/S
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL, "Testcase FAILED is: "+result.getName());
		extentTest.log(Status.INFO, "Testcase Failed due to "+result.getThrowable().getMessage());
		
		try {
			String imgPath = BaseTest.takeScreenshot(result.getName());
			extentTest.addScreenCaptureFromPath(imgPath);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP, "TestCase SKIPPED is: "+result.getName());
	}
	public void onFinish(ITestContext context)	{
		extentReports.flush();
	}	
	
}
	
