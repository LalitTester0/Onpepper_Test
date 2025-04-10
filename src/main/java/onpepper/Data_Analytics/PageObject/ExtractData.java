package onpepper.Data_Analytics.PageObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import onpepper.Data_Analytics.AbstractComponent.AbstractComponent;

public class ExtractData extends AbstractComponent {
	public WebDriver driver;

	public ExtractData(WebDriver driver) {
		super(driver);

	}

	public String extractData(String fileName, String sheets, String ColumnName) {
	String value = "";
	if (sheets.contains("Borrower Stats")) {
	String filePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\data ingestion\\uploaded  files\\New_Master_Comps_v937 1_06.xlsx";
	String sheetName = sheets;
	String targetColumnName = ColumnName;
	String matchColumnName = "Company";
	String matchValue = "Apex Service Partners";
	int headerRowIndex = 3;

	try (FileInputStream fis = new FileInputStream(new File(filePath));
	XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
	Sheet sheet = workbook.getSheet(sheetName);
	if (sheet == null) {
	System.out.println("Sheet not found: " + sheetName);
	return value;
	}
	Map<String, Integer> columnIndices = new HashMap<>();
	Row headerRow = sheet.getRow(headerRowIndex);
	if (headerRow != null) {
	for (Cell cell : headerRow) {
		columnIndices.put(cell.getStringCellValue(), cell.getColumnIndex());
	}
    	}
	if (!columnIndices.containsKey(targetColumnName) || !columnIndices.containsKey(matchColumnName)) {
	System.out.println("One or both columns not found: " + targetColumnName + ", " + matchColumnName);
	return value;
	}
	int targetColumnIndex = columnIndices.get(targetColumnName);
	int matchColumnIndex = columnIndices.get(matchColumnName);

	for (Row row : sheet) {
		if (row.getRowNum() <= headerRowIndex)
		continue;
		Cell matchCell = row.getCell(matchColumnIndex);
		if (matchCell != null && matchCell.getCellType() == CellType.STRING) {
		String cellValue = matchCell.getStringCellValue();
		if (cellValue.equals(matchValue)) {
		Cell targetCell = row.getCell(targetColumnIndex);
		if (targetCell != null) {
		switch (targetCell.getCellType()) {
		case STRING:
		value=targetCell.getStringCellValue();
		break;
		case NUMERIC:
		double values = targetCell.getNumericCellValue();
		double roundedValue = Math.round(values * 100.0) / 100.0;
		value = Double.toString(roundedValue);
		break;
		default:
		System.out.println("Value: ");								}
		}
	}
	}
	}
	} catch (IOException e) {
				e.printStackTrace();
			}
	catch (IllegalStateException e) {
    	value=null;
		}
		}
	else if (sheets.contains("Client Holdings")) {
		String filePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\data ingestion\\uploaded  files\\PENPL_CashFile_2025_06.xlsx";
		String sheetName = sheets;
		String targetColumnName = ColumnName;
		String matchColumnName = "Issuer/Borrower Name";
        String matchValue = "Flairminds Technology LLC"; 
        int headerRowIndex = 4; 

		try (FileInputStream fis = new FileInputStream(new File(filePath));
		XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
		System.out.println("Sheet not found: " + sheetName);
		return value;
		}
		Map<String, Integer> columnIndices = new HashMap<>();
		Row headerRow = sheet.getRow(headerRowIndex);
		if (headerRow != null) {
		for (Cell cell : headerRow) {
			columnIndices.put(cell.getStringCellValue(), cell.getColumnIndex());
		}
	    	}
		if (!columnIndices.containsKey(targetColumnName) || !columnIndices.containsKey(matchColumnName)) {
		System.out.println("One or both columns not found: " + targetColumnName + ", " + matchColumnName);
		return value;
		}
		int targetColumnIndex = columnIndices.get(targetColumnName);
		int matchColumnIndex = columnIndices.get(matchColumnName);

		for (Row row : sheet) {
			if (row.getRowNum() <= headerRowIndex)
			continue;
			Cell matchCell = row.getCell(matchColumnIndex);
			if (matchCell != null && matchCell.getCellType() == CellType.STRING) {
			String cellValue = matchCell.getStringCellValue();
			if (cellValue.equals(matchValue)) {
			Cell targetCell = row.getCell(targetColumnIndex);
			if (targetCell != null) {
			switch (targetCell.getCellType()) {
			case STRING:
			value=targetCell.getStringCellValue();
			break;
			case NUMERIC:
			double values = targetCell.getNumericCellValue();
			double roundedValue = Math.round(values * 100.0) / 100.0;
			value = Double.toString(roundedValue);
			break;
			default:
			System.out.println("Value: ");								}
			}
		}
		}
		}
		} catch (IOException e) {
					e.printStackTrace();
				}
		catch (IllegalStateException e) {
        	value=null;
			}
		}
	else if (sheets.contains("PFLT Borrowing Base")) {
		 String filePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\data ingestion\\uploaded  files\\New_Master_Comps_v937 1_06.xlsx";
		String sheetName = sheets;
		String targetColumnName = ColumnName;
		String matchColumnName = "Security";
        String matchValue = "Apex (Term Loan)"; 
        int headerRowIndex = 2; 

		try (FileInputStream fis = new FileInputStream(new File(filePath));
		XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
		System.out.println("Sheet not found: " + sheetName);
		return value;
		}
		Map<String, Integer> columnIndices = new HashMap<>();
		Row headerRow = sheet.getRow(headerRowIndex);
		if (headerRow != null) {
		for (Cell cell : headerRow) {
			columnIndices.put(cell.getStringCellValue(), cell.getColumnIndex());
		}
	    	}
		if (!columnIndices.containsKey(targetColumnName) || !columnIndices.containsKey(matchColumnName)) {
		System.out.println("One or both columns not found: " + targetColumnName + ", " + matchColumnName);
		return value;
		}
		int targetColumnIndex = columnIndices.get(targetColumnName);
		int matchColumnIndex = columnIndices.get(matchColumnName);

		for (Row row : sheet) {
			if (row.getRowNum() <= headerRowIndex)
			continue;
			Cell matchCell = row.getCell(matchColumnIndex);
			if (matchCell != null && matchCell.getCellType() == CellType.STRING) {
			String cellValue = matchCell.getStringCellValue();
			if (cellValue.equals(matchValue)) {
			Cell targetCell = row.getCell(targetColumnIndex);
			if (targetCell != null) {
			switch (targetCell.getCellType()) {
			case STRING:
			value=targetCell.getStringCellValue();
			break;
			case NUMERIC:
			double values = targetCell.getNumericCellValue();
			double roundedValue = Math.round(values * 100.0) / 100.0;
			value = Double.toString(roundedValue);
			break;
			default:
			System.out.println("Value: ");								}
			}
		}
		}
		}
		} catch (IOException e) {
					e.printStackTrace();
				}
		catch (IllegalStateException e) {
        	value=null;
			}
		
	}
	else if (sheets.contains("Securities Stats")) {
		 String filePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\data ingestion\\uploaded  files\\New_Master_Comps_v937 1_06.xlsx";
		String sheetName = sheets;
		String targetColumnName = ColumnName;
		String matchColumnName = "Security";
       String matchValue = "Apex (Term Loan)"; 
       int headerRowIndex = 2; 

		try (FileInputStream fis = new FileInputStream(new File(filePath));
		XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
		System.out.println("Sheet not found: " + sheetName);
		return value;
		}
		Map<String, Integer> columnIndices = new HashMap<>();
		Row headerRow = sheet.getRow(headerRowIndex);
		if (headerRow != null) {
		for (Cell cell : headerRow) {
			columnIndices.put(cell.getStringCellValue(), cell.getColumnIndex());
		}
	    	}
		if (!columnIndices.containsKey(targetColumnName) || !columnIndices.containsKey(matchColumnName)) {
		System.out.println("One or both columns not found: " + targetColumnName + ", " + matchColumnName);
		return value;
		}
		int targetColumnIndex = columnIndices.get(targetColumnName);
		int matchColumnIndex = columnIndices.get(matchColumnName);

		for (Row row : sheet) {
			if (row.getRowNum() <= headerRowIndex)
			continue;
			Cell matchCell = row.getCell(matchColumnIndex);
			if (matchCell != null && matchCell.getCellType() == CellType.STRING) {
			String cellValue = matchCell.getStringCellValue();
			if (cellValue.equals(matchValue)) {
			Cell targetCell = row.getCell(targetColumnIndex);
			if (targetCell != null) {
			switch (targetCell.getCellType()) {
			case STRING:
			value=targetCell.getStringCellValue();
			break;
			case NUMERIC:
			double values = targetCell.getNumericCellValue();
			double roundedValue = Math.round(values * 100.0) / 100.0;
			value = Double.toString(roundedValue);
			break;
			default:
			System.out.println("Value: ");								}
			}
		}
		}
		}
		} catch (IOException e) {
					e.printStackTrace();
				}
		catch (IllegalStateException e) {
       	value=null;
			}
	}
	else if (sheets.contains("US Bank Holdings")) {
		 String filePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\data ingestion\\uploaded  files\\PENPL_CashFile_2025_06.xlsx";
		String sheetName = sheets;
		String targetColumnName = ColumnName;
		String matchColumnName = "Security/Facility Name";
        String matchValue = "Flairminds Technology T/L"; 
        int headerRowIndex = 4; 

		try (FileInputStream fis = new FileInputStream(new File(filePath));
		XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
		System.out.println("Sheet not found: " + sheetName);
		return value;
		}
		Map<String, Integer> columnIndices = new HashMap<>();
		Row headerRow = sheet.getRow(headerRowIndex);
		if (headerRow != null) {
		for (Cell cell : headerRow) {
			columnIndices.put(cell.getStringCellValue(), cell.getColumnIndex());
		}
	    	}
		if (!columnIndices.containsKey(targetColumnName) || !columnIndices.containsKey(matchColumnName)) {
		System.out.println("One or both columns not found: " + targetColumnName + ", " + matchColumnName);
		return value;
		}
		int targetColumnIndex = columnIndices.get(targetColumnName);
		int matchColumnIndex = columnIndices.get(matchColumnName);

		for (Row row : sheet) {
			if (row.getRowNum() <= headerRowIndex)
			continue;
			Cell matchCell = row.getCell(matchColumnIndex);
			if (matchCell != null && matchCell.getCellType() == CellType.STRING) {
			String cellValue = matchCell.getStringCellValue();
			if (cellValue.equals(matchValue)) {
			Cell targetCell = row.getCell(targetColumnIndex);
			if (targetCell != null) {
			switch (targetCell.getCellType()) {
			case STRING:
			value=targetCell.getStringCellValue();
			break;
			case NUMERIC:
			double values = targetCell.getNumericCellValue();
			double roundedValue = Math.round(values * 100.0) / 100.0;
			value = Double.toString(roundedValue);
			break;
			default:
			System.out.println("Value: ");								}
			}
		}
		}
		}
		} catch (IOException e) {
					e.printStackTrace();
				}
		catch (IllegalStateException e) {
      	value=null;
			}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	return value;
	}
}
