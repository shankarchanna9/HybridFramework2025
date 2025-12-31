package com.opencart.qa.factory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	public static Properties prop;
	public Logger log = LogManager.getLogger(OptionsManager.class);
	public ChromeOptions co;
	public FirefoxOptions fo;
	public EdgeOptions eo;
	
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(DriverFactory.propertyReader("headless"))) {
			log.info("Running tests in headless mode");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(DriverFactory.propertyReader("incognito"))) {
			log.info("Running tests in incognito mode");
			co.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(DriverFactory.propertyReader("remote"))) {
			co.setCapability("browserName", "chrome");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(DriverFactory.propertyReader("headless"))) {
			log.info("Running tests in headless mode");
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(DriverFactory.propertyReader("incognito"))) {
			log.info("Running tests in incognito mode");
			fo.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(DriverFactory.propertyReader("remote"))) {
			fo.setCapability("browserName", "firefox");
		}
		return fo;
	}
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(DriverFactory.propertyReader("headless"))) {
			log.info("Running tests in headless mode");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(DriverFactory.propertyReader("incognito"))) {
			log.info("Running tests in incognito mode");
			eo.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(DriverFactory.propertyReader("remote"))) {
			eo.setCapability("browserName", "edge");
		}
		return eo;
	}
}
