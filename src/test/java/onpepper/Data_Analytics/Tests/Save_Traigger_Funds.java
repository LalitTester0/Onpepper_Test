package onpepper.Data_Analytics.Tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import onpepper.Data_Analytics.PageObject.BaseDataPreview;
import onpepper.Data_Analytics.PageObject.DataIngestion;
import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.TestComponent.BaseTest;
import onpepper.Data_Analytics.TestComponent.Retry;

public class Save_Traigger_Funds extends BaseTest {
	@Test
	public void openPCOFBaseDataFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		driver.manage().window().maximize();
		DataIngestion data = homePage.navigateToDataIngestion();
		BaseDataPreview base = data.clickOnBaseData("PCOF", "Completed");
		String FundType = base.getFundType();
		Assert.assertEquals(FundType, "PCOF");
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void triggerCalculationforPCOF() throws IOException, InterruptedException, AWTException {
		String fundName="PCOF";
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
	}
	
	@Test
	public void triggerCalculationforPFLT() throws IOException, InterruptedException, AWTException {
		String fundName="PFLT";
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
	}
	
	@Test
	public void triggerCalculationforPSSL() throws IOException, InterruptedException, AWTException {
		String fundName="PSSL";
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
	}
	
	@Test
	public void triggerCalculationforPSLF() throws IOException, InterruptedException, AWTException {
		String fundName="PSLF";
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
	}
	
	@Test
	public void triggerCalculationforPSCF() throws IOException, InterruptedException, AWTException {
		String fundName="PSCF";
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
	}
	
}
