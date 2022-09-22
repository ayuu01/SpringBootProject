package com.assignment.SpringBootAssignment5.controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.SpringBootAssignment5.request.AuthorRequest;
import com.assignment.SpringBootAssignment5.response.AuthorResponse;
import com.assignment.SpringBootAssignment5.service.AuthorService;


@RequestMapping("/author/v1")
@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping("/test")
	public String handleTest() {
		return "welcome";
	}

	@PostMapping
	public String madePurchase(@RequestBody AuthorRequest AuthorRequest) {
		authorService.postAuthor(AuthorRequest);
		return "Author entry is saved";
	}

	@GetMapping
	public List<AuthorResponse> getAllAuthors() {
		return authorService.getAllAuthors();
	}

	@GetMapping("/{id}")
	public AuthorResponse getAuthorById(@PathVariable(name = "id") String id) throws ValidationException {
		return authorService.getAuthorById(id);
	}

	@PutMapping("/{id}")
	public String updateAuthor(@RequestBody AuthorRequest AuthorRequest, @PathVariable(name = "id") String id)
			throws ValidationException {
		authorService.updateAuthor(AuthorRequest, id);
		return "Author is updated";
	}

	@DeleteMapping("/{id}")
	public String deleteAuthor(@PathVariable(name = "id") String id) throws ValidationException {
		authorService.deleteAuthor(id);
		return "Author is deleted";
	}
}
