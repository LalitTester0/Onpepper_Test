package onpepper.Data_Analytics.PageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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

public class SourceFileLists extends AbstractComponent {
	public WebDriver driver;

	public SourceFileLists(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(.,'+ Upload Files')]/parent::button")
	WebElement UploadFilesBtn;
	@FindBy(xpath = "//div[@class='ant-modal-body']//div[@class='ant-picker-input']//input[@placeholder='Report Date']")
	WebElement ReportDateField;
	@FindBy(xpath = "//span[text()='PFLT']/preceding-sibling::span[contains(@class,'ant-checkbox')]")
	WebElement PFLTCheckbox;
	@FindBy(xpath = "//span[text()='PSSL']/preceding-sibling::span[contains(@class,'ant-checkbox')]")
	WebElement PSSLCheckbox;
	@FindBy(xpath = "//span[text()='PCOF']/preceding-sibling::span[contains(@class,'ant-checkbox')]")
	WebElement PCOFCheckbox;
	@FindBy(xpath = "//div[contains(@class,'_dropzone_')]")
	WebElement DragandDropField1;
	@FindBy(xpath = "//span[contains(text(),'Load')]/parent::button")
	WebElement LoadBtn;
	@FindBy(xpath = "//div[contains(@class,'Toastify__toast-icon')]/following-sibling::div")
	WebElement getToastMsg;
	@FindBy(xpath = "(//th[.='Fund']/ancestor::table//tbody//td[.='Completed']/parent::tr//td[1]//input)[1]")
	WebElement fileCheckbox;
	@FindBy(xpath = "(//th[.='Fund']/ancestor::table//tbody//td[.='Completed']/parent::tr//td[3])[1]")
	WebElement fileName;
	@FindBy(xpath = "//span[contains(normalize-space(),'Add to Archives')]/parent::button")
	WebElement addToArchieveBtn;
	@FindBy(xpath = "//button[@role='switch']")
	WebElement switcher;
	@FindBy(xpath = "//th[.='File Name']/ancestor::table//tbody//td[3]")
	List<WebElement> ArchievedfileNames;
	@FindBy(xpath = "//span[.='Unarchive']/parent::button")
	WebElement unArchieveBtn;
	@FindBy(xpath = "(//th[.='Fund']/ancestor::table//tbody//td//input)[1]")
	WebElement archieveFileCheckbox;
	@FindBy(xpath = "(//th[.='Fund']/ancestor::table//tbody//td[3])[1]")
	WebElement archivedFileName;
	@FindBy(xpath = "//span[@class='ant-select-selection-search']/parent::div")
	WebElement selectFundDropdown;
	@FindBy(xpath = "//div[@title='PCOF']")
	WebElement PCOFOption;
	@FindBy(xpath = "//div[@title='PFLT']")
	WebElement PFLTOption;
	@FindBy(xpath = "//div[@title='PSSL']")
	WebElement PSSLOption;
	@FindBy(xpath = "//td[2]//span[text()='PFLT']")
	List<WebElement> PFLTfundTypeColumn;
	@FindBy(xpath = "//th[@title='Fund']/parent::tr/parent::thead/following-sibling::tbody//td[4]")
	List<WebElement> ReportDateColumn;
	@FindBy(xpath = "//td[2]//span[text()='PCOF']")
	List<WebElement> PCOFfundTypeColumn;
	@FindBy(xpath = "//td[2]//span[text()='PSSL']")
	List<WebElement> PSSLfundTypeColumn;
	@FindBy(xpath = "//input[@placeholder='Report Date']")
	WebElement ReportDate;
	@FindBy(xpath = "//span[contains(text(),'<- Base Data')]/parent::button")
	WebElement NavigatetoBaseDataButton;
	@FindBy(xpath = "//span[contains(text(),'Extract Base Data')]/parent::button")
	WebElement exportBaseDataBtn;
	@FindBy(xpath = "//span[text()='Cancel']/parent::button")
	WebElement cancelBtn;
	@FindBy(xpath = "//td[contains(text(),'No Data')]")
	WebElement NoDataCell;

