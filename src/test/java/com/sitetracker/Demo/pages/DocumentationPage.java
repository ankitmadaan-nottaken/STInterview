package com.sitetracker.Demo.pages;


import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DocumentationPage extends BaseModule{

	private String TESTING_APEX_LINK = "//a[text()='Testing Apex']";
	private String TOPIC_TITLE = "topic-title";
	
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
	 * THERE IS NO SOFTASSERT in Junit, so using Assert. .Can use SoftAssert with TestNG as well
	 * @return
	 */
	@Then("^Verify Testing Apex Page is Loaded$")
	public void isTestingApexPage() {
		Assert.assertTrue(getElement(TOPIC_TITLE).getText().equals("Testing Apex"));
	}
	

}
