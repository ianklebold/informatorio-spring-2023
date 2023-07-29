package com.info.javajediprimerapp.service.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.exceptions.NotFoundException;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    List<BookResponseDTO> getAllBooks(String title);

    Book createBook(BookDTO book) throws NotFoundException;

    Optional<BookDTO> updateBook(UUID uuidBook, BookDTO bookUpdated);

    boolean deleteBook(UUID uuidBook);

    Optional<BookDTO> getBookById(UUID uuid);
}
