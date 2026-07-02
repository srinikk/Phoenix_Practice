package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CountAPITest {
	@Test
	public void verifyCountAPIReponse() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.time(lessThan(2000L))
		.body("data",notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", everyItem(not(blankOrNullString())))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"))
		.body("data.key",containsInAnyOrder("created_today","pending_for_delivery","pending_fst_assignment"));
	}
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}

}
