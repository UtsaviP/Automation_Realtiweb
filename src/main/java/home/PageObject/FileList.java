package home.PageObject;


import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;

public class FileList extends AbstractComponent {
	

	WebDriver driver;

	public FileList(WebDriver driver) {// constructor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// File->FileList PageFactory
	@FindBy(xpath = "(//span[contains(text(),'Create File')])[2]")
	public WebElement CreateFile_Button;

	@FindBy(xpath = "//div[@id='tdActiveFiles']/span[text()='Active']")
	public WebElement ActiveRadioOption;

	@FindBy(id = "(//input[@id='currentListFilter']")
	public WebElement ListFilter;

	@FindBy(xpath = " //a[contains(text(),'Archive')]")
	public WebElement Archive;

	@FindBy(xpath = " //a[contains(text(),'Activate')]")
	public WebElement Activate;
	
	@FindBy(xpath = "//input[@id='fileSearchInput']")
	public WebElement freeSearch;
	
	@FindBy(xpath = "//i[contains(text(),'- Starting')]")
	public WebElement Expandsearchcaption;
		
	By ThreeDotmenu = By.xpath("//tbody/tr[1]/td[7]//button");
	By Firstfilestatus = By.xpath("//tbody/tr[1]/td[6]");
	
	AzureDevOpsIntegration Azure = new AzureDevOpsIntegration();
	
	
	//***Verify Archived and Activate option working or not in File List > Three Dots menu***
	public FileList ArchiveAndActiveOption() throws InterruptedException, IOException {
	    Advance_Search_Filter searchobject = new Advance_Search_Filter(driver);
	    List<WebElement> initialFileList = driver.findElements(searchobject.Filelistname);
	    searchobject.waitForFileListUpdate(initialFileList);

	    if (!isElementDisplayed(searchobject.Noresult)) {
	        try {
	            ThreeDotsmenu();
	            boolean archiveStatus = false;
	            boolean activeStatus = false;

	            if (Archive.isDisplayed()) {
	                Archive.click();
	                Thread.sleep(2000);
	                String status = driver.findElement(Firstfilestatus).getText();
	                if (status.equals("Archived")) {
	                    archiveStatus = true;
	                    System.out.println("*****Pass : Status has been changed from Active to archived.*****");

	                    // Now proceed to the second test case
	                    ThreeDotsmenu();
	                    ThreeDotsmenu();
	                    if (Activate.isDisplayed()) {
	                        Activate.click();
	                        Thread.sleep(2000);
	                        status = driver.findElement(Firstfilestatus).getText();
	                        if (status.equals("Active")) {
	                            activeStatus = true;
	                            System.out.println("*****Pass : Status has been changed from Archived to Active.*****");
	                        } else {
	                            Assert.fail("*****Fail : Status has not been changed from Archived to Active.*****");
	                        }
	                    }
	                } else {
	                    Assert.fail("*****Fail : Status has not been changed from Active to archived.*****");
	                }
	            }

	            if (archiveStatus && activeStatus) {
	                Azure.updateTestCaseStatus("12067", "Automation Pass");
	                System.out.println("***Both test cases passed.**");
	            } else {
	                Azure.updateTestCaseStatus("12067", "Automation Fail");
	                System.out.println("**One or both test cases failed.**");
	            }
	        } catch (Exception e) {
	        	Azure.updateTestCaseStatus("12067", "Automation Fail");
	           Assert.fail("Fail",e);
	            e.printStackTrace(); 
	        }
	    } else {
	        System.out.println("***No Search result found ***");
	    }

	    return new FileList(driver);
	}

	public void ThreeDotsmenu() {
	    try {
	        WebElement elementToHover = driver.findElement(ThreeDotmenu);
	        Actions actions = new Actions(driver);
	        actions.moveToElement(elementToHover).build().perform();
	        driver.findElement(ThreeDotmenu).click();
	    } catch (Exception e) {
	        Assert.fail("Fail",e);
	        e.printStackTrace(); 
	    }
	}

	

	  
	
	//*** Verify the free search functionality and check if the bold name exactly matches the searchable name or not ***
	public FileList FreeSearch(String FreeSearch_1, String FreeSearch_2) throws InterruptedException, IOException {
	    Advance_Search_Filter searchobject = new Advance_Search_Filter(driver);
	    String[] freesearchname = { FreeSearch_1, FreeSearch_2 };

	    try {
	        for (String freesearch : freesearchname) {
	            freeSearch.clear();
	            freeSearch.sendKeys(freesearch);

	            List<WebElement> initialFileList = driver.findElements(searchobject.Filelistname);
	            searchobject.waitForFileListUpdate(initialFileList);

	            if (!isElementDisplayed(searchobject.Noresult)) {
	                boolean allFilesMatchingSearch = true;
	                // Re-find the elements after waiting
	                initialFileList = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr/td"));

	                for (WebElement fileElement : initialFileList) {
	                    String fileName = fileElement.getText();
	                    String boldText = getBoldTextFromElement(fileElement);
	                    if (boldText.isEmpty()) {
	                        continue; 
	                    }

	                    if (boldText.equalsIgnoreCase(freesearch)) { // Using equalsIgnoreCase for case-insensitive comparison
	                        System.out.println("Pass: Bold text inside file name matches search text: " + boldText + ":" + fileName);
	                    } else {                    
	                        System.out.println("***** Fail: Bold text inside file name does not match search text: " + boldText + ":" + fileName);
	                        allFilesMatchingSearch = false;
	                        break;
	                    }
	                }

	                if (allFilesMatchingSearch) {
	                    Azure.updateTestCaseStatus("12066", "Automation Pass");
	                    System.out.println("***** Pass: All files match search criteria *****");
	                } else {
	                    Azure.updateTestCaseStatus("12066", "Automation Fail");
	                    System.out.println("***** Fail: Some files do not match search criteria *****");
	                    Assert.fail("***** Fail: Some files do not match search criteria *****");
	                }
	            } else {
	                System.out.println("*** No Search result found ***");
	            }
	        }
	    } catch (Exception e) {
	    	 Azure.updateTestCaseStatus("12066", "Automation Fail");
	    	 Assert.fail("Fail",e);
	         e.printStackTrace();
	    }
	    return new FileList(driver);
	}


	public String getBoldTextFromElement(WebElement element) {
	    String boldText = "";

	    List<WebElement> spanElements = element.findElements(By.tagName("span"));
	    
	    for (WebElement spanElement : spanElements) {
	        String fontWeight = spanElement.getCssValue("font-weight");
	        
	        if (fontWeight.equals("bold") || Integer.parseInt(fontWeight) >= 700) {
	            boldText = spanElement.getAttribute("textContent").trim();
	            break;  // Assuming only one bold span element contains the text
	        }
	    }

	    return boldText;
	}


		
	
	


  
  
  
}