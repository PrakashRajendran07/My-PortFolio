@BillPay
Feature: Bill Pay Functionality

  @Positive
  Scenario Outline: Successful bill payment with valid data
    Given the user is logged in and navigates to the Bill Pay page
    When the user enters "<PayeeName>", "<Address>", "<City>", "<State>", "<ZipCode>", "<PhoneNumber>", "<Account>", "<VerifyAccount>", "<Amount>"
    And clicks the Send Payment button
    Then the user should see a success message with payee "<PayeeName>" and amount "<Amount>"

    Examples:
      | PayeeName | Address       | City      | State | ZipCode | PhoneNumber | Account | VerifyAccount | Amount |
      | Prakash   | 123 Main St   | Chennai   | TN    | 600001  | 9876543210  | 25332   | 25332         | 100    |

  @Negative1
  Scenario Outline: Submit bill pay form with missing mandatory fields
    Given the user is logged in and navigates to the Bill Pay page
    When the user fills all the available fields except "<Field>"
    And clicks the Send Payment button
    Then the user views the error message "<ErrorMessage>"

    Examples:
      | Field           | ErrorMessage                |
      | Payee Name      | Payee name is required.     |
      | Address         | Address is required.        |
      | City            | City is required.           |
      | State           | State is required.          |
      | Zip Code        | Zip code is required.       |
      | Phone Number    | Phone Number is required.   |
      | Account         | Account number is required. |
      | Verify Account  | Account number is required. |
      | Amount          | The amount cannot be empty. |


  @Negative2
  Scenario: Account number mismatch validation
    Given the user is logged in and navigates to the Bill Pay page
    When the user provides account number "12345" and verify account number "54321"
    And clicks the Send Payment button
    Then the user views the error message "<ErrorMessage>"
     Examples:
      | Field           | ErrorMessage                |
      | Verify Account  | The account numbers do not match.    |


  @Validation
  Scenario Outline: Validate success message contains correct payee and amount
    Given the user is logged in and navigates to the Bill Pay page
    When the user enters "<PayeeName>", "<Address>", "<City>", "<State>", "<ZipCode>", "<PhoneNumber>", "<Account>", "<VerifyAccount>", "<Amount>"
    And clicks the Send Payment button
    Then the user should see a success message with payee "<PayeeName>" and amount "<Amount>"

    Examples:
      | PayeeName | Address     | City     | State | ZipCode | PhoneNumber | Account | VerifyAccount | Amount |
      | Mani      | 10 Hills Rd | Pune     | MH    | 411001  | 9876543210  | 88888   | 88888         | 300    |
