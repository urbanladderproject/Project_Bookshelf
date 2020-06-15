package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class Excelutlis_Collections_List {
	
	public static String path;
	public static String Chrome_result = System.getProperty("user.dir") + "\\testresult_chrome.xls";
	public static String Firefox_result = System.getProperty("user.dir") + "\\testresult_firefox.xls";
	
	public static void ResultData(String[] data,String browser) throws IOException {
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
		int rownum = sheet.getLastRowNum() + 2;
		System.out.println(rownum);
		HSSFRow row ;
		HSSFCell cell;
		int length = data.length;
		for(int i=0;i<length;i++) {
			row= sheet.createRow(rownum);
			cell =  row.createCell(0);
			try 
			{
				cell.setCellValue(data[i]);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				//return false;
			}
			rownum++;
		}
		
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();
		wb.close();
		
	}
}