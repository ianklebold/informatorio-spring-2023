package com.info.javajediprimerapp.mapper.book.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.mapper.book.BookResponseMapper;
import com.info.javajediprimerapp.mapper.category.CategoryResponseMapper;
import com.info.javajediprimerapp.model.dto.book.BookResponseDTO;
import com.info.javajediprimerapp.model.dto.category.CategoryResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BookResponseMapperImpl implements BookResponseMapper {

    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public BookResponseDTO bookToBookResponseDTO(Book book) {

        BookResponseDTO bookResponseDTO = BookResponseDTO.builder()
                .nombreAuthor(book.getAuthor().getName())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .numberPages(book.getNumberPages())
                .build();

        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();

        for (Category category:book.getCategories()) {
            categoryResponseDTOS.add(categoryResponseMapper.categoryToCategoryResponseDTO(category));
        }
        bookResponseDTO.setCategoryResponseDTOS(categoryResponseDTOS);

        return bookResponseDTO;
    }
}
