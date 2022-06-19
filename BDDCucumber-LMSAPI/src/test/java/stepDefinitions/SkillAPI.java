package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import org.testng.Assert;

import Utilities.ApiConfig;
import Utilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SkillAPI {
	String uri;
	JsonObject jsonObject;
	String basePath = "/Skills";
	public Response response;
	public RequestSpecification request;
	String jsonAsString;
	String checkid;

	@Given("User sets request endpoint for Skill API with valid authorization")
	public void user_sets_request_endpoint_for_skill_api_with_valid_authorization() {
		this.uri = ApiConfig.BASE_URL + this.basePath;

		this.request = RestAssured.given().auth().preemptive().basic(ApiConfig.USERNAME, ApiConfig.PASSWORD)
				.header("Content-Type", "application/json");
	}

	@When("User sends GET request for Skill API")
	public void user_sends_get_request_for_skill_api() {
		response = this.request.get(this.uri);
		response.then().log().all();
	}

	@Then("User should get the status code {string} for Skill API")
	public void user_should_get_the_status_code_for_skill_api(String statusCode) {
		int StatusCodeCheck = response.getStatusCode();

		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);
			jsonAsString = response.asString();

			response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/SkillAPI_Get.json")));
		} else if (StatusCodeCheck == 404) {
			System.out.println("Not Found Status Code : " + StatusCodeCheck);
		}
		else {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}
	}

	@When("User sends GET request with Skillid from {string} and {int}")
	public void user_sends_get_request_with_skillid_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {

		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String skill_id = testData.get(rowNumber).get("skill_id");

		checkid = skill_id;
		int id = Integer.parseInt(skill_id);
		response = this.request

				.when().get(this.uri + "/" + id).then().log().all().extract().response();

	}

	@Then("User should get the status code {string} and skillid for Skill API")
	public void user_should_get_the_status_code_and_skillid_for_skill_api(String statusCode) {

		int StatusCodeCheck = response.getStatusCode();
		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);
			jsonAsString = response.asString();
			// Assert.assertEquals(jsonAsString.contains(checkid), true);

			response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/SkillAPI_Get1.json")));
		} else if (StatusCodeCheck == 404) {
			System.out.println("Not Found Status Code : " + StatusCodeCheck);
		}else {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}
	}

	@When("User sends POST request with Skill data from {string} and {int}")
	public void user_sends_post_request_with_skill_data_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String skill_name = testData.get(rowNumber).get("skill_name");

		JSONObject requestParam = new JSONObject();

		requestParam.put("skill_name", skill_name);

		response = this.request.body(requestParam.toJSONString()).when().post(this.uri).then().log().all().extract()
				.response();

	}

	@Then("User should get status code {string} and respose body for successful post request for Skill API")
	public void user_should_get_status_code_for_successful_post_request_for_skill_api(String statusCode) {
		
		int StatusCodeCheck = response.getStatusCode();
		if (StatusCodeCheck == 201) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);
		jsonAsString = response.asString();
		Assert.assertEquals(jsonAsString.contains("Successfully Created !!"), true);

		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/SkillAPI_Post.json")));
		}else if (StatusCodeCheck == 400) {
			System.out.println("Response received Bad Request: " + StatusCodeCheck);
		}else  {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}
		
		}

	@When("User sends PUT request with skill data from {string} and {int}")
	public void user_sends_put_request_with_skill_data_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();
		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String skill_id1 = testData.get(rowNumber).get("skill_id");
		String skill_name = testData.get(rowNumber).get("skill_name");

		JSONObject requestParam = new JSONObject();

		requestParam.put("skill_name", skill_name);

		System.out.println(this.uri + "/" + skill_id1);
		System.out.println(requestParam.toJSONString());
		response = this.request.body(requestParam.toJSONString()).when().put(this.uri + "/" + skill_id1).then().log()
				.all().extract().response();
	}

	@Then("User should get status code {string} and respose body for successful put request for Skill API")
	public void user_should_get_status_code_for_successful_put_request_for_skill_api(String statusCode) {
		int StatusCodeCheck = response.getStatusCode();
		if (StatusCodeCheck == 201) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);
		jsonAsString = response.asString();
		Assert.assertEquals(jsonAsString.contains("Successfully Updated !!"), true);

		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/SkillAPI_Put.json")));
		}else if (StatusCodeCheck == 404) {
			System.out.println("Response received Not found: " + StatusCodeCheck);
		}else  {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}
		
		}

	@When("User sends DELETE request with skillid from {string} and {int}")
	public void user_sends_delete_request_with_skillid_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String skill_id = testData.get(rowNumber).get("skill_id");

		int id = Integer.parseInt(skill_id);
		response = this.request
				// .body(requestParam.toJSONString())
				.when().delete(this.uri + "/" + skill_id);
		response.then().log().all().extract().response();

	}

	@Then("User should get status code {string} and respose body for successful DELETE request for Skill API")
	public void user_should_get_status_code_for_successful_delete_request_for_skill_api(String statusCode) {

		int StatusCodeCheck = response.getStatusCode();
		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);

		jsonAsString = response.asString();
		Assert.assertEquals(jsonAsString.contains("The record has been deleted !!"), true);

		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/SkillAPI_Delete.json")));
	
		}else if (StatusCodeCheck == 404) {
			System.out.println("Response received Not found: " + StatusCodeCheck);
		}else  {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}
		
	}

	@Then("Get all Skill data displayed with updated data")
	public void Get_all_Users_data_displayed_with_updated_data() {
		RestAssured.given().when().get(this.uri).then().header("Content-Type", "application/json").log().all().extract()
				.response();
	}

}
