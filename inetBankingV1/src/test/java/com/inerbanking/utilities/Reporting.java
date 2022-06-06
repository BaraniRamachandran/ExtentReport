package com.inerbanking.utilities;

//Listener class used to generate Extent reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.inetbanking.testCases.BaseClass;

public  class Reporting extends BaseClass implements ITestListener 
{
	// public ExtentHtmlReporter htmlReporter;
	public ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest logger;

	public WebDriver driver;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repName = "Test-Report-" + timeStamp + ".html";

		String path = System.getProperty("user.dir") + "/reports/" + repName;
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setReportName("Automation Tests");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("project name", "internet Banking");
		extent.setSystemInfo("tester name", "Barani");

	}

	public void onTestSuccess(ITestResult tr) {

		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult tr) {

		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed
      	try {
			captureScreen(tr.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\failure.png";
		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/"+tr.getName()+".png";

		//String screenshotPath = System.getProperty("user.dir") + "/Screenshots/failure.png";

		File f = new File(screenshotPath);

		if(f.exists())
		{
		logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
		
	}

	

	

	public void onTestSkipped(ITestResult tr) {

		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
}

/*
 * public void captureScreen() throws IOException { TakesScreenshot ts =
 * (TakesScreenshot) driver; File source = ts.getScreenshotAs(OutputType.FILE);
 * // File target = new File(System.getProperty("user.dir") + "/Screenshots/" +
 * // tname + ".png"); try { FileUtils.copyFile(source, new
 * File(System.getProperty("user.dir") + "/Screenshots/")); }
 * 
 * catch (IOException e)
 * 
 * { e.printStackTrace(); } System.out.println("Screenshot taken"); } }
 */
