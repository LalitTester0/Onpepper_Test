package onpepper.Data_Analytics.PageObject;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class ExtractNewBaseData extends AbstractComponent {
	public WebDriver driver;

	public ExtractNewBaseData(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Select Fund')]")
	WebElement selectFundDropdown;
	@FindBy(id = "reportDatePicker")
	WebElement ReportDateField;
	@FindBy(xpath = "//span[contains(text(),'Next ')]/parent::button")
	WebElement nextBtn;
	@FindBy(xpath = "//table//td[2]")
	List<WebElement> fileNames;
	@FindBy(xpath = "//span[contains(text(),'Upload New Files')]/parent::button")
	WebElement uploadNewFileBtn;
	@FindBy(xpath = "//span[contains(text(),'Load')]/parent::button")
	WebElement LoadBtn;
	@FindBy(xpath = "(//td[contains(text(),'Master Comps')]/parent::tr//td[1]//input)[1]")
	WebElement master_Comp_SourceFile;
	@FindBy(xpath = "(//td[contains(text(),'Market')]/parent::tr//td[1]//input)[1]")
	WebElement market_SourceFile;
	@FindBy(xpath = "(//td[contains(text(),'Cash')]/parent::tr//td[1]//input)[1]")
	WebElement cash_SourceFile;
	@FindBy(xpath = "(//td[contains(text(),'Ratings')]/parent::tr//td[1]//input)[1]")
	WebElement Ratings_SourceFile;
	@FindBy(xpath = "(//td[contains(text(),'PSSLF')]/parent::tr//td[1]//input)[1]")
	WebElement psslf_Cash_SourceFile;
	@FindBy(xpath = "(//td[contains(text(),'PSLF')]/parent::tr//td[1]//input)[1]")
	WebElement pslf_Cash_SourceFile;
	@FindBy(xpath = "(//td[contains(text(),'PSCF_Cash')]/parent::tr//td[1]//input)[1]")
	WebElement pscf_Cash_SourceFile;
	@FindBy(xpath = "//h1[contains(text(),'Data mapping')]")
	WebElement data_mappingText;
	@FindBy(xpath = "//span[contains(text(),'Start Extraction')]/parent::button")
	WebElement start_ExctractionBtn;

	public BaseDataPreview clickstart_ExctractionBtn() throws InterruptedException {
		waitforclickable(start_ExctractionBtn);
		Thread.sleep(10);
		start_ExctractionBtn.click();
		BaseDataPreview base = new BaseDataPreview(driver);
		return base;
	}

	public void check_Data_Mapping_info() {
		waitforElementAppear(data_mappingText);
		click_NextBtn();
	}

	public void selectSourceFileforPFLT() {
		master_Comp_SourceFile.click();
		Ratings_SourceFile.click();
		cash_SourceFile.click();
		click_NextBtn();
	}

	public void selectSourceFileforPCOF() {
		master_Comp_SourceFile.click();
		market_SourceFile.click();
		click_NextBtn();
	}

	public void selectSourceFileforPSSL() {
		master_Comp_SourceFile.click();
		Ratings_SourceFile.click();
		psslf_Cash_SourceFile.click();
		click_NextBtn();
	}

	public void selectSourceFileforPSLF() {
		master_Comp_SourceFile.click();
		pslf_Cash_SourceFile.click();
		click_NextBtn();
	}

	public void selectSourceFileforPSCF() {
		master_Comp_SourceFile.click();
		pscf_Cash_SourceFile.click();
		click_NextBtn();
	}

	public boolean marketFilestatusUpload(List<WebElement> fileNameElements)
			throws InterruptedException, IOException, AWTException {
		boolean status = fileNameElements.stream().map(WebElement::getText)
				.filter(text -> text != null && !text.trim().isEmpty())
				.anyMatch(filename -> filename.toLowerCase().contains("market"));
		if (!status) {
			click_upload_New_Btn();
			SourceFileLists source = new SourceFileLists(driver);
			source.uploadNewMasterFile();
			Thread.sleep(500);
			clickLoadButton();
			Thread.sleep(500);
			waitForElementToDisappear(LoadBtn);
		}
		return status;
	}

	public boolean cashFilestatusUpload(List<WebElement> fileNameElements)
			throws InterruptedException, IOException, AWTException {
		boolean status = fileNameElements.stream().map(WebElement::getText)
				.filter(text -> text != null && !text.trim().isEmpty())
				.anyMatch(filename -> filename.toLowerCase().contains("cash"));
		if (!status) {
			click_upload_New_Btn();
			SourceFileLists source = new SourceFileLists(driver);
			source.uploadNewCashFile();
			Thread.sleep(500);
			clickLoadButton();
			Thread.sleep(500);
			waitForElementToDisappear(LoadBtn);
		}
		return status;
	}

	public boolean ratingsFilestatusUpload(List<WebElement> fileNameElements)
			throws InterruptedException, IOException, AWTException {
		boolean status = fileNameElements.stream().map(WebElement::getText)
				.filter(text -> text != null && !text.trim().isEmpty())
				.anyMatch(filename -> filename.toLowerCase().contains("ratings"));
		if (!status) {
			click_upload_New_Btn();
			SourceFileLists source = new SourceFileLists(driver);
			source.uploadNewRatingFile();
			Thread.sleep(500);
			clickLoadButton();
			Thread.sleep(500);
			waitForElementToDisappear(LoadBtn);
		}
		return status;
	}

	public boolean masterFilestatusUpload(List<WebElement> fileNameElements)
			throws InterruptedException, IOException, AWTException {
		boolean status = fileNameElements.stream().map(WebElement::getText)
				.filter(text -> text != null && !text.trim().isEmpty())
				.anyMatch(filename -> filename.toLowerCase().contains("master"));
		if (!status) {
			click_upload_New_Btn();
			Thread.sleep(500);
			SourceFileLists source = new SourceFileLists(driver);
			source.uploadNewMasterFile();
			Thread.sleep(500);
			clickLoadButton();
			Thread.sleep(500);
			waitForElementToDisappear(LoadBtn);
		}
		return status;
	}

	public boolean pslfCashFilestatusUpload(List<WebElement> fileNameElements)
			throws InterruptedException, IOException, AWTException {
		boolean status = fileNameElements.stream().map(WebElement::getText)
				.filter(text -> text != null && !text.trim().isEmpty())
				.anyMatch(filename -> filename.toLowerCase().contains("pslf_cashfile"));
		if (!status) {
			click_upload_New_Btn();
			SourceFileLists source = new SourceFileLists(driver);
			Thread.sleep(500);
			source.uploadNewCashFileforPSLF();
			Thread.sleep(500);
			clickLoadButton();
			Thread.sleep(500);
			waitForElementToDisappear(LoadBtn);
		}
		return status;
	}

	public boolean pscfCashFilestatusUpload(List<WebElement> fileNameElements)
			throws InterruptedException, IOException, AWTException {
		boolean status = fileNameElements.stream().map(WebElement::getText)
				.filter(text -> text != null && !text.trim().isEmpty())
				.anyMatch(filename -> filename.toLowerCase().contains("pscf_cashfile"));
		if (!status) {
			click_upload_New_Btn();
			SourceFileLists source = new SourceFileLists(driver);
			Thread.sleep(500);
			source.uploadNewCashFileforPSCF();
			Thread.sleep(500);
			clickLoadButton();
			Thread.sleep(500);
			waitForElementToDisappear(LoadBtn);
		}
		return status;
	}

	public boolean psslCashFilestatusUpload(List<WebElement> fileNameElements)
			throws InterruptedException, IOException, AWTException {
		boolean status = fileNameElements.stream().map(WebElement::getText)
				.filter(text -> text != null && !text.trim().isEmpty())
				.anyMatch(filename -> filename.toLowerCase().contains("psslf"));
		if (!status) {
			click_upload_New_Btn();
			SourceFileLists source = new SourceFileLists(driver);
			source.uploadNewCashFileforPSSL();
			clickLoadButton();
		}
		return status;
	}

	public void clickLoadButton() {
		LoadBtn.click();
	}

	public void click_upload_New_Btn() {
		uploadNewFileBtn.click();
	}

	public void checkSourceFileAvailabilityforPFLT() throws InterruptedException, IOException, AWTException {
		masterFilestatusUpload(fileNames);
		cashFilestatusUpload(fileNames);
		ratingsFilestatusUpload(fileNames);
		click_NextBtn();
	}

	public void checkSourceFileAvailabilityforPCOF() throws InterruptedException, IOException, AWTException {
		marketFilestatusUpload(fileNames);
		masterFilestatusUpload(fileNames);
		click_NextBtn();
	}

	public void checkSourceFileAvailabilityforPSSL() throws InterruptedException, IOException, AWTException {
		ratingsFilestatusUpload(fileNames);
		psslCashFilestatusUpload(fileNames);
		masterFilestatusUpload(fileNames);
		click_NextBtn();
	}

	public void checkSourceFileAvailabilityforPSLF() throws InterruptedException, IOException, AWTException {
		pslfCashFilestatusUpload(fileNames);
		masterFilestatusUpload(fileNames);
		waitForElementToDisappear(LoadBtn);
		click_NextBtn();
	}

	public void checkSourceFileAvailabilityforPSCF() throws InterruptedException, IOException, AWTException {
		pscfCashFilestatusUpload(fileNames);
		masterFilestatusUpload(fileNames);
		click_NextBtn();
	}

	public void select_Fund(String fundtype) {
		selectFundDropdown.click();
		driver.findElement(By.xpath("//div[contains(text(),'" + fundtype + "')]")).click();
	}

	public String uploadDate() {
		LocalDate date = LocalDate.now().withDayOfMonth(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(formatter);
	}

	public void selectReportDate() {
		Actions act = new Actions(driver);
		waitforclickable(ReportDateField);
		act.click(ReportDateField).perform();
		String date = uploadDate();
		WebElement date1 = driver.findElement(By.xpath("(//td[@title='" + date + "'])[1]"));
		date1.click();
	}

	public void click_NextBtn() {
		waitforclickable(nextBtn);
		Actions act = new Actions(driver);
		act.moveToElement(nextBtn).perform();
		nextBtn.click();
	}

	public void enter_Fund_and_Date_Date(String fundtype) {
		select_Fund(fundtype);
		selectReportDate();
		click_NextBtn();
	}

}
