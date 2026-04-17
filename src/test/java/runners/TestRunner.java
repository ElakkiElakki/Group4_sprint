package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/scenario_03.feature",
        glue = {"stepdefinitions"},
        plugin = {"pretty"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}