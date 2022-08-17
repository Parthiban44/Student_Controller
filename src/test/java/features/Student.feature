Feature: Student Feature

  Scenario: Verify that student data is created
    Given the body
    When creating the student data
    Then student data is created

  Scenario: Verify that student data is updated
    Given to create the student data
    When to update the email field in student data
    Then the Student data is updated

  Scenario: Verify that student data is updated
    Given to create the student data with updated name
    When to update the name field in student data
    Then the Student name is updated

  Scenario: Verify that student data is updated
    Given to create the student data with updated age
    When to update the age field in student data
    Then the Student age is updated

  Scenario: Verify that student details are displayed
    Given the body
    When creating a user
    Then user details displayed

    Scenario: verify the error is thrown when the name is not given by student
      Given  student data without name
      When create a student data without name
      Then Name is required error will be thrown

  Scenario: verify the error is thrown when the age is not given by student
    Given  student data without age
    When create a student data without age
    Then Age is required error will be thrown

  Scenario: verify the error is thrown when the email is not given by student
    Given  student data without email
    When create a student data without email
    Then Email is required error will be thrown

  Scenario: verify the error is thrown when the id is not given by student
    Given  student data without id
    When create a student data without id
    Then Internal Server Error will be thrown


  Scenario: Verify that student data is deleted
    #Given to Create the student data in postman
    When Create the student data
    Then student data is deleted

