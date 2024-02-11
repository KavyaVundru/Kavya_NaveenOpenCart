package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailID() {
		return "testautomation"+System.currentTimeMillis()+"@opencart.com";
		//return "testautomation"+UUID.randomUUID()+"@opencart.com";
	}
	
	
//	@DataProvider
//	public Object[][] getUserRegData() {
//		return new Object[][] {
//			{"Rahul", "Yadav","9122334456","test@123","yes"},
//			{"karishma", "automation","9122323456","test@123","yes"},
//			{"Jyothi", "auto","9122323412","test@123","yes"}
//		};
//	}
	
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider= "getUserRegTestExcelData")
	public void userRegTest(String firstname, String lastName, String mobileNumber, String password,String subscribe) {
		boolean isRegDone = registerPage.userRegistration(firstname, lastName,getRandomEmailID(), mobileNumber, password, subscribe);
		Assert.assertTrue(isRegDone);
	}
}
