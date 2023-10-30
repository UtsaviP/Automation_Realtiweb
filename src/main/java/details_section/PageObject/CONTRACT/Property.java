package details_section.PageObject.CONTRACT;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class Property extends AbstractComponent {
	WebDriver driver;

	CommonFuncs common;
	AzureDevOpsIntegration Azure;

	public Property(WebDriver driver) {
		super(driver);
		this.driver = driver;
		common = new CommonFuncs(driver); // Initialize CommonFuncs
		Azure = new AzureDevOpsIntegration(); // Initialize AzureDevOpsIntegration
		PageFactory.initElements(driver, this);
	}

	// Details Section >CONTRACT >Property Tab PageFactory

	@FindBy(xpath = "//div/i[@class='indicator indicator-warn ']/following-sibling::span[text()='Property']")
	public WebElement Property_Tab;

	@FindBy(xpath = "(//span[@attrname='PropertyAddress'])[2]")
	public WebElement PropertyAddress_field;

	@FindBy(xpath = "//td//input[@attrname='StreetNumber' and @subnodestatus='0']")
	public WebElement StreetNumber;

	@FindBy(xpath = "//td//input[@attrname='StreetName' and @subnodestatus='0']")
	public WebElement StreetName;

	@FindBy(xpath = "//td//span[@subnodestatus='0' and @inputhelp='Enter the Town, City,  etc. where property is located' ]")
	public WebElement City_field;

	@FindBy(xpath = "//tr[@xmlbase='../.']//i[@title='Select']")
	public WebElement City_Select;

	@FindBy(xpath = "(//tr[@title='Click to select this item'])/td[@subnodestatus='0' and @attrname='Name']")
	List<WebElement> City_List;

	@FindBy(xpath = "//tr[@title='Enter the municipal address of the property']//input[@title='Type in capitals with a space']")
	public WebElement PostalCode_Field;

	@FindBy(xpath = "//button[text()='Verify']")
	public WebElement PostalCode_VerifyButton;

	@FindBy(xpath = "(//tr[contains(@onmouseover,'this.style.backgroundColor=\"lightblue\"')])/td[5]")
	List<WebElement> PostalCode_AddressList;

	@FindBy(xpath = "//td[@id='addressFinalRetrieved']//div[3]/span[1]/u")
	public WebElement Select_PostalCode;

	@FindBy(xpath = "//i[@class='fa ldd-check tool-icon']")
	public WebElement PostalCodeVerified;

	@FindBy(xpath = "//td/select[@attrname='PropertyType']")
	public WebElement PropertyType;

	@FindBy(xpath = "(//h2[@class='title-2'][text()='Condominium'])[1]")
	public WebElement CondominiumTitle;

	@FindBy(xpath = "(//h2[contains(text(),'Condominium')])[2]")
	public WebElement CondominiumTitle_freehold;

	@FindBy(xpath = "//tr//td[@class='caption'][text()='Unit Number'and @subnodestatus='0']")
	public WebElement UnitNumber_field;
	
	@FindBy(xpath = "//tr[@xmlbase='Surveyor']//i[@title='Select']")
	public WebElement Surveyor_Select;

	@FindBy(xpath = "(//tr[@title='Click to select this item'])/td[@attrname='indexattributevalue' and @subnodestatus=0]")
	List<WebElement> Surveyor_List;

	@FindBy(xpath = "//span[@inputhelp='You must indicate if there is a survey.']//input[@value=1]")
	public WebElement Surveyor_Yes;

	@FindBy(xpath = "//span[@inputhelp='You must indicate if there is a survey.']//input[@value=0]")
	public WebElement Surveyor_No;

	@FindBy(xpath = "//span[@attrname='PropertyAddress' and @subnodestatus='0']")
	public WebElement BasicTab_Address;
	
	@FindBy(xpath = "//tr[@xmlbase='Surveyor']//input[@class='input input-long']")
	public WebElement Surveyor_field;
	
	By PostalCode_ActivityMessage = By.xpath("//b[contains(text(),'Postal code cannot be verified, please review and')]");
	
	
	// Declare CityElement1 as an instance variable in your class
	private String CityElement1;

	// ***Property Address: verify property address in different fields and also
	// verify in Basic tab***
	public void Property_Address(String Street_Number, String Street_Name, String City)
			throws InterruptedException, IOException {
		CityElement1 = null; // Assign a value to the instance variable

		Thread.sleep(2000);
		Property_Tab.click();
		common.switchToIframe(common.MiddlePortionFrame);
		StreetNumber.sendKeys(Street_Number);
		StreetName.click();
		StreetName.clear();
		StreetName.sendKeys(Street_Name);
		City_select(City);
		common.switchToIframe(common.MiddlePortionFrame);
		String PropertyAddress = PropertyAddress_field.getText();
		String expectedPropertyAddress = Street_Number + " " + Street_Name + ", " + CityElement1 + ", " + "Ontario";
		driver.switchTo().defaultContent();
		common.Basic_Tab.click();
		common.switchToIframe(common.MiddlePortionFrame);
		String getAddress = BasicTab_Address.getText();
		if (PropertyAddress.contains(expectedPropertyAddress) && PropertyAddress.equals(getAddress)) {
			System.out.println("PASS: Property Address matches");
			Azure.updateTestCaseStatus("12225", "Automation Pass");
		} else {
			System.out.println("****FAIL: Property Address does not match**** ");
			Azure.updateTestCaseStatus("12225", "Automation Fail");
			Assert.fail();
		}
	}
   //select City or Town
	public void City_select(String City) {
		City_Select.click();
		common.switchToIframe(common.PopUpFrame);

		List<WebElement> CityList = City_List;
		if (CityList.size() > 0) {
			@SuppressWarnings("unused")
			boolean cityNameFound = false;

			for (WebElement cityElement : CityList) {
				if (cityElement.getText().equalsIgnoreCase(City)) {
					cityNameFound = true;
					CityElement1 = cityElement.getText(); // Update the instance variable
					cityElement.click();
					break;
				}
			}
		}
	}

	// ***Property Address: Verify Postal code functionality ***
	@SuppressWarnings("unused")
	public void Postal_Code(String Postal_Code) throws InterruptedException, IOException {
	    try {
	        Thread.sleep(2000);
	        Property_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);
	        StreetNumber.sendKeys("715");
	        StreetName.click();
	        StreetName.clear();
	        StreetName.sendKeys("Don Mills");
	        City_select("Toronto");
	        common.switchToIframe(common.MiddlePortionFrame);

	        PostalCode_Field.sendKeys(Postal_Code);

	        String getCode = null; // Declare getCode outside the if-else blocks
	        String getCode1 = null;

	        driver.switchTo().defaultContent();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the wait time as needed
	        wait.until(ExpectedConditions.presenceOfElementLocated(PostalCode_ActivityMessage));
	        common.switchToIframe(common.MiddlePortionFrame);

	        if (PostalCode_VerifyButton.isDisplayed()) {
	            PostalCode_VerifyButton.click();
	            List<WebElement> list = PostalCode_AddressList;

	            // Iterate through the list to find "Addresses" in any element's text
	            boolean hasAddresses = false;
	            for (WebElement element : list) {
	                if (element.getText().contains("Addresses")) {
	                    hasAddresses = true;
	                    
	                    element.click();
	                    list.get(0).click();
	                    getCode = Select_PostalCode.getText();
	                    Select_PostalCode.click();
	                    break;
	                }
	            }

	            if (!hasAddresses) {
	                getCode1 = list.get(0).getText();
	                Select_PostalCode.click();
	            }

	            boolean getTextMatched = false;
	            String getText1 = PostalCode_Field.getAttribute("value");

	            if ((getText1.equals(getCode) || getText1.equals(getCode1)) && PostalCodeVerified.isDisplayed()) {
	                System.out.println("PASS: Postal code working as Expected");
	                Azure.updateTestCaseStatus("12227", "Automation Pass");
	                getTextMatched = true;
	            } else {
	                System.out.println("****FAIL: Postal code not working as Expected**** ");
	                Azure.updateTestCaseStatus("12227", "Automation Fail");
	                Assert.fail();
	            }
	        } else {
	            if (PostalCodeVerified.isDisplayed()) {
	                System.out.println("PASS: Postal code working as Expected");
	                Azure.updateTestCaseStatus("12227", "Automation Pass");
	            } else {
	                System.out.println("****FAIL: Postal code not working as Expected**** ");
	                Azure.updateTestCaseStatus("12227", "Automation Fail");
	                Assert.fail();
	            }
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println("****FAIL: An exception occurred while verifying Postal code****");
	        Azure.updateTestCaseStatus("12227", "Automation Fail");
	        Assert.fail("****FAIL: An exception occurred while verifying Postal code****",ex);
	    }
	}

	
	
	// ***Property Description: Verify Property Type and t/w Common Elements checkbox  functionality ***		
	public void verifyPropertyType(String propertyType) throws InterruptedException, IOException {
	  
	    Select select = new Select(PropertyType);
	    select.selectByValue(propertyType);
	    
	    if ((propertyType.equals("Condo") && UnitNumber_field.isDisplayed() && CondominiumTitle.isDisplayed()) ||
	        (propertyType.equals("Freehold (Fee Simple)") && !UnitNumber_field.isDisplayed() && !CondominiumTitle.isDisplayed())) {
	        System.out.println("PASS: Property Type [Freehold & Condo] options working proper");
	        Azure.updateTestCaseStatus("12228", "Automation Pass");
	    } else {
	        System.out.println("****FAIL:Property Type [Freehold & Condo] options not  working as Expected****");	        
	        Azure.updateTestCaseStatus("12228", "Automation Fail");
	        Assert.fail();
	    }
	}

	public void Property_Type() throws InterruptedException, IOException {
		  Thread.sleep(2000);
		    Property_Tab.click();
		    common.switchToIframe(common.MiddlePortionFrame);
	    verifyPropertyType("Condo");
	    verifyPropertyType("Freehold (Fee Simple)");
	    driver.findElement(By.xpath("(//input[@attrname='twCommonElements'])[1]")).click();
	    if (CondominiumTitle_freehold.isDisplayed()) {
	        System.out.println("PASS:In FreeHold Property Type ,t/w Common Elements checkbox working as Expected");
	        Azure.updateTestCaseStatus("12229", "Automation Pass");
	    } else {
	        System.out.println("****FAIL:In FreeHold Property Type ,t/w Common Elements checkbox working as Expected**** ");	        
	        Azure.updateTestCaseStatus("12229", "Automation Fail");
	        Assert.fail();
	    }
	}
	
	
	//****Survey: Verify Survey functionality using Yes/No option working properly or not****
	public void Survey() throws IOException {
	    try {
	        Thread.sleep(2000);
	        Property_Tab.click();
	        common.switchToIframe(common.MiddlePortionFrame);
	        performSurveyAction(true);
	        performSurveyAction(false);
	    } catch (InterruptedException | IOException e) {
	        handleException(e);
	    }
	}

	private void performSurveyAction(boolean conditionToCheck) throws IOException {
	    try {
	        if (conditionToCheck) {
	            Surveyor_Yes.click();
	            Surveyor_Select.click();
	            common.switchToIframe(common.PopUpFrame);
	            List<WebElement> Surveyor_Listing = Surveyor_List;
	            if (Surveyor_Listing.size() > 0) {
	                String text = Surveyor_Listing.get(0).getText();
	                Surveyor_Listing.get(0).click();
	                common.switchToIframe(common.MiddlePortionFrame);
	                String text1 = Surveyor_field.getAttribute("value");
	                if (text.equals(text1)) {
	                    System.out.println("PASS: In Survey, YES option working properly");
	                    Azure.updateTestCaseStatus("12230", "Automation Pass");
	                } else {
	                    System.out.println("FAIL: In Survey, YES option not working as expected");
	                    Assert.fail("FAIL: In Survey, YES option not working as expected");
	                    Azure.updateTestCaseStatus("12230", "Automation Fail");
	                }
	            } else {
	                System.out.println("FAIL: No Survey names are available in Pop-up");
	                driver.switchTo().defaultContent();
	                driver.findElement(By.xpath("//i[@id='dialog-close']")).click();
	                Azure.updateTestCaseStatus("12230", "Automation Fail");
	                Assert.fail("FAIL: No Survey names are available in Pop-up");
	            }
	        } else {
	            Surveyor_No.click();
	            if (!Surveyor_field.isDisplayed()) {
	                System.out.println("PASS: In Survey, No option working properly");
	                Azure.updateTestCaseStatus("12230", "Automation Pass");
	            } else {
	                System.out.println("FAIL: In Survey, No option not working as expected");
	                Azure.updateTestCaseStatus("12230", "Automation Fail");
	                Assert.fail("FAIL: In Survey, No option not working as expected");
	            }
	        }
	    } catch (Exception e) {
	        handleException(e);
	    }
	}

	private void handleException(Exception e) throws IOException {
	    System.out.println("An exception occurred: " + e.getMessage());
	    Azure.updateTestCaseStatus("12230", "Automation Fail");
	    Assert.fail("Error",e);
	}

}


		    
		    
		
		    
  
		
	
	

