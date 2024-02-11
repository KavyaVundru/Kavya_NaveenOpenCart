package com.qa.opencart.tests;

import java.util.Map;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultsPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetUp() {
		acctPage = loginPage.doLogin("kavya.vundru99@gmail.com", "Vkavya@1234567890");
	}
	
//	@DataProvider
//	public Object[][] getSearchData() {
//		return new Object[][] {
//			{"MacBook", "MacBook Pro",4},
//			{"MacBook", "MacBook Air",4},
//			{"iMac", "iMac",3},
//			{"Samsung","Samsung SyncMaster 941BW",1}
//		};
//	}
	
	
	@DataProvider
	public Object[][] getProductExcelSearchData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SEARCH_DATA);
	}
	
	@Test(dataProvider = "getProductExcelSearchData")
	public void productImagesTest(String searchKey, String productName,String imageCount) {
		searchResultsPage = acctPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actProductimages = productInfoPage.getProductImagesCount();     
		Assert.assertEquals(String.valueOf(actProductimages),imageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = acctPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> productDetailsMap = productInfoPage.getProductDetails();
		
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"),"$2,000.00");
		
		softAssert.assertAll();
	}
}
