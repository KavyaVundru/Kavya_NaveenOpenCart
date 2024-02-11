package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] incorrectLoginTestDate() {
		return new Object[][] { 
			{ "qweqe", "test123" }, 
			{ "test12312", "12321312" },
			{ "oooooo", "" }, 
			{ "ioiioi", "testing" }
			};
	}
	
//	@DataProvider
//	public Object[][] getIncorrectCredentialsExcel()
//	{
//		Object[][] data = ExcelUtil.getTestData(AppConstants.LOGIN_WRONG_CREDENTIALS);
//		return data;
//	}
	
	
	@Test(dataProvider = "incorrectLoginTestDate")
	public void loginWithWrongCredentialsTest(String userName, String password)
	{
		boolean value = loginPage.doLoginWrongCredentials(userName, password);
		Assert.assertTrue(value);
	}

}
