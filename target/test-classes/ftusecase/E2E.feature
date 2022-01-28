Feature: E2E Test cases to validate all the promotions details and
        UnAuthorized API Key Response

  Description : The purpose of this test is to validate the promotion information

  @validatePromotionDetails
  Scenario:Authorized user is able to retrieve the promotion details
    Given I launch the promotion endpoint
    When I authenticate the user using valid apikey
    Then I retrieve the promotion details
    And I validate the response status
    And I validate the promotion details


  @InvalidAPIRequest
  Scenario:UnAuthorized user is not able to retrieve the promotion details
    Given I launch the promotion endpoint
    When I pass the invalid apikey
    And I validate the response status as 403
    And I validate the Error message







