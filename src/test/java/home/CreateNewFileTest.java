package home;

import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.Test;
import home.PageObject.Create_New_File;
import project.TestComponents.BaseTest;

public class CreateNewFileTest extends BaseTest {

	@Test(dataProvider = "loginData")

	public void Verify_Create_New_Purchase_File(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.CreateNewPurchaseFile();
	}

	@Test(dataProvider = "loginData")
	public void Verify_Create_New_Sale_File(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.CreateNewSaleFile();

	}

	@Test(dataProvider = "loginData")
	public void Verify_Create_New_Mortgage_File(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.CreateNewMortgageFile();

	}

	@Test(dataProvider = "loginData")
	public void Verify_Create_button_should_be_Enable_or_Disable_based_on_condition(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.DisableCreateFileButton();
		CreateNewFileObject.EnableCreateFileButton();
	}

	@Test(dataProvider = "loginData")
	public void verify_warning_message_while_provide_same_name_that_already_exist_in_database(
			HashMap<String, String> loginData) throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.existFilenameMessage(loginData.get("Purchase_FileName"));

	}

	@Test(dataProvider = "loginData")
	public void verify_Fields_should_be_reflect_based_on_File_types(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		Create_New_File CreateNewFileObject = new Create_New_File(driver);
		CreateNewFileObject.DropdownVerificationTest();
	}
}
