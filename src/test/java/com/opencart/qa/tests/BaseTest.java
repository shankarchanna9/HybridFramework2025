package com.opencart.qa.tests;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.opencart.qa.factory.DriverFactory;
import com.opencart.qa.utils.ExcelUtil;


public class BaseTest {

	public WebDriver driver;		
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	public Properties prop;
	public Logger logger = LogManager.getLogger(BaseTest.class);
	DriverFactory driverFactory;
	
	
	
	
	public void initializeDriver(String browserName) {
		try {
			switch(browserName.toLowerCase()) {
			case "chrome": 
				driver = new ChromeDriver();
				break;
			case "edge": 
				driver = new EdgeDriver();
				break;
			case "firefox": 
				driver = new FirefoxDriver();
				break;
			default : 
				System.out.println("Browser is not available");
				return;		
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(prop.getProperty("IMPLICIT_WAIT"))));		
		driver.get(DriverFactory.propertyReader("baseUrl"));	
		logger.info("*** Navigated to Url"+prop.getProperty("baseUrl")+" ***");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@BeforeMethod(groups = {"Smoke", "Regression"})
	@Parameters("browser")
	void createDriver1(@Optional() String browserName) {
		try {
			if(browserName==null)
				browserName = DriverFactory.propertyReader("browser");

			driverFactory = new DriverFactory();
			driver=driverFactory.initDriver(browserName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	@AfterMethod(groups = {"Smoke", "Regression"})
	void closeDriver() {
		logger.info("*** Killing the instance of driver ***");
		driver.quit();
		driverFactory.removeDriver();		
	}
	
	
	@DataProvider
	public Object[][] getTestData(){
		Object [][]data= {
							{"asbssja@gmail.com","abcfds"},
							{"asdhakjsdh@yahoo.com","paswrd"},
							{"adfasdkja@gmail.com","sdfsf"},
						};		
		return data;
	}
	
	@DataProvider
	public Object[][] getTestDataUsingExcel(){
		return ExcelUtil.getTestDataUsingExcel("credentials");
	}
	

	public static String takeScreenshot(String tName) {
		String targetPath=null;
		try {
			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));						
			TakesScreenshot tsDriver = (TakesScreenshot) DriverFactory.getDriver();			
			File sourceFile = tsDriver.getScreenshotAs(OutputType.FILE);			
			targetPath = System.getProperty("user.dir") + "\\screenshots\\" + tName + date + ".png";
			File targetFile = new File(targetPath);
			
			sourceFile.renameTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return targetPath;
	}
}
