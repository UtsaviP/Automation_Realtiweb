package details_section.PageObject.BUYER_SELLER_SIDE;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import details_section.PageObject.CONTRACT.Basic;
import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;
import project.AbstractComponents.CommonFuncs;

public class Buyer_side extends AbstractComponent {
	WebDriver driver;

	CommonFuncs common;
	AzureDevOpsIntegration Azure;
	Basic Basic_tab;

	public Buyer_side(WebDriver driver) {
		super(driver);
		this.driver = driver;
		common = new CommonFuncs(driver); // Initialize CommonFuncs
		Azure = new AzureDevOpsIntegration(); // Initialize AzureDevOpsIntegration
		Basic_tab = new Basic(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//a[text()='Individual'])[1]")
	public WebElement individual;

	@FindBy(xpath = "(//a[text()='Corporation'])[1]")
	public WebElement Corporation;

	@FindBy(xpath = "(//a[text()='Estate'])[1]")
	public WebElement Estate;

	@FindBy(xpath = "(//i[@class='fa-light fa-plus'])[1]")
	public WebElement Plus_icon;

	@FindBy(xpath = "(//i[@class='fa-solid fa-id-card menuHoverItem'])[1]")
	public WebElement IDVerification_icon;

	@FindBy(xpath = "//h2[@class='title-2'][text()='Identification Verification Information']")
	public WebElement IDVerification_Title;

	@FindBy(xpath = "(//i[@class='fa-solid fa-ellipsis-vertical'])[1]")
	public WebElement Three_dots_menu;

	@FindBy(xpath = "//a[text()='Move Down']")
	public WebElement Move_Down;

	@FindBy(xpath = "//a[text()='Move Up']")
	public WebElement Move_Up;

	@FindBy(xpath = "(//a[@role='button'][text()='Remove'])[1]")
	public WebElement Remove;

	@FindBy(xpath = "//span[@class='item-name ' and text()='Basic']")
	public WebElement Basic_Tab;

	
//Test Case 12348: Verify Individual ,Estate and Corporations options working proper or not
	public void BuyerSide_options(String Individual_Name, String Estate_Name, String Corporation_Name,
			Map<String, String> clientNames) throws IOException {
		List<String> expectedNames = Arrays.asList(Individual_Name, Estate_Name, Corporation_Name);

		try {
			for (String fieldName : new String[] { "Individual_Name", "Estate_Name", "Corporation_Name" }) {
				handleFieldAction(fieldName, clientNames.get(fieldName));
			}

			verifyBuyerSideNames(expectedNames);
			Basic_Tab.click();
			verifyPurchaserField(expectedNames);
		} catch (Exception e) {
			common.handleException(e, "An exception occurred while testing Buyer Side section.", "12348");
		}
	}

	private void handleFieldAction(String fieldName, String clientName) throws IOException {
		if (clientName != null) {
			driver.switchTo().defaultContent();
			try {
				switch (fieldName) {
				case "Individual_Name":
					handleIndividualName(clientName);
					break;
				case "Estate_Name":
					handleEstateName(clientName);
					break;
				case "Corporation_Name":
					handleCorporationName(clientName);
					break;
				}
			} catch (Exception e) {
				common.handleException(e, "Failed during " + fieldName + " processing", "12348");
			}
		}
	}

	// Individual
	private void handleIndividualName(String clientName) throws InterruptedException {
		driver.switchTo().defaultContent();
		waitForWebElementToAppear(Plus_icon);
		Plus_icon.click();
		individual.click();
		common.switchToIframe(common.MiddlePortionFrame);
		Select GenderDropdown = new Select(Basic_tab.genderfield);
		GenderDropdown.selectByValue("Female");
		Basic_tab.name_on_aggrement_field.sendKeys(clientName);
		Thread.sleep(2000);
		Basic_tab.personal_info_title.click();
	}

	// Estate
	private void handleEstateName(String clientName) throws InterruptedException, IOException {
		driver.switchTo().defaultContent();
		waitForWebElementToAppear(Plus_icon);
		Plus_icon.click();
		Estate.click();
		common.switchToIframe(common.MiddlePortionFrame);
		if (Basic_tab.estateCheckbox.isSelected() && Basic_tab.name_of_Deceased_field.isDisplayed()) {
			Thread.sleep(2000);
			Basic_tab.name_of_Deceased_field.sendKeys(clientName);
			Basic_tab.personal_info_title.click();
		} else {
			common.handleException(new Exception("Failed during Estate processing"), "Failed during Estate processing",
					"12348");
		}
	}

	// Corporation
	private void handleCorporationName(String clientName) throws InterruptedException, IOException {
		driver.switchTo().defaultContent();
		waitForWebElementToAppear(Plus_icon);
		Plus_icon.click();
		Corporation.click();
		Thread.sleep(2000);
		common.switchToIframe(common.MiddlePortionFrame);
		Select GenderDropdown1 = new Select(Basic_tab.genderfield);
		boolean isCorporationVisible = GenderDropdown1.getFirstSelectedOption().getText().equals("Corporation");

		if (isCorporationVisible && !Basic_tab.BirthDatefield.isDisplayed()) {
			Basic_tab.name_on_aggrement_field.sendKeys(clientName);
			Basic_tab.personal_info_title.click();
		} else {
			common.handleException(new Exception("Failed during Corporation processing"),
					"Failed during Corporation processing", "12348");
		}
	}

	private void verifyBuyerSideNames(List<String> expectedNames) throws IOException {
		driver.switchTo().defaultContent();
		boolean isMatchFound_purchaser = true;

		for (WebElement nameElement : Basic_tab.fileNavigationBuyerSide) {
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
			Azure.updateTestCaseStatus("12348", "Automation Pass", "");
			System.out.println("PASS: All Buyer Names Found in File navigation > Buyer Side");
		} else {
			common.handleException(new Exception("Fail: Not All Buyer Names Found in File navigation > Buyer Side"),
					"Fail: Not All Buyer Names Found in File navigation > Buyer Side", "12348");
		}
	}

	private void verifyPurchaserField(List<String> expectedNames) throws IOException {
		common.switchToIframe(common.MiddlePortionFrame);
		Basic_tab.editTransferee.click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//i[@id='dialog-close']")).click();
		common.switchToIframe(common.MiddlePortionFrame);
		String textInIframe = Basic_tab.Transferee_field.getText();
		boolean isMatchFound_purchaser1 = true;

		boolean isNameMatch = false;

		for (String expectedName : expectedNames) {
			if (textInIframe.contains(expectedName)) {
				System.out.println("Expected:" + expectedNames);
				System.out.println("Actual:" + textInIframe);
				isNameMatch = true;
				break;
			}
		}

		if (!isNameMatch) {
			isMatchFound_purchaser1 = false;
		}

		if (isMatchFound_purchaser1) {
			Azure.updateTestCaseStatus("12348", "Automation Pass", "");
		} else {
			common.handleException(new Exception("Fail: Not All Buyer Names Found in Basic > Transferee field"),
					"Fail: Not All Buyer Names Found in Basic > Transferee field", "12348");
		}
	}

	// Test Case 12353 ,12354 : Verify Move Up ,Move Down and Remove options in three dots menu
	public void BuyerSideThreeDotsMenu() throws IOException {
	    Map<String, String> clientNames = new HashMap<>();

	    clientNames.put("Individual_Name", "John Doe");
	    clientNames.put("Estate_Name", "Test");
	    clientNames.put("Corporation_Name", "XYZ Corporation");

	    // Call the BuyerSide_options method 
	    BuyerSide_options("John Doe", "Test", "XYZ Corporation", clientNames);

	    driver.switchTo().defaultContent();
	    List<WebElement> fileNavigationBuyerSide = Basic_tab.fileNavigationBuyerSide;

	    // Move Down
	    fileNavigationBuyerSide.get(0).click();
	    fileNavigationBuyerSide.get(0).findElement(By.xpath("(//i[@class='fa-solid fa-ellipsis-vertical'])[1]"))
	            .click();
	    String firstBuyerName = fileNavigationBuyerSide.get(0).getText();

	    Move_Down.click();
	    String secondBuyerName = fileNavigationBuyerSide.get(1).getText();

	    try {
	        if (firstBuyerName.equals(secondBuyerName)) {
	            Azure.updateTestCaseStatus("12353", "Automation Pass", "");
	        } else {
	            common.handleException(new Exception("Fail: Move Down options not working properly"),
	                    "Fail: Move Down options not working properly", "12353");
	        }
	    } catch (Exception e) {
	    	  common.handleException(new Exception("Fail: Move Down options not working properly"),
	                    "Fail: Move Down options not working properly", "12353");
	    }

	    // Move Up
	    fileNavigationBuyerSide.get(1).click();
	    fileNavigationBuyerSide.get(1).findElement(By.xpath("(//i[@class='fa-solid fa-ellipsis-vertical'])[2]"))
	            .click();
	    String firstBuyerName1 = fileNavigationBuyerSide.get(0).getText();
	    Move_Up.click();

	    String secondBuyerName1 = fileNavigationBuyerSide.get(1).getText();

	    try {
	        if (secondBuyerName1.equals(firstBuyerName1)) {
	            Azure.updateTestCaseStatus("12353", "Automation Pass", "");
	        } else {
	            common.handleException(new Exception("Fail: Move Up options not working properly"),
	                    "Fail: Move Up options not working properly", "12353");
	        }
	    } catch (Exception e) {
	    	  common.handleException(new Exception("Fail: Move Up options not working properly"),
	                    "Fail: Move Down options not working properly", "12353");
	    }

		/*
		 * // Remove option fileNavigationBuyerSide.get(0).click();
		 * fileNavigationBuyerSide.get(0).findElement(By.
		 * xpath("(//i[@class='fa-solid fa-ellipsis-vertical'])[1]")) .click(); String
		 * firstBuyerName2 = fileNavigationBuyerSide.get(0).getText();
		 * 
		 * try { Remove.click();
		 * 
		 * Thread.sleep(2000);
		 * 
		 * 
		 * @SuppressWarnings("unlikely-arg-type") boolean deletename =
		 * !fileNavigationBuyerSide.contains(firstBuyerName2);
		 * 
		 * if (deletename) { Azure.updateTestCaseStatus("12354", "Automation Pass", "");
		 * } else { common.handleException(new
		 * Exception("Fail: Remove option in three dots menu not working properly"),
		 * "Fail: Remove option in three dots menu not working properly", "12354"); } }
		 * catch (InterruptedException e) { common.handleException(new
		 * Exception("Fail:Remove option in three dots menu not working properly"),
		 * "Fail: Remove option in three dots menu not working properly", "12354"); }
		 * 
		 * 
		 */
		
	 // Test Case 12352: If click on the ID verification icons, it redirects to the ID verification area at the bottom.

	    fileNavigationBuyerSide.get(0).click();
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Give some time for the iframe to load
	    try {
	        Thread.sleep(2000); // Adjust the sleep duration as needed
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Switch to iframe
	    common.switchToIframe(common.MiddlePortionFrame);

	    // After clicking IDVerification_icon, wait for dynamic content to load
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    IDVerification_icon.click();

	    // Wait for dynamic content to load
	   // wait.until(ExpectedConditions.visibilityOfElementLocated(By IDVerification_Title));

	    // Get the initial scroll position
	    Long initialScrollPosition = (Long) js.executeScript("return document.documentElement.scrollTop || document.body.scrollTop;");
	    System.out.println("Initial Scroll position: " + initialScrollPosition);

	    // Scroll to the ID verification area
	 //   js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth' });", IDVerification_area);

	    // Wait for a short duration to ensure the smooth scroll has completed
	    try {
	        Thread.sleep(5000); // Adjust the sleep duration as needed
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Get the scroll position after scrolling
	    Long scrollPositionAfter = (Long) js.executeScript("return document.documentElement.scrollTop || document.body.scrollTop;");
	    System.out.println("Scroll position after: " + scrollPositionAfter);

	    // Check if the scroll position has changed
	    if (!scrollPositionAfter.equals(initialScrollPosition)) {
	        System.out.println("Scrolling down successful.");
	        Azure.updateTestCaseStatus("12352", "Automation Pass", "");
	    } else {
	        common.handleException(new Exception("Fail: Scrolling down after clicking on ID verification failed."),
	                "Fail: Scrolling down after clicking on ID verification failed.", "12352");
	    }
	}
}

