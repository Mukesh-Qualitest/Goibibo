/**
 * 
 */
package stepDefinitons;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import drivers.baseClass;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import pageObject.cabPageObjects;
import pageObject.flightPageObjects;
import pageObject.homePageObjects;
import pageObject.hotelPageObjects;
import pageObject.orangeHRMPageObjects;
import pageObject.trainPageObjects;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author mukesh.mandal
 *
 */
public class backgroundDemoStepDef extends baseClass {

	WebDriver driver = setDriver();

	orangeHRMPageObjects hrm = new orangeHRMPageObjects(driver);
	homePageObjects home = new homePageObjects(driver);
	flightPageObjects flight = new flightPageObjects(driver);
	hotelPageObjects hotel = new hotelPageObjects(driver);
	trainPageObjects train = new trainPageObjects(driver);
	cabPageObjects cab = new cabPageObjects(driver);
	String nv;

	@Given("User shoud be in OrangeHrm Login page")
	public void user_shoud_be_in_orange_hrm_login_page() {
		launchOrangeHrm();
	}

	@When("User Enters Username {string}")
	public void user_enters_username(String string) {
		hrm.enterUserName();
	}

	@When("User Enters Password {string}")
	public void user_enters_password(String string) {
		hrm.enterPassword();
	}

	@When("User veryfies its home page of orangeHRM website")
	public void user_veryfies_its_home_page_of_orange_hrm_website() {
		hrm.homePageVerifcation();
	}

	@Then("User should Logout and close browser")
	public void user_should_logout_and_close_browser() {
		hrm.logout();
		launchWebsite();
	}

	@Given("User should be in flight booking page of Goibibo {string}")
	public void user_should_be_in_flight_booking_page_of_goibibo(String nav) {
		home.allNavButtons(nav);
		nv = nav;
		home.closeOfferButton();
	}

	@When("User selects trip type {string}")
	public void user_selects_trip_type(String trip) {
		flight.getTripsButton(trip);
	}

	@When("User gives departure palce {string} , selects {string} and {string} , selects {string}")
	public void user_gives_departure_palce_selects_and_selects(String from, String city1, String to, String city2) {
		if (nv.equals("Flights"))
			flight.enterPlaces(from, city1, to, city2);
		else if (nv.equals("Trains"))
			train.enterDetails();
		else if (nv.equals("Cabs"))
			cab.cabEnterDetails();

	}

	@When("User will select current date to book the flight")
	public void user_will_select_current_date_to_book_the_flight() {
		flight.currentDate();
	}

	@When("User will the fare type {string} and add passanger counts")
	public void user_will_the_fare_type_and_add_passanger_counts(String fare) {
		flight.enterFareType(fare);
	}

	@When("User clicks on search flight")
	public void user_clicks_on_search_flight() {
		flight.searchButton();
	}

	@When("User should be in flight result page")
	public void user_should_be_in_flight_result_page() {
		flight.flightResultAssert();
		closeBrowser();
	}

	@When("User will select future date {int} {int} to book the flight")
	public void user_will_select_future_date_to_book_the_flight(Integer date1, Integer date2) {
		flight.roundTrip(date1);
		flight.futureDate(date2);
	}

	@Given("User should be in hotel booking page of Goibibo {string}")
	public void user_should_be_in_hotel_booking_page_of_goibibo(String nav) {
		home.allNavButtons(nav);
	}

	@When("User selects country as {string} and fill details for for hotel search")
	public void user_selects_country_as_and_fill_details_for_for_hotel_search(String country) {
		hotel.enterHotelDetails(country);
	}

	@When("User selects hotels")
	public void user_selects_hotels() {
		hotel.hotelSelection();
	}

	@When("User fill all the details of guest {string} {string} {string} {string} and click on payment button")
	public void user_fill_all_the_details_of_guest_and_click_on_payment_button(String first, String last, String email,
			String phone) {
		hotel.enterCustomerDetails(first, last, email, phone);
	}

	@Then("User should see hotel booking summary")
	public void user_should_see_hotel_booking_summary() {
		hotel.hotelAssertValue();
		closeBrowser();
	}

	@Given("User should be in Train booking page {string}")
	public void user_should_be_in_train_booking_page(String nav) {
		home.allNavButtons(nav);
		nv = nav;
	}

	@When("User selects {string}")
	public void user_selects(String radio) {
		train.bookTrainRadio();
	}

	@When("User will select current date to book the train")
	public void user_will_select_current_date_to_book_the_train() {
		train.currentDate();
	}

	@When("User clicks on search button")
	public void user_clicks_on_search_button() {
		train.searchTButton();
	}

	@When("User should be able to train result and selects train")
	public void user_should_be_able_to_train_result_and_selects_train() {
		System.out.println("Good to go");
	}

	@When("User will fill all the details of passenger and clicks on book button")
	public void user_will_fill_all_the_details_of_passenger_and_clicks_on_book_button() {
		train.selectTrainCoach();
	}

	@Then("User should be able to train booking summary")
	public void user_should_be_able_to_train_booking_summary() {
		System.out.println("Good to go");
		closeBrowser();
	}

	@Given("User should be in cab booking page of Goibibo {string}")
	public void user_should_be_in_cab_booking_page_of_goibibo(String nav) {
		home.allNavButtons(nav);
		nv = nav;
		cab.onewayCheck();
	}

	@When("User selects pickup date as current date and time as {string}")
	public void user_selects_pickup_date_as_current_date_and_time_as(String string) {
		cab.dateAndTime();
	}

	@When("User selects Return date as future Date <returnDate> and time as {string}")
	public void user_selects_return_date_as_future_date_return_date_and_time_as(String string) {
		System.out.println("this is for current date bookig.!!!!");
	}

	@When("User will select car")
	public void user_will_select_car() {
		cab.selectCar();
	}

	@When("User fill all detail of passenger for car book and clicks on payment button")
	public void user_fill_all_detail_of_passenger_for_car_book_and_clicks_on_payment_button() {
		cab.enterPassDetails();
	}

	@Then("User will see the car booking details")
	public void user_will_see_the_car_booking_details() {
		System.out.println("Good to Go!!!");
		closeBrowser();
	}

	@AfterStep
	public void takeScreenshot(Scenario scenario) throws IOException {
		if(scenario.isFailed()) {
			byte[] scrennshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("ScreenShot", new ByteArrayInputStream(scrennshot));
	}
	}

}