	public String selectFilecheckbox(String date, String FileName) {
		WebElement fileCheckbox = driver
				.findElement(By.xpath("(//span[contains(text(),'Completed')]/ancestor::tr//td[text()='" + date
						+ "']/preceding-sibling::td[1][contains(text(),'" + FileName
						+ "')]/preceding-sibling::td[2]//input)[1]"));
		fileCheckbox.click();
		return getFileName(FileName);
	}

	public String getFileName(String FileName) {
		WebElement fileName = driver.findElement(By.xpath(
				"(//th[@title='Fund']/parent::tr/parent::thead/following-sibling::tbody//td[7]//span[text()='Completed']/ancestor::tr//td[3][contains(text(),'"
						+ FileName + "')])[1]"));
		return fileName.getText();
	}

	public String noDataCellText() {
		return NoDataCell.getText();
	}

	public void clickCancelBtn() {
		cancelBtn.click();
	}

	public DataIngestion NavigatetoBaseDataButton() throws InterruptedException {
		NavigatetoBaseDataButton.click();
		DataIngestion data = new DataIngestion(driver);
		return data;
	}

	public void clickexportBaseDataBtn() {
		exportBaseDataBtn.click();
	}

	public String selectSourceFileforPFLT() throws InterruptedException {
		String date = uploadDate();
		filteredPFLTValue();
		List<String> FileNames = new ArrayList<>();
		FileNames.add(selectFilecheckbox(date, "Master"));
		FileNames.add(selectFilecheckbox(date, "PENPL"));
		FileNames.add(selectFilecheckbox(date, "Market"));
		Thread.sleep(1000);
		List<String> cleanedNames = new ArrayList<>();
		for (String filename : FileNames) {
			cleanedNames.add(filename.replaceAll("\\.\\w+$", ""));
		}
		String formattedString = String.join("; ", cleanedNames);
		clickexportBaseDataBtn();
		return formattedString;
	}

	public String selectSourceFileforPCOF() throws InterruptedException {
		String date = uploadDate();
		filteredPCOFValue();
		List<String> FileNames = new ArrayList<>();
		FileNames.add(selectFilecheckbox(date, "Master"));
		FileNames.add(selectFilecheckbox(date, "PENPL"));
		FileNames.add(selectFilecheckbox(date, "Market"));
		Thread.sleep(1000);
		List<String> cleanedNames = new ArrayList<>();
		for (String filename : FileNames) {
			cleanedNames.add(filename.replaceAll("\\.\\w+$", ""));
		}
		String formattedString = String.join("; ", cleanedNames);
		clickexportBaseDataBtn();
		return formattedString;
	}

	public String selectSourceFileforPSSL() throws InterruptedException {
		String date = uploadDate();
		filteredPSSLValue();
		List<String> FileNames = new ArrayList<>();
		FileNames.add(selectFilecheckbox(date, "Master"));
		FileNames.add(selectFilecheckbox(date, "PSSLF"));
		FileNames.add(selectFilecheckbox(date, "Market"));
		Thread.sleep(1000);
		List<String> cleanedNames = new ArrayList<>();
		for (String filename : FileNames) {
			cleanedNames.add(filename.replaceAll("\\.\\w+$", ""));
		}
		String formattedString = String.join("; ", cleanedNames);
		clickexportBaseDataBtn();
		return formattedString;
	}

	public List<WebElement> selectReportDate1() {
		Actions act = new Actions(driver);
		waitforclickable(ReportDate);
		act.click(ReportDate).perform();
		String date = uploadDate();
		WebElement date1 = driver.findElement(By.xpath("(//td[@title='" + date + "'])[1]"));
		date1.click();
		return ReportDateColumn;
	}

