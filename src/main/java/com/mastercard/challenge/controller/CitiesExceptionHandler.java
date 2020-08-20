package com.mastercard.challenge.controller;

import java.io.IOException;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mastercard.challenge.ServiceError;
/**
 * To handle any sort of exception 
 * 
 * @author karim
 *
 */
@ControllerAdvice
public class CitiesExceptionHandler extends ResponseEntityExceptionHandler {

	
	 @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
		    protected ResponseEntity<Object> handleConflict(
		      RuntimeException ex, WebRequest request) {
		        String bodyOfResponse = "This should be application specific";
		        return handleExceptionInternal(ex, bodyOfResponse, 
		          new HttpHeaders(), HttpStatus.CONFLICT, request);
		    }
	 
	 @ExceptionHandler({ Exception.class, IOException.class })
		public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		 ServiceError serviceError = new ServiceError(
		      HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
		    return new ResponseEntity<Object>(
		    		serviceError, new HttpHeaders(), serviceError.getStatus());
		}
	 
	 @Override
		protected ResponseEntity<Object> handleMissingServletRequestParameter(
		  MissingServletRequestParameterException ex, HttpHeaders headers, 
		  HttpStatus status, WebRequest request) {
		    String error = ex.getParameterName() + " Parameter is missing";
		    
		    ServiceError serviceError = 
		      new ServiceError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		    return new ResponseEntity<Object>(
		    		serviceError, new HttpHeaders(), serviceError.getStatus());
		}
	 
	 @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
		public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
		  MethodArgumentTypeMismatchException ex, WebRequest request) {
		    String error = 
		      ex.getName() + " should be of type " + ex.getRequiredType().getName();
		 
		    ServiceError serviceError = 
		      new ServiceError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		    return new ResponseEntity<Object>(
		    		serviceError, new HttpHeaders(), serviceError.getStatus());
		}
	
}
