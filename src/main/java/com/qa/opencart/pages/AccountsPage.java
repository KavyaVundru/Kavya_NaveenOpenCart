package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logOutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchicon = By.cssSelector("div#search button");
	private By acctHeaders = By.cssSelector("div#content > h2");
	
	//page const....
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	//page Actions:
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE,AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Acc Page Title is "+title);
		return title;
	}
	
	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION,AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page url "+url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logOutLink,AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public void logout() {
		if(isLogoutLinkExist())
		{
			eleUtil.doClick(logOutLink);
		}
	}
	
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search,AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public List<String> getAccountHeaders() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(acctHeaders,AppConstants.LONG_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		
		for(WebElement e : headersList)
		{
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchicon);
		return new SearchResultsPage(driver);
	}
	
	
}
