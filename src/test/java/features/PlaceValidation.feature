Feature: Validaying place API's

  Scenario Outline: Verify if place being successfully added using AddPlaceAPI
    Given Add Place Payload with as "<Name>" and as "<Language>" and as "<Address>"
    When User calls "AddPlaceAPI" with "post" http request
    Then User got response success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And user added query parameter "place_id"
    And User calls "GetPlaceAPI" with "get" http request
    And User validate "<Name>" with response parameter "name"
    And User add new story line for practice first push



    Examples:
    | Name         | Language  | Address           |
    | Mark John    | French    | 103 Geere Road    |
    | Alex Mercer  | German    | 102 Uptown Park   |
    | Daniel Craig | English   | 009 Prown walley  |


  Scenario: Delete Place request
      Given user added delete place payload
      When User calls "DeletePlaceAPI" with "delete" http request
      Then User got response success with status code 200
      And "status" in response body is "OK"