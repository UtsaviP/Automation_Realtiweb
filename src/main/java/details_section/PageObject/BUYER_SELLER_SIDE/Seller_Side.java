package details_section.PageObject.BUYER_SELLER_SIDE;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import details_section.PageObject.CONTRACT.Basic;
import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;
import project.AbstractComponents.CommonFuncs;

public class Seller_Side extends AbstractComponent {
	WebDriver driver;

	CommonFuncs common;
	AzureDevOpsIntegration Azure;
	Basic Basic_tab;

	public Seller_Side(WebDriver driver) {
		super(driver);
		this.driver = driver;
		common = new CommonFuncs(driver); // Initialize CommonFuncs
		Azure = new AzureDevOpsIntegration(); // Initialize AzureDevOpsIntegration
		Basic_tab = new Basic(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//h4[text()='Seller Side']//following::div/a[text()='Individual'])")
	public WebElement individual;

	@FindBy(xpath = "(//h4[text()='Seller Side']//following::div/a[text()='Corporation'])")
	public WebElement Corporation;

	@FindBy(xpath = "(//h4[text()='Seller Side']//following::div/a[text()='Estate'])")
	public WebElement Estate;

	@FindBy(xpath = "(//i[@class='fa-light fa-plus'])[2]")
	public WebElement Plus_icon;

	@FindBy(xpath = "(//i[@class='fa-solid fa-id-card menuHoverItem'])[1]")
	public WebElement IDVerification_icon;

	@FindBy(xpath = "//h2[@class='title-2'][text()='Identification Verification Information']")
	public WebElement IDVerification_Title;

	@FindBy(xpath = "(//h4[text()='Seller Side']//following::div[@class='dropend']//i[@class='fa-solid fa-ellipsis-vertical'])[1]")
	public WebElement Three_dots_menu;

	@FindBy(xpath = "(//h4[text()='Seller Side']//following::div[@class='show dropend']//a[text()='Move Down'])")
	public WebElement Move_Down;

	@FindBy(xpath = "(//h4[text()='Seller Side']//following::div[@class='show dropend']//a[text()='Move Up'])")
	public WebElement Move_Up;

	@FindBy(xpath = "(//h4[text()='Seller Side']//following::div[@class='show dropend']//a[text()='Remove'])")
	public WebElement Remove;

	@FindBy(xpath = "//span[@class='item-name ' and text()='Basic']")
	public WebElement Basic_Tab;

	
	//Test Case 12393: Verify Individual ,Estate and Corporations options working proper or not
		public void SellerSide_options(String Individual_Name, String Estate_Name, String Corporation_Name,
				Map<String, String> clientNames) throws IOException {
			List<String> expectedNames = Arrays.asList(Individual_Name, Estate_Name, Corporation_Name);

			try {
				for (String fieldName : new String[] { "Individual_Name", "Estate_Name", "Corporation_Name" }) {
					handleFieldAction(fieldName, clientNames.get(fieldName));
				}

				verifySellerSideNames(expectedNames);
				Basic_Tab.click();
				verifySellerField(expectedNames);
			} catch (Exception e) {
		        handleCommonException(e, "An exception occurred while testing seller Side section.", "12393");
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
		            handleCommonException(e, "Failed during " + fieldName + " processing", "12393");
				}
			}
		}

		// Individual
		private void handleIndividualName(String clientName) throws InterruptedException, IOException {
			 try {
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
			catch (Exception e) {
		        handleCommonException(e, "Failed during Individual processing", "12393");
		    }
		}

		// Estate
		private void handleEstateName(String clientName) throws InterruptedException, IOException {
			try {
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
				Azure.updateTestCaseStatus("12348", "Automation Fail","FAIL: Fail during Estate processing");
				Assert.fail();
			}
		 }
		catch (Exception e) {
	        handleCommonException(e, "Failed during Estate processing", "12393");
	    }
		}

		// Corporation
		private void handleCorporationName(String clientName) throws InterruptedException, IOException {
			try {
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
				Azure.updateTestCaseStatus("12348", "Automation Fail","FAIL: Fail during Corporation processing");
				Assert.fail();
			}
			 }
			catch (Exception e) {
		        handleCommonException(e, "Failed during Corporation processing", "12393");
		    }
		}

