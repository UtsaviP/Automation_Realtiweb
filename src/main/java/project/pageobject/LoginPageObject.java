package project.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project.AbstractComponents.AbstractComponent;

public class LoginPageObject extends AbstractComponent { //inherit
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {//constructor
		super(driver); //send driver to parent class AbstractComponent.java using super()
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// Login Page PageFactory
	@FindBy(id = "userID")
	public WebElement AccountName;

	@FindBy(id = "subID")
	public WebElement UserName;

	@FindBy(id = "passEntry")
	public WebElement Password;

	@FindBy(id = "tologin")
	public WebElement LoginButton;

	@FindBy(xpath = "//input[@value='1']")
	public WebElement EnhancedView;
	
	@FindBy(id = "intitials")
	public WebElement AccountInitials;
	
	@FindBy(id = "ProductProvince")
	public WebElement ProductProvince;
	
	
	
	
	public String getAccountInitials()
	{
		waitForWebElementToAppear(AccountInitials);
		return AccountInitials.getText();
	}

/*
	public void getProductProvince(String province) {			
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText(province);
		
	}
*/

	
	
	
	
}
