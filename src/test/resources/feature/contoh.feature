@TAG
Feature: Contoh
  Background: user already in login page
    Given user navigates to the login page

Scenario: login success
When  user enters username and password
Then  user is navigated to the home page

Scenario: login fail username
  When user enter invalid username
  Then error message is displayed
  Then user see error message invalid username

Scenario: login fail password
  When user enter invalid password
  Then error message is displayed
  Then user see error message invalid password