package runner;

import org.junit.runner.RunWith;

import Utilities.ApiConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.java.Scenario;


@RunWith(Cucumber.class)
@CucumberOptions(features= ApiConfig.FEATURE , 
glue = {"stepDefinitions"},
monochrome=true,
plugin = {"pretty","junit:target/JUnitReports/report.xml",
		"json:target/JsonReports/report.json",
		 "html:target/HtmlReports"},
tags = "@LMSAPI",

publish = true

		)
public class Test {
	
}
