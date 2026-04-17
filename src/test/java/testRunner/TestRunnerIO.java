package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\featureFile\\FT_EndToEndBooking.feature", glue = { "stepdefinitions",
		"hooks" }, plugin = { "pretty", "html:target/cucumber-reports.html" }, monochrome = true)
public class TestRunnerIO extends AbstractTestNGCucumberTests {
}
