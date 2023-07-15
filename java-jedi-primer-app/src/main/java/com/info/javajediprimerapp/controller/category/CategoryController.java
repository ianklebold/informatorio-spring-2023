package com.info.javajediprimerapp.controller.category;

import com.info.javajediprimerapp.exceptions.NotFoundException;
import com.info.javajediprimerapp.model.dto.category.CategoryDTO;
import com.info.javajediprimerapp.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{idCategory}")
    public CategoryDTO getCategoryById(@PathVariable(name = "idCategory")UUID idCategory) throws NotFoundException {
        return categoryService.getCategoryById(idCategory).orElseThrow(NotFoundException::new);
    }
}
