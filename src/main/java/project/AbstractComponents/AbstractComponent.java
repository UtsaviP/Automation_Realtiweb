package project.AbstractComponents;

import java.beans.Statement;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import login.pageobject.LoginPage;
import menubar_Main.PageObject.MainPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\project\\resources\\GlobalData.properties");

		prop.load(fis);

		String ProvincesName = System.getProperty("Provinces") != null ? System.getProperty("Provinces")
				: prop.getProperty("Provinces");

		if (ProvincesName.contains("Ontario")) {
			Select Province = new Select(driver.findElement(By.id("ProductProvince")));
			Province.selectByVisibleText("Ontario");

		} else if (ProvincesName.contains("Alberta")) {
			Select Province = new Select(driver.findElement(By.id("ProductProvince")));
			Province.selectByVisibleText("Alberta");
			
		} else if (ProvincesName.contains("British Columbia")) {
			Select Province = new Select(driver.findElement(By.id("ProductProvince")));
			Province.selectByVisibleText("British Columbia");
			
		} else if (ProvincesName.contains("Manitoba")) {
			Select Province = new Select(driver.findElement(By.id("ProductProvince")));
			Province.selectByVisibleText("Manitoba");
			
		} else if (ProvincesName.contains("Nova Scotia")) {
			Select Province = new Select(driver.findElement(By.id("ProductProvince")));
			Province.selectByVisibleText("Nova Scotia");
			
		} else if (ProvincesName.contains("Saskatchewan")) {
			Select Province = new Select(driver.findElement(By.id("ProductProvince")));
			Province.selectByVisibleText("Saskatchewan");
		}

		return driver;
	}

	// Login Page
	public MainPage Login(String Account, String User, String Password) throws InterruptedException, IOException {
		LoginPage PageObject = new LoginPage(driver);
		PageObject.AccountName.sendKeys(Account);
		PageObject.UserName.sendKeys(User);
		PageObject.Password.sendKeys(Password);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver = initializeDriver();
		PageObject.LoginButton.click();
		return new MainPage(driver);
	}

	// Explicit wait
	public void waitForElementToAppear1(WebElement ele  ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By) ele));
	}
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	// Explicit wait
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	// Explicit wait
	public void waitForElementfToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

	public void JavascriptExecutor(WebElement ite) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ite);
	}

}