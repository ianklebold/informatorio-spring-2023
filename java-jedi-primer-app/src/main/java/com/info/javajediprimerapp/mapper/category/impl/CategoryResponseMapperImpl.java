package com.info.javajediprimerapp.mapper.category.impl;

import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.mapper.category.CategoryResponseMapper;
import com.info.javajediprimerapp.model.dto.category.CategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapperImpl implements CategoryResponseMapper {
    @Override
    public CategoryResponseDTO categoryToCategoryResponseDTO(Category category) {
        return CategoryResponseDTO.builder()
                .uuid(category.getUuid())
                .name(category.getName())
                .build();
    }
}
