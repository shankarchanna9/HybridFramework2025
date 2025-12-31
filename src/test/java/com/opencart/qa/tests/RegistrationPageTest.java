package com.opencart.qa.tests;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencart.qa.exceptions.FrameworkException;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.pages.RegistrationPage;
import com.opencart.qa.utils.ExtentReporterManager;
import com.opencart.qa.utils.AssertUtil;

@Listeners(ExtentReporterManager.class)
public class RegistrationPageTest extends BaseTest{
	HomePage homePage;
	RegistrationPage regPage;
	AssertUtil testUtil;
	

	@Test(groups = "Regression")
	void registarNewAccount() throws InterruptedException {
	try {
		logger.info("*** Test execution staerted ***");
		homePage = new HomePage(driver);
		homePage.navigateToRegisterPage();
		logger.info("*** Navigated to registration page ***");
		regPage = new  RegistrationPage(driver);
		String createAccountText = regPage.createAccount();
		logger.info("*** Created a new Account ***");
		Assert.assertEquals(createAccountText, "Your Account Has Been Created!");
	}catch (Exception e) {
		logger.debug(e.getMessage());
		throw new FrameworkException(e.getMessage());
	}
	}	
		
}
