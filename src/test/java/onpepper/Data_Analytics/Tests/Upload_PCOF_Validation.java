package onpepper.Data_Analytics.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.PageObject.SelectAsset;
import onpepper.Data_Analytics.TestComponent.BaseTest;
import onpepper.Data_Analytics.TestComponent.Retry;

public class Upload_PCOF_Validation extends BaseTest {

	// As a user I should upload the file to perform Borrowing Base Calculations
	@Test(retryAnalyzer = Retry.class)
	public void OBDP001() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		String previoustext = homePage.getDragandDropFieldText();
		homePage.uploadExcelSheet();
		String UpdatedText = homePage.getDragandDropFieldText();
		Assert.assertNotSame(previoustext, UpdatedText);
	}

	// As a user I should select the report date to keep borrowing base results
	// unique for the date
	//@Test(retryAnalyzer = Retry.class)
	@Test
	public void OBDP002() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.selectReportDate();
		String updatedHtml = homePage.getUpdatedHTML();
		boolean isClearButtonPresent = updatedHtml.contains("ant-picker-clear");
		Assert.assertFalse(isClearButtonPresent, "HTML element is not present");
	}

//	@Test(priority = 0, retryAnalyzer = Retry.class)
	@Test
	public void OBDP001_01() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		String date = homePage.uploadDate();
		homePage.selectReportDate();
		homePage.selectPCOFvalue();
		homePage.uploadExcelSheet();
		homePage.clickOnLoadBtn();
		List<WebElement> filenames = homePage.getAllReportsDates();
		for (WebElement filename : filenames) {
			if (filename.getText().contains(date)) {
				homePage.clickYesBtn();
				break;
			}
		}
		homePage.closeToast();
		SelectAsset asset = new SelectAsset(driver);
		String ExpectedText = "Results Generated";
		String ActualText=asset.getSuccessfulMsg();
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// As a user I should get notification message for selecting existing report to
	// ask permission for over-writing the existing report data
	//@Test(dependsOnMethods = "OBDP001_01", retryAnalyzer = Retry.class)
	@Test
	public void OBDP003() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.selectReportDate();
		homePage.selectPCOFvalue();
		homePage.uploadExcelSheet();
		homePage.clickOnLoadBtn();
		driver.switchTo().activeElement();
		String OverWritingText = homePage.getOverWritingText();
		String text = "Data for the selected date already exists in the system. Do you want to replace it ?";
		Assert.assertEquals(OverWritingText, text);
	}

	//@Test(dependsOnMethods = "OBDP001_01", retryAnalyzer = Retry.class)
	@Test
	public void OBDP004() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.selectReportDate();
		homePage.selectPCOFvalue();
		homePage.uploadErrorExcelSheet();
		homePage.clickOnLoadBtn();
		homePage.clickYesBtn();
		String ExpectedText = "PL BB Build is not present in uploaded file";
		String ActualText = homePage.getSheetModificationText();
		System.out.println(ActualText);
		Assert.assertEquals(ExpectedText, ActualText);
	}

	// As a user I should re-upload the file to generate results
//	@Test(dependsOnMethods = "OBDP001_01", retryAnalyzer = Retry.class)
	@Test
	public void OBDP006() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		homePage.selectReportDate();
		homePage.selectPCOFvalue();
		homePage.uploadExcelSheet();
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
	//@Test(dependsOnMethods = "OBDP001_01")
	@Test
	public void OBDP007() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectAssetCheckbox();
		String ActualText = asset.clickOnSetBtn();
		String ExpectedText = "Configurations Set Successfully";
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// As a user I should set the configuration of asset selection table to perform
	// asset selection based on the required parameters
	//@Test(dependsOnMethods = "OBDP001_01", retryAnalyzer = Retry.class)
	@Test
	public void OBDP008() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		String ExpectedText = "*Only 4 columns allowded";
		String ActualText = asset.getErrorMsg();
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// As a user I should re-use the existing files to modify the Borrowing Base
	// Results of a particular date.
	//@Test(dependsOnMethods = "OBDP001_01", retryAnalyzer = Retry.class)
	@Test
	public void OBDP011() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		String ExpectedText = "File Loaded";
		String ActualText = asset.getLoadToastmsg();
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// As a user I should re-use the existing files to modify the Borrowing Base
	// Results of a particular date
	//@Test(dependsOnMethods = "OBDP001_01", retryAnalyzer = Retry.class)
	@Test
	public void OBDP009() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectInvestment();
		String ExpectedText = "Results Generated";
		String ActualText = asset.getSuccessfulMsg();
		Assert.assertEquals(ActualText, ExpectedText);
	}

	// as a user i should able to filter investname from uploaded file
	@Test(enabled = false)
	public void OBPD013() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectInvestmentNamedropdown();
		List<WebElement> values = asset.getdropdownvalues();
		List<WebElement> names = asset.getinvestmentnames();
		for (int i = 1; i <= 5; i++) {
			WebElement value = values.get(i);
			String maintitle = value.getAttribute("title");
			value.click();
			Thread.sleep(500);
			for (WebElement name : names) {
				String expectedtext = name.getText();
				Assert.assertEquals(maintitle, expectedtext);

			}
			asset.selectInvestmentNamedropdown();
		}
	}

	// as a user i should able to filter investtype from uploaded file
	//@Test(dependsOnMethods = "OBDP001_01", retryAnalyzer = Retry.class)
	@Test
	public void OBPD013_01() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectinvestmentTypeDropdown();
		List<WebElement> values = asset.getdropdownvalues();

		List<WebElement> names = asset.getinvestmenttypes();
		System.out.println(values.size());
		for (int i = 2; i <= values.size() - 2; i++) {
			WebElement value = values.get(i);
			String maintitle = value.getAttribute("title");
			value.click();
			Thread.sleep(500);
			for (WebElement name : names) {
				String expectedtext = name.getText();
				Assert.assertEquals(maintitle, expectedtext);
			}
			asset.selectinvestmentTypeDropdown();
		}
	}

	// as a user i should able to filter investindustry from uploaded file
	//@Test(dependsOnMethods = "OBDP001_01", retryAnalyzer = Retry.class)
	@Test
	public void OBPD013_02() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		homePage.clickOnImportFileOption();
		driver.switchTo().activeElement();
		SelectAsset asset = homePage.clickUseBtn();
		asset.selectinvestmentindustryDropdown();
		List<WebElement> values = asset.getdropdownvalues();
		List<WebElement> names = asset.getinvestmentindustry();
		for (int i = 2; i <= 5; i++) {
			WebElement value = values.get(i);
			Actions act = new Actions(driver);
			act.moveToElement(value);
			String maintitle = value.getAttribute("title");
			value.click();
			Thread.sleep(500);
			for (WebElement name : names) {
				String expectedtext = name.getText();
				Assert.assertEquals(maintitle, expectedtext);
			}
			asset.selectinvestmentindustryDropdown();
		}
	}

}