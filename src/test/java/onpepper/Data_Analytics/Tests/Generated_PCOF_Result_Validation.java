package onpepper.Data_Analytics.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.PageObject.PCOF_Dashboard;
import onpepper.Data_Analytics.PageObject.SelectAsset;
import onpepper.Data_Analytics.TestComponent.BaseTest;
import onpepper.Data_Analytics.TestComponent.Retry;

public class Generated_PCOF_Result_Validation extends BaseTest {

	// As a user I should have different overview cards to see the summarised values
	// of the report on the dashboard.
	@Test(retryAnalyzer = Retry.class)
	public void OBPD014() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		List<WebElement> Cards = homePage.getOverviewCards();
		for (WebElement Card : Cards) {
			Assert.assertTrue(Card.isDisplayed());
		}
	}

	// As a user I should open the overview cards to view intermediate values
	// involved in the calculation of different cards
	@Test(retryAnalyzer = Retry.class)
	public void OBPD014_01() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		List<WebElement> Cards = homePage.getOverviewCards();
		for (WebElement Card : Cards) {
			Card.click();
			String Cardname = homePage.getCardName();
			Assert.assertNotNull(Cardname, "Card name is not present");
			Thread.sleep(1000);
		}
	}

	// check segmentation row count is correct or not
	@Test(retryAnalyzer = Retry.class)
	public void OBPD016() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		int mainrowcount = dashboard.getsegmentationRowsSize();
		dashboard.clicklastrowsofsegmentationtable();
		int actualrowcount = dashboard.getactualsegmentationRows().size();
		Assert.assertEquals(mainrowcount, actualrowcount);
	}

	// check security row count is correct or not
	@Test(retryAnalyzer = Retry.class)
	public void OBPD017() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		int mainrowcount = dashboard.getsecurityRowsSize();
		dashboard.clicklastrowsofsecuritytable();
		int actualrowcount = dashboard.getactualsegmentationRows().size();
		Assert.assertEquals(mainrowcount, actualrowcount);
	}

	// check security row count is correct or not
	@Test(retryAnalyzer = Retry.class)
	public void OBPD017_01() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		int mainrowcount = dashboard.getsecurityBar().size();
		dashboard.clicklastrowsofsecuritytable();
		List<WebElement> values = dashboard.getbbCell();
		ArrayList<String> main = new ArrayList<String>();
		for (WebElement value : values) {
			if (value.getText().equals("$0")) {

			} else {
				main.add(value.getText());
			}
		}
		Assert.assertEquals(main.size(), mainrowcount);
	}

	// check security row count is correct or not
	@Test(retryAnalyzer = Retry.class)
	public void OBPD016_01() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		int mainrowcount = dashboard.getsegmentationBar().size();
		dashboard.clicklastrowsofsegmentationtable();
		List<WebElement> values = dashboard.getbbCell();
		ArrayList<String> main = new ArrayList<String>();
		for (WebElement value : values) {
			if (value.getText().equals("$0")) {

			} else {
				main.add(value.getText());
			}
		}
		Assert.assertEquals(main.size(), mainrowcount);
	}

	// As a user I should select a date on the calendar to dynamically change the
	// dashboard data to selected date
	@Test(retryAnalyzer = Retry.class)
	public void OBPD018() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.selectReportDateData();
		String formattedDate = homePage.uploadDate();
		String message = homePage.getToastmsg();
		boolean datePresent = message.contains(formattedDate);
		Assert.assertTrue(datePresent, "Data is loaded for today's date");
	}

	// As a user I should view intermediate metrics of Borrowing to validate the
	// calculation
	@Test(retryAnalyzer = Retry.class)
	public void OBPD020() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		homePage.closeToast();
		homePage.selectReportDateData();
		homePage.clickOnIntermediateMetrics();
		WebElement IntermediateMetrics = homePage.getIntermediateMetricsTable();
		Assert.assertNotNull(IntermediateMetrics, "Intermediate Metrics is present");
	}

	// As a user I should view mathematical formula and values with color codes
	// involved in calculation of intermediate metric to analyze and validate the
	// calculation
	@Test(retryAnalyzer = Retry.class)
	public void OBPD021() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();

		homePage.selectReportDateData();
		homePage.clickOnIntermediateMetrics();
		List<WebElement> Columns = homePage.getIntermediateMetricsTableData();
		for (WebElement column : Columns) {
			column.click();
			Thread.sleep(500);
			try {
				String formulavaluecolor = homePage.getFormulaValuecolor();
				String formulaColor = homePage.getFormulacolor();
				Assert.assertNotEquals(formulaColor, formulavaluecolor);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				String formulaColor = homePage.getFormulacolor();
				String formulaValueColor = homePage.getButtonFormulaValuecolor();
				String[] splitResult = formulaValueColor.split("\\*\\*\\*\\*");
				for (int i = 0; i < splitResult.length; i++) {
					Assert.assertNotEquals(splitResult[i], formulaColor);
					for (int j = i + 1; j < splitResult.length; j++) {
						Assert.assertNotEquals(splitResult[i], splitResult[j]);

					}
				}

			}
			homePage.closePopup();
		}
	}

	// As a user I should drill-down on the formulas to see the complete calculation
	// involved in the calculation of particular intermediate metric
	@Test(enabled = false)
	public void OBPD022() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnIntermediateMetrics();
		List<WebElement> Columns = homePage.getIntermediateMetricsTableData();
		for (WebElement column : Columns) {
			column.click();
			Thread.sleep(1000);
			try {
				List<WebElement> formulas = homePage.formulas();
				for (WebElement formula : formulas) {
					formula.click();

				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				homePage.closePopup();
			}
		}
	}

	// only enable concentration tests should get displayed
	@Test(retryAnalyzer = Retry.class)
	public void concentrationSwitchButtonsvalues() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		ArrayList<String> testNames = dashboard.getconcentrationSwitchButtons();
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		int testcount = dashboard.getConcentrationTestsNumber();
		Assert.assertEquals(testNames.size(), testcount);
	}

	// passed and failed test count should be equal to enable concentration tests
	@Test(retryAnalyzer = Retry.class)
	public void compareConcentrationTestNumber() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		ArrayList<String> testNames = dashboard.getconcentrationSwitchButtons();
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		int addCount = dashboard.addPassedFailedCount();
		Assert.assertEquals(testNames.size(), addCount);
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedMaxIndConcentration() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (2nd Largest Industry, % BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(36, 137, 0, 1)");

	}

	@Test(retryAnalyzer = Retry.class)
	public void FailedMaxIndConcentration() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (2nd Largest Industry, % BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualFailedtestvalue(testName);
		System.out.println(limitValue);
		dashboard.enableConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedMaxIndConcentrationlargest() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (Largest Industry, % BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(36, 137, 0, 1)");

	}

	@Test(retryAnalyzer = Retry.class)
	public void FailedMaxIndConcentrationlargest() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (Largest Industry, % BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualFailedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedMaxIndustryConcentration() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (% BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(36, 137, 0, 1)");

	}

	@Test(retryAnalyzer = Retry.class)
	public void FailedMaxIndustryConcentration() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (% BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualFailedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedIssuer() throws IOException, InterruptedException {
		String testName = "Number of Issuers";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectuniueInvestment(9);
		dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, 9);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(36, 137, 0, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void FailedIssuer() throws IOException, InterruptedException {
		String testName = "Number of Issuers";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectuniueInvestment(6);
		dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, 8);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	// @Test(retryAnalyzer = Retry.class)
	@Test
	public void PassedminIssuer() throws IOException, InterruptedException {
		String testName = "Min. Eligible Issuers (#)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectuniueInvestment(10);
		dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, 8);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(36, 137, 0, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void FailedminIssuer() throws IOException, InterruptedException {
		String testName = "Min. Eligible Issuers (#)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enableconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectuniueInvestment(6);
		dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, 8);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	// As a user I should upload a file to perform what-if analysis by adding assets
	//@Test(retryAnalyzer = Retry.class)
	@Test
	public void uploadAssetFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.getSuccessfulMsg();
		homePage.closeToast();
		Thread.sleep(4000);
		homePage.UploadAssetFile();
		String Expectedmsg = homePage.getToastmsg();
		String Actualmsg = "Saved";
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
}
