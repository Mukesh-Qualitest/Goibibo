/**
 * 
 */
package stepDefinitons;

import org.openqa.selenium.WebDriver;

import drivers.baseClass;
import drivers.factoryDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.flightPageObjects;
import pageObject.homePageObjects;
import pageObject.hotelPageObjects;

/**
 * @author mukesh.mandal
 *
 */
public class flightHotelSteps extends baseClass {

	public WebDriver driver = setDriver();
	private homePageObjects home = new homePageObjects(driver);
	private flightPageObjects flight = new flightPageObjects(driver);
	private hotelPageObjects hotel = new hotelPageObjects(driver);

	@Given("User should be in home page of website")
	public void user_should_be_in_home_page_of_website() {
		launchWebsite();
		home.clickOnHomeButton();
		home.closeOfferButton();
	}

	@When("User will select what they want to book anc click on {string}")
	public void user_will_select_what_they_want_to_book_anc_click_on(String nav) {
		home.allNavButtons(nav);
	}

	@When("User selects which kind of trip he wants {string}")
	public void user_selects_which_kind_of_trip_he_wants(String trip) {
		flight.getTripsButton(trip);
	}

	@When("User fill details for {string} and selects {string} , {string} and selects {string}")
	public void user_fill_details_for_and_selects_and_selects(String from, String city1, String to, String city2) {
		flight.enterPlaces(from, city1, to, city2);
	}

	@When("User selects dates {int} {int} which he want to travel")
	public void user_selects_dates_which_he_want_to_travel(Integer day1, Integer day2) {
		if (day1 > 0 && day2 > 0) {
			flight.roundTrip(day1);
			flight.futureDate(day2);
		} else {
			flight.currentDate();
		}
	}

	@When("User select which kind of fare he want {string}")
	public void user_select_which_kind_of_fare_he_want(String fare) {
		flight.enterFareType(fare);
	}

	@When("clicks on search flight")
	public void clicks_on_search_flight() {
		flight.searchButton();
	}

	@Then("User should be able to see flight details")
	public void user_should_be_able_to_see_flight_details() {
		flight.flightResultAssert();
	}

	@Then("User applies some filter {string} {string}")
	public void user_applies_some_filter(String departure, String stops) {
		flight.getFilterSelected(departure, stops);
	}

	@Then("User should be ble to see the modified results")
	public void user_should_be_ble_to_see_the_modified_results() {
		flight.flightResultAssert();
	}

	@When("User wants to book hotel")
	public void user_wants_to_book_hotel() {
		flight.flightResultAssert();
	}

	@When("User clicks on home page of the website")
	public void user_clicks_on_home_page_of_the_website() {
		home.clickOnHomeButton();
	}

	@When("User fill all the detals and select the city {string}")
	public void user_fill_all_the_detals_and_select_the_city(String country) {
		hotel.enterHotelDetails(country);
	}

	@When("User selects the Hotel")
	public void user_selects_the_hotel() {
		hotel.hotelSelection();
	}

	@When("User fill the customer details {string} {string} {string} {string} and click on payment button")
	public void user_fill_the_customer_details_and_click_on_payment_button(String first, String last,
			String email, String phone) {
		hotel.enterCustomerDetails(first, last, email, phone);
	}

	@Then("User should move to confirm booking page")
	public void user_should_move_to_confirm_booking_page() {
		hotel.hotelAssertValue();
		closeBrowser();
	}
}
