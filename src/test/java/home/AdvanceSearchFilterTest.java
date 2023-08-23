package home;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import home.PageObject.Advance_Search_Filter;
import home.PageObject.Create_New_File;
import home.PageObject.FileList;
import project.TestComponents.BaseTest;

public class AdvanceSearchFilterTest extends BaseTest {

	private FileList launchApplicationAndLogin(HashMap<String, String> input) throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		return MainPageObject;
	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Cancel_button_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.CancelButton();

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_File_Types_Criteria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchFileTypes();

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_File_Status_Critria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchFileStatus();

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Responsible_Lawyer_Critria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchResponsibleLawyer(input.get("LawyerName"));

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Firm_Contact_Critria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchFirmContact(input.get("FirmContact"));

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Client_name_Criteria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchClientname(input.get("AdvanceSearch_Client1"), input.get("AdvanceSearch_Client2"),
				input.get("AdvanceSearch_Client3"));

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Reline_Criteria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		String[] singleRelineCriteriaArray = new String[] { input.get("AdvanceSearch_Reline") };
		AdvanceSearch.AdvanceSearchReline(singleRelineCriteriaArray);

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Closing_date_Criteria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchClosingDate(input.get("ClosingDate_After"), input.get("ClosingDate_Before"));

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Address_Criteria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);

		AdvanceSearch.AdvanceSearchAddress(input.get("Advancesearch_Address1"), input.get("Advancesearch_Address2"));

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Property_Type_Criteria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		String[] PropertyTypeArray = new String[] { input.get("Property_Type1"), input.get("Property_Type2") };
		AdvanceSearch.AdvanceSearchPropertyType(PropertyTypeArray, PropertyTypeArray);

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Lawyer_name_Criteria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		AdvanceSearch.AdvanceSearchLawyername(input.get("AdvanceSearch_LawyerName"));

	}

	@Test(dataProvider = "getData")
	public void Verify_In_Advance_search_Other_side_File_name_Criteria_working_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = launchApplicationAndLogin(input);
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		AdvanceSearch.AdvanceSearchOthersideFilenumber(input.get("AdvanceSearch_FileName"));

	}

}
