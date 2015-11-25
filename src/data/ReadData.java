package data;

import lib.ExcelDataConfig;

public class ReadData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
			String path=System.getProperty("user.dir");
			ExcelDataConfig dc=new ExcelDataConfig(path+"\\file\\TestData.xls");
			
			String data=dc.getData(0, 1, 0);
			System.out.println(data);
		
	}

}
