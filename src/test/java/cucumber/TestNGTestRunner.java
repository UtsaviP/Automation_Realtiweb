package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="project.stepDefinations",
monochrome=true,tags= "@Regression",plugin= {"html:reports/cucumber.html"})//tags= "@Regression", @ErrorValidation, @OrderSubmit
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}

