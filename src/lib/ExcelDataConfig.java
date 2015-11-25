package lib;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	
	HSSFWorkbook wb;
	HSSFSheet sheet;
	public ExcelDataConfig(String filepath){
		
		try {
			File fl=new File(filepath);
			FileInputStream fis=new FileInputStream(fl);
			wb=new HSSFWorkbook(fis);
			
			if(wb==null)
			{
				System.out.println("Wb is null");
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public String getData(int sheetNumber, int row, int col) {
		//sheet=wb.getSheet(sheetName);
		sheet=wb.getSheetAt(sheetNumber);
		if(sheet==null){
			System.out.println("Sheet is null");
		}
		String data=sheet.getRow(row).getCell(col).getStringCellValue();
		
		return data;
	}
	public int getRowCount(int sheetNumber){
		int rows=wb.getSheetAt(sheetNumber).getLastRowNum();
		rows+=1;
		return rows;
	}
	

}
