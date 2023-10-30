package details_section.PageObject.CONTRACT;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;
import project.AbstractComponents.CommonFuncs;

public class Contract extends AbstractComponent {
	WebDriver driver;

	CommonFuncs common;
	AzureDevOpsIntegration Azure;

	public Contract(WebDriver driver) {
		super(driver);
		this.driver = driver;
		common = new CommonFuncs(driver); // Initialize CommonFuncs
		Azure = new AzureDevOpsIntegration(); // Initialize AzureDevOpsIntegration
		PageFactory.initElements(driver, this);
	}

	// Details Section >CONTRACT >Contract Tab PageFactory

	@FindBy(xpath = "//input[@attrname='ResideAtProperty' and @subnodestatus='0'][1]")
	public WebElement RadioButton_Yes;

	@FindBy(xpath = "//span[text()='To Reside at Property on Closing']")
	public WebElement RadioButton_YesText;

	@FindBy(xpath = "//input[@attrname='ResideAtProperty' and @subnodestatus='0'][2]")
	public WebElement RadioButton_No;

	@FindBy(xpath = "(//td[contains(text(),'Address for Service')])[5]")
	public WebElement RadioButton_NoText;

	@FindBy(xpath = "//span[text()='Contract']")
	public WebElement Contract_Tab;

	@FindBy(xpath = "//td[text()='Lawyer' and @subnodestatus='0'][1]/following-sibling::td/i[@title='Select']")
	public WebElement Select_Lawyer;

	@FindBy(xpath = "//input[@id='ldd_last_nm']")
	public WebElement Last_name;

	@FindBy(xpath = "//button[contains(text(),'Search') and @subnodestatus='0']")
	public WebElement SearchButton;

	@FindBy(xpath = "//button[@id='btnAddLawyer']")
	public WebElement BtnAddLawyer;

	@FindBy(xpath = "//td[text()='Lawyer' and @subnodestatus='0'][1]/following-sibling::td/span[@subnodestatus='0']")
	public WebElement Lawyerfield;

	@FindBy(xpath = "//tr/td/a[@title='View Lawyer Information']")
	List<WebElement> LawyerList;

	@FindBy(xpath = "//td/h2[text()='Contract']/following::tr/td//input[@attrname='ClosingDate' and @subnodestatus=0]")
	public WebElement ClosingDate;

	@FindBy(xpath = "//span[@title='Is the offer conditional']//input[@value=1]")
	public WebElement offercondition_Yes;

	@FindBy(xpath = "//span[@title='Is the offer conditional']//input[@value=0]")
	public WebElement offercondition_No;

	@FindBy(xpath = "//td[text()='Condition Cleared']")
	public WebElement Condition_Cleared;

	@FindBy(xpath = "(//select[@attrname='heldBy' and @subnodestatus=0 ])[last()]")
	public WebElement Deposits;

	@FindBy(xpath = "//span[text()='Total $']")
	public WebElement Totalcaption;

	@FindBy(xpath = "(//tbody[@name='DepositLoop']//tr//td//input[@attrname='Amount' and @subnodestatus=0])[last()]")
	public WebElement DepositAmountfield;

	@FindBy(xpath = "//span[@attrname='DepositTotal']")
	public WebElement DepositTotal;

	@FindBy(xpath = "//td[text()='Consideration $']/following::td//input[@datatype='money' and @attrname='ConsiderationAmount']")
	public WebElement Consideration;

	@FindBy(xpath = "//td[text()='Consideration for Ereg $']/following-sibling::td/input[@datatype='money' and @attrname='LTTItem2f']")
	public WebElement ConsiderationforEreg;

	@FindBy(xpath = "//td[@class='content']//input[@attrname='PurchasePrice' and @subnodestatus='0']")
	public WebElement ContractPrice;

	@FindBy(xpath = "//td[text()='Amount $']//following-sibling::td//input[@attrname='GSTAmount']")
	public WebElement HSTAmountfield;

	@FindBy(xpath = "//tr[@title='Enter the name of the Real Estate Agent.']//i[@title='Select']")
	public WebElement Select_Broker;

	@FindBy(xpath = "//input[@id='ldd_LastName']")
	public WebElement Broker_Lastname;

