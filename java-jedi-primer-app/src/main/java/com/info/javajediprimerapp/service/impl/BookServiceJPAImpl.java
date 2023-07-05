package com.info.javajediprimerapp.service.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.repository.book.BookRepository;
import com.info.javajediprimerapp.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@AllArgsConstructor
public class BookServiceJPAImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll(); //Traer todos los libros
    }

    @Override
    public Book createBook(Book book) {
        book.setUuid(UUID.randomUUID());
        bookRepository.save(book); //Guardar en base de datos
        return book;
    }

    @Override
    public Optional<Book> updateBook(UUID uuidBook, Book bookUpdated) {
        //Buscar libro por ID
        Optional<Book> bookOptional = bookRepository.findById(uuidBook);

        if(bookOptional.isPresent()){
            updatingBook(bookOptional.get(),bookUpdated);
            //Save --> Si existe entonces lo actualiza y sino lo crea.
            bookRepository.saveAndFlush(bookOptional.get());
            return bookOptional;
        }else {
            return Optional.empty();
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

    @Override
    public boolean deleteBook(UUID uuidBook) {
        if (bookRepository.existsById(uuidBook)){
            bookRepository.deleteById(uuidBook);
            return true;
        }
        return false;
    }

}
