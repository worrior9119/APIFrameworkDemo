package cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/PlaceValidation.feature",
        glue = {"stepDefinations"},
        plugin = {"json:target/jsonReports/cucumber-report.json"}


)
public class TestRunner {
}
