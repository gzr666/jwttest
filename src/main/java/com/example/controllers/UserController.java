package com.example.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.BookNotFoundException;


@RestController
public class UserController {
	
	 private final Map<String, List<String>> userDb = new HashMap<>();

	 public UserController() {
	        userDb.put("geezer", Arrays.asList("user"));
	        userDb.put("sally", Arrays.asList("user", "admin"));
	    }
	 
	 @RequestMapping(value = "api/login", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	    public LoginResponse login(@RequestBody final UserLogin login)
	         {
	        if (login.name == null || !userDb.containsKey(login.name)) {
	            throw new BookNotFoundException("Error");
	        }
	        return new LoginResponse(Jwts.builder().setSubject(login.name)
	            .claim("roles", userDb.get(login.name)).setIssuedAt(new Date())
	            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
	    }
	 
	 
	 
	 @SuppressWarnings("unused")
	    private static class UserLogin {
	        public String name;
	        public String password;
	    }

	    @SuppressWarnings("unused")
	    private static class LoginResponse {
	        public String token;

	        public LoginResponse(final String token) {
	            this.token = token;
	        }
	    }

}
