@Wikipedia
Feature: First Test - Wikipedia
  @wikipediaDynamicData
  Scenario Outline: Locators with Wikipedia
    Given I open browser to webpage "wikipedia.org"
    Then I wait "30" seconds to see the text "The Free Encyclopedia"
    Then I enter "<Entry>" to "search.field"
    Then I wait for "3" seconds
    Then I click on "search.btn"
    Then I wait "30" seconds to see the text "<validationTerm>"

    Examples: {'datafile' : 'src/main/resources/data/WikipediaEntries.xls', 'sheetName': 'Sheet1'}






