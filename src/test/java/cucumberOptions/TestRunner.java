package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinitions",
        stepNotifications = true,
        dryRun = true,
        tags = "@SmokeTest or @RegTest",
        plugin = {"pretty", "html:target/cucumber.html","json:target/cucumber.json",
        "junit:target/cukes.xml"}
)
public class TestRunner {
}
