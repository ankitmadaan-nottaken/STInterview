package com.sitetracker.Demo.pages;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseModule {
	protected WebDriver driver;
	Logger log = LogManager.getLogger(BaseModule.class);
	
    public BaseModule() {
		this.driver = BaseTestSuite.driver;
	}
    
    /**
	 * Click on a WebElement
	 * @param element
	 * @param info
	 */
	public void Click(WebElement element, String info){
		if (element != null) {
			try {
				element.click();
				log.info("Clicked On :: " +info);		
			} catch (Exception e) {
				log.info("Cannot click on element");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Write Text 
	 * @param element
	 * @param data
	 * @param info
	 * @param clear
	 */
	public void SendKeys(WebElement element, String data, String info, Boolean clear){
		if (element != null) {
				if(clear)
					element.clear();
				element.sendKeys(data);
				log.info("Send Keys On Element :: "+info+" With Data : "+data);
		}
	}

	/**
	 * Builds The WebElement By given locator strategy
	 * @param locator - locator strategy, id, name=sample, css=#sample, 
	 * 					tag=div, //[@id=sample], link=sample
	 * @return WebElement
	 */
	public WebElement getElement(String locator){
		WebElement webElement = null;
		waitForElementVisibility(locator);
		try{
			if(locator.startsWith("//")) {	
				webElement = driver.findElement(By.xpath(locator));				
			}else if( locator.startsWith("css=")) {
				locator=locator.substring(4);
				webElement = driver.findElement(By.cssSelector(locator));
			}else if( locator.startsWith("class=")) {
				locator = locator.split("\\=")[1];
				webElement = driver.findElement(By.className(locator));
			}else if( locator.startsWith("name=")) {
				locator = locator.split("\\=")[1];
				webElement = driver.findElement(By.name(locator));
			}else if( locator.startsWith("link=")) {
				locator = locator.split("\\=")[1];
				webElement = driver.findElement(By.linkText(locator));
			}else if( locator.startsWith("tag=")) {
				locator = locator.split("\\=")[1];
				webElement = driver.findElement(By.tagName(locator));
			} else
				webElement = driver.findElement(By.id(locator));
			log.debug("ELEMENT FOUND WITH LOCATOR : "+locator);
		}catch (NoSuchElementException ex) {
			log.debug("No such element "+locator);
			return webElement;
		} catch(Exception ex) {
			log.debug("ELEMENT NOT FOUND WITH LOCATOR : "+locator);
		     ex.printStackTrace();
		     return webElement;
		}
		return webElement;		
	}
	
	public List<WebElement> getElements(String locator){
		List<WebElement> webElement = null;
		waitForElementVisibility(locator);
		try{
				if( locator.startsWith("class=")) {
					locator = locator.split("\\=")[1];
					webElement = driver.findElements(By.className(locator));
				}else if( locator.startsWith("name=")) {
					locator = locator.split("\\=")[1];
					webElement = driver.findElements(By.name(locator));
				}else if( locator.startsWith("link=")) {
					locator = locator.split("\\=")[1];
					webElement = driver.findElements(By.linkText(locator));
				}else if( locator.startsWith("tag=")) {
					locator = locator.split("\\=")[1];
					webElement = driver.findElements(By.tagName(locator));
				} else if (locator.startsWith("//")) {
					webElement = driver.findElements(By.xpath(locator));
				} else
					webElement = driver.findElements(By.id(locator));
				log.debug("ELEMENT FOUND WITH LOCATOR : "+locator);
		}catch(Exception ex) {
			log.debug("ELEMENT NOT FOUND WITH LOCATOR : "+locator);
	        ex.printStackTrace();
	        return webElement;
		}
		return webElement;
	}

     /**
      * 
      * @param locator
      * @return
      */
     public Boolean isElementPresent(String locator){    
     	try{
     		if (getElement(locator) != null){
     			log.debug("Element : "+locator+" : Successfully Appeared On Page");
     			return true;
     		}
     		else{
     			log.debug("Element : "+locator+" : NOT Appeared On Page");
                 return false;
     		}
     	} catch(Exception e){
     			return false;
     	}

     }


     /**
      * Take Screenshot
      * @param issueType
      */
     public void screenShot(String issueType) {
    	String fileName = System.currentTimeMillis()+"."+issueType+".png";
 		File screenshotDir =	new File("resources/screencapture");
 		screenshotDir.mkdir();
 		if ( fileName.contains("/") ) {
 			fileName = fileName.replaceAll("/", "-");
 			log.info(fileName);
 		}
 		try {
 			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 			FileUtils.copyFile(screenshot, new File(screenshotDir+"//"+fileName));
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 	}
     
     /**
      * Wait For Element Visibility
      * @param locator
      */
     public void waitForElementVisibility(String locator) {
    	 WebDriverWait wait = new WebDriverWait(driver, 10);
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(autoLocator(locator)));
     }
     
     /**
      * COnvert String Locator to Xpath/ID/Classname/CSS
      * @param locator
      * @return
      */
     public By autoLocator(String locator){
      	try{
      		if(locator.startsWith("//")){
      			return By.xpath(locator);
      		}else if (locator.startsWith("class=")){
      			locator = locator.split("=")[1];
      		    return By.className(locator);
      		}else if (locator.startsWith("css=")) {
      			locator=locator.substring(4);
  				return By.cssSelector(locator);
      		}else
      			return By.id(locator);
      	} catch (NoSuchElementException noSuchElementException){
      		return null;
      	}
      }
}
