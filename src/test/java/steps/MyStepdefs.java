package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.ErrorObject;
import models.ErrorObjectId;
import models.Student;
import org.json.simple.JSONObject;
import org.testng.Assert;
import utils.EndPoints;

import static utils.JsonInputParser.data;

import static io.restassured.RestAssured.given;

public class MyStepdefs{
    ObjectMapper objectMapper = new ObjectMapper();
    Student student;
    Response response;
    JsonPath jsonPath;


    @Given("the body")
    public void theBody() {

        JSONObject testData = (JSONObject) data.get("createStudent");

        student = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
    }

    @When("creating the student data")
    public void creatingTheStudentData() {
        response = given()
                .body(student)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(200).extract().response();
    }
    @Then("student data is created")
    public void studentDataIsCreated() throws JsonProcessingException {
        Student responseStud = objectMapper.readValue(response.asString(), Student.class);
        Assert.assertEquals(student.getEmail(), responseStud.getEmail());
        Assert.assertEquals(student.getName(), responseStud.getName());
        Assert.assertEquals(student.getId(), responseStud.getId());
        Assert.assertEquals(student.getAge(), responseStud.getAge());
    }


    Response putResponse;
    Student student_2;
    int studId;
    @Given("to create the student data")
    public void toCreateTheStudentData()throws JsonProcessingException {
        JSONObject testData = (JSONObject) data.get("createStudent");
         student_2 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
        putResponse = given()
                .body(student_2)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(200).extract().response();
        JsonPath jsonPath=new JsonPath(putResponse.asString());
        studId= jsonPath.getInt("id");
    }

    @When("to update the email field in student data")
    public void toUpdateTheEmailFieldInStudentData() {
        JSONObject testData = (JSONObject) data.get("updateStudent");
        student_2 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
        putResponse = given()
                .body(student_2)
                .when().put(EndPoints.STUDENT_ENDPOINT+"/"+studId)
                .then().statusCode(200).extract().response();
    }

    @Then("the Student data is updated")
    public void theStudentDataIsUpdated() throws JsonProcessingException {
        Student putResponseStud = objectMapper.readValue(putResponse.asString(),Student.class);
        Assert.assertEquals(student_2.getEmail(),putResponseStud.getEmail());
    }


    Response putResponse1;
    Student putStudent_2;
    int studId1;
    @Given("to create the student data with updated name")
    public void toCreateTheStudentDataWithUpdatedName() {
        JSONObject testData = (JSONObject) data.get("createStudent");
        putStudent_2 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
        putResponse1 = given()
                .body(putStudent_2)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(200).extract().response();
        JsonPath jsonPath=new JsonPath(putResponse1.asString());
        studId1= jsonPath.getInt("id");
    }

    @When("to update the name field in student data")
    public void toUpdateTheNameFieldInStudentData() {

        JSONObject testData = (JSONObject) data.get("updateStudentName");
        putStudent_2 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
        putResponse1 = given()
                .body(putStudent_2)
                .when().put(EndPoints.STUDENT_ENDPOINT+"/"+studId1)
                .then().statusCode(200).extract().response();

    }

    @Then("the Student name is updated")
    public void theStudentNameIsUpdated() throws JsonProcessingException {
        Student putResponseStud1 = objectMapper.readValue(putResponse1.asString(),Student.class);
        Assert.assertEquals(putStudent_2.getName(),putResponseStud1.getName());
    }


    Student putStudent_3;
    int studId2;
    Response putResponse2;
    @Given("to create the student data with updated age")
    public void toCreateTheStudentDataWithUpdatedAge() {
        JSONObject testData = (JSONObject) data.get("createStudent");
        putStudent_3 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
        putResponse2 = given()
                .body(putStudent_3)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(200).extract().response();
        JsonPath jsonPath=new JsonPath(putResponse2.asString());
        studId2= jsonPath.getInt("id");
    }


    @When("to update the age field in student data")
    public void toUpdateTheAgeFieldInStudentData() {
        JSONObject testData = (JSONObject) data.get("updateStudentAge");
        putStudent_3 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
        putResponse2 = given()
                .body(putStudent_3)
                .when().put(EndPoints.STUDENT_ENDPOINT+"/"+studId2)
                .then().statusCode(200).extract().response();
    }


