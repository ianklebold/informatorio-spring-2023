package com.info.javajediprimerapp.service.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    Map<UUID,Book> bookMap;

    public BookServiceImpl() {
        bookMap = new HashMap<>();

        Book book = new Book();
        book.setUuid(UUID.randomUUID());
        book.setAuthor("Gabriel Garcia Marquez");
        book.setTitle("Cien años de soledad");

        Book book2 = new Book();
        book2.setUuid(UUID.randomUUID());
        book2.setAuthor("George Orwell");
        book2.setTitle("1984");

        Book book3 = new Book();
        book3.setUuid(UUID.randomUUID());
        book3.setAuthor("Antoine de Saint-Exupery");
        book3.setTitle("El principito");

        bookMap.put(book.getUuid(),book);
        bookMap.put(book2.getUuid(),book2);
        bookMap.put(book3.getUuid(),book3);
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book createBook(Book book) {
        book.setUuid(UUID.randomUUID());
        bookMap.put(book.getUuid(),book);
        return book;
    }

    @Override
    public Optional<Book> updateBook(UUID uuid,Book bookUpdated) {

        Book book = bookMap.get(uuid);

        if (book != null){
            if (bookUpdated.getTitle() != null){
                book.setTitle(bookUpdated.getTitle());
            }
            if (bookUpdated.getTitle() != null){
                book.setAuthor(bookUpdated.getAuthor());
            }
            return Optional.of(book);
        }else {
            return Optional.empty();
        }
    }
}
