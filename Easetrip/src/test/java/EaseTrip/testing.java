package EaseTrip;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class testing{
	static WebDriver driver;
	static Page_Factory pf;
	static Properties prop;
	WebDriverWait wait;

@BeforeMethod
	public void beforeMethod() throws IOException {
	prop=new Properties();
	FileInputStream inp = new FileInputStream("C:\\Users\\kaviy\\eclipse-workspace\\flights\\confif,properties");
	prop.load(inp);
	System.setProperty("webdriver.chrome.driver",prop.getProperty("chrome"));
	driver=new ChromeDriver();
	driver.get(prop.getProperty("url"));
	driver.manage().window().maximize();
	pf = PageFactory.initElements(driver, Page_Factory.class);
	}

	@Test(priority=1)
		public void Hotelicon() {
		Page_Factory.hotels_icon.isDisplayed();
		Page_Factory.hotels_icon.isEnabled();
		Page_Factory.hotels_icon.click();
		Page_Factory.enter_loc.click();
		Page_Factory.select_loc.sendKeys("Goa");
		Actions act=new Actions(driver);
		act.pause(2000).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Page_Factory.checkin_button.click();
		Page_Factory.check_in.click();
		Page_Factory.check_out.click();
		Page_Factory.add_adult.click();
		Page_Factory.add_child.click();
		Page_Factory.add_room.click();
		Page_Factory.add_done.click();
		Page_Factory.add_search.click();
	}
	
	@Test(priority=2)
	public void invaliddate() {
		driver.get(prop.getProperty("url"));
	Page_Factory.hotels_icon.isDisplayed();
	Page_Factory.hotels_icon.isEnabled();
	Page_Factory.hotels_icon.click();
	Page_Factory.enter_loc.click();
	Page_Factory.select_loc.sendKeys("Goa");
	Actions act=new Actions(driver);
	act.pause(2000).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
	Page_Factory.checkin_button.click();
	Page_Factory.check_in.click();
	Page_Factory.invalid_date.click();
	Page_Factory.add_adult.click();
	Page_Factory.add_done.click();
	Page_Factory.add_search.click();
	Alert alt=driver.switchTo().alert();
	alt.accept();

}
	@Test(priority=3)
	public void RemoveRoom() {
		
	Page_Factory.hotels_icon.isDisplayed();
	Page_Factory.hotels_icon.isEnabled();
	Page_Factory.hotels_icon.click();
	Page_Factory.enter_loc.click();
	Page_Factory.select_loc.sendKeys("Goa");
	Actions act=new Actions(driver);
	act.pause(2000).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
	Page_Factory.checkin_button.click();
	Page_Factory.check_in.click();
	Page_Factory.check_out.click();
	Page_Factory.add_adult.click();
	Page_Factory.add_child.click();
	Page_Factory.add_room.click();
	Page_Factory.removeroom.click();
	Page_Factory.add_done.click();
	Page_Factory.add_search.click();
	}
	
	 @Test(priority=4)
	public void all_filters() {
    driver.get(prop.getProperty("url3"));
	Page_Factory.sortBy.click();
	Page_Factory.Star_Rating.click();
	Page_Factory.trip_rating.click();
	Page_Factory.priceLtoH.click();
	Page_Factory.slider.click();
	Page_Factory.Property_type.click();
		}
	
   
   @AfterMethod
		public void afterClass() throws InterruptedException {
			 driver.quit();
			 }
}


	

