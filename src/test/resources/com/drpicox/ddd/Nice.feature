Feature: Nice

  This is a service that is useful to know that
  everything works nicely.

  Scenario: say hello
    Then the nice service is nice
    And say hello
    And you have a pleasent rest

  Scenario: say hello peter
    Then hello peter

  Scenario: I say hello
    Given I am David
    Then I say hello
    And My favourite number is 10
