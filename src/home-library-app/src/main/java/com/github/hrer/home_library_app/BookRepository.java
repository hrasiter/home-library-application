package com.github.hrer.home_library_app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByAuthor(String author);
	
	Book findById(long Id);
	Book findByTitle(String Name);

}
