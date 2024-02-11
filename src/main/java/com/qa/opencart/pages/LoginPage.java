package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	private By usename = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgetPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");
	private By loginErrorMsg = By.xpath("//div[contains(@class,'alert')]");
		
	//Page/Action/methods
	
	@Step("Getting Login Page Title : {0} and timeout: {1}")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.SHORT_DEFAULT_WAIT);
		//String title = driver.getTitle();
		System.out.println("login Page Title is :"+title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Current Page URL is "+url);
		return url;
	}
	
	public boolean isForgotPasswordLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgetPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	@Step("username is :{0} and password is {1}")
	public AccountsPage doLogin(String username, String pwd)
	{
		System.out.println("username is "+username+"password is"+pwd);
		eleUtil.waitForVisibilityOfElement(usename,AppConstants.SHORT_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	
	public boolean doLoginWrongCredentials(String username, String pwd)
	{
		System.out.println("Wrong Credentials are+ "+username+" and password is " +pwd);
		eleUtil.getElement(usename).clear();
		eleUtil.waitForVisibilityOfElement(usename,AppConstants.SHORT_DEFAULT_WAIT).sendKeys(username);
		eleUtil.getElement(password).clear();
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String errorMessage = eleUtil.doElementGetText(loginErrorMsg);
		System.out.println("Error Message Captured is "+errorMessage);
		if(errorMessage.contains(AppConstants.LOGIN_PAGE_CREDENTIALS_ERROR))
			return true;
		else
			return false;
	}
	
	public boolean doRegisterLinkExist()
	{
		return eleUtil.waitForVisibilityOfElement(registerLink,AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink,AppConstants.SHORT_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}
}
