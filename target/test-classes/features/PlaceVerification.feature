
Feature: Validating Place Api's
@AddPlace
Scenario Outline: Verifyif place is been succesfully added using AddPlaceApi
Given Add Place payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceApi" with "POST" Http request
Then the Api call got suuccess with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP" 
And verify place_id created maps to"<name>" using "GetPlaceApi"

Examples:
|name   |language  |address       |
|Athirad|malayalam |pandakkal mahe|
#|sarang |english   |urothummal    |

@DeletePlace
Scenario: Deleting the existing place
Given DeletePlace payload
When User calls "DeletePlaceApi" with "POST" Http request
Then the Api call got suuccess with status code 200
And "status" in response body is "OK"
