package onpepper.Data_Analytics.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class DataIngestion extends AbstractComponent {
	public WebDriver driver;

	public DataIngestion(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[contains(@class,'_baseDataTableContainer')]//table//tbody//td[3][contains(text(),'completed')]//parent::tr//td[6]//div)[1]")
	WebElement previewBtn;
	@FindBy (xpath = "//div[contains(@class,'_columnContainer')]//input")
	List<WebElement> columncheckboxes;
	@FindBy (xpath = "//div[contains(@class,'_columnContainer')]")
	List<WebElement> columns;
	@FindBy (xpath = "//div[contains(@class,'_columnContainer')]//label")
	List<WebElement> columnLabels;
	@FindBy (xpath = "(//div[contains(@class,'_tableContainer')]/div)[2]//span[@aria-label='setting']")
	WebElement settingIcon;
	@FindBy(xpath="//button[@aria-label='Close']")
	WebElement closeBtn;
	@FindBy(xpath="//div[@class='ant-modal-body']//div[1]")
	WebElement columnValue;
	@FindBy(xpath="//span[contains(.,'+ Extract New Base Data')]/parent::button")
	WebElement extractNewBaseBtn;
	@FindBy(xpath="(//td[@title='03-12-2025']/following-sibling::td[4])[1]")
	WebElement baseFileasperDate;
	
	public String getbaseFileasperDate(){
		return baseFileasperDate.getAttribute("title");
	}
	public SourceFileLists clickExtractNewBaseBtn() {
		extractNewBaseBtn.click();
		SourceFileLists  source=new SourceFileLists(driver);
		return source;
	}
	public void closeBtn() {
		closeBtn.click();
	}
	public List<WebElement> uncheckAllColumn() throws InterruptedException {
		previewBtn.click();
		waitforElementAppear(settingIcon);
		settingIcon.click();
		for (int i =2; i<=10;i++) {
			WebElement check=driver.findElement(By.xpath("(//input)["+i+"]"));
			check.click();
		}
		
		return columns;
	}
	public String getcellValue() throws InterruptedException {
		WebElement cell = driver.findElement(By.xpath("//td[text()='Flairminds Technology LLC']/parent::tr//td[2]"));
		cell.click();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		String cellValue;
		 try {
			 String value = columnValue.getText();
				int startIndex = value.indexOf(": ") + 2; 
				 cellValue = value.substring(startIndex);
		    } catch (StringIndexOutOfBoundsException e) {
		    	cellValue="";
		    }

			return cellValue;
	}
	public void openSettingIcon() {
		settingIcon.click();
	}
	public void unselectAsset(int i) {
		settingIcon.click();
		WebElement check=driver.findElement(By.xpath("(//input)["+i+"]"));
		check.click();
		settingIcon.click();
	}
	public String getExtractValue() throws InterruptedException {
		/*WebElement cell = driver.findElement(By.xpath("//td[text()='Flairminds Technology LLC']/parent::tr//td[2]"));
		cell.click();
		Thread.sleep(2000);*/
		//driver.switchTo().activeElement();
		String FileName = getSourceFile();
		String SheetName = getsheetName();
		String ColumnName = getColumntName();
		ExtractData extract=new ExtractData(driver);
		
		String extractvalue = extract.extractData(FileName, SheetName, ColumnName);
		return extractvalue;
	}
	public String getSourceFile() throws InterruptedException {
		WebElement sourcefile = driver.findElement(By.xpath("//strong[contains(.,'Source file name:')]/parent::div"));
		Thread.sleep(1000);
		String filename = sourcefile.getText();
		int startIndex = filename.indexOf(": ") + 2; 
		int endIndex = filename.lastIndexOf(".xlsx"); 
		String fileNames = filename.substring(startIndex, endIndex);
		return fileNames;
	}
	public String getsheetName() throws InterruptedException {
		WebElement sheet = driver.findElement(By.xpath("//strong[contains(.,'Sheet name:')]/parent::div"));
		//Thread.sleep(1000);
		String sheetname = sheet.getText();
		int startIndex = sheetname.indexOf(": ") + 2; 
		String sheetName = sheetname.substring(startIndex);
		return sheetName;
	}
	public String getColumntName() throws InterruptedException {
		WebElement column = driver.findElement(By.xpath("//strong[contains(.,'Column name:')]/parent::div"));
		//Thread.sleep(1000);
		String columnname = column.getText();
		int startIndex = columnname.indexOf(": ") + 2; 
		String columnName = columnname.substring(startIndex);
		return columnName;
	}
	

}
