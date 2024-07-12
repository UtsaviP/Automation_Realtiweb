package project.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import home.PageObject.FileList;
import io.github.bonigarcia.wdm.WebDriverManager;

import project.AbstractComponents.AbstractComponent;

public class BaseTest {

	public WebDriver driver;// globally
	public AbstractComponent landingPage;

	public WebDriver initializeDriver() throws IOException {
		// Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\project\\resources\\GlobalData.properties");
		prop.load(fis);

		// To run Test with different browser using Maven
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			
			
			// Setup ChromeDriver using WebDriverManager
			WebDriverManager.chromedriver().browserVersion("123.0.6312.106").setup(); 
			 driver = new ChromeDriver();
             
				
			driver.manage().window().setSize(new Dimension(1440, 900));// to run application in full screen
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();// firefoxdriver will be downloaded automatically
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();// edgedriver will be downloaded automatically
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	// Screenshot for failed test case
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public AbstractComponent launchApplication() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\project\\resources\\GlobalData.properties"); // convert

		prop.load(fis);
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		landingPage = new AbstractComponent(driver);
		return landingPage; // return landingPage object so that it can be catch in test case
	}

	// data provider for login page
	@DataProvider(name = "loginData")
	public Object[][] loginData() throws Exception {
		JsonObject jsonData = JsonParser.parseReader(new FileReader("src\\test\\java\\project\\data\\HomePage.json"))
				.getAsJsonObject();
		JsonObject loginPageData = jsonData.getAsJsonObject("loginPageData");

		HashMap<String, String> loginDataMap = convertJsonObjectToHashMap(loginPageData);

		return new Object[][] { { loginDataMap } };
	}

	// data provider for Home page
	@DataProvider(name = "HomePageData")
	public Object[][] fullTestData() throws Exception {
		JsonObject jsonData = JsonParser.parseReader(new FileReader("src\\test\\java\\project\\data\\HomePage.json"))
				.getAsJsonObject();
		JsonObject advanceSearchData = jsonData.getAsJsonObject("advanceSearchData");
		JsonObject loginPageData = jsonData.getAsJsonObject("loginPageData");
		JsonObject freeSearchData = jsonData.getAsJsonObject("freeSearchData");
		HashMap<String, String> loginDataMap = convertJsonObjectToHashMap(loginPageData);
		HashMap<String, String> advanceSearchDataMap = convertJsonObjectToHashMap(advanceSearchData);
		HashMap<String, String> freeSearchDataMap = convertJsonObjectToHashMap(freeSearchData);

		List<HashMap<String, String>> data = new ArrayList<>();
		data.add(loginDataMap);
		data.add(advanceSearchDataMap);
		data.add(freeSearchDataMap);

		return new Object[][] { { data } };
	}

	
	//Data provider for Details Section
	@DataProvider(name = "Details_Section")
	public Object[][] PurchaseDetailsSection() throws Exception {
		try {
			JsonObject jsonData = JsonParser
					.parseReader(new FileReader("src\\test\\java\\project\\data\\Details_Section.json"))
					.getAsJsonObject();
			//Contract
			JsonObject BasicTabData = jsonData.getAsJsonObject("CONTRACT").getAsJsonObject("Basic");
			JsonObject ContractTabData = jsonData.getAsJsonObject("CONTRACT").getAsJsonObject("Contract");
			JsonObject PropertyTabData = jsonData.getAsJsonObject("CONTRACT").getAsJsonObject("Property");
			
			//Buyer-Seller side
			JsonObject buyersideData = jsonData.getAsJsonObject("BUYER_SELLER_SIDE").getAsJsonObject("BuyerSide");
			JsonObject SellersideData = jsonData.getAsJsonObject("BUYER_SELLER_SIDE").getAsJsonObject("SellerSide");
			
			//Additional parties
			JsonObject AdditionalPartiesData = jsonData.getAsJsonObject("ADDITIONAL_PARTIES").getAsJsonObject("RealEstateBroker_Search");
			
			HashMap<String, String> BasicDataMap = convertJsonObjectToHashMap(BasicTabData);
			HashMap<String, String> ContractDataMap = convertJsonObjectToHashMap(ContractTabData);
			HashMap<String, String> PropertyDataMap = convertJsonObjectToHashMap(PropertyTabData);
			
			HashMap<String, String> buyersideDataMap = convertJsonObjectToHashMap(buyersideData);
			HashMap<String, String> SellersideDataMap = convertJsonObjectToHashMap(SellersideData);
			
			HashMap<String, String> AdditionalPartiesDataMap = convertJsonObjectToHashMap(AdditionalPartiesData);
			
			List<HashMap<String, String>> data = new ArrayList<>();
			data.add(BasicDataMap);
			data.add(ContractDataMap);
			data.add(PropertyDataMap);
			data.add(buyersideDataMap);
			data.add(SellersideDataMap);
			data.add(AdditionalPartiesDataMap);
			return new Object[][] { { data } };
		} catch (Exception e) {
			e.printStackTrace();
			// Handle the exception or rethrow it as needed.
			throw e;
		}
	}


	
	
