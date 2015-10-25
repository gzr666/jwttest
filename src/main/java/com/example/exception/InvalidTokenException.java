package com.example.exception;

import io.jsonwebtoken.SignatureException;

public class InvalidTokenException extends SignatureException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message;
	
	public InvalidTokenException(String message) {
		super(message);
		this.message = message;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	

}
