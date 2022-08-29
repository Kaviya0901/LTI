package Orangehrm;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Testing {
	WebDriver driver ;
	  
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void test1()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		Reporter.log("ChromeBrowser Started",true);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	

	@Test
	public void  invalidLogin() throws IOException {
	try {
		//invalid login function
		driver.findElement(By.xpath
				("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Admin");
		driver.findElement
		(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("12345");
		driver.findElement
		(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
		WebElement invalid = driver.findElement(By.xpath
				("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p"));
		System.out.println("The Message are:"+ invalid.getText());
		String Actual   = driver.getTitle();
		String Expected = "OrangeHRM";
		Assert.assertEquals(Expected,Actual);
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File invalid1 = new File("src\\test\\resources\\invalid.png");
	    FileUtils.copyFile(srcfile, invalid1);
	    Reporter.log("<img src =\"C:\\Users\\10699082\\eclipse-workspace\\DEMO1\\src\\test\\resources\\invalid.png\" height='300' width='300'/>");
	    
		}
	   catch (ElementNotInteractableException e) {
			e.printStackTrace();
			Reporter.log("Exception is found for "+e.getMessage());

		}  	
	}
	
	@Test
	public void OrangeHRM1() throws IOException, InterruptedException
	{	
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Admin");
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("admin123");
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
		driver.findElement(By.linkText("PIM")).click();
//		List<WebElement> element=driver.findElements(By.xpath("//*[@id=\"employee-information\"]/a"));
//		for(WebElement e:element){
//			Reporter.log("links are "+ e.getAttribute("href"),true);
//		    }
        driver.findElement(By.linkText("Employee List")).click();
        driver.findElement(By.xpath
        		("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")).click();
        driver.findElement(By.xpath
        		("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/input"))
        .sendKeys("Kaviya "); 
        driver.findElement(By.xpath
        		("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[2]/input"))
        .sendKeys("Ravi"); 
        driver.findElement(By.xpath
        		("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input"))
        .clear();
        driver.findElement(By.xpath
        		("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input"))
        .sendKeys("0987"); 
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")).click();
        Reporter.log("Employee Added Sucessfully!!",true);
        
        //Searching Employee 
        
        driver.findElement(By.linkText("Employee List")).click();
        driver.findElement(By.xpath
        		("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input"))
        .sendKeys("02510987");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")).click();
        utils.MoreHelperFunctions.wait(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        utils.MoreHelperFunctions.wait(2);
        utils.HelperFunctions.captureScreenShot(driver, "src\\test\\resources\\Search.png");
        Reporter.log("<img src =\"C:\\Users\\10699082\\eclipse-workspace\\KT_TRAINING\\src\\test\\resources\\Search.png\" height ='400' width ='400'/>");
        Reporter.log("<a href=\"C:\\Users\\10699082\\eclipse-workspace\\KT_TRAINING\\src\\test\\resources\\Search.png\">Screenshot1</a>");
        
        //Apply leave
       
		driver.findElement(By.linkText("Leave")).click();
		utils.MoreHelperFunctions.wait(5);
		driver.findElement(By.linkText
				("My Leave")).click();
		WebElement Leave = driver.findElement(By.linkText("Leave Period"));
		Actions act = new Actions(driver);
        act.moveToElement(Leave).click().build().perform();
        Thread.sleep(1000);
        WebElement  Leaveperiod= driver.findElement(By.id
        		("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]"));
        Select s = new Select(Leaveperiod);
        s.selectByValue("4");
        WebElement  Leaveperioddate= driver.findElement(By.id("leaveperiod_cmbStartDate"));
        Select s1 = new Select(Leaveperioddate);
        s1.selectByValue("19");
        driver.findElement(By.id("btnEdit")).click();
        System.out.println("Edited scussefully");
        WebElement Leave1 = driver.findElement(By.xpath("//*[@id=\"frmLeavePeriod\"]/fieldset/ol/li[4]/span"));
        Reporter.log("Leave Period is  "+ Leave1.getText(),true);
        Thread.sleep(5000);
        utils.HelperFunctions.captureScreenShot(driver, "src\\test\\resources\\Screenshots.png");
        utils.MoreHelperFunctions.wait(2);
        System.out.println("Screenshot Taken");
        Reporter.log("<img src =\"C:\\Users\\10699082\\eclipse-workspace\\KT_TRAINING\\src\\test\\resources\\Screenshots.png\" height='300' width='300'/>");
        System.out.println("link added in Testng report");
        Reporter.log("<a href=\"C:\\Users\\10699082\\eclipse-workspace\\KT_TRAINING\\src\\test\\resources\\Screenshots.png\">Screenshot2</a>");
     }

	@AfterMethod
	public void test() {
	driver.quit();
	}
}
