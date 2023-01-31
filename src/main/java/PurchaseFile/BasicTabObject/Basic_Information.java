package PurchaseFile.BasicTabObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project.AbstractComponents.AbstractComponent;

public class Basic_Information extends AbstractComponent {
	WebDriver driver;

	public Basic_Information(WebDriver driver) {// constructor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Login Page PageFactory
		@FindBy(xpath = "//tr[@title='Lawyer responsible for this file']//i[@class='fa tool-icon ldd-list']")
		public WebElement ResponsibleLawyer;

		@FindBy(xpath = "(//input[@id='currentListFilter'])[1]")
		public WebElement LawyerFilter;
		
		@FindBy(xpath = "//tr[@class='SelectRow']//td[@class='SelectRow']")
		List<WebElement> LawyerList;
		
		By LawyerListBy = By.xpath(
				"//tr[@class='SelectRow']//td[@class='SelectRow']");
		
		
		
		
		public List<WebElement> getLawyerList() {
			 driver.switchTo().frame("dialog-body");
		     WebElement m = driver.findElement(By.xpath("//tr[@class='SelectRow']//td[@class='SelectRow']"));
		     
		    // waitForElementToAppear(m);
			return (List<WebElement>) m;

		}

		public WebElement GetFileName(String LawyerName) {
			WebElement prod = getLawyerList().stream().filter(Lawyer -> Lawyer.getText().equals(LawyerName)).findFirst()
					.orElse(null);
			return prod;
		}
		public void FIletest()
		{
			waitForWebElementToAppear(ResponsibleLawyer);
			ResponsibleLawyer.click();		
		}
		
		
		
		
		public void FileDetails(String LawyerName)
		{
				
			WebElement prod = GetFileName(LawyerName);
			prod.click();
			
			
		}
		 

}
