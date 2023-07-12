package com.info.javajediprimerapp.service.book.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.mapper.book.BookMapper;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import com.info.javajediprimerapp.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    Map<UUID,Book> bookMap;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
        bookMap = new HashMap<>();

        Book book = new Book();
        book.setUuid(UUID.randomUUID());
        //book.setAuthor("Gabriel Garcia Marquez");
        book.setTitle("Cien años de soledad");

        Book book2 = new Book();
        book2.setUuid(UUID.randomUUID());
        //book2.setAuthor("George Orwell");
        book2.setTitle("1984");

        Book book3 = new Book();
        book3.setUuid(UUID.randomUUID());
        //book3.setAuthor("Antoine de Saint-Exupery");
        book3.setTitle("El principito");

        bookMap.put(book.getUuid(),book);
        bookMap.put(book2.getUuid(),book2);
        bookMap.put(book3.getUuid(),book3);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookList = new ArrayList<>();
        for (Book book:bookMap.values()) {
            bookList.add(
                    bookMapper.bookToBookDto(book)
            );
        }
        return bookList;
    }

    @Override
    public Book createBook(BookDTO bookDTO) {

        Book book = new Book();

        book.setUuid(UUID.randomUUID());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(book.getIsbn());
        book.setNumberPages(bookDTO.getNumberPages());

        bookMap.put(book.getUuid(),book);
        return book;
    }

    @Override
    public Optional<Book> updateBook(UUID uuidBook, Book bookUpdated) {
        //Buscamos libro
        Book book = bookMap.get(uuidBook);

        if(book != null){
            updatingBook(book,bookUpdated);
            return Optional.of(book);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteBook(UUID uuidBook) {
        return false;
    }

    @Override
    public Optional<BookDTO> getBookById(UUID uuid) {

        return Optional.of(bookMapper.bookToBookDto(bookMap.get(uuid)));
    }

    private void updatingBook(Book book,Book bookUpdated){

        if (bookUpdated.getTitle() != null){
            book.setTitle(bookUpdated.getTitle());
        }

        if (bookUpdated.getAuthor() != null){
            book.setAuthor(bookUpdated.getAuthor());
        }

    }

    private boolean deleteBookByName(String title) {
        for (Book book : bookMap.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                bookMap.values().remove(book);
                return true;
            }
        }
        return false;
    }
}
