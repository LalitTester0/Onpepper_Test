package onpepper.Data_Analytics.Tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import onpepper.Data_Analytics.PageObject.BaseDataPreview;
import onpepper.Data_Analytics.PageObject.DataIngestion;
import onpepper.Data_Analytics.PageObject.ExtractNewBaseData;
import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.TestComponent.BaseTest;
import onpepper.Data_Analytics.TestComponent.Retry;

public class Generate_BaseFile_Validation extends BaseTest {

	@Test(retryAnalyzer = Retry.class, description = "Scenario: Generate and verify PFLT Fund Base File")
	public void generatePFLT_Fund_Base_File() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion data = homePage.navigateToDataIngestion();
		ExtractNewBaseData ExtractNewBaseData = data.select_ExtractNewBaseDataBtn();
		ExtractNewBaseData.enter_Fund_and_Date_Date("PFLT");
		ExtractNewBaseData.checkSourceFileAvailabilityforPFLT();
		ExtractNewBaseData.selectSourceFileforPFLT();
		ExtractNewBaseData.check_Data_Mapping_info();
		BaseDataPreview base = ExtractNewBaseData.clickstart_ExctractionBtn();
		String fundType = base.getFundType();
		Assert.assertEquals("PFLT", fundType);
	}

	@Test(retryAnalyzer = Retry.class, description = "Scenario: Generate and verify PCOF Fund Base File")
	public void generatePCOF_Fund_Base_File() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion data = homePage.navigateToDataIngestion();
		ExtractNewBaseData ExtractNewBaseData = data.select_ExtractNewBaseDataBtn();
		ExtractNewBaseData.enter_Fund_and_Date_Date("PCOF");
		ExtractNewBaseData.checkSourceFileAvailabilityforPCOF();
		ExtractNewBaseData.selectSourceFileforPCOF();
		ExtractNewBaseData.check_Data_Mapping_info();
		BaseDataPreview base = ExtractNewBaseData.clickstart_ExctractionBtn();
		String fundType = base.getFundType();
		Assert.assertEquals("PCOF", fundType);
	}

	@Test(description = "Scenario: Generate and verify PSSL Fund Base File")
	public void generatePSSL_Fund_Base_File() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion data = homePage.navigateToDataIngestion();
		ExtractNewBaseData ExtractNewBaseData = data.select_ExtractNewBaseDataBtn();
		ExtractNewBaseData.enter_Fund_and_Date_Date("PSSL");
		ExtractNewBaseData.checkSourceFileAvailabilityforPSSL();
		ExtractNewBaseData.selectSourceFileforPSSL();
		ExtractNewBaseData.check_Data_Mapping_info();
		BaseDataPreview base = ExtractNewBaseData.clickstart_ExctractionBtn();
		String fundType = base.getFundType();
		Assert.assertEquals("PSSL", fundType);
	}

	@Test(description = "Scenario: Generate and verify PSLF Fund Base File")
	public void generatePSLF_Fund_Base_File() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion data = homePage.navigateToDataIngestion();
		ExtractNewBaseData ExtractNewBaseData = data.select_ExtractNewBaseDataBtn();
		ExtractNewBaseData.enter_Fund_and_Date_Date("PSLF");
		ExtractNewBaseData.checkSourceFileAvailabilityforPSLF();
		ExtractNewBaseData.selectSourceFileforPSLF();
		ExtractNewBaseData.check_Data_Mapping_info();
		BaseDataPreview base = ExtractNewBaseData.clickstart_ExctractionBtn();
		String fundType = base.getFundType();
		Assert.assertEquals("PSLF", fundType);
	}

	@Test(description = "Scenario: Generate and verify PSCF Fund Base File")
	public void generatePSCF_Fund_Base_File() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion data = homePage.navigateToDataIngestion();
		ExtractNewBaseData ExtractNewBaseData = data.select_ExtractNewBaseDataBtn();
		ExtractNewBaseData.enter_Fund_and_Date_Date("PSCF");
		ExtractNewBaseData.checkSourceFileAvailabilityforPSCF();
		ExtractNewBaseData.selectSourceFileforPSCF();
		ExtractNewBaseData.check_Data_Mapping_info();
		BaseDataPreview base = ExtractNewBaseData.clickstart_ExctractionBtn();
		String fundType = base.getFundType();
		Assert.assertEquals("PSCF", fundType);

	}

}