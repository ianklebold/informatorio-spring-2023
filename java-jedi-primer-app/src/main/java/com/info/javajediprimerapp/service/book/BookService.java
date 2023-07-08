package com.info.javajediprimerapp.service.book;

import com.info.javajediprimerapp.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    List<Book> getAllBooks();

    Book createBook(Book book);

    Optional<Book> updateBook(UUID uuidBook, Book bookUpdated);

    boolean deleteBook(UUID uuidBook);

    Optional<Book> getBookById(UUID uuid);
}
