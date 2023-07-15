package com.info.javajediprimerapp.service.category;

import com.info.javajediprimerapp.model.dto.category.CategoryDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    Optional<CategoryDTO> getCategoryById(UUID uuid);
}
