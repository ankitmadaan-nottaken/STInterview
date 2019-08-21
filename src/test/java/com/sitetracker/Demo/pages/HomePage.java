package com.sitetracker.Demo.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class HomePage extends BaseModule{
	private String LOGOUT_BTN = "login-button";
	private String USER_LOGOUT_BTN = "user-info-logout";
	private String SEARCH_INPUT = "st-search-input";
	private String SEARCH_RESULT = "//div[@class='autocomplete']//li//p";

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	
	/**
	 * Enter text in Search Text Box
	 * @param text
	 * @throws InterruptedException 
	 */
	
	@When("^Search for \"([^\"]*)\"$")
	public void searchForText(String text) throws InterruptedException {
		Click(getElement(SEARCH_INPUT), "Click to slow down SendKeys");
		SendKeys(getElement(SEARCH_INPUT), text, "Search for "+text, true);
	}
	
	@Then("^\"([^\"]*)\" is listed$")
	public void isTestPageListed(String text) {
		Boolean isSearchResult = false;
		List<WebElement> searchResultList = getElements(SEARCH_RESULT);
		for (WebElement element : searchResultList) {
			if (element.getText().contains(text)) {
				isSearchResult = true;
				break;
			}
		}
		Assert.assertFalse(isSearchResult);;
	}
	
	@When("^Navigate to \"([^\"]*)\" Page$")
	public void navigateToSearchResult(String text) {
		List<WebElement> searchResultList = getElements(SEARCH_RESULT);
		for (WebElement element : searchResultList) {
			if (element.getText().contains(text)) {
				Click(element, "Search Result "+text);
				break;
			}
		}
	}
	
	/**
	 * Logout
	 */
	@When("^Logout$")
	public void logOut() {
		Click(getElement(LOGOUT_BTN), "Logout Button");
		Click(getElement(USER_LOGOUT_BTN), "User Logout Button");
	}

}
