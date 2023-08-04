package home;

import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.Test;

import home.PageObject.Create_New_File;
import home.PageObject.FileList;
import project.TestComponents.BaseTest;

public class CreateNewFileTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void Verify_Create_New_Purchase_File(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		MainPageObject = CreateNewFileObject.CreateNewPurchaseFile();

	}

	@Test(dataProvider = "getData")
	public void Verify_Create_New_Sale_File(HashMap<String, String> input) throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		MainPageObject = CreateNewFileObject.CreateNewSaleFile();

	}

	@Test(dataProvider = "getData")
	public void Verify_Create_New_Mortgage_File(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		MainPageObject = CreateNewFileObject.CreateNewMortgageFile();

	}

	@Test(dataProvider = "getData")
	public void Verify_Create_button_should_be_Enable_or_Disable_based_on_condition(HashMap<String, String> input)
			throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.DisableCreateFileButton();
		CreateNewFileObject.EnableCreateFileButton();
	}
	
	
	@Test(dataProvider = "getData")
	public void  verify_warning_message_while_provide_same_name_that_already_exist_in_database(HashMap<String, String> input)
			throws InterruptedException, IOException {
 		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.existFilenameMessage(input.get("FileName"));
	
	}
	
	
	@Test(dataProvider = "getData")
	public void  verify_Fields_should_be_reflect_based_on_File_types(HashMap<String, String> input)
			throws InterruptedException, IOException {
 		FileList MainPageObject = landingPage.Login(input.get("Account"), input.get("User"), input.get("Password"));
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.DropdownVerificationTest();
}
}
