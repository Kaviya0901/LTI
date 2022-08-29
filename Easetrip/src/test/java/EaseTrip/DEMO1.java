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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DEMO1 {
	WebDriver driver ;
	 

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void Lauunchbrowser()
	{
				
		WebDriverManager.chromedriver().setup();	
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\10699082\\Downloads\\chromedriver_win32\\chromedriver.exe");
	    driver = new ChromeDriver();	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		Reporter.log("ChromeBrowser Started",true);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		}
	
	@Test
	public void  invalidLogin() throws IOException {
	try {
		//invalid login function
		driver.findElement(By.cssSelector("#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("#txtPassword")).sendKeys("admin124");
		driver.findElement(By.className("button")).click();
		WebElement invalid = driver.findElement(By.xpath("//*[@id=\"spanMessage\"]"));
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
	public void  validLogin() throws InterruptedException, IOException {
		
		driver.findElement(By.cssSelector("#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("#txtPassword")).sendKeys("admin123");
		driver.findElement(By.className("button")).click();
		WebElement  PIM= driver.findElement(By.xpath("//*[@id=\"menu_pim_viewPimModule\"]/b"));
		Actions act = new Actions(driver);
        act.moveToElement(PIM).click().build().perform();
        Thread.sleep(1000);
		List<WebElement> element=driver.findElements(By.xpath("//*[@id=\"employee-information\"]/a"));
		for(WebElement e:element){
			Reporter.log("links are "+ e.getAttribute("href"),true);
		}
		
        driver.findElement(By.linkText("Employee List")).click();
        driver.findElement(By.cssSelector("#btnAdd")).click();
        driver.findElement(By.cssSelector("#firstName")).sendKeys("Kaviya "); 
        driver.findElement(By.cssSelector("#lastName")).sendKeys("Ravi"); 
        driver.findElement(By.cssSelector("#employeeId")).clear(); 
        driver.findElement(By.cssSelector("#employeeId")).sendKeys("0987"); 
        driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
        Reporter.log("Employee Added Sucessfully!!",true);
	    File srcfile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File Screenshot = new File("src\\test\\resources\\Adduser+"+driver.getTitle()+".png");
	    FileUtils.copyFile(srcfile1, Screenshot);
	   
	    WebElement  PIM1= driver.findElement(By.xpath("//*[@id=\"menu_pim_viewPimModule\"]/b"));
		Actions act1 = new Actions(driver);
        act1.moveToElement(PIM1).click().build().perform();
        driver.findElement(By.linkText("Employee List")).click();
	    driver.findElement(By.xpath("//*[@id=\"empsearch_id\"]")).sendKeys("0987");
	    driver.findElement(By.xpath("//*[@id=\"searchBtn\"]")).click();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,250)", "");
	    WebElement Result = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[3]/a"));
	    
	    //Get all the links in created user
	    List<WebElement> element2=driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td/a"));
		for(WebElement e1:element2){
		Reporter.log(" Searched User links are " + e1.getAttribute("href"),true);
		     }
	    
	    //Print the Employee Name
	    System.out.println("Employee searched at 0987 is "+ Result.getText());
	    File srcfile11 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    File Search = new File("src\\test\\resources\\Search+"+driver.getTitle()+".png");
	    FileUtils.copyFile(srcfile11, Search);
	    Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]/a")).click();
		js.executeScript("window.scrollBy(0,250)", "");
	    driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
	    
	     //EDIT USER	        
	    	WebElement Gender = driver.findElement(By.id("personal_optGender_2"));								
	    	Gender.click();			
	    	Reporter.log("Gender Button Selected",true);
	        WebElement Calender = driver.findElement(By.id("personal_DOB"));
	        Calender.click();
	        Select Month = new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")));
	        Month.selectByVisibleText("Jan");
	        Select year = new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]")));
	        year.selectByVisibleText("2000");
	        driver.findElement(By.linkText("2")).click();
	        Reporter.log("Date Selected",true);
	        Thread.sleep(1000);
	        Select s = new Select(driver.findElement(By.id("personal_cmbNation")));
	        s.selectByVisibleText("Indian");
	        Reporter.log("Nationality Selected",true);
	        
	        // EDIT QUALIFICATIONS
	        
	        driver.findElement(By.linkText("Qualifications")).click();
	        driver.findElement(By.id("addEducation")).click();
	        Select Qualifications = new Select(driver.findElement(By.xpath("//*[@id=\"education_code\"]")));
	        Qualifications.selectByVisibleText("Bachelor's Degree");
	        Reporter.log("Degree Added",true);
	        Thread.sleep(2000);
	        driver.findElement(By.id("education_institute")).sendKeys("DMI College of Engineering");
	        Reporter.log("College Added",true);
	        Thread.sleep(2000);
	        driver.findElement(By.id("education_major")).sendKeys("IT");
	        Reporter.log("Major Stream Added",true);
	        Thread.sleep(2000);
	        driver.findElement(By.id("education_year")).sendKeys("2021");
	        Reporter.log("Year Added",true);
	        Thread.sleep(2000);
	        driver.findElement(By.id("education_gpa")).sendKeys("8.23");       
	        Reporter.log("GPA Added",true);
	        Thread.sleep(2000);
	        Thread.sleep(1000);
	        
	        WebElement Calender2 = driver.findElement(By.id("education_start_date"));
	        Calender2.click();
	        Select Month2 = new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")));
	        Month2.selectByVisibleText("Sep");
	        Select year2 = new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]")));
	        year2.selectByVisibleText("2017");
	        driver.findElement(By.linkText("10")).click();
	        
	        WebElement Calender3 = driver.findElement(By.id("education_end_date"));
	        Calender3.click();
	        Select Month3 = new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")));
	        Month3.selectByVisibleText("Mar");
	        Select year3 = new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]")));
	        year3.selectByVisibleText("2021");
	        driver.findElement(By.linkText("10")).click();
	        Reporter.log("Calender Button Selected",true);
	        Thread.sleep(1000);

	        //APPLY LEAVE
			WebElement  Leave= driver.findElement(By.xpath("//*[@id=\"menu_leave_viewLeaveModule\"]/b"));
			WebElement  Configure= driver.findElement(By.xpath("//*[@id=\"menu_leave_Configure\"]"));
			Actions act2 = new Actions(driver);
	        act2.moveToElement(Leave).moveToElement(Configure).click().build().perform();
	        Thread.sleep(1000);
	        
	        driver.findElement(By.linkText("Leave Period")).click();
	        driver.findElement(By.xpath("//*[@id=\"btnEdit\"]")).click();
	        WebElement  Leaveperiod= driver.findElement(By.id("leaveperiod_cmbStartMonth"));
	        Select Leavemonth = new Select(Leaveperiod);
	        Leavemonth.selectByValue("4");
	        WebElement  Leaveperioddate= driver.findElement(By.id("leaveperiod_cmbStartDate"));
	        Select date = new Select(Leaveperioddate);
	        date.selectByValue("19");
	        driver.findElement(By.id("btnEdit")).click();
	        Reporter.log("Edited Qualifications scussefully",true);
	        
	        WebElement Leave1 = driver.findElement(By.xpath("//*[@id=\"frmLeavePeriod\"]/fieldset/ol/li[4]/span"));
	        Reporter.log("Leave Period is  "+ Leave1.getText(),true);
	        Thread.sleep(5000);
	        File srcfile12 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File ApplyLeave = new File("src\\test\\resources\\Leave.png");
		    FileUtils.copyFile(srcfile12, ApplyLeave);
		    System.out.println("Screenshot Taken");
	        Reporter.log("<img src =\"C:\\Users\\10699082\\eclipse-workspace\\DEMO1\\src\\test\\resources\\Leave.png\" height='300' width='300'/>");
	        System.out.println("link added in Testng report");
	        Reporter.log("<a href=\"C:\\Users\\10699082\\eclipse-workspace\\DEMO1\\src\\test\\resources\\Leave.png\">Screenshot2</a>");
	        
	        }
     
	 @AfterTest
	public void test2() {	
	driver.close();
	    }
	}

