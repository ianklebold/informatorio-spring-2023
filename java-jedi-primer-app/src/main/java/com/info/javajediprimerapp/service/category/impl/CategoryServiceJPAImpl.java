package com.info.javajediprimerapp.service.category.impl;

import com.info.javajediprimerapp.domain.Category;
import com.info.javajediprimerapp.mapper.category.CategoryMapper;
import com.info.javajediprimerapp.model.dto.category.CategoryDTO;
import com.info.javajediprimerapp.repository.category.CategoryRepository;
import com.info.javajediprimerapp.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceJPAImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category:categoryRepository.findAll()) {
            categoryDTOS.add(categoryMapper.categoryToCategoryDTO(category));
        }

        return categoryDTOS;
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(UUID uuid) {
        Optional<Category> category = categoryRepository.findById(uuid);

        if (category.isPresent()){
            return Optional.of(categoryMapper.categoryToCategoryDTO(category.get()));
        }

        return Optional.empty();
    }
}