	@FindBy(xpath = "//button[text()='Search']")
	public WebElement Broker_Search;

	@FindBy(xpath = "//tr/td[@id='RealtorFoundResult']//tr//td[@id='RealtorName']")
	List<WebElement> Broker_List;

	@FindBy(xpath = "//tr[@id='ResultsRow']//td[@align='left']")
	public WebElement Result_List;

	@FindBy(xpath = "//span[@class='input-inactive input-long' and @attrname='CompanyAgentName']")
	public WebElement Broker_Field;
	
	@FindBy(xpath = "//input[@datatype='float']")
	public WebElement Contract_PriceField;
	
	@FindBy(xpath = "//span[@attrname='CommissionPaidToRealEstateAgent']")
	public WebElement Commission_Amount$;


	@FindBy(xpath = "//span[@attrname='PaidToRealEstateAgent']")
	public WebElement Total_Commission$;


	@FindBy(xpath = "//span[@attrname='AmountOwingToBroker']")
	public WebElement Amount_Owing_To_Broker$;
	
	@FindBy(xpath = "//tr[@xmlbase='Trust']//td[@class='content']//input[@type='checkbox']")
	public WebElement HST_checkbox;
	
	@FindBy(xpath = "//input[@attrname='GSTAmountOnCommission']")
	public WebElement HST$_field;
	
	
	
