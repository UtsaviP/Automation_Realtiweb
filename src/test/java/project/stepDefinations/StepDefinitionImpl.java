package project.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import project.AbstractComponents.AbstractComponent;
import project.TestComponents.BaseTest;
import project.pageobject.CartpageObject;
import project.pageobject.CheckoutpageObject;
import project.pageobject.ConFirmationpageObject;
import project.pageobject.LandingPageObject;
import project.pageobject.ProductCataloguePageObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	//String productName="ZARA COAT 3";
	 public AbstractComponent landingPage; 
	 public ProductCataloguePageObject productCatalogue;
	 public ConFirmationpageObject confirmationPage;
	
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException 
	{
		landingPage=launchApplication();
	}
	
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password) throws InterruptedException, IOException
	{
		LandingPageObject MainPageObject = landingPage.Login(username,password);
		//productCatalogue=landingPage.loginApplication(username,password); //catching the desired PageObject class

	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException
	{
		Thread.sleep(3000);
		ProductCataloguePageObject productCatalogue = new ProductCataloguePageObject(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		CartpageObject cartPage= new CartpageObject(driver);
		cartPage.gotoCartPage();
		
		//CartpageObject cartPage=productCatalogue.gotoCartPage(); //inherit from parent class AbstractComponent and catching the desired PageObject class
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);//assertTrue() assertion method will accept only Boolean value true
		CheckoutpageObject checkoutPage=cartPage.gotoCheckout();//catching the desired PageObject class
		checkoutPage.selectCountry("can");
		confirmationPage=checkoutPage.submitOrder();//catching the desired PageObject class
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage(); //Thank you
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
		LandingPageObject MainPageObject = new LandingPageObject(driver);
		Assert.assertEquals(strArg1, MainPageObject.getErrorMessage()); //landingPage.getErrorMessage();
		driver.close();
    }
	
}
