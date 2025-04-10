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


	@Test
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

	@Test
	public void unArchieveFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		source.clickOnSwitcher();
		String fileName = source.archieveFileName();
		source.selectArchieveFileCheckbox();
		source.clickOnUnArchieveBtn();
		source.clickOnSwitcher();
		List<WebElement> files = source.getFileNames();
		boolean isValuePresent = files.stream().anyMatch(e -> e.getText().contains(fileName));
		Assert.assertTrue(isValuePresent, "Expected value '" + fileName + "' not found in the list!");
	}

	@Test
	public void fiteredPCOFFiles() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		List<WebElement> funds = source.filteredPCOFValue();
		for (WebElement fund : funds) {
			System.out.println(fund.getText());
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
		for (WebElement fund : funds) {
			System.out.println(fund.getText());
		}
		boolean isvaleuePresent = funds.stream().allMatch(e -> e.getText().contains("PFLT"));
		Assert.assertTrue(isvaleuePresent, "PCOF Funds are not get filtered");
	}

	@Test
	public void fiteredFilesByReportDates() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String formattedDate = source.formattedDate();
		List<WebElement> funds = source.selectReportDate1();
		for (WebElement fund : funds) {
			Assert.assertEquals(formattedDate, fund.getText());
		}
	}

	@Test
	public void createPFLTBaseDataFile() throws IOException, InterruptedException {
		HomePage homePage = page.goTo();
		DataIngestion Data = homePage.navigateToDataIngestion();
		SourceFileLists source = Data.clickExtractNewBaseBtn();
		String FileNames = source.selectSourceFile();
		source.NavigatetoBaseDataButton();
		String naems = Data.getbaseFileasperDate();
		System.out.println(naems);
		Assert.assertEquals(FileNames, naems);
	}
	
	

}
