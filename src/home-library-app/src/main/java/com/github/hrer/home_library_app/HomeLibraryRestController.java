package com.github.hrer.home_library_app;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeLibraryRestController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private BookRepository bookRepo;
	
	
	@Autowired
	public HomeLibraryRestController(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}



	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value= "name", defaultValue="World") String name) {
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
		
	}
	
	@GetMapping("/bookinfo")
	public Book bookInfo(@RequestParam(value= "name", defaultValue="none") String name) {
		
		return bookRepo.findByTitle(name);
		
	}
}
