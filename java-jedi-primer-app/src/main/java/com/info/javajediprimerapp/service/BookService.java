package com.info.javajediprimerapp.service;

import com.info.javajediprimerapp.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book createBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(String name);
}
