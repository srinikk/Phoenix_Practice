package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	@Test
	public void userDetailsAPITest() throws IOException {
		
		Header authHeader= new Header("Authorization",getToken(SUP));
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
	}

}
