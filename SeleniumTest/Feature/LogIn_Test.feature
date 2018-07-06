#Author: Wes Feltz
#Scenario: Successful and Unsuccessful login/logout
#Description: Username and password entry and login/logout

@tag
Feature: Login Action
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Successful Login with Valid Credentials
    Given User is on Home Page
    When User Navigate to LogIn Page
    And User enters "<username>" and "<password>"
    Then Message displayed Login Successfully
    When User LogOut from the Application
    Then Message displayed LogOut Successfully

Examples:
| username	| password		|
| westest		| WesTest123!	|

@tag2
Scenario: Unsuccessful Login with Invalid Credentials
	Given User is on Home Page
	When User Navigate to LogIn Page
	And User enters BadCredentials to LogIn
| baduser | badpass |
	Then Message displayed Login Unsuccessful
	Then Forgot Password Link Appears

@tag3
Scenario: Unsuccessful Login with Invalid Credentials II
	Given User is on Home Page
	When User Navigate to LogIn Page
	And User enters BadCredentials to LogIn II
| username	| password |
| junkuser	| nogood!	|
	Then Message displayed Login Unsuccessful
	Then Forgot Password Link Appears

@tag4
Scenario: Unsuccessful Login with Invalid Credentials III
	Given User is on Home Page
	When User Navigate to LogIn Page
	And User enters BadCredentials to LogIn III
| username	| password |
| junkuser	| nogood!	|
| baduser	| nogood!	|
	Then Message displayed Login Unsuccessful
	Then Forgot Password Link Appears
	