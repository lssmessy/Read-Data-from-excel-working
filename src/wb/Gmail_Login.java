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


public class Gmail_Login {

	WebDriver wb;
//	public static void main(String a[]){
//		
//	}
	@Test(dataProvider="datafromSheet")
	public void loginGmail(String username,String password){
		wb=new FirefoxDriver();
		String webaddress="https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/#identifier";
		
		wb.manage().window().maximize();
		wb.get(webaddress);
		wb.findElement(By.id("Email")).sendKeys(username);
		wb.findElement(By.id("next")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wb.findElement(By.id("Passwd")).sendKeys(password);
		wb.findElement(By.id("signIn")).click();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(wb.getTitle().contains("Inbox"), "got incorrect title");
		System.out.println("Successflly logged in");
				
	}
	@AfterMethod
	public void browerQuit(){
		wb.quit();
	}
	@DataProvider(name="datafromSheet")
	public Object[][] getData(){
		
		String path=System.getProperty("user.dir");
		ExcelDataConfig dc=new ExcelDataConfig(path+"\\file\\TestData.xls");
		
		int rows=dc.getRowCount(0);
		Object[][] data = new Object[rows][2];
		for(int i=0; i<rows; i++){
			data[i][0]=dc.getData(0, i, 0);
			data[i][1]=dc.getData(0, i, 1);
		}
		return data;
	}
}
