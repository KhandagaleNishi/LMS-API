package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;

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

public class UserSkillsGetMapAPI {
	String uri;
	String uri1;

	JsonObject jsonObject;

	String basePath = "/UserSkillsMap";
	String basePath2 = "/UsersSkillsMap";
	public Response response;
	public RequestSpecification request;

	@Given("User sets GET request for UsersSkillsMap endpoint with valid authorization2")
	public void user_sets_get_request_for_users_skills_map_endpoint_with_valid_authorization() {
		this.uri1 = ApiConfig.BASE_URL + this.basePath2;

		this.request = RestAssured.given().auth().preemptive().basic(ApiConfig.USERNAME, ApiConfig.PASSWORD)
				.header("Content-Type", "application/json");
	}
	
	@Given("User sets GET request for UserSkillsMap endpoint with valid authorization")
	public void user_sets_get_request_for_user_skills_map_endpoint_with_valid_authorization() {
		
		this.uri = ApiConfig.BASE_URL + this.basePath;

		this.request = RestAssured.given().auth().preemptive().basic(ApiConfig.USERNAME, ApiConfig.PASSWORD)
				.header("Content-Type", "application/json");
	}

	@When("User sends GET request for UserSkillsMap")
	public void user_sends_get_request_for_user_skills_map() {
		response = this.request.get(this.uri);
		response.then().log().all();
	}

	@When("User sends GET request with skillId from {string} and {int}")
	public void user_sends_get_request_with_skill_id_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String id = testData.get(rowNumber).get("id");
		
		int ID = Integer.parseInt(id);
		
		System.out.println(this.uri + "/" + ID);

		response = this.request
				.when().get(this.uri1 + "/" + ID).then().log().all()
				.extract().response();
	}

	@When("User sends GET request with userId from {string} and {int}")
	public void user_sends_get_request_with_user_id_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> testData = excelReader.getData(ApiConfig.EXCEL, sheetName);

		String userId = testData.get(rowNumber).get("userId");

		JSONObject requestParam = new JSONObject();

		requestParam.put("userId", userId);

		System.out.println(this.uri + "/" + userId);

		response = this.request
				.when().get(this.uri + "/" + userId).then().log().all()
				.extract().response();

	}

	
	@Then("User should get the status code {string} and checks response with json schema validation for UserSkillsGetMap")
	public void user_should_get_the_status_code_and_checks_response_with_json_schema_validation_for_user_skills_get_map(String statusCode) {
		int StatusCodeCheck = response.getStatusCode();

		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);
		response.then().assertThat()
		.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserSkillsGetMap_Get.json")));
		}else if (StatusCodeCheck == 404) {
			System.out.println("Not Found Status Code : " + StatusCodeCheck);
		}else {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}
	}

	@Then("User should get the status code {string} and checks response with json schema validation for userId in UserSkillsGetMap")
	public void user_should_get_the_status_code_and_checks_response_with_json_schema_validation_for_user_id_in_user_skills_get_map(String statusCode) {
		int StatusCodeCheck = response.getStatusCode();

		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);
		response.then().assertThat()
		.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserSkillsGetMap_GetuserId.json")));
		}else if (StatusCodeCheck == 404) {
			System.out.println("Not Found Status Code : " + StatusCodeCheck);
		}else {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}
	}

	@Then("User should get the status code {string} and checks response with json schema validation for skillId in UserSkillsGetMap")
	public void user_should_get_the_status_code_and_checks_response_with_json_schema_validation_for_skill_id_in_user_skills_get_map(String statusCode) {
		int StatusCodeCheck = response.getStatusCode();

		if (StatusCodeCheck == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			System.out.println("Status Code received successfully: " + StatusCodeCheck);
		response.then().assertThat()
		.body(JsonSchemaValidator.matchesJsonSchema(new File(ApiConfig.JSON + "/UserSkillsGetMap_GetskillId.json")));
		}else if (StatusCodeCheck == 404) {
			System.out.println("Not Found Status Code : " + StatusCodeCheck);
		}else {
			System.out.println("Response received successfully: " + StatusCodeCheck);
		}
	}



}
