package onpepper.Data_Analytics.Tests;

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

public class extractData {

	public static void main(String[] args) {
		String value;
		// String filePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\data ingestion\\uploaded  files\\New_Master_Comps_v937 1_06.xlsx";
		 String filePath = "C:\\Users\\Admin\\Documents\\Flairminds\\fileupload-Onpeper\\data ingestion\\uploaded  files\\PENPL_CashFile_2025_06.xlsx";
		   
		String sheetName = "US Bank Holdings";
	        String targetColumnName = "Original Purchase Price"; 
	        String matchColumnName = "Security/Facility Name";
	        String matchValue = "Flairminds Technology T/L"; 
	        int headerRowIndex = 4; 

	        try (FileInputStream fis = new FileInputStream(new File(filePath));
	             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            if (sheet == null) {
	                System.out.println("Sheet not found: " + sheetName);
	                return;
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
	                return;
	            }
	            int targetColumnIndex = columnIndices.get(targetColumnName);
	            int matchColumnIndex = columnIndices.get(matchColumnName);

	            for (Row row : sheet) {
	                if (row.getRowNum() <= headerRowIndex) continue; 

	                Cell matchCell = row.getCell(matchColumnIndex);
	                if (matchCell != null) {
	                    String cellValue = null;
	                    if (matchCell.getCellType() == CellType.STRING) {
	                        cellValue = matchCell.getStringCellValue();
	                    } else if (matchCell.getCellType() == CellType.NUMERIC) {
	                        // Convert numeric value to a string
	                        cellValue = String.valueOf(matchCell.getNumericCellValue());
	                    }

	                    if (cellValue != null && cellValue.equals(matchValue)) { 
	                        Cell targetCell = row.getCell(targetColumnIndex);
	                        if (targetCell != null) {
	                            switch (targetCell.getCellType()) {
	                                case STRING:
	                                    value= targetCell.getStringCellValue();
	                                    System.out.println(value +"string");
	                                    break;
	                                case NUMERIC:
	                                    double values = targetCell.getNumericCellValue();
	                                    double roundedValue = Math.round(values * 100.0) / 100.0;
	                                    System.out.println("Rounded Value: " + roundedValue); 
	                                    break;
	                                default:
	                                    System.out.println("Value: ");
	                            }
	                        }
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        catch (IllegalStateException e) {
	        	value=null;
	        	System.out.println("****");
				}

	}

}
