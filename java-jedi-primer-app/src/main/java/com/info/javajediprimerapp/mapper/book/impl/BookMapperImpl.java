package com.info.javajediprimerapp.mapper.book.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.mapper.book.BookMapper;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookDTO bookToBookDto(Book book) {

        if (book != null){
            return BookDTO.builder()
                    .isbn(book.getIsbn())
                    .title(book.getTitle())
                    .numberPages(book.getNumberPages())
                    .idAuthor(book.getAuthor().getUuid().toString())
                    .build();
        }
        return null;
    }
}
