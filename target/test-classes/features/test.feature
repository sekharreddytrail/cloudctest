Feature: First  test Rest with cucumber


  Scenario: VERIFY QUOTE FOR A USER

    Given i try to autheticate user
    When i try to create quote for gbp and usd
      | GBP | USD | sell | 1000 |
    Then I verify buying amount is correct
      | 1000 |


  Scenario: VERIFY NEGATIVE SCENARIO QUOTE FOR A USER
    Given i try to autheticate user
    When i try to create quote for gbp and usd
      | gbp | usd | sell | 1000 |
    Then I verify it doesnot show value