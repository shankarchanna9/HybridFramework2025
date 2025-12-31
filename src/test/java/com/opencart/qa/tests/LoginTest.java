package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencart.qa.exceptions.FrameworkException;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.pages.LoginPage;
import com.opencart.qa.utils.ExtentReporterManager;

@Listeners(ExtentReporterManager.class)
public class LoginTest extends BaseTest{
	HomePage homePage;
	LoginPage loginPage;
	
	@Test(groups = "Smoke")
	void loginToApplication() {
		try {
			logger.info("*** Test execution started ***");
			homePage = new HomePage(driver);
			homePage.navigateToLoginPage();
			logger.info("*** Navigated to login page ***");
			loginPage = new LoginPage(driver);
			loginPage.loginToApp();
			Assert.assertTrue(loginPage.loginStatus());
		} catch (Exception e) {		
			logger.debug(e.getMessage());
			throw new FrameworkException(e.getMessage());
		}
	}
	
	
	
}



