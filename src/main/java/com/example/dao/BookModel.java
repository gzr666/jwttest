package com.example.dao;

import java.util.ArrayList;
import java.util.List;

public class BookModel
{
	public int id;
	public String name;
	
	public BookModel()
	{
		reviews = new ArrayList<ReviewModel>();
	}

	public List<ReviewModel> reviews;

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

	public List<ReviewModel> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}
	
	

}
