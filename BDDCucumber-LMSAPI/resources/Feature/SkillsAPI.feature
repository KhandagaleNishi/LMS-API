@LMSAPI
Feature: User will be able to perform get, post, put and delete operations on SkillAPI

  @SkillAPI
  Scenario: Verify that all Skill data can be retrieved with the url
    Given User sets request endpoint for Skill API with valid authorization
    When User sends GET request for Skill API
    Then User should get the status code "200" for Skill API

  @SkillAPI
  Scenario Outline: Verify that single User Skill can be retrieved with the url
    Given User sets request endpoint for Skill API with valid authorization
    When User sends GET request with Skillid from "<SheetName>" and <Rownumber>
    Then User should get the status code "200" and skillid for Skill API
    Then Get all Skill data displayed with updated data

    Examples: 
      | SheetName  | Rownumber |
      | getSkillId |         0 |
      | getSkillId |         1 |
      | getSkillId |         2 |
      | getSkillId |         3 |
      | getSkillId |         4 |
      | getSkillId |         5 |
      | getSkillId |         6 |
      | getSkillId |         7 |
      | getSkillId |         8 |

  @SkillAPI
  Scenario Outline: Verify that User can post user Skill
    Given User sets request endpoint for Skill API with valid authorization
    When User sends POST request with Skill data from "<SheetName>" and <Rownumber>
    Then User should get status code "201" and respose body for successful post request for Skill API
    Then Get all Skill data displayed with updated data

    Examples: 
      | SheetName   | Rownumber |
      | postSkillId |         0 |
      | postSkillId |         1 |
      | postSkillId |         2 |
      | postSkillId |         3 |
      | postSkillId |         4 |
      | postSkillId |         5 |
      | postSkillId |         6 |
      | postSkillId |         7 |
      | postSkillId |         8 |

  @SkillAPI
  Scenario Outline: Verify that User can update user Skill
    Given User sets request endpoint for Skill API with valid authorization
    When User sends PUT request with skill data from "<SheetName>" and <Rownumber>
    Then User should get status code "201" and respose body for successful put request for Skill API
    Then Get all Skill data displayed with updated data

    Examples: 
      | SheetName  | Rownumber |
      | putSkillId |         0 |
      | putSkillId |         1 |
      | putSkillId |         2 |
      | putSkillId |         3 |
      | putSkillId |         4 |
      | putSkillId |         5 |
      | putSkillId |         6 |
      | putSkillId |         7 |
      | putSkillId |         8 |

  @SkillAPI
  Scenario Outline: Verify that User can delete Skill record
    Given User sets request endpoint for Skill API with valid authorization
    When User sends DELETE request with skillid from "<SheetName>" and <Rownumber>
    Then User should get status code "200" and respose body for successful DELETE request for Skill API
    Then Get all Skill data displayed with updated data

    Examples: 
      | SheetName  | Rownumber |
      | delSkillId |         0 |
      | delSkillId |         1 |
      | delSkillId |         2 |
      | delSkillId |         3 |
      | delSkillId |         4 |
      | delSkillId |         5 |
      | delSkillId |         6 |
      | delSkillId |         7 |
