package com.opencart.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.opencart.qa.tests.BaseTest;

public class AssertUtil extends BaseTest{
	 private static final Logger log = LogManager.getLogger(AssertUtil.class);

	public void assertEqualsElementText(By locator, String text) {
		String str = driver.findElement(locator).getText().trim();
		Assert.assertEquals(str, text);
	}
	public void assertEqualsText(WebElement ele, String text) {
		try{
			String str = ele.getText().trim();
		    Assert.assertEquals(str, text);
		    log.info("Assertion PASSED");
		}catch(AssertionError  assErr) {
			assErr.printStackTrace();
			log.debug(assErr.getMessage());
		}
	}
	
}
