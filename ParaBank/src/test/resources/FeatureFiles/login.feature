Feature: Login page validations

  @Positive
  Scenario Outline: User accesses the bank portal with valid credentials
    Given the user navigates to the customer login page
    When the user provides valid credentials "<Username>" and "<Password>"
    And clicks the Login button
    Then the user should be redirected to the Accounts Overview page

    Examples:
      | Username | Password |
      | John     | 12345    |

  @Negative
  Scenario Outline: User tries to access the bank portal with invalid credentials
    Given the user navigates to the customer login page
    When the user provides invalid details "<Username>" and "<Password>"
    And clicks the Login button
    Then the user should be redirected to the error page displaying the message "The username and password could not be verified."

    Examples:
      | Username | Password |
      | ABCD     | 12345    |

  @Negative
  Scenario Outline: User tries to access the bank portal with an empty username
    Given the user navigates to the customer login page
    When the user provides credentials "<Username>" and "<Password>"
    And clicks the Login button
    Then the user should be redirected to the error page displaying the message "Please enter a username and password."

    Examples:
      | Username | Password |
      |          | 12345    |

  @Negative
  Scenario Outline: User tries to access the bank portal with an empty password
    Given the user navigates to the customer login page
    When the user provides credentials "<Username>" and "<Password>"
    And clicks the Login button
    Then the user should be redirected to the error page displaying the message "Please enter a username and password."

    Examples:
      | Username | Password |
      | John     |          |
