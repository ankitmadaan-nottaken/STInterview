# Maven Project to test developer.salesforce.com

The Project is an interview exercise for Sitetracker to demonstrate Functional and BDD Testing expertise. It uses Selenium Webdriver, Cucumber, JUnit, Log4j and is written in Java. 

## Getting Started
Follow the following instructions to deploy the framework on your machine and run tests

### Prerequisites

Download drivers for Browsers and keep them in your path on your local machine (run echo $PATH to check path)
1. Firefox - GeckoDriver (https://github.com/mozilla/geckodriver/releases)
2. Chrome - ChromeDriver (http://chromedriver.chromium.org/downloads)
3. Edge - MicrosoftWebDriver (https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/#downloads)

Note: Currently I have hardcoded the browser to be Chrome and the location of the driver executables in your system in com.sitetracker.Demo.pages.BaseTestSuite but they can easily be parameterized in Cucumber's Feature file or in several other ways..

Run "mvn test" to run the test. The test will check for Feature files in "src/test/java/com/sitetracker/Demo/tests/Features" folder and run Scenario(s) in the feature file. 

## Technical Explanation
1. The Demo Project is written in Page Object Model design pattern, where "com.sitetracker.Demo.pages" and "com.sitetracker.Demo.tests" are different packages and if there is a change in the UI, the tests do not have to be changed.
2. BaseTestSuite.java in pages package, instantiates the driver using Cucumber's @Before annotation and opens the browser before running every scenario. Similarly, using @After annotation, the browser is closed after every scenario.
3. There are different Page Level Classes for Home Page, Login Page and Documentation Page (where the search result lands) and they have Given, When and Then methods to perform every action that is present in the feature file. We can create other Page Level Classes in the future to test other pages in developer.salesforce.com
4. All Page Level Classes extend "BaseModule.java" (also in Pages package). BaseModule has overwitten methods to click, send keys, take screenhot, right click, runnig javascript (future), and can have methods for all browser based operations that can be leveraged by other Page level calsses. The benefit of overwiriting these methods is to make logging easier, make code more readable and if there are obvious issues like StaleElementException etc. , these methods can be put in try/catch blocks and there will not be any need to take care of each click/sendkeys etc. separately. Obviously, there are a lot of other benefits of using this approach.
5. getElement() and getElements() methods in BaseModule, read a String as an argument and returns a WebElement based on the String. If it starts with "//", it is considered an xpath and returns driver.findElement(By.xpath(locator)), if it starts with "css=", it uses driver.findElement(By.cssSelector(locator)) and so on. It makes it easier to manage xpaths and if the locators change, we only have to change the String variable and the tests/page methods do not have to change.
6. The Project uses Cucumber's test runner and is glued to the pages package, where it looks for all Step definitions for Cucumber's Feature file.
7. The feature file(s) are in the test package itself and we can introduce tags in the future to run specific tags in the feature files e.g @SmokeTest could be a tag. Currently, it runs all feature files serially. 
8. If the scenraio fails, a screenshot is automatically taken and embedded to "target\cucumber-html-report"

# Workaround for Verification Code Email
Salesforce developer, sends a verification code to your email address every time you login from a new IP or a browser which has not cached the session. As a workaround, you can use IP whitelisting to add your IP and it will not send a verification code if Salesforce developer is opened from that IP

setup -> Security Controls -> Network Access -> New Button.
For know your IP address: enter below website in you URL - 
http://www.whatismyip.com/

Otherwise, Java Mail API (https://javaee.github.io/javamail/) can be used to get the content of the email by creating a session with your SMTP server and adding Selenium code to enter that verification code
