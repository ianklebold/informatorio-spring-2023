package com.info.javajediprimerapp.service.book.impl;

import com.info.javajediprimerapp.domain.Author;
import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.exceptions.NotFoundException;
import com.info.javajediprimerapp.mapper.book.BookMapper;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import com.info.javajediprimerapp.repository.author.AuthorRepository;
import com.info.javajediprimerapp.repository.book.BookRepository;
import com.info.javajediprimerapp.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@AllArgsConstructor
public class BookServiceJPAImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book:bookRepository.findAll()) {
            bookDTOS.add(
                    bookMapper.bookToBookDto(book)
            );
        }
        return bookDTOS;
    }

    @Override
    public Book createBook(BookDTO bookDTO) throws NotFoundException {

        Book newBook = new Book();

        newBook.setUuid(UUID.randomUUID());
        newBook.setTitle(bookDTO.getTitle());
        newBook.setNumberPages(bookDTO.getNumberPages());
        newBook.setIsbn(bookDTO.getIsbn());

        Optional<Author> author = authorRepository.findById(UUID.fromString(bookDTO.getIdAuthor()));

        if (author.isPresent()){
            newBook.setAuthor(author.get());
            return bookRepository.save(newBook); //Guardar en base de datos
        }else {
            throw new NotFoundException();
        }
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

    @Override
    public Optional<BookDTO> getBookById(UUID uuid) {
        Optional<Book> bookOptional = bookRepository.findById(uuid);
        if (bookOptional.isPresent()) {
            return Optional.of(bookMapper.bookToBookDto(bookOptional.get()));
        }
        return Optional.empty();
    }

}
