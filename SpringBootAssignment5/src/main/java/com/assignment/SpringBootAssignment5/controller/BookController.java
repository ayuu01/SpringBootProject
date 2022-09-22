package com.assignment.SpringBootAssignment5.controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.assignment.SpringBootAssignment5.request.BookRequest;
import com.assignment.SpringBootAssignment5.response.BookResponse;
import com.assignment.SpringBootAssignment5.service.BookService;


@RequestMapping("/book/v1")
@RestController
@CrossOrigin
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/test")
	public String handleTest() {
		return "welcome";
	}

	@PostMapping
	public String madePurchase(@RequestBody BookRequest bookRequest) {
		bookService.postBook(bookRequest);
		return "book entry is saved";
	}

	@GetMapping
	public List<BookResponse> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/{id}")
	public BookResponse getBookById(@PathVariable(name = "id") String id) throws ValidationException {
		return bookService.getBookById(id);
	}

	@PutMapping("/{id}")
	public String updateBook(@RequestBody BookRequest bookRequest, @PathVariable(name = "id") String id)
			throws ValidationException {
		bookService.updateBook(bookRequest, id);
		return "book is updated";
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable(name = "id") String id) throws ValidationException {
		bookService.deleteBook(id);
		return "book is deleted";
	}
}
