package PurchaseFile.BasicTabObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PurchaseFile.BasicTabObject.Basic_Information;
import menubar_File.PageObject.Create_New_File;
import menubar_Main.PageObject.MainPage;
import project.TestComponents.BaseTest;

public class Basic_InformationTest extends BaseTest {

	// String FileName = "AutomationFile_[Do not Delete]";

	@Test(dataProvider = "getData")
	public void Verify_Basic_Information_Tab(HashMap<String, String> input) throws InterruptedException, IOException {
		MainPage MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		MainPageObject.ActiveRadio();
		List<WebElement> FileList = MainPageObject.getFileList();
		Basic_Information Basic_Information = MainPageObject.OpenPurchaseFile(input.get("FileName"));
		
		Basic_Information BasicInfo = new Basic_Information(driver);
		BasicInfo.FIletest();		
		List<WebElement> FileList1 = BasicInfo.getLawyerList();
		BasicInfo.FileDetails(input.get("LawyerName"));
		
	}

}
;