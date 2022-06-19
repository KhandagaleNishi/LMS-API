#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@LMSAPI
Feature: User will be able to perform get, post, put and delete operations on UserAPI

  @UserAPI
  Scenario: Verify that all Users data can be retrieved with the url
    Given User sets request endpoint with valid authorization
    When User sends GET request
    Then User should get the status code "200"

  @UserAPI
  Scenario Outline: Verify that single User data can be retrieved with the url
    Given User sets request endpoint with valid authorization
    When User sends GET request with Userid from "<SheetName>" and <Rownumber>
    Then User should get the status code "200" and userid

    Examples: 
      | SheetName | Rownumber |
      | getData   |         0 |
      | getData   |         1 |
      | getData   |         2 |
      | getData   |         3 |
      | getData   |         4 |
      | getData   |         5 |
      | getData   |         6 |
      | getData   |         7 |
      | getData   |         8 |

  @UserAPI
  Scenario Outline: Verify that User can post user data
    Given User sets request endpoint with valid authorization
    When User sends POST request with user data from "<SheetName>" and <Rownumber>
    Then User should get status code "201" and respose body for successful post request
    Then Get all Users data displayed with updated data

    Examples: 
      | SheetName | Rownumber |
      | postData  |         0 |
      | postData  |         1 |
      | postData  |         2 |
      | postData  |         3 |
      | postData  |         4 |
      | postData  |         5 |
      | postData  |         6 |
      | postData  |         7 |
      | postData  |         8 |

  @UserAPI
  Scenario Outline: Verify that User can update user data
    Given User sets request endpoint with valid authorization
    When User sends PUT request with user data from "<SheetName>" and <Rownumber>
    Then User should get status code "201" and respose body for successful put request
    Then Get all Users data displayed with updated data

    Examples: 
      | SheetName | Rownumber |
      | putData   |         0 |
      | putData   |         1 |
      | putData   |         2 |
      | putData   |         3 |
      | putData   |         4 |
      | putData   |         5 |
      | putData   |         6 |
      | putData   |         7 |
      | putData   |         8 |

  @UserAPI
  Scenario Outline: Verify that User can delete user record
    Given User sets request endpoint with valid authorization
    When User sends DELETE request with Userid from "<SheetName>" and <Rownumber>
    Then User should get status code "200" and respose body for successful DELETE request
    Then Get all Users data displayed with updated data

    Examples: 
      | SheetName  | Rownumber |
      | deleteData |         0 |
      | deleteData |         1 |
      | deleteData |         2 |
      | deleteData |         3 |
      | deleteData |         4 |
      | deleteData |         5 |
      | deleteData |         6 |
      | deleteData |         7 |
