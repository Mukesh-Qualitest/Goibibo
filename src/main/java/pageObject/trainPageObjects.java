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
public class trainPageObjects {

	private WebDriver driver;

	public trainPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	By bookTrain = By.xpath("//ul[@class='styles_TripTypeCont__NdHOs']/descendant::h2");
	By clickSource = By.xpath("//p[contains(text(),'Source')]");
	By enterCityName = By.xpath("//input[@type='text']");
	By clickDestination = By.xpath("//p[contains(text(),'Destination ')]");
	By surceDelhiClick = By.xpath(
			"//p[text()='Popular Stations']/following-sibling::li[@class='styles_FswAutoCompItem__RE1dP']/descendant::p[contains(text(),'Delhi')]");
	By destinationBengaluruClick = By.xpath(
			"//p[text()='Popular Stations']/following-sibling::li[@class='styles_FswAutoCompItem__RE1dP']/descendant::p[contains(text(),'Bangalore,')]");
	By month_Year = By.xpath("//div[contains(@class,'styles_calMnth__mnthNmWrp__Q_3Cb')]");
	By nextMonthButton = By.xpath("//button[contains(@class,'styles_calCmp__arwBtn--right__B3_Ho')]");
	By searchTrainButton = By.xpath("//span[text()='SEARCH TRAINS']");
	By AcCoach = By.xpath(
			"//p[text()='Sbc Rajdhani']/ancestor::div[contains(@class,'styles.trnCrd__ODT')]/descendant::li//p[text()='7300']/preceding-sibling::p[text()='1A']");
	By irctIdInput = By.xpath("//input[@placeholder='Enter IRCTC User ID']");
	By checkIDButton = By.xpath("//button[text()='Check ID']");
	By addTravellerButton = By.xpath("//button[@class='addTrvlrComp__addBtn']");
	By enterNameInputBox = By.xpath("//input[@placeholder='Enter Name']");
	By enterAgeInputBox = By.xpath("//input[@placeholder='Enter Age']");
	By saveButton = By.xpath("//button[text()='Save']");
	By enterPhoneTextBox = By.xpath("//input[@placeholder='Enter Mobile Number']");
	By enterEmaiTextBox = By.xpath("//input[@placeholder='Enter Email Address']");
	By freeCancel = By.xpath(
			"//input[@type='checkbox']/ancestor::span[@class='goCnfTcCmp__chkBtn']/following-sibling::p[text()='Not interested']");
	By bookButton = By.xpath("//button[text()='Book now']");

	public void bookTrainRadio() {
		driver.findElement(bookTrain).click();
	}

	public void enterDetails() {
		driver.findElement(clickSource).click();
		driver.findElement(surceDelhiClick).click();
		driver.findElement(clickDestination).click();
		driver.findElement(destinationBengaluruClick).click();
	}

	public void searchTButton() {
		driver.findElement(searchTrainButton).click();
	}

	public void addDate() {
		currentDate();
	}

	public void selectTrainCoach() {
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(AcCoach));
		driver.findElement(AcCoach).click();
		WebElement wt1 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(irctIdInput));
		driver.findElement(irctIdInput).sendKeys("Mdlmukesh");
		driver.findElement(checkIDButton).click();
		driver.findElement(addTravellerButton).click();
		WebElement wt2 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(enterNameInputBox));
		driver.findElement(enterNameInputBox).sendKeys("abc");
		WebElement wt3 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(enterAgeInputBox));
		baseClass.delayScript();
		driver.findElement(enterAgeInputBox).sendKeys("24");
		driver.findElement(saveButton).click();
		driver.findElement(enterPhoneTextBox).sendKeys("9876543210");
		driver.findElement(enterEmaiTextBox).sendKeys("abc@xyz.com");
		driver.findElement(freeCancel).click();
		driver.findElement(bookButton).click();
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

		String monthYear = driver.findElement(month_Year).getText();
		String month = monthYear.split(" ")[0].trim();
		String year = monthYear.split(" ")[1].trim();
		while (!(curMonth.equals(month) && curYear.equals(year))) {
			driver.findElement(nextMonthButton).click();
			monthYear = driver.findElement(month_Year).getText();
			month = monthYear.split(" ")[0].trim();
			year = monthYear.split(" ")[1].trim();
		}
		driver.findElement(By.xpath("//p[text()='" + month + " " + year
				+ "']/ancestor::div[@class='styles_calMnth__calCntWrp__d_zvI']/descendant::p[text()='" + curDay + "']"))
				.click();
	}

}
