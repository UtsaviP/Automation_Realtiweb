package home;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PurchaseFile.BasicTabObject.Basic_Information;
import home.PageObject.Advance_Search_Filter;
import home.PageObject.Create_New_File;
import home.PageObject.FileList;
import project.TestComponents.BaseTest;

public class FileListTest extends BaseTest {

	

	@Test(dataProvider = "loginData")

	public void Verify_Archive_and_Activate_option_working_proper_or_not(HashMap<String, String> loginData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin(loginData);
		FileList filelist = new FileList(driver);
		filelist.ArchiveAndActiveOption();
		
	
	}


	@Test(dataProvider = "HomePageData")
	public void Verify_Free_search_Functionality_working_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		HashMap<String, String> loginData = testData.get(0);
		HashMap<String, String> freeSearchData = testData.get(2);
	    launchApplicationAndLogin(loginData);
	    FileList filelist = new FileList(driver);
	    filelist.FreeSearch(freeSearchData.get("FreeSearch_1"),freeSearchData.get("FreeSearch_2"));
	}
	
	
}