    @Then("the Student age is updated")
    public void theStudentAgeIsUpdated() throws JsonProcessingException {
        Student putResponseStud2 = objectMapper.readValue(putResponse2.asString(),Student.class);
        Assert.assertEquals(putStudent_3.getAge(),putResponseStud2.getAge());
    }

    Student student_8;
    Response response_8,responseStudent;
    @When("creating a user")
    public void creatingAUser() throws JsonProcessingException {
        JSONObject testData = (JSONObject) data.get("createStudent");
        student_8 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
        response_8 = given()
                .body(student_8)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(200).extract().response();
    }


    @Then("user details displayed")
    public void userDetailsDisplayed() {
        response_8 = given()
                .body(student_8)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(200).extract().response();
    }

    Student student_3;
    Response response_2;
    @Given("student data without name")
    public void studentDataWithoutName() {
        JSONObject testData = (JSONObject) data.get("createStudentWithoutName");
        student_3 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
    }

    @When("create a student data without name")
    public void createAStudentDataWithoutName() {
        response_2 = given()
                .body(student_3)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(400).extract().response();
    }

    @Then("Name is required error will be thrown")
    public void nameIsRequiredErrorWillBeThrown() throws JsonProcessingException {
        ErrorObject errorObject= objectMapper.readValue(response_2.asString(),ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(),"Name is required");
    }

    //without id
    Student student_4;
    Response response_3;
    @Given("student data without age")
    public void studentDataWithoutAge() {
        JSONObject testData = (JSONObject) data.get("createStudentWithoutAge");
        student_4 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
    }

    @When("create a student data without age")
    public void createAStudentDataWithoutAge() {
        response_3 = given()
                .body(student_4)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(400).extract().response();
    }

    @Then("Age is required error will be thrown")
    public void ageIsRequiredErrorWillBeThrown() throws JsonProcessingException {
        ErrorObjectId errorObjectId= objectMapper.readValue(response_3.asString(),ErrorObjectId.class);
        Assert.assertEquals(errorObjectId.getMessage(),"Age is required");

    }



    Student student_5;
    Response response_4;
    @Given("student data without email")
    public void studentDataWithoutEmail() {
        JSONObject testData = (JSONObject) data.get("createStudentWithoutEmail");
        student_5 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
    }

    @When("create a student data without email")
    public void createAStudentDataWithoutEmail() {
        response_4 = given()
                .body(student_5)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(400).extract().response();
    }

    @Then("Email is required error will be thrown")
    public void emailIsRequiredErrorWillBeThrown() throws JsonProcessingException {
        ErrorObject errorObject= objectMapper.readValue(response_4.asString(),ErrorObject.class);
        Assert.assertEquals(errorObject.getMessage(),"Email is required");
    }


    //without id
    Student student_6;
    Response response_5;
    @Given("student data without id")
    public void studentDataWithoutId() {
        JSONObject testData = (JSONObject) data.get("createStudentWithoutId");
        student_6 = new Student(0, (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
    }

    @When("create a student data without id")
    public void createAStudentDataWithoutId() {
        response_5 = given()
                .body(student_6)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(500).extract().response();
    }

    @Then("Internal Server Error will be thrown")
    public void internalServerErrorWillBeThrown() throws JsonProcessingException {
        ErrorObjectId errorObjectId= objectMapper.readValue(response_5.asString(),ErrorObjectId.class);
        Assert.assertEquals(errorObjectId.getError(),"Internal Server Error");
        Assert.assertEquals(errorObjectId.getStatus(),500);
    }



    Student student_7;
    Response response_6;
    int userID;
    JSONObject testData;
    @Given("to Create the student data in postman")
    public void toCreateTheStudentDataInPostman() {
         testData = (JSONObject) data.get("createStudent_2");
    }
    @When("Create the student data")
    public void createTheStudentData() throws JsonProcessingException {
         testData = (JSONObject) data.get("createStudent_2");
        student_7 = new Student((long) testData.get("id"), (String) testData.get("name"),
                (long) testData.get("age"), (String) testData.get("email"));
        response_6 = given()
                .body(student_7)
                .when().post(EndPoints.STUDENT_ENDPOINT)
                .then().statusCode(200).extract().response();
        jsonPath = new JsonPath(response_6.asString());
          userID=jsonPath.getInt("id");
    }
    @Then("student data is deleted")
    public void studentDataIsDeleted() {
        response_6 = given()
                .when().delete(EndPoints.STUDENT_ENDPOINT+"/" + userID)
                .then()
                .statusCode(200).extract().response();
    }

}
