#Author: dayana@generalsoftwareinc.com
#Keywords Summary: Google Search
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
@tag
Feature: Make a Google Search
  I want to search in google a random artist name
  So that I can select the first search result

  @tag1
  Scenario: Successfully search in google
    Given the user is on the google search view
    When typing the artist name in the google input search field
    And press Enter keyboard
    Then the system will show all the possible search results verifying that the title of the first element match with the text introduced
    And will click on the first element on the search result verifying that the link matches with the url open
