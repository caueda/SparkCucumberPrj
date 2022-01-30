Feature: Application Login

  Scenario: Home page default login
    Given User is on NetBanking landing page
    When User login into application with "Jin" and "Password"
    Then Home page is populated
    And Cards displayed are "true"