package notes_and_pin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import details_section.PageObject.CONTRACT.Basic;
import notes_and_pin.PageObject.default_Pin;
import notes_and_pin.PageObject.notes;
import project.TestComponents.BaseTest;

public class Default_pinTest extends BaseTest {
	private Basic BasicTab;
	private default_Pin pin;

	@BeforeMethod
	public void initializeTest() {
		BasicTab = new Basic(driver);
		pin = new default_Pin(driver);
	}

	
	//Test Case 13815: verify pin option is available or not in Note, Activity center and Key dates
	@Test(dataProvider = "Right_Side_Navigation")
	public void  verify_pin_option_is_available_or_not(
			List<HashMap<String,String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		pin.verifyPinOptionAvailability();
	}
	
	
	
	//Test Case 13818: verify Pin feature working proper or not in Notes with logout functionality
	
	//@Test(dataProvider = "Right_Side_Navigation")
	public void  verify_pin_option_in_Notes_functionality(
			List<HashMap<String,String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		pin.pin_in_Notes();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		pin.after_pin_notes();
	}
	
	
	
	
	
	
	
	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		
	}

}
