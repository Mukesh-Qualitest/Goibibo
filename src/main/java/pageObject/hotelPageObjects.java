/**
 * 
 */
package pageObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.baseClass;

/**
 * @author mukesh.mandal
 *
 */
public class hotelPageObjects {

	private WebDriver driver;

	public hotelPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	String country ="India",city="Mumbai",hotel="Santacruz",type1="Luxury",type2="King",type3="Pool";
	
	By select_Country = By.xpath("//h4[text()='India']/preceding-sibling::input");
	By check_In_Date = By.xpath("//div[contains(text(),'Check-in')]/following-sibling::h4");
	By month_Year = By.xpath("//div[contains(@class,'kPBkGN')]//following-sibling::span[contains(@class,'jdlFBV')]");
	By landmark_Input = By.xpath("//input[contains(@class,'euhecC')][contains(@placeholder,'Landmark')]");
	By trending_City_List = By.xpath("//ul[contains(@class,'sUlOI')]//child::p[text()='"+city+"']");
	By customer_Rating = By.xpath("//span[text()='Customer Ratings']");
	By hotel_Options = By.xpath("//h4[contains(text(),'"+hotel+"')]/ancestor::div[contains(@class,'geKxaL')]");
	By view_Room_Options = By.xpath("//span[contains(text(),'VIEW')]/ancestor::button");
	By select_Room_Button = By
			.xpath("//h3[contains(text(),'"+type1+"') and contains(text(),'"+type2+"') and contains(text(),'"+type3+"')]/ancestor::div[contains(@class,'ZTwTx')]/descendant::button[1]");
	By title_Selection = By.xpath("//select[contains(@class,'hkMeMW')]");
	By enter_First_Name = By.xpath("//input[contains(@class,'hEgRgJ')][@placeholder='Enter First Name']");
	By enter_Last_Name = By.xpath("//input[contains(@class,'hEgRgJ')][@placeholder='Enter Last Name']");
	By enter_Email_Name = By.xpath("//input[contains(@class,'fBxMec')]");
	By enter_Phone_Name = By.xpath("//input[contains(@class,'eAViUT')]");
	By book_Button = By.xpath("//button[contains(@class,'blGWwt')]");
	By payment_Button = By.xpath("//button[contains(@class,'blGWwt')]");
	By assert_Value = By.xpath("//div[@class='bookingSumry__infoLink']");

	public void enterHotelDetails(String country) {
		driver.findElement(check_In_Date).click();
		getFutureDate(10);
		System.out.println("After changes");
		System.out.println("After changes2");
		getFutureDate(20);
		driver.findElement(select_Country).click();
		driver.findElement(landmark_Input).click();
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(trending_City_List));
		driver.findElement(trending_City_List).click();
	}
	
	public void hotelSelection() {
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(customer_Rating));
		driver.findElement(customer_Rating).click();
		WebElement wt1 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(hotel_Options));
		driver.findElement(hotel_Options).click();
		switchWindow();
//		WebElement wt2 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
//				.until(ExpectedConditions.presenceOfElementLocated(view_Room_Options));
//		baseClass.delayScript();
//		driver.findElement(view_Room_Options).click();
		WebElement wt3 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(select_Room_Button));
		baseClass.delayScript();
		driver.findElement(select_Room_Button).click();
	}
	
	public void enterCustomerDetails(String first,String last,String email,String phone) {
		
		WebElement wt3 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(title_Selection));
		Select sc = new Select(driver.findElement(title_Selection));
		sc.selectByVisibleText("Miss");
		driver.findElement(enter_First_Name).sendKeys(first);
		driver.findElement(enter_Last_Name).sendKeys(last);
		driver.findElement(enter_Email_Name).sendKeys(email);
		driver.findElement(enter_Phone_Name).sendKeys(phone);
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(payment_Button));
		driver.findElement(payment_Button).click();
	}

	public void hotelAssertValue() {
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(assert_Value));
		Assert.assertTrue(driver.findElement(assert_Value).isDisplayed());
	}
	
	public void selectDate(String currentDateTime) {
		System.out.println(currentDateTime);
		String curDay = currentDateTime.split("-")[0].trim();
		String curMonth = currentDateTime.split("-")[1].trim();
		String curYear = currentDateTime.split("-")[2].trim();
		String month_year = driver.findElement(month_Year).getText();
		System.out.println(month_year);
		String month = month_year.split(" ")[0].trim();
		String year = month_year.split(" ")[1].trim();
		while (!(curMonth.equals(month) && curYear.equals(year))) {
			driver.findElement(By.xpath("//span[contains(@class,'WOjvm')]")).click();
			month_year = driver
					.findElement(By.xpath(
							"//div[contains(@class,'kPBkGN')]//following-sibling::span[contains(@class,'jdlFBV')]"))
					.getText();
			month = month_year.split(" ")[0].trim();
			year = month_year.split(" ")[1].trim();
		}
		driver.findElement(By.xpath("//span[contains(text(),'" + month + " " + year
				+ "')]//ancestor::div[contains(@class,'kCupBq')]//child::ul[contains(@class,'eFyCNL')]/li[@class='date_is_selectable_true']/span[text()='"
				+ curDay + "']")).click();
	}

	public void getCurrentDate() {
		String currentDateTime = getCurrentFormattedTime("d-MMMMMMMMM-yyyy");
		selectDate(currentDateTime);
	}
	
	public void getFutureDate(int days) {
		String currentDateTime = getFutureFormatedTime(days, "d-MMMMMMMMM-yyyy");
		selectDate(currentDateTime);
	}
	
	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		TimeZone etTimeZone = TimeZone.getTimeZone("GMT-5:00");
		dateFormat.setTimeZone(etTimeZone);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

	public static String getFutureFormatedTime(int daysToBeAdded, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		TimeZone etTimeZone = TimeZone.getTimeZone("America/New_York");
		dateFormat.setTimeZone(etTimeZone);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, daysToBeAdded);
		return dateFormat.format(calendar.getTime());
	}
	public void switchWindow() {
		String parentWindow=driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String childWindow1 = it.next();
		String childWindow = it.next();
		driver.switchTo().window(childWindow);
	}
}
