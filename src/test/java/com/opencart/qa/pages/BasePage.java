package com.opencart.qa.pages;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public WebDriver driver;
	
	//constructor
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	void waitABit(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public String randomString(int size) {
		String str = RandomStringUtils.randomAlphabetic(size);
		return str;
	}
	
	public String randomNumeric(int size) {
		String num = RandomStringUtils.randomNumeric(size);		
		return num;
	}
	
	
}
