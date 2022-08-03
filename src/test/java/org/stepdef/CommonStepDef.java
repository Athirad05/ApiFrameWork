package org.stepdef;

import org.resources.ApiResource;
import org.resources.TestData;
import org.resources.Utils;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
public class CommonStepDef extends Utils {
	 RequestSpecification req; 
	 Response res ;
	 static String Place_id;
	TestData data = new TestData();
	@Given("Add Place payload with {string} {string} {string}")
	public void add_Place_payload_with(String pname, String plang, String paddress) throws FileNotFoundException {
		 
	 req = given().spec(requestSpecification()).body(data.payload(pname, plang, paddress));
	}

	@When("User calls {string} with {string} Http request")
	public void user_calls_with_Http_request(String resourceUrl, String method) {
		
		ApiResource apiResource = ApiResource.valueOf(resourceUrl);
		String resource = apiResource.getResource();
		System.out.println(resource);
		
		if(method.equalsIgnoreCase("POST")) 
		res = req.when().post(resource);
		else if(method.equalsIgnoreCase("GET"))
		res=req.when().get(resource);
		else if(method.equalsIgnoreCase("PUT"))
		res=req.when().put(resource);
		else if(method.equalsIgnoreCase("DELETE"))
		res=req.when().post(resource);
		
	}

	@Then("the Api call got suuccess with status code {int}")
	public void the_Api_call_got_suuccess_with_status_code(Integer int1) {
		
		try {
		int statusCode = res.getStatusCode();
		System.out.println(statusCode);
		assertEquals(statusCode, 200);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String Expectedvalue) {
		String Actualvalue = getjsonpath(res, key);
		assertEquals(Actualvalue, Expectedvalue);
		
	    
	}

	@Then("verify place_id created maps to{string} using {string}")
	public void verify_place_id_created_maps_to_using(String Expectedname, String resourceUrl) throws FileNotFoundException {
		
		String Place_id = getjsonpath(res,"place_id");
	    System.out.println(Place_id);
	    
	     req = given().spec(requestSpecification()).queryParam("place_id", Place_id);
	     user_calls_with_Http_request(resourceUrl, "GET");
	     System.out.println(res.asString());
	     String Actualname = getjsonpath(res, "name");
	     System.out.println(Actualname);
	     assertEquals(Actualname, Expectedname);
	     
	    
	}

	@Given("DeletePlace payload")
	public void deleteplace_payload() throws FileNotFoundException {
		req = given().spec(requestSpecification()).body(data.deletPayload(Place_id));
		
		
	    
}


	
	

}
