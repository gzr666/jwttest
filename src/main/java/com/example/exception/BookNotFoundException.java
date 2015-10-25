package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public class BookNotFoundException extends RuntimeException {

	String message;
	/**
	 * 
	 */public BookNotFoundException(String message)
	 {
		 this.message=message;
	 }
	
	
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	
	
	
	

}
