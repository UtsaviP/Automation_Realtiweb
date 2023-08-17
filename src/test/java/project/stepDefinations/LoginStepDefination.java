package project.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import project.AbstractComponents.AbstractComponent;
import project.TestComponents.BaseTest;
import project.pageobject.LoginPageObject;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginStepDefination extends BaseTest {

	@Given("Enter Website for Login")
	public void enter_website_for_login() throws IOException 
	{
		landingPage=launchApplication();
	}
	
	
	@When("^Enter Account (.+) username (.+) and password (.+) for Login")
	public void Enter_username_and_password_for_Login(String Account,String username,String password) throws InterruptedException, IOException
	{
		LoginPageObject MainPageObject = landingPage.Login1(Account,username,password);
		
	}
	
	@When("^Enter province (.+)")
	public void Enter_province(String Provinces) throws InterruptedException, IOException
	{
		LoginPageObject MainPageObject = landingPage.Login1(Provinces);
	}
	
	@Then("Verify the Login fail")
	public void verify_the_login_fail() 
	{
		//
	}
	
	
	
	
}
