package home.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PurchaseFile.BasicTabObject.Basic_Information;
import project.AbstractComponents.AbstractComponent;

public class FileList extends AbstractComponent {
	WebDriver driver;

	public FileList(WebDriver driver) {// constructor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// File->FileList PageFactory
	@FindBy(xpath = "//span[text()='New']")
	public WebElement NewOption;

	@FindBy(xpath = "(//span[contains(text(),'Create File')])[2]")
	public WebElement CreateFile_Button;

	@FindBy(xpath = "//div[@id='tdActiveFiles']/span[text()='Active']")
	public WebElement ActiveRadioOption;

	@FindBy(id = "(//input[@id='currentListFilter']")
	public WebElement ListFilter;

	@FindBy(xpath = "//div[@id='listActiveFiles']//tbody[@id='oneSingleList']/tr/td[@attrname='MatterNumber' and @subnodestatus='0']")
	List<WebElement> ActiveFiles;

	@FindBy(xpath = "(//span[@class='btn btn-inactive'][normalize-space()='Create File'])[1]")
	public WebElement Inactive_Button;

	
	By ActiveFilesBy = By.xpath(
			"//div[@id='listActiveFiles']//tbody[@id='oneSingleList']/tr/td[@attrname='MatterNumber' and @subnodestatus='0']");

	public String NewFileOption() throws InterruptedException {
		waitForWebElementToAppear(NewOption);
		NewOption.click();
		waitForWebElementToAppear(Inactive_Button);
		String Actual = Inactive_Button.getText();
		return Actual;
	}

	public void ActiveRadio() throws InterruptedException {
		waitForWebElementToAppear(ActiveRadioOption);
		ActiveRadioOption.click();

	}

	public List<WebElement> getFileList() {
		waitForElementToAppear(ActiveFilesBy);
		return ActiveFiles;

	}

	public WebElement GetFileName(String FileName) {
		WebElement prod = getFileList().stream().filter(product -> product.getText().equals(FileName)).findFirst()
				.orElse(null);
		return prod;
	}

	public Basic_Information OpenPurchaseFile(String FileName) throws InterruptedException {
		WebElement prod = GetFileName(FileName);
		prod.click();
		return new Basic_Information(driver);

	}
}
