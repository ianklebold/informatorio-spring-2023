package com.info.javajediprimerapp.service.book.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.repository.BookRepository;
import com.info.javajediprimerapp.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class BookServiceJPAImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceJPAImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        //Metodo find All - Buscar todos los recursos
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        //Metodo save - Guardar recursos
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateBook(UUID uuidBook, Book bookUpdated) {
        //Metodo actualizar recursos
        Optional<Book> book = bookRepository.findById(uuidBook);

        if (book.isEmpty()){
            return Optional.empty();
        }else {
            updatingBook(book.get(),bookUpdated);
            //El metodo save funciona como update
            bookRepository.save(book.get());
            return book;
        }
    }

    private void updatingBook(Book book,Book bookUpdated){

        if (bookUpdated.getTitle() != null){
            book.setTitle(bookUpdated.getTitle());
        }

        if (bookUpdated.getAuthor() != null){
            book.setAuthor(bookUpdated.getAuthor());
        }

    }
}