		private void verifySellerSideNames(List<String> expectedNames) throws IOException {
			try {
			driver.switchTo().defaultContent();
			boolean isMatchFound_seller = true;

			for (WebElement nameElement : Basic_tab.fileNavigationSellerSide) {
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
					isMatchFound_seller = false;
					break;
				}
			}

			if (isMatchFound_seller) {
				Azure.updateTestCaseStatus("12393", "Automation Pass", "");
				System.out.println("PASS: All Seller Names Found in File navigation > Seller Side");
			} else {
				Azure.updateTestCaseStatus("12393", "Automation Fail","Fail: Not All Seller Names Found in File navigation > Seller Side");
				Assert.fail();
			}
			}
			catch (Exception e) {
		        handleCommonException(e, "An exception occurred during Seller Side Names verification", "12393");
		    }
		}

		private void verifySellerField(List<String> expectedNames) throws IOException {
			 try {
			common.switchToIframe(common.MiddlePortionFrame);
			Basic_tab.editTransferor.click();
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//i[@id='dialog-close']")).click();
			common.switchToIframe(common.MiddlePortionFrame);
			String textInIframe = Basic_tab.Transferor_field.getText();
			boolean isMatchFound_seller1 = true;

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
				isMatchFound_seller1 = false;
			}

			if (isMatchFound_seller1) {
				Azure.updateTestCaseStatus("12393", "Automation Pass", "");
			} else {
				Azure.updateTestCaseStatus("12393", "Automation Fail","Fail: Not All Seller Names Found in Basic > Transferor field");
				Assert.fail();
			}
			 }
			catch (Exception e) {
		        handleCommonException(e, "An exception occurred during Seller Field verification", "12393");
		    }
		}
		private void handleCommonException(Exception e, String errorMessage, String testCaseNumber) throws IOException {
		    e.printStackTrace();
		    String exceptionTitle = e.getClass().getSimpleName();
		    Azure.updateTestCaseStatus(testCaseNumber, "Automation Error", exceptionTitle);
		    System.out.println("****ERROR: " + errorMessage + " Details: " + e.getMessage());
		    Assert.fail("Error", e);
		}

		//Test Case 12395: Seller Side>verify Move Up ,Move Down options in three dots menu
		//Test Case 12396: Seller Side>Verify Remove option in three dots menu.
		public void SellerSideThreeDotsMenu() throws IOException {
			 try {
		    Map<String, String> clientNames = new HashMap<>();
		   
		    clientNames.put("Individual_Name", "John Doe");
		    clientNames.put("Estate_Name", "Test");
		    clientNames.put("Corporation_Name", "XYZ Corporation");
		   
		    // Call the BuyerSide_options method 
		    SellerSide_options("John Doe", "Test", "XYZ Corporation", clientNames);
		   
		    driver.switchTo().defaultContent();
		    List<WebElement> fileNavigationsellerSide = Basic_tab.fileNavigationSellerSide;
		    try {
		    // Move Down
		    	fileNavigationsellerSide.get(0).click();
		    	fileNavigationsellerSide.get(0).findElement(By.xpath("(//i[@class='fa-solid fa-ellipsis-vertical'])[1]"))
		            .click();
		    String firstsellerName = fileNavigationsellerSide.get(0).getText();

		    Move_Down.click();
		    String secondsellerName = fileNavigationsellerSide.get(1).getText();

		
		        if (firstsellerName.equals(secondsellerName)) {
		            Azure.updateTestCaseStatus("12395", "Automation Pass", "");
		        } else {
		        	Azure.updateTestCaseStatus("12395", "Automation Fail","Fail: Move Down options not working properly");
					Assert.fail();
		        }
		        
		 }
		    
		     catch (Exception e) {
		    	  common.handleException(new Exception("Fail: Move Down options not working properly"),
		                    "Fail: Move Down options not working properly", "12395");
		    }
			
			
		    // Move Up
		    fileNavigationsellerSide.get(1).click();
		    fileNavigationsellerSide.get(1).findElement(By.xpath("(//i[@class='fa-solid fa-ellipsis-vertical'])[2]"))
		            .click();
		    String firstsellerName1 = fileNavigationsellerSide.get(0).getText();
		    Move_Up.click();

		    String secondsellerName1 = fileNavigationsellerSide.get(1).getText();

		    try {
		        if (secondsellerName1.equals(firstsellerName1)) {
		            Azure.updateTestCaseStatus("12395", "Automation Pass", "");
		        } else {
		        	Azure.updateTestCaseStatus("12395", "Automation Fail","Fail: Move Up options not working properly");
					Assert.fail();
		        }
		    } catch (Exception e) {
		    	  common.handleException(new Exception("Fail: Move Up options not working properly"),
		                    "Fail: Move Up options not working properly", "12395");
		    }

			
			    
		    //Test Case 12396: Seller Side>Verify Remove option in three dots menu.
		    //Remove
		    driver.switchTo().defaultContent();
		    fileNavigationsellerSide.get(0).click();
			String firstsellerName2 = fileNavigationsellerSide.get(0).getText();

			try {
				Three_dots_menu.click();
				Remove.click();

				Thread.sleep(2000);

				@SuppressWarnings("unlikely-arg-type")
				boolean deletename = !fileNavigationsellerSide.contains(firstsellerName2);

				if (deletename) {
					Azure.updateTestCaseStatus("12396", "Automation Pass", "");
				} else {
					common.handleException(new Exception("Fail: Remove option in three dots menu not working properly"),
							"Fail: Remove option in three dots menu not working properly", "12396");
				}
			} catch (InterruptedException e) {
				Azure.updateTestCaseStatus("12396", "Automation Fail","Fail: Remove option in three dots menu not working properly");
				Assert.fail();		
				}
			 }
			 
			catch (Exception e) {
		    	  common.handleException(new Exception("Fail: one of options from Move up,Move down,remove or ID verification area option not working"),
		                    "Fail: one of options from Move up,Move down,remove or ID verification area option not working", "12395");
		    }

		}
		 
}
