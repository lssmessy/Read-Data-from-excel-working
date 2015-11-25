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
	
	@Test(dataProvider="datafromSheet")
	public void loginGmail(String username,String password){
		wb=new FirefoxDriver();
	
		String webaddress="http://pms.inheritxserver.net/";
		wb.manage().window().maximize();
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
