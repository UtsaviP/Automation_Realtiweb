package login.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import project.AbstractComponents.AbstractComponent;

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

	@FindBy(id = "ErrorMessageSection")

	public WebElement ErrorMessageSection;
	By AccountOption = By.xpath("//body[@class='bg ldd_main_body menu']//i[@class='fa ldd-account'][1]");

	public String GetLoginDetails() {
		AccountName.sendKeys("LDDTesting");
		UserName.sendKeys("Automation");
		Password.sendKeys("Automation");
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText("Ontario");
		LoginButton.click();
		waitForElementToAppear(AccountOption);
		driver.findElement(AccountOption).click();
		String Actal = driver.findElement(By.id("currentLoginAccountName")).getText();
		return Actal;

	}

	public String LoginPassWrong() {
		AccountName.sendKeys("LDDTesting");
		UserName.sendKeys("Automation");
		Password.sendKeys("Welcome-12345");
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText("Ontario");
		LoginButton.click();
		String Actal = ErrorMessageSection.getText();
		return Actal;

	}

	public String LoginUsernameWrong() {
		AccountName.sendKeys("LDDTesting");
		UserName.sendKeys("Automation1");
		Password.sendKeys("Automation");
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText("Ontario");
		LoginButton.click();
		String Actal = ErrorMessageSection.getText();
		return Actal;

	}

	public String LoginAccountnameWrong() {
		AccountName.sendKeys("LDDTesting1");
		UserName.sendKeys("Automation");
		Password.sendKeys("Automation");
		Select Province = new Select(ProductProvince);
		Province.selectByVisibleText("Ontario");
		LoginButton.click();
		String Actal = ErrorMessageSection.getText();
		return Actal;

	}
}
