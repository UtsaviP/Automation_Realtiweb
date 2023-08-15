package project.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

//import menubar_Main.PageObject.MainPage;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import project.TestComponents.BaseTest;
import project.TestComponents.Retry;
import project.pageobject.CartpageObject;
import project.pageobject.CheckoutpageObject;
import project.pageobject.ConFirmationpageObject;
import project.pageobject.LandingPageObject;
import project.pageobject.ProductCataloguePageObject;

public class ErrorValidationsTest extends BaseTest{ //inherit

		
		@Test(dataProvider = "getData", groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
		public void LoginErrorValidation(HashMap<String, String> input) throws InterruptedException, IOException
		{
		//String productName= "ZARA COAT 3";
	
		LandingPageObject MainPageObject = landingPage.Login(input.get("userEmail"), input.get("userPassword"));
		
		//landingPage.loginApplication("sadik@gmail.com", "Pass2020"); //catching the desired PageObject class
		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage()); //landingPage.getErrorMessage();
		
		Assert.assertEquals("Incorrect email password.", MainPageObject.getErrorMessage()); //landingPage.getErrorMessage();
		}
		
		
		
		@Test(dataProvider = "getData")
		public void ProductValidation(HashMap<String, String> input) throws InterruptedException, IOException
		{
		String productName= "ZARA COAT 3";
		
		//ProductCataloguePageObject productCatalogue=landingPage.loginApplication("al@gmail.com", "Pass2020#"); //catching the desired PageObject class
		LandingPageObject MainPageObject = landingPage.Login(input.get("userEmail"), input.get("userPassword"));
		
		ProductCataloguePageObject productCatalogue = new ProductCataloguePageObject(driver);//object of ProductCatalogue class
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
		
		//CartpageObject cartPage=productCatalogue.gotoCartPage(); //inherit from parent class AbstractComponent and catching the desired PageObject class
		
		CartpageObject cartPage= new CartpageObject(driver);
		cartPage.gotoCartPage();
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);//assertfalse() assertion method will accept only Boolean value false
		
		}
		
		
}
