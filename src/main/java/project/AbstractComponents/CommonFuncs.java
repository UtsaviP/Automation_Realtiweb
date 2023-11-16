package project.AbstractComponents;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import home.PageObject.Advance_Search_Filter;
import home.PageObject.FileList;

public class CommonFuncs extends AbstractComponent {
	WebDriver driver;
	AzureDevOpsIntegration Azure;
	public CommonFuncs(WebDriver driver) {
		super(driver);
		this.driver = driver;
		Azure = new AzureDevOpsIntegration(); // Initialize AzureDevOpsIntegration
		PageFactory.initElements(driver, this);
	}

	// Common Xpath
	public By Filelistname = By.xpath("//div[@id='ListSection']//table//tbody//td[1]/span");

	@FindBy(xpath = "//iframe[@class='clsCompatHost']")
	public WebElement MiddlePortionFrame;
	
	@FindBy(xpath = "//iframe[@id='dialog-body']")
	public WebElement PopUpFrame;
	

	@FindBy(xpath = "//div[@class='me-auto']//span[contains(text(), 'Basic')]")
	public WebElement Basic_Tab;
	
	
	
	
   //A utility method to switch to an iframe by its XPath
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

	// Method to wait File list Update
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
					// System.out.println("File list has been updated with " + updatedSize + "
					// elements.");
				} else {
					System.out.println("No search result found.");
				}
			} else {
				System.out.println("No search result found.");
			}
		} catch (TimeoutException ex) {

		} catch (StaleElementReferenceException ex) {
			// Handle the stale element reference exception
			System.out.println("Stale element reference exception occurred.");
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

	//Handle if..Else  and try..catch parts Exceptions	
	public void handleException(Exception e, String errorMessage, String testCaseId) throws IOException {
	    e.printStackTrace();

	    String truncatedMessage = truncateErrorMessage(e.getMessage(), 500);

	    if (errorMessage.contains("Automation Fail")) {
	        Azure.updateTestCaseStatus(testCaseId, "Automation Fail", "Failed during Estate processing");
	    } else {
	        Azure.updateTestCaseStatus(testCaseId, "Automation Error", truncatedMessage);
	    }

	    Assert.fail("*****Fail: " + errorMessage + ".*****", e);
	}

	public String truncateErrorMessage(String errorMessage, int maxLength) {
	    if (errorMessage.length() > maxLength) {
	        return errorMessage.substring(0, maxLength);
	    }
	    return errorMessage;
	}

}
