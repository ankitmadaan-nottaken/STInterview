package com.sitetracker.Demo.tests;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/sitetracker/Demo/tests/Features" ,
	plugin = {"pretty", "html:target/cucumber-html-report"}, glue = {"com.sitetracker.Demo.pages"})
public class TestRunner {

}
