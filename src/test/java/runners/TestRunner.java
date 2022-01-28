package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"},
        features = "src/test/resources/ftusecase",
          glue = {"stepDefinitions"},

                  monochrome = true,publish = true)

public class TestRunner {


    @AfterClass
    public static void tearDown() {
        Reportgenerator.main(new String[]{});
    }
}
