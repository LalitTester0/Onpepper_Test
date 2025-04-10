package onpepper.Data_Analytics.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.PageObject.SelectAsset;
import onpepper.Data_Analytics.TestComponent.BaseTest;
import onpepper.Data_Analytics.TestComponent.Retry;

public class Upload_PFLT_validation extends BaseTest {

	// As a user I should upload the file to perform PLFT calculation
	@Test(retryAnalyzer = Retry.class)
	public void UploadFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		String previoustext = homePage.getDragandDropFieldText();
		homePage.uploadPFLTExcelSheet();
		String UpdatedText = homePage.getDragandDropFieldText();
		Assert.assertNotSame(previoustext, UpdatedText);
	}

	// As a user I should select the report date to keep PLFT results
	// unique for the date
	@Test(retryAnalyzer = Retry.class)
	public void SelectReportDate() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.selectPFLTReportDate();
		String updatedHtml = homePage.getUpdatedHTML();
		boolean isClearButtonPresent = updatedHtml.contains("ant-picker-clear");
		Assert.assertFalse(isClearButtonPresent, "HTML element is not present");
	}

	@Test(priority = 1)
	public void uploadPFLTFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		String date = homePage.uploadPFLTDate();
		homePage.selectListofExistingFileRadioBtn();
		List<WebElement> filenames = homePage.getAllReportsDates();
		System.out.println(filenames.size());
		homePage.selectUploadFileRadioBtn();
		homePage.selectPFLTReportDate();
		homePage.selectPFLTvalue();
		homePage.uploadPFLTExcelSheet();
		homePage.clickOnLoadBtn();
		System.out.println(filenames.size());
		for (WebElement filename : filenames) {
			System.out.println(filename.getText());
			if (filename.getText().contains(date)) {
				homePage.clickYesBtn();
				break;
			}
		}
		homePage.closeToast();
		SelectAsset asset = new SelectAsset(driver);
		String ExpectedText = "Results Generated";
		String ActualText = asset.getSuccessfulMsg();
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// As a user I should get notification message for selecting existing report to
	// ask permission for over-writing the existing report data
	 @Test(dependsOnMethods = "uploadPFLTFile", retryAnalyzer = Retry.class)
	public void checkPFLTOverwritingText() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.selectPFLTReportDate();
		homePage.selectPFLTvalue();
		homePage.uploadPFLTExcelSheet();
		homePage.clickOnLoadBtn();
		driver.switchTo().activeElement();
		String OverWritingText = homePage.getOverWritingText();
		String text = "Data for the selected date already exists in the system. Do you want to replace it ?";
		Assert.assertEquals(OverWritingText, text);
	}

	// @Test(dependsOnMethods = "uploadPFLTFile", retryAnalyzer = Retry.class)
	@Test
	 public void uploadPFLTErrorFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.selectPFLTReportDate();
		homePage.selectPFLTvalue();
		homePage.uploadErrorExcelSheet();
		homePage.clickOnLoadBtn();
		homePage.clickYesBtn();
		String ExpectedText = "Loan List is not present in uploaded file";
		String ActualText = homePage.getSheetModificationText();
		System.out.println(ActualText);
		Assert.assertEquals(ExpectedText, ActualText);
	}

	// As a user I should re-upload the file to generate results
	// @Test(dependsOnMethods = "uploadPFLTFile", retryAnalyzer = Retry.class)
	@Test
	public void checkReUploadPFLTFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.selectPFLTReportDate();
		homePage.selectPFLTvalue();
		homePage.uploadPFLTExcelSheet2();
		homePage.clickOnLoadBtn();
		homePage.clickYesBtn();
		homePage.closeToast();
		SelectAsset asset = new SelectAsset(driver);
		String ExpectedText = "Results Generated";
		String ActualText = asset.getSuccessfulMsg();
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// As a user I should set the configuration of asset selection table to perform
	// asset selection based on the required parameters
	 //@Test(dependsOnMethods = "uploadPFLTFile", retryAnalyzer = Retry.class)
	@Test
	public void checkPLFTAssetSelection() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickPFLTUseBtn();
		asset.selectAssetCheckbox();
		String ActualText = asset.clickOnSetBtn();
		String ExpectedText = "Configurations Set Successfully";
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// As a user I should set the configuration of asset selection table to perform
	// asset selection based on the required parameters
	 @Test(dependsOnMethods = "uploadPFLTFile", retryAnalyzer = Retry.class)	
	public void checkPLFTAssetErrorMsg() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickPFLTUseBtn();
		String ExpectedText = "*Only 4 columns allowded";
		String ActualText = asset.getErrorMsg();
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// as a user i should able to filter investname from uploaded file
	 @Test(enabled=false)
	public void filterObligerName() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickPFLTUseBtn();
		asset.selectObligorNamedropdown();
		List<WebElement> values = asset.getdropdownvalues();
		List<WebElement> names = asset.getobligorsnames();
		for (int i = 3; i <= 5; i++) {
			WebElement value = values.get(i);
			String maintitle = value.getAttribute("title");
			System.out.println(maintitle);
			value.click();
			Thread.sleep(1000);
			for (WebElement name : names) {
				String expectedtext = name.getText();
				System.out.println(expectedtext);
				Assert.assertEquals(maintitle, expectedtext);

			}
			asset.selectObligorNamedropdown();
		}
	}

	// as a user i should able to filter investtype from uploaded file
	 @Test(dependsOnMethods = "uploadPFLTFile", retryAnalyzer = Retry.class)	
	public void filterLoanType() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickPFLTUseBtn();
		asset.selectloanTypeDropdown();
		List<WebElement> values = asset.getdropdownvalues();

		List<WebElement> names = asset.getinvestmenttypes();
		for (int i = 2; i <= 5; i++) {
			WebElement value = values.get(i);
			String maintitle = value.getAttribute("title");
			value.click();
			Thread.sleep(100);
			for (WebElement name : names) {
				String expectedtext = name.getText();
				Assert.assertEquals(maintitle, expectedtext);
			}
			asset.selectloanTypeDropdown();
		}
	}

	// as a user i should able to filter investindustry from uploaded file
	 @Test(dependsOnMethods = "uploadPFLTFile", retryAnalyzer = Retry.class)
	public void filterObligerindustry() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickPFLTUseBtn();
		asset.selectobligerindustryDropdown();
		List<WebElement> values = asset.getdropdownvalues();
		List<WebElement> names = asset.getobligerIndustry();
		for (int i = 2; i <= 5; i++) {
			WebElement value = values.get(i);
			String maintitle = value.getAttribute("title");
			value.click();
			Thread.sleep(100);
			for (WebElement name : names) {
				String expectedtext = name.getText();
				Assert.assertEquals(maintitle, expectedtext);
			}
			asset.selectobligerindustryDropdown();
		}
	}
}
