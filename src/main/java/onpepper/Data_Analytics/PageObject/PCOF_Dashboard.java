package onpepper.Data_Analytics.PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class PCOF_Dashboard extends AbstractComponent {
	public WebDriver driver;

	public PCOF_Dashboard(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//h6/following-sibling::ul//li)[1]")
	WebElement SheetModificationText;
	@FindBy(xpath = "//th[text()='Industry']/ancestor::table//tbody//tr")
	List<WebElement> segmentationRows;
	@FindBy(xpath = "//button[text()='Included']/parent::div//following-sibling::div//table//tbody//tr")
	List<WebElement> actualsegmentationRows;
	@FindBy(xpath = "//th[text()='Security']/ancestor::table//tbody//tr")
	List<WebElement> securityRows;
	@FindBy(xpath = "//div[text()='Segmentation Overview']/parent::div//div[contains(@class,'_chartContainer')]//*[local-name()='g'and @class='recharts-layer recharts-bar-rectangle']")
	List<WebElement> segmentationbar;
	@FindBy(xpath = "//div[text()='Security']/parent::div//div[contains(@class,'_chartContainer')]//*[local-name()='g'and @class='recharts-layer recharts-bar-rectangle']")
	List<WebElement> securityBar;
	@FindBy(xpath = "//button[text()='Included']/parent::div//following-sibling::div//table//tbody//td[2]")
	List<WebElement> bbCell;
	@FindBy(xpath = "//th[text()='Loan Type']/ancestor::table//tbody//tr")
	List<WebElement> LoanTypesRows;
	@FindBy(xpath = "//div[text()='Security']/parent::div//div[contains(@class,'_chartContainer')]//*[local-name()='g'and @class='recharts-layer recharts-bar-rectangle']")
	List<WebElement> loanTypeBar;
	@FindBy(xpath = "//th[text()='Loan Type']/ancestor::table//tbody//td[2]")
	List<WebElement> bbCellofloantype;
	@FindBy(xpath = "(//div[contains(@class,'sidebarContainer')]//div)[2]")
	WebElement dashboardButton;
	@FindBy(xpath = "(//div[contains(@class,'sidebarContainer')]//div)[3]")
	WebElement concentrationTestButton;
	@FindBy(xpath = "//th[text()='Concentration Test']/ancestor::table//tbody//td[6]//button")
	List<WebElement> concentrationSwitchButtons;
	@FindBy(xpath = "//button[text()=' Analyze ']")
	WebElement analyzeButton;
	@FindBy(xpath = "//div[contains(@class,'_updateBtn')]//button")
	WebElement analyzeButton2;
	@FindBy(xpath = "(//div[text()='Fund Type']//following-sibling::div)[1]")
	WebElement fundTypeDropdown;
	@FindBy(xpath = "(//span[text()='Concentration Test']/following-sibling::div)[1]")
	WebElement concentrationTestcount;
	@FindBy(xpath = "(//span[text()='Concentration Test']/following-sibling::div)[2]")
	WebElement passedConcentrationCount;
	@FindBy(xpath = "(//span[text()='Concentration Test']/following-sibling::div)[3]")
	WebElement failedConcentrationCount;
	@FindBy(xpath = "//th[text()='Concentration Test']/ancestor::table//tbody//td[1]")
	List<WebElement> concentrationTestNames;
	@FindBy(xpath = "//th[text()='Concentration Test']/ancestor::table")
	WebElement concentrationTable;
	@FindBy(xpath = "//div[@title='PFLT']//div")
	WebElement PFLTValue;

	public void selectPFLTValue() throws InterruptedException {
		fundTypeDropdown.click();
		PFLTValue.click();;
	}

	public int getmainactualPassedtestvalue(String testName) throws InterruptedException {
	
	Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");
		Thread.sleep(1000);
		WebElement actualvalue = driver
				.findElement(By.xpath("//td[text()='" + testName + "']//following-sibling::td[2]//div"));
		waitforElementAppear(actualvalue);
		System.out.println(actualvalue);
		String actualValuetext = actualvalue.getText();
		String[] splitresult = actualValuetext.split("\\%");
		String mainActualValue = splitresult[0];
		Double actValue = Double.valueOf(mainActualValue) + 1;
		;
		int roundedValue = (int) actValue.doubleValue();

		return roundedValue;

	}

	public int getmainactualFailedtestvalue(String testName) throws InterruptedException {
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");
		Thread.sleep(1000);
		WebElement actualvalue = driver
				.findElement(By.xpath("//td[text()='" + testName + "']//following-sibling::td[2]//div"));
		String actualValuetext = actualvalue.getText();
		String[] splitresult = actualValuetext.split("\\%");
		String mainActualValue = splitresult[0];
		Double actValue = Double.valueOf(mainActualValue) - 1;
		;
		int roundedValue = (int) actValue.doubleValue();
		return roundedValue;

	}

	public Double getactualtestvalue(String testName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");
		WebElement actualvalue = driver
				.findElement(By.xpath("//td[text()='" + testName + "']//following-sibling::td[2]//div"));
		String actualValuetext = actualvalue.getText();
		String[] splitresult = actualValuetext.split("\\%");
		String mainActualValue = splitresult[0];
		Double actValue = Double.valueOf(mainActualValue);
		return actValue;

	}

	public String getcssValue(String testName) throws InterruptedException {
		WebElement actualvalue = driver.findElement(By.xpath("//td[text()='" + testName + "']//following-sibling::td[2]//div"));
		waitforElementAppear(actualvalue);
		String color = actualvalue.getCssValue("background-color");
		return color;
	}

	public void enableconcentration(String testName) {
		navigatetoConcetration();
		Actions act = new Actions(driver);
		WebElement button = driver
				.findElement(By.xpath("//td[text()='" + testName + "']//following-sibling::td[5]//button"));
		String buttonValue = button.getAttribute("aria-checked");
		if (buttonValue.contains("false")) {
			act.moveToElement(button).perform();
			act.click(button).perform();
		}
		waitforElementAppear(analyzeButton2);
		act.moveToElement(analyzeButton2).perform();
		analyzeButton2.click();
		closeToast();
	}

	public void enablePFLTconcentration(String testName) throws InterruptedException  {
		navigatetoConcetration();
		selectPFLTValue();
		Actions act = new Actions(driver);
		WebElement button = driver.findElement(By.xpath("//td[text()='" + testName + "']//following-sibling::td[5]//button"));
		waitforElementAppear(button);
		Point point = button.getLocation();
		String buttonValue = button.getAttribute("aria-checked");
		if (buttonValue.contains("false")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(" + point.getX() + ", " + point.getY() + ");");
			act.click(button).perform();
		}
		act.moveToElement(analyzeButton2).perform();
		waitforElementAppear(analyzeButton2);
		analyzeButton2.click();

	}

	public void enablePFLTconcentration2(String testName) throws InterruptedException  {
		navigatetoConcetration();
		selectPFLTValue();
		Actions act = new Actions(driver);
		WebElement button = driver.findElement(By.xpath("//td[text()='" + testName + "']/following-sibling::td[5]//button"));
		waitforElementAppear(button);
		Point point = button.getLocation();
		String buttonValue = button.getAttribute("aria-checked");
		if (buttonValue.contains("false")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(" + point.getX() + ", " + point.getY() + ");");
			Thread.sleep(500);
			js.executeScript("arguments[0].click();", button);
		}
		act.moveToElement(analyzeButton2).perform();
		waitforElementAppear(analyzeButton2);
		analyzeButton2.click();

	}
	
	public void enableConcentrationTest(String testName, int limitValue) throws InterruptedException {
		navigatetoConcetration2();
		Actions act = new Actions(driver);
		for (int i = 1; i <= concentrationTestNames.size(); i++) {
		
			WebElement concentrationTestName = driver.findElement(
					By.xpath("(//th[text()='Concentration Test']/ancestor::table//tbody//tr)[" + i + "]//td[1]"));
			waitforElementAppear(concentrationTestName);
			WebElement button = driver.findElement(By
					.xpath("(//th[text()='Concentration Test']/ancestor::table//tbody//tr)[" + i + "]//td[6]//button"));
			act.moveToElement(concentrationTestName).perform();
			String buttonValue = button.getAttribute("aria-checked");
			String name = concentrationTestName.getText();
			if (name.equalsIgnoreCase(testName)) {
				if (buttonValue.contains("false")) {
					act.click(button).perform();
				}
				WebElement limit = driver
						.findElement(By.xpath("//td[text()='" + testName + "']//following-sibling::td[3]//input"));
				limit.clear();
				limit.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
				limit.clear();
				Thread.sleep(500);
				String iValue = String.valueOf(limitValue); // Convert the integer to a string

				for (char digit : iValue.toCharArray()) {
					switch (digit) {
					case '0':
						limit.sendKeys(Keys.NUMPAD0);
						break;
					case '1':
						limit.sendKeys(Keys.NUMPAD1);
						break;
					case '2':
						limit.sendKeys(Keys.NUMPAD2);
						break;
					case '3':
						limit.sendKeys(Keys.NUMPAD3);
						break;
					case '4':
						limit.sendKeys(Keys.NUMPAD4);
						break;
					case '5':
						limit.sendKeys(Keys.NUMPAD5);
						break;
					case '6':
						limit.sendKeys(Keys.NUMPAD6);
						break;
					case '7':
						limit.sendKeys(Keys.NUMPAD7);
						break;
					case '8':
						limit.sendKeys(Keys.NUMPAD8);
						break;
					case '9':
						limit.sendKeys(Keys.NUMPAD9);
						break;
					}
				}

			} else if (!name.equalsIgnoreCase(testName)) {
				if (buttonValue.contains("true")) {
					act.click(button).perform();
				}
			}
		}
		waitforElementAppear(analyzeButton2);
		act.moveToElement(analyzeButton2).perform();
		analyzeButton2.click();
		closeToast();
	}

	public void enablePFLTConcentrationTest(String testName, int limitValue) throws InterruptedException {
		navigatetoConcetration2();
		selectPFLTValue();
		Actions act = new Actions(driver);
		for (int i = 1; i <= concentrationTestNames.size(); i++) {
		
			WebElement concentrationTestName = driver.findElement(
					By.xpath("(//th[text()='Concentration Test']/ancestor::table//tbody//tr)[" + i + "]//td[1]"));
			waitforElementAppear(concentrationTestName);
			WebElement button = driver.findElement(By
					.xpath("(//th[text()='Concentration Test']/ancestor::table//tbody//tr)[" + i + "]//td[6]//button"));
			act.moveToElement(concentrationTestName).perform();
			String buttonValue = button.getAttribute("aria-checked");
			String name = concentrationTestName.getText();
			if (name.equalsIgnoreCase(testName)) {
				if (buttonValue.contains("false")) {
					act.click(button).perform();
				}
				WebElement limit = driver
						.findElement(By.xpath("//td[text()='" + testName + "']//following-sibling::td[3]//input"));
				waitforclickable(limit);
				limit.clear();
				Thread.sleep(500);
				limit.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
				limit.clear();
				Thread.sleep(500);
				String iValue = String.valueOf(limitValue); // Convert the integer to a string

				for (char digit : iValue.toCharArray()) {
					switch (digit) {
					case '0':
						limit.sendKeys(Keys.NUMPAD0);
						break;
					case '1':
						limit.sendKeys(Keys.NUMPAD1);
						break;
					case '2':
						limit.sendKeys(Keys.NUMPAD2);
						break;
					case '3':
						limit.sendKeys(Keys.NUMPAD3);
						break;
					case '4':
						limit.sendKeys(Keys.NUMPAD4);
						break;
					case '5':
						limit.sendKeys(Keys.NUMPAD5);
						break;
					case '6':
						limit.sendKeys(Keys.NUMPAD6);
						break;
					case '7':
						limit.sendKeys(Keys.NUMPAD7);
						break;
					case '8':
						limit.sendKeys(Keys.NUMPAD8);
						break;
					case '9':
						limit.sendKeys(Keys.NUMPAD9);
						break;
					}
				}

			} else if (!name.equalsIgnoreCase(testName)) {
				if (buttonValue.contains("true")) {
					act.click(button).perform();
				}
			}
		}
		waitforElementAppear(analyzeButton2);
		act.moveToElement(analyzeButton2).perform();
		analyzeButton2.click();
	}

	public int getConcentrationTestsNumber() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)");
		int count = Integer.parseInt(concentrationTestcount.getText());
		return count;
	}

	public int getpassedConcentrationCount()  {
		int count = Integer.parseInt(passedConcentrationCount.getText());
		return count;
	}

	public int getfailedConcentrationCount() {
		int count = Integer.parseInt(failedConcentrationCount.getText());
		return count;
	}

	public int addPassedFailedCount() {
		int passcount = getpassedConcentrationCount();
		int failcount = getfailedConcentrationCount();
		int summation = passcount + failcount;
		return summation;
	}

	public ArrayList<String> getconcentrationSwitchButtons() {
		navigatetoConcetration();

		List<WebElement> buttons = concentrationSwitchButtons;
		ArrayList<String> testNames = new ArrayList<String>();
		for (int i = 1; i <= buttons.size(); i++) {
			WebElement button = driver.findElement(By
					.xpath("(//th[text()='Concentration Test']/ancestor::table//tbody//tr)[" + i + "]//td[6]//button"));
			WebElement testName = driver.findElement(
					By.xpath("(//th[text()='Concentration Test']/ancestor::table//tbody//tr)[" + i + "]//td[1]"));
			String buttonValue = button.getAttribute("aria-checked");
			if (buttonValue.contains("true")) {
				testNames.add(testName.getText());
			}
		}
		return testNames;
	}

	public ArrayList<String> getconcentrationSwitchButtons2() throws InterruptedException {
		navigatetoConcetration();
		selectPFLTValue();
		//Thread.sleep(10000);
		List<WebElement> buttons = concentrationSwitchButtons;
		ArrayList<String> testNames = new ArrayList<String>();
		WebElement btn1=buttons.get(0);
		waitforElementAppear(btn1);
		
		for (int i = 1; i <= buttons.size(); i++) {
			
			WebElement button = driver.findElement(By
					.xpath("(//th[text()='Concentration Test']/ancestor::table//tbody//tr)[" + i + "]//td[6]//button"));
			//waitforElementAppear(button);
			WebElement testName = driver.findElement(
					By.xpath("(//th[text()='Concentration Test']/ancestor::table//tbody//tr)[" + i + "]//td[1]"));
			String buttonValue = button.getAttribute("aria-checked");
			if (buttonValue.contains("true")) {
				testNames.add(testName.getText());
			}
		}
		return testNames;
	}

	public void navigatetoConcetration() {
		concentrationTestButton.click();
	}

	public void navigatetoConcetration2() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,00)");
		Actions act = new Actions(driver);
		act.click(concentrationTestButton).perform();
	}

	public void navigatetoDashBoard() throws InterruptedException {
		dashboardButton.click();
	}

	public List<WebElement> getbbCell() {
		WebElement bbCell2 = bbCell.get(0);
		waitforElementAppear(bbCell2);
		return bbCell;
	}

	public List<WebElement> getbbCellofLoantype() {
		WebElement bbCellofloantype2 = bbCellofloantype.get(0);
		waitforElementAppear(bbCellofloantype2);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		return bbCellofloantype;
	}

	public List<WebElement> getactualsegmentationRows() {
		driver.switchTo().activeElement();
		return actualsegmentationRows;
	}

	public List<WebElement> getsegmentationRows() {
		return segmentationRows;
	}

	public void clicklastrowsofsegmentationtable() {
		Actions act = new Actions(driver);
		WebElement lastrow = driver.findElement(By
				.xpath("//th[text()='Industry']/ancestor::table//tbody//tr[" + segmentationRows.size() + "]//button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		act.click(lastrow).perform();
	}

	public int getsegmentationRowsSize() {
		int industryCount = getIndustryCount();
		int rowscount = segmentationRows.size() - 1 + industryCount;
		return rowscount;
	}

	public int getIndustryCount() {
		WebElement countText = driver.findElement(By
				.xpath("//th[text()='Industry']/ancestor::table//tbody//tr[" + segmentationRows.size() + "]//button"));
		String countNumber = countText.getText();
		String number = countNumber.split("\\+")[1].trim();
		return Integer.parseInt(number);
	}

	public int getSecurityCount() {
		WebElement countText = driver.findElement(
				By.xpath("//th[text()='Security']/ancestor::table//tbody//tr[" + securityRows.size() + "]//button"));
		String countNumber = countText.getText();
		String number = countNumber.split("\\+")[1].trim();
		return Integer.parseInt(number);
	}

	public int getLoantypeCount() {
		WebElement countText = driver.findElement(
				By.xpath("//th[text()='Loan Type']/ancestor::table//tbody//tr[" + securityRows.size() + "]//button"));
		String countNumber = countText.getText();
		String number = countNumber.split("\\+")[1].trim();
		return Integer.parseInt(number);
	}

	public int getsecurityRowsSize() {
		int securityCount = getSecurityCount();
		int rowscount = securityRows.size() - 1 + securityCount;
		return rowscount;
	}

	public int getLoantypeRowsSize() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		int loanCount = getLoantypeCount();
		int rowscount = LoanTypesRows.size() - 1 + loanCount;
		return rowscount;
	}

	public List<WebElement> getsegmentationBar() {
		return segmentationbar;
	}

	public List<WebElement> getsecurityBar() {
		return securityBar;
	}

	public List<WebElement> getLoanTypeBar() {
		return loanTypeBar;
	}

	public void clicklastrowsofsecuritytable() {
		Actions act = new Actions(driver);
		WebElement lastrow = driver.findElement(By
				.xpath("//th[text()='Security']/ancestor::table//tbody//tr[" + segmentationRows.size() + "]//button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		act.click(lastrow).perform();
	}

}
