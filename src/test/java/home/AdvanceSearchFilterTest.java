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



	@Test(dataProvider = "getData")
	public void Verify_different_criteria_for_advanced_search_filter(HashMap<String, String> input)
			throws InterruptedException, IOException {

		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Advance_Search_Filter AdvanceSearch = new Advance_Search_Filter(driver);
		AdvanceSearch.CancelButton();
		AdvanceSearch.AdvanceSearchFileTypes();
		AdvanceSearch.AdvanceSearchFileStatus();
		AdvanceSearch.AdvanceSearchResponsibleLawyer(input.get("LawyerName"));
		AdvanceSearch.AdvanceSearchFirmContact(input.get("FirmContact"));
		AdvanceSearch.AdvanceSearchClientname(input.get("AdvanceSearch_Client1"), input.get("AdvanceSearch_Client2"),
				input.get("AdvanceSearch_Client3"));
		String[] singleRelineCriteriaArray = new String[] { input.get("AdvanceSearch_Reline") };
		AdvanceSearch.AdvanceSearchReline(singleRelineCriteriaArray);
		AdvanceSearch.AdvanceSearchClosingDate(input.get("ClosingDate_After"), input.get("ClosingDate_Before"));
		AdvanceSearch.AdvanceSearchAddress(input.get("Advancesearch_Address1"), input.get("Advancesearch_Address2"));
		String[] PropertyTypeArray = new String[] { input.get("Property_Type1"), input.get("Property_Type2") };
		AdvanceSearch.AdvanceSearchPropertyType(PropertyTypeArray, PropertyTypeArray);
		AdvanceSearch.AdvanceSearchLawyername(input.get("AdvanceSearch_LawyerName"));
		AdvanceSearch.AdvanceSearchOthersideFilenumber(input.get("AdvanceSearch_FileName"));

	}
}