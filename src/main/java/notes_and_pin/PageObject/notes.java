package notes_and_pin.PageObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import details_section.PageObject.CONTRACT.Basic;
import details_section.PageObject.CONTRACT.Contract;
import project.AbstractComponents.AbstractComponent;
import project.AbstractComponents.AzureDevOpsIntegration;
import project.AbstractComponents.CommonFuncs;

public class notes extends AbstractComponent {
	WebDriver driver;

	CommonFuncs common;
	AzureDevOpsIntegration Azure;
	Basic Basic_tab;
	Contract Contract_tab;

	public notes(WebDriver driver) {
		super(driver);
		this.driver = driver;
		common = new CommonFuncs(driver); // Initialize CommonFuncs
		Azure = new AzureDevOpsIntegration(); // Initialize AzureDevOpsIntegration
		Basic_tab = new Basic(driver);
		Contract_tab = new Contract(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//i[@class='fa-duotone fa-notes'])")
	public WebElement notes_navigate;

	@FindBy(xpath = "//button[@title='Add Note']//i[@class='fa-regular fa-plus']")
	public WebElement add_icon;

	@FindBy(xpath = "//textarea[@placeholder='Add new note here..']")
	public WebElement addnote_Textarea;

	@FindBy(xpath = "//textarea[@placeholder='Add reply here..']")
	public WebElement replynote_Textarea;

	@FindBy(xpath = "//textarea[@class='form-control']")
	public WebElement Editnote_Textarea;

	@FindBy(xpath = "//button[text()='Add']")
	public WebElement add_button;

	@FindBy(xpath = "//a[text()='Remove']")
	public WebElement Remove_note;

	@FindBy(xpath = "//div[contains(text(),'Note Removed')]")
	public WebElement Removenote_message;

	@FindBy(xpath = "//button[text()='Cancel']")
	public WebElement Cancel_button;

	@FindBy(xpath = "(//div[contains(@class,'col-md-auto')])/following-sibling::div/label")
	public WebElement date_time;

	@FindBy(xpath = "//button[text()='+ Reply']")
	public WebElement reply_note;

	@FindBy(xpath = "//i[contains(@class,'fa-solid fa-ellipsis note-options')]")
	public WebElement ellipsis_menu;

	@FindBy(xpath = "//div[contains(@class,'note-container note-container-hidden')]")
	public WebElement After_notes_add;

	@FindBy(xpath = "//a[text()='Edit']")
	public WebElement Edit_note;

	@FindBy(xpath = "//button[text()='Update']")
	public WebElement update_button;

	@FindBy(xpath = "//button[text()='Reply']")
	public WebElement reply_button;

	@FindBy(xpath = "//div[@class='note-reply']//div[@class='note-container']")
	public WebElement after_note_reply;

	@FindBy(xpath = "//i[@class='fa-solid fa-caret-down']")
	public WebElement show_dropdown;

	@FindBy(xpath = "//a[text()='Mortgage']")
	public WebElement dropdown_mortgage;

	@FindBy(xpath = "//a[text()='All']")
	public WebElement dropdown_All;

	By File_Details = By.xpath("//h2[text()='File Details']");

	// Test Case 13702: Verify Add notes functionality working or not
	// Test Case 13705: verify Edit notes working or not
	public void Add_Edit_notes(String Add_Text, String Edit_Text) throws IOException {
		try {
			// Add note
			add_note(Add_Text);

			// Edit note
			ellipsis_menu.click();
			Edit_note.click();
			Editnote_Textarea.clear();
			Editnote_Textarea.sendKeys(Edit_Text);
			update_button.click();
			Thread.sleep(2000);

			// Verify Edit note functionality
			String getEditnotetext = After_notes_add.getText();
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US);
			String Edittime = "(edited)";
			String formattedDateTime = now.format(formatter).toLowerCase() + "" + Edittime;
			if (getEditnotetext.equals(Edit_Text)) {
				Azure.updateTestCaseStatus("13705", "Automation Pass", "");
				System.out.println("*****Pass: Edit Note functionality working fine*****");

				// Verify date and time format after edit
				String getdatetime = date_time.getText();
				if (getdatetime.equals(formattedDateTime)) {
					Azure.updateTestCaseStatus("13704", "Automation Pass", "");
					System.out.println("*****Pass: Date and time format after edit is correct*****");
				} else {
					handleTestFailure("13704", "Date and time format after edit is incorrect");
				}
			} else {
				handleTestFailure("13705", "Edit Note functionality is not working fine");
			}

		} catch (Exception e) {
			handleException("13702", e);
		}
	}

	// only add note functionality
	public void only_add_note(String Add_Text) throws IOException {
		try {
			common.switchToIframe(common.MiddlePortionFrame);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the wait time as needed
			wait.until(ExpectedConditions.presenceOfElementLocated(File_Details));
			driver.switchTo().defaultContent();
			notes_navigate.click();
			add_icon.click();
			addnote_Textarea.sendKeys(Add_Text);
			add_button.click();
		} catch (Exception e) {
			handleException("13702", e);

		}

	}

	public void add_note(String Add_Text) throws IOException {
		try {

			only_add_note(Add_Text);

			// Verify Add note functionality
			String getnotetext = After_notes_add.getText();
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US);
			String formattedDateTime = now.format(formatter).toLowerCase();
			if (getnotetext.equals(Add_Text)) {
				Azure.updateTestCaseStatus("13702", "Automation Pass", "");
				System.out.println("*****Pass: Add Note functionality working fine*****");
				// Verify date and time format after add
				String getdatetime = date_time.getText();
				if (getdatetime.equals(formattedDateTime)) {
					Azure.updateTestCaseStatus("13702", "Automation Pass", "");
				} else {
					handleTestFailure("13704", "Date and time format after add is incorrect");
				}

			} else {
				handleTestFailure("13702", "Add Note functionality is not working fine");
			}

		} catch (Exception e) {
			handleException("13702", e);
		}
	}

