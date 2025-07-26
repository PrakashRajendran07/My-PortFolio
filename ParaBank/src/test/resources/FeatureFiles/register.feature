Feature: User Registration Validation

  @Positive
  Scenario Outline: Submit registration form with valid details
    Given the user navigates to the registration page
    When the user provides valid details "<FirstName>", "<LastName>", "<Address>", "<City>", "<State>", "<ZipCode>", "<SSN>", "<Username>", "<Password>", "<ConfirmPassword>"
    And clicks the submit button
    Then the user sees confirmation message "<SuccessMessage>"

    Examples:
      | FirstName | LastName | Address     | City      | State    | ZipCode | SSN      | Username   | Password  | ConfirmPassword | SuccessMessage                        |
      | John      | snow1      | 123 Street  | New York  | NY       | 10001    | 123-45-6789 | snow123 | pass@123 | pass@123        |Your account was created successfully. You are now logged in.|
      
  @Negative
  Scenario Outline: Submit registration form with missing mandatory fields
    Given the user navigates to the registration page
    When the user fills all fields except "<Field>"
    And clicks the submit button
    Then the user sees error message "<ErrorMessage>"

    Examples:
      | Field                 | ErrorMessage                               |
      | Password              | Password is required.                      |
      | SSN                   | Social Security Number is required.        |
      | Zip Code              | Zip Code is required.                      |
      | Username              | Username is required.                      |
      | First Name            | First name is required.                    |
      | Last Name             | Last Name is required.                     |
      | Address               | Address is required.                       |
      | City                  | City is required.                          |
      | State                 | State is required.                         |
      | Password confirmation | Password confirmation is required.         |
      
    @ExistingUser
  Scenario: Submit registration form with an existing username
    Given the user navigates to the registration page
    When the user provides valid details "John", "snow1", "123 Main St", "New York", "NY", "10001", "123-45-6789", "snow123", "pass@123", "pass@123"
    And clicks the submit button
    Then the user sees duplicate user error message "This username already exists."

  

  