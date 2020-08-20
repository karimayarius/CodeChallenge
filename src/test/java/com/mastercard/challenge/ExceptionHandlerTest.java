package com.mastercard.challenge;

import org.junit.jupiter.api.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

/**
 * Skeleton to Test All the Exception Handler 
 * @author karim
 *
 */

public class ExceptionHandlerTest {
	
	private static final String URL_PREFIX = "http://localhost:8080/";
	
	
	private RequestSpecification givenAuth() {

        return RestAssured.given()
            .auth().preemptive().basic("", "");
          
    }
	
	 @Test
	    public void whenTry_thenOK() {
	        final Response response = givenAuth().get(URL_PREFIX + "connected?origin=city1&destination=city2");
	        
	        //assertEquals(200, response.statusCode());
	        //System.out.println(response.asString());

	    }
	
	
	@Test
	public void whenMethodArgumentMismatch_thenBadRequest() {
	    /*Response response = givenAuth().get(URL_PREFIX + "connected?origin=city1&destination=city2");
	    ServiceError error = response.as(ServiceError.class);
	 
	    assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	    assertEquals(1, error.getErrors().size());
	    assertTrue(error.getErrors().get(0).contains("should be of type"));*/
	}

}
