package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.inerbanking.utilities.ReadConfig;
import com.inerbanking.utilities.Reporting;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	
	//public String baseURL=readconfig.getaApplicationURL();
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger log;
	

	@Parameters({"browser","url"})
	
	@BeforeMethod(alwaysRun=true)
	public void setup(String br,String bURL)
	{
		//Logger log = Logger.getLogger(BaseClass.class);
		log  = Logger.getLogger("eBanking");
		PropertyConfigurator.configure("log4j.properties");
		if(br.equals("chrome"))
		{
		
		//System.setProperty("webdriver.chrome.driver", "C:/Users/sures/eclipse-workspace/inetBankingv1/Drivers/chromedriver.exe");  
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());  
       driver = new ChromeDriver();
      System.out.println("url under test : " + bURL);
      driver.get(bURL);
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	

	public void onTestStart(ITestContext testContext) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
public void captureScreen(String name) throws IOException {
	
		File scrFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/Screenshots/"+name+".png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
		}
		
	}


}

/*
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
		*/
