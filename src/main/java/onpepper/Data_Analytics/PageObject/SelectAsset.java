package onpepper.Data_Analytics.PageObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class SelectAsset extends AbstractComponent {
	public WebDriver driver;

	public SelectAsset(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//th[text()='+']")
	WebElement SettingBtn;
	@FindBy(xpath = "//div[text()='Asset Parameter Selection']")
	WebElement AssetParameterBtn;
	@FindBy(xpath = "(//div[contains(@class,'_listContainer')]//input)[1]")
	WebElement IssuerCheckBox;
	@FindBy(xpath = "(//div[contains(@class,'_listContainer')]//input)[7]")
	WebElement CostCheckBox;
	@FindBy(xpath = "(//div[contains(@class,'_listContainer')]//input)[13]")
	WebElement PIKCheckBox;
	@FindBy(xpath = "(//div[contains(@class,'_listContainer')]//input)[5]")
	WebElement MaturityCheckBox;
	@FindBy(xpath = "(//div[contains(@class,'_listContainer')]//input)[6]")
	WebElement PARCheckBox;
	@FindBy(xpath = "//div[contains(@class,'_errorMessage')]")
	WebElement ErrorMessage;
	@FindBy(xpath = "//div[contains(@class,'tableContainer')]//th[1]")
	WebElement AllCheckBoxBtn;
	@FindBy(xpath = "//div[contains(@class,'tableContainer')]//tr[2]//td[1]")
	WebElement RowCheckBoxBtn;
	@FindBy(xpath = "//div[contains(@class,'tableContainer')]//tr[3]//td[1]")
	WebElement RowCheckBoxBtn1;
	@FindBy(xpath = "//button[text()='Set']")
	WebElement SetBtn;
	@FindBy(xpath = "//div[contains(@class,'Toastify__toast-icon')]/following-sibling::div")
	WebElement getToastMsg;
	@FindBy(xpath = "//span[text()='View Results']/parent::button")
	WebElement ViewResultBtn;
	@FindBy(xpath = "//button[@aria-label='close']")
	WebElement CloseToastBtn;
	@FindBy(xpath = "//div[@aria-selected='false']")
	List<WebElement> investmentdropdownvalue;
	@FindBy(xpath = "//label[text()='Investment Name']/following-sibling::div")
	WebElement investmentNameDropdown;
	@FindBy(xpath = "//th[text()='Investment Name']/ancestor::table//tbody//td[2]")
	List<WebElement> investmentnames;
	@FindBy(xpath = "//th[text()='Investment Name']/ancestor::table//tbody//td[3]")
	List<WebElement> investmentTypes;
	@FindBy(xpath = "//label[text()='Investment Investment Type']/following-sibling::div")
	WebElement investmentTypeDropdown;
	@FindBy(xpath = "//th[text()='Investment Name']/ancestor::table//tbody//td[5]")
	List<WebElement> investmentIndustry;
	@FindBy(xpath = "//label[text()='Investment Industry']/following-sibling::div")
	WebElement investmentindustryDropdown;
	@FindBy(xpath = "//label[text()='Obligor Name']/following-sibling::div")
	WebElement ObligorNameDropdown;
	@FindBy(xpath = "//th[text()='Obligor Name']/ancestor::table//tbody//td[1]")
	List<WebElement> obligornames;
	@FindBy(xpath = "//label[text()='Loan Type (Term / Delayed Draw / Revolver)']/following-sibling::div")
	WebElement loanTypeDropdown;
	@FindBy(xpath = "//th[text()='Obligor Name']/ancestor::table//tbody//td[2]")
	List<WebElement> loanTypes;
	@FindBy(xpath = "//label[text()='Obligor Industry']/following-sibling::div")
	WebElement obligerindustryDropdown;
	@FindBy(xpath = "//th[text()='Obligor Name']/ancestor::table//tbody//td[6]")
	List<WebElement> obligerIndustry;
	@FindBy(xpath = "//th[.='Investment Industry']//ancestor::table//tbody//td[5]")
	List<WebElement> issuerNames;
	@FindBy(xpath = "//td//input[@type='checkbox']")
	List<WebElement> assetCheckboxes;
	@FindBy(xpath = "//th[.='Security Name']//ancestor::table//tbody//td[5]")
	List<WebElement> LienTypes;

	public List<WebElement> getissuerNames() {
		return issuerNames;
	}
	
	public List<WebElement> getLienTypes() {
		return LienTypes;
	}

	public List<WebElement> getobligerIndustry() {
		return obligerIndustry;
	}

	public void selectobligerindustryDropdown() {
		obligerindustryDropdown.click();
	}

	public void selectinvestmentindustryDropdown() {
		investmentindustryDropdown.click();
	}

	public List<WebElement> getinvestmenttypes() {
		return investmentTypes;
	}

	public List<WebElement> getloantypes() {
		return loanTypes;
	}

	public List<WebElement> getinvestmentindustry() {
		return investmentIndustry;
	}

	public void selectinvestmentTypeDropdown() {
		investmentTypeDropdown.click();
	}

	public void selectloanTypeDropdown() {
		loanTypeDropdown.click();
	}

	public List<WebElement> getdropdownvalues() {
		return investmentdropdownvalue;
	}

	public void selectInvestmentNamedropdown() {
		investmentNameDropdown.click();
	}

	public void selectObligorNamedropdown() {
		ObligorNameDropdown.click();
	}

	public List<WebElement> getinvestmentnames() {
		return investmentnames;
	}

	public List<WebElement> getobligorsnames() {
		return obligornames;
	}

	public void selectAssetCheckbox() throws InterruptedException {
		//closeToast();
		waitforElementAppear(SettingBtn);
		Actions act = new Actions(driver);
		SettingBtn.click();
		driver.switchTo().activeElement();
		Thread.sleep(500);
		act.click(PARCheckBox).perform();
		Thread.sleep(500);
		act.click(CostCheckBox).perform();
		Thread.sleep(500);
		act.click(PIKCheckBox).perform();
		Thread.sleep(500);
		act.click(MaturityCheckBox).perform();
		Thread.sleep(500);
	}

	public String getErrorMsg() throws InterruptedException {
		selectAssetCheckbox();
		return ErrorMessage.getText();
	}

	public String clickOnSetBtn() throws InterruptedException {
		SetBtn.click();
		Thread.sleep(500);
		return getToastMsg.getText();
	}

	public void selectInvestment()  {
		//closeToast();
		waitforElementAppear(AllCheckBoxBtn);
		AllCheckBoxBtn.click();
		RowCheckBoxBtn.click();
		RowCheckBoxBtn1.click();
	}

	

	public HomePage selectuniueInvestment(int issuer1) throws InterruptedException {
		Thread.sleep(500);
		for (WebElement checkbox : assetCheckboxes) {
			String value = checkbox.getAttribute("value");
			if (value.contentEquals("true")) {
				Thread.sleep(2000);
				AllCheckBoxBtn.click();
				AllCheckBoxBtn.click();
				break;
			}
		}
		List<WebElement> getissuerNames = getissuerNames();
		Set<String> uniqueIndustries = new HashSet<>();
		for (int i = 1; i <= getissuerNames.size(); i++) {
			if (uniqueIndustries.size() >= issuer1) {
				break;
			}
			WebElement element = getissuerNames.get(i);
			uniqueIndustries.add(element.getText());
			WebElement checkbox = driver.findElement(By.xpath("(//td[1]//input)[" + i + "]"));
			Actions act = new Actions(driver);
			act.moveToElement(checkbox).perform();
			Thread.sleep(500);
			checkbox.click();
		}
		HomePage page = selectViewResultBtn();
		return page;
	}
	
	public HomePage selectuniueInvestmentPFLT(int issuer1) throws InterruptedException {
		Thread.sleep(500);
		for (WebElement checkbox : assetCheckboxes) {
			String value = checkbox.getAttribute("value");
			if (value.contentEquals("true")) {
				Thread.sleep(1000);
				AllCheckBoxBtn.click();
				AllCheckBoxBtn.click();
				break;
			}
			else  {
				Thread.sleep(1000);
				AllCheckBoxBtn.click();
				AllCheckBoxBtn.click();
				break;
			}
		}
		List<WebElement> getissuerNames = getLienTypes();
		Set<String> uniqueIndustries = new HashSet<>();
		System.out.println(getissuerNames.size());
		for (int i = 1; i <= issuer1; i++) {
			if (uniqueIndustries.size() >= issuer1) {
				break;
			}
			WebElement element = getissuerNames.get(i);
			uniqueIndustries.add(element.getText());
			WebElement checkbox = driver.findElement(By.xpath("(//td[1]//input)[" + i + "]"));
			Actions act = new Actions(driver);
			act.moveToElement(checkbox).perform();
			Thread.sleep(500);
			checkbox.click();
		}
		HomePage page = selectViewResultBtn();
		return page;
	}
	

	public HomePage selectViewResultBtn()  {
		Actions act = new Actions(driver);
		act.click(ViewResultBtn).perform();
		waitforElementAppear(getToastMsg);
		HomePage page = new HomePage(driver);
		return page;
	}

	public String getSuccessfulMsg() {
		waitforElementAppear(ViewResultBtn);
		Actions act = new Actions(driver);
		act.moveToElement(ViewResultBtn).click(ViewResultBtn).perform();
		waitforElementAppear(getToastMsg);
		return getToastMsg.getText();
	}
	

	public String getLoadToastmsg() throws InterruptedException {
		waitforElementAppear(getToastMsg);
	//	System.out.println(getToastMsg);
		return getToastMsg.getText();
	}

	public HomePage selectuniueInvestment2(int issuer1) throws InterruptedException {
		Thread.sleep(2000);
		for (WebElement checkbox : assetCheckboxes) {
			String value = checkbox.getAttribute("value");
			if (value.contentEquals("true")) {
				Thread.sleep(2000);
				AllCheckBoxBtn.click();
				AllCheckBoxBtn.click();
				break;
			}
		}
		List<WebElement> getissuerNames = getissuerNames();
		Set<String> uniqueIndustries = new HashSet<>();
		for (int i = 1; i <= getissuerNames.size(); i++) {
			if (uniqueIndustries.size() >= issuer1) {
				break;
			}
			WebElement element = getissuerNames.get(i);
			uniqueIndustries.add(element.getText());
			WebElement checkbox = driver.findElement(By.xpath("(//td[1]//input)[" + i + "]"));
			Actions act = new Actions(driver);
			act.moveToElement(checkbox).perform();
			Thread.sleep(500);
			checkbox.click();
		}
		HomePage page = selectViewResultBtn();
		return page;
	}

}
