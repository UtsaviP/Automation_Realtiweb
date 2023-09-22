package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features",glue="project.stepDefinations",
monochrome=true,tags= "@Regression2" ,plugin= {"html:reports/cucumber.html"})//tags= "@Regression", "@Regression2",
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
