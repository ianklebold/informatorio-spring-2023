package com.info.javajediprimerapp.mapper.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;

public interface BookResponseMapper {

    BookResponseDTO bookToBookResponseDTO(Book book);
}
