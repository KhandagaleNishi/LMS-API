@LMSAPI
Feature: User will be able to perform get, post, put and delete operations on SkillAPI

  @UserSkillMapAPI
  Scenario: Verify that all Skill data can be retrieved with the url
    Given User sets request endpoint for UserSkillMap API with valid authorization
    When User sends GET request for Skillmap API
    Then User should get the status code "200" for UserSkillMap API

  @UserSkillMapAPI
  Scenario Outline: Verify that single SkillMap can be retrieved with the url
    Given User sets request endpoint for UserSkillMap API with valid authorization
    When User sends GET request with Skillmapid from "<SheetName>" and <Rownumber>
    Then User should get the status code "200" and skillid for UserSkillMap API
    Then Get all UserSkillMap data displayed with updated data

    Examples: 
      | SheetName     | Rownumber |
      | getSkillMapId |         0 |
      | getSkillMapId |         1 |
      | getSkillMapId |         2 |
      | getSkillMapId |         3 |
      | getSkillMapId |         4 |
      | getSkillMapId |         5 |
      | getSkillMapId |         6 |
      | getSkillMapId |         7 |
      | getSkillMapId |         8 |

  @UserSkillMapAPI
  Scenario Outline: Verify that User can post SkillMap
    Given User sets request endpoint for UserSkillMap API with valid authorization
    When User sends POST request with Skillmap data from "<SheetName>" and <Rownumber>
    Then User should get status code "201" and respose body for successful post request for UserSkillMap API
    Then Get all UserSkillMap data displayed with updated data

    Examples: 
      | SheetName      | Rownumber |
      | postSkillMapId |         0 |
      | postSkillMapId |         1 |
      | postSkillMapId |         2 |
      | postSkillMapId |         3 |
      | postSkillMapId |         4 |
      | postSkillMapId |         5 |
      | postSkillMapId |         6 |
      | postSkillMapId |         7 |
      | postSkillMapId |         8 |

  @UserSkillMapAPI
  Scenario Outline: Verify that User can update SkillMap
    Given User sets request endpoint for UserSkillMap API with valid authorization
    When User sends PUT request with skillmap data from "<SheetName>" and <Rownumber>
    Then User should get status code "201" and respose body for successful put request for UserSkillMap API
    Then Get all UserSkillMap data displayed with updated data

    Examples: 
      | SheetName     | Rownumber |
      | putSkillMapId |         0 |
      | putSkillMapId |         1 |
      | putSkillMapId |         2 |
      | putSkillMapId |         3 |
      | putSkillMapId |         4 |
      | putSkillMapId |         5 |
      | putSkillMapId |         6 |
      | putSkillMapId |         7 |
      | putSkillMapId |         8 |

  @UserSkillMapAPI
  Scenario Outline: Verify that User can delete SkillMap
    Given User sets request endpoint for UserSkillMap API with valid authorization
    When User sends DELETE request with skillmapid from "<SheetName>" and <Rownumber>
    Then User should get status code "200" and respose body for successful DELETE request for UserSkillMap API
    Then Get all UserSkillMap data displayed with updated data

    Examples: 
      | SheetName     | Rownumber |
      | delSkillMapId |         0 |
      | delSkillMapId |         1 |
      | delSkillMapId |         2 |
      | delSkillMapId |         3 |
      | delSkillMapId |         4 |
      | delSkillMapId |         5 |
      | delSkillMapId |         6 |
      | delSkillMapId |         7 |
   
