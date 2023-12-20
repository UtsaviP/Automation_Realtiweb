package details_section.Additional_Parties;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import details_section.PageObject.Additional_Parties.Additional_Parties;
import details_section.PageObject.CONTRACT.Basic;
import project.TestComponents.BaseTest;

public class Additional_PartiesTest extends BaseTest {
	private Basic BasicTab;
	private Additional_Parties Additional_PartiesTab;

	@BeforeMethod
	public void initializeTest() {
		BasicTab = new Basic(driver);
		Additional_PartiesTab = new Additional_Parties(driver);
	}


	

	//@Test(dataProvider = "Details_Section")
	public void Verify_additional_parties_different_options_and_update_list_button_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
	    Additional_PartiesTab.selectPartyAndVerify();
	}

	
	@Test(dataProvider = "Details_Section")
	public void Verify_additional_parties_Search_functionality_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
	
	    Additional_PartiesTab.AdditionalParties_Search(testData.get(5).get("Search_name"));
	    
	}
	
	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}
}
