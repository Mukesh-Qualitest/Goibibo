/**
 * 
 */
package pageObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.baseClass;

/**
 * @author mukesh.mandal
 *
 */
public class cabPageObjects {

	private WebDriver driver;

	public cabPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	By onewayService = By.xpath("//label[@id='Outstation-One-way']");
	By pickupInputBox = By.xpath("//input[@placeholder='Enter Pickup location']");
	By bangaloreLocSelect = By.xpath("//h4[text()='Popular Searches']/ancestor::div[contains(@class,'cKAcje')]/descendant::p[contains(text(),'Bangalore')]");
	By dropInputBox = By.xpath("//input[@placeholder='Enter Drop location']");
	By mysuruLocSelect = By.xpath("//h4[text()='Popular Searches']/ancestor::div[contains(@class,'cKAcje')]/descendant::p[contains(text(),'Mysuru')]");
	By pickupDate = By.xpath("//div[contains(@class,'iCkPml')]/descendant::span");
	By month_year = By.xpath("//p[contains(@class,'cgPmkC')]");
	By nextMonthClick = By.xpath("//div[contains(@class,'bvshgn')]");
	By pickupTimeClick = By.xpath("//label[text()='Pickup Time']/ancestor::div/span");
	By searchCab = By.xpath("//button[text()='SEARCH CABS']");
	By selectTime = By.xpath("//span[text()='11:30 PM']");
	By etiosSelect = By.xpath("//h4[text()='Etios']/ancestor::div[contains(@class,'kuHnZt')]/descendant::button[text()='SELECT']");
	By clearInputBox = By.xpath("//label[text()='Enter Pickup Location']/ancestor::div[@class='labelCont']/following-sibling::input");
	By updatePickUpLocation = By.xpath("//p[contains(text(),'Back gate 5')]");
	By nameInput = By.xpath("//input[@placeholder='Enter Full Name']");
	By phoneInputBox = By.xpath("//input[@placeholder='Enter Mobile Number']");
	By emailInputBox = By.xpath("//input[@placeholder='Enter Email']");
	By paymentButton = By.xpath("//button[@id='paymentButton']");
	By closeAdd = By.xpath("//div[contains(@class,'MHKLz')]");
	
	public void onewayCheck() {
		driver.findElement(onewayService).click();
	}
	
	public void cabEnterDetails() {
		driver.findElement(pickupInputBox).click();
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(bangaloreLocSelect));
		driver.findElement(bangaloreLocSelect).click();
		driver.findElement(dropInputBox).click();
		WebElement wt1 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(mysuruLocSelect));
		driver.findElement(mysuruLocSelect).click();
	}
	
	public void dateAndTime() {
		driver.findElement(pickupDate).click();
		WebElement wt1 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(month_year));
		currentDate();
		driver.findElement(pickupInputBox).click();
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(pickupTimeClick));
		baseClass.delayScript();
		driver.findElement(pickupTimeClick).click();
		driver.findElement(searchCab).click();
	}
	public void selectCar() {
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(closeAdd));
		driver.findElement(closeAdd).click();
		driver.findElement(etiosSelect).click();
	}
	
	public void enterPassDetails(){
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(clearInputBox));
		driver.findElement(clearInputBox).clear();
		baseClass.delayScript();
		driver.findElement(clearInputBox).sendKeys("manyata");
		WebElement wt1 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(updatePickUpLocation));
		driver.findElement(updatePickUpLocation).click();
		WebElement wt3 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(nameInput));
		driver.findElement(nameInput).sendKeys("xyz");
		driver.findElement(phoneInputBox).sendKeys("0987654321");
		driver.findElement(emailInputBox).sendKeys("abc@xyz.com");
		driver.findElement(paymentButton).click();
	}
	
	public void currentDate() {
		String currentDateTime = getCurrentFormattedTime("d-MMMMMMMMM-yyyy");
		selectDate(currentDateTime);
	}

	public void futureDate(int days) {
		String currentDateTime = getFutureFormatedTime(days, "d-MMMMMMMMM-yyyy");
		selectDate(currentDateTime);
	}

	public static String getFutureFormatedTime(int daysToBeAdded, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		TimeZone etTimeZone = TimeZone.getTimeZone("America/New_York");
		dateFormat.setTimeZone(etTimeZone);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, daysToBeAdded);
		return dateFormat.format(calendar.getTime());
	}

	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		TimeZone etTimeZone = TimeZone.getTimeZone("GMT-5:00");
		dateFormat.setTimeZone(etTimeZone);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

	public void selectDate(String currentDateTime) {

		String curDay = currentDateTime.split("-")[0].trim();
		String curMonth = currentDateTime.split("-")[1].trim();
		String curYear = currentDateTime.split("-")[2].trim();

		String monthYear = driver.findElement(month_year).getText();
		String month = monthYear.split(" ")[0].trim();
		String year = monthYear.split(" ")[1].trim();
		while (!(curMonth.equals(month) && curYear.equals(year))) {
			driver.findElement(nextMonthClick).click();
			monthYear = driver.findElement(month_year).getText();
			month = monthYear.split(" ")[0].trim();
			year = monthYear.split(" ")[1].trim();
		}
		driver.findElement(By.xpath("//p[contains(text(),'"+month +" "+year+"')]/ancestor::div[contains(@class,'dVjvCM')]/descendant::span[text()='"+curDay+"']"))
				.click();
	}
 }
