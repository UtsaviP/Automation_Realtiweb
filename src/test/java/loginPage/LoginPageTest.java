package loginPage;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import login.pageobject.LoginPage;
import project.AbstractComponents.AzureDevOpsIntegration;
import project.TestComponents.BaseTest;

public class LoginPageTest extends BaseTest { // inherit
    
	String Accountname = "Automation";
	String WrongPass = "Wrong Password";
	String WrongUsername = "Invalid Account, Username or Password";
	String WrongAccountName = "Invalid Account, Username or Password";
	
	AzureDevOpsIntegration Azure = new AzureDevOpsIntegration();
	
	//Test Case 12055: Login with Correct User name, Password and Account Name
	@Test
	public void Verify_Login_Page_Working_Proper_Or_Not() throws IOException, InterruptedException {
	    LoginPage LoginPage = new LoginPage(driver);
	    String Login = LoginPage.GetLoginDetails();

	    try {	        
	        Assert.assertEquals(Login, Accountname);
	        System.out.println("PASS: Login Page working properly");	        	       
	        Azure.updateTestCaseStatus("12055", "Automation Pass","");
	        
	    } catch (AssertionError e) {	        
	        System.out.println("***FAIL: Login Page not working properly***");
	        Azure.updateTestCaseStatus("12055", "Automation Fail",e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	//Test Case 12057: Login with Wrong Password , Correct User name and Account Name
	@Test
	public void Verify_validation_when_password_is_wrong() throws IOException {
	    LoginPage LoginPage = new LoginPage(driver);
	    try {
	        String Login = LoginPage.LoginPassWrong();
	        Assert.assertEquals(Login, WrongPass);
	        System.out.println("PASS: Validation for incorrect password is working correctly");
	        Azure.updateTestCaseStatus("12057", "Automation Pass","");
	    } catch (AssertionError e) {
	        System.out.println("***FAIL: Validation for incorrect password is not working correctly***");
	        Azure.updateTestCaseStatus("12057", "Automation Fail",e.getMessage());
	        e.printStackTrace();
	    } 
	    }
		
	
	//Test Case 12056: Login with Wrong User name, Correct Password and Account Name
	@Test
	public void Verify_validation_when_username_is_wrong() throws IOException {
	    LoginPage LoginPage = new LoginPage(driver);
	    String Login = LoginPage.LoginUsernameWrong();

	    try {
	       
	        Assert.assertEquals(Login, WrongUsername);
	        System.out.println("PASS: Validation for incorrect username is working correctly");	     
	        Azure.updateTestCaseStatus("12056", "Automation Pass","");
	        
	    } catch (AssertionError e) {
	       
	        System.out.println("***FAIL: Validation for incorrect username is not working correctly***");
	        Azure.updateTestCaseStatus("12056", "Automation Fail",e.getMessage());
	        e.printStackTrace();
	    }
	}

    //Test Case 12058: Login with Wrong Account Name, Correct User name and Password
	@Test
	public void Verify_validation_when_AccountName_is_wrong() throws Exception {
	    LoginPage LoginPage = new LoginPage(driver);
	    String Login = LoginPage.LoginAccountnameWrong();

	    try {
	        
	        Assert.assertEquals(Login, WrongAccountName);
	        System.out.println("PASS: Validation for incorrect Account Name is working correctly");	    
	        Azure.updateTestCaseStatus("12058", "Automation Pass","");
	        
	    } catch (AssertionError e) {
	       
	        System.out.println("***FAIL: Validation for incorrect Account Name is not working correctly***");
	        Azure.updateTestCaseStatus("12058", "Automation Fail",e.getMessage());
	        e.printStackTrace();
	    }
	}


}
