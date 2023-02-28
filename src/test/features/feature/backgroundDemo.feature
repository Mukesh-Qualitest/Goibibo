Feature: working on Background keyword

  Background: User will Open OrangeHRM website Login and Logout and launch the Goibibo website
    Given User shoud be in OrangeHrm Login page
    When User Enters Username "<username>"
    And User Enters Password "<password>"
    And User veryfies its home page of orangeHRM website
    Then User should Logout and close browser

  @Regression @Flight_oneWay
  Scenario: User will open Goibibo and book flight
    Given User should be in flight booking page of Goibibo "Flights"
    When User selects trip type "One-Way"
    And User gives departure palce "Ban" , selects "Bengaluru" and "Mum" , selects "Mumbai"
    And User will select current date to book the flight
    And User will the fare type "Regular" and add passanger counts
    And User clicks on search flight
    And User should be in flight result page

  @Regression @Flight_roundTrip
  Scenario Outline: User will open Goibibo and book flight
    Given User should be in flight booking page of Goibibo "<nav>"
    When User selects trip type "<trip>"
    And User gives departure palce "<departure>" , selects "<city1>" and "<arrival>" , selects "<city2>"
    And User will select future date <date1> <date2> to book the flight
    And User will the fare type "<fare>" and add passanger counts
    And User clicks on search flight
    And User should be in flight result page

    Examples: 
      | nav     | trip       | departure | city1 | arrival | city2  | date1 | date2 | fare | nav     |
      | Flights | Round-trip | del       | Delhi | bho     | Bhopal |     7 |     9 |      | Flights |

  @Regression @Hotel
  Scenario: User will open Goibibo and book the hotel
    Given User should be in hotel booking page of Goibibo "Hotels"
    When User selects country as "India" and fill details for for hotel search
    And User selects hotels
    And User fill all the details of guest "abc" "xyz" "abc@xyz.com" "0987654321" and click on payment button
    Then User should see hotel booking summary

  @Regression @Train
  Scenario: User will open Goibibo ann book train
    Given User should be in Train booking page "Trains"
    When User selects "Book Train tickets"
    And User gives departure palce "Del" , selects "Delhi" and "Ban" , selects "Bangalore"
    And User will select current date to book the train
    And User clicks on search button
    And User should be able to train result and selects train
    And User will fill all the details of passenger and clicks on book button
    Then User should be able to train booking summary

  @Regression @Cab
  Scenario: User will open Goibibo and book a cab
    Given User should be in cab booking page of Goibibo "Cabs"
    When User selects trip type "Outstation One-way"
    And User gives departure palce "Del" , selects "Delhi" and "Ban" , selects "Bangalore"
    And User selects pickup date as current date and time as "04:45 AM"
    And User selects Return date as future Date <returnDate> and time as "04:45 AM"
    And User will select car
    And User fill all detail of passenger for car book and clicks on payment button
    Then User will see the car booking details
