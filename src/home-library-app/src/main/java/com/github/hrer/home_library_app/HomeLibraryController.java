package com.github.hrer.home_library_app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller//@RestController
@RequestMapping("/")
public class HomeLibraryController {

	private BookRepository bookRepo;

	@Autowired
	public HomeLibraryController(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	@RequestMapping(value="/{author}", method = RequestMethod.GET) //@GetMapping
	public String getAuthorsBooks(@PathVariable("author") String author, Model model) { //@RequestParam PathVariable yerine
		
		List<Book> bookList =
				bookRepo.findByAuthor(author);
				if (bookList != null) {
				model.addAttribute("books", bookList);
				}
				
				return "bookList";
		
	}
	
	@RequestMapping(value="/{author}", method=RequestMethod.POST)
	public String addToReadingList(
	@PathVariable("author") String author, Book book) {
	book.setAuthor(author);
	bookRepo.save(book);
	return "redirect:/{author}";
	}
}
