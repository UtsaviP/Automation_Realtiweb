package home;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import home.PageObject.Advance_Search_Filter;
import home.PageObject.Create_New_File;
import home.PageObject.FileList;
import project.TestComponents.BaseTest;

public class FileListTest extends BaseTest {

	private FileList filelist;

	@BeforeMethod
	public void initializeTest() {

		filelist = new FileList(driver);
	}

	
	
	//Test Case 12067: In three dots menu ,Verify archive and active option working proper or not
	@Test(dataProvider = "loginData")
	public void Verify_Archive_and_Activate_option_working_proper_or_not(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		filelist.ArchiveAndActiveOption();

	}

	//Test Case 12066: Verify the free search functionality and check if the bold name exactly matches the searchable name
	@Test(dataProvider = "HomePageData")
	public void Verify_Free_search_Functionality_working_or_not(List<HashMap<String, String>> testData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		filelist.FreeSearch(testData.get(2).get("FreeSearch_1"), testData.get(2).get("FreeSearch_2"));
	}

	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}

}
