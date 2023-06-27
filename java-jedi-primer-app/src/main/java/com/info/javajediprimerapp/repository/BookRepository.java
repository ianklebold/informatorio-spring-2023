package com.info.javajediprimerapp.repository;

import com.info.javajediprimerapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {}
