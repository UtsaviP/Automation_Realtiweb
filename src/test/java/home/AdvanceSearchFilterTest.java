package home;

import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.Test;
import home.PageObject.Advance_Search_Filter;
import project.TestComponents.BaseTest;

public class AdvanceSearchFilterTest extends BaseTest {

	@Test(dataProvider = "HomePageData")
	public void Verify_Cancel_button_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.CancelButton();
	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_File_Types_Criteria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		AdvanceSearch.AdvanceSearchFileTypes();
	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_File_Status_Critria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchFileStatus();

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Responsible_Lawyer_Critria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchResponsibleLawyer(advanceSearchData.get("LawyerName"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Firm_Contact_Critria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchFirmContact(advanceSearchData.get("FirmContact"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Client_name_Criteria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchClientname(advanceSearchData.get("AdvanceSearch_Client1"),
				advanceSearchData.get("AdvanceSearch_Client2"), advanceSearchData.get("AdvanceSearch_Client3"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Reline_Criteria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		String[] singleRelineCriteriaArray = new String[] { advanceSearchData.get("AdvanceSearch_Reline") };
		AdvanceSearch.AdvanceSearchReline(singleRelineCriteriaArray);

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Closing_date_Criteria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchClosingDate(advanceSearchData.get("ClosingDate_After"),
				advanceSearchData.get("ClosingDate_Before"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Address_Criteria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchAddress(advanceSearchData.get("Advancesearch_Address1"),
				advanceSearchData.get("Advancesearch_Address2"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Property_Type_Criteria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		String[] PropertyTypeArray = new String[] { advanceSearchData.get("Property_Type1"),
				advanceSearchData.get("Property_Type2") };
		AdvanceSearch.AdvanceSearchPropertyType(PropertyTypeArray, PropertyTypeArray);

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Lawyer_name_Criteria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		AdvanceSearch.AdvanceSearchLawyername(advanceSearchData.get("AdvanceSearch_LawyerName"));

	}

	@Test(dataProvider = "HomePageData")
	public void Verify_In_Advance_search_Other_side_File_name_Criteria_working_or_not(HashMap<String, String> loginData,
			HashMap<String, String> advanceSearchData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		AdvanceSearch.AdvanceSearchOthersideFilenumber(advanceSearchData.get("AdvanceSearch_FileName"));

	}

}
