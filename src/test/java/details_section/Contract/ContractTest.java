package details_section.Contract;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import details_section.PageObject.CONTRACT.Basic;
import details_section.PageObject.CONTRACT.Contract;
import project.TestComponents.BaseTest;

public class ContractTest extends BaseTest {
	private Basic BasicTab;
	private Contract ContractTab;

	@BeforeMethod
	public void initializeTest() {
		BasicTab = new Basic(driver);
		ContractTab = new Contract(driver);
	}

	// Test Case 12141: Purchasers(s):Verify To Reside at Property on Closing field based on Yes/No changes.
	@Test(dataProvider = "Details_Section")
	public void Verify_To_Reside_at_Property_on_Closing_field_based_on_Yes_or_No_changes(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.ToResideAtPropertyOnClosing_Field();
	}

	// Test Case 12144: Other Side Legal Representation: Verify Lawyer field with Law Society of Ontario functionality.
	@Test(dataProvider = "Details_Section")
	public void Verify_Lawyer_field_with_Law_Society_of_Ontario_functionality(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.OtherSideLawyer(testData.get(1).get("OtherSide_Lawyer"));
	}

	// Test Case 12145: Contract: Verify Closing Date field working proper or not
	@Test(dataProvider = "Details_Section")
	public void Verify_in_Contract_section_Closing_date_field_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.Closing_Date(testData.get(1).get("Closing_Date"));
	}

	//Test Case 12146: Contract: Verify Offer Conditional field Functionality
	@Test(dataProvider = "Details_Section")
	public void Verify_in_Contract_section_offer_conditional_date_field_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.Offer_ConditionalField();
	}

	// Test Case 12147: Deposits: Verify Deposits functionality
	@Test(dataProvider = "Details_Section")
	public void Verify_in_Deposit_section_Deposit_field_working_proper_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.Deposits(testData.get(1).get("Broker_Deposit"), testData.get(1).get("Lawyer_Deposit"),
		testData.get(1).get("Vendor_Deposit"));
	}

	// Test Case 12148: Consideration: Verify Consideration functionality [Contract Price $ should be shows in this fields]
	@Test(dataProvider = "Details_Section")
	public void Verify_in_Consideration_section_Consideration_field_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.Consideration(testData.get(1).get("Contract_Price"));
	}

	// Test Case 12149: HST: Verify HST functionality
	@Test(dataProvider = "Details_Section")
	public void Verify_in_HST_section_HST_functionality_working_proper_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.HST(testData.get(1).get("Contract_Price"));
	}

	// Test Case 12150: Real Estate Commissions: verify Broker field using Search REALTORS® across Canada functionality.
	@Test(dataProvider = "Details_Section")
	public void Verify_in_RealEstateCommissions_section_Broker_functionality_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.Broker(testData.get(1).get("Broker_LastName"));
	}

	// Test Case 12151: Real Estate Commissions: Verify % of Contract Price field.
	@Test(dataProvider = "Details_Section")
	public void Verify_in_RealEstateCommissions_section_Broker_contractPrice_functionality_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		ContractTab.Broker_ContractPrice(testData.get(1).get("Contract_Price"),
				testData.get(1).get("Broker_ContractPrice"));
	}

	
	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}

}
