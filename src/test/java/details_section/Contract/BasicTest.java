package details_section.Contract;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import details_section.PageObject.CONTRACT.Basic;
import home.PageObject.FileList;
import project.TestComponents.BaseTest;

public class BasicTest extends BaseTest {

	public HashMap<String, String> loginData; // Class-level variable to store login data

	// Fetch login data from the loginData data provider and store it
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void setupLoginData() {
		Object[][] loginDataArray = null;
		try {
			loginDataArray = loginData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (loginDataArray != null && loginDataArray.length > 0) {
			loginData = (HashMap<String, String>) loginDataArray[0][0];
		}
	}

	// Define a login method
	public void login(HashMap<String, String> loginData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}

	
	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_File_Details_Responsible_Lawyer_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {

		HashMap<String, String> BasicData = testData.get(0);
		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
			
 		BasicTab.ResponsibleLawyer(BasicData.get("Responsible_Lawyer"));
		Thread.sleep(2000);
		
		

	}
	
	
	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_File_Details_FirmContactAddEditDelete_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {

		
		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));	
		BasicTab.FirmContactAddEditClear();
		

	}
	

	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_File_Details_Conveyancer_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {

		
		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));		
		BasicTab.Conveyancer();

	}
	
	
	

	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_File_Configuration_Different_fields_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {

		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		String frame_xpath1 = "//iframe[@class='clsCompatHost']";
		WebElement frame_element1 = driver.findElement(By.xpath(frame_xpath1));
		driver.switchTo().frame(frame_element1);
		BasicTab.File_Configuration();

	}

   @Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_Transferee_Different_Criteria_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		HashMap<String, String> BasicData = testData.get(0);
		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));		
		BasicTab.Transferee(BasicData.get("Single_Transferee"), BasicData.get("Estate_Transferee"),
		BasicData.get("Corporation_Transferee"), BasicData);

	}
	

	@Test(dataProvider = "Details_Section")
	public void Verify_In_BasicSection_Transferor_Different_Criteria_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		HashMap<String, String> BasicData = testData.get(0);
		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));		
		BasicTab.Transferor(BasicData.get("Single_Transferor"), BasicData.get("Estate_Transferor"),
		BasicData.get("Corporation_Transferor"), BasicData);

	}
	
	@Test(dataProvider = "Details_Section")
	
	public void Verify_In_BasicSection_Fire_Insurance_Different_fields_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		BasicTab.FireInsurance();
		
	}
	

	@Test(dataProvider = "Details_Section")
	
	public void Verify_In_BasicSection_Mortgage_Broker_field_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		BasicTab.MortgageBroker();
		
	}
	
  @Test(dataProvider = "Details_Section")
	
	public void Verify_In_BasicSection_MortgageBrokerFee_field_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
	HashMap<String, String> BasicData = testData.get(0);
		login(loginData);
		Basic BasicTab = new Basic(driver);
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));		
		BasicTab.mortgageBrokerFee(BasicData.get("MortgageBroker_Fee"));
		
	}
	
	
	
}
