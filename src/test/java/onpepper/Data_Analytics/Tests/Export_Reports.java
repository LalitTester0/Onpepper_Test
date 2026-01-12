package onpepper.Data_Analytics.Tests;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import onpepper.Data_Analytics.PageObject.BaseDataPreview;
import onpepper.Data_Analytics.PageObject.DataIngestion;
import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.TestComponent.BaseTest;
import onpepper.Data_Analytics.TestComponent.Retry;

public class Export_Reports extends BaseTest {

	@Test(retryAnalyzer = Retry.class, dependsOnMethods = {
			"onpepper.Data_Analytics.Tests.Save_Traigger_Funds.triggerCalculationforPCOF" })
	public void export_Report_PCOF() throws IOException, InterruptedException, AWTException {
		String fundName = "PCOF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		String downloadedFile = homePage.clickExportBtn(fundName);
		Assert.assertTrue(homePage.isFileDownloaded(downloadedFile), "File was not downloaded or is empty!");

	}

	@Test
	public void view_Report_PCOF() throws IOException, InterruptedException, AWTException {
		String fundName = "PCOF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		boolean rowStatus = homePage.getRowstatus("PL BB Build");
		Assert.assertTrue(rowStatus, "row is not present");

	}

	@Test(retryAnalyzer = Retry.class, dependsOnMethods = {
			"onpepper.Data_Analytics.Tests.Save_Traigger_Funds.triggerCalculationforPFLT" })
	public void export_Report_PFLT() throws IOException, InterruptedException, AWTException {
		String fundName = "PFLT";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		Thread.sleep(3000);
		base.handleJavascriptAlert();

		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		String downloadedFile = homePage.clickExportBtn(fundName);
		Assert.assertTrue(homePage.isFileDownloaded(downloadedFile), "File was not downloaded or is empty!");

	}

	@Test
	public void view_Report_PFLT() throws IOException, InterruptedException, AWTException {
		String fundName = "PFLT";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		Thread.sleep(3000);
		base.handleJavascriptAlert();

		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		boolean rowStatus = homePage.getRowstatus("Loan List");
		Assert.assertTrue(rowStatus, "row is not present");

	}

	@Test(retryAnalyzer = Retry.class, dependsOnMethods = {
			"onpepper.Data_Analytics.Tests.Save_Traigger_Funds.triggerCalculationforPSSL" })
	public void export_Report_PSSL() throws IOException, InterruptedException, AWTException {
		String fundName = "PSSL";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		String downloadedFile = homePage.clickExportBtn(fundName);
		Assert.assertTrue(homePage.isFileDownloaded(downloadedFile), "File was not downloaded or is empty!");

	}

	@Test
	public void view_Report_PSSL() throws IOException, InterruptedException, AWTException {
		String fundName = "PSSL";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		boolean rowStatus = homePage.getRowstatus("Portfolio");
		Assert.assertTrue(rowStatus, "row is not present");

	}

	@Test(retryAnalyzer = Retry.class, dependsOnMethods = {
			"onpepper.Data_Analytics.Tests.Save_Traigger_Funds.triggerCalculationforPSLF" })
	public void export_Report_PSLF() throws IOException, InterruptedException, AWTException {
		String fundName = "PSLF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		String downloadedFile = homePage.clickExportBtn(fundName);
		Assert.assertTrue(homePage.isFileDownloaded(downloadedFile), "File was not downloaded or is empty!");

	}

	@Test
	public void view_Report_PSLF() throws IOException, InterruptedException, AWTException {
		String fundName = "PSLF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		boolean rowStatus = homePage.getRowstatus("Loan List");
		Assert.assertTrue(rowStatus, "row is not present");

	}

	@Test(retryAnalyzer = Retry.class, dependsOnMethods = {
			"onpepper.Data_Analytics.Tests.Save_Traigger_Funds.triggerCalculationforPSCF" })
	public void export_Report_PSCF() throws IOException, InterruptedException, AWTException {
		String fundName = "PSCF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		String downloadedFile = homePage.clickExportBtn(fundName);
		Assert.assertTrue(homePage.isFileDownloaded(downloadedFile), "File was not downloaded or is empty!");

	}

	@Test
	public void view_Report_PSCF() throws IOException, InterruptedException, AWTException {
		String fundName = "PSCF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData(fundName, "Completed");
		base.clickConfirmandProceedBtn();
		base.clickSaveTriggerButton();
		if (homePage.isErrorListVisible()) {
			System.out.println("❌ Validation Failed. Error messages:");
			homePage.getErrorLists();
			Assert.fail("Errors were displayed on screen.");
		} else {
			String actualMsg = homePage.getFundtype(fundName);
			Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
		}
		boolean rowStatus = homePage.getRowstatus("Loan Tape");
		Assert.assertTrue(rowStatus, "row is not present");

	}

