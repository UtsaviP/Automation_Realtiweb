package notes_and_pin.PageObject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import details_section.PageObject.CONTRACT.Basic;
import details_section.PageObject.CONTRACT.Contract;
import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;
import project.AbstractComponents.CommonFuncs;

public class default_Pin extends AbstractComponent {
	WebDriver driver;

	CommonFuncs common;
	AzureDevOpsIntegration Azure;
	Basic Basic_tab;
	Contract Contract_tab;

	public default_Pin(WebDriver driver) {
		super(driver);
		this.driver = driver;
		common = new CommonFuncs(driver); // Initialize CommonFuncs
		Azure = new AzureDevOpsIntegration(); // Initialize AzureDevOpsIntegration
		Basic_tab = new Basic(driver);
		Contract_tab = new Contract(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//i[@class='fa-duotone fa-notes'])[1]")
	public WebElement notes_navigate;

	@FindBy(xpath = "(//i[contains(@class,'fa-regular fa-thumbtack')])[2]")
	public WebElement activityCenter_navigate;

	@FindBy(xpath = "//i[@class='fa-duotone fa-calendar-star']")
	public WebElement keydates_navigate;

	@FindBy(xpath = "(//i[@class='fa-solid fa-thumbtack']")
	public WebElement notes_pin_checked;

	@FindBy(xpath = "(//i[@class='fa-regular fa-thumbtack")
	public WebElement notes_pin_unchecked;

	@FindBy(xpath = "//i[contains(@class,'fa-duotone fa-check-double')]")
	public WebElement activityCenter_pin;

	@FindBy(xpath = "(//i[@class='fa-solid fa-thumbtack']")
	public WebElement keydates_pin;

	@FindBy(xpath = "(//i[@class='fa-solid fa-thumbtack']")
	public WebElement Home_icon;

	@FindBy(xpath = "//h2[text()='Notes']")
	public WebElement Notes_title;

	// i[@class="fa-duotone fa-house fa-action"]

	// Test Case 13815: Verify pin option is available or not in Note, Activity
	// Center, and Key Dates
	public void verifyPinOptionAvailability() throws IOException {
		checkPinOptionAvailability("Activity Center", activityCenter_navigate, activityCenter_pin);
		checkPinOptionAvailability("Key Dates", keydates_navigate, keydates_pin);
		checkPinOptionAvailability("Notes", notes_navigate, notes_pin_unchecked);
	}

	private void checkPinOptionAvailability(String sectionName, WebElement navigateElement, WebElement pinElement)
			throws IOException {
		navigateElement.click();
		if (pinElement.isDisplayed()) {
			Azure.updateTestCaseStatus("13815", "Automation Pass", "");
			System.out.println(sectionName + " pin option available: pass");
		} else {
			System.out.println(sectionName + " pin option available: fail");
			handleTestFailure("13815", sectionName + " pin option is not available");
		}
	}

	private void handleTestFailure(String testCaseNumber, String errorMessage) throws IOException {
		Azure.updateTestCaseStatus(testCaseNumber, "Automation Fail", errorMessage);
		Assert.fail("****Fail: " + errorMessage + "*****");
	}

//Test Case 13818: verify Pin feature working proper or not in Notes with logout functionality
	public void pin_in_Notes() {
		notes_navigate.click();
		notes_pin_unchecked.click();

		Home_icon.click();

	}

	public void after_pin_notes() throws IOException {

		if (notes_pin_checked.isDisplayed() && Notes_title.isDisplayed()) {
			Azure.updateTestCaseStatus("13815", "Automation Pass", "");
			System.out.println("Notes functionality is working fine: pass");
		}
		else
		{
			System.out.println("Notes functionality is not  working fine: fail");
			handleTestFailure("13815", "Notes functionality is not  working fine");
		}
	}

}