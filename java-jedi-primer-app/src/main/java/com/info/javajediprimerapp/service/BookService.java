package com.info.javajediprimerapp.service;

import com.info.javajediprimerapp.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book createBook(Book book);
}
