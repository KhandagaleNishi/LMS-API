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
Feature: User will be able to get UserSkillMapGet API details

  @UserSkillsMap
  Scenario: Verify that all UserSkillsMap data can be retrieved with the url
    Given User sets GET request for UserSkillsMap endpoint with valid authorization
    When User sends GET request for UserSkillsMap
    Then User should get the status code "200" and checks response with json schema validation for UserSkillsGetMap

  @UserSkillsMap
  Scenario Outline: Verify that single UsersSkillsMap data with skillId can be retrieved with the url
    Given User sets GET request for UsersSkillsMap endpoint with valid authorization2
    When User sends GET request with skillId from "<SheetName>" and <Rownumber>
    Then User should get the status code "200" and checks response with json schema validation for skillId in UserSkillsGetMap

    Examples: 
      | SheetName | Rownumber |
      | skillId   |         0 |
      | skillId   |         1 |
      | skillId   |         2 |
      | skillId   |         3 |
      | skillId   |         4 |
      | skillId   |         5 |
      | skillId   |         6 |
      | skillId   |         7 |

  @UserSkillsMap
  Scenario Outline: Verify that single UserSkillsMap data with userId can be retrieved with the url
    Given User sets GET request for UserSkillsMap endpoint with valid authorization
    When User sends GET request with userId from "<SheetName>" and <Rownumber>
    Then User should get the status code "200" and checks response with json schema validation for userId in UserSkillsGetMap

    Examples: 
      | SheetName | Rownumber |
      | userId    |         0 |
      | userId    |         1 |
      | userId    |         2 |
      | userId    |         3 |
      | userId    |         4 |
      | userId    |         5 |
      | userId    |         6 |
      | userId    |         7 |
