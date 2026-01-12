package onpepper.Data_Analytics.PageObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class BaseDataPreview extends AbstractComponent {

	public WebDriver driver;

	public BaseDataPreview(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[text()='Fund Type']/following-sibling::div//b")
	WebElement FundTypeText;
	@FindBy(xpath = "//span[@aria-label='setting']")
	WebElement settingIcon;
	@FindBy(xpath = "//input[@type='checkbox']")
	List<WebElement> checkboxes;
	@FindBy(xpath = "//div[contains(@class,'_crossIcon')]")
	WebElement closeSettingPopup;
	@FindBy(xpath = "//tr[contains(@class,'_headRow')]/th")
	List<WebElement> tableHeaders;

	@FindBy(xpath = "//div[contains(@class,'_orderColumnContainer')]//span[1]")
	List<WebElement> reorderedColumnNames;
	@FindBy(xpath = "//span[text()='Reorder']/parent::button")
	WebElement reorderButton;
	@FindBy(xpath = "(//div[contains(@class,'_orderColumnContainer')]//span[2])[1]")
	WebElement reorderColumn1;
	@FindBy(xpath = "(//div[contains(@class,'_orderColumnContainer')]//span[2])[3]")
	WebElement reorderColumn2;
	@FindBy(xpath = "(//*[local-name()='svg'])[2]")
	WebElement reorderIcon;
	@FindBy(xpath = "//span[contains(text(),'Bulk Update')]/parent::button")
	WebElement updateSecurityBtn;
	@FindBy(xpath = "//span[contains(text(),'Trigger Calculation')]/parent::button")
	WebElement TriggerCalculationBtn;
	@FindBy(xpath = "//span[contains(text(),'Confirm & Proceed')]/parent::button")
	WebElement ConfirmProceedBtn;

	@FindBy(xpath = "//span[contains(text(),'Save')]/parent::button")
	WebElement saveBtn;
	@FindBy(xpath = "//span[contains(text(),'Confirm')]/parent::button")
	WebElement confirmBtn;
	@FindBy(xpath = "//span[contains(text(),'Save & Trigger')]/parent::button")
	WebElement saveTriggerBtn;

	public void clickTriggerButton() {
		TriggerCalculationBtn.click();
	}

	public void clickConfirmandProceedBtn() throws InterruptedException {
		clickTriggerButton();
		driver.switchTo().activeElement();
		Actions act = new Actions(driver);
		Thread.sleep(1000);
		act.click(ConfirmProceedBtn).perform();
	}

	public void clickSaveTriggerButton() {

		waitforclickable(saveTriggerBtn);
		saveTriggerBtn.click();
	}

	public HomePage handleJavascriptAlert() {
		Alert alt = driver.switchTo().alert();
		alt.accept();
		HomePage home = new HomePage(driver);
		return home;
	}

	public void clickconfirmButton() {
		driver.switchTo().activeElement();
		confirmBtn.click();
		// act.moveToElement(confirmBtn).click(confirmBtn).perform();
	}

	public void clickTriggerCalculationBtn() {
		TriggerCalculationBtn.click();
	}

	public void clickupdateSecurityBtn() {
		updateSecurityBtn.click();
	}

	public List<WebElement> reordercolumn() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.click(reorderIcon).perform();
		Thread.sleep(1000);
		actions.clickAndHold(reorderColumn1).moveToElement(reorderColumn2).release().build().perform();
		Thread.sleep(1000);
		return reorderedColumnNames;
	}

	public void clickReorderButton() {
		reorderButton.click();
	}

	public String getFundType() {
		waitforElementAppear(FundTypeText);
		return FundTypeText.getText();
	}

	public int getIndexofColumnName(String ColumnName) {
		int index = 0;
		for (int i = 0; i < tableHeaders.size(); i++) {
			String title = tableHeaders.get(i).getAttribute("title");

			if (ColumnName.equalsIgnoreCase(title)) {
				index = i + 1;
				break;
			}
		}
		return index;
	}

	public int getUniqueColumnsData(int index) {
		List<WebElement> data = driver.findElements(By.xpath("//td[" + index + "]"));
		Set<String> obligerNames = new LinkedHashSet<>();
		for (WebElement Data : data) {
			String name = Data.getText().trim();
			if (!name.isEmpty()) {
				obligerNames.add(name);
			}
		}
		List<String> uniqueObligerNamesList = new ArrayList<>(obligerNames);
		return uniqueObligerNamesList.size();
	}

	public List<WebElement> getFilteredColumnsData(int index) {
		List<WebElement> data = driver.findElements(By.xpath("//td[" + index + "]"));
		return data;
	}

	public int getColumnsData1(int index) {
		List<WebElement> columnValues = driver.findElements(By.xpath("//tbody//td[" + index + "]"));
		ArrayList<String> values = new ArrayList<String>();
		for (WebElement columnValue : columnValues) {
			String value = columnValue.getText();
			if (value.contentEquals("-")) {
				System.out.println(columnValue.getText());
				values.add(columnValue.getText());
			}

		}
		return values.size();
	}

	public double getColumnsData(int index) throws InterruptedException {
		List<WebElement> data = driver.findElements(By.xpath("//td[" + index + "]"));
		double total = 0.0;
		for (WebElement Data : data) {
			String value = Data.getAttribute("title");
			if (value == null || value.trim().isEmpty()) {
				value = Data.getText().trim();
				if (value == null || value.isEmpty()) {
					value = "0";
				}
			}
			value = value.replaceAll(",", "");
			try {
				double number = Double.parseDouble(value);
				total += number;
			} catch (NumberFormatException e) {
				System.out.println("Error parsing value: " + value);
			}
		}
		return total;
	}

	public void selectSpecificColumns() throws InterruptedException {
		waitforElementAppear(settingIcon);
		settingIcon.click();
		List<String> allowedNames = Arrays.asList("security_name", "total_commitment", "outstanding_principal",
				"obligor_name");
		driver.switchTo().activeElement();
		for (WebElement checkbox : checkboxes) {
			String name = checkbox.getAttribute("name");
			if (allowedNames.contains(name)) {
				if (!checkbox.isSelected()) {
					checkbox.click();
				}
			} else {
				if (checkbox.isSelected()) {
					checkbox.click();
				}
			}
		}
		closeSettingPopup.click();

	}

	public void selectSpecificPCOFColumns() throws InterruptedException {
		waitforElementAppear(settingIcon);
		settingIcon.click();
		List<String> allowedNames = Arrays.asList("investment_cost", "issuer", "investment_name");
		driver.switchTo().activeElement();
		for (WebElement checkbox : checkboxes) {
			String name = checkbox.getAttribute("name");
			if (allowedNames.contains(name)) {
				if (!checkbox.isSelected()) {
					checkbox.click();
				}
			} else {
				if (checkbox.isSelected()) {
					checkbox.click();
				}
			}
		}
		closeSettingPopup.click();

	}
}
