package EaseTrip;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



	public class Page_Factory {

		@FindBy(xpath = "//div[@class='ng-scope']//a[normalize-space()='Hotels']")
		public static WebElement hotels_icon;

		@FindBy(xpath ="//span[contains(text(),'Bangalore,')]")
		public static WebElement enter_loc;

		@FindBy(name="txtCity")
		public static WebElement select_loc;

		@FindBy(id="htl_dates")
		public static WebElement checkin_button;

		@FindBy(linkText="1")
		public static WebElement check_in;
		
		@FindBy(linkText="1")
		public static WebElement invalid_date;
		
		@FindBy(linkText="5")
		public static WebElement check_out;
		
		@FindBy(id = "Adults_room_1_1_plus")
		public static WebElement add_adult;
		
		@FindBy(id ="Children_room_1_1_plus")
		public static WebElement add_child;
		
        @FindBy(linkText = "Done")
        public static WebElement add_done;
        
        @FindBy(xpath = "//input[@onclick='HotelSearch();']")
        public static WebElement add_search;
		
		@FindBy(linkText = "Add Room")
		public static WebElement add_room;
		
		@FindBy(linkText = "Remove Room")
		public static WebElement removeroom;
		
		@FindBy(xpath = "//*[@id=\"sidebar\"]/div/div[6]/label[1]")
		public static WebElement Star_Rating;

		@FindBy(xpath = "//*[@id=\"rating-filter\"]/label[2]/span[1]/img")
		public static WebElement trip_rating;

		@FindBy(xpath = "//*[@id=\"drpHighList\"]")
		public static WebElement sortBy;

		@FindBy(xpath = "//*[@id=\"drpHighList\"]/option[2]")
		public static WebElement priceLtoH;
		
		@FindBy(xpath = "//*[@id=\"sidebar\"]/div/div[15]/div/label[2]")
		public static WebElement Amentities;
		
		@FindBy(xpath = "//*[@id=\"slider-range\"]/span[2]")
		public static WebElement slider;
		
		@FindBy(xpath="//*[@id=\"sidebar\"]/div/div[18]/div/label[5]")
		public static WebElement Property_type;

		@FindBy(xpath = "//div[@class='right-content']//div[2]//div[2]//div[1]//div[1]//a[1]//div[6]")
		public static WebElement selectHotel;
	}


