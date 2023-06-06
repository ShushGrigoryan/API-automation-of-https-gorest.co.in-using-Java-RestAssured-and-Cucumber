package StepDefinitions;

import org.junit.jupiter.api.Assertions;
import Functions.FunctionsWithSQL;
import io.cucumber.java.en.*;
import io.restassured.response.Response;


public class ApiStepsSQL extends FunctionsWithSQL {

 Response response;

@When("^I create a new user with \"(.*)\" access token and \"(.*)\" request body$")
public void createNewUser(String accessTokenType, String requestBodyType) {
    response = sendPostRequestToCreateUser(accessTokenType, requestBodyType);
    }

   

@Then("^I assert that the response status code is equal to (\\d+)$")
public void assertResponseStatusCode(int expectedStatusCode) {
    int actualStatusCode = response.getStatusCode();
    System.out.println(actualStatusCode);
    Assertions.assertEquals(expectedStatusCode, actualStatusCode);
}


@Then("^I assert that the response contains \"(.*)\"$")
public void assertResponseMessage(String expectedResponseMessage) {
    String responseBody = response.getBody().asString();
    System.out.println(responseBody);
    Assertions.assertTrue(responseBody.contains(expectedResponseMessage));
}

@When("^I update the user with \"(.*)\" access token and \"(.*)\" request body$")
public void updateUser(String accessTokenType, String requestBodyType) {
    response = super.sendPatchRequestToUpdateTheUser(accessTokenType, requestBodyType);
}

@When("^I delete the user with \"(.*)\" access token$")
public void deleteUser(String accessTokenType) {
    response = super.sendDeleteRequestToDeleteTheUser(accessTokenType);
}

@When("^I update the user with \"(.*)\" access token and \"(.*)\" request body, without user ID$")
public void updateWithoutID(String accessTokenType, String requestBodyType) {
    response = super.sendPatchRequestToUpdateTheUserWithoutID(accessTokenType, requestBodyType);
}

@When("^I create a new user with \"(.*)\" access token and empty body$")
public void createUserWithEmptyBody(String accessTokenType) {
    response = super.sendPostRequestToCreateUserWithEmptyBody(accessTokenType);
}



}