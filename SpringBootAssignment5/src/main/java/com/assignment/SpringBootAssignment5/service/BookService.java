package com.assignment.SpringBootAssignment5.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.assignment.SpringBootAssignment5.dao.BookRepository;
import com.assignment.SpringBootAssignment5.entity.Book;
import com.assignment.SpringBootAssignment5.request.BookRequest;
import com.assignment.SpringBootAssignment5.response.BookResponse;


@Component
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public void postBook(BookRequest bookRequest) {
		Book book = new Book();
		//book.setBookId(bookRequest.getBookId());
		book.setBookName(bookRequest.getBookName());
		book.setBookAuthor(bookRequest.getBookAuthor());
		book.setAuthor(bookRequest.getAuthor());
		book.setDate(new Date());
		bookRepository.save(book);
	}

	public List<BookResponse> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		List<BookResponse> list = new ArrayList<>();
		books.forEach(element -> {
			BookResponse bookResponse = new BookResponse();
			BeanUtils.copyProperties(element, bookResponse);
			list.add(bookResponse);
		});
		return list;
	}

	public BookResponse getBookById(String id) throws ValidationException {
		Optional<Book> book = bookRepository.findById(id);
		if (!book.isPresent()) {
			throw new ValidationException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"book does not exists for id::" + id);
		}
		Book book1 = book.get();
		BookResponse bookResponse = new BookResponse();
		BeanUtils.copyProperties(book1, bookResponse);
		return bookResponse;
	}

	public void updateBook(BookRequest bookRequest, String id) throws ValidationException {
		Optional<Book> book = bookRepository.findById(id);
		if (!book.isPresent()) {
			throw new ValidationException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"book does not exists for id::" + id);
		}
		
		Book book1 = book.get();
		/*
		 * if (!(book1.getBookId().equals(bookRequest.getBookId()))) {
		 * System.out.print("err"); throw new
		 * ValidationException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
		 * "Book id can not be changed::" + id); }
		 */
		if (!(book1.getBookName().equals(bookRequest.getBookName()))) {
			book1.setBookName(bookRequest.getBookName());
		}
		if (!(book1.getBookAuthor().equals(bookRequest.getBookAuthor()))) {
			book1.setBookAuthor(bookRequest.getBookAuthor());
		}
		
		/*
		 * if(!(book1.getAuthor().equals(bookRequest.getAuthor()))) {
		 * book1.setAuthor(bookRequest.getAuthor()); }
		 */
		bookRepository.save(book1);
	}

	public void deleteBook(String id) throws ValidationException {
		Optional<Book> book = bookRepository.findById(id);
		if (!book.isPresent()) {
			throw new ValidationException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"book does not exists for id::" + id);
		}
		bookRepository.deleteById(id);
	}
}
