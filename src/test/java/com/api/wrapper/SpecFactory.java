package com.api.wrapper;
import org.apache.http.impl.execchain.RequestAbortedException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecFactory {
	
	public static RequestSpecification requestspec;
	public static ResponseSpecification responsespec;
	private static final String BASE_URL = "https://restful-booker.herokuapp.com";
	
	public static RequestSpecification getRequestSpec()
	{
		return requestspec=new RequestSpecBuilder()
				.setBaseUri(BASE_URL)
				.setContentType(ContentType.JSON)
				.build();
	}
	
	public static ResponseSpecification getResponseSpec()
	{
		return responsespec=new ResponseSpecBuilder()
				     .expectContentType(ContentType.JSON)
				     .build();
	}

}
