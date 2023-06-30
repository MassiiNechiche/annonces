Feature: a user requests the list of annoucements
  Scenario: a user sends a GET request to the endpoint /annonces
    When A user asks for the announcements list
    Then He should have a success response
#    And The following announcements list should be in the response content
#      | title      |
#      | annonce1   |