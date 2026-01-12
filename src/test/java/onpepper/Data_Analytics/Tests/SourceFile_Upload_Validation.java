package onpepper.Data_Analytics.Tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import onpepper.Data_Analytics.PageObject.DataIngestion;
import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.PageObject.SourceFileLists;
import onpepper.Data_Analytics.TestComponent.BaseTest;
import onpepper.Data_Analytics.TestComponent.Retry;

public class SourceFile_Upload_Validation extends BaseTest {

	// As a user, I should receive a message if the report date is not filled while
	// uploading files.
	@Test(description = "Scenario: Verify mandatory fields validation during file upload")
	public void checkMandatoryFields() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		source.clickUploadFilesBtn();
		source.clickLoadButton();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Select Report Date");
	}

	// as a User I should able to upload Master File for PFLT Fund
	@Test
	public void uploadMasterFileforPFLT() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadMasterFile("PFLT");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should able to upload Cash File for PFLT Fund
	@Test
	public void uploadCashFileforPFLT() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadCashFile("PFLT");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should able to upload Market Book File for PFLT Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMarketFileforPFLT() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadMarketFile("PFLT");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should not able to upload same file which is uploaded previously
	@Test(retryAnalyzer = Retry.class)
	public void DupliateFile() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		source.uploadDuplicateFile();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Files with same name already exist.");
	}

	// As a user, I should receive a message if the report date is not filled while
	// uploading files.
	@Test(retryAnalyzer = Retry.class)
	public void checkMandatoryFields21() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		source.clickUploadFilesBtn();
		source.clickLoadButton();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Select Report Date");
	}

	// As a user, I should receive a message if the fund type is not selected while
	// uploading files.
	@Test(retryAnalyzer = Retry.class)
	public void checkMandatoryFields2() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		source.clickUploadFilesBtn();
		source.selectReportDate();
		source.clickLoadButton();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Please select files.");
	}

	// As a user, I should receive a message if the file is not uploaded.
	@Test(retryAnalyzer = Retry.class)
	public void checkMandatoryFields3() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		source.clickUploadFilesBtn();
		source.selectReportDate();
		source.selectPFLTCheckbox();
		source.clickLoadButton();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Please select files.");
	}

	// As a user, I should receive a message if the fund type is not selected while
	// uploading files.
	@Test(retryAnalyzer = Retry.class)
	public void checkMandatoryFields4() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		source.clickUploadFilesBtn();
		source.selectReportDate();
		source.uploadNewMarketFile();
		Thread.sleep(500);
		source.clickLoadButton();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Please select Fund.");
	}

	// As a user, I should receive a message if the report date is not filled while
	// uploading files.
	@Test(retryAnalyzer = Retry.class)
	public void checkMandatoryFields5() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		source.clickUploadFilesBtn();
		source.selectPFLTCheckbox();
		source.uploadNewMarketFile();
		source.clickLoadButton();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Select Report Date");
	}

	// as a User I should able to upload Master File for PCOF Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMasterFileforPCOF() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadMasterFile("PCOF");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should able to upload Cash File for PCOF Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadCashFileforPCOF() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadCashFile("PCOF");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should able to upload Market Book File for PCOF Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMarketFileforPCOF() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadMarketFile("PCOF");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should able to upload Master File for All Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMasterFileforAllFunds() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadMasterFile("ALL");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should able to upload Cash File for All Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadCashFileforAll() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadCashFile("ALL");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should able to upload Market Book File for PFLT Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMarketFileforALL() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadMarketFile("ALL");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

	// as a User I should able to upload Cash File for PCOF Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadCashFileforPSSL() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.select_SourceFileTab();
		String newFileName = source.uploadCashFileforPSSL("PSSL");
		Thread.sleep(20000);
		boolean fileStatus = source.get_UploadFileStatus(newFileName);
		Assert.assertTrue(fileStatus, "file is not displayed");
	}

}
