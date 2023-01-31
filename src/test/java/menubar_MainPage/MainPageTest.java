package menubar_MainPage;

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

public class MainPageTest extends BaseTest {

	// String FileName = "AutomationFile_[Do not Delete]";

	@Test(dataProvider = "getData")
	public void Verify_Create_New__File_Option(HashMap<String, String> input) throws InterruptedException, IOException {
		MainPage MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));

		String Actual =MainPageObject.NewFileOption();
		Create_New_File File = new Create_New_File(driver);
		String expected = File.Inactive_Button.getText();
		Assert.assertEquals(Actual, expected);

	}

	@Test(dataProvider = "getData")
	public void Verify_Purchase_File_is_open_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		MainPage MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));

		MainPageObject.ActiveRadio();
		List<WebElement> FileList = MainPageObject.getFileList();
		Basic_Information Basic_Information = MainPageObject.OpenPurchaseFile(input.get("FileName"));
       
	}
}
