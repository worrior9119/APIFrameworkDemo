package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;


import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefination {
    Utility utl = new Utility();
    TestDataBuild tb = new TestDataBuild();
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    JsonPath jp;
    static String placeId;

    public StepDefination() throws FileNotFoundException {
    }

    @Given("Add Place Payload with as {string} and as {string} and as {string}")
    public void add_place_payload(String name,String lang,String add) throws IOException {
        res = given().spec(utl.requestSpecificationUtility()).body(tb.addPlacePayload(name,lang,add));

    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource,String method) {
        APIResources resourseApi = APIResources.valueOf(resource);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourseApi.getResource()).then().extract().response();
        }
        else if (method.equalsIgnoreCase("GET")){
            response = res.when().get(resourseApi.getResource()).then().extract().response();
        }
        else if (method.equalsIgnoreCase("PUT")){
            response = res.when().put(resourseApi.getResource()).then().extract().response();
        }
        else if (method.equalsIgnoreCase("DELETE")){
            response = res.when().delete(resourseApi.getResource()).then().extract().response();
        }

    }

    @Then("User got response success with status code {int}")
    public void user_got_response_success_with_status_code(Integer status) {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        Assert.assertEquals(utl.getJsonPathValue(response,key),value);
    }

    @And("user added query parameter {string}")
    public void userGetThePlaceId(String key) throws IOException {
        String param = utl.getJsonPathValue(response,key);
        placeId = utl.getJsonPathValue(response,"place_id");
        res = given().spec(utl.requestSpecificationUtility()).queryParam("place_id",param);
    }

    @And("User validate {string} with response parameter {string}")
    public void userValidateWithResponse(String aValue,String key) {
        String param = utl.getJsonPathValue(response,key);
        Assert.assertEquals(aValue,param);
    }

    @And("user added delete place payload")
    public void userAddedDeletePlacePayload() throws IOException {
        res = given().spec(utl.requestSpecificationUtility()).body(tb.deletePlacePayload(placeId));
    }
}
