package loginPage;

import org.testng.Assert;

import org.testng.annotations.Test;
import login.pageobject.LoginPage;
import project.TestComponents.BaseTest;

public class LoginPageTest extends BaseTest { // inherit
    
	String Accountname = "Automation";
	String WrongPass = "Wrong Password";
	String WrongUsername = "Invalid Account, Username or Password";
	String WrongAccountName = "Invalid Account, Username or Password";

	@Test
	public void Verify_Login_Page_Working_Proper_Or_Not() {

		LoginPage LoginPage = new LoginPage(driver);
		String Login = LoginPage.GetLoginDetails();
		Assert.assertEquals(Login, Accountname);

	}

	@Test
	public void Verify_validation_when_password_is_wrong() {

		LoginPage LoginPage = new LoginPage(driver);
		String Login = LoginPage.LoginPassWrong();
		Assert.assertEquals(Login, WrongPass);

	}
	
	@Test
	public void Verify_validation_when_username_is_wrong() {

		LoginPage LoginPage = new LoginPage(driver);
		String Login = LoginPage.LoginUsernameWrong();
		Assert.assertEquals(Login, WrongUsername);

	}

	@Test
	public void Verify_validation_when_AccountName_is_wrong() {

		LoginPage LoginPage = new LoginPage(driver);
		String Login = LoginPage.LoginAccountnameWrong();
		Assert.assertEquals(Login, WrongAccountName);

	}

}
