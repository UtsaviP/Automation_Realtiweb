package details_section.PageObject.CONTRACT;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import home.PageObject.Advance_Search_Filter;
import home.PageObject.FileList;
import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;

public class Basic extends AbstractComponent {
	WebDriver driver;

	public Basic(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Details Section >CONTRACT >Basic Tab PageFactory
	@FindBy(xpath = "//tr[@class='SelectRow']//td[@class='SelectRow']")
	List<WebElement> LawyerList;

	@FindBy(xpath = "//tbody[@id ='lookUpTBody']/tr[@class='SelectRow']/td[@class='SelectRow']")
	List<WebElement> list;

	@FindBy(xpath = "//tbody/tr/td[@class='last-col']/a[@subnodestatus=0]")
	List<WebElement> fireInsuranceList;

	@FindBy(xpath = "//tbody/tr/td[@title='All Mortgage Brokers']//tbody[@id='oneSingleList']/tr/td/a[contains(@subnodestatus,0)][1]")
	List<WebElement> mortgageBrokerListEdit;

	@FindBy(xpath = "//tr[@class='BrowserRow']/td[@subnodestatus=0 and @class='BrowserRow']")
	List<WebElement> mortgageBrokerList;

	@FindBy(xpath = "//div[@class='list-group-item']//div/h4[contains(text(), 'Buyer Side')]/following::button[@role='tab'][preceding::h4[contains(text(), 'Buyer Side')]][following::h4[contains(text(), 'Seller Side')]]//span[@class='item-name']")
	List<WebElement> fileNavigationBuyerSide;

	@FindBy(xpath = "//div[@class='list-group-item']//div/h4[contains(text(), 'Seller Side')]/following::button[@role='tab'][preceding::h4[contains(text(), 'Seller Side')]][following::span[contains(text(), 'Lawyer')]]//span[@class='item-name']")
	List<WebElement> fileNavigationSellerSide;

	@FindBy(xpath = "//tr[@title='Lawyer responsible for this file']//i[@class='fa tool-icon ldd-list']")
	public WebElement ResponsibleLawyer;

	@FindBy(xpath = "(//input[@id='currentListFilter'])[1]")
	public WebElement LawyerFilter;

	@FindBy(xpath = "//iframe[@class='clsCompatHost']")
	public WebElement MiddlePortionFrame;

	@FindBy(xpath = "//iframe[@id='dialog-body']")
	public WebElement PopUpFrame;

	@FindBy(xpath = "//body/dialog[2]/iframe[1]")
	public WebElement EditPopUpFrame;

	@FindBy(xpath = "//a[contains(text(),'+ Add')]")
	public WebElement addbutton;

	@FindBy(xpath = "//span[@attrname='LawyerName' and @subnodestatus=0]")
	public WebElement ResponsibleLawyerField;

	@FindBy(xpath = "//button[@condition='document.all.AlwaysUpdateList==null'][text()='OK']")
	public WebElement okButton;

	@FindBy(xpath = "//tr[@xmlbase='Staff']//i[@title='Select Contact']")
	public WebElement selectFirmContactButton;

	@FindBy(xpath = "//tr[@xmlbase='Staff']//i[@title='Edit Contact']")
	public WebElement editFirmContactButton;

	@FindBy(xpath = "//tr[@xmlbase='Staff']//i[@title='Clear Information']")
	public WebElement clearFirmContactButton;

	@FindBy(xpath = "//tr[@xmlbase='Staff']//span[@class='input-inactive input-long']/span[1]")
	public WebElement displayFirmContactName;

	@FindBy(xpath = "//tr[@xmlbase='Conveyancer']//i[@title='Select Contact']")
	public WebElement selectConveyancerButton;

	@FindBy(xpath = "//body[1]/div[2]/div[1]/div[2]/div[1]//table[1]//tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[2]/td[1]")
	public WebElement ConveyancerNameList;

	@FindBy(xpath = "//tr[@title='Enter the name of the Insurance Agent.']//i[@title='Select Contact']")
	public WebElement selectFireInsuranceButton;

	@FindBy(xpath = "(//tr[@xmlbase='InsuranceAgent'])[1]//span[@attrname='Company']")
	public WebElement displayFireInsuranceName;

	@FindBy(xpath = "(//span[contains(text(),'Fire Ins.')])/preceding::span[@class='item-name'][1]")
	public WebElement fireInsFileNavigation;

	@FindBy(xpath = "(//span[contains(text(),'Mortgage Broker')])/preceding::span[@class='item-name'][1]")
	public WebElement MortgageBroFileNavigation;

	@FindBy(xpath = "(//tr[@xmlbase='MortgageBroker'])[1]//span[@attrname='Company']")
	public WebElement displayMortgageBrokerName;

	@FindBy(xpath = "//tr[@xmlbase='Conveyancer']//span[@class='input-inactive input-long']/span[1]")
	public WebElement displayConveyancerName;

	@FindBy(xpath = "//tr[@xmlbase='MortgageBroker']//i[@title='Select Contact']")
	public WebElement selectMortgageBrokerButton;

	@FindBy(xpath = "//input[@attrname='MortgageBrokerFee']")
	public WebElement mortgageBrokerField;

	@FindBy(xpath = "(//i[@title='Edit Contact'])[3]")
	public WebElement editTransferee;

	@FindBy(xpath = "(//select[@attrname='Gender' and @subnodestatus='0'])[last()]")
	public WebElement genderfield;

	@FindBy(xpath = "(//input[@attrname='IsEstate' and @subnodestatus='0'])[last()]")
	public WebElement estateCheckbox;

	@FindBy(xpath = "(//span[contains(text(),'Edit')])[5]")
	public WebElement editTransferor;

	@FindBy(xpath = "(//input[@attrname='Name' and @subnodestatus='0'])[last()]")
	public WebElement name_on_aggrement_field;

	@FindBy(xpath = "(//h2[@class='title-2' and @subnodestatus='0' and text()='Personal Information'])[last()]")
	public WebElement personal_info_title;

	@FindBy(xpath = "//td[@class='contact-list']//span[@class='btn btn-primary' and text()='Add New' and not(contains(@style, 'display: none;'))]")
	public WebElement add_New_Button;

	@FindBy(xpath = "(//input[@attrname='DeceasedName' and @subnodestatus='0'])[last()]")
	public WebElement name_of_Deceased_field;

	@FindBy(xpath = "(//td[contains(text(),'Birth Date') and @subnodestatus='0'])[last()]")
	public WebElement BirthDatefield;

	By LawyerListBy = By.xpath("//tr[@class='SelectRow']//td[@class='SelectRow']");

	By listby = By.xpath("//tbody[@id ='lookUpTBody']/tr[@class='SelectRow']/td[@class='SelectRow']");

	By Staff_Member_Name = By
			.xpath("//div[@id='ReactConveyancer']//tr[@title='Enter the name of the Conveyancer.']//td//input[1]");

	By Staff_Member_Name1 = By
			.xpath("//div[@id='divDetailEdit']//table[1]//tr[@title='Enter the name of the Conveyancer.']//td/input");

	AzureDevOpsIntegration Azure = new AzureDevOpsIntegration();

	//***Verify File Details->Responsible Lawyer field Working Properly or not***
	public void ResponsibleLawyer(String Responsible_Lawyer) throws InterruptedException, IOException {
		switchToIframe(MiddlePortionFrame);
		ResponsibleLawyer.click();

		driver.switchTo().defaultContent();
		WebElement prod = GetLawyerName(Responsible_Lawyer);
		prod.click();
		switchToIframe(MiddlePortionFrame);

		String getText = ResponsibleLawyerField.getText();

		boolean LawyerText = getText.equals(Responsible_Lawyer); // Define the 'LawyerText' variable

		

		if (LawyerText) {
			Azure.updateTestCaseStatus("12080", "Automation Pass");
			Assert.assertTrue(LawyerText, "PASS: Responsible Lawyer field Working Properly");
			System.out.println("PASS: Responsible Lawyer field Working Properly");
		} else {
			Azure.updateTestCaseStatus("12080", "Automation Fail");
			Assert.fail("****FAIL: Responsible Lawyer field is not as expected****");
			System.out.println("****FAIL: Responsible Lawyer field is not as expected****");
		}
	}

	public List<WebElement> getLawyerList() throws InterruptedException {
		if (list.isEmpty()) {
			WebElement innerFrameElement = driver.findElement(By.id("dialog-body"));
			driver.switchTo().frame(innerFrameElement);
		}
		return list;
	}

	public WebElement GetLawyerName(String Responsible_Lawyer) throws InterruptedException {
		WebElement prod = getLawyerList().stream().filter(Lawyer -> Lawyer.getText().equals(Responsible_Lawyer))
				.findFirst().orElse(null);
		return prod;
	}

	// Verify Add,Edit and Clear Functionality in File Details->Firm Contact Field
	public void FirmContactAddEditClear() throws IOException {
	    boolean addPass = false;
	    boolean editPass = false;
	    boolean clearPass = false;

	    try {
	        addFirmContact();
	        addPass = true;

	        editFirmContact();
	        editPass = true;

	        clearFirmContact();
	        clearPass = true;

	        // If all three operations pass, update the test case status to "Automation Pass"
	        if (addPass && editPass && clearPass) {
	            Azure.updateTestCaseStatus("12081", "Automation Pass");
	            System.out.println("*****PASS: All Firm Contact operations passed successfully*****");
	        } else {
	            // If any operation fails, update the test case status to "Automation Fail"
	            Azure.updateTestCaseStatus("12081", "Automation Fail");
	            System.out.println("*****FAIL: One or more Firm Contact operations failed*****");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions and update the test case status to "Automation Fail"
	        Azure.updateTestCaseStatus("12081", "Automation Fail");
	        Assert.fail("*****FAIL: Something went wrong in FirmContactAddEditClear functionality*****");
	        System.out.println("*****FAIL: Something went wrong in FirmContactAddEditClear functionality*****");
	        e.printStackTrace();
	    }
	}


	// Add
	private void addFirmContact() {
		try {
			switchToIframe(MiddlePortionFrame);
			selectFirmContactButton.click();
			switchToIframe(PopUpFrame);
			addbutton.click();

			String staffMemberName = "FirmContact_" + new Random().nextInt(10000);
			WebElement staffNameInput = driver
					.findElement(By.xpath("//tr[@title='Enter the name of the Staff member.']//td//input"));
			staffNameInput.sendKeys(staffMemberName);
			okButton.click();

			switchToIframe(MiddlePortionFrame);

			String displayedStaffName = displayFirmContactName.getAttribute("value");

			Assert.assertEquals(displayedStaffName.trim(), staffMemberName.trim());
			System.out.println("PASS: Firm Contact field 'Add' functionality working Properly");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Edit
	private void editFirmContact() {
		try {
			editFirmContactButton.click();
			switchToIframe(EditPopUpFrame);
			String staffMemberName = "EditFirmContact_" + new Random().nextInt(10000);
			WebElement staffNameInput = driver.findElement(
					By.xpath("//tr[@title='Enter the name of the Staff member.']//td//input[@subnodestatus=0]"));
			staffNameInput.clear();
			staffNameInput.sendKeys(staffMemberName);
			okButton.click();

			switchToIframe(MiddlePortionFrame);
			String displayedStaffName = displayFirmContactName.getAttribute("value");

			Assert.assertEquals(displayedStaffName.trim(), staffMemberName.trim());
			System.out.println("PASS :Firm Contact field 'Edit' functionality working Properly");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Clear
	private void clearFirmContact() {
		try {
			clearFirmContactButton.click();
			if (!displayFirmContactName.isDisplayed()) {
				System.out.println("PASS :Firm Contact field 'Clear' functionality working Properly");
			} else {
				Assert.fail("****FAIL :Firm Contact field 'Clear' functionality not working Properly****");
				String staffName = displayFirmContactName.getText();
				System.out.printf(staffName,
						"****FAIL :Firm Contact field 'Clear' functionality not working Properly****");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify File Details->Conveyancer Field working properly or not

	public void Conveyancer() throws InterruptedException {
		try {
			switchToIframe(MiddlePortionFrame);
			selectConveyancerButton.click();
			switchToIframe(PopUpFrame);
			if (ConveyancerNameList.isDisplayed()) {
				String listFirstName = ConveyancerNameList.getText();
				ConveyancerNameList.click();
				switchToIframe(MiddlePortionFrame);
				String getConveyancerName = displayConveyancerName.getText();

				try {
					Assert.assertEquals(getConveyancerName.trim(), listFirstName.trim());
					 Azure.updateTestCaseStatus("12082", "Automation Pass");
					System.out.println("PASS: Conveyancer field functionality working Properly");

					 
				} catch (AssertionError e) {
					Azure.updateTestCaseStatus("12082", "Automation Fail");
					System.out.println("*****FAIL: Conveyancer field functionality working Improperly*****");
					System.out.println("Assertion Error Details: " + e.getMessage());
				}
			} else {
				System.out.println("Any Conveyancer name is not available in Pop Up ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// verify File Configuration->File_Configuration functionality working proper or
	// not

	public void File_Configuration() throws InterruptedException, IOException {
		switchToIframe(MiddlePortionFrame);

		// select Yes in Acting for both purchaser and vendor in this transaction
		// element
		driver.findElement(By.xpath("//tbody/tr[@xcondition=\"@PSM='P' or @PSM='S'\"]/td/span[@type='radio']/input[2]"))
				.click();

		// select No in New Home Purchased from a Builder element
		WebElement elementToClick = driver.findElement(By.xpath(
				"//span[@inputhelp='You must indicate if this is a new home purchased from/sold by a builder.']//input[@value='1']"));
		elementToClick.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Vacant Land']")));
		WebElement anotherElement = driver.findElement(By.xpath("//span[normalize-space()='Vacant Land']"));

		boolean isDisplayed = anotherElement.isDisplayed();

		if (isDisplayed) {
			Azure.updateTestCaseStatus("12083", "Automation Pass");
			System.out.println("PASS:In File_Configuration-> Vacant Land element is displayed.");
		} else {
			Azure.updateTestCaseStatus("12083", "Automation Fail");
			Assert.assertTrue(false, "****FAIL: In File_Configuration-> Vacant Land element is not displayed.*****");
		}
	}

	// verify Transferee(s) functionality working proper or not
	public void Transferee(String Single_Transferee, String Estate_Transferee, String Corporation_Transferee,
			Map<String, String> clientNames) throws InterruptedException, IOException {
		switchToIframe(MiddlePortionFrame);
		editTransferee.click();

		Thread.sleep(2000);
		switchToIframe(PopUpFrame);

		for (String fieldName : new String[] { "Single_Transferee", "Estate_Transferee", "Corporation_Transferee" }) {
			String clientName = clientNames.get(fieldName);

			// For Single_Transferee, select the appropriate value and fill the input field
			if (clientName != null) {
				Select GenderDropdown = new Select(genderfield);
				if (fieldName.equalsIgnoreCase("Single_Transferee")) {
					GenderDropdown.selectByValue("Female");
					name_on_aggrement_field.sendKeys(clientName);
					Thread.sleep(2000);
					personal_info_title.click();

					add_New_Button.click();
				} else if (fieldName.equalsIgnoreCase("Estate_Transferee")) {

					// For Estate_Transferee, interact with the Estate checkbox and fill the input
					// field
					estateCheckbox.click();
					Thread.sleep(2000);
					name_of_Deceased_field.sendKeys(clientName);
					personal_info_title.click();
					add_New_Button.click();

				} else if (fieldName.equalsIgnoreCase("Corporation_Transferee")) {

					// For Corporation_Transferee, select the appropriate value and fill the input
					// field
					Thread.sleep(2000);

					Select GenderDropdown1 = new Select(genderfield);
					GenderDropdown1.selectByValue("Corporation");
					name_on_aggrement_field.sendKeys(clientName);

					// Check if the birthday field is displayed
					boolean isBirthdayFieldDisplayed = BirthDatefield.isDisplayed();
					if (isBirthdayFieldDisplayed) {
						Assert.assertTrue(false,
								"****Fail: BirthDate field shows even Corporation option selected*****");
					} else {
						System.out.println(
								"****PASS: BirthDate field is not showing if Corporation option selected*****");
					}
				}
			}
		}
		// Return to the main page context if needed
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//i[@id='dialog-close']")).click();

		// Verify all Transferee(s) name on File navigation > Buyer side
		List<String> expectedNames = Arrays.asList(Single_Transferee, Estate_Transferee, Corporation_Transferee);

		boolean isMatchFound_purchaser = true;

		for (WebElement nameElement : fileNavigationBuyerSide) {
			String displayedName = nameElement.getText();

			boolean isNameMatch = false;

			for (String expectedName : expectedNames) {
				if (displayedName.contains(expectedName)) {
					System.out.println("Expected:" + expectedName);
					System.out.println("Actual:" + displayedName);
					isNameMatch = true;
					break;
				}
			}

			if (!isNameMatch) {
				isMatchFound_purchaser = false;
				break;
			}
		}

		if (isMatchFound_purchaser) {
			Azure.updateTestCaseStatus("12087", "Automation Pass");
			System.out.println("PASS: All Buyer Names Found in File navigation >Buyer Side");
		} else {
			Azure.updateTestCaseStatus("12087", "Automation Fail");
			System.out.println("****Fail: Not All Buyer Names Found in File navigation >Buyer Side*****");
		}

		// Assert based on the overall match flag
		Assert.assertTrue(isMatchFound_purchaser,
				"****Fail: Not All Buyer Names Found in File navigation >Buyer Side*****");
	}

	// verify Transferor(s) functionality working proper or not
	public void Transferor(String Single_Transferor, String Estate_Transferor, String Corporation_Transferor,
			Map<String, String> clientNames) throws InterruptedException, IOException {
		switchToIframe(MiddlePortionFrame);
		editTransferor.click();
		Thread.sleep(2000);
		switchToIframe(PopUpFrame);

		for (String fieldName : new String[] { "Single_Transferor", "Estate_Transferor", "Corporation_Transferor" }) {
			String clientName = clientNames.get(fieldName);

			if (clientName != null) {
				Select GenderDropdown = new Select(genderfield);
				if (fieldName.equalsIgnoreCase("Single_Transferor")) {
					GenderDropdown.selectByValue("Female");
					name_on_aggrement_field.sendKeys(clientName);
					Thread.sleep(2000);
					personal_info_title.click();

					add_New_Button.click();
				} else if (fieldName.equalsIgnoreCase("Estate_Transferor")) {

					// For Estate_Transferee, interact with the Estate checkbox and fill the input
					// field
					estateCheckbox.click();

					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

					try {

						wait.until(ExpectedConditions.alertIsPresent());

						Alert alert = driver.switchTo().alert();
						alert.accept();

						System.out.println("Alert was present and accepted.");
					} catch (NoAlertPresentException e) {
						System.out.println("No alert was present. Continuing with the next option.");
					}
					Thread.sleep(2000);
					name_of_Deceased_field.sendKeys(clientName);
					personal_info_title.click();
					add_New_Button.click();

				} else if (fieldName.equalsIgnoreCase("Corporation_Transferor")) {

					// For Corporation_Transferee, select the appropriate value and fill the input
					// field
					Thread.sleep(2000);

					Select GenderDropdown1 = new Select(genderfield);
					GenderDropdown1.selectByValue("Corporation");
					name_on_aggrement_field.sendKeys(clientName);

					// Check if the birthday field is displayed
					boolean isBirthdayFieldDisplayed = BirthDatefield.isDisplayed();
					if (isBirthdayFieldDisplayed) {
						Assert.assertTrue(false,
								"****Fail: BirthDate field shows even Corporation option selected*****");
					} else {
						System.out.println(
								"****PASS: BirthDate field is not showing if Corporation option selected*****");
					}
				}
			}
		}
		// Return to the main page context if needed
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//i[@id='dialog-close']")).click();

		// Verify all Transferor(s) name on File navigation > Seller side
		List<String> expectedNames1 = Arrays.asList(Single_Transferor, Estate_Transferor, Corporation_Transferor);
		boolean isMatchFound_Seller = true;

		for (WebElement nameElement : fileNavigationSellerSide) {
			String displayedName = nameElement.getText();

			boolean isNameMatch1 = false;

			for (String expectedName : expectedNames1) {
				if (displayedName.contains(expectedName)) {
					System.out.println("Expected:" + expectedName);
					System.out.println("Actual:" + displayedName);
					isNameMatch1 = true;
					break;
				}
			}

			if (!isNameMatch1) {
				isMatchFound_Seller = false;
				break;
			}
		}

		if (isMatchFound_Seller) {
			Azure.updateTestCaseStatus("12089", "Automation Pass");
			System.out.println("PASS: All Seller Names Found in File navigation >Seller Side");
		} else {
			Azure.updateTestCaseStatus("12089", "Automation Fail");
			System.out.println("****Fail: Not All Seller Names Found in File navigation >Seller Side*****");
		}

		// Assert based on the overall match flag
		Assert.assertTrue(isMatchFound_Seller,
				"****Fail: Not All Seller Names Found in File navigation >Seller Side*****");
	}

	
	// Verify Fire Insurance functionality working properly or not
	public void FireInsurance() throws InterruptedException, IOException {
	    boolean isTestPassed = false;

	    try {

	        switchToIframe(MiddlePortionFrame);
	        selectFireInsuranceButton.click();
	        Thread.sleep(2000);
	        switchToIframe(PopUpFrame);

	        if (fireInsuranceList.size() > 0) {
	            fireInsuranceList.get(0).click();

	            String textCompanyName = driver.findElement(By.xpath(
	                    "//tr[@title='Enter the name of the Insurance Company.']//td/input[@attrname='Company' and @subnodestatus=0]"))
	                    .getAttribute("value");

	            okButton.click();
	            switchToIframe(MiddlePortionFrame);
	            String textFireInsuranceName = displayFireInsuranceName.getText();

	            if (textFireInsuranceName.equals(textCompanyName)) {
	                isTestPassed = true;
	                System.out.println("PASS: Selected Company name is shown in Fire insurance field");

	                driver.switchTo().defaultContent();
	                String textFireInsFileNavigation = fireInsFileNavigation.getText();

	                if (textFireInsFileNavigation.contains(textFireInsuranceName)) {
	                    System.out.println(
	                            "PASS: In file navigation, Fire ins. name is the same as Fire ins. field value.");
	                    Azure.updateTestCaseStatus("12093", "Automation Pass");
	                } else {
	                    isTestPassed = false;
	                    System.out.println(
	                            "*****FAIL: In file navigation, Fire ins. name is not the same as Fire ins. field value.*****");
	                    Azure.updateTestCaseStatus("12093", "Automation Fail");
	                }
	            } else {
	                System.out.println("*****FAIL: Selected Company name is not shown in Fire insurance field*****");
	                Azure.updateTestCaseStatus("12093", "Automation Fail");
	            }
	        } else {
	            System.out.println("***Any Name is not available in Fire Insurance List****");
	        }
	        
	        Assert.assertTrue(isTestPassed,
	                "*****FAIL: Selected Company name is not shown in Fire insurance field*****");
	    } catch (Exception e) {
	        e.printStackTrace();
	        Azure.updateTestCaseStatus("12093", "Automation Fail");
	    }
	}

	// Verify Mortgage Broker functionality working properly or not
	public void MortgageBroker() throws InterruptedException, IOException {
	    boolean isTestPassed = false;

	    try {
	        switchToIframe(MiddlePortionFrame);
	        selectMortgageBrokerButton.click();
	        Thread.sleep(2000);
	        switchToIframe(PopUpFrame);

	        if (mortgageBrokerListEdit.size() > 0) {
	            mortgageBrokerListEdit.get(0).click();

	            String textCompanyName = driver.findElement(By.xpath(
	                    "//tr[@title='Enter the name of the Broker Company.']//td/input[@attrname='Company' and @subnodestatus=0]"))
	                    .getAttribute("value");

	            okButton.click();
	            switchToIframe(MiddlePortionFrame);
	            String textMortgageBrokerName = displayMortgageBrokerName.getText();

	            if (textMortgageBrokerName.equals(textCompanyName)) {
	                isTestPassed = true;
	                System.out.println("PASS: Selected Company name is shown in Mortgage Broker field");

	                driver.switchTo().defaultContent();
	                String textMortgageBroFileNavigation = MortgageBroFileNavigation.getText();

	                if (textMortgageBroFileNavigation.contains(textMortgageBrokerName)) {
	                    System.out.println(
	                            "PASS: In file navigation, Mortgage Broker name is the same as Mortgage Broker field value.");
	                    Azure.updateTestCaseStatus("12094", "Automation Pass");
	                } else {
	                    isTestPassed = false;
	                    System.out.println(
	                            "*****FAIL: In file navigation, Mortgage Broker name is not the same as Mortgage Broker field value.*****");
	                    Azure.updateTestCaseStatus("12094", "Automation Fail");
	                }
	            } else {
	                System.out.println("*****FAIL: Selected Company name is not shown in Mortgage Broker field*****");
	                Azure.updateTestCaseStatus("12094", "Automation Fail");
	            }
	        } else {
	            System.out.println("***Any Name is not available in Mortgage Broker List****");
	        }

	        Assert.assertTrue(isTestPassed,
	                "*****FAIL: Selected Company name is not shown in Mortgage Broker field*****");
	    } catch (Exception e) {
	        e.printStackTrace();
	        Azure.updateTestCaseStatus("12094", "Automation Fail");
	    }
	}
	// Verify Mortgage Broker -> Fee$ field and Pay from Trust functionality working properly or not
	public void mortgageBrokerFee(String mortgageBrokerFee) throws InterruptedException, IOException {
	    try {
	        // Convert the input mortgageBrokerFee to a comma-formatted amount
	        String formattedMortgageBrokerFee = formatAmountWithCommas(mortgageBrokerFee);
	        switchToIframe(MiddlePortionFrame);
	        selectMortgageBrokerButton.click();
	        switchToIframe(PopUpFrame);
	        Thread.sleep(2000);

	        if (mortgageBrokerList.size() > 0) {
	            mortgageBrokerList.get(0).click();
	            switchToIframe(MiddlePortionFrame);
	            mortgageBrokerField.clear();
	            mortgageBrokerField.sendKeys(formattedMortgageBrokerFee);

	            driver.findElement(By.xpath("//a[text()='Pay from Trust']")).click();	            	         
	            Azure.updateTestCaseStatus("12095", "Automation Pass");
	            System.out.println("PASS: Mortgage Broker Fee showing Trust Account Screen");
	        } else {
	        	
	            System.out.println("***Any Name is not available in Mortgage Broker List****");	     
	            Azure.updateTestCaseStatus("12095", "Automation Fail");
	        }
	        
	    } catch (Exception e) {
	        Assert.fail("*****Fail: Mortgage Broker Fee not showing Trust Account Screen*****");
	        System.out.println("*****Fail: Mortgage Broker Fee not showing Trust Account Screen*****");
	        // If the test fails
	        Azure.updateTestCaseStatus("12095", "Automation Fail");
	    }
	}

	// Format a numeric amount with commas for thousands and two decimal places.
	public static String formatAmountWithCommas(String amount) {
		try {

			double numericAmount = Double.parseDouble(amount);
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
			String formattedAmount = decimalFormat.format(numericAmount);

			return formattedAmount;
		} catch (NumberFormatException e) {
			System.err.println("Invalid numeric input: " + amount);
			return amount; // Return the original string
		}
	}

	// A utility method to switch to an iframe by its XPath
	public void switchToIframe(WebElement iframeElement) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(iframeElement);
	}

	// Click on First Purchase File in File List
	public void PurchaseFileClick(String PurchaseFilename) throws InterruptedException {
		{
			FileList searchobject = new FileList(driver);
			searchobject.freeSearch.sendKeys(PurchaseFilename);
			Advance_Search_Filter searchobject1 = new Advance_Search_Filter(driver);
			List<WebElement> initialFileList = driver.findElements(searchobject1.Filelistname);
			searchobject1.waitForFileListUpdate(initialFileList);
			if (!isElementDisplayed(searchobject1.Noresult)) {
				searchobject1.clickOnFirstFile();

			}
		}

	}
}
