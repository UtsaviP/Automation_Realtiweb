package home.PageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

	@FindBy(xpath = "(//span[@class='btn btn-primary'][text()='Create File'])[1]")
	public WebElement Active_Button;

	@FindBy(xpath = "//li[text()='Create New File']")
	public WebElement CreateNewFile1;

	@FindBy(xpath = "//table[@id='create_SectionTable']/tbody/tr[4]/td[2]/input")
	public WebElement MatterNumber;

	By Create_New_File = By.xpath("//a[text()='New File']");
	By FileNameBy = By.xpath("//tr[@name='OpeningInfo']//input[@class='input input-long']");
	By Frame = By.xpath("//iframe[@class='clsCompatHost']");	
	By P_TitleInsuranceTitle = By.xpath("//h2[contains(text(),'Select Title Insurance Provider') and @subnodestatus=0]");
	By S_FileConfigurationcaption = By.xpath("//tr[@name='NewHomeNonTPLine'][2]/td/span[contains(text(),'New Home Sold by a Builder')]");
	By M_MortgagorTitle = By.xpath("//span[contains(text(),'Mortgagor(s)')]");

	AzureDevOpsIntegration Azure = new AzureDevOpsIntegration();
	
	//Test Case 12048: Verify Purchase File created Successfully or not
	public FileList CreateNewPurchaseFile() throws InterruptedException, IOException {
	    try {
	        PlusButton.click();
	        waitForElementToAppear(Create_New_File);
	        driver.findElement(Create_New_File).click();
	        frame();
	        Thread.sleep(3000);
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
	            Azure.updateTestCaseStatus("12048", "Automation Pass","");
	            System.out.println(" *****Pass: Purchase File Created and deleted Successfully*****");
	        } else {
	            Azure.updateTestCaseStatus("12048", "Automation Fail","Fail: Purchase File not Created Successfully");
	            Assert.fail("****Fail: Purchase File not Created Successfully****");
	            
	        }
	    } catch (Exception e) {
	    	String exceptionTitle = e.getMessage().split("\n")[0]; 
	        Azure.updateTestCaseStatus("12048", "Automation Error",exceptionTitle);
	        Assert.fail("****Fail: Exception occurred during file creation****", e);

	    }
	    return new FileList(driver);
	}

	//Test Case 12049: Verify Sale File created Successfully or not
	public FileList CreateNewSaleFile() throws InterruptedException, IOException {
	    try {
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
	            Azure.updateTestCaseStatus("12049", "Automation Pass","");
	            System.out.println(" *****Pass: Sale File Created and deleted Successfully*****");
	        } else {	           
	            Azure.updateTestCaseStatus("12049", "Automation Fail","Fail: Sale File not Created Successfully");
	            Assert.fail("****Fail: Sale File not Created Successfully****");
	        }
	    } catch (Exception e) {
	    	String exceptionTitle = e.getMessage().split("\n")[0]; 
	        Azure.updateTestCaseStatus("12049", "Automation Error",exceptionTitle);
	        Assert.fail("****Fail: Exception occurred during file creation****", e);
	        
	    }
	    return new FileList(driver);
	}
	//Test Case 12050: Verify Mortgage File created Successfully or not
	public FileList CreateNewMortgageFile() throws InterruptedException, IOException {
	    try {
	        PlusButton.click();
	        waitForElementToAppear(Create_New_File);
	        driver.findElement(Create_New_File).click();
	        frame();
	        Thread.sleep(3000);
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
	            System.out.println(" *****Pass: Mortgage File Created and deleted Successfully*****");
	        }
	        
	        if (flag) {
	            Azure.updateTestCaseStatus("12050", "Automation Pass","");
	        } else {         
	            Azure.updateTestCaseStatus("12050", "Automation Fail","Fail: Mortgage File not Created Successfully");
	            Assert.fail("*****Fail: Mortgage File not Created Successfully*****");
	        }
	    } catch (Exception e) {
	    	String exceptionTitle = e.getMessage().split("\n")[0]; 
	        Azure.updateTestCaseStatus("12050", "Automation Error",exceptionTitle);	       
	        Assert.fail("****Fail: Exception occurred during file creation****", e);
	    }
	    return new FileList(driver);
	}

	//Test Case 12051: Create button should be Disable if file types and File name not provide
			public void DisableCreateFileButton() throws IOException {
			    try {
			        PlusButton.click();
			        waitForElementToAppear(Create_New_File);
			        driver.findElement(Create_New_File).click();
			        frame();
			        waitForWebElementToAppear(Inactive_Button);
			        boolean flag = false;

			        WebElement fileNameElement = driver.findElement(FileNameBy);
			        String fileName = fileNameElement.getAttribute("value");

			        if (fileName == null || fileName.isEmpty()) {
			            flag = true;
			            Azure.updateTestCaseStatus("12051", "Automation Pass","");			           
			            System.out.println(" *****Pass: Create button Disabled****");
			        }

			        if (!flag) {			           
			            Azure.updateTestCaseStatus("12051", "Automation Fail","Fail: Create button enabled even when the filename is available");
			            Assert.fail("*****Fail: Create button enabled even when the filename is available*****");
			        }
			    } catch (Exception e) {
			    	String exceptionTitle = e.getMessage().split("\n")[0]; 
			        Azure.updateTestCaseStatus("12051", "Automation Error",exceptionTitle);
			        Assert.fail("Fail",e);
			    }
			    
			}
	
	
	
	//Test Case 12052: Create button should be Enable if file types and File name are provide
	public void EnableCreateFileButton() throws InterruptedException, IOException {
	    try {
	        Thread.sleep(2000);
	        Select DealType_select = new Select(DealType);
	        DealType_select.selectByVisibleText("Purchase");
	        FileName.sendKeys("Purchase");
	        required_text.click();

	        boolean flag1 = false;
	        if (Active_Button.isEnabled()) {
	            flag1 = true;	           
	            Azure.updateTestCaseStatus("12052", "Automation Pass","");
	            System.out.println("*****Pass: Create button enabled*****");
	        }

	        if (!flag1) {            
	            Azure.updateTestCaseStatus("12052", "Automation Fail","Fail: Create button Disabled even filename is available");
	            Assert.fail("*****Fail: Create button Disabled even filename is available*****");
	        }
	    } catch (Exception e) {
	    	String exceptionTitle = e.getMessage().split("\n")[0]; 
	    	Azure.updateTestCaseStatus("12052", "Automation Error",exceptionTitle);
	    	Assert.fail("Fail",e);
	       
	        
	    }
	}


	//Test Case 12053: Fields should be reflect based on File types
	public void DropdownVerificationTest() throws InterruptedException, IOException {
	    boolean isVerificationSuccessful = true;
	    try {
	        PlusButton.click();
	        waitForElementToAppear(Create_New_File);
	        driver.findElement(Create_New_File).click();
	        frame();
	        Thread.sleep(2000);

	        // Define a data structure to store file types and their corresponding fields
	        Map<String, By> fileTypeToFields = new HashMap<>();
	        fileTypeToFields.put("Purchase", P_TitleInsuranceTitle);
	        fileTypeToFields.put("Sale", S_FileConfigurationcaption);
	        fileTypeToFields.put("Mortgage", M_MortgagorTitle);

	        Select DealType_select = new Select(DealType);

	        for (Map.Entry<String, By> entry : fileTypeToFields.entrySet()) {
	            String fileType = entry.getKey();
	            By fieldElement = entry.getValue();
	            
	            DealType_select.selectByVisibleText(fileType);
	            
	            if (!isElementDisplayed(fieldElement)) {
	                System.out.println("Failed to show " + fileType + " related fields.");
	                isVerificationSuccessful = false;
	            }
	        }
	    } catch (Exception e) {
	    	String exceptionTitle = e.getMessage().split("\n")[0]; 
	    	  Azure.updateTestCaseStatus("12053", "Automation Error",exceptionTitle);
	    	Assert.fail("Fail",e);	       
	        isVerificationSuccessful = false;
	    }

	    if (isVerificationSuccessful) {
	        Azure.updateTestCaseStatus("12053", "Automation Pass","");
	        System.out.println("*****Pass: Dropdown verification test passed.*****");
	    } else {
	        Azure.updateTestCaseStatus("12053", "Automation Fail","Fail: Dropdown verification test failed.");	       
	        Assert.fail("*****Fail: Dropdown verification test failed.*****");
	    }
	}

	// Test Case 12054: verify warning message while provide same name that already exist in database
	public void verifyWarningForDuplicateFileName(String Purchase_FileName) throws InterruptedException, IOException {
		PlusButton.click();
		waitForElementToAppear(Create_New_File);
		driver.findElement(Create_New_File).click();
		frame();
		Thread.sleep(2000);
		Select DealType_select = new Select(DealType);
		DealType_select.selectByVisibleText("Purchase");

		MatterNumber.sendKeys(Purchase_FileName);

		driver.findElement(By.xpath("(//span[@class='help-note' and @subnodestatus='0' ])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(CreateFile_Button));

		CreateFile_Button.click();
        Thread.sleep(2000);
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String alertText = alert.getText();
			if (alertText.contains("Do you still want to create this file?")) {
				alert.dismiss();
				Azure.updateTestCaseStatus("12054", "Automation Pass","");
				System.out.println("*****Pass: Warning message displayed for duplicate file name.*****");
			} else {
				alert.dismiss();
				Azure.updateTestCaseStatus("12054", "Automation Fail","Fail: No warning message displayed for duplicate file name.");				
				Assert.fail("*****Fail: No warning message displayed for duplicate file name.*****");
			}
		} catch (NoAlertPresentException e) {
			String exceptionTitle = e.getMessage().split("\n")[0]; 
			Azure.updateTestCaseStatus("12054", "Automation Error",exceptionTitle);		
			Assert.fail("Fail",e);
				
		}
	}



	// Switch to frame
	public void frame() {
		WebElement iframe = driver.findElement(Frame);
		driver.switchTo().frame(iframe);
	}

}
