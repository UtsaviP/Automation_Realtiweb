package details_section.PageObject.ADDITIONAL_PARTY;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import details_section.PageObject.CONTRACT.Basic;
import details_section.PageObject.CONTRACT.Contract;
import home.PageObject.Advance_Search_Filter;
import home.PageObject.FileList;
import io.netty.util.internal.ThreadLocalRandom;
import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;
import project.AbstractComponents.CommonFuncs;

public class Additional_Parties extends AbstractComponent {
	WebDriver driver;

	CommonFuncs common;
	AzureDevOpsIntegration Azure;
	Basic Basic_tab;
	Contract Contract_tab;

	public Additional_Parties(WebDriver driver) {
		super(driver);
		this.driver = driver;
		common = new CommonFuncs(driver); // Initialize CommonFuncs
		Azure = new AzureDevOpsIntegration(); // Initialize AzureDevOpsIntegration
		Basic_tab = new Basic(driver);
		Contract_tab = new Contract(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//i[@class='fa-light fa-plus'])[3]")
	public WebElement Plus_menu;

	@FindBy(xpath = "//a[text()='Conveyancer']")
	public WebElement Conveyancer;

	@FindBy(xpath = "//tr[@title='Enter the name of the Conveyancer.']/td/input")
	public WebElement Conveyancer_field;

	@FindBy(xpath = "//a[text()='Fire Insurance']")
	public WebElement Fire_Insurance;

	@FindBy(xpath = "//tr[@title='Enter the name of the Insurance Company.']//td[text()='Company']/following-sibling::td[input[@attrname='Company' and @subnodestatus='0']]/input")
	public WebElement insurancecompany_field;

	@FindBy(xpath = "//tr[@title='Enter the name of the Broker Company.']//td[text()='Company']/following-sibling::td[input[@attrname='Company' and @subnodestatus='0']]/input")
	public WebElement Brokercompany_field;

	@FindBy(xpath = "//tr[@title='Enter the name of the Real Estate Agent.']//td[text()='Realtor Name']/following-sibling::td[input[@attrname='Name' and @subnodestatus='0']]/input")
	public WebElement RealtorName_field;

	@FindBy(xpath = "//tr[@title='Enter the name of the Company.']//td[text()='Office Name']/following-sibling::td[input[@subnodestatus='0']]/input")
	public WebElement OfficeName_field;

	@FindBy(xpath = "//tr[@title='Enter the name of the Company.']//td[text()='Office Name']/following-sibling::td[input[@subnodestatus='0']]/input")
	public WebElement RelocationName_field;

	@FindBy(xpath = "(//span[text()='Referral']//preceding::tr[@title='Enter the name of the Company.']/td/input[@subnodestatus='0'])[last()]")
	public WebElement ReferralCompany_field;

	@FindBy(xpath = "(//span[text()='Relocation Company']//preceding::tr[@title='Enter the name of the Company.']/td/input[@subnodestatus='0'])[last()]")
	public WebElement RelocationCompany_field;

	@FindBy(xpath = "(//span[text()='Water Company']//preceding::tr[@title='Enter the name of the Company.']/td/input[@subnodestatus='0'])[last()]")
	public WebElement WaterCompany_field;

	@FindBy(xpath = "(//tr[@title='Enter the title of the Insurance Agent.']//td[text()='Agent Title']/following-sibling::td[input[@subnodestatus='0']]/input)[last()]")
	public WebElement Agentname_field;

	@FindBy(xpath = "//a[text()='Mortgage Broker']")
	public WebElement Mortgage_Broker;

	@FindBy(xpath = "//a[text()='Real Estate Broker']")
	public WebElement Real_Estate_Broker;

	@FindBy(xpath = "//a[text()='Relocation Company']")
	public WebElement Relocation_Company;

	@FindBy(xpath = "//a[text()='Referral']")
	public WebElement Referral;

	@FindBy(xpath = "//a[text()='Water Company']")
	public WebElement Water_Company;

	@FindBy(xpath = "//input[@id='fileSearchInput']")
	public WebElement AdditionalParties_Search;

	@FindBy(xpath = "//button[@class='btn btn-primary']//i[@class='fa-light fa-plus']")
	public WebElement AddNew_Parties;

	@FindBy(xpath = "//div[@id='ReactConveyancer']//button[contains(text(),'Update List') and @subnodestatus='0']")
	public WebElement UpdateList_Button;

	@FindBy(xpath = "//span[contains(text(),'Conveyancer') and @class='badge rounded-pill text-dark bg-info']//preceding::span[1]")
	public WebElement Navigation_Conveyancername;

	@FindBy(xpath = "//span[contains(text(),'Fire Ins.')]//preceding::span[1]")
	public WebElement Navigation_fireinsname;

	@FindBy(xpath = "//span[contains(text(),'Mortgage Broker')]//preceding::span[1]")
	public WebElement Navigation_Brokername;

	@FindBy(xpath = "(//span[contains(text(),'Realtor') ]//preceding::span[1])[last()]")
	public WebElement Navigation_Realtername;

	@FindBy(xpath = "(//h4[contains(text(),'Additional Parties (3')]//following::span//i[@class='fa-light fa-chevron-down'])[1]")
	public WebElement Navigation_ArrowDOwn;

	@FindBy(xpath = "//table[@class='table table-hover']//tbody/tr/td")
	List<WebElement> PartiesList;

	@FindBy(xpath = "//table[@class='table table-hover']//tbody/tr")
	List<WebElement> SearchPartiesList;

	@FindBy(xpath = "//button[text()='Cancel']")
	public WebElement CancelButton;

	// Test Case 12688: Additional Parties >Verify all options from + menu and The
	// selected name should be displayed in the File navigation as well as in the
	// respective tab where that option is available.
	// Test Case 12690: Additional Parties >in Lists, Any name is not available then
	// click on +Add option and add new name
	public void selectPartyAndVerify() throws IOException {

		// Conveyancer
		switchPartyAndVerify(Conveyancer, "Conveyancer_name", Navigation_Conveyancername,
				Basic_tab.displayConveyancerName);

		// Fire_Insurance
		switchPartyAndVerify(Fire_Insurance, "Fireins_name", Navigation_fireinsname,
				Basic_tab.displayFireInsuranceName);

		// Mortgage_Broker
		switchPartyAndVerify(Mortgage_Broker, "Broker_name", Navigation_Brokername,
				Basic_tab.displayMortgageBrokerName);

		// Real_Estate_Broker
		switchPartyAndVerify(Real_Estate_Broker, "Realter_name", Navigation_Realtername, Contract_tab.Broker_Field);

	}
	private void switchPartyAndVerify(WebElement partyElement, String defaultName, WebElement navigationElement,
	        WebElement displayElement) throws IOException {
	    try {
	        Plus_menu.click();
	        partyElement.click();
	        List<WebElement> additionalPartiesList = PartiesList;

	        if (additionalPartiesList.size() > 0) {
	            additionalPartiesList.get(0).click();
	            common.switchToIframe(common.MiddlePortionFrame);
	            String fieldName = getFieldText(defaultName);
	            driver.switchTo().defaultContent();
	        
	            By arrowDownLocator = By.xpath("//h4[contains(text(),'Additional Parties (3')]");
	            if (isElementPresent(arrowDownLocator)) {
	                Navigation_ArrowDOwn.click();
	            }
	                String navigationName = navigationElement.getText();
	                clickOnTab(defaultName);

	                common.switchToIframe(common.MiddlePortionFrame);
	                String displayFieldName = getDisplayFieldText(defaultName);

	                if (removeCommasAndDots(fieldName).contains(removeCommasAndDots(navigationName)) &&
	                	    removeCommasAndDots(fieldName).contains(removeCommasAndDots(displayFieldName))) {
	                	    
	                	

	                    Azure.updateTestCaseStatus("12688", "Automation Pass", "");
	                    System.out.println("PASS: Additional Parties options working fine");
	                } else {
	                    common.handleException(new Exception("Fail: Not All Additional Parties options working fine"),
	                            "Fail: Not All Additional Parties options working fine", "12688");
	                }
	           
	            driver.switchTo().defaultContent();
	        } else {
	            driver.switchTo().defaultContent();
	            AddNew_Parties.click();
	            common.switchToIframe(common.MiddlePortionFrame);
	            Addname(defaultName);
	            String fieldName = getFieldText(defaultName);
	            System.out.println(fieldName);
	            driver.switchTo().defaultContent();
	            driver.findElement(By.xpath("//i[@class='fa-regular fa-align-left fa-indicator']")).click();
	            try {
	                Thread.sleep(2000);
	                if (driver.findElement(By.xpath("//h4[contains(text(),'Additional Parties (3')]")).isDisplayed()) {
	                    Navigation_ArrowDOwn.click();
	                }

	                String navigationName = navigationElement.getText();

	                if (fieldName.contains(navigationName)) {
	                    Azure.updateTestCaseStatus("12690", "Automation Pass", "");
	                    System.out.println("PASS: Additional Parties  +ADD functionality working fine");
	                } else {
	                    common.handleException(
	                            new Exception("Fail: Additional Parties  +ADD functionality not working fine"),
	                            "Fail: Additional Parties  +ADD functionality not working fine", "12690");
	                }
	            } catch (Exception e) {
	                common.handleException(e, "Failed during  click on Additional Parties Arrows Down ", "12688");
	            }
	        }
	    } catch (Exception e) {
	        common.handleException(e, "Failed during  check  Additional Parties different options", "12688");
	    }
	}
	
	
	private String removeCommasAndDots(String input) {
	    return input.replaceAll("[.,]", "");
	}

	
	
	
	
	// Add a method to check if an element is present
	private boolean isElementPresent(By locator) {
	    try {
	        driver.findElement(locator);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    }
	private String getFieldText(String defaultName) {
		switch (defaultName) {
		case "Conveyancer_name":
			return Conveyancer_field.getAttribute("value");
		case "Fireins_name":
			return insurancecompany_field.getAttribute("value");
		case "Broker_name":
			return Brokercompany_field.getAttribute("value");
		case "Realter_name":
			if (!RealtorName_field.getAttribute("value").isEmpty()) {
				return RealtorName_field.getAttribute("value");
			} else {
				return OfficeName_field.getAttribute("value");
			}

		default:
			return "";
		}

	}

	private String getDisplayFieldText(String defaultName) {
		switch (defaultName) {
		case "Conveyancer_name":
			return Basic_tab.displayConveyancerName.getText();
		case "Fireins_name":
			return Basic_tab.displayFireInsuranceName.getText();
		case "Broker_name":
			return Basic_tab.displayMortgageBrokerName.getAttribute("value");
		case "Realter_name":
			return processBrokerField(Contract_tab.Broker_Field.getText());
		default:
			return "";
		}
	}

	private static String processBrokerField(String input) {

		String cleaned = input.replaceAll("[^a-zA-Z0-9]+", " ");

		// Split the cleaned string into words
		String[] words = cleaned.split("\\s");

		// List of words to ignore
		List<String> ignoredWords = Arrays.asList("--", "other_word_to_ignore");

		// Filter out ignored words
		List<String> filteredWords = Arrays.stream(words).filter(word -> !ignoredWords.contains(word))
				.collect(Collectors.toList());

		// Join the remaining words back into a string
		String result = String.join(" ", filteredWords);

		return result;
	}

	private void clickOnTab(String defaultName) {
		switch (defaultName) {
		case "Conveyancer_name":
			common.Basic_Tab.click();
			break;
		case "Fireins_name":
			common.Basic_Tab.click();
			break;
		case "Broker_name":
			common.Basic_Tab.click();

		case "Realter_name":
			common.Contract_Tab.click();
			break;
		}
	}

	private void Addname(String defaultName) {
		switch (defaultName) {
		case "Conveyancer_name":
			Conveyancer_field.sendKeys("Conveyancer_name");
			break;
		case "Fireins_name":
			insurancecompany_field.sendKeys("Fireins_name");
			break;
		case "Broker_name":
			Brokercompany_field.sendKeys("Broker_name");

		case "Realter_name":
			RealtorName_field.sendKeys("Realter_name");
			break;

		}

	}

	// Test Case 12689: Additional Parties >Verify Search functionality working
	// proper or not.

	public void AdditionalParties_Search(String searchName) throws InterruptedException, IOException {
		String[] searchNames = { searchName };

		try {
			Plus_menu.click();
			Real_Estate_Broker.click();

			for (String brokerSearchName : searchNames) {
				AdditionalParties_Search.click();
				List<WebElement> additionalPartiesList = PartiesList;

				if (!additionalPartiesList.isEmpty()) {
					AdditionalParties_Search.sendKeys(brokerSearchName);

					Thread.sleep(3000);
					List<WebElement> searchPartiesList = SearchPartiesList;

					if (!searchPartiesList.isEmpty()) {
						boolean allPass = true;

						int originalSize = searchPartiesList.size();

						for (int index = 0; index < originalSize; index++) {
							try {
								WebElement additionalPartiesList1 = searchPartiesList.get(index);
								WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
						        wait.until(ExpectedConditions.visibilityOfAllElements(additionalPartiesList1));

								String partyName = additionalPartiesList1.getText();

								if (!partyName.isEmpty()
										&& partyName.toLowerCase().contains(brokerSearchName.toLowerCase())) {
									// Your existing code...
								} else {
									allPass = false;
								}
							} catch (StaleElementReferenceException e) {
								// Re-locate the element and try again
								searchPartiesList = SearchPartiesList;
								originalSize = searchPartiesList.size();
								index = -1; // Re-initialize the index
								continue;
							} catch (Exception e) {
								common.handleException(e, "Failed during check Additional Parties Search functionality",
										"12689");
							}
						}

						try {
							if (allPass) {
								CancelButton.click();
								Azure.updateTestCaseStatus("12689", "Automation Pass", "");
								System.out.println("PASS: Additional Parties Search functionality working fine");
							} else {
								CancelButton.click();
								common.handleException(
										new Exception("Fail: Additional Parties Search functionality working fine"),
										"Fail: Additional Parties Search functionality not working fine", "12689");
							}
						} catch (Exception e) {
							common.handleException(e, "Failed during check Additional Parties Search functionality",
									"12689");
						}
					} else {
						System.out.println("No Search result found for Additional parties pop up");
						CancelButton.click();
					}
				} else {
					System.out.println("No file list available Real estate broker pop up");
					CancelButton.click();
				}
			}
		} catch (Exception e) {
			common.handleException(e, "Failed during check Additional Parties Search functionality", "12689");
		}
	}

	// Test Case 12691: Additional Parties >Update List button option working proper or not
	public void updateListButton() throws IOException {
		Plus_menu.click();
		Conveyancer.click();
		AddNew_Parties.click();

		common.switchToIframe(common.MiddlePortionFrame);

		Conveyancer_field.click();
		int int_random = ThreadLocalRandom.current().nextInt();
		Conveyancer_field.sendKeys("UpdateList" + int_random);

		String buttonText = Conveyancer_field.getAttribute("value");
		UpdateList_Button.click();

		driver.switchTo().alert().accept();

		driver.switchTo().defaultContent();

		Plus_menu.click();
		Conveyancer.click();

		List<WebElement> searchPartiesList = SearchPartiesList;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(searchPartiesList));
		if (searchPartiesList.stream().anyMatch(element -> element.getText().equals(buttonText))) {
			CancelButton.click();
			Azure.updateTestCaseStatus("12691", "Automation Pass", "");
			System.out.println("PASS: Additional Parties Update List button functionality working fine");
		} else {
			CancelButton.click();
			common.handleException(
					new Exception("Fail: Additional Parties Update List button functionality not working fine"),
					"Fail: Additional Parties Update List button functionality not working fine", "12691");
		}
	}

}
