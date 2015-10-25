package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    
    public ErrorTester noRequestHandlerFoundExceptionHandler(BookNotFoundException e) {
        
        return new ErrorTester("There is no book with ID: " + e.getMessage());
    }
	
	@ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    
    public ErrorTester noRequestHandlerFoundExceptionHandler(InvalidTokenException e) {
        
        return new ErrorTester("Invalid Token...Try again with valid token");
    }
	
	@ExceptionHandler(NoTokenException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    
    public ErrorTester noRequestHandlerFoundExceptionHandler(NoTokenException e) {
        
        return new ErrorTester("There is no book with ID: " + e.getMessage());
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ResponseBody
    
    public ErrorTester noRequestHandlerFoundExceptionHandler(MethodArgumentNotValidException e) {
        
		BindingResult result = e.getBindingResult();
	    FieldError error = result.getFieldError();
	    
		
        return new ErrorTester("There is error " + error.getDefaultMessage() );
    }
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ResponseBody
   
   public ErrorTester noRequestHandlerFoundExceptionHandler(HttpMessageNotReadableException e) {
       
		
		
       return new ErrorTester("Message format must be valid JSON");
   }
	
	
}
