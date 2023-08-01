package PurchaseFile.BasicTabObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.netty.util.internal.ThreadLocalRandom;
import project.AbstractComponents.AbstractComponent;

public class Basic_Information extends AbstractComponent {
	WebDriver driver;

	public Basic_Information(WebDriver driver) {// constructor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Login Page PageFactory
	@FindBy(xpath = "//tr[@title='Lawyer responsible for this file']//i[@class='fa tool-icon ldd-list']")
	public WebElement ResponsibleLawyer;

	@FindBy(xpath = "(//input[@id='currentListFilter'])[1]")
	public WebElement LawyerFilter;

	@FindBy(xpath = "//tr[@class='SelectRow']//td[@class='SelectRow']")
	List<WebElement> LawyerList;

	By LawyerListBy = By.xpath("//tr[@class='SelectRow']//td[@class='SelectRow']");

	@FindBy(xpath = "//tbody[@id ='lookUpTBody']/tr[@class='SelectRow']/td[@class='SelectRow']")
	List<WebElement> list;

	By listby = By.xpath("//tbody[@id ='lookUpTBody']/tr[@class='SelectRow']/td[@class='SelectRow']");
	By Staff_Member_Name = By.xpath("//tr[@title='Enter the name of the Staff member.']//td//input");

	public List<WebElement> getLawyerList() throws InterruptedException {
		if (list.isEmpty()) {
			driver.switchTo().frame("dialog-body");
			// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("dialog-body"));

		}

		String t = driver.findElement(By.xpath("//tbody[@id ='lookUpTBody']/tr[@class='SelectRow']")).getText();
		System.out.println(t);
		return list;

	}

	public WebElement GetFileName(String LawyerName) throws InterruptedException {
		WebElement prod = getLawyerList().stream().filter(Lawyer -> Lawyer.getText().equals(LawyerName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void FileDetails(String LawyerName) throws InterruptedException {

		WebElement prod = GetFileName(LawyerName);
		prod.click();

	}

	public void FirmContact() throws InterruptedException {
		driver.findElement(By.xpath("//tr[@xmlbase='Staff']//i[@title='Edit Contact']")).click();
		// Thread.sleep(2000);
		driver.switchTo().frame("dialog-body");
		driver.findElement(By.xpath("//button[text()='Select Another']")).click();
		driver.findElement(By.xpath("//a[text()='+ Add']")).click();
		driver.findElement(By.xpath("//tr[@title='Enter the name of the Staff member.']//td//input")).click();
		
		
		int int_random = ThreadLocalRandom.current().nextInt();
		WebElement a = driver.findElement(Staff_Member_Name);
		a.sendKeys("FirmContact_" + int_random);
		String FirmnameText = a.getAttribute("value");
		driver.findElement(By.xpath("//button[@condition='document.all.AlwaysUpdateList==null'][text()='OK']")).click();
		String getText = driver.findElement(By.xpath("(//span[@class='input-inactive input-long'])[3]")).getText();
		Assert.assertEquals(getText, FirmnameText);
	}

	public void Conveyancer() {
		driver.findElement(By.xpath("//tr[@xmlbase='Conveyancer']//i[@title='Edit Contact']")).click();
		driver.switchTo().frame("dialog-body");
		driver.findElement(By.xpath("//button[text()='Select Another']")).click();
		driver.findElement(By.xpath("//a[text()='+ Add']")).click();
		int int_random = ThreadLocalRandom.current().nextInt();
		WebElement a = driver.findElement(Staff_Member_Name);
		a.sendKeys("Conveyancer_" + int_random);
		String FirmnameText = a.getAttribute("value");
		driver.findElement(By.xpath("//button[@condition='document.all.AlwaysUpdateList==null'][text()='OK']")).click();
		String getText = driver.findElement(By.xpath("(//span[@class='input-inactive input-long'])[4]")).getText();
		Assert.assertEquals(getText, FirmnameText);

	}

	public void File_Configuration() {
		
		
	}

}
