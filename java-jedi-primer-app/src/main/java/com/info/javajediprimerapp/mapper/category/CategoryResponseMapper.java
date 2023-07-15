package com.info.javajediprimerapp.mapper.category;

import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.model.dto.category.CategoryResponseDTO;

public interface CategoryResponseMapper {

    CategoryResponseDTO categoryToCategoryResponseDTO(Category category);
}
