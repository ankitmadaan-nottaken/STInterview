package com.sitetracker.Demo.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DocumentationPage extends BaseModule{

	private String TESTING_APEX_LINK = "//a[text()='Testing Apex']";
	private String TOPIC_TITLE = "topic-title";
	SoftAssert softAssert = new SoftAssert();
	
	public DocumentationPage() {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Click on Testing Apex Link
	 */
	@When("^Click Link Testing Apex$")
	public void clickOnTestingApexLink() {
		Click(getElement(TESTING_APEX_LINK), "Testing Apex Link");
	}
	
	/**
	 * Get Title of the Topic Page you are on
	 * THERE IS NO SOFTASSERT in Junit, so Using TestNG
	 * @return
	 */
	@Then("^Verify Testing Apex Page is Loaded$")
	public void isTestingApexPage() {
		softAssert.assertTrue(getElement(TOPIC_TITLE).getText().equals("Testing Apex"));
		softAssert.assertAll();
	}
	

}
