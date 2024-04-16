package home;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import home.PageObject.Advance_Search_Filter;
import project.TestComponents.BaseTest;

public class AdvanceSearchFilterTest extends BaseTest {

	private Advance_Search_Filter AdvanceSearch;

	@BeforeMethod
	public void initializeTest() {

		AdvanceSearch = new Advance_Search_Filter(driver);
	}

	//Test Case 12064: Verify Cancel Button working proper or not
	@Test(dataProvider = "HomePageData")
	public void Verify_Cancel_button_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.CancelButton();
	}

	//Test Case 12068: Verify Search with File Types Purchase and Sale
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_File_Types_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchFileTypes();
	}

	//Test Case 12069: Verify File Status Active and Archives working or not
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_File_Status_Critria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchFileStatus();
	}

	//Test Case 12070: Verify Responsible Lawyer working proper or not
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Responsible_Lawyer_Critria_working_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchResponsibleLawyer(testData.get(1).get("LawyerName"));

	}

	//Test Case 12078: Verify firm contact working or not
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Firm_Contact_Critria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchFirmContact(testData.get(1).get("FirmContact"));

	}

	//Test Case 12071: Verify Client Name Functionality with single name , multiple name and Estate name
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Client_name_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchClientname(testData.get(1).get("AdvanceSearch_Client1"),
				testData.get(1).get("AdvanceSearch_Client2"), testData.get(1).get("AdvanceSearch_Client3"));

	}

	//Test Case 12072: verify Reline Include and Doesn't include criteria working or not
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Reline_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		String[] singleRelineCriteriaArray = new String[] { testData.get(1).get("AdvanceSearch_Reline") };
		AdvanceSearch.AdvanceSearchReline(singleRelineCriteriaArray);

	}

	//Test Case 12073: verify closing date After and before working proper or not
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Closing_date_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchClosingDate(testData.get(1).get("ClosingDate_After"),
				testData.get(1).get("ClosingDate_Before"));

	}

	
	//Test Case 12074: verify property address working proper or not search with Full address and only Postal Code
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Address_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchAddress(testData.get(1).get("Advancesearch_Address1"),
				testData.get(1).get("Advancesearch_Address2"));

	}

	
	//Test Case 12075: verify property type[Freehold & Condominium] working proper or not
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Property_Type_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		String[] PropertyTypeArray = new String[] { testData.get(1).get("Property_Type1"),
				testData.get(1).get("Property_Type2") };
		AdvanceSearch.AdvanceSearchPropertyType(PropertyTypeArray, PropertyTypeArray);

	}

	
	//Test Case 12076: verify other side info. > Lawyer name working or not
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Lawyer_name_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchLawyername(testData.get(1).get("AdvanceSearch_LawyerName"));

	}

	
	//Test Case 12077: verify other side info. > File name working or not
	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Other_side_File_name_Criteria_working_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchOthersideFilenumber(testData.get(1).get("AdvanceSearch_FileName"));

	}

	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}
}
