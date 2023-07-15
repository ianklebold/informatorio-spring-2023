package com.info.javajediprimerapp.mapper.book.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.mapper.author.AuthorMapper;
import com.info.javajediprimerapp.mapper.book.BookMapper;
import com.info.javajediprimerapp.mapper.category.CategoryMapper;
import com.info.javajediprimerapp.mapper.category.CategoryResponseMapper;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;
import com.info.javajediprimerapp.model.dto.category.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookMapperImpl implements BookMapper {
    @Override
    public Book bookDTOtoBook(BookDTO bookDTO) {
        return Book.builder()
                .uuid(UUID.randomUUID())
                .isbn(bookDTO.getIsbn())
                .title(bookDTO.getTitle())
                .numberPages(bookDTO.getNumberPages())
                .build();
    }

    @Override
    public BookDTO bookToBookDTO(Book book) {
        return BookDTO.builder()
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .numberPages(book.getNumberPages())
                .idAuthor(book.getAuthor().getUuid().toString())
                .build();

    }


}
