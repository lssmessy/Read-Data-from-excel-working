package lib;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//Following code is generalized library which we can use in multiple scripts at the same time from
//single data source i.e Excel file
public class ExcelDataConfig {
	
	HSSFWorkbook wb;
	HSSFSheet sheet;
	//We have used HSSFWorkbook because we are using .xls file (compatible for Windows NT/95/XP) 
	//If you wants to .xlsx (latest excel files[after 2007]) then use XSSF object everywhere
	public ExcelDataConfig(String filepath){//This is the constructor of the class and it accepts string as an argument
		
		try {
			
			File fl=new File(filepath);
			//it imports file from the given 'filepath'
			FileInputStream fis=new FileInputStream(fl);
			//it converts the imported files into java objects which is readable by java objects
			wb=new HSSFWorkbook(fis);
			//initialize HSSFWorkbook object
			if(wb==null)//Check if workbook(our excel file) is null or not
			{
				System.out.println("Wb is null");
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public String getData(int sheetNumber, int row, int col) {
		//This function will accept the arguments as sheetnumber, row number and column number from where we will get the data
		//It will return the data from particular cell in the form of string  
		sheet=wb.getSheetAt(sheetNumber);
		//get the sheet number. Moreover, sheet number starts with '0' (like array). So, first sheet will have '0' number
		if(sheet==null){//check whether sheet contains some data or not
			System.out.println("Sheet is null");
		}
		String data=sheet.getRow(row).getCell(col).getStringCellValue();
		//This will give actual data from a cell. 
		return data;
		//It will return the string data as a result
	}
	public int getRowCount(int sheetNumber){
		//This function will count how many rows are there in a sheet and it will return the count of rows
		int rows=wb.getSheetAt(sheetNumber).getLastRowNum();
		//get the number of rows.For exmaple,There are 15 rows in sheet
		//It will give '14' as rows values because it works like array(start from 0 to 14) 
		rows+=1;
		//Make count 15
		return rows;
		//return the count
	}
	

}
