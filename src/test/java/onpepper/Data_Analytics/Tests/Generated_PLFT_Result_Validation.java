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

public class Generated_PLFT_Result_Validation extends BaseTest {

	// As a user I should have different overview cards to see the summarised values
	// of the report on the dashboard.
	@Test(retryAnalyzer = Retry.class)
	public void CheckCardNameforPLFT() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		List<WebElement> Cards = homePage.getOverviewCards();
		for (WebElement Card : Cards) {
			Assert.assertTrue(Card.isDisplayed());
		}
	}

	// As a user I should open the overview cards to view intermediate values
	// involved in the calculation of different cards
	@Test(retryAnalyzer = Retry.class)
	public void checkCardDetailsforPLFT() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		List<WebElement> Cards = homePage.getOverviewCards();
		for (WebElement Card : Cards) {
			Card.click();
			String Cardname = homePage.getCardName();
			Assert.assertNotNull(Cardname, "Card name is not present");
		}
	}

	// check segmentation row count is correct or not
	@Test(retryAnalyzer = Retry.class)
	public void checkSegmentationRowCount() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		int mainrowcount = dashboard.getsegmentationRowsSize();
		dashboard.clicklastrowsofsegmentationtable();
		int actualrowcount = dashboard.getactualsegmentationRows().size();
		Assert.assertEquals(mainrowcount, actualrowcount);
	}

	// check security row count is correct or not
	@Test(enabled = false)
	public void checkLoanTypeRowCount() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		int mainrowcount = dashboard.getLoantypeRowsSize();
		dashboard.clicklastrowsofsecuritytable();
		int actualrowcount = dashboard.getactualsegmentationRows().size();
		Assert.assertEquals(mainrowcount, actualrowcount);
	}

	// check security row count is correct or not
	@Test(retryAnalyzer = Retry.class, enabled = false)
	public void checksecurityBar() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		int mainrowcount = dashboard.getLoanTypeBar().size();
		// dashboard.clicklastrowsofsecuritytable();
		List<WebElement> values = dashboard.getbbCellofLoantype();
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
	//@Test(retryAnalyzer = Retry.class, enabled = false)
	@Test
	public void checksegmentationBar() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		/*PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
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
		Assert.assertEquals(main.size(), mainrowcount);*/
	}

	// only enable concentration tests should get displayed
	// @Test(retryAnalyzer = Retry.class)
	@Test(enabled = false)
	public void concentrationSwitchButtonsvalues() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		ArrayList<String> testNames = dashboard.getconcentrationSwitchButtons2();
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		int testcount = dashboard.getConcentrationTestsNumber();
		Assert.assertEquals(testNames.size(), testcount);
	}

	// passed and failed test count should be equal to enable concentration tests
	 @Test(retryAnalyzer = Retry.class)
	public void compareConcentrationTestNumber() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		ArrayList<String> testNames = dashboard.getconcentrationSwitchButtons2();
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		int addCount = dashboard.addPassedFailedCount();
		Thread.sleep(1000);
		Assert.assertEquals(testNames.size(), addCount);
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedMaxIndConcentration() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (2nd Largest Industry, % BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enablePFLTconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enablePFLTConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
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
		dashboard.enablePFLTconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualFailedtestvalue(testName);
		dashboard.enablePFLTConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedMaxIndConcentrationLargest() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (Largest Industry, % BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enablePFLTconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualPassedtestvalue(testName);
		System.out.println(limitValue);
		dashboard.enablePFLTConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(36, 137, 0, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void FailedMaxIndConcentrationLargest() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (Largest Industry, % BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enablePFLTconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualFailedtestvalue(testName);
		// System.out.println(limitValue);
		dashboard.enablePFLTConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedMaxIndConcentrationbb() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (% BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enablePFLTconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualPassedtestvalue(testName);
		System.out.println(limitValue);
		dashboard.enablePFLTConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(36, 137, 0, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void FailedMaxIndConcentrationbb() throws IOException, InterruptedException {
		String testName = "Max. Industry Concentration (% BB)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enablePFLTconcentration(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		int limitValue = dashboard.getmainactualFailedtestvalue(testName);
		dashboard.enablePFLTConcentrationTest(testName, limitValue);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedIssuer() throws IOException, InterruptedException {
		String testName = "8 or 9 Issuers?";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enablePFLTconcentration2(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.selectuniueInvestmentPFLT(8);
		dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, 8);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(36, 137, 0, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void FailedIssuer() throws IOException, InterruptedException {
		String testName = "8 or 9 Issuers?";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enablePFLTconcentration2(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.selectuniueInvestmentPFLT(10);
		dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, 8);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

	@Test(retryAnalyzer = Retry.class)
	public void PassedminIssuer() throws IOException, InterruptedException {
		String testName = "Min. Eligible Issuers (#)";
		HomePage homePage = page.goTo();
		PCOF_Dashboard dashboard = homePage.navigatetoPCOF();
		dashboard.enablePFLTconcentration2(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.selectuniueInvestmentPFLT(9);
		dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, 8);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
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
		dashboard.enablePFLTconcentration2(testName);
		dashboard.navigatetoDashBoard();
		homePage.closeToast();
		homePage.clickOnImportFileOption2();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUsePFLTBtn();
		asset.selectuniueInvestmentPFLT(7);
		dashboard.getmainactualPassedtestvalue(testName);
		dashboard.enableConcentrationTest(testName, 8);
		dashboard.navigatetoDashBoard();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.clickUsePFLTBtn();
		asset.getSuccessfulMsg();
		dashboard.getactualtestvalue(testName);
		String color = dashboard.getcssValue(testName);
		Assert.assertEquals(color, "rgba(235, 87, 87, 1)");
	}

}
