package home.PageObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import home.PageObject.*;
import project.AbstractComponents.AbstractComponent;

public class Advance_Search_Filter extends AbstractComponent {
	WebDriver driver;

	public Advance_Search_Filter(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// FileList->Advance Search filter PageFactory

	By CancelButton = By.xpath("//button[contains(text(),'Cancel')]");
	By AdvanceSearchbutton = By.xpath("//i[@id='searchOptionBtn2']");
	By Filelist = By.xpath("//table[@class='table table-striped table-hover']//thead//tr/th");
	By ClearSearch = By.xpath("//button[@id='clearSearchBtn']");
	By SelectLawyer1 = By.xpath("//div[@id='adSearchContainer']//div[1]//select[1]");
	By FileLawyerName = By.xpath("//tr[@title='Lawyer responsible for this file']/td[2]/span");
	By FileFirmName = By.xpath("//tr[@xmlbase='Staff']//span[@class='input-inactive input-long']/span[1]");
	By Filelistname = By.xpath("//div[@id='ListSection']//table//tbody//td[1]/span");
	By Filelist11 = By.xpath("//div[@id='ListSection']");
	By HomeButton = By.xpath("//div[@id='root']//div[1]//a[1]");
	By ClientNameField = By.xpath("//body/div[@id='root']/main[1]//div[3]/input[@class='form-control']");
	By Reline_IncludeField = By.xpath("(//input[@class='form-control'])[2]");
	By Reline_ExcludeField = By.xpath("//div[@id='adSearchContainer']/div[@class='mb-5 row']/div[2]/input[1]");
	By Reline_InsideFile = By.xpath("//div[@class='card-header']/div[2]/div[1]");
	By InsideFileName = By.xpath("(//div[@class='col'])[1]/b");
	By Closing_After = By.xpath("//body/div[@id='root']/main[1]//div[3]/div[3]/div[1]/div[1]/div[1]/input[1]");
	By Closing_Before = By.xpath("//body/div[@id='root']/main[1]//div[3]/div[4]/div[1]/div[1]/div[1]/input[1]");
	By Propertyaddress = By.xpath("//div[@class='col-6 col']/input");
	By Propertytype = By.xpath("//div[@class='mb-5 row']/div[2]/label[text()='Property Type']/following-sibling::*");
	By menu_property=By.xpath("(//div[@class='me-auto'])[4]");
	
	
	@FindBy(xpath = "//label[contains(text(),'Purchase')]")
	public WebElement PurchaseCheckbox;

	@FindBy(xpath = "//iframe[@class='clsCompatHost']")
	public WebElement frametest;

	@FindBy(xpath = "//label[contains(text(),'Sale')]")
	public WebElement SaleCheckbox;

	@FindBy(xpath = "//label[contains(text(),'Mortgage')]")
	public WebElement MortgageCheckbox;

	@FindBy(xpath = "//label[contains(text(),'Archive')]")
	public WebElement ArchivedCheckbox;

	@FindBy(xpath = "//label[contains(text(),'Active')]")
	public WebElement ActiveCheckbox;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	public WebElement SearchButton;

	@FindBy(xpath = "//table[2]/tbody[1]/tr[3]/td[2]/input[2]")
	public WebElement FileName;

	@FindBy(xpath = "//div[@id='ListSection']//table//tbody//td[4]/span")
	List<WebElement> ListFileType;

	@FindBy(xpath = "//div[@id='ListSection']//table//tbody//td[5]/span")
	List<WebElement> ListClosingDate;

	@FindBy(xpath = "//div[@id='ListSection']//table//tbody//td[6]/span")
	List<WebElement> ListFileStatus;

	@FindBy(xpath = "//div[@id='ListSection']//table//tbody//td[1]/span")
	List<WebElement> ListFileName;

	@FindBy(xpath = "//div[@id='ListSection']//table//tbody//td[2]/span")
	List<WebElement> ListClientName;

	@FindBy(xpath = "//div[@id='ListSection']//table//tbody//td[3]/span")
	List<WebElement> ListAddress;

	@FindBy(xpath = "//div[@id='adSearchContainer']//div[1]//select[1]")
	public WebElement SelectLawyer;
	
	@FindBy(xpath = "//tr[@title=\"If not a standard condominium plan description, click on 'Input text below' in 'Input Legal Description details' and complete.\"]//td//select")
	public WebElement PropertyType;
	
	
	
	@FindBy(xpath = "(//select[@class='form-select'])[2]")
	public WebElement SelectFirmContact;

	@FindBy(xpath = "//div[@id='ListSection']//table//tbody//td[1]/span")
	List<WebElement> Filelist1;

	// Verify Cancel Button working proper or not
	public FileList CancelButton() throws InterruptedException {
		AdvanceSearchButton();
		waitForElementToAppear(CancelButton);
		driver.findElement(CancelButton).click();

		boolean isPopupClosed = isElementNotPresent(driver, By.id("//div[@id='adSearchContainer']"));

		if (isPopupClosed) {
			System.out.println("Cancel button works: Pop-up is closed.");
		} else {
			System.out.println("Cancel button does not work: Pop-up is still open.");
		}

		return new FileList(driver);
	}

	// Verify Search with File Types Purchase and Sale
	public FileList AdvanceSearchFileTypes() throws InterruptedException {
		AdvanceSearchButton();
		Thread.sleep(3000);

		if (PurchaseCheckbox.isSelected()) {
			PurchaseCheckbox.click();
		}
		if (SaleCheckbox.isSelected()) {
			SaleCheckbox.click();
		}
		if (!MortgageCheckbox.isSelected()) {
			MortgageCheckbox.click();
		}
		SearchButton.click();
		Thread.sleep(4000);
		List<WebElement> getFileList = ListFileType;
		List<WebElement> getFileList1 = ListFileName;
		boolean allFilesArePurchaseOrSale = true;
		for (int i = 0; i < getFileList.size(); i++) {
			WebElement fileTypeElement = getFileList.get(i);
			WebElement fileNameElement = getFileList1.get(i);

			String filetype = fileTypeElement.getText();
			String fileName = fileNameElement.getText();
			if (filetype.contains("Purchase") || filetype.contains("Sale")) {
				System.out.println("Expected file: " + fileName + ":" + filetype);
			} else {
				System.out.println("UnExpected file: " + fileName + ":" + filetype);
			}
		}

		if (allFilesArePurchaseOrSale) {
			System.out.println("Verification successful: Only 'Purchase' and 'Sale' files are displayed.");
		} else {
			allFilesArePurchaseOrSale = false;
			System.out.println("Verification failed: Other file types are also displayed.");
		}

		return new FileList(driver);

	}

	// Verify File Status Active and Archives working or not
	public FileList AdvanceSearchFileStatus() throws InterruptedException {

		if (isElementDisplayed(ClearSearch)) {
			waitForElementToAppear(ClearSearch);
			driver.findElement(ClearSearch).click();
		}
		AdvanceSearchButton();

		if (!ActiveCheckbox.isSelected()) {
			ActiveCheckbox.click();
		}
		if (!ArchivedCheckbox.isSelected()) {
			ArchivedCheckbox.click();
		}
		SearchButton.click();
		waitForElementToAppear(Filelist);
		List<WebElement> getFileList = ListFileStatus;
		List<WebElement> getFileList1 = ListFileName;
		boolean allFilesAreArchived = true;

		for (int i = 0; i < getFileList.size(); i++) {
			WebElement fileStatusElement = getFileList.get(i);
			WebElement fileNameElement = getFileList1.get(i);

			String fileStatus = fileStatusElement.getText();
			String fileName = fileNameElement.getText();

			if (fileStatus.contains("Archived")) {
				System.out.println("Expected file: " + fileName + ":" + fileStatus);
			} else {
				allFilesAreArchived = false; // Update the flag to false if any non-archived file is found
				System.out.println("Unexpected file: " + fileName + ":" + fileStatus);
			}
		}

		if (allFilesAreArchived) {
			System.out.println("Verification successful: Only 'Archived' files are displayed.");
		} else {
			allFilesAreArchived = false;
			System.out.println("Verification failed: Other file statuses are also displayed.");
		}

		return new FileList(driver);

	}

	// Verify Responsible Lawyer working proper or not
	public FileList AdvanceSearchResponsibleLawyer(String LawyerName) throws InterruptedException {

		ClearSearch();
		AdvanceSearchButton();

		Select selectLawyer = new Select(SelectLawyer);
		selectLawyer.selectByValue(LawyerName);

		SearchButton.click();

		waitForElementToAppear(Filelist11);
		Thread.sleep(3000);
		List<WebElement> fileList1 = driver.findElements(Filelistname);
		boolean lawyerNameMatch = false;
		if (fileList1 != null && !fileList1.isEmpty()) {
			fileList1.get(0).click();
			waitForWebElementToAppear(frametest);
			driver.switchTo().frame(frametest);
			waitForElementToAppear(FileLawyerName);
			String fileContentText = driver.findElement(FileLawyerName).getText();
			lawyerNameMatch = fileContentText.equals(LawyerName);
			if (lawyerNameMatch) {
				System.out.println("Pass: Lawyer name match found in the file content.");
			} else {
				System.out.println("Fail: Lawyer name match not found in the file content.");
			}
			driver.switchTo().defaultContent();
		} else {
			System.out.println("No Search result found");
		}
		return new FileList(driver);
	}

	// Verify Firm contact working proper or not
	public FileList AdvanceSearchFirmContact(String FirmContact) throws InterruptedException {

		Homebutton();
		ClearSearch();
		AdvanceSearchButton();

		Select selectLawyer = new Select(SelectFirmContact);
		selectLawyer.selectByValue(FirmContact);

		SearchButton.click();

		waitForElementToAppear(Filelist11);
		Thread.sleep(3000);
		List<WebElement> fileList1 = driver.findElements(Filelistname);
		boolean FirmNameMatch = false;
		if (fileList1 != null && !fileList1.isEmpty()) {
			fileList1.get(0).click();
			waitForWebElementToAppear(frametest);
			driver.switchTo().frame(frametest);
			waitForElementToAppear(FileFirmName);
			String fileContentText = driver.findElement(FileFirmName).getText();
			FirmNameMatch = fileContentText.equals(FirmContact);
			if (FirmNameMatch) {
				System.out.println("Pass: Firm name match found in the file content.");
			} else {
				System.out.println("Fail: Firm name match not found in the file content.");
			}
			driver.switchTo().defaultContent();
		} else {
			System.out.println("No Search result found");
		}
		return new FileList(driver);
	}

	// Verify ClientName working or not with different criteria
	public FileList AdvanceSearchClientname(String AdvanceSearch_Client1, String AdvanceSearch_Client2,
			String AdvanceSearch_Client3) throws InterruptedException {

		Homebutton();

		String[] clientNames = { AdvanceSearch_Client1, AdvanceSearch_Client2, AdvanceSearch_Client3 };
		for (String clientName : clientNames) {
			ClearSearch();
			AdvanceSearchButton();
			waitForElementToAppear(ClientNameField);
			driver.findElement(ClientNameField).clear();
			driver.findElement(ClientNameField).sendKeys(clientName);
			SearchButton.click();

			List<WebElement> initialFileList = driver.findElements(Filelistname);
			waitForFileListUpdate(initialFileList);
			List<WebElement> getFileList = ListClientName;

			boolean allClientname = true;
			for (int i = 0; i < getFileList.size(); i++) {

				// Re-find the elements after waiting
				initialFileList = driver.findElements(Filelistname);
				getFileList = ListClientName;
				WebElement clientnameElement = getFileList.get(i);
				WebElement fileNameElement = initialFileList.get(i);

				String clientname = clientnameElement.getText();
				String fileName = fileNameElement.getText();
				if (clientname.contains(clientName)) {
					System.out.println("Expected file: " + fileName + ":" + clientname);
				} else {
					allClientname = false;
					System.out.println("UnExpected file: " + fileName + ":" + clientname);
				}
			}

		}

		return new FileList(driver);
	}

	// Verify Reline Criteria working proper or not
	@SuppressWarnings("unlikely-arg-type")
	public FileList AdvanceSearchReline(String[] AdvanceSearch_Reline) throws InterruptedException {

		Homebutton();
		for (String includeCriteria : AdvanceSearch_Reline) {
			performSearch(includeCriteria, true);

			// Click on the first file in the list
			boolean RelineMatch = false;
			clickOnFirstFile();
			waitForElementToAppear(InsideFileName);
			String fileContentText = driver.findElement(Reline_InsideFile).getText();
			boolean anyRelineMatch = false;
			for (String reline : AdvanceSearch_Reline) {
				if (fileContentText.contains(reline)) {
					anyRelineMatch = true;
					System.out.println("Pass: Reline name match found in the file content.");
					break; // Exit the loop once a match is found
				}
			}

			if (!anyRelineMatch) {
				System.out.println("Fail: Reline name match not found in the file content.");
			}

			Homebutton();
			for (String excludeCriteria : AdvanceSearch_Reline) {
				performSearch(excludeCriteria, false);
				boolean noRelinematch = true;
				clickOnFirstFile();
				waitForElementToAppear(InsideFileName);
				String fileContentText1 = driver.findElement(Reline_InsideFile).getText();

				for (String reline : AdvanceSearch_Reline) {
					if (fileContentText1.contains(reline)) {
						noRelinematch = false;

						System.out.println("Fail: Reline name match found in the file content.");
						break; // Exit the loop once a match is found
					}
				}

				if (noRelinematch) {
					System.out.println("Pass: Reline name match not found in the file content.");
				}

			}
		}
		return new FileList(driver);

	}

	// Method to perform search in the Advance Search page
	private void performSearch(String AdvanceSearch_Reline, boolean isIncludeCriteria) {
		ClearSearch();
		AdvanceSearchButton();

		By searchLocator = isIncludeCriteria ? Reline_IncludeField : Reline_ExcludeField;

		WebElement searchField = driver.findElement(searchLocator);

		waitForWebElementToAppear(searchField);
		searchField.clear();
		searchField.sendKeys(AdvanceSearch_Reline);
		SearchButton.click();

		// Wait for file list update
		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);

		// Check if any files were found
		if (initialFileList != null && !initialFileList.isEmpty()) {

			// You can add any additional action or verification here
		} else {
			System.out.println("No Search Result found");
			// You can add any additional action or verification here
		}
	}

