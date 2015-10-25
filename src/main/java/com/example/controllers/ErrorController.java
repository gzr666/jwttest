package com.example.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.BookModel;
import com.example.entity.Book;
import com.example.exception.BookNotFoundException;
import com.example.exception.InvalidTokenException;

@RestController
public class ErrorController {

	@RequestMapping(value="api/notoken",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookModel> getBook()
	{
		
			
			throw new BookNotFoundException("Provide a valid token");
			
		
	}
	
	@RequestMapping(value="api/wrongtoken",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookModel> wrongtoken()
	{
		
			
			throw new InvalidTokenException("Provide a valid token");
			
		
	}
	
}
