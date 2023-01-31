package menubar_File;

import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.Test;
import menubar_File.PageObject.Create_New_File;
import menubar_Main.PageObject.MainPage;
import project.TestComponents.BaseTest;

public class CreateNewFileTest extends BaseTest {

	 @Test(dataProvider = "getData")
	public void Verify_Create_New_Purchase_File(HashMap<String, String> input)
			throws InterruptedException, IOException {
		MainPage MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		MainPageObject = CreateNewFileObject.CreateNewPurchaseFile();

	}

	 @Test(dataProvider = "getData")
	public void Verify_Create_New_Sale_File(HashMap<String, String> input) throws InterruptedException, IOException {
		MainPage MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		MainPageObject = CreateNewFileObject.CreateNewSaleFile();

	}

	 @Test(dataProvider = "getData")
	public void Verify_Create_New_Mortgage_File(HashMap<String, String> input)
			throws InterruptedException, IOException {
		MainPage MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		MainPageObject = CreateNewFileObject.CreateNewMortgageFile();

	}

	@Test(dataProvider = "getData")
	public void Verify_Create_File_button_is_disable_or_not(HashMap<String, String> input)
			throws InterruptedException, IOException {
		MainPage MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.DisableCreateFileButton();
		CreateNewFileObject.EnableCreateFileButton();
	}
}
