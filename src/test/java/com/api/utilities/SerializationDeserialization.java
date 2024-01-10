package com.api.utilities;

import java.util.ArrayList;

import com.api.pojos.BookingPojo;
import com.api.pojos.Bookingdates;
import com.api.pojos.AuthTokenPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SerializationDeserialization {

	
	public static String bookingJsonpayload()
	{
	
	  Bookingdates bookingdates=new Bookingdates("2013-02-23", "2013-02-25");
	   
	   BookingPojo booking=new BookingPojo("Vivek", "Sharma", 4000, false, "breakfast", bookingdates);
	   
	   
	   ObjectMapper mapper=new ObjectMapper();
	   try {
		String json=mapper.writeValueAsString(booking);
		return json;
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return "";
		
	}
	
	public static String bookingJsonpayload(BookingPojo booking)
	{
		   
	   ObjectMapper mapper=new ObjectMapper();
	   try {
		String json=mapper.writeValueAsString(booking);
		return json;
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return "";
		
	}
	
	public static String Authtokenpayload()
	{
	
	    AuthTokenPojo auth=new AuthTokenPojo("admin", "password123");	   
	   
	   ObjectMapper mapper=new ObjectMapper();
	   try {
		String json=mapper.writeValueAsString(auth);
		return json;
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return "";
		
	}
	
	
	public static BookingPojo getStudentPojo(String jsonpayload)
	{
		ObjectMapper mapper=new ObjectMapper();
		BookingPojo booking=null;
		try {
			booking = mapper.readValue(jsonpayload, BookingPojo.class);
			return booking;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booking;
	}
	
}
