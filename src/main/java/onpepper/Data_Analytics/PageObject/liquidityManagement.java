package onpepper.Data_Analytics.PageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class liquidityManagement extends AbstractComponent {
	public WebDriver driver;

	public liquidityManagement(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(text(),'Display Funds:')]/parent::div//span[@class='ant-checkbox-label']")
	List<WebElement> fundNames;
	@FindBy(xpath = "//span[contains(text(),'Projection Dates ')]/parent::button")
	WebElement projectiondatebtn;
	@FindBy(xpath = "//input[@id='proformaDatePicker']")
	WebElement projectiondate_input_field;
	@FindBy(xpath = "//span[contains(text(),'Add')]/parent::button")
	WebElement add_Date_Btn;
	@FindBy(xpath = "(//th[@title='Terms'])[2]/parent::tr//th")
	List<WebElement> tableheaderCount;
	@FindBy(xpath = "//span[contains(text(),'Update Current Values')]/parent::button")
	WebElement update_Current_Valuebtn;
	@FindBy(xpath = "//span[contains(text(),'What-if Analysis')]/parent::button")
	WebElement WIAbtn;
	@FindBy(xpath = "//input[@id='asOfDatePicker']")
	WebElement asOfDate_input_field;
	@FindBy(xpath = "//span[contains(text(),'Save')]/parent::button")
	WebElement savebtn;
	@FindBy(xpath = "//td[contains(@class,'htEditableCell')]//parent::tr//td[2]")
	List<WebElement> editable_CellData;
	@FindBy(xpath = "//textarea[@class='handsontableInput']")
	WebElement tableInputField;
	@FindBy(xpath = "//span[contains(text(),'Run Analysis')]/parent::button")
	WebElement run_AnalysisBtn;
	@FindBy(xpath = "//span[contains(text(),'Export')]/parent::button")
	WebElement ExportReportBtn;
	@FindBy(xpath = "//span[contains(text(),'Cancel')]/parent::button//following-sibling::button")
	WebElement ExportBtn;
	@FindBy(xpath = "//span[contains(text(),'Import File')]/parent::button")
	WebElement import_ReportBtn;
	@FindBy(xpath = "//div[contains(@class,'_dropzone_')]")
	WebElement DragandDropField;
	@FindBy(xpath = "//span[contains(text(),'Upload')]/parent::button")
	WebElement uploadBtn;

	public void click_UploaBtn() {
		waitforclickable(uploadBtn);
		uploadBtn.click();
	}

	public void click_Import_ReportBtn() {
		click_update_Current_Valuebtn();
		import_ReportBtn.click();
	}

	public void upload_Import_FIle(String filePath) throws AWTException {
		click_Import_ReportBtn();
		Robot robot = new Robot();
		Actions act = new Actions(driver);
		act.click(DragandDropField).perform();
		robot.delay(1000);
		StringSelection selection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public boolean get_Current_ColumnStatus(String subfundname) {
		WebElement column = driver.findElement(By.xpath("(//span[text()='" + subfundname + " Current'])[2]"));
		waitforElementAppear(column);
		return column.isDisplayed();
	}

	public void select_CalculationTab(String tabname) {
		driver.findElement(By.xpath("//div[contains(text(),'" + tabname + "')]")).click();
	}

	public void click_update_Current_Valuebtn() {
		update_Current_Valuebtn.click();
	}

	public void click_WIAbtn() {
		WIAbtn.click();
	}

	public void add_AsOfDate() {
		asOfDate_input_field.click();
		String d = getDateAsPerDays2(-1);
		driver.findElement(By.xpath("//td[@title='" + d + "']")).click();
		savebtn.click();
	}

	public void get_EditData(String tabname) throws InterruptedException {
		click_update_Current_Valuebtn();
		select_CalculationTab(tabname);
		Actions act = new Actions(driver);
		for (WebElement cell : editable_CellData) {
			String originalValue = cell.getText().trim();
			String updatedValue = getUpdatedValue(originalValue);
			act.click(cell).perform();
			Thread.sleep(500);
			act.doubleClick(cell).perform();
			Thread.sleep(500);
			tableInputField.clear();
			tableInputField.sendKeys(updatedValue);
			Thread.sleep(500);
		}
		add_AsOfDate();
	}

	public void get_EditData_ByWIA(String tabname) throws InterruptedException {
		click_WIAbtn();
		select_CalculationTab(tabname);
		Actions act = new Actions(driver);
		for (WebElement cell : editable_CellData) {
			String originalValue = cell.getText().trim();
			String updatedValue = getUpdatedValue(originalValue);
			act.click(cell).perform();
			Thread.sleep(500);
			act.doubleClick(cell).perform();
			Thread.sleep(500);
			tableInputField.clear();
			tableInputField.sendKeys(updatedValue);
			Thread.sleep(500);
		}
		run_AnalysisBtn.click();
	}

	public String getUpdatedValue(String value) {
		if (value.contains("%")) {
			double percent = Double.parseDouble(value.replace("%", "")) / 100;
			percent += 0.01; // increment
			return String.format("%.2f", percent);
		}
		if (value.contains(",")) {
			double number = Double.parseDouble(value.replace(",", ""));
			number += 1;
			return String.format("%,.2f", number);
		}
		if (value.matches("\\d+")) {
			int number = Integer.parseInt(value);
			return String.valueOf(number + 1);
		}
		return value;
	}

	public boolean SelectMultipleProjectionDates(int days) throws InterruptedException {
		click_ProjectionDate();
		projectiondate_input_field.click();
		String d = getDateAsPerDays2(days);
		String d1 = getDateAsPerDays(days);
		driver.findElement(By.xpath("//td[@title='" + d + "']")).click();
		add_Date_Btn.click();
		WebElement calculationColumn = driver
				.findElement(By.xpath("(//span[contains(text(),'PFLT_SPV " + d1 + "')])[2]"));
		waitforElementAppear(calculationColumn);
		return calculationColumn.isDisplayed();
	}

	public boolean RemoveProjectionDateUsingCloseIcon(int days) throws InterruptedException {
		click_ProjectionDate();
		projectiondate_input_field.click();
		String d = getDateAsPerDays2(days);
		driver.findElement(By.xpath("//td[@title='" + d + "']")).click();
		add_Date_Btn.click();
		Thread.sleep(1000);
		int headercount = tableheaderCount.size();
		String d2 = getDateAsPerDays3(7);
		driver.findElement(By.xpath("//span[text()='" + d2 + "']/following-sibling::span")).click();
		Thread.sleep(2000);
		int headercount2 = tableheaderCount.size();
		boolean status = false;
		if (headercount >= headercount2) {
			status = true;
		}
		return status;
	}

	public void click_ProjectionDate() {
		projectiondatebtn.click();
	}

	public String getDateAsPerDays(int days) {
		LocalDate today = LocalDate.now();
		LocalDate dateAfterTenDays = today.plusDays(days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");
		String resultDate = dateAfterTenDays.format(formatter);
		return resultDate;
	}

	public String get_Friday_Date() {
		LocalDate today = LocalDate.now();
		LocalDate nextFriday = today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");
		String fridayDate = nextFriday.format(formatter);
		return fridayDate;
	}

	public String getDateAsPerDays2(int days) {
		LocalDate today = LocalDate.now();
		LocalDate dateAfterTenDays = today.plusDays(days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String resultDate = dateAfterTenDays.format(formatter);
		System.out.println(resultDate);
		return resultDate;
	}

	public String getDateAsPerDays3(int days) {
		LocalDate today = LocalDate.now();
		LocalDate dateAfterTenDays = today.plusDays(days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String resultDate = dateAfterTenDays.format(formatter);
		System.out.println(resultDate);
		return resultDate;
	}

	public boolean get_calculationcolumn_StatusasperFriday(String subfundname) {
		String d = get_Friday_Date();
		WebElement calculationcolumn = driver
				.findElement(By.xpath("(//span[contains(text(),'" + subfundname + " " + d + "')])[2]"));
		waitforElementAppear(calculationcolumn);
		return calculationcolumn.isDisplayed();
	}

	public void click_Fund_Checkbox(String fundName) {
		WebElement funds_Checkbox = driver
				.findElement(By.xpath("//span[text()='" + fundName + "']/preceding-sibling::span"));
		waitforclickable(funds_Checkbox);
		funds_Checkbox.click();
	}

	public boolean isFundCheckboxSelected(String fundName) {
		WebElement checkboxInput = driver.findElement(
				By.xpath("//span[text()='" + fundName + "']" + "/preceding-sibling::span//input[@type='checkbox']"));
		return checkboxInput.isSelected();
	}

	public List<String> get_expectedfunds() {
		List<String> expectedFunds = Arrays.asList("PFLT", "PSLF", "PSSL", "PSCF", "PCOF", "PNNT");
		return expectedFunds;
	}

	public List<String> get_Liquidity_Fund_Names() {
		List<String> actualFunds = fundNames.stream().map(e -> e.getText().trim()).filter(text -> !text.isEmpty())
				.collect(Collectors.toList());
		return actualFunds;
	}

	public int getActual_SubfundCount(String fundname) {
		WebElement d = driver.findElement(
				By.xpath("//span[contains(text(),'" + fundname + "')]//parent::label/following-sibling::span"));
		int num = extractNumber(d.getText());
		return num;

	}

	public List<WebElement> getexpected_SubfundCount(String fundName) {
		List<WebElement> subfund_checkbox = driver.findElements(By.xpath("//div[normalize-space()='" + fundName
				+ " subfunds:']/parent::div//span[contains(@class,'ant-checkbox ant-wave-target css')]"));
		return subfund_checkbox;
	}

	public List<WebElement> getexpected_SubfundLabel(String fundName) {
		List<WebElement> subfund_label = driver.findElements(By.xpath("//div[normalize-space()='" + fundName
				+ " subfunds:']/parent::div//span[@class='ant-checkbox-label']"));
		return subfund_label;
	}

	public int extractNumber(String text) {
		return Integer.parseInt(text.replaceAll("\\D+", ""));
	}

	public void clickExportReportBtn() {
		ExportReportBtn.click();
	}

	public String clickExportBtn(String fundName) throws InterruptedException, IOException {
		clickExportReportBtn();
		Actions act = new Actions(driver);
		act.moveToElement(ExportBtn).perform();
		ExportBtn.click();
		Thread.sleep(5000);
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String downloadDir = prop.getProperty("downloadFilepath");
		System.out.println(downloadDir);
		String baseFileName = "Liquidity_Overview_Report";
		String extension = ".xlsx";
		System.out
				.println("status is " + downloadDir + " Filename " + baseFileName + " extension name is " + extension);
		String latestFilePath = getLatestDownloadedFile(downloadDir, baseFileName, extension);
		return latestFilePath;
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
}
