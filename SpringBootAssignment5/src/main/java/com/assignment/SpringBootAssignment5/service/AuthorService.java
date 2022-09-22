package com.assignment.SpringBootAssignment5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.assignment.SpringBootAssignment5.dao.AuthorRepository;
import com.assignment.SpringBootAssignment5.entity.Author;
import com.assignment.SpringBootAssignment5.request.AuthorRequest;
import com.assignment.SpringBootAssignment5.response.AuthorResponse;


@Component
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public void postAuthor(AuthorRequest authorRequest) {
		Author author = new Author();
		author.setAuthorId(authorRequest.getAuthorId());
		author.setAuthorName(authorRequest.getAuthorName());
		author.setBook(authorRequest.getBook());
		authorRepository.save(author);
	}

	public List<AuthorResponse> getAllAuthors() {
		List<Author> Authors = authorRepository.findAll();
		List<AuthorResponse> list = new ArrayList<>();
		Authors.forEach(element -> {
			AuthorResponse authorResponse = new AuthorResponse();
			BeanUtils.copyProperties(element, authorResponse);
			list.add(authorResponse);
		});
		return list;
	}

	public AuthorResponse getAuthorById(String id) throws ValidationException {
		Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent()) {
			throw new ValidationException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"Author does not exists for id::" + id);
		}
		Author author1 = author.get();
		AuthorResponse authorResponse = new AuthorResponse();
		BeanUtils.copyProperties(author1, authorResponse);
		return authorResponse;
	}

	public void updateAuthor(AuthorRequest authorRequest, String id) throws ValidationException {
		Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent()) {
			throw new ValidationException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"Author does not exists for id::" + id);
		}
		Author author1 = author.get();
		if (!(author1.getAuthorId().equals(authorRequest.getAuthorId()))) {
			throw new ValidationException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"Author id can not be changed::" + id);
		}
		if (!(author1.getAuthorName().equals(authorRequest.getAuthorName()))) {
			author1.setAuthorName(authorRequest.getAuthorName());
		}
		if(!(author1.getBook().equals(authorRequest.getBook()))) {
			author1.setBook(authorRequest.getBook());
		}
		authorRepository.save(author1);
	}

	public void deleteAuthor(String id) throws ValidationException {
		Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent()) {
			throw new ValidationException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
					"Author does not exists for id::" + id);
		}
		authorRepository.deleteById(id);
	}
}
