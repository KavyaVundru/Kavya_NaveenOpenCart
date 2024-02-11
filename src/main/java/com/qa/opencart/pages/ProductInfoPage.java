package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By Locator
	private By productHeader = By.cssSelector("div#content  h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	//private Map<String,String> productMap = new LinkedHashMap<String,String>();
	private Map<String,String> productMap = new TreeMap<String,String>();

	public ProductInfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderName() {
		String productHeaderval = eleUtil.doElementGetText(productHeader);
		System.out.println("Product Header value is: "+productHeaderval);
		return productHeaderval;
	}

	public int getProductImagesCount() {
		// TODO Auto-generated method stub
		int imagesCount =  eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("product " + getProductHeaderName()+ " images count is "+imagesCount);
		return imagesCount;
	}
//	
//	Brand: Apple
//	Product Code: Product 14
//	Availability: Out Of Stock

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		for(WebElement e:metaDataList)
		{
			String metaData = e.getText();
			String metaKey = metaData.split(":")[0].trim();
			String metaValue= metaData.split(":")[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.waitForVisibilityOfElements(productPricingData, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		String productPrice = metaPriceList.get(0).getText();
		String productExTax = metaPriceList.get(1).getText().split(":")[1].trim();
		productMap.put("price", productPrice);
		productMap.put("extaxprice", productExTax);
		
	}
	
	public Map<String,String> getProductDetails() {
		productMap.put("productName",getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
	}
}
