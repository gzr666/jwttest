package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="book_name")
	@NotNull(message="cannot be null")
	@Size(min=5,message="wrong size")
	private String name;
	
	@OneToMany(mappedBy="book")
	private List<Review> reviews;

	
	public Book()
	{
		reviews = new ArrayList<Review>();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
	
}
