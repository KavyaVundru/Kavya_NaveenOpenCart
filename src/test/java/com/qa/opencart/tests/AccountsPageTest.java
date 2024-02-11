package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void acctSetUp() {
		acctPage = loginPage.doLogin("kavya.vundru99@gmail.com", "Vkavya@1234567890");
	}
	
	@Test
	public void acctPageTitleTest() {
		Assert.assertEquals(acctPage.getAccPageTitle(),AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void acctPageURLTest() {
		Assert.assertTrue(acctPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(acctPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExist() {
		Assert.assertTrue(acctPage.isSearchFieldExist());
	}
	
	@Test
	public void acctPageHeadersCountTest() {
		List<String> actAccPageHeadersList = acctPage.getAccountHeaders();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList.size(),AppConstants.ACC_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void acctPageHeadersTest() {
		List<String> actAccPageHeadersList = acctPage.getAccountHeaders();
		System.out.println(actAccPageHeadersList);
		//sort the actual list
		//sort the expected list
		//compare
		Assert.assertEquals(actAccPageHeadersList,AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void searchTest() {
		searchResultsPage = acctPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName();
		
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}
	
	
			
}

