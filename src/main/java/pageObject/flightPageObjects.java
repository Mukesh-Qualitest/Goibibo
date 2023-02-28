/**
 * 
 */
package pageObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author mukesh.mandal
 *
 */
public class flightPageObjects {

	private WebDriver driver;

	public flightPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	By trip_Options = By.xpath("//ul[contains(@class,'hRWUSL')]/li/span");
	By from_Input_Click = By.xpath("//div[contains(@class,'fswFld ')]/span[text()='From']");
	By enter_City_Name = By.xpath("//div[contains(@class,'kjxDYN')]/input");
	By city_Names = By.xpath("//ul[@id='autoSuggest-list']/descendant::span[@class='autoCompleteTitle ']");
	By select_Fare_Type = By.xpath("//ul[contains(@class,'hnjmtl')]/descendant::span");
	By children_Number = By.xpath(
			"//p[text()='Children']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'eHZarz')]");
	By increase_Children_Pass_Count = By.xpath(
			"//p[text()='Children']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'sc-dvQaRk')][2]");
	By decrease_Children_Pass_Count = By.xpath(
			"//p[text()='Children']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'sc-dvQaRk')][1]");
	By adult_Number = By.xpath(
			"//p[text()='Adults']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'eHZarz')]");
	By increase_Adults_Pass_Count = By.xpath(
			"//p[text()='Adults']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'sc-dvQaRk')][2]");
	By decrease_Adults_Pass_Count = By.xpath(
			"//p[text()='Adults']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'sc-dvQaRk')][1]");
	By infants_Number = By.xpath(
			"//p[text()='Infants']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'eHZarz')]");
	By increase_Infants_Pass_Count = By.xpath(
			"//p[text()='Infants']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'sc-dvQaRk')][2]");
	By decrease_Infants_Pass_Count = By.xpath(
			"//p[text()='Infants']/ancestor::div[contains(@class,'hWaYfP')]/descendant::div/span[contains(@class,'sc-dvQaRk')][1]");
	By pass_Section = By.xpath("//div[contains(@class,'ieHAuN')]");
	By traveller_Done_Button = By.xpath("//a[text()='Done']");
	By traveller_Cancel_Button = By.xpath("//a[text()='Cancel']");
	By search_Flight_Button = By.xpath("//span[text()='SEARCH FLIGHTS']");
	By depart_Filter = By
			.xpath("//div[text()='Departure']/ancestor::div[@class='flexCol']/descendant::span[@class='black']");
	By stops_Filter = By.xpath(
			"//div[text()='Stops']/ancestor::div[@class='flexCol']/descendant::span[contains(@class,'TxtCenter')]");
	By assert_Value = By.xpath("//label[text()='PASSENGER & CLASS']");

	public void getTripsButton(String tripType) {
		List<WebElement> tOptipns = driver.findElements(trip_Options);
		for (WebElement to : tOptipns) {
			if (to.getText().equals(tripType)) {
				to.click();
				break;
			}
		}
	}

	public void getFareType(String type) {
		List<WebElement> fareType = driver.findElements(select_Fare_Type);
		for (WebElement ft : fareType) {
			if (ft.getText().contains(type)) {
				ft.click();
				break;
			}
		}
	}

	public void enterFareType(String fare) {
		getFareType(fare);
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(pass_Section));
		if (fare.equals("Regular")) {
			driver.findElement(increase_Adults_Pass_Count).click();
			driver.findElement(increase_Children_Pass_Count).click();
			driver.findElement(increase_Infants_Pass_Count).click();
		} else if (fare.equals("Senior")) {
			driver.findElement(increase_Adults_Pass_Count).click();;
		}
		driver.findElement(traveller_Done_Button).click();
	}

	public void getFilterSelected(String filter1, String filter2) {
		List<WebElement> ft1 = driver.findElements(depart_Filter);
		for (WebElement f1 : ft1) {
			if (f1.getText().contains(filter1)) {
				f1.click();
				break;
			}
		}
		List<WebElement> ft2 = driver.findElements(stops_Filter);
		for (WebElement f2 : ft1) {
			if (f2.getText().contains(filter2)) {
				f2.click();
				break;
			}
		}
	}

	public void selectCityName(String cityName) {
		List<WebElement> listOfCites = driver.findElements(city_Names);
		for (WebElement loc : listOfCites) {
			if (loc.getText().contains(cityName)) {
				loc.click();
				break;
			}
		}
	}

	public void enterPlaces(String from, String city1, String to, String city2) {
		driver.findElement(from_Input_Click).click();
		driver.findElement(enter_City_Name).sendKeys(from);
		WebElement wt = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(city_Names));
		selectCityName(city1);
		driver.findElement(enter_City_Name).sendKeys(to);
		WebElement wt1 = (WebElement) (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.presenceOfElementLocated(city_Names));
		selectCityName(city2);
	}

	public void searchButton() {
		driver.findElement(search_Flight_Button).click();
	}
	
	public void flightResultAssert() {
		Assert.assertTrue(driver.findElement(assert_Value).isEnabled());
	}

	public void currentDate() {
		String currentDateTime = getCurrentFormattedTime("d-MMMMMMMMM-yyyy");
		selectDate(currentDateTime);
	}

	public void futureDate(int days) {
		String currentDateTime = getFutureFormatedTime(days, "d-MMMMMMMMM-yyyy");
		selectDate(currentDateTime);
	}

	public void roundTrip(int days) {
		String currentDateTime = getFutureFormatedTime(days, "d-MMMMMMMMM-yyyy");
		roundSelectDate(currentDateTime);
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

		String monthYear = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		// System.out.println(monthYear);
		String month = monthYear.split(" ")[0].trim();
		String year = monthYear.split(" ")[1].trim();
		while (!(curMonth.equals(month) && curYear.equals(year))) {
			driver.findElement(By.xpath("//span[contains(@class,'DayPicker-NavButton--next')]")).click();
			monthYear = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
			month = monthYear.split(" ")[0].trim();
			year = monthYear.split(" ")[1].trim();
		}
		driver.findElement(By.xpath("//div[contains(text(),'" + curMonth + " " + curYear
				+ "')]/ancestor::div[@class='DayPicker-Month']//div[contains(@class,'DayPicker-Day')]//p[text()='"
				+ curDay + "']")).click();
		driver.findElement(By.xpath("//span[text()='Done']")).click();
	}

	public void roundSelectDate(String currentDateTime) {
		String curDay = currentDateTime.split("-")[0].trim();
		String curMonth = currentDateTime.split("-")[1].trim();
		String curYear = currentDateTime.split("-")[2].trim();

		String monthYear = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		// System.out.println(monthYear);
		String month = monthYear.split(" ")[0].trim();
		String year = monthYear.split(" ")[1].trim();
		while (!(curMonth.equals(month) && curYear.equals(year))) {
			driver.findElement(By.xpath("//span[contains(@class,'DayPicker-NavButton--next')]")).click();
			monthYear = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
			month = monthYear.split(" ")[0].trim();
			year = monthYear.split(" ")[1].trim();
		}
		driver.findElement(By.xpath("//div[contains(text(),'" + curMonth + " " + curYear
				+ "')]/ancestor::div[@class='DayPicker-Month']//div[contains(@class,'DayPicker-Day')]//p[text()='"
				+ curDay + "']")).click();
	}

}
