package onpepper.Data_Analytics.PageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class HomePage extends AbstractComponent {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "((//nav//following-sibling::div)[3]//img)[3]")
	WebElement OptionBtn;
	@FindBy(xpath = "//div[contains(@class,'Toastify__toast-icon')]/following-sibling::div")
	WebElement ToastMsg;
	@FindBy(xpath = "//span[text()='Formula']/parent::div/parent::div/parent::div//button[@aria-label='Close']")
	WebElement CloseFormulaBtn;
	@FindBy(xpath = "//span[contains(text(),'What-if Analysis')]/parent::button")
	WebElement WhatIfAnalysisBtn;
	@FindBy(xpath = "//div[contains(text(),'Update Values')]/parent::div//select")
	WebElement select_ColumnDropdown;
	@FindBy(xpath = "(//span[text()='Save']/parent::button)[1]")
	WebElement SaveBtn;
	@FindBy(xpath = "(//span[text()='Save']/parent::button)[2]")
	WebElement SaveBtn2;
	@FindBy(xpath = "//input[@placeholder='Untitled']")
	WebElement UntitledField;
	@FindBy(xpath = "//input[@placeholder='Notes']")
	WebElement notes_Field;
	@FindBy(xpath = "//div[contains(text(),'What-if Analysis library')]")
	WebElement WhatifanalysisOption;
	@FindBy(xpath = "//div[contains(text(),'Borrowing Base Reports Library')]")
	WebElement BorrowingBaseLibraryOption;
	@FindBy(xpath = "//th[text()='Name']/parent::tr/parent::thead//th")
	List<WebElement> HeadNames;
	@FindBy(xpath = "//span[contains(.,'Data Ingestion')]/parent::div")
	WebElement dataIngestionBtn;
	@FindBy(xpath = "//span[contains(@class,'_columnName_')]")
	List<WebElement> errorLists;
	@FindBy(xpath = "//span[contains(text(),'Export Report')]/parent::button")
	WebElement ExportReportBtn;
	@FindBy(xpath = "//span[contains(text(),'Cancel')]/parent::button//following-sibling::button")
	WebElement ExportBtn;
	@FindBy(xpath = "//span[contains(text(),'View Full Report')]/parent::button")
	WebElement viewReportBtn;
	@FindBy(id = "changeValue")
	WebElement changeValueTextfield;
	@FindBy(xpath = "//span[contains(text(),'Apply Changes')]/parent::button")
	WebElement apply_Changes_Btn;
	@FindBy(xpath = "//span[contains(text(),'Run')]/ancestor::button")
	WebElement run_Btn;
	@FindBy(xpath = "//span[contains(text(),'Use')]/parent::button")
	WebElement use_Btn;
	@FindBy(xpath = "//div[contains(text(),'What if Analysis Library')]/parent::div/following-sibling::div//table//tbody//td[2]")
	List<WebElement> wia_reportNames;
	@FindBy(xpath = "//span[contains(.,'Liquidity Management')]/parent::div")
	WebElement liquidityManagementBtn;

	public liquidityManagement navigateToLiquidityManagement() {
		liquidityManagementBtn.click();
		driver.manage().window().maximize();
		liquidityManagement liquid = new liquidityManagement(driver);
		return liquid;
	}

	public void clickExportReportBtn() {
		ExportReportBtn.click();
	}

	public void clickViewReportBtn() {
		viewReportBtn.click();
	}

	public boolean isrowPresent() {
		List<WebElement> rows = driver.findElements(By.xpath("//tr"));
		return rows.size() > 0;
	}

	public String clickExportBtn(String fundName) throws InterruptedException, IOException {
		clickExportReportBtn();
		ExportBtn.click();
		Thread.sleep(5000);
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String downloadDir = prop.getProperty("downloadFilepath");
		System.out.println(downloadDir);
		String baseFileName = fundName + " - Borrowing Base Report";
		String extension = ".xlsx";
		String latestFilePath = getLatestDownloadedFile(downloadDir, baseFileName, extension);
		return latestFilePath;
	}

	public boolean isFileDownloaded(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return false;
		}
		if (file.length() == 0) {
			return false;
		}
		return true;
	}

	public String getLatestDownloadedFile(String dirPath, String baseName, String extension) {
		File dir = new File(dirPath);
		File[] matchingFiles = dir.listFiles((dir1, name) -> name.startsWith(baseName) && name.endsWith(extension));
		if (matchingFiles == null || matchingFiles.length == 0) {
			return null;
		}
		Arrays.sort(matchingFiles, Comparator.comparingLong(File::lastModified).reversed());
		return matchingFiles[0].getAbsolutePath();
	}

	public String getFundtype(String fundName) {
		WebElement fundType = driver.findElement(By.xpath("//span[contains(@title,'" + fundName + "')]"));
		waitforElementAppear(fundType);
		return fundType.getText();
	}

	public boolean getRowstatus(String tabName) throws InterruptedException {
		clickViewReportBtn();
		driver.findElement(By.xpath("//div[contains(text(),'" + tabName + "')]")).click();
		Thread.sleep(5000);
		return isrowPresent();

	}

	public boolean isErrorListVisible() {
		try {
			return errorLists.size() > 0 && errorLists.get(0).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void getErrorLists() {
		for (WebElement Error : errorLists) {
			System.out.println(Error.getText());
		}
	}

	public void clickWhatifanalysisOption() {
		waitforclickable(OptionBtn);
		OptionBtn.click();
		waitforclickable(WhatifanalysisOption);
		WhatifanalysisOption.click();
	}

	public void clickBorrowingBaseLibraryOption() {
		waitforclickable(OptionBtn);
		OptionBtn.click();
		BorrowingBaseLibraryOption.click();
	}

	public void clickViewResultBtn(String fundName) {
		clickBorrowingBaseLibraryOption();
		WebElement viewResultBtn = driver
				.findElement(By.xpath("(//td[contains(text(),'" + fundName + "')]/following-sibling::td//button)[1]"));
		Actions act = new Actions(driver);
		act.moveToElement(viewResultBtn).perform();
		viewResultBtn.click();

	}

	public void sendvalueUntitledField(String fundName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String value = fundName + "_" + timestamp;
		UntitledField.sendKeys(value);
	}

	public void sendvalueNotes() {
		notes_Field.sendKeys("Test");
	}

	public void clickSaveButton() {
		SaveBtn.click();
	}

	public void clickSaveButton2() {
		SaveBtn2.click();
	}

	public DataIngestion navigateToDataIngestion() {
		dataIngestionBtn.click();
		driver.manage().window().maximize();
		DataIngestion data = new DataIngestion(driver);
		return data;
	}

	public boolean updateValues_WIA(String columnName, String fundName) throws InterruptedException {
		waitforElementAppear(select_ColumnDropdown);
		select_ColumnDropdown.click();
		driver.findElement(By.xpath("(//option[contains(text(),'" + columnName + "')])[2]")).click();
		changeValueTextfield.sendKeys("10");
		apply_Changes_Btn.click();
		Actions act = new Actions(driver);
		act.moveToElement(run_Btn).perform();
		run_Btn.click();
		WebElement wia_Text = driver.findElement(
				By.xpath("//div[contains(text(),'Showing " + fundName + " What-If Analysis Report for')]"));
		waitforElementAppear(wia_Text);
		return wia_Text.isDisplayed();
	}

	public void save_WIA_data(String fundName) {
		sendvalueUntitledField(fundName);
		clickSaveButton();
		sendvalueNotes();
		clickSaveButton2();
	}

	public String getLatestWIAReport(String fundname) throws InterruptedException {
		String latestReport = null;
		Date latestDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		for (WebElement report : wia_reportNames) {
			String text = report.getText().trim();
			if (text.startsWith(fundname) && text.contains("_")) {
				try {
					String timestamp = text.split(" ")[1];
					Date reportDate = sdf.parse(timestamp);

					if (latestDate == null || reportDate.after(latestDate)) {
						latestDate = reportDate;
						latestReport = text;
					}

				} catch (Exception e) {

				}
			}
		}
		WebElement wia_radio = driver.findElement(
				By.xpath("//span[contains(text(),'" + latestReport + "')]/parent::td/preceding-sibling::td//input"));
		Actions act = new Actions(driver);
		act.moveToElement(wia_radio).perform();
		wia_radio.click();
		act.moveToElement(use_Btn).perform();
		use_Btn.click();
		return latestReport;
	}

	public void clickWhatIfAnalysisBtn() {
		WhatIfAnalysisBtn.click();
	}

	public void closePopup() {
		CloseFormulaBtn.click();
	}

	public WebElement getToastpopup() {
		// System.out.println(ToastMsg.getText());
		return ToastMsg;
	}

}