	// Method to click on the first file in the list
	private void clickOnFirstFile() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		waitForElementToAppear(Filelist11);

		List<WebElement> fileList1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Filelistname));

		if (fileList1 != null && !fileList1.isEmpty()) {
			wait.until(ExpectedConditions.elementToBeClickable(fileList1.get(0))).click();
		} else {
			System.out.println("No Search result found");
		}
	}

	public void waitForFileListUpdate(List<WebElement> initialFileList) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try {
			// Create a wait condition for staleness of the initial file list
			ExpectedCondition<Boolean> staleCondition = ExpectedConditions.stalenessOf(initialFileList.get(0));

			// Wait until the condition is met
			wait.until(staleCondition);

			// Wait for the updated file list to appear
			List<WebElement> updatedFileList = wait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Filelistname));

			// Perform an initial check to get the size of the file list
			int initialSize = initialFileList.size();

			// Check if the updated file list size has changed
			if (updatedFileList.size() != initialSize) {
				return; // File list has been updated
			} else {
				throw new TimeoutException("File list update was not detected.");
			}
		} catch (TimeoutException ex) {
			// Handle the timeout exception
			System.out.println("File list update was not detected within the specified timeout.");
		} catch (StaleElementReferenceException ex) {
			// Handle the stale element reference exception
			System.out.println("Stale element reference exception occurred.");
		}
	}

	// verify closing date After and before working proper or not
	public FileList AdvanceSearchClosingDate(String ClosingDate_After, String ClosingDate_Before)
			throws InterruptedException {
		String beforeClosingDate = ClosingDate_After;
		String afterClosingDate = ClosingDate_Before;
		Homebutton();
		ClearSearch();
		AdvanceSearchButton();
		waitForElementToAppear(Closing_After);
		driver.findElement(Closing_After).sendKeys(beforeClosingDate);
		waitForElementToAppear(Closing_Before);
		driver.findElement(Closing_Before).sendKeys(afterClosingDate);
		SearchButton.click();

		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);

		String inputValue = driver.findElement(By.xpath("//input[@id='fileSearchInput']")).getAttribute("value");

		Pattern pattern = Pattern.compile("from:\\((.*?)\\)\\s*to:\\((.*?)\\)");
		Matcher matcher = pattern.matcher(inputValue);

		if (matcher.find()) {

			String fromDate = matcher.group(1);
			String toDate = matcher.group(2);

			for (WebElement file : ListClosingDate) {
				String closingdate = file.getText();

				if (isDateInRange(closingdate, fromDate, toDate)) {

					System.out.println("Pass :File is within the specified date range.");
				} else {

					System.out.println("Fail :File is not within the specified date range.");
				}
			}
		}
		return new FileList(driver);

	}

	// verify property address working proper or not
	public FileList AdvanceSearchAddress(String Advancesearch_Address1, String Advancesearch_Address2)
			throws InterruptedException {

		Homebutton();

		String[] Addresses = { Advancesearch_Address1, Advancesearch_Address2 };
		for (String AddressName : Addresses) {
			ClearSearch();
			AdvanceSearchButton();
			waitForElementToAppear(Propertyaddress);
			driver.findElement(Propertyaddress).clear();
			driver.findElement(Propertyaddress).sendKeys(AddressName);
			SearchButton.click();

			List<WebElement> initialFileList = driver.findElements(Filelistname);
			waitForFileListUpdate(initialFileList);
			List<WebElement> getAddressList = ListAddress;

			boolean allAddressname = true;
			for (int i = 0; i < getAddressList.size(); i++) {

				// Re-find the elements after waiting
				initialFileList = driver.findElements(Filelistname);
				getAddressList = ListAddress;
				WebElement addressElement = getAddressList.get(i);
				WebElement fileNameElement = initialFileList.get(i);

				String Address = addressElement.getText();
				String fileName = fileNameElement.getText();
				if (Address.contains(AddressName)) {
					{
						System.out.println("Expected file: " + fileName + ":" + Address);
					}
				} else {
					allAddressname = false;
					System.out.println("UnExpected file: " + fileName + ":" + Address);
				}
			}

		}

		return new FileList(driver);
	}

	
	//verify property type working proper or not
	@SuppressWarnings("unlikely-arg-type")
	public FileList AdvanceSearchPropertyType(String[] AdvanceSearch_Reline) throws InterruptedException {

		Homebutton();
		for (String includeCriteria : AdvanceSearch_Reline) {
			performSearch(includeCriteria, true);

			// Click on the first file in the list
			boolean RelineMatch = false;
			clickOnFirstFile();
			waitForElementToAppear(InsideFileName);
			String fileContentText = driver.findElement(Reline_InsideFile).getText();
			boolean anyRelineMatch = false;
			for (String reline : AdvanceSearch_Reline) {
				if (fileContentText.contains(reline)) {
					anyRelineMatch = true;
					System.out.println("Pass: Reline name match found in the file content.");
					break; // Exit the loop once a match is found
				}
			}

			if (!anyRelineMatch) {
				System.out.println("Fail: Reline name match not found in the file content.");
			}

			Homebutton();
			for (String excludeCriteria : AdvanceSearch_Reline) {
				performSearch(excludeCriteria, false);
				boolean noRelinematch = true;
				clickOnFirstFile();
				waitForElementToAppear(InsideFileName);
				String fileContentText1 = driver.findElement(Reline_InsideFile).getText();

				for (String reline : AdvanceSearch_Reline) {
					if (fileContentText1.contains(reline)) {
						noRelinematch = false;

						System.out.println("Fail: Reline name match found in the file content.");
						break; // Exit the loop once a match is found
					}
				}

				if (noRelinematch) {
					System.out.println("Pass: Reline name match not found in the file content.");
				}

			}
		}
		return new FileList(driver);

	}

	
	
	
	
	
	private boolean isDateInRange(String closingdate, String fromDate, String toDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {

			// Check if the file's closing date is within the specified range
			return (closingdate.compareTo(fromDate) >= 0) && (closingdate.compareTo(toDate) <= 0);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void ClearSearch() {
		if (isElementDisplayed(ClearSearch)) {
			driver.findElement(ClearSearch).click();
		}

	}

	public void Homebutton() {
		driver.switchTo().defaultContent();
		if (isElementDisplayed(HomeButton)) {
			driver.findElement(HomeButton).click();
		}
	}

	public void AdvanceSearchButton() {
		waitForElementToAppear(AdvanceSearchbutton);
		driver.findElement(AdvanceSearchbutton).click();
	}

}
