package home;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import home.PageObject.Advance_Search_Filter;
import home.PageObject.Create_New_File;
import home.PageObject.FileList;
import project.TestComponents.BaseTest;

public class AdvanceSearchFilterTest extends BaseTest {

	// @Test(dataProvider = "getData")
	public void Verify_Cancel_Button_working_proper_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {

		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		MainPageObject = AdvanceSearch.CancelButton();

	}

	@Test(dataProvider = "getData")
	public void Verify_different_criteria_for_advanced_search_filter(HashMap<String, String> input)
			throws InterruptedException, IOException {

		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		// MainPageObject = AdvanceSearch.AdvanceSearchFileTypes();
		// MainPageObject = AdvanceSearch.AdvanceSearchFileStatus();
		//MainPageObject = AdvanceSearch.AdvanceSearchResponsibleLawyer(input.get("LawyerName"));
		//MainPageObject = AdvanceSearch.AdvanceSearchFirmContact(input.get("FirmContact"));
		//AdvanceSearch.AdvanceSearchClientname(input.get("AdvanceSearch_Client1"),input.get("AdvanceSearch_Client2"),input.get("AdvanceSearch_Client3"));
		//String[] singleRelineCriteriaArray = new String[] { input.get("AdvanceSearch_Reline") };
		//AdvanceSearch.AdvanceSearchReline(singleRelineCriteriaArray);
		//AdvanceSearch.AdvanceSearchClosingDate(input.get("ClosingDate_After"),input.get("ClosingDate_Before"));
		AdvanceSearch.AdvanceSearchAddress(input.get("Advancesearch_Address1"),input.get("Advancesearch_Address2"));
	}
}