	public String formattedDate() {
		String inputDate = uploadDate();
		LocalDate date = LocalDate.parse(inputDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String formattedDate = date.format(formatter);
		return formattedDate;
	}

	public void clickSelectFundDropdown() {
		waitforclickable(selectFundDropdown);
		selectFundDropdown.click();
	}

	public List<WebElement> filteredPCOFValue() {
		clickSelectFundDropdown();
		PCOFOption.click();
		return PCOFfundTypeColumn;
	}

	public List<WebElement> filteredPFLTValue() {
		clickSelectFundDropdown();
		PFLTOption.click();
		return PFLTfundTypeColumn;
	}

	public List<WebElement> filteredPSSLValue() {
		clickSelectFundDropdown();
		PSSLOption.click();
		return PSSLfundTypeColumn;
	}

	public void clickOnUnArchieveBtn() {
		unArchieveBtn.click();
	}

	public List<WebElement> getFileNames() {
		waitforElementAppear(ArchievedfileNames.get(0));
		return ArchievedfileNames;

	}

	public void selectFileCheckbox() {
		fileCheckbox.click();
	}

	public void selectArchieveFileCheckbox() {
		archieveFileCheckbox.click();
	}

	public String fileName() {
		return fileName.getText();
	}

	public String archieveFileName() {
		waitforElementAppear(archivedFileName);
		return archivedFileName.getText();
	}

	public void clickOnArchieveBtn() {
		waitforclickable(addToArchieveBtn);
		addToArchieveBtn.click();
	}

	public void clickOnSwitcher() {
		waitforclickable(switcher);
		Actions act = new Actions(driver);
		act.click(switcher).perform();
		System.out.println("***");
	}

	public String getToastmsg() {
		getToastMsg.click();
		return getToastMsg.getText();
	}

	public void clickUploadFilesBtn() {
		driver.manage().window().maximize();
		UploadFilesBtn.click();
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
		System.out.println(date);
		WebElement date1 = driver.findElement(By.xpath("(//td[@title='" + date + "'])[1]"));
		date1.click();
	}

	public void selectPFLTCheckbox() {
		PFLTCheckbox.click();
	}

	public void selectFundCheckbox(String FundType) {
		WebElement checkbox = driver.findElement(
				By.xpath("//span[text()='" + FundType + "']/preceding-sibling::span[contains(@class,'ant-checkbox')]"));
		checkbox.click();
	}

	public void selectPCOFCheckbox() {
		PCOFCheckbox.click();
	}

	public void uploadMasterFile2() throws InterruptedException, IOException {
		waitforElementAppear(DragandDropField1);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Thread.sleep(500);
		ProcessBuilder processBuilder = new ProcessBuilder(
				"C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\uploaded application\\Master File Upload.exe");
		processBuilder.start();
		Thread.sleep(500);
	}

	public void uploadDuplicateFile() throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		selectPFLTCheckbox();
		uploadMasterFile2();
		Thread.sleep(500);
		clickLoadButton();
		Thread.sleep(10000);
	}

