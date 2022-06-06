package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inerbanking.utilities.Reporting;
import com.inetbanking.pageObjects.LoginPage;


public class TC_LogInTest_001 extends BaseClass {
	
	
	@Test
	public void loginTest() throws IOException
	{
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** logInTest *****************************************");
		//driver.get("baseURL");
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(username);
		lp.serPassWord(password);
		lp.clickSubmit();
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			//captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			
		
		log.info("****************************** ending test case *****************************************");
		log.info("****************************** logInTest *****************************************");
		
		}

}
}
