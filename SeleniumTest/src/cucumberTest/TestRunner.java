package cucumberTest;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature"
		//features= "Feature/LogIn_Test.feature:42"
		,plugin = {"pretty", "html:target/Destination"}
		,glue={"stepDefinition"}
		//,dryRun=true
		//,monochrome=true
		)

public class TestRunner {

}