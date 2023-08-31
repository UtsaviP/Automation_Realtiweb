package home.PageObject;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
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
import org.testng.Assert;


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
	By FileLawyerName = By.xpath("//tr[@title='Lawyer responsible for this file']/td[2]/span");
	By FileFirmName = By.xpath("//tr[@xmlbase='Staff']//span[@class='input-inactive input-long']/span[1]");
	By Filelistname = By.xpath("//div[@id='ListSection']//table//tbody//td[1]/span");
	By HomepageFilelist = By.xpath("//div[@id='ListSection']");
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
	By menu_property = By.xpath("//span[@class='item-name' and contains(text(),'Property')]");
	By FilePropertyType = By.cssSelector("select[required='1'][requiredstatus='1'][attrname='PropertyType']");
	By LawyerName = By.xpath("//div[@class='row']//div[1]//input[1]");
	By FileLawyername = By
			.cssSelector("tr[xmlbase='Solicitor'] span[class='input-inactive input-long'] span[attrname='Name']");
	By Filenumber = By.xpath("//div[@class='row']//div[2]//input[1]");
	By Noresult = By.xpath("//i[normalize-space()='- No Search Results Found -']");
	By OtherFileno=By.xpath("//input[@attrname='OtherSideMatterNumber']");
			
	@FindBy(xpath = "//label[contains(text(),'Purchase')]")
	public WebElement PurchaseCheckbox;

	@FindBy(xpath = "//iframe[@class='clsCompatHost']")
	public WebElement InsidefileFrame;

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

	@FindBy(xpath = "(//select[@class='form-select'])[2]")
	public WebElement SelectFirmContact;

	@FindBy(xpath = "//div[@id='ListSection']//table//tbody//td[1]/span")
	List<WebElement> WebFilelist;

	// Verify Cancel Button working proper or not
	public FileList CancelButton() throws InterruptedException {
		AdvanceSearchButton();
		waitForElementToAppear(CancelButton);
		driver.findElement(CancelButton).click();

		boolean isPopupClosed = isElementNotPresent(driver, By.id("//div[@id='adSearchContainer']"));

		if (isPopupClosed) {
			System.out.println("*****Pass : Cancel button works: Pop-up is closed.*****");
		} else {
			Assert.fail("*****Fail : Cancel button does not work: Pop-up is still open.*****");
			
		}

		return new FileList(driver);
	}

	// Verify File Types Criteria working or not
	public FileList AdvanceSearchFileTypes() throws InterruptedException {
	   // ClearAndHome();
	    AdvanceSearchButton();
	    Filelistwait(); // explicit wait

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

	    List<WebElement> initialFileList = driver.findElements(Filelistname);

	    waitForFileListUpdate(initialFileList);

	    if (!isElementDisplayed(Noresult)) {
	        List<WebElement> getFileList = ListFileType; // Move this line before the loop

	        boolean allFilesArePurchaseOrSale = false;
	        for (int i = 0; i < Math.min(getFileList.size(), initialFileList.size()); i++) {
	            WebElement fileTypeElement = getFileList.get(i);
	            waitForWebElementToAppear(fileTypeElement);

	            WebElement fileNameElement = initialFileList.get(i);
	            waitForWebElementToAppear(fileNameElement);

	            String filetype = fileTypeElement.getText();
	            String fileName = fileNameElement.getText();

	            if (filetype.contains("Purchase") || filetype.contains("Sale")) {
	                System.out.println("Expected file: " + fileName + ":" + filetype);
	                allFilesArePurchaseOrSale = true;
	            } else {
	                System.out.println("Unexpected file: " + fileName + ":" + filetype);
	            }
	        }

	        if (allFilesArePurchaseOrSale) {
	            System.out.println("*****Pass : Only 'Purchase' and 'Sale' files are displayed.*****");
	        } else {
	            Assert.fail("*****Fail : Other file types are also displayed.*****");
	        }
	    } else {
	        System.out.println("***No Search result found for FileTypes criteria***");
	    }
	    return new FileList(driver);
	}

	
	// Verify File status  Criteria working or not
	public FileList AdvanceSearchFileStatus() throws InterruptedException {
		ClearAndHome();
		AdvanceSearchButton();

		Filelistwait(); // explicit wait

		if (!ActiveCheckbox.isSelected()) {
			ActiveCheckbox.click();
		}
		if (!ArchivedCheckbox.isSelected()) {
			ArchivedCheckbox.click();
		}
		SearchButton.click();

		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);
		

		if (!isElementDisplayed(Noresult)) {
			List<WebElement> getFileList = ListFileStatus;
			boolean allFilesAreArchived = false;

			for (int i = 0; i < Math.min(getFileList.size(), initialFileList.size()); i++) {

				initialFileList = driver.findElements(Filelistname);

				WebElement fileStatusElement = getFileList.get(i);
				WebElement fileNameElement = initialFileList.get(i);

				String fileStatus = fileStatusElement.getText();
				String fileName = fileNameElement.getText();

				if (fileStatus.contains("Archived")) {
					System.out.println("Expected file: " + fileName + ":" + fileStatus);
					allFilesAreArchived = true;
				} else {

					System.out.println("Unexpected file: " + fileName + ":" + fileStatus);
				}
			}

			if (allFilesAreArchived) {
				System.out.println("*****Pass : Only 'Archived' files are displayed.*****");
			} else {
				allFilesAreArchived = false;
				Assert.fail("*****Fail : Other file statuses are also displayed.*****");
			}
		} else {
			System.out.println("***No Search result found for File Status Criteria***");
		}
		return new FileList(driver);
	}

	// Verify Responsible Lawyer working proper or not
	public FileList AdvanceSearchResponsibleLawyer(String LawyerName) throws InterruptedException {
		ClearAndHome();
		AdvanceSearchButton();
		Filelistwait(); // explicit wait

		Select selectLawyer = new Select(SelectLawyer);
		selectLawyer.selectByValue(LawyerName);
		SearchButton.click();

		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);

		if (!isElementDisplayed(Noresult)) {
			clickOnFirstFile();
			waitForWebElementToAppear(InsidefileFrame);
			driver.switchTo().frame(InsidefileFrame);
			waitForElementToAppear(FileLawyerName);
			String fileContentText = driver.findElement(FileLawyerName).getText();

			boolean lawyerNameMatch = fileContentText.equals(LawyerName);

			if (lawyerNameMatch) {
				System.out.println("*****Pass: Lawyer name match found in the file content.*****");
			} else {
				Assert.fail("****Fail: Lawyer name match not found in the file content.*****");
			}
			driver.switchTo().defaultContent();
		} else {
			System.out.println("*****No Search result found for Responsible Lawyer criteira*****");
		}
		return new FileList(driver);
	}

	// Verify Firm contact working proper or not
	public FileList AdvanceSearchFirmContact(String FirmContact) throws InterruptedException {
		ClearAndHome();
		AdvanceSearchButton();
		Filelistwait(); // explicit wait

		Select selectLawyer = new Select(SelectFirmContact);
		selectLawyer.selectByValue(FirmContact);

		SearchButton.click();

		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);

		if (!isElementDisplayed(Noresult)) {
			clickOnFirstFile();
			waitForWebElementToAppear(InsidefileFrame);
			driver.switchTo().frame(InsidefileFrame);
			waitForElementToAppear(FileFirmName);
			String fileContentText = driver.findElement(FileFirmName).getText();
			boolean FirmNameMatch = fileContentText.equals(FirmContact);
			if (FirmNameMatch) {
				System.out.println("*****Pass: Firm name match found in the file content.*****");
			} else {
				Assert.fail("*****Fail: Firm name match not found in the file content.*****");
			}
			driver.switchTo().defaultContent();
		} else {
			System.out.println("****No Search result found for Firm Contact criteria****");
		}
		return new FileList(driver);
	}

	// Verify ClientName working or not with different criteria
	public FileList AdvanceSearchClientname(String AdvanceSearch_Client1, String AdvanceSearch_Client2,
			String AdvanceSearch_Client3) throws InterruptedException {
		String[] clientNames = { AdvanceSearch_Client1, AdvanceSearch_Client2, AdvanceSearch_Client3 };
		for (String clientName : clientNames) {
			ClearAndHome();
			AdvanceSearchButton();
			Filelistwait(); // explicit wait

			waitForElementToAppear(ClientNameField);
			driver.findElement(ClientNameField).clear();
			driver.findElement(ClientNameField).sendKeys(clientName);
			SearchButton.click();

			List<WebElement> initialFileList = driver.findElements(Filelistname);
			waitForFileListUpdate(initialFileList);

			if (!isElementDisplayed(Noresult)) {
				List<WebElement> getFileList = ListClientName;
				boolean allClientname = false;

				// Re-find the elements after waiting
				initialFileList = driver.findElements(Filelistname);
				getFileList = ListClientName;

				for (int i = 0; i < getFileList.size(); i++) {
					WebElement clientnameElement = getFileList.get(i);
					WebElement fileNameElement = initialFileList.get(i);

					String clientname = clientnameElement.getText();
					String fileName = fileNameElement.getText();
					if (clientname.contains(clientName)) {
						System.out.println("Expected file: " + fileName + ":" + clientname);
						allClientname = true;
					} else {
						System.out.println("UnExpected file: " + fileName + ":" + clientname);
					}
				}

				if (allClientname) {
					System.out.println("*****Pass : Client name criteria working fine with different Scenario*****");
				} else {
					Assert.fail("*****Fail : Client name criteria not working fine with different Scenario*****");
				}
			} else {
				System.out.println("***No Search result found for Client name criteria***");
			}
		}
		return new FileList(driver);
	}

	// Verify Reline[include & Doesn't include] Criteria working proper or not
	@SuppressWarnings("unlikely-arg-type")
	public FileList AdvanceSearchReline(String[] AdvanceSearch_Reline) throws InterruptedException {

		for (String isCriteria : AdvanceSearch_Reline) {
			performSearchforReline(isCriteria, true);

			if (!isElementDisplayed(Noresult)) {

				clickOnFirstFile();
				waitForElementToAppear(InsideFileName);
				String fileContentText = driver.findElement(Reline_InsideFile).getText();
				boolean anyRelineMatch = false;
				for (String reline : AdvanceSearch_Reline) {
					if (fileContentText.contains(reline)) {
						anyRelineMatch = true;
						System.out.println("******Pass: Reline[include] Criteria working fine*****");
						break;
					}
				}

				if (!anyRelineMatch) {
					Assert.fail("*****Fail: Reline[include] Criteria not  working fine*****");
				}
			} else {
				System.out.println("***No Search result found for Reline[include]***");
			}

			for (String excludeCriteria : AdvanceSearch_Reline) {
				performSearchforReline(excludeCriteria, false);

				if (!isElementDisplayed(Noresult)) {

					boolean noRelinematch = true;
					clickOnFirstFile();
					waitForElementToAppear(InsideFileName);
					String fileContentText1 = driver.findElement(Reline_InsideFile).getText();

					for (String reline : AdvanceSearch_Reline) {
						if (fileContentText1.contains(reline)) {
							noRelinematch = false;

							Assert.fail("****Fail: Reline[Doesn't include] Criteria not  working fine.*****");
							break;
						}
					}

					if (noRelinematch) {
						System.out.println("******Pass: Reline[Doesn't include] Criteria working fine*****");
					}
				} else {
					System.out.println("***No Search result found for Reline[Doesn't include]***");
				}
			}
		}
		return new FileList(driver);

	}

	// Method to perform Reline search in the Advance Search page
	private void performSearchforReline(String AdvanceSearch_Reline, boolean isCriteria) {
		ClearAndHome();
		AdvanceSearchButton();
		Filelistwait(); // explicit wait
		By searchLocator = isCriteria ? Reline_IncludeField : Reline_ExcludeField;

		WebElement searchField = driver.findElement(searchLocator);

		waitForWebElementToAppear(searchField);
		searchField.clear();
		searchField.sendKeys(AdvanceSearch_Reline);
		SearchButton.click();

		List<WebElement> initialFileList = driver.findElements(Filelistname);

		waitForFileListUpdate(initialFileList);

	}

	// verify closing date After and before working proper or not
	public FileList AdvanceSearchClosingDate(String ClosingDate_After, String ClosingDate_Before)
			throws InterruptedException {

		ClearAndHome();
		AdvanceSearchButton();
		Filelistwait(); // explicit wait
		waitForElementToAppear(Closing_After);
		driver.findElement(Closing_After).sendKeys(ClosingDate_After);
		waitForElementToAppear(Closing_Before);
		driver.findElement(Closing_Before).sendKeys(ClosingDate_Before);
		SearchButton.click();

		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);

		if (!isElementDisplayed(Noresult)) {
			String inputValue = driver.findElement(By.xpath("//input[@id='fileSearchInput']")).getAttribute("value");

			Pattern pattern = Pattern.compile("from:\\((.*?)\\)\\s*to:\\((.*?)\\)");
			Matcher matcher = pattern.matcher(inputValue);

			if (matcher.find()) {
				String fromDate = matcher.group(1);
				String toDate = matcher.group(2);

				for (WebElement file : ListClosingDate) {
					String closingdate = file.getText();

					if (isDateInRange(closingdate, fromDate, toDate)) {
						System.out.println("*****Pass :Closing date Criteria are working fine*****");
					} else {
						Assert.fail("*****Fail :Closing date Criteria are  not working fine*****");
					}
				}
			}

		} else {
			System.out.println("***No Search result found for Closing Date***");
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

	// verify Property address working proper or not
	public FileList AdvanceSearchAddress(String Advancesearch_Address1, String Advancesearch_Address2)
			throws InterruptedException {

		String[] Addresses = { Advancesearch_Address1, Advancesearch_Address2 };
		for (String AddressName : Addresses) {
			ClearAndHome();
			AdvanceSearchButton();
			Filelistwait(); // explicit wait
			waitForElementToAppear(Propertyaddress);
			driver.findElement(Propertyaddress).clear();
			driver.findElement(Propertyaddress).sendKeys(AddressName);
			SearchButton.click();

			List<WebElement> initialFileList = driver.findElements(Filelistname);
			waitForFileListUpdate(initialFileList);

			if (!isElementDisplayed(Noresult)) {
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
						System.out.println("Expected file: " + fileName + ":" + Address);
					} else {
						allAddressname = false;
						System.out.println("UnExpected file: " + fileName + ":" + Address);
					}
				}

				if (allAddressname) {
					System.out.println("*****Pass :All Property addresses meet the criteria.*****");
				} else {
					Assert.fail("****Fail :Not all addresses meet the criteria.*****");
					
				}

			} else {
				System.out.println("***No Search result found For property address Criteria***");
			}
		}
		return new FileList(driver);
	}

	// verify property type working proper or not
	public FileList AdvanceSearchPropertyType(String[] Property_Type1, String[] Property_Type2)
			throws InterruptedException {
		ClearAndHome();
		for (String includeCriteria : Property_Type1) {
			performSearchforPropertytype(new String[] { includeCriteria }, true);

		}

		return new FileList(driver);
	}

	private void verifyPropertyTypeMatch(String[] Property_Type1) {

		switchToFrameAndClickMenu();
		WebElement dropdownElement = driver.findElement(FilePropertyType);
		Select dropdown = new Select(dropdownElement);
		WebElement selectedOption = dropdown.getFirstSelectedOption();
		String PropertyTypeText = selectedOption.getText();
		boolean PropertyTypeMatch = false;

		for (String property : Property_Type1) {
			if (PropertyTypeText.equals(property)) {
				PropertyTypeMatch = true;
				System.out.println(
						"****Pass: propertyType name=" + PropertyTypeText + "match found in the file content.****");
				break;
			}
		}

		if (!PropertyTypeMatch) {
			System.out.println(
					"****Fail: propertyType name=" + PropertyTypeText + "match not found in the file content.****");
		}

	}

	private void performSearchforPropertytype(String[] propertyTypes, boolean isIncludeCriteria) {
		ClearAndHome();
		AdvanceSearchButton();
		Filelistwait(); // explicit wait

		WebElement propertyTypeDropdown = driver.findElement(Propertytype);
		waitForWebElementToAppear(propertyTypeDropdown);
		Select pro = new Select(propertyTypeDropdown);
		for (String propertyType : propertyTypes) {

			pro.selectByVisibleText(propertyType);

			SearchButton.click();

			List<WebElement> initialFileList = driver.findElements(Filelistname);

			waitForFileListUpdate(initialFileList);

			if (!isElementDisplayed(Noresult)) {

				clickOnFirstFile();
				verifyPropertyTypeMatch(propertyTypes);

			} else {
				System.out.println("*****Search result not found for Property Type*****");
			}
		}
	}

	// Verify ClientName working or not with different criteria
	public FileList AdvanceSearchLawyername(String AdvanceSearch_LawyerName) throws InterruptedException {

		ClearAndHome();
		AdvanceSearchButton();
		Filelistwait(); // explicit wait
		waitForElementToAppear(LawyerName);
		driver.findElement(LawyerName).clear();
		driver.findElement(LawyerName).sendKeys(AdvanceSearch_LawyerName);
		SearchButton.click();
		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);
		if (!isElementDisplayed(Noresult)) {
			boolean LawyerNameMatch = false;
			clickOnFirstFile();
			waitForWebElementToAppear(InsidefileFrame);
			driver.switchTo().frame(InsidefileFrame);
			waitForElementToAppear(FileLawyername);
			String fileContentText = driver.findElement(FileLawyername).getText();
			LawyerNameMatch = fileContentText.equals(AdvanceSearch_LawyerName);
			if (LawyerNameMatch) {
				System.out.println(
						"****Pass: Other side Lawyername=" + fileContentText + " "+"match found in the file content.****");
			} else {
				Assert.fail("****Fail: Other side Lawyername=" + fileContentText
						+ "match  not found in the file content.***");
			}
			driver.switchTo().defaultContent();
		} else {
			System.out.println("****No Search result found Other Side file Lawyer name****");
		}
		return new FileList(driver);
	}
	// Verify Otherside Filenumber working or not 
	public FileList AdvanceSearchOthersideFilenumber(String AdvanceSearch_FileName) throws InterruptedException {

		ClearAndHome();
		AdvanceSearchButton();
		Filelistwait(); // explicit wait
		waitForElementToAppear(Filenumber);
		driver.findElement(Filenumber).clear();
		driver.findElement(Filenumber).sendKeys(AdvanceSearch_FileName);
		SearchButton.click();

		// Wait for file list update
		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);
		if (!isElementDisplayed(Noresult)) {
			boolean FileNameMatch = false;
			clickOnFirstFile();			
			waitForWebElementToAppear(InsidefileFrame);
			driver.switchTo().frame(InsidefileFrame);

			// JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			// jsExecutor.executeScript("window.scrollBy(0, 500);");
			waitForElementToAppear(OtherFileno);
			String fileContentText = driver.findElement(OtherFileno).getAttribute("value");
;
			FileNameMatch = fileContentText.equals(AdvanceSearch_FileName);
			if (FileNameMatch) {
				System.out.println(
						"****Pass: Other side Filename=" + fileContentText + "match found in the file content.****");
			} else {
				Assert.fail("****Fail: Other side Filename=" + fileContentText
						+ "match  not found in the file content.****");
			}
			driver.switchTo().defaultContent();
		} else {
			System.out.println("****No Search result found Other Side file name****");
		}
		return new FileList(driver);
	}

	private void switchToFrameAndClickMenu() {
		waitForElementToAppear(menu_property);
		driver.findElement(menu_property).click();
		driver.switchTo().frame(InsidefileFrame);
		waitForElementToAppear(FilePropertyType);
	}

	// Method to click on the first file in the list
	private void clickOnFirstFile() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		List<WebElement> initialFileList = driver.findElements(Filelistname);
		waitForFileListUpdate(initialFileList);
		if (!isElementDisplayed(Noresult)) {
			wait.until(ExpectedConditions.elementToBeClickable(initialFileList.get(0))).click();
		} else {
			System.out.println("No Search result found while try to click on First file");
		}
	}

	public void waitForFileListUpdate(List<WebElement> initialFileList) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try {
			if (!initialFileList.isEmpty()) {
				ExpectedCondition<Boolean> staleCondition = ExpectedConditions.stalenessOf(initialFileList.get(0));
				wait.until(staleCondition);
				List<WebElement> updatedFileList = wait
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Filelistname));

				int updatedSize = updatedFileList.size();
				if (updatedSize > 0) {
					//System.out.println("File list has been updated with " + updatedSize + " elements.");
				} else {
					System.out.println("No search result found.");
				}
			} else {
				System.out.println("No search result found.");
			}
		} catch (TimeoutException ex) {

			// System.out.println("File list update was not detected within the specified
			// timeout.");
		} catch (StaleElementReferenceException ex) {
			// Handle the stale element reference exception
			System.out.println("Stale element reference exception occurred.");
		}
	}

	public void ClearAndHome() {
		if (isElementDisplayed(ClearSearch)) {
			driver.findElement(ClearSearch).click();
		} else {
			driver.switchTo().defaultContent();
			driver.findElement(HomeButton).click();
		}
	}

	

	public void AdvanceSearchButton() {
		waitForElementToAppear(AdvanceSearchbutton);
		driver.findElement(AdvanceSearchbutton).click();
	}

	public void Filelistwait() {
		// Apply explicit wait for the search results to be displayed
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(Filelistname));
	}

}
