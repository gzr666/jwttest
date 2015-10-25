package com.example.exception;

import javax.servlet.ServletException;

public class NoTokenException extends ServletException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;
	/**
	 * 
	 */public NoTokenException(String message)
	 {
		 this.message=message;
	 }
	
	

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	
	

}