	//Data provider for Right Side Navigation inside file
		@DataProvider(name = "Right_Side_Navigation")
		public Object[][] RightSideNavigation1() throws Exception {
			JsonObject jsonData = JsonParser.parseReader(new FileReader("src\\test\\java\\project\\data\\Right_Side_Navigation.json"))
					.getAsJsonObject();
			JsonObject notesData = jsonData.getAsJsonObject("notes");
			
			HashMap<String, String> notesDataMap = convertJsonObjectToHashMap(notesData);
			

			List<HashMap<String, String>> data = new ArrayList<>();
			data.add(notesDataMap);
			
			return new Object[][] { { data } };
		}
	
	
	
	
	
	
	protected HashMap<String, String> convertJsonObjectToHashMap(JsonObject jsonObject) {
		HashMap<String, String> hashMap = new HashMap<>();
		for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			hashMap.put(entry.getKey(), entry.getValue().getAsString());
		}
		return hashMap;
	}

	
	@Test(dataProvider = "testData", groups = "loginData")
	public FileList launchApplicationAndLogin(HashMap<String, String> loginData)
			throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(loginData.get("Account"), loginData.get("User"),
				loginData.get("Password"));
		return MainPageObject;
	}

	
	// Fetch login data from the loginData data provider and store it	
	// Define a login method
//public void login(HashMap<String, String> loginData) throws InterruptedException, IOException {
//launchApplicationAndLogin(loginData);
	//}
	
	
	public HashMap<String, String> loginData; // Class-level variable to store login data	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void setupLoginData() {
		Object[][] loginDataArray = null;
		try {
			loginDataArray = loginData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (loginDataArray != null && loginDataArray.length > 0) {
			loginData = (HashMap<String, String>) loginDataArray[0][0];
		}
	}

	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
	    try {
	        if (driver != null) {
	            // Switch to the default content
	            driver.switchTo().defaultContent();

	            // Attempt to locate the element with initials
	            WebElement initialsElement = null;
	            try {
	                initialsElement = driver.findElement(By.xpath("//span[@id='intitials']"));
	            } catch (NoSuchElementException ignored) {
	                
	            }

	            if (initialsElement != null && initialsElement.isDisplayed()) {
	                // If the element is visible, click it
	                initialsElement.click();
	                Thread.sleep(2000);
	                driver.findElement(By.xpath("//button[text()='Log Out']")).click();

	                // Check if an alert is present
	                try {
	                    Alert alert = driver.switchTo().alert();
	                    alert.accept();
	                } catch (NoAlertPresentException e) {
	                    // No alert is present, proceed to close the browser
	                }
	            }
	        }
	    } catch (TimeoutException e) {
	        
	    } catch (Exception innerException) {
	        System.out.println("Error while handling the alert: " + innerException.getMessage());
	    } finally {
	        // Quit the driver
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}
}

