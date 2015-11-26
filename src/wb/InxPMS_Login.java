package wb;

import java.util.concurrent.TimeUnit;

import lib.ExcelDataConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import wb.*;


public class InxPMS_Login {

	WebDriver wb;
	
	@Test(dataProvider="datafromSheet")//@Test indicates this function should work as test case and dataProvider="datafromSheet" indicates it will get data from this dataprovider 
	public void loginPMS(String username,String password){
		wb=new FirefoxDriver();
	
		String webaddress="http://pms.inheritxserver.net/";
		//wb.manage().window().maximize();
		wb.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wb.get(webaddress);
		wb.findElement(By.id("loginform-email")).sendKeys(username);
		wb.findElement(By.id("loginform-password")).sendKeys(password);
		wb.findElement(By.xpath("//*[@id='login-form']/div[3]/button")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
		Assert.assertTrue(wb.getTitle().contains("My Yii"), "got incorrect title");
		//This will evaluate the title of the page. It will print below message-Successflly logged in-if it is true otherwise 'got incorrect title' message on console 
		System.out.println("Successflly logged in");
				
	}
	@AfterMethod//this annotation indicates it will execute each time after completing execution of each test case 
	public void browerQuit(){
		System.out.println("Closed the browser");
		wb.quit();
		//closes the browser
	}
	@DataProvider(name="datafromSheet")//This annotation indicates that this function will provide data to other function. following syntax is must for dataprovider function
	public Object[][] getData(){
		//This function will return data in the form of 2-d objects
		String path=System.getProperty("user.dir");//getting the project path
		ExcelDataConfig dc=new ExcelDataConfig(path+"\\file\\TestData.xls");//Creating object of ExcelDataConfig class and passing filepath as an argument
		int rows=dc.getRowCount(0);//get the number of rows from the sheet '0'
		Object[][] data = new Object[rows][2];//[rows][2] indicates there will be 'n' number of rows and '2' columns in the sheet'0'
		for(int i=0; i<rows; i++){
			data[i][0]=dc.getData(0, i, 0);//get data of first column
			data[i][1]=dc.getData(0, i, 1);//get data of second column
		}
		return data;
	}
}
