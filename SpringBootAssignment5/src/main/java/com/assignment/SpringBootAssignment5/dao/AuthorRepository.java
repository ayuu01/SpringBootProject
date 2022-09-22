package com.assignment.SpringBootAssignment5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.SpringBootAssignment5.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, String> {

}