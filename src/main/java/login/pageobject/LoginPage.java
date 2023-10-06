package login.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;

public class LoginPage extends AbstractComponent {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
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

	@FindBy(id = "ProductProvince")
	public WebElement ProductProvince;

	@FindBy(xpath = "//input[@type='radio' and @value=1]")
	public WebElement Checked_site;
    
	@FindBy(xpath = "//tbody/tr[4]/td[3]")
	public WebElement ErrorMessageSection;
	
	@FindBy(id = "intitials")
	public WebElement Accountoption;
   
	AzureDevOpsIntegration Azure = new AzureDevOpsIntegration();
	
	//***Login with Correct User name, Password and Account Name***
	public String GetLoginDetails() throws InterruptedException {
		AccountName.sendKeys("LDDTesting");
		UserName.sendKeys("Automation");
		Password.sendKeys("Automation");
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText("Ontario");
		waitForWebElementToAppear(Checked_site);
		Checked_site.click();
		LoginButton.click();
		waitForWebElementToAppear(Accountoption);
		Accountoption.click();
		String Actual = driver.findElement(By.xpath("//div [@class='col-10 col']/b")).getText();
		String arr[] = Actual.split(" ", 2);
		String Actual1 = arr[0];
		Accountoption.click();
		Thread.sleep(2000);
		return Actual1;
	}
    //***Login with Wrong Password ,  Correct  User name and Account Name***
	public String LoginPassWrong() {
		AccountName.sendKeys("LDDTesting");
		UserName.sendKeys("Automation");
		Password.sendKeys("Welcome-12345");
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText("Ontario");
		Checked_site.click();
		LoginButton.click();
		String Actual = ErrorMessageSection.getText();
		return Actual;

	}
	
    //***Login with Wrong User name,  Correct Password and Account Name***
	public String LoginUsernameWrong() {
		AccountName.sendKeys("LDDTesting");
		UserName.sendKeys("Automation1");
		Password.sendKeys("Automation");
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText("Ontario");
		Checked_site.click();
		LoginButton.click();
		String Actual = ErrorMessageSection.getText();
		return Actual;

	}
	//***Login with Wrong Account Name,  Correct  User name and Password***
	public String LoginAccountnameWrong() {
		AccountName.sendKeys("LDDTesting1");
		UserName.sendKeys("Automation");
		Password.sendKeys("Automation");
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText("Ontario");
		Checked_site.click();
		LoginButton.click();
		String Actual = ErrorMessageSection.getText();
		return Actual;

	}
}
