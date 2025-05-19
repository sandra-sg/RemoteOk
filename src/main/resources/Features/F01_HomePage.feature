Feature: Filter jobs by role on RemoteOK

  Scenario: User searches for Developer jobs using the search bar
    Given the user is on the RemoteOK homepage
    When the user start typing "Dev" in the search bar
    And the user selects "Developer" from the dropdown
    Then only jobs related to "Developer" should be displayed