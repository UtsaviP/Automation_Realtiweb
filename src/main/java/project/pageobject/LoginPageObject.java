package project.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
		
		@FindBy(id = "ProductProvince")
		public WebElement ProductProvince;
		
		
		
		public String getVerifyLogout()
		{
			return LoginButton.getText();
		}
	
	/*
		public void getProductProvince(String province) {			
			Select Province = new Select(ProductProvince);
			Province.selectByVisibleText(province);
			
		}
	*/
	
	
}
