package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import com.example.entity.Book;
import com.example.entity.Review;
import com.example.exception.GlobalExceptionHandlerController;
import com.example.filters.CorsFilter;
import com.example.filters.JWTFilter;
import com.example.repos.BookRepository;
import com.example.repos.ReviewRepository;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	public BookRepository repo;
	
	@Autowired
	public ReviewRepository repo2;
	
	
	@Bean
	 public FilterRegistrationBean corsFilter() {
	     final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	     registrationBean.setFilter(new CorsFilter());
	     registrationBean.addUrlPatterns("/*");
	     registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

	     return registrationBean;
	  }
	
	
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        
        registrationBean.setFilter(new JWTFilter());
        registrationBean.addUrlPatterns("/api/books/*");

        return registrationBean;
    }
	
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    @PostConstruct
    public void init()
    {
    	
    	
    	Book b1 = new Book();
    	b1.setName("CarlSagan");
    	Book b2 = new Book();
    	b2.setName("CarlSagan Cosmos");
    	
    	Review r1 = new Review();
    	r1.setContent("Great book");
    	r1.setBook(b1);
    	
    	Review r2 = new Review();
    	r2.setContent("Great book again");
    	r2.setBook(b1);
    	
    	Review r3 = new Review();
    	r3.setContent("Great book again by carl");
    	r3.setBook(b2);
    	Review r4 = new Review();
    	r4.setContent("Great book by Castaneda and carl");
    	r4.setBook(b2);
    	
    	
    	repo.save(b1);
    	repo.save(b2);
    	repo2.save(r1);
    	repo2.save(r2);
    	repo2.save(r3);
    	repo2.save(r4);
    	
    	
    	
    	
    	
    	
    }
}

