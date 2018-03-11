@Contacts
Feature: Add Contact
  @addContactAssignment
  Scenario: Using Custom Steps
    Given I launch Contacts application
    And Delete Contact "Perfecto" if exists
    Then I Add Contact with name "Perfecto" and number  "12345"
