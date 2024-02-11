package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	public RegisterPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		eleUtil = new  ElementUtil(driver);
	}
	
	//Private By locators
	private By firstName = By.id("input-firstname");
	private By lastName  = By.id("input-lastname");
	private By email     = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password  = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[text()='Subscribe']/..//input[@value='1']");
	private By subscribeNo = By.xpath("//label[text()='Subscribe']/..//input[@value='0']");
	
	
	private By agreeCheckBox           = By.name("agree");
	private By continueButton  = By.xpath("//input[@value='Continue']");
	
	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink   = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public boolean userRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {
		
		eleUtil.waitForVisibilityOfElement(this.firstName,AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstName);;
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equals("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else
		{
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String successMesg = eleUtil.waitForVisibilityOfElement(successMessg, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		System.out.println(successMesg);
		
		if(successMesg.contains(AppConstants.USER_REGISTRATION_SUCCESS_MESSAGE))
		{
			
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else
		{
			return false;
		}
	}

}
