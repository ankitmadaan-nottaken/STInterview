package com.sitetracker.Demo.pages;

import java.util.concurrent.TimeUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class BaseTestSuite {
	protected  static WebDriver driver;
	private Logger log = LogManager.getLogger(BaseTestSuite.class);
	private String browser = "Chrome";
	
	@Before
	public void setUp() {
		driver = getInstance(browser);
	}
	
	/**
	 * 
	 * @param browser
	 * @param locale
	 * @throws Exception
	 */
	@Given("^Launch \"([^\"]*)\"$")
	public void launchURL( String baseURL) throws Exception{
		driver .get(baseURL);
	}
	
	@After
	public void cleanUp() {
		driver.close();
	}
	
	public WebDriver getInstance(String browser) {
		WebDriver driver = null;
		
		if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/sync/Downloads/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/amadan/Downloads/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-fullscreen");
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", "/Users/sync/Downloads/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}

		//Setting Driver Implicit Time out for An Element
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		log.info("WEBDRIVER IS STARTING ON LOCAL SESSION");
		return driver;
	}
	
}
