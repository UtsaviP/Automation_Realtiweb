package PurchaseFile.BasicTabObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PurchaseFile.BasicTabObject.Basic_Information;
import home.PageObject.Create_New_File;
import home.PageObject.FileList;
import project.TestComponents.BaseTest;

public class Basic_InformationTest extends BaseTest {

	// String law1 = "DAVID RUSH";

	@Test(dataProvider = "getData")
	public void Verify_Basic_Information_Tab(HashMap<String, String> input) throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		MainPageObject.ActiveRadio();
		List<WebElement> FileList = MainPageObject.getFileList();
		Basic_Information Basic_Information = MainPageObject.OpenPurchaseFile(input.get("FileName"));

		Basic_Information BasicInfo = new Basic_Information(driver);
		BasicInfo.ResponsibleLawyer.click();
		List<WebElement> FileList1 = BasicInfo.getLawyerList();
		BasicInfo.FileDetails(input.get("LawyerName"));
		BasicInfo.FirmContact();
		BasicInfo.Conveyancer();
		
	}

}
