package onpepper.Data_Analytics.PageObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	@FindBy(xpath = "//div[contains(text(),'Import File')]")
	WebElement ImportFileOption;
	@FindBy(xpath = "//div[contains(text(),'Intermediate Metrics')]")
	WebElement IntermediateMetricsOption;
	@FindBy(xpath = "//div[@class='dropzone']//div//span")
	WebElement DragandDropField;
	@FindBy(xpath = "//div[contains(@class,'_container')]//input[@placeholder='Report Date']")
	WebElement ReportDateField;
	@FindBy(xpath = "//a[contains(text(),'Today')]")
	WebElement TodaysDate;
	@FindBy(xpath = "//span[text()='Load']/parent::button")
	WebElement LoadBtn;
	@FindBy(xpath = "((//div[@class='ant-modal-content'])[2]//div[@class='ant-modal-header']//div)[4]")
	WebElement OverWritngText;
	@FindBy(xpath = "//span[text()='Yes']/parent::button")
	WebElement YesBtn;
	@FindBy(xpath = "(//h6/following-sibling::ul//li)[1]")
	WebElement SheetModificationText;
	@FindBy(xpath = "//div[@class='ant-modal-content']//table//tr//td[1]")
	List<WebElement> existingFileNames;
	@FindBy(xpath = "(//th[text()='Fund']/ancestor::table//tbody//td[text()='PCOF']/following-sibling::td)[1]")
	WebElement UseBtnPCOF;
	@FindBy(xpath = "(//th[text()='Fund']/ancestor::table//tbody//td[text()='PFLT']/following-sibling::td)[1]")
	WebElement UseBtnPFLT;
	@FindBy(xpath = "//div[contains(@class,'Toastify__toast-icon')]/following-sibling::div")
	WebElement ToastMsg;
	@FindBy(xpath = "//div[contains(@class,'_tabsParent')]//div[contains(@class,'_tabsDiv')]")
	List<WebElement> OverViewCards;
	@FindBy(xpath = "//div[contains(@class,'_cardHeader')]//div")
	WebElement CardName;
	@FindBy(xpath = "//input[@placeholder='Start date']")
	WebElement StartDateField;
	@FindBy(xpath = "//input[@placeholder='End date']")
	WebElement EndDateField;
	@FindBy(xpath = "(//*[local-name()='text'])[1]")
	WebElement StartDateonChart;
	@FindBy(xpath = "(//*[local-name()='text'])[3]")
	WebElement EndDateonChart;
	@FindBy(xpath = "//label[@for='reportDatePicker']")
	WebElement ReportDate;
	@FindBy(xpath = "//div[contains(@class,'_tabsContainer')]/following-sibling::div")
	WebElement IntermediateMetricsTable;
	@FindBy(xpath = "//div[contains(@class,'_tabsContainer')]/following-sibling::div//tr[1]//td")
	List<WebElement> FirstRowData;
	@FindBy(xpath = "(//div[contains(@class,'_formulaDiv')])[1]//span[1]")
	WebElement FormulaName;
	@FindBy(xpath = "(//div[contains(@class,'_formulaDiv')])[1]//span[2]")
	WebElement FormulaValue;
	@FindBy(xpath = "(//div[contains(@class,'_formulaDiv')])[1]//span[1]")
	WebElement Formula;
	@FindBy(xpath = "//span[text()='Formula']/parent::div/parent::div/parent::div//button[@aria-label='Close']")
	WebElement CloseFormulaBtn;
	@FindBy(xpath = "(//div[@class=\"ant-modal-content\"])[3]")
	WebElement FormulaPopup;
	@FindBy(xpath = "(//div[contains(@class,'_formulaDiv')])[1]//button")
	List<WebElement> ButtonFormulaValue;
	@FindBy(xpath = "(//div[@class='ant-modal-content'])[3]//button")
	WebElement CloseFormulaPopup;
	@FindBy(xpath = "//span[contains(text(),'-- What if Analysis --')]/parent::div")
	WebElement WhatIfDropdown;
	@FindBy(xpath = "//div[@class='ant-select-item-option-content']")
	List<WebElement> WhatIfDropdownOptions;
	@FindBy(xpath = "//div[text()='Add New Asset']")
	WebElement AddNewAssetOption;
	@FindBy(xpath = "//button[text()='Save']")
	WebElement SaveBtn;
	@FindBy(xpath = "//span[text()='Save']/parent::button")
	WebElement SaveBtn2;
	@FindBy(xpath = "(//div[@class='dropzone']//div//span)[1]")
	WebElement DragandDropField1;
	@FindBy(xpath = "//button[text()='Asset Selection']")
	WebElement AssetSelectionBtn;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/parent::table//*[local-name()='svg'])[1]")
	WebElement EditAssetOption;
	@FindBy(xpath = "//button[text()='Create Asset']")
	WebElement CreateAssetBtn;
	@FindBy(xpath = "//button[text()='Modify Asset']")
	WebElement ModifyAssetBtn;
	@FindBy(xpath = "//th[text()='Investment Name']/parent::tr/parent::thead/parent::table//tbody//tr")
	List<WebElement> AssetRows;
	@FindBy(xpath = "//input[@placeholder='Notes']")
	WebElement NotesField;
	@FindBy(xpath = "//button[text()=' Create Asset ']")
	WebElement CreateAssetBtn1;
	@FindBy(xpath = "//th[text()='Investment Name']/parent::tr/parent::thead/parent::table//thead//tr//th//input[@type='checkbox']")
	WebElement AllAssetCheckbox;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/parent::table//tbody//tr//td[1]//input[@type='checkbox'])[1]")
	WebElement AssetCheckbox1;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/parent::table//tbody//tr//td[1]//input[@type='checkbox'])[2]")
	WebElement AssetCheckbox2;
	@FindBy(xpath = "//button[text()=' Submit']")
	WebElement Submitbtn;
	@FindBy(xpath = "//input[@placeholder='Untitled']")
	WebElement UntitledField;
	@FindBy(xpath = "//div[text()='Update Parameters']")
	WebElement UpdateParameterOption;
	@FindBy(xpath = "//span[text()='EBITDA']/preceding-sibling::input")
	WebElement EbitdaRadioBtn;
	@FindBy(xpath = "//span[text()='Leverage']/preceding-sibling::input")
	WebElement LeverageRadioBtn;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/following-sibling::tbody//tr//td[1]//input[@type='checkbox'])[3]")
	WebElement ParameterCheckbox1;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/following-sibling::tbody//tr//td[1]//input[@type='checkbox'])[4]")
	WebElement ParameterCheckbox2;
	@FindBy(xpath = "//input[contains(@placeholder,'Range:')]")
	WebElement RangeField;
	@FindBy(xpath = "(//input[contains(@placeholder,'Range:')]/following-sibling::button)[1]")
	WebElement AcceptBtn;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/following-sibling::tbody//tr//td[4])[3]")
	WebElement UpdatedValueField;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/following-sibling::tbody//tr//td[5])[3]")
	WebElement UpdatedText1;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/following-sibling::tbody//tr//td[6])[3]")
	WebElement UpdatedText2;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/following-sibling::tbody//tr//td[5])[4]")
	WebElement UpdatedText3;
	@FindBy(xpath = "(//th[text()='Investment Name']/parent::tr/parent::thead/following-sibling::tbody//tr//td[6])[4]")
	WebElement UpdatedText4;
	@FindBy(xpath = "//div[text()='What if analysis library']")
	WebElement WhatifanalysisOption;
	@FindBy(xpath = "//button[text()='Use']")
	WebElement UseBtn;
	@FindBy(xpath = "//th[text()='Name']/parent::tr/parent::thead//th")
	List<WebElement> HeadNames;
	@FindBy(xpath = "//th[text()='Name']/parent::tr/parent::thead/following-sibling::tbody//tr[1]//td[1]//input")
	WebElement BaseFileRadio1;
	@FindBy(xpath = "//span[contains(.,'Select Fund')]")
	WebElement selectFundDropdown;
	@FindBy(xpath = "//div[text()='PCOF']")
	WebElement PCOFvalue;
	@FindBy(xpath = "//th[text()='Report Date']/ancestor::table//tbody//td[2]")
	List<WebElement> reportDates;
	@FindBy(xpath = "//div[text()='PFLT']")
	WebElement PFLTvalue;
	@FindBy(xpath = "(//div[contains(@class,'_formulaDiv')])[1]//button")
	List<WebElement> formulas;
	@FindBy(xpath = "//button[@aria-label='close']")
	WebElement closeToast;
	@FindBy(xpath = "(//div[contains(@class,'sidebarContainer')]//div)[4]")
	WebElement dataIngestionBtn;
	@FindBy(xpath = "//span[contains(.,'List of Existing Files')]/preceding-sibling::span")
	WebElement listofExistingFileRadioBtn;
	@FindBy(xpath = "//span[contains(.,'Upload File')]/preceding-sibling::span")
	WebElement UploadFileRadioBtn;
	
	public void selectListofExistingFileRadioBtn() {
		waitforclickable(listofExistingFileRadioBtn);
		Actions act = new Actions(driver);
		act.click(listofExistingFileRadioBtn).perform();
	}
	
	public void selectUploadFileRadioBtn() {
		waitforclickable(UploadFileRadioBtn);
		Actions act = new Actions(driver);
		act.click(UploadFileRadioBtn).perform();
	}
	public DataIngestion navigateToDataIngestion() {
		dataIngestionBtn.click();
		DataIngestion data=new DataIngestion(driver);
		return data;
	}
	
	public void closeToast() {
		waitforclickable(closeToast);
		closeToast.click();
	}

	public SelectAsset clickPFLTUseBtn() throws InterruptedException {
		waitforclickable(listofExistingFileRadioBtn);
		Actions act = new Actions(driver);
		act.click(listofExistingFileRadioBtn).perform();
		waitforElementAppear(UseBtnPFLT);
		act.moveToElement(UseBtnPFLT).perform();
		UseBtnPFLT.click();
		//waitforElementAppear(closeToast);
		//closeToast();
		SelectAsset select = new SelectAsset(driver);
		return select;
	}

	public SelectAsset clickUsePFLTBtn() throws InterruptedException {
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		act.moveToElement(UseBtnPFLT).perform();
		UseBtnPFLT.click();
		SelectAsset select = new SelectAsset(driver);
		return select;
	}

	public SelectAsset clickUseBtn() {
		selectListofExistingFileRadioBtn();
		waitforElementAppear(UseBtnPCOF);
		Actions act = new Actions(driver);
		act.moveToElement(UseBtnPCOF).perform();
		UseBtnPCOF.click();
		closeToast();
		SelectAsset select = new SelectAsset(driver);
		return select;
	}

	public PCOF_Dashboard navigatetoPCOF() {
		PCOF_Dashboard pcof = new PCOF_Dashboard(driver);
		return pcof;
	}

	public List<WebElement> getAllReportsDates() {
		return reportDates;
	}

	public String uploadDate() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		String day = year + "-0" + month + "-" + 17;
		
		return day;
	}

	public void selectReportDate() {
		Actions act=new Actions(driver);
		waitforclickable(ReportDateField);
		act.click(ReportDateField).perform();
		String date = uploadDate();
		System.out.println("(//td[@title='" + date + "'])[1]");
		WebElement date1 = driver.findElement(By.xpath("(//td[@title='" + date + "'])[1]"));
		date1.click();
	}

	public String uploadPFLTDate() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		String day = year + "-0" + month + "-" + 13;
		return day;
	}

	public void selectPFLTReportDate() throws InterruptedException {
		Thread.sleep(500);
		ReportDateField.click();
		Thread.sleep(500);
		String date = uploadPFLTDate();
		driver.findElement(By.xpath("(//td[@title='" + date + "'])[1]")).click();
	}

	public void selectfundrodown() {
		selectFundDropdown.click();
	}

	public void selectPCOFvalue() {
		selectfundrodown();
		PCOFvalue.click();
	}

	public void selectPFLTvalue() {
		selectfundrodown();
		PFLTvalue.click();
	}

	public void retriveBasefile() throws InterruptedException {
		clickWhatifanalysisOption();
		Actions act = new Actions(driver);
		act.click(BaseFileRadio1).perform();
		UseBtn.click();
	}

	public void clickWhatifanalysisOption() throws InterruptedException {
		Actions act = new Actions(driver);
		act.click(OptionBtn).perform();
		WhatifanalysisOption.click();
	}

	public List<WebElement> getHeadNames() throws InterruptedException {
		clickWhatifanalysisOption();
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		return HeadNames;
	}

	public void updateMultipleFieldValue() {
		ParameterCheckbox1.click();
		ParameterCheckbox2.click();
		RangeField.sendKeys("80");
		AcceptBtn.click();
	}

	public void selectEBITDARadio() {
		EbitdaRadioBtn.click();
	}

	public void selectLeverageRadioBtn() {
		LeverageRadioBtn.click();
	}

	public void updateFieldValue() {
		UpdatedValueField.click();
		Actions act = new Actions(driver);
		act.moveToElement(UpdatedValueField).click().build().perform();
		act.sendKeys("80").build().perform();
	}

	public String getUpdatedText1value()  {
		waitforElementAppear(UpdatedText1);
		return UpdatedText1.getText();
	}

	public String getUpdatedText2value() {
		waitforElementAppear(UpdatedText2);
		return UpdatedText2.getText();
	}

	public String getUpdatedText3value() {
		waitforElementAppear(UpdatedText3);
		return UpdatedText3.getText();
	}

	public String getUpdatedText4value() {
		waitforElementAppear(UpdatedText4);
		return UpdatedText4.getText();
	}

	public void selectUpdateParameterOption() {
		WhatIfDropdown.click();
		UpdateParameterOption.click();
	}

	public void updateUniqueEBITDAValue() {
		selectEBITDARadio();
		updateFieldValue();
	}

	public void sendUntitledField() {
		UntitledField.sendKeys("Test1");

	}

	public void editAssetRow() throws InterruptedException, IOException {
		waitforElementAppear(WhatIfDropdown);
		WhatIfDropdown.click();
		waitforElementAppear(AddNewAssetOption);
		AddNewAssetOption.click();
		uploadAssetExcelSheet1();
		//waitforclickable(AssetSelectionBtn);
		AssetSelectionBtn.click();
	}

	public void clickAllAssetCheckbox() throws InterruptedException {
		AllAssetCheckbox.click();
	}

	public void clickAssetCheckbox() {
		AssetCheckbox1.click();
	}

	public void selectAsset() throws InterruptedException, IOException {
		editAssetRow();
		clickAllAssetCheckbox();
		clickAssetCheckbox();
		Submitbtn.click();
		LoadBtn.click();
		sendUntitledField();
		clickSaveButton();
		clickSaveButton2();
	}

	public void clickEditAssetOption() {
		EditAssetOption.click();
	}

	public int getAssetRowCount() {
		int count = AssetRows.size();
		return count;
	}

	public void clickCreateAssetBtn1() {
		CreateAssetBtn1.click();
		waitforElementAppear(CreateAssetBtn);
		CreateAssetBtn.click();
	}

	public void clickCreateAssetBtn()  {
		clickEditAssetOption();
		waitforElementAppear(CreateAssetBtn);
		CreateAssetBtn.click();
	}

	public void clickModifyAssetBtn() {
		clickEditAssetOption();
		waitforElementAppear(ModifyAssetBtn);
		ModifyAssetBtn.click();
	}

	public void clickSaveButton() {
		SaveBtn.click();
	}

	public void clickSaveButton2() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		NotesField.sendKeys("Test");
		SaveBtn2.click();
	}

	public void UploadAssetFile() throws InterruptedException, IOException {
		WhatIfDropdown.click();
		waitforElementAppear(AddNewAssetOption);
		AddNewAssetOption.click();
		uploadAssetExcelSheet1();
		LoadBtn.click();
		clickSaveButton();
		clickSaveButton2();

	}

	public void uploadAssetExcelSheet1() throws InterruptedException, IOException {
		waitforElementAppear(DragandDropField1);
		Actions act = new Actions(driver);
		act.click(DragandDropField1).perform();
		Thread.sleep(500);
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\uploaded application\\Asset fileupload.exe");
		processBuilder.start(); 
		Thread.sleep(500);
	}

	public List<WebElement> getWhatIfDropdownoptions() {
		WhatIfDropdown.click();
		return WhatIfDropdownOptions;
	}

	public WebElement getWhatIfDropdown() {
		return WhatIfDropdown;
	}

	public void selectReportDateData() {
		Actions act = new Actions(driver);
		act.click(ReportDate).perform();
		String date = uploadDate();
		driver.findElement(By.xpath("//td[@title='" + date + "']")).click();
	}

	public String getToastmsg() {
		waitforElementAppear(ToastMsg);
		return ToastMsg.getText();
	}

	public void clickOnImportFileOption() {
		waitforElementAppear(OptionBtn);
		waitforclickable(OptionBtn);
		Actions act = new Actions(driver);
		act.click(OptionBtn).perform();
		ImportFileOption.click();
	}

	public void clickOnImportFileOption2() {
		waitforElementAppear(OptionBtn);
		waitforclickable(OptionBtn);
		Actions act = new Actions(driver);
		//act.moveToElement(OptionBtn).perform();
		act.click(OptionBtn).perform();
		ImportFileOption.click();
	}

	public String getDragandDropFieldText(){
		waitforElementAppear(DragandDropField);
		return DragandDropField.getText();
	}

	public void uploadExcelSheet() throws InterruptedException, IOException {
		DragandDropField.click();
		Thread.sleep(500);
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\uploaded application\\mainfileupload.exe");
		processBuilder.start(); 
		Thread.sleep(500);
	}

	public void uploadPFLTExcelSheet() throws InterruptedException, IOException {
		DragandDropField.click();
		Thread.sleep(500);
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\uploaded application\\PLFTFileupload.exe");
		
		processBuilder.start(); 
		Thread.sleep(500);
	}

	public void uploadPFLTExcelSheet2() throws InterruptedException, IOException {
		DragandDropField.click();
		Thread.sleep(500);
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\uploaded application\\PLFTFileupload.exe");
		processBuilder.start(); 
		Thread.sleep(500);
	}

	public String getUpdatedHTML() {
		return ReportDateField.getAttribute("outerHTML");
	}

	public void clickOnLoadBtn() throws InterruptedException {
		LoadBtn.click();
		Thread.sleep(1000);
	}

	public String getOverWritingText(){
		waitforElementAppear(OverWritngText);
		return OverWritngText.getText();
	}

	public void uploadErrorExcelSheet() throws InterruptedException, IOException {
		DragandDropField.click();
		Thread.sleep(500);
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\uploaded application\\errorfileupload.exe");
		processBuilder.start(); 
		Thread.sleep(500);
	}

	public void uploadPFLTErrorExcelSheet() throws InterruptedException, IOException {
		DragandDropField.click();
		Thread.sleep(500);
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\uploaded application\\errorfileupload.exe");
		processBuilder.start(); 
		Thread.sleep(500);
	}

	public void clickYesBtn() throws InterruptedException {
		waitforElementAppear(YesBtn);
		Actions act = new Actions(driver);
		act.click(YesBtn).perform();
		Thread.sleep(1000);
	}

	public String getSheetModificationText() {
		waitforElementAppear(SheetModificationText);
		return SheetModificationText.getText();
	}

	public SelectAsset NavigatetoSelectAsset() {
		YesBtn.click();
		SelectAsset select = new SelectAsset(driver);
		return select;
	}

	public List<WebElement> getUploadedFileNames() {
		return existingFileNames;
	}

	public List<WebElement> getOverviewCards() {
		return OverViewCards;
	}

	public String getCardName() {
		return CardName.getText();
	}

	public void selectRangeDate(String StartDate, String EndDate) {
		StartDateField.sendKeys(StartDate);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
		EndDateField.sendKeys(EndDate);
		act.sendKeys(Keys.ENTER).perform();
	}

	public String getActualStartDate() {
		return StartDateonChart.getText();
	}

	public String getActualEndDate() {
		return EndDateonChart.getText();
	}

	public void clickOnIntermediateMetrics()  {
		waitforElementAppear(OptionBtn);
		Actions act = new Actions(driver);
		act.click(OptionBtn).perform();
		IntermediateMetricsOption.click();
	}

	public WebElement getIntermediateMetricsTable() {
		waitforElementAppear(IntermediateMetricsTable);
		return IntermediateMetricsTable;
	}

	public List<WebElement> getIntermediateMetricsTableData() {
		return FirstRowData;
	}

	public void closePopup() {
		CloseFormulaBtn.click();
	}

	public String getFormulaValue() {
		return FormulaValue.getText();
	}

	public String getFormulaValuecolor() {
		return FormulaValue.getAttribute("style");
	}

	public String getFormulacolor() {
		return Formula.getAttribute("style");
	}

	/*
	 * public String getButtonFormulaColor() { return
	 * ButtonFormulaValue.getAttribute("style"); }
	 */
	public String getButtonFormulaValue() {
		int numberofBtn = ButtonFormulaValue.size();
		StringBuilder buttonTexts = new StringBuilder();
		for (int i = 1; i <= numberofBtn; i++) {
			String path = "(//div[contains(@class,'_formulaDiv')])[1]//button[" + i + "]";
			WebElement button = driver.findElement(By.xpath(path));
			String buttonText = button.getText().trim();
			buttonTexts.append(buttonText).append(" ");
		}
		return buttonTexts.toString().trim();
	}

	public String getButtonFormulaValuecolor() {
		int numberofBtn = ButtonFormulaValue.size();
		StringBuilder buttonTexts = new StringBuilder();
		for (int i = 1; i <= numberofBtn; i++) {
			String path = "(//div[contains(@class,'_formulaDiv')])[1]//button[" + i + "]";
			WebElement button = driver.findElement(By.xpath(path));
			String buttonText = button.getAttribute("style");
			buttonTexts.append(buttonText).append("****");
		}
		return buttonTexts.toString().trim();

	}

	public WebElement formulaPopup() {
		String path = "(//div[contains(@class,'_formulaDiv')])[1]//button[1]";
		WebElement button = driver.findElement(By.xpath(path));
		/*
		 * Actions act=new Actions(driver); act.click(button).perform();
		 */
		return button;
	}

	public List<WebElement> formulas() {
		return formulas;
	}

	public void closeFormulaPopup() {
		Actions act = new Actions(driver);
		act.click(CloseFormulaPopup).perform();
	}

	public WebElement getToastpopup() {
		return ToastMsg;
	}

}
