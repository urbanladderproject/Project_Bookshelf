package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelUtils_Bookshelf_name_and_price {

	public static String[][] details = new String[12][9] ;
	public static String path = System.getProperty("user.dir") + "\\testdata.xls";
	public static String Chrome_result = System.getProperty("user.dir") + "\\testresult_chrome.xls";
	public static String Firefox_result = System.getProperty("user.dir") + "\\testresult_firefox.xls";
	
	public static int counter = 1;

	public static String[][] readExcelData(String sheetName) throws Exception
	{
		System.out.println("Entered into excel utils");

		FileInputStream file = new FileInputStream(path);
		System.out.println("Selected the file " + file);
		HSSFWorkbook wb = new HSSFWorkbook(file);
		HSSFSheet sheet = wb.getSheet(sheetName);
		int n = 13;
		for(int i = 1;i<n;i++) 
		{
			HSSFRow row = sheet.getRow(i);
			details[i-1][0] = String.valueOf(row.getCell(1).getStringCellValue());
			System.out.println(details[i-1][0]);
			float cell_float = (float) row.getCell(2).getNumericCellValue();
			int cell_int = (int) cell_float;
			details[i-1][1] = String.valueOf(cell_int);
			System.out.println(details[i-1][1]);
			details[i-1][2] = String.valueOf(row.getCell(3).getStringCellValue());
			System.out.println(details[i-1][2]);
			details[i-1][3] = String.valueOf(row.getCell(4).getNumericCellValue());
			System.out.println(details[i-1][3]);
			details[i-1][4] = String.valueOf(row.getCell(5).getStringCellValue());
			System.out.println(details[i-1][4]);
			details[i-1][5] = String.valueOf(row.getCell(6).getStringCellValue());
			System.out.println(details[i-1][5]);
			details[i-1][6] = String.valueOf(row.getCell(7).getStringCellValue());
			System.out.println(details[i-1][6]);
			details[i-1][7] = String.valueOf(row.getCell(8).getStringCellValue());
			System.out.println(details[i-1][7]);
			details[i-1][8] = String.valueOf(row.getCell(9).getStringCellValue()).substring(1);
			System.out.println(details[i-1][8]);
		}
		System.out.println("Returning to search Page");
		return details;

	}

	public static void writeExcelData(String[][] results,String browser) throws Exception {

		System.out.println("Into write excel Data");
		if(browser.equalsIgnoreCase("Chrome")) {
			path = Chrome_result;
		}
		else if(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("Mozilla")) {
			path = Firefox_result;
		}

		FileInputStream files = new FileInputStream(new File(path));
		HSSFWorkbook wb = new HSSFWorkbook(files);
		HSSFSheet sheet = wb.getSheet("testresult");
		int rownum;
		if(counter == 1)
			rownum = 0;
		else
			rownum = sheet.getLastRowNum() + 2;
		//System.out.println(rownum);
		HSSFRow row;
		HSSFCell cell;
		for(int i=0;i<5;i++) 
		{

			row = sheet.createRow(rownum);
			cell = row.createCell(0);
			cell.setCellValue(results[i][0]);
			cell = row.createCell(1);
			cell.setCellValue(results[i][1]);
			rownum++;

		}

		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		System.out.println("Writing to excel sheet");
		fos.close();
		wb.close();

	}

}
