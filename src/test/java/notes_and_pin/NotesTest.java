
package  notes_and_pin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import details_section.PageObject.CONTRACT.Basic;
import notes_and_pin.PageObject.notes;
import project.TestComponents.BaseTest;

public class NotesTest extends BaseTest {
	private Basic BasicTab;
	private notes note;

	@BeforeMethod
	public void initializeTest() {
		BasicTab = new Basic(driver);
		note = new notes(driver);
	}

	//Test Case 13702: Verify Add notes functionality working or not
	////Test Case 13705: verify Edit notes working or not
	//@Test(dataProvider = "Right_Side_Navigation")
	public void Verify_Add_notes_functionality_working_or_not(
			List<HashMap<String,String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		note.Add_Edit_notes(testData.get(0).get("Add_Text"),testData.get(0).get("Edit_Text"));
	}
	
	
	//Test Case 13706: verify Reply functionality working or not
	//@Test(dataProvider = "Right_Side_Navigation")
	public void verify_Reply_note_functionality_working_or_not(
			List<HashMap<String,String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		note.reply_notes(testData.get(0).get("Add_Text"),testData.get(0).get("Reply_Text"));
	}
	
	
	//Test Case 13708: verify cancel button working or not
	//@Test(dataProvider = "Right_Side_Navigation")
	public void verify_cancel_button_working_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));

		note.cancel_button();
	    
	}
	
	//Test Case 13707: verify remove notes fnctionality working or not	
	//@Test(dataProvider = "Right_Side_Navigation")
		public void verify_remove_note_working_or_not(
				List<HashMap<String, String>> testData) throws InterruptedException, IOException {
			
			launchApplicationAndLogin();
			BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));

			note.remove_note(testData.get(0).get("Add_Text"));
		    
		}
	
	//Test Case 13703: Verify added notes in other dropdown option are showing in ALL section or not
		@Test(dataProvider = "Right_Side_Navigation")
		public void verify_badge_dropdown_working_or_not(
				List<HashMap<String, String>> testData) throws InterruptedException, IOException {
			
			launchApplicationAndLogin();
			BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));

			note.badge_dropdown(testData.get(0).get("Add_Text"));
		    
		}
		
	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
		
	}
}
