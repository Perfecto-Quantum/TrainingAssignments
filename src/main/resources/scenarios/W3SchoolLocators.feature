@W3
Feature: First Assignment - W3Schools
  @W3testLocators
  Scenario: find html lessons witjh locators
    Given I open browser to webpage "w3schools.com"
    Then I wait "30" seconds to see the text "the language"
    Then I click on "learn.html"
    Then I wait "30" seconds to see the text "with html you can create"
