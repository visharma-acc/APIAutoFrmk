package com.api.tests;

import org.eclipse.sisu.Priority;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojos.BookingPojo;
import com.api.utilities.SerializationDeserialization;
import com.api.wrapper.RestWrapper;
import com.api.wrapper.SpecFactory;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class bookertest {
	
	private ResponseSpecification resspec;
	private RestWrapper restwrapper;
	private static String BookingId;
	private static String BookingJsonPayload;
	private BookingPojo booking;
	
	@BeforeClass
	public void init()
	{
		 resspec=SpecFactory.getResponseSpec();	
		 restwrapper=new RestWrapper();
		
	}
	
	@Test (priority = 1 ,description = "create booking test scenario")
	public void createbookingtest()
	{
		BookingJsonPayload=SerializationDeserialization.bookingJsonpayload();
		Response response=restwrapper.post("/booking", BookingJsonPayload).then().spec(resspec).log().all().extract().response() ;
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		BookingId=response.jsonPath().getString("bookingid");
		System.out.println(BookingId);
		booking=SerializationDeserialization.getStudentPojo(BookingJsonPayload);
		System.out.println(booking.toString());
	}
	
	@Test (priority = 2 ,description = "update booking test scenario")
	public void updatebookingtest()
	{
		booking.setLastname("Shrups");
		BookingJsonPayload=SerializationDeserialization.bookingJsonpayload(booking);
		String payload=SerializationDeserialization.Authtokenpayload();
		Response AuthtokenResponse=restwrapper.post("/auth", payload).then().spec(resspec).log().all().extract().response();
		String token=AuthtokenResponse.jsonPath().getString("token");
		Response response=restwrapper.put("/booking/"+BookingId,token, BookingJsonPayload).then().log().all().extract().response() ;
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		BookingPojo obj=SerializationDeserialization.getStudentPojo(BookingJsonPayload);
		System.out.println(obj.toString());
	}
	
	@Test(priority = 3,description = "get booking test scenario")
	public void getbookertest()
	{
	  Response response= restwrapper.get("/booking/"+BookingId).then().spec(resspec).log().all().extract().response();
	  Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
	  System.out.println(response.jsonPath().getString("firstname"));
	}
	
	@Test(priority = 4,description = "delete booking test scenario")
	public void deletebookingtest()
	{
		String payload=SerializationDeserialization.Authtokenpayload();
		Response AuthtokenResponse=restwrapper.post("/auth", payload).then().spec(resspec).log().all().extract().response();
		Assert.assertEquals(AuthtokenResponse.getStatusLine(), "HTTP/1.1 200 OK");
		String token=AuthtokenResponse.jsonPath().getString("token");
		System.out.println("token is "+token);
		Response response=restwrapper.delete("/booking/"+BookingId,token).then().log().all().extract().response();
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
	}

	

}
