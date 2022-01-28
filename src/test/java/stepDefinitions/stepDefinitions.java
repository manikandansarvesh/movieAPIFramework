package stepDefinitions;

import io.cucumber.java.en.*;

public class stepDefinitions {
    Steps steps =new Steps();
    @Given("I launch the promotion endpoint")
    public void i_launch_the_promotion_endpoint() throws Exception{
        steps.launchEndpointURl();
    }
    @When("I authenticate the user using valid apikey")
    public void i_authenticate_the_user_using_valid_apikey() {
        steps.validateAPIKey();
    }
    @Then("I retrieve the promotion details")
    public void i_retrieve_the_promotion_details() {
        steps.validatePromtion();
    }
    @Then("I validate the response status")
    public void i_validate_the_response_status() {
        steps.validateResponseStatus();

    }
    @Then("I validate the promotion details")
    public void i_validate_the_promotion_details() throws Exception  {

        steps.validatePromtionDetails();
    }

    @When("I pass the invalid apikey")
    public void i_pass_the_invalid_apikey() {
        steps.Invalid_APIKey();

    }
    @When("I validate the response status as {int}")
    public void i_validate_the_response_status_as(Integer int1) {
        steps.InvalidResponseStatus(int1);
    }
    @When("I validate the Error message")
    public void i_validate_the_error_message() {
        steps.validateErrorResponseDetails();
    }

}
