package onpepper.Data_Analytics.PageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
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

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	@FindBy(xpath = "//span[text()='PCOF']/preceding-sibling::span[contains(@class,'ant-checkbox')]")
	WebElement PCOFCheckbox;
	@FindBy(xpath = "(//div[@class='dropzone']//div//span)[1]")
	WebElement DragandDropField1;
	@FindBy(xpath = "//span[contains(text(),'Load')]/parent::button")
	WebElement LoadBtn;
	@FindBy(xpath = "//span[contains(text(),'In Progress')]/ancestor::tr//td[3]")
	List<WebElement> InprogressLists;
	@FindBy(xpath = "//th[@title='Fund']/ancestor::table//tbody//td[3]")
	List<WebElement> fileLists;
	@FindBy(xpath = "//div[contains(@class,'Toastify__toast-icon')]/following-sibling::div")
	WebElement getToastMsg;
	@FindBy(xpath = "(//th[.='Fund']/ancestor::table//tbody//td[.='Completed']/parent::tr//td[1]//input)[1]")
	WebElement fileCheckbox;
	@FindBy(xpath = "(//th[.='Fund']/ancestor::table//tbody//td[.='Completed']/parent::tr//td[3])[1]")
	WebElement fileName;
	@FindBy(xpath = "//span[contains(normalize-space(),'Add to Archives')]/parent::button")
	WebElement addToArchieveBtn;
	@FindBy(xpath = "//span[@class='ant-switch-inner']")
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
	@FindBy(xpath = "//input[@id='fileuploadDatePicker']")
	WebElement reportDate;
	@FindBy(xpath = "//td[2]//span[text()='PFLT']")
	List<WebElement> PFLTfundTypeColumn;
	@FindBy(xpath = "//th[@title='Fund']/parent::tr/parent::thead/following-sibling::tbody//td[4]")
	List<WebElement> ReportDateColumn;
	@FindBy(xpath = "//td[2]//span[text()='PCOF']")
	List<WebElement> PCOFfundTypeColumn;
	@FindBy(xpath = "//input[@placeholder='Report Date']")
	WebElement ReportDate;
	@FindBy(xpath = "(//span[contains(text(),'Completed')]/ancestor::tr//td[text()='03-12-2025']/preceding-sibling::td[1][contains(text(),'PENPL')]/preceding-sibling::td[2]//input)[1]")
	WebElement cashFileCheckbox;
	@FindBy(xpath = "(//span[contains(text(),'Completed')]/ancestor::tr//td[text()='03-12-2025']/preceding-sibling::td[1][contains(text(),'Market')]/preceding-sibling::td[2]//input)[1]")
	WebElement marketFileCheckbox;
	@FindBy(xpath = "(//span[contains(text(),'Completed')]/ancestor::tr//td[text()='03-12-2025']/preceding-sibling::td[1][contains(text(),'Master')]/preceding-sibling::td[2]//input)[1]")
	WebElement masterFileCheckbox;
	@FindBy(xpath = "//span[contains(text(),'<- Base Data')]/parent::button")
	WebElement NavigatetoBaseDataButton;
	@FindBy(xpath = "//span[contains(text(),'Extract Base Data')]/parent::button")
	WebElement exportBaseDataBtn;
	@FindBy(xpath = "(//th[@title='Fund']/parent::tr/parent::thead/following-sibling::tbody//td[7]//span[text()='Completed']/ancestor::tr//td[3][contains(text(),'PENPL')])[1]")
	WebElement cashFileName;
	@FindBy(xpath = "(//th[@title='Fund']/parent::tr/parent::thead/following-sibling::tbody//td[7]//span[text()='Completed']/ancestor::tr//td[3][contains(text(),'Market')])[1]")
	WebElement marketFileName;
	@FindBy(xpath = "(//th[@title='Fund']/parent::tr/parent::thead/following-sibling::tbody//td[7]//span[text()='Completed']/ancestor::tr//td[3][contains(text(),'Master')])[1]")
	WebElement masterFileName;
	@FindBy(xpath = "(//span[contains(text(),'Failed')]/ancestor::tr//td[1]//input)[1]")
	WebElement failedFileCheckbox;
	@FindBy(xpath = "//span[text()='Cancel']/parent::button")
	WebElement cancelBtn;
	
	public String getdragText() {
		return DragandDropField1.getText();
	}
	
	public String selectReportDate11() {
		Actions act = new Actions(driver);
		waitforclickable(ReportDateField);
		act.click(ReportDateField).perform();
		String date = uploadDate();
		WebElement date1 = driver.findElement(By.xpath("(//td[@title='" + date + "'])[1]"));
		String attributeValue=date1.getAttribute("class");
		System.out.println(attributeValue);
		return attributeValue;
	}
	
	
	public void clickCancelBtn() {
		cancelBtn.click();
	}
	public boolean isPFLTCheckboxselected() {
		return PFLTCheckbox.isSelected();
	}
	public boolean isPCOFCheckboxselected() {
		return PCOFCheckbox.isSelected();
	}
	
	public DataIngestion NavigatetoBaseDataButton() throws InterruptedException {
		NavigatetoBaseDataButton.click();
		DataIngestion data = new DataIngestion(driver);
		return data;
	}

	public String clickCashFileCheckbox() {
		cashFileCheckbox.click();
		return cashFileName.getText();
	}

	public String clickMarketFileCheckbox() {
		marketFileCheckbox.click();
		return marketFileName.getText();

	}

	public String clickMasterFileCheckbox() {
		masterFileCheckbox.click();
		return masterFileName.getText();
	}

	public void clickexportBaseDataBtn() {
		exportBaseDataBtn.click();
	}

	public String selectSourceFile() throws InterruptedException {
		List<String> FileNames = new ArrayList<>();
		FileNames.add(clickMasterFileCheckbox());
		FileNames.add(clickCashFileCheckbox());
		FileNames.add(clickMarketFileCheckbox());
		Thread.sleep(4000);
		List<String> cleanedNames = new ArrayList<>();
		for (String filename : FileNames) {
			cleanedNames.add(filename.replaceAll("\\.\\w+$", ""));
		}
		String formattedString = String.join("; ", cleanedNames);
		System.out.println(formattedString);
		clickexportBaseDataBtn();
		return formattedString;
	}

	public List<WebElement> selectReportDate1() {
		Actions act = new Actions(driver);
		waitforclickable(ReportDate);
		act.click(ReportDate).perform();
		String date = uploadDate();
		System.out.println(date);
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

	public void clickOnUnArchieveBtn() {
		unArchieveBtn.click();
	}

	public List<WebElement> getFileNames() {
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
		return archivedFileName.getText();
	}

	public void clickOnArchieveBtn() {
		addToArchieveBtn.click();
	}

	public void clickOnSwitcher() {
		Actions act = new Actions(driver);
		act.click(switcher).perform();
	}

	public String getToastmsg() {
		getToastMsg.click();
		return getToastMsg.getText();
	}

	public void clickUploadFilesBtn() {
		UploadFilesBtn.click();
	}

	public String uploadDate() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		String day = year + "-0" + month + "-" + 12;
		return day;
	}

	public void selectReportDate() {
		Actions act = new Actions(driver);
		waitforclickable(ReportDateField);
		act.click(ReportDateField).perform();
		String date = uploadDate();
		WebElement date1 = driver.findElement(By.xpath("(//td[@title='" + date + "'])[1]"));
System.out.println(date1.getAttribute("class"));
		date1.click();
	}

	public void selectPFLTCheckbox() {
		PFLTCheckbox.click();
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

	public void uploadErrorFile() throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		selectPFLTCheckbox();
		uploadnewErrorFile();
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
		}
		else if (Fund.equals("ALL")) {
			selectPFLTCheckbox();
			selectPCOFCheckbox();
		}
		String newFileName = uploadNewMasterFile();
		Thread.sleep(500);
		clickLoadButton();
		Thread.sleep(25000);
		return newFileName;
	}

	public String uploadCashFile(String Fund) throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		if (Fund.equals("PCOF")) {
			selectPCOFCheckbox();
		} else if (Fund.equals("PFLT")) {
			selectPFLTCheckbox();
		}
		else if (Fund.equals("ALL")) {
			selectPFLTCheckbox();
			selectPCOFCheckbox();
		}
		String newFileName = uploadNewCashFile();
		Thread.sleep(500);
		clickLoadButton();
		Thread.sleep(20000);
		return newFileName;
	}

	public String uploadNewCashFile() throws InterruptedException, IOException, AWTException {
		String originalFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\PENPL_AOD_20241231_v2_108.xlsm";
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "PENPL_AOD_20241231_v2_108_" + timestamp + ".xlsm";
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

	public String uploadNewMasterFile() throws InterruptedException, IOException, AWTException {
		String originalFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\Master Comps v1175 - 11.xlsx";
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "Master Comps v1175_" + timestamp + ".xlsx";
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
	
	public void uploadnewErrorFile() throws InterruptedException, IOException, AWTException {
	String newFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\its-sql-plus-setting-line-and-page-sizes.pdf";
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
	}

	public String uploadMarketFile(String Fund) throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		if (Fund.equals("PCOF")) {
			selectPCOFCheckbox();
		} else if (Fund.equals("PFLT")) {
			selectPFLTCheckbox();
		}
		else if (Fund.equals("ALL")) {
			selectPFLTCheckbox();
			selectPCOFCheckbox();
		}
		String newFileName = uploadNewMarketFile();
		Thread.sleep(500);
		clickLoadButton();
		Thread.sleep(10000);
		return newFileName;
	}
	public void addAllData(String Fund) throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		if (Fund.equals("PCOF")) {
			selectPCOFCheckbox();
		} else if (Fund.equals("PFLT")) {
			selectPFLTCheckbox();
		}
		else if (Fund.equals("ALL")) {
			selectPFLTCheckbox();
			selectPCOFCheckbox();
		}
		uploadNewMarketFile();
		Thread.sleep(500);
		clickCancelBtn();
	}
	
	public String uploadMultipleFile(String Fund) throws InterruptedException, IOException, AWTException {
		clickUploadFilesBtn();
		selectReportDate();
		if (Fund.equals("PCOF")) {
			selectPCOFCheckbox();
		} else if (Fund.equals("PFLT")) {
			selectPFLTCheckbox();
		}
		else if (Fund.equals("ALL")) {
			selectPFLTCheckbox();
			selectPCOFCheckbox();
		}
		String newFileName = uploadNewmultipleFile();
		Thread.sleep(500);
	clickLoadButton();
		Thread.sleep(25000);
		return newFileName;
	}
	
	public String uploadNewmultipleFile() throws InterruptedException, IOException, AWTException {
		String originalFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\Market and Book Value Position_20250120_162325_02.xls";
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "Market and Book Value Position_" + timestamp + ".xls";
		String newFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\"
				+ newFileName;
		File originalFile = new File(originalFilePath);
		File renamedFile = new File(newFilePath);
		Files.copy(originalFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		String originalFile1Path1 = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\PENPL_AOD_20241231_v2_108.xlsm";
		String timestamp1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName1 = "PENPL_AOD_20241231_v2_108_" + timestamp1 + ".xlsm";
		String newFilePath1 = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\"
				+ newFileName1;
		File originalFile1 = new File(originalFile1Path1);
		File renamedFile1 = new File(newFilePath1);
		Files.copy(originalFile1.toPath(), renamedFile1.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Robot rb = new Robot();
		rb.delay(2000);
		String new1=newFileName+"/"+newFileName1;
		StringSelection ss = new StringSelection(newFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		rb.delay(3000);
		act.click(DragandDropField1).perform();
		rb.delay(2000);
		StringSelection ss1 = new StringSelection(newFilePath1);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss1, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		System.out.println(new1);
		return new1;
	}
	
	public String uploadNewMarketFile() throws InterruptedException, IOException, AWTException {
		String originalFilePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\Upoladed Files\\Data Ingestion\\Market and Book Value Position_20250120_162325_02.xls";
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String newFileName = "Market and Book Value Position_" + timestamp + ".xls";
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
		LoadBtn.click();
	}

	public List<WebElement> getInprogressFilesList() {
		return InprogressLists;
	}

	public List<WebElement> getFilesList() {
		return fileLists;
	}
}
