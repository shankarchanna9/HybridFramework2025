package com.opencart.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.exceptions.ElementException;
import com.opencart.qa.pages.BasePage;

public class ElementUtil extends BasePage{
	public Logger log = LogManager.getLogger(ElementUtil.class);
	
	
	public ElementUtil(WebDriver driver) {
		super(driver);
	}
	
	public void clickElement(WebElement ele, String locatorName)  {
		try {
			ele.click();
			log.info("Succesfully CLICKED "+locatorName);
		} catch (Exception e) {
			log.debug("Unable to Click Element due to "+locatorName+e.getMessage());
			throw new ElementException(e.getMessage());
		}
	}
	public void clickElement(By locator) {
		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			throw new ElementException(e.getMessage());
			
		}
	}
	public String getText(WebElement ele, String locatorName) {
		String str =null;
		try {
			str = ele.getText().trim();
			log.info(locatorName+" Succesfully Get Text: "+str);
		} catch (Exception e) {
			log.debug("Unable to Get Text "+locatorName);
			throw new ElementException(e.getMessage());
		}
		return str;
	}
	
	
	public void sendText(WebElement ele, String text, String locatorName) {
		try {
			ele.sendKeys(text);
			log.info("Succesfully Send Text to "+locatorName);
		} catch (Exception e) {
			log.debug("Unable to Send Text due to "+locatorName+e.getMessage());
			throw new ElementException(e.getMessage());
		}
	}
}
