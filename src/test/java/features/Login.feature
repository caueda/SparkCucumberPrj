Feature: Application Login

  Background: A clean test
    Given : a map
    | country  | state |
    | Brazil   | MT    |
    | Brazil   | PR    |
    | Brazil   | SC    |

  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User login into application with "Jin" and "Password"
    Then Home page is populated
    And Cards displayed are "true"

  @RegTest
  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User sign up with following details
    | jenny | abcd | john@abcd.com | Australia | 45646 |

    Then Home page is populated
    And Cards displayed are "false"

  @SmokeTest
  Scenario Outline: Home page default login
    Given User is on NetBanking landing page
    When User login into app with <Username> and <password>
    Then Home page is populated
    And Cards displayed are "true"

    Examples:
    |Username|password|
    |user1   |pass1   |
    |user2   |pass2   |
    |user3   |pass3   |