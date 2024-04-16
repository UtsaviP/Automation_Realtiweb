package details_section.Buyer_Seller_Side;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import details_section.PageObject.BUYER_SELLER_SIDE.Seller_Side;
import details_section.PageObject.CONTRACT.Basic;
import project.TestComponents.BaseTest;

public class Seller_SideTest extends BaseTest {
	private Basic BasicTab;
	private Seller_Side Seller_SideTab;

	@BeforeMethod
	public void initializeTest() {
		BasicTab = new Basic(driver);
		Seller_SideTab = new Seller_Side(driver);
	}


	//Test Case 12393: Seller Side>Verify Individual ,Corporation and Estate options working proper or not
	@Test(dataProvider = "Details_Section")
	public void Verify_Seller_Side_all_Three_options_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		Seller_SideTab.SellerSide_options(testData.get(3).get("Individual_Name"), testData.get(3).get("Estate_Name"),
				testData.get(3).get("Corporation_Name"), testData.get(3));
	}
	
	
	//Test Case 12395: Seller Side>verify Move Up ,Move Down options in three dots menu
	//Test Case 12396: Seller Side>Verify Remove option in three dots menu.
	@Test(dataProvider = "Details_Section")
	public void Verify_In_Seller_Side_Move_Up_Move_Down_Delete_and_ID_Verification_icon_working_proper_or_not(
			List<HashMap<String, String>> testData) throws InterruptedException, IOException {
		
		launchApplicationAndLogin();
		BasicTab.PurchaseFileClick(loginData.get("Purchase_FileName"));
		Seller_SideTab.SellerSideThreeDotsMenu();

	}
		
	private void launchApplicationAndLogin() throws InterruptedException, IOException {
		launchApplicationAndLogin(loginData);
	}
}

