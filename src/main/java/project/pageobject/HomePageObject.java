package project.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project.AbstractComponents.AbstractComponent;

public class HomePageObject extends AbstractComponent { //inherit
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {//constructor
		super(driver); //send driver to parent class AbstractComponent.java using super()
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// Home Page PageFactory
	
	@FindBy(xpath = "//span[contains(text(),'Help')]")
	public WebElement Help;
	
	@FindBy(id = "intitials")
	public WebElement AccountInitials;
	
	@FindBy(xpath = "//button[contains(text(),'Log Out')]")
	public WebElement Logout;
	
	
	public String getHelp()
	{
		waitForWebElementToAppear(Help);
		return Help.getText();
	}

	public void getLogout()
	{
		AccountInitials.click();
		Logout.click();
		
	}
	
/*
	public void getProductProvince(String province) {			
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText(province);
		
	}
*/

	
	
	
	
}
