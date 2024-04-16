package details_section.Contract;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import details_section.PageObject.CONTRACT.Basic;
import details_section.PageObject.CONTRACT.Property;
import project.TestComponents.BaseTest;

public class PropertyTest extends BaseTest {
	private Basic BasicTab;
	private Property PropertyTab;

	@BeforeMethod
	public void initializeTest() {
		BasicTab = new Basic(driver);
		PropertyTab = new Property(driver);
	}

   //Test Case 12225: Property Address: verify property address different fields and also verify in Basic tab
	@Test(dataProvider = "Details_Section")
	public void Verify_Property_address_different_fields_and_also_verify_in_Basic_tab(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		PropertyTab.Property_Address(testData.get(2).get("Street_Number"), testData.get(2).get("Street_Name"),
				testData.get(2).get("City"));
		
	}
	
	//Test Case 12227: Property Address: Verify Postal code functionality
	@Test(dataProvider = "Details_Section")
	public void Verify_PostalCode_In_Property_address_Section(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		PropertyTab.Postal_Code(testData.get(2).get("Postal_Code"));
		
	}
	
	
	//Test Case 12229: Property Description: Verify t/w Common Elements checkbox
	//Test Case 12228: Property Description: Verify based on Property type fields should be display.
	@Test(dataProvider = "Details_Section")
	public void Verify_In_Property_Section_Property_type_Dropdown_options(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		PropertyTab.Property_Type();
		
	}
	
	//Test Case 12230: Survey: Verify survey functionality based on Yes/No selection.
	@Test(dataProvider = "Details_Section")
		public void Verify_In_Survey_Section_Survey_options_Yes_or_No_working_proper_or_not(
				List<HashMap<String, String>> testData) throws InterruptedException, IOException {
			launchApplicationAndLogin();
			BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
			PropertyTab.Survey();
			
		}
		
	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}
}
