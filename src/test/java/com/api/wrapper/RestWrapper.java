package com.api.wrapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class RestWrapper {

	private RequestSpecification spec;

	public RestWrapper()
	{
		spec=SpecFactory.getRequestSpec();
		
	}
	
	public Response get(String endpoint)
	{
	    return given().spec(spec).log().all().when().get(endpoint); 	
	}
	
	public Response post(String endpoint, String requestBody) {
        return given().spec(spec).log().all().body(requestBody).when().post(endpoint);
    }
	
	 public Response put(String endpoint, String token,String requestBody) {
	        return given().spec(spec).cookie("token", token).log().all().body(requestBody).when().put(endpoint);
	 }
	
	public Response delete(String endpoint,String token) {
        return given().spec(spec).cookie("token", token).log().all().when().delete(endpoint);
    }
}
