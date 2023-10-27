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

	@Test(dataProvider = "HomePageData")
	public void Verify_Cancel_button_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.CancelButton();
	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_File_Types_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchFileTypes();
	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_File_Status_Critria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchFileStatus();
	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Responsible_Lawyer_Critria_working_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchResponsibleLawyer(testData.get(1).get("LawyerName"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Firm_Contact_Critria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchFirmContact(testData.get(1).get("FirmContact"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Client_name_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchClientname(testData.get(1).get("AdvanceSearch_Client1"),
				testData.get(1).get("AdvanceSearch_Client2"), testData.get(1).get("AdvanceSearch_Client3"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Reline_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		String[] singleRelineCriteriaArray = new String[] { testData.get(1).get("AdvanceSearch_Reline") };
		AdvanceSearch.AdvanceSearchReline(singleRelineCriteriaArray);

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Closing_date_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchClosingDate(testData.get(1).get("ClosingDate_After"),
				testData.get(1).get("ClosingDate_Before"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Address_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchAddress(testData.get(1).get("Advancesearch_Address1"),
				testData.get(1).get("Advancesearch_Address2"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Property_Type_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		String[] PropertyTypeArray = new String[] { testData.get(1).get("Property_Type1"),
				testData.get(1).get("Property_Type2") };
		AdvanceSearch.AdvanceSearchPropertyType(PropertyTypeArray, PropertyTypeArray);

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Lawyer_name_Criteria_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		AdvanceSearch.AdvanceSearchLawyername(testData.get(1).get("AdvanceSearch_LawyerName"));

	}

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
