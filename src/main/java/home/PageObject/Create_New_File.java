package home.PageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.netty.util.internal.ThreadLocalRandom;
import project.AbstractComponents.AbstractComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Create_New_File extends AbstractComponent {
	WebDriver driver;

	public Create_New_File(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// FileList->Create New File PageFactory
	@FindBy(xpath = "//button[@class='dropdown-toggle btn btn-primary' and @aria-expanded='false']")
	public WebElement PlusButton;

	@FindBy(xpath = "(//select[@title='Select the type of transaction for this deal'])[1]")
	public WebElement DealType;

	@FindBy(xpath = "(//input[@class='input input-long'])[1]")
	public WebElement FileName;

	@FindBy(xpath = "(//span[contains(text(),'Create File')])[2]")
	public WebElement CreateFile_Button;

	@FindBy(xpath = "(//td[text()='File Details'])[1]")
	public WebElement required_text;

	@FindBy(xpath = "//div[i[@class='fa-light fa-file fa-indicator']]/b")
	public WebElement BoldFilename;

	@FindBy(xpath = "//div[@class='options dropstart']/i[@class='fa-solid fa-ellipsis-vertical']")
	public WebElement ActionMenu;

	@FindBy(xpath = "//a[contains(text(),'Delete File')]")
	public WebElement DeleteFile;

	@FindBy(xpath = "//button[contains(text(),'Delete') and @class='btn btn-primary']")
	public WebElement DeleteButton;

	@FindBy(xpath = "//td[@valign='middle']/span[@class='btn btn-inactive' and text()='Create File']")
	public WebElement Inactive_Button;

	@FindBy(xpath = "(//span[@class='btn btn-primary'][normalize-space()='Create File'])[1]")
	public WebElement Active_Button;

	@FindBy(xpath = "//li[text()='Create New File']")
	public WebElement CreateNewFile1;

	By Create_New_File = By.xpath("//a[text()='New File']");
	By FileNameBy = By.xpath("//tr[@name='OpeningInfo']//input[@class='input input-long']");
	By Frame = By.xpath("//body/div[@id='root']/main[1]/div[1]/div[1]/div[1]/iframe[1]");

	// Verify Purchase File created Successfully or not
	public FileList CreateNewPurchaseFile() throws InterruptedException {
		PlusButton.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		frame();
		Select DealType_select = new Select(DealType);
		DealType_select.selectByVisibleText("Purchase");
		int int_random = ThreadLocalRandom.current().nextInt();
		WebElement a = driver.findElement(FileNameBy);
		a.sendKeys("PurchaseFile_" + int_random);
		String GetFileName = a.getAttribute("value");
		required_text.click();
		CreateFile_Button.click();

		waitForWebElementToAppear(BoldFilename);
		String GetFileName1 = BoldFilename.getText();
		Thread.sleep(3000);
		boolean flag = false;
		if (GetFileName.equalsIgnoreCase(GetFileName1)) {
			flag = true;
			ActionMenu.click();
			DeleteFile.click();
			DeleteButton.click();
			waitForWebElementToAppear(PlusButton);
			System.out.println(" Purchase File Created and deleted Successfully:PASS");
		}
		Assert.assertTrue(flag, "Purchase File not Created Successfully");
		return new FileList(driver);
	}

	// Verify Sale File created Successfully or not
	public FileList CreateNewSaleFile() throws InterruptedException {
		PlusButton.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		frame();
		Thread.sleep(3000);
		Select DealType_select = new Select(DealType);
		DealType_select.selectByVisibleText("Sale");
		int int_random = ThreadLocalRandom.current().nextInt();
		WebElement a = driver.findElement(FileNameBy);
		a.sendKeys("SaleFile_" + int_random);
		String GetFileName = a.getAttribute("value");
		required_text.click();
		CreateFile_Button.click();
		waitForWebElementToAppear(BoldFilename);
		String GetFileName1 = BoldFilename.getText();
		Thread.sleep(3000);
		boolean flag = false;
		if (GetFileName.equalsIgnoreCase(GetFileName1)) {
			flag = true;
			waitForWebElementToAppear(ActionMenu);
			ActionMenu.click();
			DeleteFile.click();
			DeleteButton.click();
			waitForWebElementToAppear(PlusButton);
			System.out.println(" Sale File Created and deleted Successfully:PASS");

		}
		Assert.assertTrue(flag, "Sale File not Created Successfully");
		return new FileList(driver);
	}

	// Verify Mortgage File created Successfully or not
	public FileList CreateNewMortgageFile() throws InterruptedException {
		PlusButton.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		frame();
		Thread.sleep(2000);
		Select DealType_select = new Select(DealType);
		DealType_select.selectByVisibleText("Mortgage");
		int int_random = ThreadLocalRandom.current().nextInt();
		WebElement a = driver.findElement(FileNameBy);
		a.sendKeys("MortgageFile_" + int_random);
		String GetFileName = a.getAttribute("value");
		required_text.click();
		CreateFile_Button.click();
		waitForWebElementToAppear(BoldFilename);
		String GetFileName1 = BoldFilename.getText();
		Thread.sleep(3000);
		boolean flag = false;
		if (GetFileName.equalsIgnoreCase(GetFileName1)) {
			flag = true;
			waitForWebElementToAppear(ActionMenu);
			ActionMenu.click();
			DeleteFile.click();
			DeleteButton.click();
			waitForWebElementToAppear(PlusButton);
			System.out.println(" Mortgage File Created and deleted Successfully:PASS");

		}
		Assert.assertTrue(flag, "Mortgage File not Created Successfully");
		return new FileList(driver);
	}

	// Create button should be Disable if file types and File name not provide
	public void DisableCreateFileButton() {
		PlusButton.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		frame();
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

	// Create button should be Enable if file types and File name are provide
	public void EnableCreateFileButton() throws InterruptedException {
		Thread.sleep(2000);
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
	
   //Switch to frame
	public void frame() {
		WebElement iframe = driver.findElement(Frame);
		driver.switchTo().frame(iframe);
	}

}
