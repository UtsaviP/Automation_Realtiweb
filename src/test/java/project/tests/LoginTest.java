package project.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import project.TestComponents.Retry;
import project.pageobject.HomePageObject;
import project.pageobject.LoginPageObject;
import project.TestComponents.BaseTest;
	
	public class LoginTest extends BaseTest{
		
		@Test(dataProvider = "getData")
		public void VerifyLogin(HashMap<String, String> input) throws InterruptedException, IOException
		{
		String Help= "Help";
	
		LoginPageObject MainPageObject = landingPage.Login1(input.get("Account"), input.get("User"), input.get("Password"));
		LoginPageObject MainPageObject1 = landingPage.Login1(input.get("province"));
		HomePageObject MainPageObject2 = new HomePageObject(driver);
		Assert.assertEquals(Help, MainPageObject2.getHelp());
		
		}
		
}
