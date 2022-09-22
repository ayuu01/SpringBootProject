package com.assignment.SpringBootAssignment5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.SpringBootAssignment5.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}