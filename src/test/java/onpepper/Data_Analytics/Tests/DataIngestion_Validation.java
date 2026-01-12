package onpepper.Data_Analytics.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import onpepper.Data_Analytics.PageObject.DataIngestion;
import onpepper.Data_Analytics.PageObject.HomePage;
import onpepper.Data_Analytics.PageObject.SourceFileLists;
import onpepper.Data_Analytics.TestComponent.BaseTest;

public class DataIngestion_Validation extends BaseTest {

	@Test(description = "Scenario: Successfully archive a file and verify its presence in the archived list")
	public void archieveFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String fileName = source.fileName();
		source.selectFileCheckbox();
		source.clickOnArchieveBtn();
		source.clickOnSwitcher();
		List<WebElement> files = source.getFileNames();
		boolean isValuePresent = files.stream().anyMatch(e -> e.getText().contains(fileName));
		Assert.assertTrue(isValuePresent, "Expected value '" + fileName + "' not found in the list!");
	}

	@Test(description = "Scenario: Successfully unarchive a file and verify its return to the main list")
	public void unArchieveFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		Thread.sleep(500);
		source.clickOnSwitcher();
		String fileName = source.archieveFileName();
		source.selectArchieveFileCheckbox();
		source.clickOnUnArchieveBtn();
		source.clickOnSwitcher();
		List<WebElement> files = source.getFileNames();
		boolean isValuePresent = files.stream().anyMatch(e -> e.getText().contains(fileName));
		Assert.assertTrue(isValuePresent, "Expected value '" + fileName + "' not found in the list!");
	}

	@Test(description = "Scenario: Filter PCOF files and verify filtering logic")
	public void fiteredPCOFFiles() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		List<WebElement> funds = source.filteredPCOFValue();
		if (funds.size() == 0) {
			String text = source.noDataCellText();
			Assert.assertEquals(text, "No Data");
		} else {
			for (WebElement fund : funds) {
				System.out.println(fund.getText());
			}
		}
		boolean isvaleuePresent = funds.stream().allMatch(e -> e.getText().contains("PCOF"));
		Assert.assertTrue(isvaleuePresent, "PCOF Funds are not get filtered");
	}

	@Test
	public void fiteredPFLTFiles() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		List<WebElement> funds = source.filteredPFLTValue();
		if (funds.size() == 0) {
			String text = source.noDataCellText();
			Assert.assertEquals(text, "No Data");
		} else {
			for (WebElement fund : funds) {
				System.out.println(fund.getText());
			}
		}
		boolean isvaleuePresent = funds.stream().allMatch(e -> e.getText().contains("PFLT"));
		Assert.assertTrue(isvaleuePresent, "PFLT Funds are not get filtered");
	}

	@Test
	public void fiteredFilesByReportDates() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String formattedDate = source.formattedDate();
		System.out.println(formattedDate);
		List<WebElement> funds = source.selectReportDate1();
		if (funds.size() == 0) {
			String text = source.noDataCellText();
			Assert.assertEquals(text, "No Data");
		} else {
			for (WebElement fund : funds) {
				System.out.println(fund.getText());
				Assert.assertEquals(formattedDate, fund.getText());
			}
		}
	}

	@Test
	public void createPFLTBaseDataFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String FileNames = source.selectSourceFileforPFLT();
		source.NavigatetoBaseDataButton();
		String Names = Data.getbaseFileasperDate("PFLT");
		System.out.println(Names);
		boolean status = Data.verifyAllFileNamesPresent(Names, FileNames);
		Assert.assertTrue(status, FileNames + " is not present");
	}

	@Test
	public void fiteredPCOFFile2s() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		List<WebElement> funds = Data.filteredPCOFValue();
		if (funds.size() == 0) {
			String text = Data.noDataCellText();
			Assert.assertEquals(text, "No Data");
		} else {
			boolean isvaleuePresent = funds.stream().allMatch(e -> e.getText().contains("PCOF"));
			Assert.assertTrue(isvaleuePresent, "PCOF Funds are not get filtered");

		}
	}

	@Test
	public void fiteredPFLTFiles2() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		List<WebElement> funds = Data.filteredPFLTValue();
		if (funds.size() == 0) {
			String text = Data.noDataCellText();
			Assert.assertEquals(text, "No Data");
		} else {
			boolean isvaleuePresent = funds.stream().allMatch(e -> e.getText().contains("PFLT"));
			Assert.assertTrue(isvaleuePresent, "PFLT Funds are not get filtered");

		}
	}

	@Test
	public void createPCOFBaseDataFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String FileNames = source.selectSourceFileforPCOF();
		source.NavigatetoBaseDataButton();

		String Names = Data.getbaseFileasperDate("PCOF");
		System.out.println(Names);
		boolean status = Data.verifyAllFileNamesPresent(Names, FileNames);
		Assert.assertTrue(status, FileNames + " is not present");
	}

	@Test
	public void createPSSLBaseDataFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String FileNames = source.selectSourceFileforPSSL();
		source.NavigatetoBaseDataButton();
		String Names = Data.getbaseFileasperDate("PSSL");
		System.out.println(Names);
		boolean status = Data.verifyAllFileNamesPresent(Names, FileNames);
		Assert.assertTrue(status, FileNames + " is not present");
	}

}
