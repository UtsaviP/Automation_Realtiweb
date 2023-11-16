package details_section.Buyer_Seller_Side;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import details_section.PageObject.BUYER_SELLER_SIDE.Buyer_side;
import details_section.PageObject.CONTRACT.Basic;
import project.TestComponents.BaseTest;

public class Buyer_SideTest extends BaseTest {
	private Basic BasicTab;
	private Buyer_side Buyer_sideTab;

	@BeforeMethod
	public void initializeTest() {
		BasicTab = new Basic(driver);
		Buyer_sideTab = new Buyer_side(driver);
	}


	@Test(dataProvider = "Details_Section")
	public void Verify_Buyer_Side_all_Three_options_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		Buyer_sideTab.BuyerSide_options(testData.get(3).get("Individual_Name"), testData.get(3).get("Estate_Name"),
				testData.get(3).get("Corporation_Name"), testData.get(3));

	}
	
	
	@Test(dataProvider = "Details_Section")
	public void Verify_Move_Up_Move_Down_Delete_and_ID_Verification_icon_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		Buyer_sideTab.BuyerSideThreeDotsMenu();

	}
	
	
	
	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}
}
