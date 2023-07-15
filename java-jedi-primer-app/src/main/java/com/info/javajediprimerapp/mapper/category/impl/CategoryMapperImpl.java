package com.info.javajediprimerapp.mapper.category.impl;

import com.info.javajediprimerapp.domain.Book;
import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.mapper.book.BookMapper;
import com.info.javajediprimerapp.mapper.category.CategoryMapper;
import com.info.javajediprimerapp.model.dto.book.BookDTO;
import com.info.javajediprimerapp.model.dto.category.CategoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {

    private final BookMapper bookMapper;

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(category.getUuid().toString())
                .name(category.getName())
                .build();

        if (!category.getBooks().isEmpty()){
            List<BookDTO> bookDTOS = new ArrayList<>();

            for (Book book:category.getBooks()) {
                bookDTOS.add(bookMapper.bookToBookDTO(book));
            }
            categoryDTO.setBookDTOS(bookDTOS);
        }
        return categoryDTO;
    }
}