	private void handleTestFailure(String testCaseNumber, String errorMessage) throws IOException {
		Azure.updateTestCaseStatus(testCaseNumber, "Automation Fail", errorMessage);
		Assert.fail("****Fail: " + errorMessage + "*****");
	}

	private void handleException(String testCaseNumber, Exception e) throws IOException {
		String exceptionTitle = e.getMessage().split("\n")[0];
		Azure.updateTestCaseStatus(testCaseNumber, "Automation Error", exceptionTitle);
		Assert.fail(
				"*****Fail: An exception occurred while testing Add , Edit and Datetime in  notes Functionality.*****",
				e);
	}

	// Test Case 13706: verify Reply functionality working or not
	public void reply_notes(String Add_Text, String Reply_Text) throws IOException {
		try {

			add_note(Add_Text);
			reply_note.click();
			replynote_Textarea.sendKeys(Reply_Text);
			reply_button.click();
			String getnotetext = after_note_reply.getText();
			if (getnotetext.equals(Reply_Text)) {
				Azure.updateTestCaseStatus("13706", "Automation Pass", "");
				System.out.println("*****Pass: Reply  Note functionality working fine*****");

			} else {
				handleTestFailure("13706", "Reply Note functionality is not working fine");
			}

		} catch (Exception e) {
			e.printStackTrace();
			String exceptionTitle = e.getMessage().split("\n")[0];
			Azure.updateTestCaseStatus("12149", "Automation Error", exceptionTitle);
			Assert.fail("****FAIL: An Exception occurs while testing Reply Note functionality****", e);
		}
	}

	/// Test Case 13708: verify cancel button working or not
	public void cancel_button() throws IOException {
		try {
			common.switchToIframe(common.MiddlePortionFrame);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the wait time as needed
			wait.until(ExpectedConditions.presenceOfElementLocated(File_Details));
			driver.switchTo().defaultContent();
			notes_navigate.click();
			add_icon.click();

			// Get the initial visibility status of the add note textarea
			boolean addNoteTextareaVisibleInitially = addnote_Textarea.isDisplayed();

			Cancel_button.click();

			// Check if the add note textarea is still present after clicking cancel
			try {
				// Attempt to find the add note textarea
				WebElement addNoteTextarea = driver
						.findElement(By.xpath("//textarea[@placeholder='Add new note here..']"));

				// If the textarea is found, it means cancel button functionality failed
				handleTestFailure("13708", "Cancel button functionality is not working fine");
			} catch (NoSuchElementException e) {
				// If NoSuchElementException is caught, it means cancel button functionality
				// worked
				Azure.updateTestCaseStatus("13708", "Automation Pass", "");
				System.out.println("*****Pass: Cancel button functionality working fine*****");
			}
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionTitle = e.getMessage().split("\n")[0];
			Azure.updateTestCaseStatus("13708", "Automation Error", exceptionTitle);
			Assert.fail("****FAIL: An Exception occurs while testing cancel button functionality****", e);
		}
	}

//Test Case 13707: verify remove notes fnctionality working or not	
	public void remove_note(String Add_Text) throws IOException {
		add_note(Add_Text);
		ellipsis_menu.click();
		Remove_note.click();
		WebElement alert_RemoveButton = driver
				.findElement(By.xpath("//div[contains(@class,'modal-footer')]/button[2]"));
		alert_RemoveButton.click();

		boolean isAfterNotesAddVisible = false;
		boolean isMessageDisplayed = false;

		try {
			isAfterNotesAddVisible = After_notes_add.isDisplayed();
			isMessageDisplayed = Removenote_message.isDisplayed();
		} catch (NoSuchElementException e) {
			// Ignore exception if elements are not found
		}

		// If 'After_notes_add' is visible and message is not displayed, it's a failure
		if (isAfterNotesAddVisible && !isMessageDisplayed) {
			handleTestFailure("13707", "Remove note functionality is not working fine");
		} else {
			Azure.updateTestCaseStatus("13707", "Automation Pass", "");
			System.out.println("*****Pass: Remove note functionality working fine*****");
		}
	}

	// Test Case 13703: Verify added notes in other dropdown option are showing in
	// ALL section or not
	public void badge_dropdown(String Add_Text) throws IOException, InterruptedException {
		common.switchToIframe(common.MiddlePortionFrame);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the wait time as needed
		wait.until(ExpectedConditions.presenceOfElementLocated(File_Details));
		driver.switchTo().defaultContent();
		notes_navigate.click();
		Thread.sleep(2000);
		show_dropdown.click();
		dropdown_mortgage.click();
		notes_navigate.click();
		only_add_note(Add_Text);
		String getnotetext = After_notes_add.getText();
		Thread.sleep(2000);
		show_dropdown.click();
		dropdown_All.click();
		String Allgetext = After_notes_add.getText();
		if (getnotetext.equals(Allgetext)) {
			Azure.updateTestCaseStatus("13702", "Automation Pass", "");
			System.out.println("*****Pass: Add Note functionality working fine*****");
		} else {
			handleTestFailure("13702", "Add Note functionality is not working fine");
		}

	}
}
