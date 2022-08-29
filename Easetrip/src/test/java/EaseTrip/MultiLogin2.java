package Orangehrm;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.DataReaders;
import utils.DataWriter;

public class MultiLogin2 {
	WebDriver driver;
	String  filetowrite =  "C:\\Users\\10699082\\Desktop\\Dataresult.xlsx";
	String sheet_to_write = "Sheet1";
	static ExtentTest test;
	static ExtentReports report;
	
	@BeforeTest
	public void Lauunchbrowser()
	{
//    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\10699082\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		report = new ExtentReports("C:\\Users\\10699082\\eclipse-workspace\\KT_TRAINING\\test-output\\Extent-report\\extent-report1.html");
		test = report.startTest("MultiLogin2");
       
       
	}
	@DataProvider(name = "data-set")
    public static Object[][] DataSet() throws IOException{
		String  Filename = "C:\\Users\\10699082\\Desktop\\worksheet.xlsx" ;
		String  Sheetname = "Sheet1";
        Object[][] data = DataReaders.getExcelDataUsingPoi(Filename,Sheetname);
		return data;
       
    }
    
	@Test(dataProvider = "data-set")
    public void DataProvSampleTest(String type , String username, String password) throws InterruptedException, IOException {
    	driver.get("https://www.saucedemo.com/");
    	driver.manage().window().maximize();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    	Reporter.log(type + " " + username + " " + password + " ",true) ;
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
//        String wrg = "Incorrect";
//        String crt = "correct";
        if(type.equals("valid"))
		{
             wait.until(ExpectedConditions.presenceOfElementLocated(
             By.cssSelector("[class='title']")));
             Thread.sleep(1000);     
             test.log(LogStatus.PASS, "Sucessfully login");
             driver.findElement(By.id("react-burger-menu-btn")).click();
             Thread.sleep(1000);
             String Actual   = driver.getTitle();
     		 String Expected = "Swag Labs";
     		 Assert.assertEquals(Expected,Actual);       
             Thread.sleep(1000);
             System.out.println("Title of the website " + driver.getTitle());
             WebElement logout =  driver.findElement(By.id("logout_sidebar_link"));
             Actions act = new Actions(driver);
             act.moveToElement(logout).click().build().perform();
             Thread.sleep(1000);     
         }
          
       else  {
         // wait.until(ExpectedConditions.presenceOfElementLocated(
         //  By.cssSelector("[data-test='error']")));
    	 driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
		 String error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"))
				 .getAttribute("innerText");
//		 String wrg = "Incorrect";
		 Reporter.log("The Error Message are " + error,true);
		 String Expected = "Epic sadface: Username and password do not match any user in this service";
		 Assert.assertEquals(Expected,error);
		 test.log(LogStatus.PASS, " Invalid Login " + error);
		 File srcfile12 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	     File inValidLogin = new File("src\\test\\resources\\inValidLogin.png");
         FileUtils.copyFile(srcfile12, inValidLogin);
         Reporter.log("<img src =\"C:\\Users\\10699082\\eclipse-workspace\\KT_TRAINING\\src\\test\\resources\\inValidLogin.png\" height='300' width='600'/>");
       		}
        
        DataWriter.writeToFileSingleValue(filetowrite, sheet_to_write,type,username,password ) ;
        test.log(LogStatus.PASS, "logout");
        
	}
	
	@AfterTest
	public void test() {
	report.endTest(test);
	report.flush();
	driver.close();
	}
}
