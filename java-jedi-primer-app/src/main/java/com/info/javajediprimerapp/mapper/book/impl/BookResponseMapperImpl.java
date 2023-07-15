package com.info.javajediprimerapp.mapper.book.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.mapper.author.AuthorMapper;
import com.info.javajediprimerapp.mapper.book.BookResponseMapper;
import com.info.javajediprimerapp.mapper.category.CategoryResponseMapper;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;
import com.info.javajediprimerapp.model.dto.category.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookResponseMapperImpl implements BookResponseMapper {
    private final AuthorMapper authorMapper;
    private final CategoryResponseMapper categoryResponseMapper;


    @Override
    public BookResponseDTO bookToBookResponseDTO(Book book) {
        BookResponseDTO bookResponseDTO = BookResponseDTO.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .numberPages(book.getNumberPages())
                .authorDTO(authorMapper.authorToAuthorDto(book.getAuthor()))
                .build();

        List<CategoryResponseDTO> categoryDTOS = new ArrayList<>();
        if (!book.getCategories().isEmpty()){
            for (Category category: book.getCategories()) {
                categoryDTOS.add(categoryResponseMapper.categoryToCategoryResponseDTO(category));
            }
        }
        bookResponseDTO.setCategoryDTOS(categoryDTOS);

        return bookResponseDTO;
    }
}