	// ***Purchasers(s): Verify To Reside at Property on Closing field based on Yes/No changes.***
	public void ToResideAtPropertyOnClosing_Field() throws InterruptedException, IOException {
	    try {
	        Thread.sleep(2000);
	        Contract_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);

	        // Check "Yes" option
	        RadioButton_Yes.click();
	        boolean RadioYes = RadioButton_YesText.isDisplayed();

	        // Check "No" option
	        RadioButton_No.click();
	        boolean RadioNo = RadioButton_NoText.isDisplayed();

	        if (RadioYes && RadioNo) {
	            Azure.updateTestCaseStatus("12141", "Automation Pass");
	            System.out.println("PASS: Both radio buttons of the ToResideAtPropertyOnClosing Field are working properly");
	        } else {
	            Azure.updateTestCaseStatus("12141", "Automation Fail");
	            Assert.fail("****FAIL: Both radio buttons of the ToResideAtPropertyOnClosing Field are not working properly****");
	            System.out.println("****FAIL: Both radio buttons of the ToResideAtPropertyOnClosing Field are not working properly****");
	        }
	    } catch (Exception e) {	       
	        Azure.updateTestCaseStatus("12141", "Automation Fail");
	        System.out.println("****ERROR: An error occurred during the test execution. Details: " + e.getMessage());
	        Assert.fail("Fail",e);
	    }
	}


	// ***Other Side Legal Representation: Verify Lawyer field with Law Society of Ontario functionality. ***
	public void OtherSideLawyer(String OtherSide_Lawyer) throws InterruptedException, IOException {
		Thread.sleep(2000);
		Contract_Tab.click();
		common.switchToIframe(common.MiddlePortionFrame);
		Select_Lawyer.click();
		common.switchToIframe(common.PopUpFrame);
		Last_name.sendKeys(OtherSide_Lawyer);
		SearchButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='No lawyer found']")));
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//i[@id='dialog-close']")).click();
			System.out.println("***No Lawyer Found***");
			Azure.updateTestCaseStatus("12144", "Automation Fail");
			Assert.fail("****FAIL: No Lawyer Found for that name.****");
		} catch (TimeoutException e) {

			waitForElementToAppear(By.xpath("//table[@class='table fix']//tr//td[1][text()='Full Name']"));

			List<WebElement> lawyerOptions = driver
					.findElements(By.xpath("//tr/td/a[@title='View Lawyer Information']"));

			if (lawyerOptions.size() > 0) {
				String text = lawyerOptions.get(0).getText();
				lawyerOptions.get(0).click();
				BtnAddLawyer.click();
				common.switchToIframe(common.MiddlePortionFrame);

				String text1 = Lawyerfield.getText();

				if (text.equals(text1)) {
					System.out.println(
							"PASS: Lawyer name is the same as selected in the Law Society of Ontario and in the Lawyer field.");
					Azure.updateTestCaseStatus("12144", "Automation Pass");
				} else {
					System.out.println("****FAIL: Lawyer name does not match.****");
					Azure.updateTestCaseStatus("12144", "Automation Fail");
					Assert.fail("****FAIL: Lawyer name does not match.****");
				}
			}
		}
	}

	// ***Verify Contract: Closing Date should be same in Statement of Adjustments>Adjustment date***
	public void Closing_Date(String Closing_Date) throws InterruptedException, IOException {
	    try {
	        Thread.sleep(2000);
	        Contract_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);
	        ClosingDate.sendKeys(Closing_Date);
	        String Text = ClosingDate.getText();
	        driver.switchTo().defaultContent();
	        driver.findElement(By.xpath("//span[text()='Statement of Adjustments']")).click();
	        common.switchToIframe(common.MiddlePortionFrame);
	        String Text1 = driver.findElement(By.xpath("(//input[@title='Ensure same as Closing Date'])[2]")).getText();
	        if (Text.equals(Text1)) {
	            System.out.println("PASS: Added Closing Date and in Statement of Adjustments>Adjustment date both are same.");
	            Azure.updateTestCaseStatus("12145", "Automation Pass");
	        } else {
	            System.out.println("****FAIL: Added Closing Date and in Statement of Adjustments>Adjustment date does not match****");
	            Azure.updateTestCaseStatus("12145", "Automation Fail");
	            Assert.fail("****FAIL: Added Closing Date and in Statement of Adjustments>Adjustment date does not match****");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur
	        Azure.updateTestCaseStatus("12145", "Automation Fail");
	        System.out.println("****ERROR: An error occurred during the test execution. Details: " + e.getMessage());
	       Assert.fail("Fail",e);
	    }
	}

	// ***Verify Contract: Offer Conditional field based on yes/No condition***
	public void Offer_ConditionalField() throws InterruptedException, IOException {
	    try {
	        Thread.sleep(2000);
	        Contract_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);
	        offercondition_Yes.click();

	        // Check if the condition is cleared when "Yes" is selected
	        if (Condition_Cleared.isDisplayed()) {
	            System.out.println("PASS:condition Cleared field visible when 'Yes' is selected");
	        }

	        offercondition_No.click();

	        // Check if the condition is not cleared when "No" is selected
	        if (!Condition_Cleared.isDisplayed()) {
	            System.out.println("PASS:condition Cleared field disable when 'No' is selected");
	        }

	        if (Condition_Cleared.isDisplayed() || !Condition_Cleared.isDisplayed()) {
	            System.out.println("PASS:Offer Conditional field working proper.");
	            Azure.updateTestCaseStatus("12146", "Automation Pass");
	        } else {
	            System.out.println("****FAIL:Offer Conditional field not working proper.****");
	            Assert.fail("****FAIL:Offer Conditional field not working proper.****");
	            Azure.updateTestCaseStatus("12146", "Automation Fail");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur
	        Azure.updateTestCaseStatus("12146", "Automation Fail");
	        System.out.println("****ERROR: An error occurred during the test execution. Details: " + e.getMessage());
	        Assert.fail("Fail",e);
	    }
	}

	// ***Verify Deposits: Verify Deposit functionality working properly or not***
	public void Deposits(String Broker_Deposit, String Lawyer_Deposit, String Vendor_Deposit)
	        throws InterruptedException, IOException {
	    try {
	        Thread.sleep(2000);
	        Contract_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);

	        Select select = new Select(Deposits);
	        select.selectByValue("Broker");
	        DepositAmountfield.sendKeys(Broker_Deposit);
	        Totalcaption.click();

	        select.selectByVisibleText("Lawyer");
	        DepositAmountfield.sendKeys(Lawyer_Deposit);
	        Totalcaption.click();

	        select.selectByValue("Vendor");
	        DepositAmountfield.sendKeys(Vendor_Deposit);
	        Totalcaption.click();

	        String VisibleDeposit = DepositTotal.getText();
	        double JsonTotalDeposit = Double.parseDouble(Broker_Deposit) + Double.parseDouble(Lawyer_Deposit)
	                + Double.parseDouble(Vendor_Deposit);
	        String Total = CommonFuncs.formatAmountWithCommas(String.valueOf(JsonTotalDeposit));

	        if (Total.equals(VisibleDeposit)) {
	            System.out.println("PASS: Deposit functionality working properly" + "Total Deposit=" + VisibleDeposit);
	            Azure.updateTestCaseStatus("12147", "Automation Pass");
	        } else {
	            System.out.println("****FAIL: Deposit functionality is not working as expected****");
	            Azure.updateTestCaseStatus("12147", "Automation Fail");
	            Assert.fail("****FAIL: Deposit functionality is not working as expected****");
	        }
	    } catch (Exception e) {	       
	        Azure.updateTestCaseStatus("12147", "Automation Fail");
	        System.out.println("****ERROR: An error occurred during the test execution. Details: " + e.getMessage());
	        Assert.fail("****FAIL: Deposit functionality is not working as expected****",e);
	    }
	}

	// ***Verify Deposits: Verify Deposit functionality working properly or not***
	public void Consideration(String Contract_Price) throws InterruptedException, IOException {
	    try {
	        Thread.sleep(2000);
	        Contract_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);

	        ContractPrice.clear();
	        Thread.sleep(2000);
	        ContractPrice.click();
	        ContractPrice.sendKeys(Contract_Price);
	        Thread.sleep(2000);
	        Totalcaption.click();

	        @SuppressWarnings("static-access")
	        String formattedContractPrice = common.formatAmountWithCommas(String.valueOf(Contract_Price));
	        String ConsiderationText = Consideration.getAttribute("value");
	        String ConsiderationforEregText = ConsiderationforEreg.getAttribute("value");

	        if (formattedContractPrice.equals(ConsiderationforEregText)
	                && ConsiderationText.equals(ConsiderationforEregText)) {
	            System.out.println(
	                    "PASS: Consideration$ functionality working properly" + "ConsiderationValue=" + ConsiderationText);
	            Azure.updateTestCaseStatus("12148", "Automation Pass");
	        } else {
	            System.out.println("****FAIL: Consideration$ functionality not working as expected****"
	                    + "ConsiderationValue=" + ConsiderationText);
	            Azure.updateTestCaseStatus("12148", "Automation Fail");
	            Assert.fail("****FAIL: Consideration$ functionality not working as expected****");
	        }
	    } catch (Exception e) {	        
	        Azure.updateTestCaseStatus("12148", "Automation Fail");
	        System.out.println("****ERROR: An error occurred during the test execution. Details: " + e.getMessage());
	        Assert.fail("****FAIL: Consideration$ functionality not working as expected****",e);
	    }
	}

	// ***Verify HST: Verify HST functionality working properly or not***
	@SuppressWarnings("static-access")
	public void HST(String Contract_Price) throws InterruptedException, IOException {
	    try {
	        boolean hstApplicablePass = false;
	        boolean includeInPricePass = false;

	        Thread.sleep(2000);
	        Contract_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);
	        ContractPrice.clear();
	        Thread.sleep(2000);
	        ContractPrice.click();
	        ContractPrice.sendKeys(Contract_Price);
	        Thread.sleep(2000);
	        Totalcaption.click();

	        // Check if HST (GST) is applicable, select the appropriate option
	        driver.findElement(By.xpath("//input[@attrname='IsGSTApplicable'][2]")).click();

	        double JsonTotalAmount = Double.parseDouble(Contract_Price) * 1.13;
	        double MinusAmount = JsonTotalAmount - Double.parseDouble(Contract_Price);
	        String formattedMinusAmount = common.formatAmountWithCommas(String.valueOf(MinusAmount));
	        String hstAmountfield = HSTAmountfield.getAttribute("value");

	        if (hstAmountfield.equals(String.valueOf(formattedMinusAmount))) {
	            System.out.println("PASS :In HST section, HST Applicable functionality working proper");
	            hstApplicablePass = true;
	        } else {
	            System.out.println("****FAIL: In HST section, HST Applicable functionality not working as expected****");
	        }
	        // Check the "Included in Price" checkbox
	        driver.findElement(By.xpath("//input[@attrname='IsGSTinPrice']")).click();

	        // Calculate the included-in-price amount and ConsiderationText
	        double reverseHST = Double.parseDouble(Contract_Price) / 1.13;
	        String formattedreverseHST = common.formatAmountWithCommas(String.valueOf(reverseHST));

	        String considerationText = Consideration.getAttribute("value").replace(",", ""); // Remove commas
	        double considerationValue = Double.parseDouble(considerationText);

	        String formatedconsiderationValue = common.formatAmountWithCommas(String.valueOf(considerationValue));

	        // Calculate the amount (Contract Price $ - ConsiderationText)
	        double hstAmount = Double.parseDouble(Contract_Price) - considerationValue;

	        // Format the amount with commas if needed
	        String formattedHSTAmount = common.formatAmountWithCommas(String.valueOf(hstAmount));

	        String HSTAmountvalue = HSTAmountfield.getAttribute("value");

	        if (formattedreverseHST.equals(String.valueOf(formatedconsiderationValue))
	                && formattedHSTAmount.equals(HSTAmountvalue)) {
	            System.out.println("PASS: In HST section, Include In Price Checkbox functionality working proper");
	            includeInPricePass = true;
	        } else {
	            System.out.println("****FAIL: In HST section, Include In Price Checkbox functionality not working proper****");
	        }

	        // Check if both functionalities passed
	        if (hstApplicablePass && includeInPricePass) {
	            System.out.println("PASS: HST Section working as Expected");
	            Azure.updateTestCaseStatus("12149", "Automation Pass");
	        } else {
	            System.out.println("****FAIL: HST Section not working as Expected****");
	            Azure.updateTestCaseStatus("12149", "Automation Fail");
	            Assert.fail("****FAIL: HST Section not working as Expected****");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur
	        Azure.updateTestCaseStatus("12149", "Automation Fail");
	        System.out.println("****ERROR: An error occurred during the test execution. Details: " + e.getMessage());
	        Assert.fail("****FAIL: HST Section not working as Expected****",e);
	    }
	}


	// ***Real Estate Commissions: Verify Broker field with Search REALTORS® across Canada ***
	public void Broker(String Broker_LastName) throws InterruptedException, IOException {
	    try {
	        Thread.sleep(2000);
	        Contract_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);
	        Select_Broker.click();
	        common.switchToIframe(common.PopUpFrame);
	        Broker_Lastname.sendKeys(Broker_LastName);
	        Broker_Search.click();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        try {
	            wait.until(ExpectedConditions.alertIsPresent());
	            Alert alert = driver.switchTo().alert();
	            alert.accept();
	            driver.switchTo().defaultContent();
	            driver.findElement(By.xpath("//i[@id='dialog-close']")).click();
	            System.out.println("***No Matching Broker name Found***");
	            Azure.updateTestCaseStatus("12150", "Automation Fail");
	            Assert.fail("****FAIL: No Matching Broker name Found****");
	        } catch (TimeoutException e) {
	            waitForWebElementToAppear(Result_List);

	            List<WebElement> BrokerOptions = Broker_List;

	            if (BrokerOptions.size() > 0) {
	                String text = BrokerOptions.get(0).getText();
	                if (text.toLowerCase().contains(Broker_LastName.toLowerCase())) {
	                    BrokerOptions.get(0).click();
	                    driver.findElement(By.xpath("//button[text()='OK' and @subnodestatus='0']")).click();
	                    common.switchToIframe(common.MiddlePortionFrame);

	                    String text1 = Broker_Field.getText();

	                    if (text1.contains(text)) {
	                        System.out.println("PASS: Broker name is the same as selected in the Search REALTORS® across Canada "
	                                + "Broker Name=" + text1);
	                        Azure.updateTestCaseStatus("12150", "Automation Pass");
	                    } else {
	                        System.out.println("****FAIL: Broker name does not match.****");
	                        Azure.updateTestCaseStatus("12150", "Automation Fail");
	                        Assert.fail("****FAIL: Broker name does not match.****");
	                    }
	                } else {
	                    System.out.println("****FAIL: Broker name does not match.****");
	                    Azure.updateTestCaseStatus("12150", "Automation Fail");
	                    Assert.fail("****FAIL: Broker name does not match.****");
	                }
	            }
	        }
	    } catch (Exception ex) {
	        Azure.updateTestCaseStatus("12150", "Automation Fail");
	        System.out.println("****ERROR: An error occurred during the test execution. Details: " + ex.getMessage());
	        Assert.fail("Error",ex);
	    }
	}

	// ***Real Estate Commissions: Verify Broker % of Contract Price functionality
	@SuppressWarnings({ "static-access", "unused" })
	public void Broker_ContractPrice(String Contract_Price, String Broker_ContractPrice) throws InterruptedException, IOException {
	    try {
	        boolean withouthstApplicablePass = false;
	        boolean withhstApplicablePass = false;

	        Thread.sleep(2000);
	        Contract_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);
	        ContractPrice.clear();
	        Thread.sleep(2000);
	        ContractPrice.click();
	        ContractPrice.sendKeys(Contract_Price);

	        Thread.sleep(2000);
	        Contract_PriceField.click();
	        Contract_PriceField.clear();
	        Contract_PriceField.sendKeys(Broker_ContractPrice);
	        // Unchecked HST checkbox
	        HST_checkbox.click();
	        // Convert Contract_Price and Broker_ContractPrice to double for calculations
	        double contractPrice = Double.parseDouble(Contract_Price);
	        double brokerPercentage = Double.parseDouble(Broker_ContractPrice);

	        // Calculate the commission amount
	        double totalCommissionAmount = (brokerPercentage / 100) * contractPrice;
	        String formattedCommissionAmount = common.formatAmountWithCommas(String.valueOf(totalCommissionAmount));
	        String Commission_Amount = Commission_Amount$.getAttribute("value");
	        String Total_Commission = Total_Commission$.getAttribute("value");
	        String Amount_Owing_To_Broker = Amount_Owing_To_Broker$.getAttribute("value");

	        if (Commission_Amount.equals(formattedCommissionAmount) && Total_Commission.equals(formattedCommissionAmount) && Amount_Owing_To_Broker.equals(formattedCommissionAmount)) {
	            System.out.println("PASS: Without checked HST checkbox, % of Contract Price functionality working properly");
	            withouthstApplicablePass = true;
	        } else {
	            System.out.println("****FAIL: Without checked HST checkbox, % of Contract Price functionality not working properly****");
	            Azure.updateTestCaseStatus("12151", "Automation Fail");
	            Assert.fail();
	        }

	        HST_checkbox.click();

	        // Remove commas from the string before parsing it into a double
	        String sanitizedTotalCommission = Total_Commission.replace(",", "");

	        // Now you can parse the sanitized string into a double
	        double totalCommissionDouble = Double.parseDouble(sanitizedTotalCommission);

	        // Calculate the commission amount with HST
	        double commissionAmountWithHST = totalCommissionDouble * 1.13;
	        String formattedCommissionAmountWithHST = common.formatAmountWithCommas(String.valueOf(commissionAmountWithHST));
	        String Total_Commission1 = Total_Commission$.getAttribute("value");

	        // Parse HST value from the web element and handle any comma separators
	        String hstValue = HST$_field.getAttribute("value").replace(",", "");
	        double hstValueDouble = Double.parseDouble(hstValue);
	        String formattedHstValueDouble = common.formatAmountWithCommas(String.valueOf(hstValueDouble));

	        // Calculate the expected HST value
	        double hstCalculation = commissionAmountWithHST - totalCommissionDouble;
	        String formattedHstCalculation = common.formatAmountWithCommas(String.valueOf(hstCalculation));

	        if (formattedHstValueDouble.equals(formattedHstCalculation) && formattedCommissionAmountWithHST.equals(Total_Commission1)) {
	            System.out.println("PASS: With checked HST checkbox, % of Contract Price functionality working properly");
	            withhstApplicablePass = true;
	            Azure.updateTestCaseStatus("12151", "Automation Pass");
	        } else {
	            System.out.println("****FAIL: With checked HST checkbox, % of Contract Price functionality not working properly****");
	            Azure.updateTestCaseStatus("12151", "Automation Fail");
	            Assert.fail();
	        }
	    } catch (Exception ex) {
	        Azure.updateTestCaseStatus("12151", "Automation Fail");
	        System.out.println("****ERROR: An error occurred during the test execution. Details: " + ex.getMessage());
	        Assert.fail("Error",ex);
	    }
	}


}
