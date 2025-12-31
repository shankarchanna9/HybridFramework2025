package com.opencart.qa.driverfactory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDirver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver() {
		
		
		
		
		
		
		
		return driver;
		
	}
}
