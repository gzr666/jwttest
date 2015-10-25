package com.example.controllers;

import io.jsonwebtoken.Claims;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.neo4j.cypher.internal.compiler.v2_1.planner.logical.findShortestPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.BookModel;
import com.example.entity.Book;
import com.example.exception.BookNotFoundException;
import com.example.helpers.ModelFactory;
import com.example.repos.BookRepository;


@RestController
public class BooksController {
	
	public BookRepository repo;
	
	@Autowired
	public BooksController(BookRepository repo)
	{
		this.repo=repo;
	}
	
	@RequestMapping(value="api/books",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<BookModel>> getBooks(final HttpServletRequest request)
	{
		final Claims claims = (Claims) request.getAttribute("claims");
		List<String> roles = (List<String>) claims.get("roles");
		if(roles.contains("admin"))
		{
		
		return new ResponseEntity<List<BookModel>>(ModelFactory.CreateBooks(repo.findAll()),HttpStatus.OK);
		}
		else
		{
			throw new BookNotFoundException("access denied");
		}
		
		
	}
	
	@RequestMapping(value="api/books/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookModel> getBook(@PathVariable("id") int id)
	{
		Book book = repo.findOne(id);
		if (book==null)
		{
			
			throw new BookNotFoundException(Integer.toString(id));
			
		
		}
			
		return new ResponseEntity<BookModel>(ModelFactory.Create(book),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="api/books",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> saveBook(@Validated @RequestBody Book book,HttpServletResponse  response,HttpServletRequest  request)
	{
		
		Book n =repo.saveAndFlush(book);
		
		response.addHeader("Location", request.getRequestURL().toString() + n.getId());
		return new ResponseEntity<Book> (HttpStatus.CREATED);
		
	}

}
