@UpdateContactInfo
Feature: Update Contact Information

@Negative
  Scenario Outline: Submitting form with missing Fields
    Given The user is logged in and navigates to the Update Contact Info page
    When The user inputs all fields except "<Field>"
    And the user clicks the Update Profile button
    Then the user should be displayed with error message "<ErrorMessage>"

    Examples:
      | Field                 | ErrorMessage                                |
      | First Name            | First name is required.                     |
      | Last Name             | Last name is required.                      |
      | Address               | Address is required.                        |
      | City                  | City is required.                           |
      | State                 | State is required.                          |
      | Zip Code              | Zip Code is required.                       |

@positive
  Scenario: Updating contact info with valid data
    Given The user is logged in and navigates to the Update Contact Info page
    When The user enters valid values in all fields
      | firstName | lastName | address       | city     | state | zipCode | phoneNumber |
      | John      | Doe      | 123 Main St.  | New York | NY    | 10001   | 1234567890  |
    And The user clicks the Update Profile button
    Then The system should redirect to the profile updated page
    And The success message should be "Your updated address and phone number have been added to the system."