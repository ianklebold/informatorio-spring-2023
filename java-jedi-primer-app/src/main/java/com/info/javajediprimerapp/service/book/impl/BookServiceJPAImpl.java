package com.info.javajediprimerapp.service.book.impl;

import com.info.javajediprimerapp.domain.*;
import com.info.javajediprimerapp.exceptions.NotFoundException;
import com.info.javajediprimerapp.mapper.book.BookMapper;
import com.info.javajediprimerapp.mapper.book.BookResponseMapper;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;
import com.info.javajediprimerapp.repository.author.AuthorRepository;
import com.info.javajediprimerapp.repository.book.BookRepository;
import com.info.javajediprimerapp.repository.category.CategoryRepository;
import com.info.javajediprimerapp.service.book.BookService;
import com.info.javajediprimerapp.service.publisher.PublisherService;
import com.info.javajediprimerapp.service.review.ReviewService;
import jakarta.transaction.Transactional;
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

    private final BookMapper bookMapper;

    private final BookResponseMapper bookResponseMapper;
    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;

    private final PublisherService publisherService;

    private final ReviewService reviewService;

    @Override
    public List<BookResponseDTO> getAllBooks() {

        List<BookResponseDTO> bookDTOS = new ArrayList<>();

        for (Book book:bookRepository.findAll()) {
            bookDTOS.add(bookResponseMapper.bookToBookResponseDTO(book));
        }

        return bookDTOS;
    }

    @Override
    @Transactional
    public Book createBook(BookDTO book) throws NotFoundException {

        Book newBook = bookMapper.bookDTOtoBook(book);

        //Relacion BOOK - AUTHOR
        Optional<Author> author = authorRepository.findById(UUID.fromString(book.getIdAuthor()));

        if (author.isPresent()){
            newBook.setAuthor(author.get());
            newBook = bookRepository.save(newBook);
        }else {
            throw new NotFoundException();
        }

        // Relacion BOOK - CATEGORY
        updatingCategoriesBook(newBook,book);

        // Relacion BOOK - Publisher
        Publisher publisher = publisherService.createPublisher(book.getPublisherDTO());
        newBook.setPublisher(publisher);

        // Relacion BOOK - Review
        List<Review> reviews = reviewService.createAListOfReviews(book.getReviews());
        newBook.setReviews(reviews);

        return bookRepository.save(newBook);
    }

    private void updatingCategoriesBook(Book book,BookDTO bookDTO){
        if (!bookDTO.getListCategoriesIds().isEmpty()){

            //TAREA - EVITAR CARGAR DOS CATEGORIAS EN UN LIBRO
            for (String id:bookDTO.getListCategoriesIds()){
                Optional<Category> category = categoryRepository.findById(UUID.fromString(id));
                if (category.isPresent()){
                    book.addCategories(category.get());
                }
            }
        }
    }

    @Override
    public Optional<BookDTO> updateBook(UUID uuidBook, BookDTO bookUpdated) {
        //Buscar libro por ID
        Optional<Book> bookOptional = bookRepository.findById(uuidBook);

        if(bookOptional.isPresent()){
            updatingBook(bookOptional.get(),bookUpdated);
            //Save --> Si existe entonces lo actualiza y sino lo crea.
            bookRepository.saveAndFlush(bookOptional.get());
            return Optional.of(bookMapper.bookToBookDTO(bookOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    private void updatingBook(Book book,BookDTO bookUpdated){

        if (!bookUpdated.getTitle().isBlank()){
            book.setTitle(bookUpdated.getTitle());
        }

        if (!bookUpdated.getIdAuthor().isBlank()){
            Optional<Author> author = authorRepository.findById(UUID.fromString(bookUpdated.getIdAuthor()));

            if (author.isPresent()){
                book.setAuthor(author.get());
            }
        }

        if(bookUpdated.getNumberPages() > 0){
            book.setNumberPages(bookUpdated.getNumberPages());
        }

        if (!bookUpdated.getIsbn().isBlank()){
            book.setIsbn(bookUpdated.getIsbn());
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

        if (bookOptional.isPresent()){
            return Optional.of(bookMapper.bookToBookDTO(bookOptional.get()));
        }
        return Optional.empty();
    }

}
