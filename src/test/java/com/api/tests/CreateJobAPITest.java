package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() {
		
		Customer customer= new Customer("Sriniavs", "K", "9822333445", "", "test@gmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("110","Galazy apt","roady street","Bangalore","near light bulb","560023","India","Karnataka");
		CustomerProduct customerProduct=new CustomerProduct("2025-08-31T18:30:00.000Z", "15304850803612", "15304850803612", "15304850803612", "2025-08-31T18:30:00.000Z", 1, 1);
		Problems problems=new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK());
		
		
	}

}
