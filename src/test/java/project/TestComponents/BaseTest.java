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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
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
import java.util.Set;

import com.google.gson.Gson;
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

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();

			WebDriverManager.chromedriver().setup();

			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
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
	
	
	/* @DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\project\\data\\LDDCredential.json");

		return new Object[][] { { data.get(0) } };
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// Read Json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert String to HashMap using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

*/
	//Data Provider
	/*@DataProvider(name = "testData")
	public Object[][] testData() throws Exception {
	    JsonObject jsonData = JsonParser.parseReader(new FileReader("src\\test\\java\\project\\data\\HomePage.json")).getAsJsonObject();
	    JsonObject advanceSearchData = jsonData.getAsJsonObject("advanceSearchData");
	    JsonObject loginPageData = jsonData.getAsJsonObject("loginPageData");
	    HashMap<String, String> testData_search = convertJsonObjectToHashMap(advanceSearchData);
	    HashMap<String, String> testData_login = convertJsonObjectToHashMap(loginPageData);
	    return new Object[][]{{testData_search,testData_login}};
	}

	private HashMap<String, String> convertJsonObjectToHashMap(JsonObject jsonObject) {
	    HashMap<String, String> hashMap = new HashMap<>();
	    for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
	        hashMap.put(entry.getKey(), entry.getValue().getAsString());
	    }
	    return hashMap;
	}*/
	
	/*@Test(dataProvider = "testData", groups = "loginPageData")
	public FileList launchApplicationAndLogin(HashMap<String, String> input2)
			throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(input2.get("Account"), input2.get("User"), input2.get("Password"));
		return MainPageObject;
	}*/
	
	@DataProvider(name = "loginData")
	public Object[][] loginData() throws Exception {
	    JsonObject jsonData = JsonParser.parseReader(new FileReader("src\\test\\java\\project\\data\\HomePage.json")).getAsJsonObject();
	    JsonObject loginPageData = jsonData.getAsJsonObject("loginPageData");
	    
	    HashMap<String, String> loginDataMap = convertJsonObjectToHashMap(loginPageData);
	    
	    return new Object[][]{{loginDataMap}};
	}

	@DataProvider(name = "HomePageData")
	public Object[][] fullTestData() throws Exception {
	    JsonObject jsonData = JsonParser.parseReader(new FileReader("src\\test\\java\\project\\data\\HomePage.json")).getAsJsonObject();
	    JsonObject advanceSearchData = jsonData.getAsJsonObject("advanceSearchData");
	    JsonObject loginPageData = jsonData.getAsJsonObject("loginPageData");
	    
	    HashMap<String, String> loginDataMap = convertJsonObjectToHashMap(loginPageData);
	    HashMap<String, String> advanceSearchDataMap = convertJsonObjectToHashMap(advanceSearchData);
	    
	    return new Object[][]{{loginDataMap, advanceSearchDataMap}};
	}

	protected HashMap<String, String> convertJsonObjectToHashMap(JsonObject jsonObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().getAsString());
        }
        return hashMap;
    }
	
	
	
	@Test(dataProvider = "testData", groups = "loginData")
	public FileList launchApplicationAndLogin(HashMap<String, String> loginData)throws InterruptedException, IOException {
		FileList MainPageObject = landingPage.Login(loginData.get("Account"), loginData.get("User"), loginData.get("Password"));
		return MainPageObject;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}

}
