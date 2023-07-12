package com.info.javajediprimerapp.mapper.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.model.dto.book.BookDTO;

public interface BookMapper {
    BookDTO bookToBookDto(Book book);
}
