package com.sitetracker.Demo.pages;

import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.When;

public class LoginPage extends BaseModule{
	//protected WebDriver driver;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	private String LOGIN_BTN = "login-button";
	private String UNAME_TEXTBOX = "username";
	private String PWD_TEXTBOX = "password";
	private String USER_LOGIN_BTN = "Login";
	
	
	/**
	 * Login Method
	 * @param userName
	 * @param pwd
	 * @throws InterruptedException 
	 */
	@When("^Login as User \\\"([^\\\"]*)\\\" and password \"([^\"]*)\"$")
	public void loginAs(String userName, String pwd) throws InterruptedException {
		Click(getElement(LOGIN_BTN), "Login Button");
		SendKeys(getElement(UNAME_TEXTBOX), userName, "Username", true);
		SendKeys(getElement(PWD_TEXTBOX), pwd, "Password", true);
		Click(getElement(USER_LOGIN_BTN), "Login Button");
	}

}
