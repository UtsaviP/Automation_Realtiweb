package project.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import project.TestComponents.BaseTest;
import project.pageobject.CartpageObject;
import project.pageobject.CheckoutpageObject;
import project.pageobject.ConFirmationpageObject;
import project.pageobject.LandingPageObject;
import project.pageobject.OrderspageObject;
import project.pageobject.ProductCataloguePageObject;

public class SubmitOrderTest extends BaseTest{ //inherit
		
	 String productName="ZARA COAT 3";
		
		
		@Test(dataProvider="getData",groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
		{
			
		LandingPageObject MainPageObject = landingPage.Login(input.get("userEmail"), input.get("userPassword"));
			
		//ProductCataloguePageObject productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password")); //catching the desired PageObject class
		ProductCataloguePageObject productCatalogue = new ProductCataloguePageObject(driver);//object of ProductCatalogue class
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		//CartpageObject cartPage=productCatalogue.gotoCartPage(); //inherit from parent class AbstractComponent and catching the desired PageObject class
		
		CartpageObject cartPage= new CartpageObject(driver);
		cartPage.gotoCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);//assertTrue() assertion method will accept only Boolean value true
		CheckoutpageObject checkoutPage=cartPage.gotoCheckout();//catching the desired PageObject class
		checkoutPage.selectCountry("can");
		ConFirmationpageObject confirmationPage=checkoutPage.submitOrder();//catching the desired PageObject class
		String confirmMessage = confirmationPage.getConfirmationMessage(); //Thank you
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}
		
		
		
		//To verify specified product is displaying in the Order page
		@Test(dataProvider="getData",dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest(HashMap<String,String> input) throws InterruptedException, IOException
		{
			//ProductCataloguePageObject productCatalogue=landingPage.loginApplication("sadik@gmail.com", "Pass2020#"); //catching the desired PageObject class
			LandingPageObject MainPageObject = landingPage.Login(input.get("userEmail"), input.get("userPassword"));
			
			OrderspageObject ordersPage= new OrderspageObject(driver);
			ordersPage.gotoOrdersPage();
			
			//OrderspageObject ordersPage= productCatalogue.gotoOrdersPage();//catching the desired PageObject class
			Assert.assertTrue(ordersPage.VerifyOrdersDisplay(productName)); //ordersPage.VerifyOrdersDisplay(productName);
		}
		
		
		
		/*
		@DataProvider
		public Object[][] getData()
		{
			return new Object[][]  {{"sadik@gmail.com","Pass2020#","ZARA COAT 3"},{"al@gmail.com","Pass2020#","ADIDAS ORIGINAL"}};
		}
		*/
		
		/*
		@DataProvider
		public Object[][] getData() throws IOException
		{
			HashMap<String,String> map = new  HashMap<String,String>(); //key value pairs can be any data type
			map.put("email", "sadik@gmail.com");
			map.put("password", "Pass2020#");
			map.put("productName", "ZARA COAT 3");
			
			HashMap<String,String> map1 = new  HashMap<String,String>(); //key value pairs can be any data type
			map1.put("email", "al@gmail.com");
			map1.put("password", "Pass2020#");
			map1.put("productName", "ADIDAS ORIGINAL");
			
			return new Object[][]  {{data.get(0)},{data.get(1)}};
		}
		*/
		
		/*
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\company\\data\\PurchaseOrder.json");
			return new Object[][]  {{data.get(0)},{data.get(1)}};
		}
		*/
}
