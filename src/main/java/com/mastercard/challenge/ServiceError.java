package com.mastercard.challenge;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * This Entity is used for custom Excption and Exception handler
 * Due to short time window it should be moved out to another package
 * @author karim
 *
 */
public class ServiceError {
	
	    private HttpStatus status;
	    private String message;
	    private List<String> errors;
	 
	    public ServiceError(HttpStatus status, String message, List<String> errors) {
	        super();
	        this.status = status;
	        this.message = message;
	        this.errors = errors;
	    }
	 
	    public ServiceError(HttpStatus status, String message, String error) {
	        super();
	        this.status = status;
	        this.message = message;
	        errors = Arrays.asList(error);
	    }

		public HttpStatus getStatus() {
			return status;
		}

		public void setStatus(HttpStatus status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public List<String> getErrors() {
			return errors;
		}

		public void setErrors(List<String> errors) {
			this.errors = errors;
		}
	    
	    

}
