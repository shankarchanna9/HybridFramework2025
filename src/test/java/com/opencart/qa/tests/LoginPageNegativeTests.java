package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencart.qa.exceptions.FrameworkException;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.pages.LoginPage;
import com.opencart.qa.utils.ExtentReporterManager;

@Listeners(ExtentReporterManager.class)
public class LoginPageNegativeTests extends BaseTest{
	HomePage homePage;
	LoginPage loginPage;
	
	
	@Test(dataProvider = "getTestData",groups = "Regression")
	void loginToApplication(String emailId,String password) {
		try {
			logger.info("*** Test execution started ***");
			homePage = new HomePage(driver);
			homePage.navigateToLoginPage();
			logger.info("*** Navigated to login page ***");
			loginPage = new LoginPage(driver);
			loginPage.loginToApp(emailId,password);
			Assert.assertEquals(loginPage.getWarningMsg(), "Warning: No match for E-Mail Address and/or Password.");
		}catch (Exception e) {
				logger.debug(e.getMessage());
				throw new FrameworkException(e.getMessage());
			}
		}
	
	@Test(dataProvider = "getTestDataUsingExcel",groups = "Regression")
	void loginToApplicationUsingExcel(String emailId,String password) {
		try {
			logger.info("*** Test execution started ***");
			homePage = new HomePage(driver);
			homePage.navigateToLoginPage();
			logger.info("*** Navigated to login page ***");
			loginPage = new LoginPage(driver);
			loginPage.loginToApp(emailId,password);
			Assert.assertEquals(loginPage.getWarningMsg(), "Warning: No match for E-Mail Address and/or Password.");
		}catch (Exception e) {
			logger.debug(e.getMessage());
			throw new FrameworkException(e.getMessage());
		}
	}
	
	
	
}
