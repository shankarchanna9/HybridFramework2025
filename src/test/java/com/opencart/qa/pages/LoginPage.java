package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.opencart.qa.utils.ElementUtil;

public class LoginPage extends BasePage{
	ElementUtil elementUtil;
	//constructor
	public LoginPage(WebDriver driver) {
		super(driver);
		elementUtil = new ElementUtil(driver);
	}
	//locators
	//action methods
	
	
	@FindBy(css = "#input-email")
	WebElement email;
	@FindBy(css = "#input-password")
	WebElement password;
	@FindBy(css = "input[type='submit']")
	WebElement loginBtn;
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccount;
	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	WebElement warnMessage;
	@FindBy(css = "div[class*='alert-dismissible']")
	WebElement loginIssue;
	By logoutLoc = By.xpath("//ul[contains(@class,'dropdown-menu-right')]//a[text()='Logout']");
	
	public void loginToApp() {
		elementUtil.sendText(email, "shankardemo@gmail.com", "Username/Email Field");
		elementUtil.sendText(password, "opencart", "Password Field");
		elementUtil.clickElement(loginBtn, "Login Button");
	}
	
	public boolean loginStatus() {
		return myAccount.isDisplayed();
	}
	
	public void loginToApp(String emailId, String passWord) {		
		elementUtil.sendText(email, emailId, "Username/Email Field");
		elementUtil.sendText(password, passWord, "Password Field");
		elementUtil.clickElement(loginBtn, "Login Button");
	}
	
	public String getWarningMsg() {
		return elementUtil.getText(loginIssue,"No UserName/Password Msg");
	}
	
	
			
			
	
}
