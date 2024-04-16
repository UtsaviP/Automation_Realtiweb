package home;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import home.PageObject.Advance_Search_Filter;
import home.PageObject.Create_New_File;
import project.TestComponents.BaseTest;

public class CreateNewFileTest extends BaseTest {
	

	private Create_New_File CreateFile;

	@BeforeMethod
	public void initializeTest() {

		CreateFile = new Create_New_File(driver);
	}
	
	
	//Test Case 12048: Verify Purchase File created Successfully or not
	@Test(dataProvider = "loginData")
	public void Verify_Create_New_Purchase_File(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();		
		CreateFile.CreateNewPurchaseFile();
	}

	//Test Case 12049: Verify Sale File created Successfully or not
	@Test(dataProvider = "loginData")
	public void Verify_Create_New_Sale_File(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();		
		CreateFile.CreateNewSaleFile();

	}

	//Test Case 12050: Verify Mortgage File created Successfully or not
	@Test(dataProvider = "loginData")
	public void Verify_Create_New_Mortgage_File(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();	
		CreateFile.CreateNewMortgageFile();

	}
	
    //Test Case 12051: Create button should be Disable if file types and File name not provide
    //Test Case 12052: Create button should be Enable if file types and File name are provide
	@Test(dataProvider = "loginData")
	public void Verify_Create_button_should_be_Enable_or_Disable_based_on_condition(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();
		
		CreateFile.DisableCreateFileButton();
		CreateFile.EnableCreateFileButton();
	}

	//Test Case 12054: verify warning message while provide same name that already exist in database
	@Test(dataProvider = "loginData")
	public void verify_warning_message_while_provide_same_name_that_already_exist_in_database(
			HashMap<String, String> loginData) throws InterruptedException, IOException {
		launchApplicationAndLogin();		
		CreateFile.verifyWarningForDuplicateFileName(loginData.get("Purchase_FileName"));

	}

	//Test Case 12053: Fields should be reflect based on File types
	@Test(dataProvider = "loginData")
	public void verify_Fields_should_be_reflect_based_on_File_types(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin();		
		CreateFile.DropdownVerificationTest();
	}
	
	
	
	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}
}
