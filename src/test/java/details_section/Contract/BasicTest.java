package details_section.Contract;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import details_section.PageObject.CONTRACT.Basic;
import details_section.PageObject.CONTRACT.Property;
import home.PageObject.FileList;
import project.TestComponents.BaseTest;

public class BasicTest extends BaseTest {

	private Basic BasicTab;
	

	@BeforeMethod
	public void initializeTest() {
		BasicTab = new Basic(driver);
		
	}
	
	
	//Test Case 12080: File Details -> Verify Responsible lawyer field working proper or not
	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_File_Details_Responsible_Lawyer_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));			
 		BasicTab.ResponsibleLawyer(testData.get(0).get("Responsible_Lawyer"));
		Thread.sleep(2000);

	}
	
	//Test Case 12081: File Details -> Verify Firm Contact with Select, Edit and Clear functionality
	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_File_Details_FirmContactAddEditDelete_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
	
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		BasicTab.FirmContactAddEditClear();
		

	}
	
    //Test Case 12082: File Details -> verify Conveyancer field functionality proper or not
	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_File_Details_Conveyancer_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {

		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));	
		BasicTab.Conveyancer();

	}
	
	//Test Case 12083: File Configuration->verify File Configuration functionality working proper or not
	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_File_Configuration_Different_fields_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {

		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		String frame_xpath1 = "//iframe[@class='clsCompatHost']";
		WebElement frame_element1 = driver.findElement(By.xpath(frame_xpath1));
		driver.switchTo().frame(frame_element1);
		BasicTab.File_Configuration();

	}

  //Test Case 12087: Transferee(s) ->Verify Transferee(s) with Single, Estate and Corporation names
  @Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_Transferee_Different_Criteria_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		BasicTab.Transferee(testData.get(0).get("Single_Transferee"), testData.get(0).get("Estate_Transferee"),
				testData.get(0).get("Corporation_Transferee"), testData.get(0));

	}
	
   //Test Case 12089: Transferor(s) ->Verify Transferor(s) with Single, Estate and Corporation names
	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_Transferor_Different_Criteria_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));	
		BasicTab.Transferor(testData.get(0).get("Single_Transferor"), testData.get(0).get("Estate_Transferor"),
				testData.get(0).get("Corporation_Transferor"), testData.get(0));

	}
	
 //Test Case 12093: Fire Insurance -> Verify Provider field working or not
   @Test(dataProvider = "Details_Section")	
	public void Verify_In_BasicSection_Fire_Insurance_Different_fields_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
	launchApplicationAndLogin();
	BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		BasicTab.FireInsurance();
		
	}
	
   //Test Case 12094: Mortgage Broker ->Verify Broker field functionality working proper or not
	@Test(dataProvider = "Details_Section")
	
	public void Verify_In_BasicSection_Mortgage_Broker_field_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		BasicTab.MortgageBroker();
		
	}
	
	
 	//Test Case 12095: Mortgage Broker -> verify Mortgage Broker Fee checkbox in Trust statement page.	
 @Test(dataProvider = "Details_Section")	
	public void Verify_In_BasicSection_MortgageBrokerFee_field_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
	launchApplicationAndLogin();
	BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		BasicTab.mortgageBrokerFee(testData.get(0).get("MortgageBroker_Fee"));
		
	}
	
 private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}
	
}
