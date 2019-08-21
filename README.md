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

## 
