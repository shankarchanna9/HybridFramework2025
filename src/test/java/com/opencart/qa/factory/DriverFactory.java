package com.opencart.qa.factory;

import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverFactory {

	public WebDriver driver;		
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static Properties prop;
	public Logger log = LogManager.getLogger(DriverFactory.class);
	
	OptionsManager options;
	
	public WebDriver initDriver(String browserName) {
		options = new OptionsManager();
		
		try {			
			if (Boolean.parseBoolean(propertyReader("remote"))) {
				init_remoteDriver(browserName);
			} else {
				switch (browserName.toLowerCase()) {
				case "chrome":
					tlDriver.set(new ChromeDriver(options.getChromeOptions()));
					break;
				case "edge":
					tlDriver.set(new EdgeDriver());
					break;
				case "firefox":
					tlDriver.set(new FirefoxDriver());
					break;
				default:
					System.out.println("Browser is not available");
					return null;
				}
			}
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.valueOf(DriverFactory.propertyReader("IMPLICIT_WAIT"))));
			getDriver().get(DriverFactory.propertyReader("baseUrl"));
			log.info("*** Navigated to Url" + DriverFactory.propertyReader("baseUrl") + " ***");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDriver();
	}
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	public void removeDriver() {
		tlDriver.remove();
	}
	
	private void init_remoteDriver(String browserName) {
		log.info("Running tests on selenoum grid --"+ browserName);

		try {
			switch (browserName) {
			case "chrome":
				tlDriver.set(new RemoteWebDriver(new URL(propertyReader("huburl")), options.getChromeOptions()));
				break;
				
			case "firefox":
				tlDriver.set(new RemoteWebDriver(new URL(propertyReader("huburl")), options.getFirefoxOptions()));
				break;
				
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(propertyReader("huburl")), options.getEdgeOptions()));
				break;
				
			default:
				log.error("Please supply the right browser name for selenium grid....");
				log.error("Exception occurred while initializing driver: ");
			}
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public static String propertyReader(String key) {
		try {
			FileReader file = new FileReader("./src//test//resources//config.properties");
			prop = new Properties();
			prop.load(file);
			return prop.getProperty(key);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
