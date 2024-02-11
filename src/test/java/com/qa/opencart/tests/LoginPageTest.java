package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.opentelemetry.api.logs.Severity;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Epic 100: Design open Cart Login Test")
@Story("US 101: Login Page Features")
@Feature("F50: Feature Login Page")
public class LoginPageTest extends BaseTest {
	
	@Description("login page title Test......")
	@Test(priority=1)
	public void loginPageTitle() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login page Url Test......")
	@Test(priority=2)
	public void loginPageUrl() {
		String actUrl = loginPage.getLoginPageURL();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Verifying Forgot Password link exist or not")
	@Test(priority=3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Description("Verifying Application Logo Text")
	@Test(priority=4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}
	
	@Description("User is able to login with correct Credentials")
	@Test(priority=5)
	public void loginTest() {
		acctPage = loginPage.doLogin("kavya.vundru99@gmail.com", "Vkavya@1234567890");
		Assert.assertTrue(acctPage.isLogoutLinkExist());
	}
	
	@Test
	public void verifyRegisterLink() {
		Assert.assertTrue(loginPage.doRegisterLinkExist());
	}
	
}
