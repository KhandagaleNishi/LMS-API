package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.testng.Assert;

import com.google.gson.JsonObject;

import Utilities.ApiConfig;
import Utilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserAPI {

	String uri;
	JsonObject jsonObject;
	String checkid;
	String basePath = "/Users";
	public Response response;
	public RequestSpecification request;

	String jsonAsString;

	@Given("User sets request endpoint with valid authorization")
	public void user_sets_request_endpoint_with_valid_authorization() {
		this.uri = ApiConfig.BASE_URL + this.basePath;

		this.request = RestAssured.given().auth().preemptive().basic(ApiConfig.USERNAME, ApiConfig.PASSWORD)
				.header("Content-Type", "application/json");
	}

	@When("User sends GET request")
	public void user_sends_get_request() {
		response = this.request.get(this.uri);
		response.then().log().all();

	}

	@Then("User should get the status code {string}")
	public void user_should_get_the_status_code(String statusCode) {

		int StatusCodeCheck = response.getStatusCode();

		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);

			jsonAsString = response.asString();
		//	response.then().assertThat()
		//			.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserAPI_Get.json")));
		} else if (StatusCodeCheck == 404) {
			System.out.println("Not Found Status Code : " + StatusCodeCheck);
		}
		else {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}

	}

	@Then("User should get the status code {string} and userid")
	public void user_should_get_the_status_code_and_userid(String statusCode) {

		int StatusCodeCheck = response.getStatusCode();
		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);

			response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserAPI_Get1.json")));
		} else {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}

	}

	@When("User sends GET request with Userid from {string} and {int}")
	public void user_sends_get_request_with_userid_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {

		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String user_id = testData.get(rowNumber).get("user_id");

		response = this.request.when().get(this.uri + "/" + user_id).then().log().all().extract().response();

	}

	@When("User sends POST request with user data from {string} and {int}")
	public void user_sends_post_request_with_user_data_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {

		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String name = testData.get(rowNumber).get("name");
		String phone_number = testData.get(rowNumber).get("phone_number");
		String heading = testData.get(rowNumber).get("heading");
		String location = testData.get(rowNumber).get("location");
		String time_zone = testData.get(rowNumber).get("time_zone");
		String linkedin_url = testData.get(rowNumber).get("linkedin_url");
		String education_ug = testData.get(rowNumber).get("education_ug");
		String education_pg = testData.get(rowNumber).get("education_pg");
		String visa_status = testData.get(rowNumber).get("visa_status");
		String comments = testData.get(rowNumber).get("comments");

		JSONObject requestParam = new JSONObject();

		requestParam.put("name", name);
		requestParam.put("phone_number", phone_number);
		requestParam.put("location", location);
		requestParam.put("time_zone", time_zone);
		requestParam.put("linkedin_url", linkedin_url);
		requestParam.put("education_ug", education_ug);
		requestParam.put("education_pg", education_pg);
		requestParam.put("visa_status", visa_status);
		requestParam.put("comments", comments);

		response = this.request.body(requestParam.toJSONString()).when().post(this.uri).then().log().all().extract()
				.response();

	}

	@Then("User should get status code {string} and respose body for successful post request")
	public void user_should_get_status_code_for_successful_post_request(String statusCode) {

		int StatusCodeCheck = response.getStatusCode();
		if (StatusCodeCheck == 201) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);

			jsonAsString = response.asString();
			Assert.assertEquals(jsonAsString.contains("Successfully Created !!"), true);

			response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserAPI_Post.json")));

		} else if (StatusCodeCheck == 400) {
			System.out.println("Response received Bad Request: " + StatusCodeCheck);

			response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserAPI_404.json")));
		} else {
			System.out.println("Response received :" + StatusCodeCheck);
		}

	}

	@When("User sends PUT request with user data from {string} and {int}")
	public void user_sends_put_request_with_user_data_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();
		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String user_id = testData.get(rowNumber).get("user_id");
		String name = testData.get(rowNumber).get("name");
		String phone_number = testData.get(rowNumber).get("phone_number");
		String heading = testData.get(rowNumber).get("name");
		String location = testData.get(rowNumber).get("location");
		String time_zone = testData.get(rowNumber).get("time_zone");
		String linkedin_url = testData.get(rowNumber).get("linkedin_url");
		String education_ug = testData.get(rowNumber).get("education_ug");
		String education_pg = testData.get(rowNumber).get("education_pg");
		String visa_status = testData.get(rowNumber).get("visa_status");
		String comments = testData.get(rowNumber).get("comments");

		JSONObject requestParam = new JSONObject();

		requestParam.put("name", name);
		requestParam.put("phone_number", phone_number);
		requestParam.put("location", location);
		requestParam.put("time_zone", time_zone);
		requestParam.put("linkedin_url", linkedin_url);
		requestParam.put("education_ug", education_ug);
		requestParam.put("education_pg", education_pg);
		requestParam.put("visa_status", visa_status);
		requestParam.put("comments", comments);

		System.out.println(this.uri + "/" + user_id);
		System.out.println(requestParam.toJSONString());
		response = this.request.body(requestParam.toJSONString()).when().put(this.uri + "/" + user_id).then().log()
				.all().extract().response();

	}

	@Then("User should get status code {string} and respose body for successful put request")
	public void user_should_get_status_code_for_successful_put_request(String statusCode) {
		int StatusCodeCheck = response.getStatusCode();
		if (StatusCodeCheck == 201) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);

			jsonAsString = response.asString();
			Assert.assertEquals(jsonAsString.contains("Successfully Updated !!"), true);

		//	response.then().assertThat()
		//			.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserAPI_Put.json")));

		} else if (StatusCodeCheck == 404) {
			System.out.println("Response received Not found: " + StatusCodeCheck);
		}else  {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}

	}

	@When("User sends DELETE request with Userid from {string} and {int}")
	public void user_sends_delete_request_with_userid_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {

		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String user_id = testData.get(rowNumber).get("user_id");
//System.out.println(this.uri + "/" + user_id);

		response = this.request
				// .body(requestParam.toJSONString())
				.when().delete(this.uri + "/" + user_id);
		response.then().log().all().extract().response();

	}

	@Then("User should get status code {string} and respose body for successful DELETE request")
	public void user_should_get_status_code_for_successful_delete_request(String statusCode) {

		int StatusCodeCheck = response.getStatusCode();
		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);

			jsonAsString = response.asString();
			Assert.assertEquals(jsonAsString.contains("The record has been deleted !!"), true);

			response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserAPI_Delete.json")));

		} else if (StatusCodeCheck == 404) {
			System.out.println("Response received Not found: " + StatusCodeCheck);
		}else  {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}

	}

	@Then("Get all Users data displayed with updated data")
	public void Get_all_Users_data_displayed_with_updated_data() {
		RestAssured.given().when().get(this.uri).then().header("Content-Type", "application/json").log().all().extract()
				.response();
	}

}
