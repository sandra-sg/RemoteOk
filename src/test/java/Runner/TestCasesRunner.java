package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions (
        features = "src/main/resources/Features",
        glue = {"TestCases"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestCasesRunner extends AbstractTestNGCucumberTests {
}