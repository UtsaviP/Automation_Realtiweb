package menubar_File.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.netty.util.internal.ThreadLocalRandom;
import menubar_Main.PageObject.MainPage;
import project.AbstractComponents.AbstractComponent;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Create_New_File extends AbstractComponent {
	WebDriver driver;

	public Create_New_File(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// File->Create New File PageFactory
	@FindBy(id = "tdMenuBarItemFile")
	public WebElement ToolsOption_File;
	
	@FindBy(xpath = "(//select[@title='Select the type of transaction for this deal'])[1]")
	public WebElement DealType;
	
	@FindBy(xpath = "(//input[@class='input input-long'])[1]")
	public WebElement FileName;
	
	@FindBy(xpath = "(//span[contains(text(),'Create File')])[2]")
	public WebElement CreateFile_Button;
	
	@FindBy(xpath = "(//td[text()='File Details'])[1]")
	public WebElement required_text;
	
	@FindBy(xpath = "//tr[@class='file-header']//td/b")
	public WebElement HeaderFileName;
	
	@FindBy(xpath = "//i[@class='fa active ldd-ellipsis solid']")
	public WebElement ActionMenu;
	
	@FindBy(xpath = "(//span[@class='status-fail'][text()='Delete File'])[2]")
	public WebElement DeleteFile;

	@FindBy(xpath = "(//span[@class='btn btn-inactive'][normalize-space()='Create File'])[1]")
	public WebElement Inactive_Button;

	@FindBy(xpath = "(//span[@class='btn btn-primary'][normalize-space()='Create File'])[1]")
	public WebElement Active_Button;

	@FindBy(xpath = "//li[text()='Create New File']")
	public WebElement CreateNewFile1;

	By Create_New_File = By.xpath("//body[@class='bg ldd_main_body menu']//li[text()='Create New File']");
	By FileNameBy = By.xpath("//tr[@name='OpeningInfo']//input[@class='input input-long']");

	public MainPage CreateNewPurchaseFile() throws InterruptedException {
		ToolsOption_File.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		Thread.sleep(2000);
		Select DealType_select = new Select(DealType);
		DealType_select.selectByVisibleText("Purchase");
		int int_random = ThreadLocalRandom.current().nextInt();
		WebElement a = driver.findElement(FileNameBy);
		a.sendKeys("PurchaseFile_" + int_random);
		String GetFileName = a.getAttribute("value");
		required_text.click();
		CreateFile_Button.click();
		waitForWebElementToAppear(HeaderFileName);
		String GetFileName1 = HeaderFileName.getText();
		Thread.sleep(3000);
		boolean flag = false;
		if (GetFileName.equalsIgnoreCase(GetFileName1)) {
			flag = true;
			ActionMenu.click();
			DeleteFile.click();
			driver.switchTo().alert().accept();
			System.out.println(" Purchase File Created and deleted Successfully:PASS");
			
		}
		Assert.assertTrue(flag, "Purchase File not Created Successfully");
		return new MainPage(driver);
	}

	public MainPage CreateNewSaleFile() throws InterruptedException {
		ToolsOption_File.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		Thread.sleep(2000);
		Select DealType_select = new Select(DealType);
		DealType_select.selectByVisibleText("Sale");
		int int_random = ThreadLocalRandom.current().nextInt();
		WebElement a = driver.findElement(FileNameBy);
		a.sendKeys("SaleFile_" + int_random);
		String GetFileName = a.getAttribute("value");
		required_text.click();
		CreateFile_Button.click();
		waitForWebElementToAppear(HeaderFileName);
		String GetFileName1 = HeaderFileName.getText();

		boolean flag = false;
		if (GetFileName.equalsIgnoreCase(GetFileName1)) {
			flag = true;
			waitForWebElementToAppear(ActionMenu);
			ActionMenu.click();
			DeleteFile.click();
			driver.switchTo().alert().accept();
			System.out.println(" Sale File Created and deleted Successfully:PASS");
			
		}
		Assert.assertTrue(flag, "Sale File not Created Successfully");
		return new MainPage(driver);
	}

	public MainPage CreateNewMortgageFile() throws InterruptedException {
		ToolsOption_File.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		Thread.sleep(2000);
		Select DealType_select = new Select(DealType);
		DealType_select.selectByVisibleText("Mortgage");
		int int_random = ThreadLocalRandom.current().nextInt();
		WebElement a = driver.findElement(FileNameBy);
		a.sendKeys("MortgageFile_" + int_random);
		String GetFileName = a.getAttribute("value");
		required_text.click();
		CreateFile_Button.click();
		waitForWebElementToAppear(HeaderFileName);
		String GetFileName1 = HeaderFileName.getText();
		Thread.sleep(3000);
		boolean flag = false;
		if (GetFileName.equalsIgnoreCase(GetFileName1)) {
			flag = true;
			waitForWebElementToAppear(ActionMenu);
			ActionMenu.click();
			DeleteFile.click();
			driver.switchTo().alert().accept();
			System.out.println(" Mortgage File Created and deleted Successfully:PASS");

		}
		Assert.assertTrue(flag, "Mortgage File not Created Successfully");
		return new MainPage(driver);
	}

	public void DisableCreateFileButton() {
		ToolsOption_File.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		waitForWebElementToAppear(Inactive_Button);
		boolean flag = false;
		if (Inactive_Button.isEnabled());
			

		{
			flag = true;
			FileName.getAttribute("type").isBlank();
			System.out.println(" Create button Disabled:PASS");
		}

		Assert.assertTrue(flag, "Create button enabled even filename is not available");

	}

	public void EnableCreateFileButton() {
		ToolsOption_File.click();
		// waitForElementToAppear(CreateNewFile1);
		CreateNewFile1.click();
		waitForWebElementToAppear(Inactive_Button);
		Select DealType_select = new Select(DealType);
		DealType_select.selectByVisibleText("Purchase");
		FileName.sendKeys("Purchase");
		required_text.click();

		boolean flag1 = false;
		if (Active_Button.isEnabled());			

		{
			flag1 = true;
			System.out.println(" Create button enabled:PASS");
		}

		Assert.assertTrue(flag1, "Create button Disabled even filename is available");

	}
}
