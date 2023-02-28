Feature: Booking flight and hotel.

  Scenario Outline: User can book flight and hotel simultaniously
    Given User should be in home page of website
    When User will select what they want to book anc click on "<nav>"
    And User selects which kind of trip he wants "<trip>"
    And User fill details for "<from>" and selects "<city1>" , "<to>" and selects "<city2>"
    And User selects dates <day1> <day2> which he want to travel
    And User select which kind of fare he want "<fare>"
    And clicks on search flight
    Then User should be able to see flight details
    And User applies some filter "<departure>" "<stops>"
    Then User should be ble to see the modified results
    When User wants to book hotel
    And User clicks on home page of the website
    When User will select what they want to book anc click on "<nav1>"
    When User fill all the detals and select the city "<counrty>"
    And User selects the Hotel
    And User fill the customer details "<first>" "<last>" "<email>" "<phone>" and click on payment button
    Then User should move to confirm booking page

    Examples: 
      | nav     | nav1   | trip       | from | city1     | to  | city2  | day1 | day2 | fare    | departure | stops  | counrty | first | last | email       | phone      |
      | Flights | Hotels | One-way    | Ben  | Bengaluru | Mum | Mumbai |    0 |    0 | Regular | Before 6  | Direct | India   | xyz   | abc  | xyz@abc.com | 0987654321 |
      | Flights | Hotels | Round-trip | Ben  | Bengaluru | Mum | Mumbai |    4 |   10 | Senior  | After 6   | Direct | India   | xyz   | abc  | xyz@abc.com | 0987654321 |
