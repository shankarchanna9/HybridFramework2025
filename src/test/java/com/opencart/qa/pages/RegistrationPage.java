package com.opencart.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.opencart.qa.utils.ElementUtil;

public class RegistrationPage extends BasePage{
	ElementUtil elementUtil;
	//constructor
	public RegistrationPage(WebDriver driver) {
		super(driver);
		elementUtil = new ElementUtil(driver);
	}
	
	//locators
	@FindBy(css = "#input-firstname")
	WebElement firstName;
	@FindBy(css = "#input-lastname")
	WebElement lastName;
	@FindBy(css = "#input-email")
	WebElement email;
	@FindBy(css = "#input-telephone")
	WebElement telephone;
	@FindBy(css = "#input-password")
	WebElement password;
	@FindBy(css = "#input-confirm")
	WebElement confirmPassword;
	@FindBy(css = "input[name='agree']")
	WebElement policyCheckBox;
	@FindBy(css = "input[type='submit']")
	WebElement submitButton;
	@FindBy(xpath = "//div[@id='content']//h1")
	public WebElement accountCreatedText;
	
	public String createAccount() {
		String emailId = randomString(8) +"@yahoo.com";
		String pass = randomString(8);
		
		elementUtil.sendText(firstName, randomString(5), "FirstName");
		elementUtil.sendText(lastName, randomString(4), "LastName");	
		elementUtil.sendText(email, emailId, "Email");
		elementUtil.sendText(telephone, randomNumeric(10), "Telephone");	
		elementUtil.sendText(password, pass, "Password");
		elementUtil.sendText(confirmPassword, pass, "Confirm Password");
		elementUtil.clickElement(policyCheckBox,"Policy CheckBox");
		elementUtil.clickElement(submitButton,"Submit Button");
		return elementUtil.getText(accountCreatedText,"Account Created Success Msg");
	}
	
	
	
	
	
	
}
