package com.opencart.qa.pages;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.opencart.qa.utils.ElementUtil;

public class HomePage extends BasePage {
	ElementUtil elementUtil;// = new ElementUtil(driver);;
	
	public HomePage(WebDriver driver) {
		super(driver);
		elementUtil = new ElementUtil(this.driver);
	}
	
	//locators
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccount;
	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerBtn;
	@FindBy(xpath = "//a[text()='Login']")
	WebElement loginBtn;
	@FindBy(css = "div#search input")
	WebElement searchBox;
	@FindBy(xpath = "//div[@id='search']//button[@type='button']")
	WebElement searchButton;
	@FindBy(xpath = "//div[contains(@class,'product-layout')]//a[text()]")
	List<WebElement> productNames;
	@FindBy(css = "#logo a")
	WebElement appLogo;
	
	
	
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.clickElement(myAccount,"My Account");
		elementUtil.clickElement(registerBtn,"Register");
		return new RegistrationPage(this.driver);
	}
	
	public LoginPage navigateToLoginPage() {
		elementUtil.clickElement(myAccount,"My Account");
		elementUtil.clickElement(loginBtn,"Login");
		return new LoginPage(this.driver);
	}
	
	public void searchProduct(String productName) {
		elementUtil.sendText(searchBox, productName, "Search Field");
		elementUtil.clickElement(searchButton,"Search Icon");
	}
	
	public boolean productLists(String productName) {
		boolean flag = false;
		for(int i=0; i<productNames.size();i++) {
			if(productNames.get(i).getText().trim().toLowerCase().contains(productName.toLowerCase())) {
				flag = true;
			}else flag = false;
		}
		
		return flag;
	}
	
	public void goToHomePage() {
		appLogo.click();
	}

}
