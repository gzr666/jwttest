package com.example.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Book;


public interface BookRepository extends JpaRepository<Book,Integer>
{
	

}
