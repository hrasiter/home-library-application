package com.github.hrer.home_library_app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private String title;
	private String author;
	private String ISBN;
	private String description;
	
	
	
	public Book() {}
	
	public Book(String title, String author, String iSBN, String description) {
		this.title = title;
		this.author = author;
		ISBN = iSBN;
		this.description = description;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Book [Id=" + Id + ", title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", description="
				+ description + "]";
	}
}
