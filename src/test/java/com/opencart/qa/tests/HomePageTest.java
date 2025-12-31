package com.opencart.qa.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencart.qa.exceptions.FrameworkException;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.pages.LoginPage;
import com.opencart.qa.utils.ExtentReporterManager;
@Listeners(ExtentReporterManager.class)
public class HomePageTest extends BaseTest {
	
	public Logger logger;
	LoginPage loginPage;
	
	@Test(groups = {"Smoke","Regression"})
	void searchAndValidateProductList() {
		try {
			logger = LogManager.getLogger(this.getClass());
			logger.info("*** Test execution started ***");
			HomePage homePage = new HomePage(driver);
			homePage.navigateToLoginPage();
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToApp();
			Assert.assertTrue(loginPage.loginStatus());
			homePage.goToHomePage();
			homePage.searchProduct("Mac");
			Assert.assertTrue(homePage.productLists("Macc"));
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new FrameworkException(e.getMessage());
		}
	}
	
	
}
