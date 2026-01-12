package onpepper.Data_Analytics.Tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.PageObject.liquidityManagement;
import onpepper.Data_Analytics.TestComponent.BaseTest;
import onpepper.Data_Analytics.TestComponent.Retry;

public class LiquidityManagement extends BaseTest {

	@Test(retryAnalyzer = Retry.class)
	public void verifyAllAvailableFundsAreDisplayedInDisplayFundsSection()
			throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		List<String> expectedFunds = liquid.get_expectedfunds();
		List<String> actualFunds = liquid.get_Liquidity_Fund_Names();
		for (String expectedFund : expectedFunds) {
			if (!actualFunds.contains(expectedFund)) {
				throw new AssertionError("Fund NOT found in UI: " + expectedFund);
			}
		}
	}

	@Test(retryAnalyzer = Retry.class)
	public void select_ReportverifyAllSubfundsAreDisplayedForSelectedFund()
			throws IOException, InterruptedException, AWTException {
		SoftAssert softAssert = new SoftAssert();
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		List<String> expectedFunds = liquid.get_expectedfunds();
		for (String fundname : expectedFunds) {
			int actual_Count = liquid.getActual_SubfundCount(fundname);
			liquid.click_Fund_Checkbox(fundname);
			int expected_Count = liquid.getexpected_SubfundCount(fundname).size();
			softAssert.assertEquals(actual_Count, expected_Count, "subfund count is mismatch for fund " + fundname);
		}
		softAssert.assertAll();
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyAllSubfundsAreDisplayedForSelectedFund() throws IOException, InterruptedException, AWTException {
		SoftAssert softAssert = new SoftAssert();
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		List<String> expectedFunds = liquid.get_expectedfunds();
		Actions act = new Actions(driver);
		for (int i = 0; i < expectedFunds.size(); i++) {
			String fundName = expectedFunds.get(i);
			WebElement fundLabel = driver
					.findElement(By.xpath("//span[normalize-space()='" + fundName + "']/preceding-sibling::span"));
			if (liquid.isFundCheckboxSelected(fundName)) {
				List<WebElement> subfundCheckboxes = liquid.getexpected_SubfundCount(fundName);
				List<WebElement> subfundLabels = liquid.getexpected_SubfundLabel(fundName);
				for (int j = 0; j < subfundLabels.size(); j++) {
					WebElement checkbox = subfundCheckboxes.get(j);
					checkbox.click();
					Thread.sleep(1000);
					String subFundName = subfundLabels.get(j).getText();
					WebElement column=driver.findElement(By.xpath("(//span[text()='"+subFundName+" Current'])[2]"));
					act.moveToElement(column).perform();
					boolean columnStatus = liquid.get_Current_ColumnStatus(subFundName);
					softAssert.assertTrue(columnStatus, "Subfund column is not displayed for: " + subFundName);
				}
			}
			if (i + 1 < expectedFunds.size()) {
				WebElement nextFundLabel = driver.findElement(By
						.xpath("//span[normalize-space()='" + expectedFunds.get(i + 1) + "']/preceding-sibling::span"));
				act.click(nextFundLabel).perform();
				Thread.sleep(2000);
			}

			act.click(fundLabel).perform();
			Thread.sleep(2000);
		}

		softAssert.assertAll();
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyUserCanSelectSpecificSubfundPFLTSPV() throws IOException, InterruptedException, AWTException {
		String subfund = "PFLT_Parent";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		liquid.click_Fund_Checkbox(subfund);
		boolean column_status = liquid.get_Current_ColumnStatus(subfund);
		Assert.assertTrue(column_status, "subfund column is not displayed");
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyDefaultProjectionDateIsDisplayed() throws IOException, InterruptedException, AWTException {
		String subfundname = "PFLT_SPV";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		Assert.assertEquals(liquid.get_calculationcolumn_StatusasperFriday(subfundname), true,
				"column is not displayed");
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyUserCanSelectMultipleProjectionDates() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		boolean column_Status = liquid.SelectMultipleProjectionDates(7);
		Assert.assertEquals(column_Status, true, "column is not displayed");
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyUserCanRemoveProjectionDateUsingCloseIcon()
			throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		boolean column_Status = liquid.RemoveProjectionDateUsingCloseIcon(7);
		Assert.assertEquals(column_Status, true, "column is not displayed");
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifySavedChangesArePersistedForSelectedDate() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		liquid.get_EditData("Unfunded Commitments");
		boolean column_Status = liquid.get_Current_ColumnStatus("PFLT_SPV");
		Assert.assertEquals(column_Status, true, "column is not displayed");
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyCalculationsUpdateAfterEachWhatIfValueChange()
			throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		liquid.get_EditData_ByWIA("Unfunded Commitments");
		boolean column_Status = liquid.get_Current_ColumnStatus("PFLT_SPV");
		Assert.assertEquals(column_Status, true, "column is not displayed");
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyUserCanExportReportSuccessfully() throws IOException, InterruptedException, AWTException {
		String fundName = "all";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		String downloadedFile = liquid.clickExportBtn(fundName);
		Assert.assertTrue(homePage.isFileDownloaded(downloadedFile), "File was not downloaded or is empty!");
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyUserCanImportReportSuccessfully() throws IOException, InterruptedException, AWTException {
		String fundName = "PFLT_SPV";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		liquidityManagement liquid = homePage.navigateToLiquidityManagement();
		String downloadedFile = liquid.clickExportBtn(fundName);
		Assert.assertTrue(homePage.isFileDownloaded(downloadedFile), "File was not downloaded or is empty!");
		liquid.upload_Import_FIle(downloadedFile);		
		liquid.click_UploaBtn();
		boolean column_status = liquid.get_Current_ColumnStatus(fundName);
		Assert.assertTrue(column_status, "subfund column is not displayed");
	
	}
}