	public String uploadMasterFile(String Fund) throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		if (Fund.equals("PCOF")) {
			selectPCOFCheckbox();
		} else if (Fund.equals("PFLT")) {
			selectPFLTCheckbox();
		} else if (Fund.equals("ALL")) {
			selectPFLTCheckbox();
			selectPCOFCheckbox();
		}
		String newFileName = uploadNewMasterFile();
		Thread.sleep(500);
		clickLoadButton();
		return newFileName;
	}

	public String uploadNewMasterFile() throws InterruptedException, IOException, AWTException {
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String originalFilePath = prop.getProperty("MasterFile");
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "Master Comps v1175_" + timestamp + ".xlsx";
		String RenameFolderPath = prop.getProperty("RenamedFolderPath");
		String newFilePath = RenameFolderPath + newFileName;
		File originalFile = new File(originalFilePath);
		File renamedFile = new File(newFilePath);
		Files.copy(originalFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection(newFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		return newFileName;
	}

	public String uploadCashFile(String Fund) throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		if (Fund.equals("PCOF")) {
			selectFundCheckbox(Fund);
		} else if (Fund.equals("PFLT")) {
			selectFundCheckbox(Fund);
		} else if (Fund.equals("PSSL")) {
			selectFundCheckbox(Fund);
		} else if (Fund.equals("ALL")) {
			selectPCOFCheckbox();
			selectPFLTCheckbox();
		}
		String newFileName = uploadNewCashFile();
		Thread.sleep(500);
		clickLoadButton();
		Thread.sleep(20000);
		return newFileName;
	}

	public String uploadNewCashFile() throws InterruptedException, IOException, AWTException {
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String originalFilePath = prop.getProperty("CashFile");
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "Copy of PENPL_CashFile_20250630_" + timestamp + ".xlsm";
		String RenameFolderPath = prop.getProperty("RenamedFolderPath");
		String newFilePath = RenameFolderPath + newFileName;
		System.out.println(newFilePath);
		File originalFile = new File(originalFilePath);
		File renamedFile = new File(newFilePath);
		Files.copy(originalFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection(newFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		return newFileName;
	}

	public String uploadMarketFile(String Fund) throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		if (Fund.equals("PCOF")) {
			selectPCOFCheckbox();
		} else if (Fund.equals("PFLT")) {
			selectPFLTCheckbox();
		} else if (Fund.equals("ALL")) {
			selectPFLTCheckbox();
			selectPCOFCheckbox();
		}
		String newFileName = uploadNewMarketFile();
		Thread.sleep(500);
		clickLoadButton();
		Thread.sleep(10000);
		return newFileName;
	}

	public String uploadNewMarketFile() throws InterruptedException, IOException, AWTException {
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String originalFilePath = prop.getProperty("MarketFile");
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "Market and Book Value Position_" + timestamp + ".csv";
		String newFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\"
				+ newFileName;
		File originalFile = new File(originalFilePath);
		File renamedFile = new File(newFilePath);
		Files.copy(originalFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection(newFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		return newFileName;
	}

	public String uploadCashFileforPSSL(String Fund) throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		if (Fund.equals("PCOF")) {
			selectFundCheckbox(Fund);
		} else if (Fund.equals("PFLT")) {
			selectFundCheckbox(Fund);
		} else if (Fund.equals("PSSL")) {
			selectFundCheckbox(Fund);
		} else if (Fund.equals("ALL")) {
			selectPCOFCheckbox();
			selectPFLTCheckbox();
		}
		String newFileName = uploadNewCashFileforPSSL();
		Thread.sleep(500);
		clickLoadButton();
		Thread.sleep(20000);
		return newFileName;
	}

	public String uploadNewRatingFile() throws InterruptedException, IOException, AWTException {
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String originalFilePath = prop.getProperty("RatingFile");
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "Portfolio Ratings_v102_" + timestamp + ".xlsx";
		String newFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\"
				+ newFileName;
		File originalFile = new File(originalFilePath);
		File renamedFile = new File(newFilePath);
		Files.copy(originalFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection(newFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		return newFileName;
	}

	public String uploadNewCashFileforPSLF() throws InterruptedException, IOException, AWTException {
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String originalFilePath = prop.getProperty("PSLFCashFile");
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "PSLF_CashFile_20250228" + timestamp + ".xlsx";
		String newFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\"
				+ newFileName;
		File originalFile = new File(originalFilePath);
		File renamedFile = new File(newFilePath);
		Files.copy(originalFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection(newFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		return newFileName;
	}

	public String uploadNewCashFileforPSCF() throws InterruptedException, IOException, AWTException {
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String originalFilePath = prop.getProperty("PSCFCashFile");
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "PSCF_CashFile_20250228" + timestamp + ".xlsx";
		String newFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\"
				+ newFileName;
		File originalFile = new File(originalFilePath);
		File renamedFile = new File(newFilePath);
		Files.copy(originalFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection(newFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		return newFileName;
	}

	public String uploadNewCashFileforPSSL() throws InterruptedException, IOException, AWTException {
		Properties prop = new Properties();
		FileInputStream fsi = new FileInputStream((System.getProperty("user.dir")
				+ "//src//main//java//onpepper//Data_Analytics//resources//globaldata.properties"));
		prop.load(fsi);
		String originalFilePath = prop.getProperty("PSSLCashFile");
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "PSSLF221_CashFile_20250228" + timestamp + ".xlsm";
		String newFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\"
				+ newFileName;
		File originalFile = new File(originalFilePath);
		File renamedFile = new File(newFilePath);
		Files.copy(originalFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection(newFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		return newFileName;
	}

	public void clickLoadButton() {
		waitforclickable(LoadBtn);
		LoadBtn.click();
	}

	public boolean get_UploadFileStatus(String fileName) {
		WebElement filestatus = driver.findElement(By.xpath("//td[contains(text(),'" + fileName + "')]"));
		waitforElementAppear(filestatus);
		return filestatus.isDisplayed();
	}
}
