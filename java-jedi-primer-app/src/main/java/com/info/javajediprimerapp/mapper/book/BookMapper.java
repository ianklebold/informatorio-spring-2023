package com.info.javajediprimerapp.mapper.book;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;

public interface BookMapper {

    Book bookDTOtoBook(BookDTO bookDTO);

    BookDTO bookToBookDTO(Book book);
}