	@Test
	public void reuse_PCOF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PCOF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		String actualMsg = homePage.getFundtype(fundName);
		Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
	}

	@Test
	public void reuse_PFLT_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PFLT";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		String actualMsg = homePage.getFundtype(fundName);
		Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
	}

	@Test
	public void reuse_PSSL_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSSL";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		String actualMsg = homePage.getFundtype(fundName);
		Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
	}

	@Test
	public void reuse_PSLF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSLF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		String actualMsg = homePage.getFundtype(fundName);
		Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
	}

	@Test
	public void reuse_PSCF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSCF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		String actualMsg = homePage.getFundtype(fundName);
		Assert.assertEquals(actualMsg, fundName, "Fund Type Mismatch!");
	}

	@Test
	public void wia_PCOF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PCOF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatIfAnalysisBtn();
		boolean wia_text = homePage.updateValues_WIA("Investment Cost", fundName);
		Assert.assertTrue(wia_text);
		homePage.save_WIA_data(fundName);
	}

	@Test
	public void wia_PFLT_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PFLT";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatIfAnalysisBtn();
		boolean wia_text = homePage.updateValues_WIA("Total Commitment (Issue Currency)", fundName);
		Assert.assertTrue(wia_text);
		homePage.save_WIA_data(fundName);
	}

	@Test
	public void wia_PSSL_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSSL";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatIfAnalysisBtn();
		boolean wia_text = homePage.updateValues_WIA("Borrower Facility Commitment", fundName);
		Assert.assertTrue(wia_text);
		homePage.save_WIA_data(fundName);
	}

	@Test
	public void wia_PSLF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSLF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatIfAnalysisBtn();
		boolean wia_text = homePage.updateValues_WIA("OutstandingAmount", fundName);
		Assert.assertTrue(wia_text);
		homePage.save_WIA_data(fundName);
	}

	@Test
	public void wia_PSCF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSCF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatIfAnalysisBtn();
		boolean wia_text = homePage.updateValues_WIA("(Local Currency) Current Commitment Amount", fundName);
		Assert.assertTrue(wia_text);
		homePage.save_WIA_data(fundName);
		

	}
	
	@Test
	public void use_WIA_PCOF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PCOF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatifanalysisOption();
		String latestReport = homePage.getLatestWIAReport(fundName);
		Thread.sleep(5000);
		WebElement msg = homePage.getToastpopup();
		String expectedMsg = latestReport + "_imported Successfully";
		String expected = expectedMsg.replace(" ", "_");
		String actual = msg.getText().replace(" ", "_");
		System.out.println(expected +"*******"+ actual);
		Assert.assertEquals(actual, expected);
	}

	@Test(retryAnalyzer = Retry.class)
	public void use_WIA_PFLT_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PFLT";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatifanalysisOption();
		String latestReport = homePage.getLatestWIAReport(fundName);
		Thread.sleep(5000);
		WebElement msg = homePage.getToastpopup();
		String expectedMsg = latestReport + "_imported Successfully";
		String expected = expectedMsg.replace(" ", "_");
		String actual = msg.getText().replace(" ", "_");
		Assert.assertEquals(actual, expected);
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void use_WIA_PSSL_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSSL";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatifanalysisOption();
		String latestReport = homePage.getLatestWIAReport(fundName);
		Thread.sleep(5000);
		WebElement msg = homePage.getToastpopup();
		String expectedMsg = latestReport + "_imported Successfully";
		String expected = expectedMsg.replace(" ", "_");
		String actual = msg.getText().replace(" ", "_");
		Assert.assertEquals(actual, expected);
	}
	@Test(retryAnalyzer = Retry.class)
	public void use_WIA_PSLF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSLF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatifanalysisOption();
		String latestReport = homePage.getLatestWIAReport(fundName);
		Thread.sleep(5000);
		WebElement msg = homePage.getToastpopup();
		String expectedMsg = latestReport + "_imported Successfully";
		String expected = expectedMsg.replace(" ", "_");
		String actual = msg.getText().replace(" ", "_");
		Assert.assertEquals(actual, expected);
	}
	@Test(retryAnalyzer = Retry.class)
	public void use_WIA_PSCF_fund() throws IOException, InterruptedException, AWTException {
		String fundName = "PSCF";
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		homePage.clickViewResultBtn(fundName);
		homePage.clickWhatifanalysisOption();
		String latestReport = homePage.getLatestWIAReport(fundName);
		Thread.sleep(5000);
		WebElement msg = homePage.getToastpopup();
		String expectedMsg = latestReport + "_imported Successfully";
		String expected = expectedMsg.replace(" ", "_");
		String actual = msg.getText().replace(" ", "_");
		Assert.assertEquals(actual, expected);
	}
	

}
