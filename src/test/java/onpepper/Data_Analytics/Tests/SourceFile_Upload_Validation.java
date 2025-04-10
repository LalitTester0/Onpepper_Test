package onpepper.Data_Analytics.Tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
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
		@Test(retryAnalyzer = Retry.class)
		public void checkMandatoryFields() throws IOException, InterruptedException, AWTException {
			HomePage homePage = page.goTo();
			DataIngestion Data = homePage.navigateToDataIngestion();
			SourceFileLists source = Data.clickExtractNewBaseBtn();
			source.clickUploadFilesBtn();
			source.clickLoadButton();
			String message = source.getToastmsg();
			Assert.assertEquals(message, "Select Report Date");
			System.out.println("************");
		}
	
	/*
	// as a User I should able to upload Master File for PFLT Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMasterFileforPFLT() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadMasterFile("PFLT");
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + " is not uploaded");

	}

	// as a User I should able to upload Cash File for PFLT Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadCashFileforPFLT() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadCashFile("PFLT");
		System.out.println(newFileName);
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + " is not uploaded");
	}

	// as a User I should able to upload Market Book File for PFLT Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMarketFileforPFLT() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadMarketFile("PFLT");
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + "is not uploaded");
	}

	// as a User I should not able to upload same file which is uploaded previously
	@Test(retryAnalyzer = Retry.class)
	public void DupliateFile() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		source.uploadDuplicateFile();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Files with same name already exist.");
	}

	// As a user, I should receive a message if the report date is not filled while
	// uploading files.
	@Test(retryAnalyzer = Retry.class)
	public void checkMandatoryFields() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
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
		SourceFileLists source = Data.clickExtractNewBaseBtn();
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
		SourceFileLists source = Data.clickExtractNewBaseBtn();
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
		SourceFileLists source = Data.clickExtractNewBaseBtn();
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
		SourceFileLists source = Data.clickExtractNewBaseBtn();
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
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadMasterFile("PCOF");
		source.filteredPCOFValue();
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + " is not uploaded");
	}

	// as a User I should able to upload Cash File for PCOF Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadCashFileforPCOF() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadCashFile("PCOF");
		source.filteredPCOFValue();
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + " is not uploaded");
	}

	// as a User I should able to upload Market Book File for PCOF Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMarketFileforPCOF() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadMarketFile("PCOF");
		source.filteredPCOFValue();
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + "is not uploaded");
	}

	// as a User I should able to upload Master File for All Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMasterFileforAllFunds() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadMasterFile("ALL");
		source.filteredPCOFValue();
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + " is not uploaded");
	}

	// as a User I should able to upload Cash File for All Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadCashFileforAll() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadCashFile("ALL");
		source.filteredPCOFValue();
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + " is not uploaded");
	}

	// as a User I should able to upload Market Book File for PFLT Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadMarketFileforALL() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileName = source.uploadMarketFile("ALL");
		System.out.println(newFileName);
		source.filteredPCOFValue();
		List<WebElement> list = source.getFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(newFileName));
		Assert.assertTrue(isFilePresent, newFileName + "is not uploaded");
	}

	// as a User I should able to upload Market Book File for PFLT Fund
	@Test(retryAnalyzer = Retry.class)
	public void uploadmultipleMarketFileforALL() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String newFileNames = source.uploadMultipleFile("ALL");
		String[] files = newFileNames.split("/");
		String file1 = files[0];
		String file2 = files[1];
		source.filteredPCOFValue();
		List<WebElement> list = source.getInprogressFilesList();
		boolean isFilePresent = list.stream().anyMatch(element -> element.getText().trim().equals(file1));
		Assert.assertTrue(isFilePresent, file1 + "is not uploaded");
		boolean isFilePresent1 = list.stream().anyMatch(element -> element.getText().trim().equals(file2));
		Assert.assertTrue(isFilePresent1, file2 + "is not uploaded");
	}

	// User can cancel upload even after selecting files and the modal should reset
	@Test(retryAnalyzer = Retry.class)
	public void resetModal() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		source.addAllData("ALL");
		source.clickUploadFilesBtn();
		boolean status = source.isPFLTCheckboxselected();
		boolean status1 = source.isPCOFCheckboxselected();
		Assert.assertFalse(status, "PFLT checkbox is selected");
		Assert.assertFalse(status1, "PCOF checkbox is selected");
		Assert.assertEquals(source.getdragText(), "Drag and drop files here, or");
		String attributeValue = source.selectReportDate11();
		Assert.assertFalse(attributeValue.contains("ant-picker-cell-selected"), "Date is selected!");
	}

	// as a User I should not able to upload same file which is uploaded previously
	@Test(retryAnalyzer = Retry.class)
	public void wrongFile() throws IOException, InterruptedException, AWTException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		source.uploadErrorFile();
		String message = source.getToastmsg();
		Assert.assertEquals(message, "Invalid File Format");
	}*/
}
