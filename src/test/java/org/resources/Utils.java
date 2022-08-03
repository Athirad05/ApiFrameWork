package org.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqspec;
	
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		if(reqspec==null) {
		PrintStream stream = new PrintStream(new File("log.txt"));
		reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		return reqspec;
	}
		 return reqspec;
	}
	

	
	public String getjsonpath(Response response,String key) {
		
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
		
		

	}
	
	
}
