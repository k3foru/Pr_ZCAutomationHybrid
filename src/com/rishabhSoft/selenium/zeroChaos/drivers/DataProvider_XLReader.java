package com.rishabhSoft.selenium.zeroChaos.drivers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * @author Abhishek Singh
 * It Will Read all data from test data sheet.
 * */
public class DataProvider_XLReader {
/**
 * 
 * @param xlFilePath
 * @param sheetName
 * @return
 */
	public static String[][] getTableArray(String xlFilePath, String sheetName) {
		List<List<String>> searchTerms = new ArrayList<List<String>>();
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			int startRow = 1, startCol = 0, endRow = sheet.getRows(), endCol = getNumberOfColumns(sheet);
			
			System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", " + "startCol=" + startCol + ", endCol=" + endCol);
			
			outer: for (int rowNum = startRow; rowNum < endRow; rowNum++) {
				List<String> rowItems = new ArrayList<String>();
				for (int colNum = startCol; colNum < endCol; colNum++) {
					String contents = sheet.getCell(colNum, rowNum).getContents();
					if (contents == null || contents.trim().length() == 0) {
						break outer;
					}
					rowItems.add(contents);
				}
				searchTerms.add(rowItems);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in getTableArray()");
		}
		
		String[][] result = new String[searchTerms.size()][];
		int i = 0;
		for(List<String> rowItems : searchTerms) {
			result[i++] = rowItems.toArray(new String[0]);
		}
		return result;
	}
	
	private static int getNumberOfColumns(Sheet sheet) {
		if(sheet.getRows() < 1) {
			return 0;
		}
		Cell[] allColumnCells = sheet.getRow(0);
		int i = 0;
		for(i = 0; i < allColumnCells.length; i++) {
			String content = allColumnCells[i].getContents();
			if(content == null || content.trim().length() == 0) {
				break;
			}
		}
		return i;
	}
}