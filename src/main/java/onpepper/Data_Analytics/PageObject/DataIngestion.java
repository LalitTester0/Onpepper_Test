package onpepper.Data_Analytics.PageObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	@FindBy(xpath = "//span[contains(.,'+ Extract New Base Data')]/parent::button")
	WebElement extractNewBaseBtn;
	@FindBy(xpath = "(//td[text()='2025-04-10']/following-sibling::td[4])[1]")
	WebElement baseFileasperDate;
	@FindBy(xpath = "//span[@title='-- Select Fund --']")
	WebElement selectFundDropdown;
	@FindBy(xpath = "//div[contains(text(),'PCOF')]")
	WebElement PCOFOption;
	@FindBy(xpath = "//div[@title='PFLT']")
	WebElement PFLTOption;
	@FindBy(xpath = "//div[@title='PSSL']")
	WebElement PSSLOption;
	@FindBy(xpath = "//td[3]//span[text()='PFLT']")
	List<WebElement> PFLTfundTypeColumn;
	@FindBy(xpath = "//td[3]//span[text()='PSSL']")
	List<WebElement> PSSLfundTypeColumn;
	@FindBy(xpath = "//td[3]//span[text()='PCOF']")
	List<WebElement> PCOFfundTypeColumn;
	@FindBy(xpath = "//td[contains(text(),'No Data')]")
	WebElement NoDataCell;
	@FindBy(xpath = "//div[text()='Source Files']")
	WebElement SourceFiletab;
	@FindBy(xpath = "//span[contains(text(),' Extract New Base Data')]/parent::button")
	WebElement ExtractNewBaseDataBtn;

	public SourceFileLists select_SourceFileTab() {
		SourceFiletab.click();
		SourceFileLists source = new SourceFileLists(driver);
		return source;
	}

	public BaseDataPreview clickOnBaseData(String Fundtype, String Status) throws InterruptedException {
		// filteredPCOFValue();
		previewBtn = driver.findElement(
				By.xpath("(//span[text()='" + Fundtype + "']/ancestor::td/following-sibling::td//div//span[text()='"
						+ Status + "']/ancestor::td/following-sibling::td//div[text()='Preview Base Data'])[1]"));
		// Thread.sleep(5000);
		waitforclickable(previewBtn);
		Actions act = new Actions(driver);
		act.click(previewBtn).perform();
		// previewBtn.click();
		BaseDataPreview base = new BaseDataPreview(driver);
		return base;
	}

	public ExtractNewBaseData select_ExtractNewBaseDataBtn() {
		ExtractNewBaseDataBtn.click();
		ExtractNewBaseData Data = new ExtractNewBaseData(driver);
		return Data;
	}

	public String noDataCellText() {
		return NoDataCell.getText();
	}

	public void clickSelectFundDropdown() {
		selectFundDropdown.click();
	}

	public List<WebElement> filteredPCOFValue() throws InterruptedException {
		clickSelectFundDropdown();
		Thread.sleep(1000);
		waitforclickable(PCOFOption);

		PCOFOption.click();
		Thread.sleep(1000);
		for (WebElement fundType : PCOFfundTypeColumn) {
			System.out.println(fundType.getText());
		}
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

	public String getbaseFileasperDate(String FundType) throws InterruptedException {
		waitforElementAppear(baseFileasperDate);
		if (FundType.equals("PCOF")) {
			filteredPCOFValue();
		} else if (FundType.equals("PFLT")) {
			filteredPFLTValue();
		} else if (FundType.equals("PSSL")) {
			filteredPSSLValue();
		}
		// Thread.sleep(10000);
		WebElement tdCell = baseFileasperDate;
		List<WebElement> fileDivs = tdCell.findElements(By.xpath("./div/div"));
		List<String> fileNames = new ArrayList<>();
		for (WebElement div : fileDivs) {
			String fullText = div.getText().trim();
			fullText = fullText.replaceFirst("^\\d+\\.\\s*", "");
			fullText = fullText.replaceFirst("\\.(xlsx|xlsm)$", "");
			if (!fullText.isEmpty()) {
				fileNames.add(fullText);
			}
		}
		Collections.sort(fileNames);
		String finalOutput = String.join("; ", fileNames);
		return finalOutput;
	}

	public boolean verifyAllFileNamesPresent(String separated, String combined) {
		String[] fileNamesToCheck = separated.split(";\\s*");
		boolean allPresent = true;
		for (String file : fileNamesToCheck) {
			if (!combined.contains(file)) {
				allPresent = false;
				System.out.println("Missing file: " + file);
			}
		}
		return allPresent;
	}

	public SourceFileLists clickExtractNewBaseBtn() {
		extractNewBaseBtn.click();
		SourceFileLists source = new SourceFileLists(driver);
		return source;
	}

}
